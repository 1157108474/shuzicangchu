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
     * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
     */
    @InitBinder("r")
    public void initRoleBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("r.");
    }

    /**
     * ����ҳ��
     */
    @RequestMapping(value = "/manageRole.htm")
    public String manageRole(@ModelAttribute("r") Role obj) {
        return "system/role/manageRole";
    }

    /**
     * ����ҳ��
     */
    @RequestMapping(value = "/addRole.htm")
    public String addRole(@ModelAttribute("r") Role obj, HttpServletRequest request, HttpServletResponse response,
                          Model model) {
        return editRole(obj, request, response, model);
    }

    /**
     * �༭ҳ��
     */
    @RequestMapping(value = "/editRole.htm")
    public String editRole(@ModelAttribute("r") Role obj, HttpServletRequest request,
                           HttpServletResponse response, Model model) {
        Role r = this.service.getRoleOne(obj.getRoleCode());

        model.addAttribute("r", r);
        return "system/role/editRole";
    }

    /**
     * �����¼
     */
    @RequestMapping(value = "/saveRole.json")
    @ResponseBody
    public HttpResponse<Role> saveRole(@ModelAttribute("r") Role obj, RoleCondition condition, HttpServletRequest request) {
        HttpResponse<Role> ret = new HttpResponse(HttpResponse.Status.FAILURE, "����ʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        BaseLocal local = RoleDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
        RoleDaoGuard.setThreadLocal(local);

        try {
            Role r = this.service.getRoleOne(obj.getRoleCode());
            if (null == r) {
                this.service.saveRole(obj, condition);
                //���÷��ز���
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�����ɹ���", obj);
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.ROLE_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                        "������ɫ:" + obj.getRoleName(), userIp, (int) userId);
            } else {
                ret.setMessage("��ɫ����ظ�!");
            }
        } catch (Exception e) {
            log.error("������ɫ:" + obj.getRoleName() + "ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**************************** ��ӷ��� ****************************************/
    // select�����б�

    /**
     * ���·���
     */
    @RequestMapping(value = "/updateRole.json")
    @ResponseBody
    public HttpResponse<Role> updateRole(@ModelAttribute("r") Role obj, RoleCondition condition, HttpServletRequest request) {
        HttpResponse<Role> ret = new HttpResponse(HttpResponse.Status.FAILURE, "�޸�ʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        BaseLocal local = RoleDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
        RoleDaoGuard.setThreadLocal(local);

        try {
            int i = this.service.updateRole(obj, condition);
            //���÷��ز���
            if (1 == i) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�޸ĳɹ���", obj);
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.ROLE_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.EDIT.getCode(), "�޸Ľ�ɫ:" + obj.getRoleName(), userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("�޸�ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �鿴ҳ��
     */
    @RequestMapping(value = "/showRole.htm")
    public String showRole(@ModelAttribute("r") Role obj, Model model) {
        Role role = this.service.getRoleOne(obj.getRoleCode());
        model.addAttribute("role", role);
        return "system/showRole";
    }

    /**
     * ����ɾ������
     */
    @RequestMapping(value = "/delRoles.json")
    @ResponseBody
    public HttpResponse<RoleCondition> delRoles(RoleCondition condition, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ��ʧ�ܣ�", condition);
        log.debug("ɾ���ύ" + condition.getRoleCodes().toString());
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        try {
            int i = this.service.delRoles(condition);
            //���÷��ز���
            if (i > 0) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ���ɹ���", condition);
                logService.addSystemLog(0, SystemLogEnums.LogObject.ROLE_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.DELETE.getCode(), "ɾ����ɫ:roleCode=" + condition.getRoleCodes().toString(),
                        userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("ɾ��ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}

