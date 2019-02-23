package com.zthc.ewms.system.task.controller;

import com.hckj.base.mvc.HttpResponse;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.task.controller.guard.InfTaskControllerGuard;
import com.zthc.ewms.system.task.entity.guard.InfTask;
import com.zthc.ewms.system.task.entity.guard.InfTaskCondition;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/system/task")
public class InfTaskController extends InfTaskControllerGuard {

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.task.controller.InfTaskController");
    }


    @Resource(name = "logService")
    public LogService logService;


    /**********************************************************/

    /**
     * ��ȡ�б�����
     */
//	@RequestMapping("/listInfTask.json")
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseBody
    public LayuiPage<InfTask> listInfTask( InfTaskCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<InfTask> ret = null;
        try {
            String name = request.getParameter("name");
            if(name !=null) {
               name = new String(name.getBytes("ISO-8859-1"), "utf-8");
               log.debug("utf-8:"+name);
                log.debug("gbk:"+new String(name.getBytes("ISO-8859-1"), "GBK"));
            }
            ret = this.service.listInfTask( condition, name);
        } catch (Exception e) {
            log.error("��ȡ������������б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();

        }
        return ret;
    }

    /**
     * �����¼
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse saveInfTask(InfTask obj, InfTaskCondition condition,
                                    HttpServletRequest request) {
        log.debug("�����ύ" + obj.getName());
        HttpResponse ret;
        try {
            Integer userId = 0;
            String userIp = "";
            //��� �����Ƿ��ظ�
            if (this.service.checkNotExit(obj)) {

                HttpSession session = request.getSession();
                if (session.getAttribute("userId") != null) {
                    userId = Integer.parseInt(session.getAttribute("userId").toString());
                    userIp = session.getAttribute("userIp").toString();
                }
                obj.setCreator(userId);
                obj.setCreateDate(new Date());
                obj.setAddType(DictionaryEnums.AddType.EWMS.getCode());
//			obj.setStatus(DictionaryEnums.Status.ENABLE.getCode());
//			BaseLocal local = InfTaskDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //�ѵ�ǰ������ID���뵱ǰ�̶߳�����
//			InfTaskDaoGuard.setThreadLocal(local);
                this.service.saveInfTask(obj, condition);
                logService.addSystemLog(0, SystemLogEnums.LogObject.TASK_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                        "��������:" + obj.getName(), userIp, userId);
                ret = new HttpResponse(obj);
            } else {
                log.debug("�����ظ�");
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "�����ظ������޸ĺ����ύ", null);
            }
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);

            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), obj);
        }
        return ret;

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public HttpResponse updateInfTask(InfTask obj, HttpServletRequest request) {
        log.debug("�޸��ύ" + obj.getName());
        HttpResponse ret;
        try {
            Integer userId = 0;
            String userIp = "";
            //��� �����Ƿ��ظ�
            if (this.service.checkNotExit(obj)) {

                HttpSession session = request.getSession();
                if (session.getAttribute("userId") != null) {
                    userId = Integer.parseInt(session.getAttribute("userId").toString());
                    userIp = session.getAttribute("userIp").toString();
                }
                obj.setUpdater(userId);
                obj.setUpdateDate(new Date());
//			BaseLocal local = InfTaskDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //�ѵ�ǰ������ID���뵱ǰ�̶߳�����
//			InfTaskDaoGuard.setThreadLocal(local);
                this.service.updateTask(obj);
                logService.addSystemLog(0, SystemLogEnums.LogObject.TASK_MANAGEMENT.getCode(), SystemLogEnums.LogAction.EDIT.getCode(),
                        "�޸�����:" + obj.getName(), userIp, userId);
                ret = new HttpResponse(obj);
            } else {
                log.debug("�����ظ�");
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "�����ظ������޸ĺ����ύ", null);
            }
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), obj);
        }
        return ret;
    }


    /**
     * ɾ��
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "{ids}")
    public HttpResponse deleteInfTask(@PathVariable("ids") String ids, HttpServletRequest request) {
        log.debug("ɾ���ύ��id in :" + ids);
        HttpResponse ret;
        try {
            Integer userId = 0;
            String userIp = "";

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                userId = Integer.parseInt(session.getAttribute("userId").toString());
                userIp = session.getAttribute("userIp").toString();
            }
            this.service.deleteTask(ids, userId);
            logService.addSystemLog(0, SystemLogEnums.LogObject.TASK_MANAGEMENT.getCode(), SystemLogEnums.LogAction.DELETE.getCode(),
                    "ɾ������:id in :" + ids, userIp, userId);

            ret = new HttpResponse(ids);
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), ids);
        }
        return ret;
    }


}

	
