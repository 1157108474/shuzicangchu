package com.zthc.ewms.system.menu.controller.guard;

import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.menu.dao.guard.MenuDaoGuard;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.entity.guard.MenuCondition;
import com.zthc.ewms.system.menu.entity.guard.MenuButtonEnums;
import com.zthc.ewms.system.menu.service.MenuService;
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


public class MenuControllerGuard {
    @Resource(name = "menuService")
    public MenuService service;

    @Resource(name = "logService")
    public LogService logService;


    private final static Log log = Log.getLog("com.zthc.ewms.system.menu.controller.guard.MenuControllerGuard");

    /**
     * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
     */
    @InitBinder("m")
    public void initMenuBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("m.");
    }

    /**
     * ����ҳ��
     */
    @RequestMapping(value = "/manageMenu.htm")
    public String manageMenu(@ModelAttribute("m") Menu obj, HttpServletRequest request, HttpServletResponse response,
                             Model model) {
        return "system/menu/manageMenu";
    }

    /**
     * ����
     */
    @RequestMapping(value = "/addMenu.htm")
    public String addMenu(@ModelAttribute("m") Menu obj, HttpServletRequest request, HttpServletResponse response,
                          Model model) {
        return editMenu(obj, request, response, model);
    }

    /**
     * �༭ҳ��
     */
    @RequestMapping(value = "/editMenu.htm")
    public String editMenu(@ModelAttribute("m") Menu obj, HttpServletRequest request,
                           HttpServletResponse response, Model model) {
        Menu menu = this.service.getMenuOne(obj.getCode());
        //ƴװ״̬ö����
        Map<Object, String> statusMaps = new HashMap<>();
        for (MenuButtonEnums.StatusEnum e : MenuButtonEnums.StatusEnum.values()) {
            statusMaps.put(e.getStatus(), e.getMeaning());
        }
        //ƴװ����ö����
        Map<Object, String> typeMaps = new HashMap<>();
        for (MenuButtonEnums.TypeEnum e : MenuButtonEnums.TypeEnum.values()) {
            typeMaps.put(e.getType(), e.getMeaning());
        }
        //ƴװ����ö����
        Map<Object, String> buttonModeEnumMaps = new HashMap<>();
        for (MenuButtonEnums.ButtonModeEnum e : MenuButtonEnums.ButtonModeEnum.values()) {
            buttonModeEnumMaps.put(e.getButtonMode(), e.getMeaning());
        }

        String menuName = null;
        String menuCode = null;
        if (null != menu.getMenu()) {
            menuName = menu.getMenu().getName();
            menuCode = menu.getMenu().getCode();
        }
        model.addAttribute("menuName", menuName);
        model.addAttribute("menuCode", menuCode);
        model.addAttribute("statusMaps", statusMaps);
        model.addAttribute("typeMaps", typeMaps);
        model.addAttribute("buttonModeEnumMaps", buttonModeEnumMaps);
        model.addAttribute("menu", menu);
        return "system/menu/editMenu";
    }

    /**
     * �����¼
     */
    @RequestMapping(value = "/saveMenu.json")
    @ResponseBody
    public HttpResponse<Menu> saveMenu(@ModelAttribute("m") Menu obj, MenuCondition condition, HttpServletRequest request,
                                       HttpServletResponse response, Model model) {

        HttpResponse<Menu> ret = new HttpResponse(HttpResponse.Status.FAILURE, "����ʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        BaseLocal local = MenuDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
        MenuDaoGuard.setThreadLocal(local);

        //����ΪĿ¼�����ӵ�ַΪ��
//        if (obj.getType() == MenuButtonEnums.TypeEnum.CATALOG.getType()) {
//            obj.setAuthIdentity("#");
//            obj.setUrl("#");
//        }

        try {
            List<Menu> menus = this.service.listMenuCode(obj);
            if (menus.size() == 0) {
                this.service.saveMenu(obj, condition);
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�����ɹ���", obj);
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.MENU_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                        "�����˵�:" + obj.getName(), userIp, (int) userId);
            } else {
                ret.setMessage("�˵�����ظ�!");
            }
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
    @RequestMapping(value = "/updateMenu.json")
    @ResponseBody
    public HttpResponse<Menu> updateMenu(@ModelAttribute("m") Menu obj, MenuCondition condition,
                                         HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse<Menu> ret = new HttpResponse(HttpResponse.Status.FAILURE, "�޸�ʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        BaseLocal local = MenuDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
        MenuDaoGuard.setThreadLocal(local);
        //����ΪĿ¼�����ӵ�ַΪ��
//        if (obj.getType() == MenuButtonEnums.TypeEnum.CATALOG.getType()) {
//            obj.setAuthIdentity("#");
//            obj.setUrl("#");
//        }
        obj.setMenu(new Menu(obj.getMenuCode()));
        try {
            int i = this.service.updateMenu(obj, condition);
            //���÷��ز���
            if (1 == i) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�޸ĳɹ���", obj);
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.MENU_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.EDIT.getCode(), "�޸Ĳ˵�:" + obj.getName(), userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("�޸�ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ����ɾ������
     */
    @RequestMapping(value = "/delMenus.json")
    @ResponseBody
    public HttpResponse<MenuCondition> delMenus(MenuCondition condition, HttpServletRequest request, HttpServletResponse response) {
        HttpResponse<MenuCondition> ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ��ʧ�ܣ�", condition);
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        try {
            int i = this.service.delMenus(condition);
            //���÷��ز���
            if (i > 0) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ���ɹ���", condition);
                logService.addSystemLog(0, SystemLogEnums.LogObject.MENU_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.DELETE.getCode(), "ɾ���˵�:codes=" + condition.getCodes().toString(),
                        userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("ɾ��ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}

