package com.zthc.ewms.system.role.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.service.MenuService;
import com.zthc.ewms.system.role.controller.guard.RoleControllerGuard;
import com.zthc.ewms.system.role.dao.guard.RoleDaoGuard;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.role.entity.guard.RoleMenuButton;
import com.zthc.ewms.system.role.entity.guard.RoleMenuButtonCondition;
import com.zthc.ewms.system.role.service.RoleMenuButtonService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserCondition;
import com.zthc.ewms.system.user.service.UserRoleService;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/role")
public class RoleController extends RoleControllerGuard {
    @Resource(name = "userRoleService")
    public UserRoleService userRoleService;
    @Resource(name = "menuService")
    public MenuService menuService;
    @Resource(name = "roleMenuButtonService")
    public RoleMenuButtonService roleMenuButtonService;
    private final static Log log = Log.getLog("com.system.role.controller.roleControllerGuard");

    /**
     * ��ȡ��ɫ�б�����
     */
    @RequestMapping("/listRole.json")
    @ResponseBody
    public LayuiPage<Role> listRole(Role obj, RoleCondition condition) throws IOException {
        LayuiPage<Role> ret = null;
        try {
            ret = service.listRole(obj, condition);
        } catch (Exception e) {
            log.error("��ȡ��ɫ�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �������Ȩ��ҳ��
     */
    @RequestMapping(value = "/allotMenuButton.htm")
    public String allotMenuButton(Role obj, Model model) {
        model.addAttribute("role", obj);

        return "system/role/allotMenuButton";
    }

    /**
     * ��ȡ�˵���ť
     */
    @RequestMapping("/listMenuButton.json")
    @ResponseBody
    public String listMenuButton(Role obj) {
        //HttpResponse<String> ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ȡ�˵���ť����ʧ�ܣ�", obj.toString());
        String ret = "";
        try {
            List<Map<String, Object>> menus = this.service.listMenuButton(obj);
            ObjectMapper objectMapper = new ObjectMapper();
            //ת����json��
            ret = objectMapper.writeValueAsString(menus);
        } catch (Exception e) {
            log.error("��ȡ�˵���ť�����б����������" + obj.toString());
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �����ɫ�˵���ť��¼
     */
    @RequestMapping(value = "/saveRoleMenuButton.json")
    @ResponseBody
    public HttpResponse<Role> saveRoleMenuButton(@ModelAttribute("r") Role obj, RoleMenuButtonCondition condition, HttpServletRequest request) {
        HttpResponse<Role> ret = new HttpResponse(HttpResponse.Status.FAILURE, "�����ɫ�˵���ťʧ�ܣ�", obj);
        try {
            this.roleMenuButtonService.saveRoleMenuButton(obj, condition);
            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�����ɫ�˵���ť�ɹ���", obj);
        } catch (Exception e) {
            log.error("�����ɫ�˵���ťʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * ���ݽ�ɫ��ȡ�û��б�����,��ҳ
     */
    @RequestMapping("/listRoleUser.json")
    @ResponseBody
    public LayuiPage<User> listRoleUser(Role obj, UserCondition condition, HttpServletResponse response) {
        LayuiPage<User> ret = null;
        try {
            ret = this.userRoleService.listRoleUser(obj, condition);
        } catch (Exception e) {
            log.error("��ȡ�û����ݳ���");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ���벿���û�ҳ��
     */
    @RequestMapping(value = "/departUser.htm")
    public String departUser(Role obj, Model model) {
        model.addAttribute("role", obj);
        return "system/user/departUser";
    }

    /**
     * �����ɫ�û���¼
     */
    @RequestMapping(value = "/saveRoleUsers.json")
    @ResponseBody
    public HttpResponse<Role> saveRoleUsers(@ModelAttribute("r") Role obj, UserCondition condition, HttpServletRequest request) {
        HttpResponse<Role> ret = new HttpResponse(HttpResponse.Status.FAILURE, "����û�ʧ�ܣ�", obj);
        try {
            this.userRoleService.saveRoleUsers(obj, condition);
            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "����û��ɹ���", obj);
        } catch (Exception e) {
            log.error("����û�ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �Ƴ���ɫ�û���¼
     */
    @RequestMapping(value = "/delRoleUsers.json")
    @ResponseBody
    public HttpResponse<Role> delRoleUsers(@ModelAttribute("r") Role obj, UserCondition condition, HttpServletRequest request) {
        HttpResponse<Role> ret = new HttpResponse(HttpResponse.Status.FAILURE, "�Ƴ��û�ʧ�ܣ�", obj);
        try {
            this.userRoleService.delRoleUsers(obj, condition);
            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�Ƴ��û��ɹ���", obj);
        } catch (Exception e) {
            log.error("�Ƴ��û�ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}
