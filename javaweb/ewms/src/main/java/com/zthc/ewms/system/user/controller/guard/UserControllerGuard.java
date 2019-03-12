package com.zthc.ewms.system.user.controller.guard;

import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import com.zthc.ewms.system.dept.service.OrganizationService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.useDep.entity.UseDep;
import com.zthc.ewms.system.user.dao.guard.UserDaoGuard;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserCondition;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserService;

import drk.system.Log;
import drk.util.MD5Util;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserControllerGuard {

    @Resource(name = "userService")
    public UserService service;
    @Resource(name = "logService")
    public LogService logService;
    @Resource(name = "organizationService")
    public OrganizationService orgService;
    private final static Log log = Log.getLog("com.system.user.controller.guard.UserControllerGuard");


    /**
     * 表单提交时，字段所用前缀，只提交一个对象时，可以不写此前缀
     */
    @InitBinder("us")
    public void initUserBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("us.");
    }

    /**
     * 管理页面
     */
    @RequestMapping(value = "/manageUser.htm")
    public String manageUser(@ModelAttribute("us") User obj, HttpServletRequest request, HttpServletResponse response,
                             Model model) {
        return "system/user/manageUser";
    }


    /**
     * 保存记录
     */
    @RequestMapping(value = "/saveUser.json")
    @ResponseBody
    public HttpResponse saveUser(@ModelAttribute("us") User obj, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "新增失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        BaseLocal local = UserDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        UserDaoGuard.setThreadLocal(local);
        Organization organization = orgService.getOrganizationOne(obj.getParentId());
        Integer parentId = obj.getParentId();
        if(organization.getParentId() != 0){
        	Organization ogtion = orgService.getOrganizationOne(organization.getParentId());
        	parentId = ogtion.getId();
        }else{
        	parentId = organization.getId();
        }
        obj.setZtId(parentId);
        //部门赋值
        Depart depart = new Depart(obj.getParentId());
        obj.setParent(depart);
        //科室区队赋值
        UseDep useDep = new UseDep(obj.getOfficesId());
        obj.setOffices(useDep);
        obj.setUserType(UserEnums.TypeEnum.EWMS.getType());
        obj.setPassWord(MD5Util.MD5("000000"));

        User user = this.service.getUserByCode(obj.getCode());
        if (user != null) {
            return new HttpResponse(HttpResponse.Status.FAILURE, "此编码已存在！", null);
        }
        try {
            this.service.saveUser(obj);
            //设置返回参数
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "新增成功！", obj);
            //日志
            this.logService.addSystemLog(0, SystemLogEnums.LogObject.PERSON_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                    "新增人员:" + obj.getName(), userIp, (int) userId);
        } catch (Exception e) {
            log.error("新增失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    /**************************** 添加方法 ****************************************/
    // select条件列表

    /**
     * 更新方法
     */
    @RequestMapping(value = "/updateUser.json")
    @ResponseBody
    public HttpResponse updateUser(@ModelAttribute("us") User obj, UserCondition condition, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "修改失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        BaseLocal local = UserDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        UserDaoGuard.setThreadLocal(local);
        //部门赋值
        Depart depart = new Depart(obj.getParentId());
        obj.setParent(depart);
        //科室区队赋值
        UseDep useDep = new UseDep(obj.getOfficesId());
        obj.setOffices(useDep);
        try {
            List<User> users = this.service.listUserCode(obj);
            if (users.size() == 0) {
                int i = this.service.updateUser(obj);
                //设置返回参数
                if (1 == i) {
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS, "修改成功！", obj);
                    //日志
                    this.logService.addSystemLog(0, SystemLogEnums.LogObject.PERSON_MANAGEMENT.getCode(),
                            SystemLogEnums.LogAction.EDIT.getCode(), "修改人员:" + obj.getName(), userIp, (int) userId);
                }
            } else {
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "此编码已存在！", null);
            }
        } catch (Exception e) {
            log.error("修改失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    //select条件列表
    @RequestMapping(value = "/showUsers.json")
    @ResponseBody
    public HttpResponse showUsers(@ModelAttribute("us") User obj, UserCondition condition,
                                  HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "查询失败！", obj);
        try {
            User us = this.service.getUserOne(obj.getId());
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "查询成功！", us);
        } catch (Exception e) {
            log.error("查询失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    //delete

    /**
     * 批量删除方法
     */
    @RequestMapping(value = "/delUsers.json")
    @ResponseBody
    public HttpResponse delUsers(UserCondition condition, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除失败！", condition);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        try {
            int i = this.service.delUsers(condition);
            //设置返回参数
            if (i > 0) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "删除成功！", condition);
                //日志
                logService.addSystemLog(0, SystemLogEnums.LogObject.PERSON_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.DELETE.getCode(), "删除人员:roleCode=" + condition.getIds().toString(),
                        userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("删除失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}

