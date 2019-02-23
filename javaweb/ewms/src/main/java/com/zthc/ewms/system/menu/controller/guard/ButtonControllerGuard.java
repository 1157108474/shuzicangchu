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
     * 表单提交时，字段所用前缀，只提交一个对象时，可以不写此前缀
     */
    @InitBinder("bu")
    public void initButtonBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("bu.");
    }

    /**
     * 管理页面
     */
    @RequestMapping(value = "/manageButton.htm")
    public String manageButton(@ModelAttribute("bu") Button obj, ButtonCondition condition, HttpServletRequest request,
                               HttpServletResponse response, Model model) {
        model.addAttribute("bu", obj);
        return "system/menu/manageButton";
    }

    /**
     * 新增页面
     */
    @RequestMapping(value = "/addButton.htm")
    public String addButton(@ModelAttribute("bu") Button obj,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
        return editButton(obj, request, response, model);
    }

    /**
     * 编辑页面
     */
    @RequestMapping(value = "/editButton.htm")
    public String editButton(@ModelAttribute("bu") Button obj, HttpServletRequest request,
                             HttpServletResponse response, Model model) {
        Button bu = this.service.getButtonOne(obj.getCode());
        if (null == obj.getCode()) {
            bu.setMenuCode(obj.getMenuCode());
        }
        //拼装启动状态枚举类
        Map<Object, String> statusMaps = new HashMap<>();
        for (MenuButtonEnums.StatusEnum e : MenuButtonEnums.StatusEnum.values()) {
            statusMaps.put(e.getStatus(), e.getMeaning());
        }
        model.addAttribute("statusMaps", statusMaps);
        model.addAttribute("bu", bu);
        return "system/menu/editButton";
    }

    /**
     * 保存记录
     */
    @RequestMapping(value = "/saveButton.json")
    @ResponseBody
    public HttpResponse<Button> saveRole(@ModelAttribute("bu") Button obj, ButtonCondition condition,
                                         HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse<Button> ret = new HttpResponse(HttpResponse.Status.FAILURE, "新增失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        BaseLocal local = ButtonDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        ButtonDaoGuard.setThreadLocal(local);
        try {
            List<Button> buttons = this.service.listButtonCode(obj);
            if (buttons.size() == 0) {
                this.service.saveButton(obj);
                //设置返回参数
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "新增成功！", obj);
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.MENU_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.ADD.getCode(), "新增按钮:" + obj.getName(), userIp, (int) userId);
            } else {
                ret.setMessage("按钮编号重复!");
            }
            //日志
        } catch (Exception e) {
            log.error("新增按钮:" + obj.getName() + "失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**************************** 添加方法 ****************************************/
    // select条件列表

    /**
     * 更新方法
     */
    @RequestMapping(value = "/updateButton.json")
    @ResponseBody
    public HttpResponse<Button> updateButton(@ModelAttribute("bu") Button obj, ButtonCondition condition,
                                             HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse<Button> ret = new HttpResponse(HttpResponse.Status.FAILURE, "修改失败！", obj);

        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        BaseLocal local = ButtonDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        ButtonDaoGuard.setThreadLocal(local);

        try {
            int i = this.service.updateButton(obj, condition);
            //设置返回参数
            if (1 == i) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "修改成功！", obj);
                //日志
                this.logService.addSystemLog(0, SystemLogEnums.LogObject.MENU_MANAGEMENT.getCode(),
                        SystemLogEnums.LogAction.EDIT.getCode(), "修改按钮:" + obj.getName(), userIp, (int) userId);
            }
        } catch (Exception e) {
            log.error("修改按钮:" + obj.getName() + "失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 查看页面
     */
    @RequestMapping(value = "/showButton.htm")
    public String showButton(@ModelAttribute("bu") Button obj, HttpServletRequest request,
                             HttpServletResponse response, Model model) {
        Button bu = this.service.getButtonOne(obj.getCode());

        model.addAttribute("bu", bu);
        return "system/showButton";
    }

    /**
     * 批量删除方法
     */
    @RequestMapping(value = "/delButton.json")
    @ResponseBody
    public HttpResponse<ButtonCondition> delButton(ButtonCondition condition, HttpServletRequest request,
                                                   HttpServletResponse response, Model model) {
        HttpResponse<ButtonCondition> ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除失败！", condition);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        try {
            int i = this.service.delButton(condition);
            //设置返回参数
            if (i > 0) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "删除成功！", condition);
            }
        } catch (Exception e) {
            log.error("删除失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}

