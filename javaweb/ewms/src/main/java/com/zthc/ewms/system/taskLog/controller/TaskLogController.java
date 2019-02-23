package com.zthc.ewms.system.taskLog.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.taskLog.entity.TaskLog;
import com.zthc.ewms.system.taskLog.entity.TaskLogCondition;
import com.zthc.ewms.system.taskLog.entity.TaskLogView;
import com.zthc.ewms.system.taskLog.service.TaskLogService;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/system/taskLog")
public class TaskLogController {

    @Resource(name = "taskLogService")
    public TaskLogService service;


    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.log.controller.LogController");
    }


     /*-------------------------------------------------跳转方法-------------------------------------------------*/

    /**
     * 进入接口日志界面
     * @return
     */
    @RequestMapping(value = "/manageTaskLog.htm")
    public String manageTaskLog(){return "system/taskLog/manageTaskLog";}







     /*-------------------------------------------------基础方法-------------------------------------------------*/

    /**
     * 获取接口日志列表方法
     * @param taskLog
     * @param condition
     * @param startTime
     * @param endTime
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listTaskLog.json")
    @ResponseBody
     public LayuiPage<TaskLogView> taskLogList(@ModelAttribute("TaskLogView")TaskLogView taskLog, TaskLogCondition condition,
                                               String startTime, String endTime,
                                               HttpServletRequest request, HttpServletResponse response){
         log.debug("进入获取接口日志方法,方法名:taskLogList");
         LayuiPage<TaskLogView> ret = null;
         try{
             ret = service.listTaskLog(taskLog,condition,startTime,endTime);
         }catch (Exception e){
             log.error("获取接口日志列表失败");
             log.errorPrintStacktrace(e);
         }
         return ret;
     }

    /**
     * 删除接口日志方法
     * @param condition
     * @param request
     * @param response
     * @return
     */
     @RequestMapping(value = "/deleteTaskLog.json")
     @ResponseBody
     public HttpResponse taskLogDelete(TaskLogCondition condition, HttpServletRequest request,
                                       HttpServletResponse response){
         HttpResponse ret;
         log.debug("进入接口日志删除方法,当前方法名:taskLogDelete,当前id为:"+condition.getIds());
         try{
             this.service.taskLogDelete(condition.getIds());

             log.debug("删除接口日志成功,ID:"+condition.getIds());
             ret = new HttpResponse(HttpResponse.Status.SUCCESS,"删除成功",null);

         }catch (Exception e){
             log.error("删除接口日志失败");
             log.errorPrintStacktrace(e);
             ret = new HttpResponse(HttpResponse.Status.FAILURE,"删除失败,原因:"+e.getMessage(),null);
         }
         return ret;
     }
}
