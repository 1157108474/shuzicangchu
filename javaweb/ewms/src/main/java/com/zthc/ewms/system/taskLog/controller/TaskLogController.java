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


     /*-------------------------------------------------��ת����-------------------------------------------------*/

    /**
     * ����ӿ���־����
     * @return
     */
    @RequestMapping(value = "/manageTaskLog.htm")
    public String manageTaskLog(){return "system/taskLog/manageTaskLog";}







     /*-------------------------------------------------��������-------------------------------------------------*/

    /**
     * ��ȡ�ӿ���־�б���
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
         log.debug("�����ȡ�ӿ���־����,������:taskLogList");
         LayuiPage<TaskLogView> ret = null;
         try{
             ret = service.listTaskLog(taskLog,condition,startTime,endTime);
         }catch (Exception e){
             log.error("��ȡ�ӿ���־�б�ʧ��");
             log.errorPrintStacktrace(e);
         }
         return ret;
     }

    /**
     * ɾ���ӿ���־����
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
         log.debug("����ӿ���־ɾ������,��ǰ������:taskLogDelete,��ǰidΪ:"+condition.getIds());
         try{
             this.service.taskLogDelete(condition.getIds());

             log.debug("ɾ���ӿ���־�ɹ�,ID:"+condition.getIds());
             ret = new HttpResponse(HttpResponse.Status.SUCCESS,"ɾ���ɹ�",null);

         }catch (Exception e){
             log.error("ɾ���ӿ���־ʧ��");
             log.errorPrintStacktrace(e);
             ret = new HttpResponse(HttpResponse.Status.FAILURE,"ɾ��ʧ��,ԭ��:"+e.getMessage(),null);
         }
         return ret;
     }
}
