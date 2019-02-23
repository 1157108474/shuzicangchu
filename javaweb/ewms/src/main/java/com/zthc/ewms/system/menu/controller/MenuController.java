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
     * 获取列表数据
     */
    @RequestMapping("/listMenu.json")
    @ResponseBody
    public HttpResponse<List<Menu>> listMenu(Menu obj, HttpServletResponse response) throws IOException {
        List<Menu> rets = new ArrayList<>();
        HttpResponse<List<Menu>> ret = new HttpResponse<List<Menu>>(HttpResponse.Status.FAILURE, "获取菜单数据失败！", rets);
        try {
            rets = this.service.listMenus();
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "获取菜单数据成功！", rets);

        } catch (Exception e) {
            log.error("获取菜单列表数据出错！参数：" + obj.toString());
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取列表数据
     */
    @RequestMapping("/listMenuStatus.json")
    @ResponseBody
    public HttpResponse<List<Menu>> listMenuStatus(Menu obj, HttpServletResponse response) throws IOException {
        List<Menu> rets = new ArrayList<>();
        HttpResponse<List<Menu>> ret = new HttpResponse<List<Menu>>(HttpResponse.Status.FAILURE, "获取菜单数据失败！", rets);
        try {
            rets = this.service.listMenuStatus();
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "获取菜单数据成功！", rets);

        } catch (Exception e) {
            log.error("获取菜单列表数据出错！参数：" + obj.toString());
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    /**
     * 功能按钮管理页面
     */
    @RequestMapping(value = "/manageButton.htm")
    public String manageButton(@ModelAttribute("m") Menu obj, HttpServletRequest request,
                               HttpServletResponse response, Model model) {
        model.addAttribute("m", obj);
        return "system/menu/manageButton";
    }
}
