package com.zthc.ewms.system.menu.controller;


import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.menu.controller.guard.MenuControllerGuard;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.entity.guard.MenuCondition;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/system/menu")
public class MenuController extends MenuControllerGuard {
    private final static Log log = Log.getLog("com.system.menu.controller.MenuController");

    /**
     * ��ȡ�б�����
     */
    @RequestMapping("/listMenu.json")
    @ResponseBody
    public HttpResponse<List<Menu>> listMenu(Menu obj, HttpServletResponse response) throws IOException {
        List<Menu> rets = new ArrayList<>();
        HttpResponse<List<Menu>> ret = new HttpResponse<List<Menu>>(HttpResponse.Status.FAILURE, "��ȡ�˵�����ʧ�ܣ�", rets);
        try {
            rets = this.service.listMenus();
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ȡ�˵����ݳɹ���", rets);

        } catch (Exception e) {
            log.error("��ȡ�˵��б����ݳ���������" + obj.toString());
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ�б�����
     */
    @RequestMapping("/listMenuStatus.json")
    @ResponseBody
    public HttpResponse<List<Menu>> listMenuStatus(Menu obj, HttpServletResponse response) throws IOException {
        List<Menu> rets = new ArrayList<>();
        HttpResponse<List<Menu>> ret = new HttpResponse<List<Menu>>(HttpResponse.Status.FAILURE, "��ȡ�˵�����ʧ�ܣ�", rets);
        try {
            rets = this.service.listMenuStatus();
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ȡ�˵����ݳɹ���", rets);

        } catch (Exception e) {
            log.error("��ȡ�˵��б����ݳ���������" + obj.toString());
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    /**
     * ���ܰ�ť����ҳ��
     */
    @RequestMapping(value = "/manageButton.htm")
    public String manageButton(@ModelAttribute("m") Menu obj, HttpServletRequest request,
                               HttpServletResponse response, Model model) {
        model.addAttribute("m", obj);
        return "system/menu/manageButton";
    }
}
