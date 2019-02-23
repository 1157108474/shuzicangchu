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
     * 获取列表数据
     */
//	@RequestMapping("/listInfTask.json")
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseBody
    public LayuiPage<InfTask> listInfTask( InfTaskCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
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
            log.error("获取任务管理数据列表出错！");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();

        }
        return ret;
    }

    /**
     * 保存记录
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse saveInfTask(InfTask obj, InfTaskCondition condition,
                                    HttpServletRequest request) {
        log.debug("新增提交" + obj.getName());
        HttpResponse ret;
        try {
            Integer userId = 0;
            String userIp = "";
            //检查 名字是否重复
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
//			local.setCurrentUserId(userId); //把当前操作人ID存入当前线程对象中
//			InfTaskDaoGuard.setThreadLocal(local);
                this.service.saveInfTask(obj, condition);
                logService.addSystemLog(0, SystemLogEnums.LogObject.TASK_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                        "新增任务:" + obj.getName(), userIp, userId);
                ret = new HttpResponse(obj);
            } else {
                log.debug("名称重复");
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "名称重复，请修改后再提交", null);
            }
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);

            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), obj);
        }
        return ret;

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public HttpResponse updateInfTask(InfTask obj, HttpServletRequest request) {
        log.debug("修改提交" + obj.getName());
        HttpResponse ret;
        try {
            Integer userId = 0;
            String userIp = "";
            //检查 名字是否重复
            if (this.service.checkNotExit(obj)) {

                HttpSession session = request.getSession();
                if (session.getAttribute("userId") != null) {
                    userId = Integer.parseInt(session.getAttribute("userId").toString());
                    userIp = session.getAttribute("userIp").toString();
                }
                obj.setUpdater(userId);
                obj.setUpdateDate(new Date());
//			BaseLocal local = InfTaskDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //把当前操作人ID存入当前线程对象中
//			InfTaskDaoGuard.setThreadLocal(local);
                this.service.updateTask(obj);
                logService.addSystemLog(0, SystemLogEnums.LogObject.TASK_MANAGEMENT.getCode(), SystemLogEnums.LogAction.EDIT.getCode(),
                        "修改任务:" + obj.getName(), userIp, userId);
                ret = new HttpResponse(obj);
            } else {
                log.debug("名称重复");
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "名称重复，请修改后再提交", null);
            }
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), obj);
        }
        return ret;
    }


    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "{ids}")
    public HttpResponse deleteInfTask(@PathVariable("ids") String ids, HttpServletRequest request) {
        log.debug("删除提交，id in :" + ids);
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
                    "删除任务:id in :" + ids, userIp, userId);

            ret = new HttpResponse(ids);
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), ids);
        }
        return ret;
    }


}

	
