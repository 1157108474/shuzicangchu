package com.zthc.ewms.system.menu.controller.guard;

import com.hckj.base.mvc.BaseLocal;

import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.menu.dao.guard.ButtonDaoGuard;
import com.zthc.ewms.system.menu.entity.guard.Button;
import com.zthc.ewms.system.menu.entity.guard.ButtonCondition;
import com.zthc.ewms.system.menu.entity.guard.MenuButtonEnums;
import com.zthc.ewms.system.menu.service.ButtonService;
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
import java.util.List;
import java.util.Map;


public class ButtonControllerGuard {
    @Resource(name = "buttonService")
    public ButtonService service;
    @Resource(name = "logService")
    public LogService logService;
    private final static Log log = Log.getLog("com.zght.crms.system.menu.controller.guard.ButtonControllerGuard");

    /**
     * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
     */
    @InitBinder("bu")
    public void initButtonBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("bu.");
    }

    /**
     * ����ҳ��
     */
    @RequestMapping(value = "/manageButton.htm")
    public String manageButton(@ModelAttribute("bu") Button obj, ButtonCondition condition, HttpServletRequest request,
                               HttpServletResponse response, Model model) {
        model.addAttribute("bu", obj);
        return "system/menu/manageButton";
    }

    /**
     * ����ҳ��
     */
    @RequestMapping(value = "/addButton.htm")
    public String addButton(@ModelAttribute("bu") Button obj,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
        return editButton(obj, request, response, model);
    }

    /**
     * �༭ҳ��
     */
    @RequestMapping(value = "/editButton.htm")
    public String editButton(@ModelAttribute("bu") Button obj, HttpServletRequest request,
                             HttpServletResponse response, Model model) {
        Button bu = this.service.getButtonOne(obj.getCode());
        if (null == obj.getCode()) {
            bu.setMenuCode(obj.getMenuCode());
        }
        //ƴװ����״̬ö����
        Map<Object, String> statusMaps = new HashMap<>();
        for (MenuButtonEnums.StatusEnum e : MenuButtonEnums.StatusEnum.values()) {
            statusMaps.put(e.getStatus(), e.getMeaning());
        }
        model.addAttribute("statusMaps", statusMaps);
        model.addAttribute("bu", bu);
        return "system/menu/editButton";
    }

    /**
     * �����¼
     */
    @RequestMapping(value = "/saveButton.json")
    @ResponseBody
    public HttpResponse<Button> saveRole(@ModelAttribute("bu") Button obj, ButtonCondition condition,
                                         HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse<Button> ret = new HttpResponse(HttpResponse.Status.FAILURE, "����ʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        BaseLocal local = ButtonDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
        ButtonDaoGuard.setThreadLocal(local);
        try {
            List<Button> buttons = this.service.listButtonCode(obj);
            if (buttons.size() == 0) {
                this.service.saveButton(obj);
                //���÷��ز���
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�����ɹ���", obj);
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.MENU_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.ADD.getCode(), "������ť:" + obj.getName(), userIp, (int) userId);
            } else {
                ret.setMessage("��ť����ظ�!");
            }
            //��־
        } catch (Exception e) {
            log.error("������ť:" + obj.getName() + "ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**************************** ��ӷ��� ****************************************/
    // select�����б�

    /**
     * ���·���
     */
    @RequestMapping(value = "/updateButton.json")
    @ResponseBody
    public HttpResponse<Button> updateButton(@ModelAttribute("bu") Button obj, ButtonCondition condition,
                                             HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse<Button> ret = new HttpResponse(HttpResponse.Status.FAILURE, "�޸�ʧ�ܣ�", obj);

        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        BaseLocal local = ButtonDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
        ButtonDaoGuard.setThreadLocal(local);

        try {
            int i = this.service.updateButton(obj, condition);
            //���÷��ز���
            if (1 == i) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�޸ĳɹ���", obj);
                //��־
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.MENU_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.EDIT.getCode(), "�޸İ�ť:" + obj.getName(), userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("�޸İ�ť:" + obj.getName() + "ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �鿴ҳ��
     */
    @RequestMapping(value = "/showButton.htm")
    public String showButton(@ModelAttribute("bu") Button obj, HttpServletRequest request,
                             HttpServletResponse response, Model model) {
        Button bu = this.service.getButtonOne(obj.getCode());

        model.addAttribute("bu", bu);
        return "system/showButton";
    }

    /**
     * ����ɾ������
     */
    @RequestMapping(value = "/delButton.json")
    @ResponseBody
    public HttpResponse<ButtonCondition> delButton(ButtonCondition condition, HttpServletRequest request,
                                                   HttpServletResponse response, Model model) {
        HttpResponse<ButtonCondition> ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ��ʧ�ܣ�", condition);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        try {
            int i = this.service.delButton(condition);
            //���÷��ز���
            if (i > 0) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ���ɹ���", condition);
            }
        } catch (Exception e) {
            log.error("ɾ��ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}

