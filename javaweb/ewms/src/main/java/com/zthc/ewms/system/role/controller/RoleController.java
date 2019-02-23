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
     * 获取角色列表数据
     */
    @RequestMapping("/listRole.json")
    @ResponseBody
    public LayuiPage<Role> listRole(Role obj, RoleCondition condition) throws IOException {
        LayuiPage<Role> ret = null;
        try {
            ret = service.listRole(obj, condition);
        } catch (Exception e) {
            log.error("获取角色数据列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 进入分配权限页面
     */
    @RequestMapping(value = "/allotMenuButton.htm")
    public String allotMenuButton(Role obj, Model model) {
        model.addAttribute("role", obj);

        return "system/role/allotMenuButton";
    }

    /**
     * 获取菜单按钮
     */
    @RequestMapping("/listMenuButton.json")
    @ResponseBody
    public String listMenuButton(Role obj) {
        //HttpResponse<String> ret = new HttpResponse(HttpResponse.Status.FAILURE, "获取菜单按钮数据失败！", obj.toString());
        String ret = "";
        try {
            List<Map<String, Object>> menus = this.service.listMenuButton(obj);
            ObjectMapper objectMapper = new ObjectMapper();
            //转换成json串
            ret = objectMapper.writeValueAsString(menus);
        } catch (Exception e) {
            log.error("获取菜单按钮数据列表出错！参数：" + obj.toString());
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 保存角色菜单按钮记录
     */
    @RequestMapping(value = "/saveRoleMenuButton.json")
    @ResponseBody
    public HttpResponse<Role> saveRoleMenuButton(@ModelAttribute("r") Role obj, RoleMenuButtonCondition condition, HttpServletRequest request) {
        HttpResponse<Role> ret = new HttpResponse(HttpResponse.Status.FAILURE, "保存角色菜单按钮失败！", obj);
        try {
            this.roleMenuButtonService.saveRoleMenuButton(obj, condition);
            //设置返回参数
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "保存角色菜单按钮成功！", obj);
        } catch (Exception e) {
            log.error("保存角色菜单按钮失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * 根据角色获取用户列表数据,分页
     */
    @RequestMapping("/listRoleUser.json")
    @ResponseBody
    public LayuiPage<User> listRoleUser(Role obj, UserCondition condition, HttpServletResponse response) {
        LayuiPage<User> ret = null;
        try {
            ret = this.userRoleService.listRoleUser(obj, condition);
        } catch (Exception e) {
            log.error("获取用户数据出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 进入部门用户页面
     */
    @RequestMapping(value = "/departUser.htm")
    public String departUser(Role obj, Model model) {
        model.addAttribute("role", obj);
        return "system/user/departUser";
    }

    /**
     * 保存角色用户记录
     */
    @RequestMapping(value = "/saveRoleUsers.json")
    @ResponseBody
    public HttpResponse<Role> saveRoleUsers(@ModelAttribute("r") Role obj, UserCondition condition, HttpServletRequest request) {
        HttpResponse<Role> ret = new HttpResponse(HttpResponse.Status.FAILURE, "添加用户失败！", obj);
        try {
            this.userRoleService.saveRoleUsers(obj, condition);
            //设置返回参数
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "添加用户成功！", obj);
        } catch (Exception e) {
            log.error("添加用户失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 移除角色用户记录
     */
    @RequestMapping(value = "/delRoleUsers.json")
    @ResponseBody
    public HttpResponse<Role> delRoleUsers(@ModelAttribute("r") Role obj, UserCondition condition, HttpServletRequest request) {
        HttpResponse<Role> ret = new HttpResponse(HttpResponse.Status.FAILURE, "移除用户失败！", obj);
        try {
            this.userRoleService.delRoleUsers(obj, condition);
            //设置返回参数
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "移除用户成功！", obj);
        } catch (Exception e) {
            log.error("移除用户失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}
