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


     /*-------------------------------------------------��ת����-------------------------------------------------*/


    /**
     * ����ϵͳ��־ҳ��
     * @return
     */
    @RequestMapping(value = "/manageLog.htm")
     public String manageLog(){
         return "system/log/manageLog";
     }


     /*-------------------------------------------------��������-------------------------------------------------*/

    /**
     * ��ȡϵͳ��־�б�
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
         log.debug("�����ȡϵͳ��־����,��ǰ������:logList");
         LayuiPage<SystemLog> ret = null;
         try{
             ret = service.listLog(systemLog,condition,startTime,endTime);
         }catch (Exception e){
             log.error("��ȡϵͳ��־�б�ʧ��");
             log.errorPrintStacktrace(e);
         }
         return ret;
     }


    /**
     * ɾ��ϵͳ��־����
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
             log.debug("����ɾ��ϵͳ��־����,��ǰ������:logDelete,��ǰ����id:"+service.objectToJson(condition.getIds()));
             this.service.deleteLog(condition.getIds());

             log.debug("ɾ��ϵͳ��־�ɹ�,ID:"+condition.getIds());
             ret = new HttpResponse(HttpResponse.Status.SUCCESS,"ɾ��ϵͳ��־�ɹ�",null);
         }catch (Exception e){
            log.error("ɾ��ϵͳ��־ʧ��");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE,"ɾ��ϵͳ��־ʧ��:ԭ��:"+e.getMessage(),null);
         }
         return ret;
     }








}
