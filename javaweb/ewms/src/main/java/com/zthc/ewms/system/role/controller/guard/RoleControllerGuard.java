package com.zthc.ewms.system.role.controller.guard;

import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.role.dao.guard.RoleDaoGuard;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.role.service.RoleService;
import drk.system.Log;
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
import java.util.Map;


public class RoleControllerGuard {
    @Resource(name = "roleService")
    public RoleService service;
    @Resource(name = "logService")
    public LogService logService;

    private final static Log log = Log.getLog("com.system.role.controller.guard.roleControllerGuard");

    /**
     * 表单提交时，字段所用前缀，只提交一个对象时，可以不写此前缀
     */
    @InitBinder("r")
    public void initRoleBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("r.");
    }

    /**
     * 管理页面
     */
    @RequestMapping(value = "/manageRole.htm")
    public String manageRole(@ModelAttribute("r") Role obj) {
        return "system/role/manageRole";
    }

    /**
     * 新增页面
     */
    @RequestMapping(value = "/addRole.htm")
    public String addRole(@ModelAttribute("r") Role obj, HttpServletRequest request, HttpServletResponse response,
                          Model model) {
        return editRole(obj, request, response, model);
    }

    /**
     * 编辑页面
     */
    @RequestMapping(value = "/editRole.htm")
    public String editRole(@ModelAttribute("r") Role obj, HttpServletRequest request,
                           HttpServletResponse response, Model model) {
        Role r = this.service.getRoleOne(obj.getRoleCode());

        model.addAttribute("r", r);
        return "system/role/editRole";
    }

    /**
     * 保存记录
     */
    @RequestMapping(value = "/saveRole.json")
    @ResponseBody
    public HttpResponse<Role> saveRole(@ModelAttribute("r") Role obj, RoleCondition condition, HttpServletRequest request) {
        HttpResponse<Role> ret = new HttpResponse(HttpResponse.Status.FAILURE, "新增失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        BaseLocal local = RoleDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        RoleDaoGuard.setThreadLocal(local);

        try {
            Role r = this.service.getRoleOne(obj.getRoleCode());
            if (null == r) {
                this.service.saveRole(obj, condition);
                //设置返回参数
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "新增成功！", obj);
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.ROLE_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                        "新增角色:" + obj.getRoleName(), userIp, (int) userId);
            } else {
                ret.setMessage("角色编号重复!");
            }
        } catch (Exception e) {
            log.error("新增角色:" + obj.getRoleName() + "失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**************************** 添加方法 ****************************************/
    // select条件列表

    /**
     * 更新方法
     */
    @RequestMapping(value = "/updateRole.json")
    @ResponseBody
    public HttpResponse<Role> updateRole(@ModelAttribute("r") Role obj, RoleCondition condition, HttpServletRequest request) {
        HttpResponse<Role> ret = new HttpResponse(HttpResponse.Status.FAILURE, "修改失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        BaseLocal local = RoleDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        RoleDaoGuard.setThreadLocal(local);

        try {
            int i = this.service.updateRole(obj, condition);
            //设置返回参数
            if (1 == i) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "修改成功！", obj);
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.ROLE_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.EDIT.getCode(), "修改角色:" + obj.getRoleName(), userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("修改失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 查看页面
     */
    @RequestMapping(value = "/showRole.htm")
    public String showRole(@ModelAttribute("r") Role obj, Model model) {
        Role role = this.service.getRoleOne(obj.getRoleCode());
        model.addAttribute("role", role);
        return "system/showRole";
    }

    /**
     * 批量删除方法
     */
    @RequestMapping(value = "/delRoles.json")
    @ResponseBody
    public HttpResponse<RoleCondition> delRoles(RoleCondition condition, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除失败！", condition);
        log.debug("删除提交" + condition.getRoleCodes().toString());
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        try {
            int i = this.service.delRoles(condition);
            //设置返回参数
            if (i > 0) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "删除成功！", condition);
                logService.addSystemLog(0, SystemLogEnums.LogObject.ROLE_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.DELETE.getCode(), "删除角色:roleCode=" + condition.getRoleCodes().toString(),
                        userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("删除失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}

