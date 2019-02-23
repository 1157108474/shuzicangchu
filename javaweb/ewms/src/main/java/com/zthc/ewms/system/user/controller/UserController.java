package com.zthc.ewms.system.user.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hckj.base.mvc.BaseLocal;
import com.hckj.base.util.Tool;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.entity.guard.DepartCondition;
import com.zthc.ewms.system.dept.service.DepartService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.role.service.RoleService;
import com.zthc.ewms.system.spare.entity.guard.Sparepartscate;
import com.zthc.ewms.system.spare.service.SparepartscateService;
import com.zthc.ewms.system.useDep.entity.UseDep;
import com.zthc.ewms.system.useDep.service.UseDepService;
import com.zthc.ewms.system.user.controller.guard.UserControllerGuard;
import com.zthc.ewms.system.user.dao.guard.UserDaoGuard;
import com.zthc.ewms.system.user.dao.guard.UserScopeDaoGuard;
import com.zthc.ewms.system.user.entity.guard.*;
import com.zthc.ewms.system.user.service.UserRoleService;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/user")
public class UserController extends UserControllerGuard {
    @Resource(name = "roleService")
    public RoleService roleservice;
    @Resource(name = "userRoleService")
    public UserRoleService userRoleService;
    @Resource(name = "userScopeService")
    public UserScopeService userScopeService;
    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;
    @Resource(name = "sparepartscateService")
    public SparepartscateService sparepartscateService;
    @Resource(name = "useDepService")
    public UseDepService useDepService;
    @Resource(name = "departService")
    public DepartService departService;

    private final static Log log = Log.getLog("com.zght.crms.system.user.controller.UserController");

    /**
     * 获取列表数据
     */
    @RequestMapping("/listUser.json")
    @ResponseBody
    public LayuiPage<User> listUser(User obj, UserCondition condition, String startTime, String endTime,
                                    HttpServletResponse response) {
        LayuiPage<User> ret = null;
        try {
            ret = this.service.listUser(obj, condition, startTime, endTime);
        } catch (Exception e) {
            log.error("获取用户数据出错！");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * 获取列表数据
     */
    @RequestMapping("/listNoRoleUser.json")
    @ResponseBody
    public LayuiPage<User> listNoRoleUser(User obj, String roleCode, UserCondition condition, HttpServletResponse
            response) {
        LayuiPage<User> ret = null;
        try {
            ret = this.userRoleService.listNoRoleUser(obj, roleCode, condition);
        } catch (Exception e) {
            log.error("获取用户数据出错！");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }
    /**
     * 打开重置密码页面
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/openResetPWD.htm")
    public String openResetPWD(@ModelAttribute("us") User obj, UserCondition condition,
                               HttpServletRequest request, HttpServletResponse response, Model model) {

        User us = this.service.getUserOne(obj.getId());

        model.addAttribute("us", us);
        model.addAttribute("source", condition.getSource());

        return "system/user/resetPWD";
    }

    /**
     * 重置密码
     *
     * @param obj
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateResetPWD.json")
    @ResponseBody
    public HttpResponse<User> updateResetPWD(@ModelAttribute("us") User obj, HttpServletRequest request,
                                             HttpServletResponse response) {
        HttpResponse<User> ret = new HttpResponse(HttpResponse.Status.FAILURE, "重置密码失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        BaseLocal local = UserDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        UserDaoGuard.setThreadLocal(local);
        try {
            int i = this.service.updateResetPWD(obj);
            //设置返回参数
            if (1 == i) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "重置密码成功！", obj);
            }
        } catch (Exception e) {
            log.error("重置密码失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 根据用户获取角色列表数据,分页
     */
    @RequestMapping("/listUserRole.json")
    @ResponseBody
    public LayuiPage<Role> listUserRole(User obj, RoleCondition condition, HttpServletResponse response) {
        LayuiPage<Role> ret = null;
        try {
            ret = this.userRoleService.listUserRole(obj, condition);
        } catch (Exception e) {
            log.error("获取用户数据出错！");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * 根据用户获取角色数据
     */
    @RequestMapping("/listUserRoles.json")
    @ResponseBody
    public List<Role> listUserRoles(User obj, HttpServletResponse response) {
        List<Role> ret = new ArrayList<>();
        try {
            ret = this.userRoleService.listUserRoles(obj.getId());
        } catch (Exception e) {
            log.error("获取角色数据出错！");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }


    /**
     * 进入通用部门页面
     */
    @RequestMapping(value = "/publicDepart.htm")
    public String publicDepart() {

        return "system/dept/publicDepart";
    }


    /**
     * 保存用户角色数据
     */
    @RequestMapping("/saveUserRoles.json")
    @ResponseBody
    public HttpResponse saveUserRoles(User obj, UserRoleCondition condition) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "保存用户角色失败！", condition);
        try {
            this.userRoleService.saveUserRoles(obj, condition);
            //设置返回参数
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "保存用户角色成功！", condition);
        } catch (Exception e) {
            log.error("保存用户角色失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 进入操作页面
     */
    @RequestMapping(value = "/manageOperation.htm")
    public String manageOperation(User obj, Model model) {
        User user = this.service.getUserOne(obj.getId());
        model.addAttribute("user", user);
        return "system/user/manageOperation";
    }

    /**
     * 公用列表数据
     */
    @RequestMapping("/publicDepart.json")
    @ResponseBody
    public String publicDepart(Depart obj) {
        String ret = "";
        try {
            List<Map<String, Object>> departs = this.departService.listPublicDepart(obj);
            //转换成json串
            ret = new ObjectMapper().writeValueAsString(departs);
        } catch (Exception e) {
            log.error("获取组织数据列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取组织机构列表数据
     */
    @RequestMapping("/listDeparts.json")
    @ResponseBody
    public List<Depart> listDeparts(User obj) {
        List<Depart> ret = new ArrayList();
        try {
            ret = this.userScopeService.listUserScopes(obj.getId(), "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        } catch (Exception e) {
            log.error("获取组织机构数据出错！");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * 获取未分配库房库区列表数据
     */
    @RequestMapping("/listWareHouses.json")
    @ResponseBody
    public List<WareHouse> listWareHouses(User obj) {
        List<WareHouse> ret = new ArrayList();
        try {
            List<WareHouse> wareHouses = this.userScopeService.listUserScopes(obj.getId(), "WareHouse", UserEnums.ScopeTypeEnum.WAREHOUSE.getType());
            Integer ids[] = new Integer[wareHouses.size()];
            for (int i = 0; i < wareHouses.size(); i++) {
                ids[i] = wareHouses.get(i).getId();
            }
            ret = this.wareHouseService.listWareHouse(obj.getZtId(), ids);
        } catch (Exception e) {
            log.error("获取库房库区数据出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取已分配库房库区列表数据
     */
    @RequestMapping("/listNewWareHouses.json")
    @ResponseBody
    public List<WareHouse> listNewWareHouses(User obj) {
        List<WareHouse> ret = new ArrayList();
        try {
            ret = this.userScopeService.listUserScopes(obj.getId(), "WareHouse", UserEnums.ScopeTypeEnum.WAREHOUSE.getType());
        } catch (Exception e) {
            log.error("获取库房库区数据出错！");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * 获取未分配物料范围列表数据
     */
    @RequestMapping("/listSpareparts.json")
    @ResponseBody
    public List<Sparepartscate> listSpareparts(User obj) {
        List<Sparepartscate> ret = new ArrayList();
        try {
            List<Sparepartscate> sparepartscates = this.userScopeService.listUserScopes(obj.getId(), "Sparepartscate", UserEnums.ScopeTypeEnum.SPAREPARTSCATE.getType());
            Integer ids[] = new Integer[sparepartscates.size()];
            if (sparepartscates.size() > 0) {
                for (int i = 0; i < sparepartscates.size(); i++) {
                    ids[i] = sparepartscates.get(i).getId();
                }
            }
            ret = this.sparepartscateService.listSpareparts(ids);
        } catch (Exception e) {
            log.error("获取物料范围数据出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取已分配物料范围列表数据
     */
    @RequestMapping("/listNewSpareparts.json")
    @ResponseBody
    public List<Sparepartscate> listNewSpareparts(User obj) {
        List<Sparepartscate> ret = new ArrayList();
        try {
            ret = this.userScopeService.listUserScopes(obj.getId(), "Sparepartscate", UserEnums.ScopeTypeEnum.SPAREPARTSCATE.getType());
        } catch (Exception e) {
            log.error("获取物料范围数据出错！");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * 获取未分配下辖科室列表数据
     */
    @RequestMapping("/listUseDeps.json")
    @ResponseBody
    public List<UseDep> listUseDeps(User obj) {
        List<UseDep> ret = new ArrayList();
        try {
            List<UseDep> useDeps = this.userScopeService.listUserScopes(obj.getId(), "UseDep", UserEnums.ScopeTypeEnum.SPAREPARTSCATE.getType());
            Integer ids[] = new Integer[useDeps.size()];
            for (int i = 0; i < useDeps.size(); i++) {
                ids[i] = useDeps.get(i).getId();
            }
            ret = this.useDepService.listDepartDep(obj.getZtId(), ids);
        } catch (Exception e) {
            log.error("获取物料范围数据出错！");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * 获取已分配下辖科室列表数据
     */
    @RequestMapping("/listNewUseDeps.json")
    @ResponseBody
    public List<UseDep> listNewUseDeps(User obj) {
        List<UseDep> ret = new ArrayList();
        try {
            ret = this.userScopeService.listUserScopes(obj.getId(), "UseDep", UserEnums.ScopeTypeEnum.WAREHOUSE.getType());
        } catch (Exception e) {
            log.error("获取库房库区数据出错！");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * 保存操作范围
     */
    @RequestMapping(value = "/saveUserScopes.json")
    @ResponseBody
    public HttpResponse saveUserScopes(@ModelAttribute("us") User obj, UserScopeCondition condition, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "保存操作范围失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        BaseLocal local = UserScopeDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        UserScopeDaoGuard.setThreadLocal(local);
        try {
            this.userScopeService.saveUserScopes(obj, condition);
            //设置返回参数
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "保存操作范围成功！", obj);
            String logDesc = "人员Id:" + obj.getId() + ";组织机构Id:" + condition.getDepts().toString() +
                    ";库房库区ID" + condition.getWareHouses().toString() + ";物料范围Id:" + condition.getSpareparts().toString() +
                    ";下辖科室Id:" + condition.getUseDeps().toString();
            //日志
//                this.logService.addSystemLog(0, SystemLogEnums.LogObject.PERSON_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
//                        logDesc, userIp, (int)userId);
        } catch (Exception e) {
            log.error("新增失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 进入通用部门用户页面
     */
    @RequestMapping(value = "/publicDepartUser.htm")
    public String publicDepartUser(String type, Model model) {
        model.addAttribute("type", type);
        return "system/user/publicDepartUser";
    }


    /** 密码修改
     *
     * @param jiuPassword   原密码
     * @param newPassword   新密码
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/usersPass.json")
    @ResponseBody
    public HttpResponse usersPass(String jiuPassword, String newPassword,
                                  HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse ret = new HttpResponse();
        try {
            Integer userId = 0;
            if (request.getSession().getAttribute("userId") != null) {
                userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
            }
            User user=this.service.getUserOne(userId);
            if(user.getPassWord().equals(jiuPassword)){
//                user.setPassWord(Tool.md5(newPassword));
                user.setPassWord(newPassword);
                this.service.updateUserPassword(user);
                ret.setStatus(HttpResponse.Status.SUCCESS);
                ret.setMessage("修改成功！");
            }else {
                ret.setStatus(HttpResponse.Status.FAILURE);
                ret.setMessage("原密码错误！");
            }
        } catch (Exception e) {
            log.error("修改密码错误");
            log.errorPrintStacktrace(e);
            ret.setStatus(HttpResponse.Status.FAILURE);
            ret.setMessage("修改密码错误！");
        }
        return ret;
    }
}
