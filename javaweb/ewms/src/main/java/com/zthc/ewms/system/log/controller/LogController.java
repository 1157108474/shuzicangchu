package com.zthc.ewms.system.log.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.log.entity.SystemLog;
import com.zthc.ewms.system.log.entity.SystemLogCondition;
import com.zthc.ewms.system.log.service.LogService;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/system/log")
public class LogController {

    @Resource(name = "logService")
    public LogService service;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.log.controller.LogController");
    }


     /*-------------------------------------------------跳转方法-------------------------------------------------*/


    /**
     * 进入系统日志页面
     * @return
     */
    @RequestMapping(value = "/manageLog.htm")
     public String manageLog(){
         return "system/log/manageLog";
     }


     /*-------------------------------------------------基础方法-------------------------------------------------*/

    /**
     * 获取系统日志列表
     * @param systemLog
     * @param condition
     * @param startTime
     * @param endTime
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listLog.json")
    @ResponseBody
     public LayuiPage<SystemLog> logList(@ModelAttribute("SystemLog") SystemLog systemLog, SystemLogCondition condition,
                                         String startTime,String endTime,HttpServletRequest request,
                                         HttpServletResponse response){
         log.debug("进入获取系统日志方法,当前方法名:logList");
         LayuiPage<SystemLog> ret = null;
         try{
             ret = service.listLog(systemLog,condition,startTime,endTime);
         }catch (Exception e){
             log.error("获取系统日志列表失败");
             log.errorPrintStacktrace(e);
         }
         return ret;
     }


    /**
     * 删除系统日志方法
     * @param condition
     * @param request
     * @param response
     * @return
     */
     @RequestMapping(value = "/deleteLog.json")
     @ResponseBody
     public HttpResponse logDelete(SystemLogCondition condition, HttpServletRequest request,
                                   HttpServletResponse response){
         HttpResponse ret;
         try{
             log.debug("进入删除系统日志方法,当前方法名:logDelete,当前参数id:"+service.objectToJson(condition.getIds()));
             this.service.deleteLog(condition.getIds());

             log.debug("删除系统日志成功,ID:"+condition.getIds());
             ret = new HttpResponse(HttpResponse.Status.SUCCESS,"删除系统日志成功",null);
         }catch (Exception e){
            log.error("删除系统日志失败");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE,"删除系统日志失败:原因:"+e.getMessage(),null);
         }
         return ret;
     }








}
