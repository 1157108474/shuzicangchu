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
     * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
     */
    @InitBinder("us")
    public void initUserBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("us.");
    }

    /**
     * ����ҳ��
     */
    @RequestMapping(value = "/manageUser.htm")
    public String manageUser(@ModelAttribute("us") User obj, HttpServletRequest request, HttpServletResponse response,
                             Model model) {
        return "system/user/manageUser";
    }


    /**
     * �����¼
     */
    @RequestMapping(value = "/saveUser.json")
    @ResponseBody
    public HttpResponse saveUser(@ModelAttribute("us") User obj, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "����ʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        BaseLocal local = UserDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
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
        //���Ÿ�ֵ
        Depart depart = new Depart(obj.getParentId());
        obj.setParent(depart);
        //�������Ӹ�ֵ
        UseDep useDep = new UseDep(obj.getOfficesId());
        obj.setOffices(useDep);
        obj.setUserType(UserEnums.TypeEnum.EWMS.getType());
        obj.setPassWord(MD5Util.MD5("000000"));

        User user = this.service.getUserByCode(obj.getCode());
        if (user != null) {
            return new HttpResponse(HttpResponse.Status.FAILURE, "�˱����Ѵ��ڣ�", null);
        }
        try {
            this.service.saveUser(obj);
            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�����ɹ���", obj);
            //��־
            this.logService.addSystemLog(0, SystemLogEnums.LogObject.PERSON_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                    "������Ա:" + obj.getName(), userIp, (int) userId);
        } catch (Exception e) {
            log.error("����ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    /**************************** ��ӷ��� ****************************************/
    // select�����б�

    /**
     * ���·���
     */
    @RequestMapping(value = "/updateUser.json")
    @ResponseBody
    public HttpResponse updateUser(@ModelAttribute("us") User obj, UserCondition condition, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "�޸�ʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        BaseLocal local = UserDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
        UserDaoGuard.setThreadLocal(local);
        //���Ÿ�ֵ
        Depart depart = new Depart(obj.getParentId());
        obj.setParent(depart);
        //�������Ӹ�ֵ
        UseDep useDep = new UseDep(obj.getOfficesId());
        obj.setOffices(useDep);
        try {
            List<User> users = this.service.listUserCode(obj);
            if (users.size() == 0) {
                int i = this.service.updateUser(obj);
                //���÷��ز���
                if (1 == i) {
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�޸ĳɹ���", obj);
                    //��־
                    this.logService.addSystemLog(0, SystemLogEnums.LogObject.PERSON_MANAGEMENT.getCode(),
                            SystemLogEnums.LogAction.EDIT.getCode(), "�޸���Ա:" + obj.getName(), userIp, (int) userId);
                }
            } else {
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "�˱����Ѵ��ڣ�", null);
            }
        } catch (Exception e) {
            log.error("�޸�ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    //select�����б�
    @RequestMapping(value = "/showUsers.json")
    @ResponseBody
    public HttpResponse showUsers(@ModelAttribute("us") User obj, UserCondition condition,
                                  HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ѯʧ�ܣ�", obj);
        try {
            User us = this.service.getUserOne(obj.getId());
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "��ѯ�ɹ���", us);
        } catch (Exception e) {
            log.error("��ѯʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    //delete

    /**
     * ����ɾ������
     */
    @RequestMapping(value = "/delUsers.json")
    @ResponseBody
    public HttpResponse delUsers(UserCondition condition, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ��ʧ�ܣ�", condition);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        try {
            int i = this.service.delUsers(condition);
            //���÷��ز���
            if (i > 0) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ���ɹ���", condition);
                //��־
                logService.addSystemLog(0, SystemLogEnums.LogObject.PERSON_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.DELETE.getCode(), "ɾ����Ա:roleCode=" + condition.getIds().toString(),
                        userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("ɾ��ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}

