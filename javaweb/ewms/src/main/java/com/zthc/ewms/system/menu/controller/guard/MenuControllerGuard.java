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
     * 表单提交时，字段所用前缀，只提交一个对象时，可以不写此前缀
     */
    @InitBinder("m")
    public void initMenuBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("m.");
    }

    /**
     * 管理页面
     */
    @RequestMapping(value = "/manageMenu.htm")
    public String manageMenu(@ModelAttribute("m") Menu obj, HttpServletRequest request, HttpServletResponse response,
                             Model model) {
        return "system/menu/manageMenu";
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/addMenu.htm")
    public String addMenu(@ModelAttribute("m") Menu obj, HttpServletRequest request, HttpServletResponse response,
                          Model model) {
        return editMenu(obj, request, response, model);
    }

    /**
     * 编辑页面
     */
    @RequestMapping(value = "/editMenu.htm")
    public String editMenu(@ModelAttribute("m") Menu obj, HttpServletRequest request,
                           HttpServletResponse response, Model model) {
        Menu menu = this.service.getMenuOne(obj.getCode());
        //拼装状态枚举类
        Map<Object, String> statusMaps = new HashMap<>();
        for (MenuButtonEnums.StatusEnum e : MenuButtonEnums.StatusEnum.values()) {
            statusMaps.put(e.getStatus(), e.getMeaning());
        }
        //拼装类型枚举类
        Map<Object, String> typeMaps = new HashMap<>();
        for (MenuButtonEnums.TypeEnum e : MenuButtonEnums.TypeEnum.values()) {
            typeMaps.put(e.getType(), e.getMeaning());
        }
        //拼装类型枚举类
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
     * 保存记录
     */
    @RequestMapping(value = "/saveMenu.json")
    @ResponseBody
    public HttpResponse<Menu> saveMenu(@ModelAttribute("m") Menu obj, MenuCondition condition, HttpServletRequest request,
                                       HttpServletResponse response, Model model) {

        HttpResponse<Menu> ret = new HttpResponse(HttpResponse.Status.FAILURE, "新增失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        BaseLocal local = MenuDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        MenuDaoGuard.setThreadLocal(local);

        //类型为目录。链接地址为空
//        if (obj.getType() == MenuButtonEnums.TypeEnum.CATALOG.getType()) {
//            obj.setAuthIdentity("#");
//            obj.setUrl("#");
//        }

        try {
            List<Menu> menus = this.service.listMenuCode(obj);
            if (menus.size() == 0) {
                this.service.saveMenu(obj, condition);
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "新增成功！", obj);
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.MENU_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                        "新增菜单:" + obj.getName(), userIp, (int) userId);
            } else {
                ret.setMessage("菜单编号重复!");
            }
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
    @RequestMapping(value = "/updateMenu.json")
    @ResponseBody
    public HttpResponse<Menu> updateMenu(@ModelAttribute("m") Menu obj, MenuCondition condition,
                                         HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse<Menu> ret = new HttpResponse(HttpResponse.Status.FAILURE, "修改失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        BaseLocal local = MenuDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        MenuDaoGuard.setThreadLocal(local);
        //类型为目录。链接地址为空
//        if (obj.getType() == MenuButtonEnums.TypeEnum.CATALOG.getType()) {
//            obj.setAuthIdentity("#");
//            obj.setUrl("#");
//        }
        obj.setMenu(new Menu(obj.getMenuCode()));
        try {
            int i = this.service.updateMenu(obj, condition);
            //设置返回参数
            if (1 == i) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "修改成功！", obj);
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.MENU_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.EDIT.getCode(), "修改菜单:" + obj.getName(), userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("修改失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 批量删除方法
     */
    @RequestMapping(value = "/delMenus.json")
    @ResponseBody
    public HttpResponse<MenuCondition> delMenus(MenuCondition condition, HttpServletRequest request, HttpServletResponse response) {
        HttpResponse<MenuCondition> ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除失败！", condition);
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        try {
            int i = this.service.delMenus(condition);
            //设置返回参数
            if (i > 0) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "删除成功！", condition);
                logService.addSystemLog(0, SystemLogEnums.LogObject.MENU_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.DELETE.getCode(), "删除菜单:codes=" + condition.getCodes().toString(),
                        userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("删除失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}

