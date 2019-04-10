package com.zthc.ewms.system.useDep.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.service.DepartService;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.spare.entity.guard.Sparepartscate;
import com.zthc.ewms.system.spare.service.SparepartscateService;
import com.zthc.ewms.system.useDep.entity.OfficesScopeCondition;
import com.zthc.ewms.system.useDep.entity.UseDep;
import com.zthc.ewms.system.useDep.entity.UseDepCondition;
import com.zthc.ewms.system.useDep.service.UseDepService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/useDep")
public class UseDepController {

    @Resource(name = "useDepService")
    public UseDepService service;

    @Resource(name = "logService")
    public LogService logService;

    @Resource(name = "sparepartscateService")
    public SparepartscateService sparepartscateService;

    @Resource(name = "departService")
    public DepartService departService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.useDep.controller.UseDepController");
    }

     /*-------------------------------------------------跳转方法-------------------------------------------------*/

    /**
     * 进入使用单位页面
     *
     * @return
     */
    @RequestMapping(value = "/manageUseDep.htm")
    public String manageUseDep() {
        return "system/useDep/manageUseDep";
    }

    /**
     * 进入新增修改页面
     *
     * @param useDep
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateUseDep.htm")
    public String updateUseDep(@ModelAttribute("UseDep") UseDep useDep, HttpServletRequest request,
                               HttpServletResponse response, Model model) {
        log.debug("进入使用单位新增更新页面,当前方法为updateUseDep");
        if (StringUtils.isEmpty(useDep.getId())) {
            log.debug("进入使用单位新增页面");
            return "system/useDep/updateUseDep";
        } else {
            log.debug("进入使用单位编辑页面,当前申请单位ID:" + useDep.getId());
            UseDep ret = this.service.getOne(useDep.getId());
            Depart depart = this.departService.getDepartByZtId(ret.getZtId());

            model.addAttribute("depName", depart.getName());
            model.addAttribute("useDepValue", ret);
            log.debug("获取单条使用单位信息成功,跳转到页面");

            return "system/useDep/updateUseDep";
        }
    }

    /**
     * 物料范围操作页面
     *
     * @param useDep
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/showMaterielRange.htm")
    public String showMaterielRange(@ModelAttribute("UseDep") UseDep useDep, OfficesScopeCondition condition,
                                    HttpServletRequest request,
                                    HttpServletResponse response, Model model) {
        log.debug("进入使用单位-物料范围操作页面,当前方法为showMaterielRange");
        model.addAttribute("useId", useDep.getId());
        model.addAttribute("ztId", useDep.getZtId());
        return "system/useDep/manageMaterielRange";
    }

    /**
     * 使用单位通用调用页面
     *
     * @param useDep
     * @param condition
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/generalUseDep.htm")
    public String generalUseDep(@ModelAttribute("UseDep") UseDep useDep, UseDepCondition condition,
                                HttpServletRequest request, HttpServletResponse response, Model model) {
        log.error("调用使用单位通用页面,当前方法:generalUseDep");
        Integer uid = 0;
        Integer ztId = 0;
        if (!StringUtils.isEmpty(useDep.getId()) && 0 != useDep.getId()) {
            uid = useDep.getId();
        }
        if (!StringUtils.isEmpty(useDep.getZtId()) && 0 != useDep.getZtId()) {
            ztId = useDep.getZtId();
        }

        model.addAttribute("usedepId", uid);
        model.addAttribute("ztId", ztId);

        return "system/useDep/generalUseDep";
    }

    @RequestMapping(value = "/generalUseDepDepart.htm")
    public String generalUseDepDepart(@ModelAttribute("UseDep") UseDep useDep, HttpServletRequest request, Model
            model) {
        log.debug("调用使用单位科室通用页面,当前方法:generalUseDepDepart");
        Integer ztId = null == useDep.getZtId() ? 0 : useDep.getZtId();

        model.addAttribute("ztId", ztId);

        return "system/useDep/generalUseDepDepart";
    }

     /*-------------------------------------------------基础方法-------------------------------------------------*/

    /**
     * 获取使用单位列表
     *
     * @param useDep
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listUseDep.json")
    @ResponseBody
    public LayuiPage<UseDep> useDepList(@ModelAttribute("UseDep") UseDep useDep, UseDepCondition condition,
                                        HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取使用单位方法,当前方法名:useDepList");
        LayuiPage<UseDep> ret = null;
        try {
            ret = service.listUseDep(useDep, condition);
        } catch (Exception e) {
            log.error("获取使用单位列表失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取通用使用单位列表
     *
     * @param useDep
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listUseDepGeneral.json")
    @ResponseBody
    public LayuiPage<UseDep> generalList(@ModelAttribute("UseDep") UseDep useDep, UseDepCondition condition,
                                         HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取通用使用单位列表方法,当前方法名:generalList");
        LayuiPage<UseDep> ret = null;
        try {
            ret = this.service.listGeneral(useDep, condition);
        } catch (Exception e) {
            log.error("获取通用使用单位列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取通用使用单位科室列表
     *
     * @param useDep
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listUseDepDepartGeneral.json")
    @ResponseBody
    public LayuiPage<UseDep> generalDepartList(@ModelAttribute("UseDep") UseDep useDep, UseDepCondition condition,
                                               HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取通用使用单位科室列表方法,当前方法名:generalDepartList");
        LayuiPage<UseDep> ret = null;
        try {
            ret = this.service.listDepartGeneral(useDep, condition);
        } catch (Exception e) {
            log.error("获取通用使用单位列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取使用单位物料范围
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/officesScopeList.json")
    @ResponseBody
    public Map<String, Object> officesScopeList(UseDepCondition condition, HttpServletRequest request,
                                                HttpServletResponse response) {
        log.debug("进入获取使用单位物料范围方法,当前方法名:officesScopeList,当前使用单位ID:" + condition.getId());
        Map<String, Object> args = new HashMap<>();
        try {

            //获取使用单位已选择的物料分类列表
            List<Sparepartscate> officesScopeList = this.sparepartscateService.listOfficeScope(condition.getId());

            //获取已选择的物料分类列表ID
            Integer[] ids = new Integer[officesScopeList.size()];

            if (officesScopeList.size() != 0) {
                for (int i = 0; i < officesScopeList.size(); i++) {
                    ids[i] = officesScopeList.get(i).getId();
                }
            }
            //获取当前未选择的物料分类列表
            List<Sparepartscate> sparepartscateList = this.sparepartscateService.getSpareList(ids);
            args.put("officesList", officesScopeList);
            args.put("scopeList", sparepartscateList);

        } catch (Exception e) {
            log.error("获取使用单位物料范围方法失败");
            log.errorPrintStacktrace(e);
        }
        return args;
    }


    /**
     * 新增修改方法
     *
     * @param useDep
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editUseDep.json")
    @ResponseBody
    public HttpResponse useDepEdit(@ModelAttribute("UseDep") UseDep useDep, HttpServletRequest request,
                                   HttpServletResponse response) {
        HttpResponse ret;
        log.debug("进入使用单位更新方法,useDepEdit");
        String str;
        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        //进入方法
        try {
            if (!StringUtils.isEmpty(useDep.getId()) && 0 != useDep.getId()) {
                log.debug("进入使用单位修改方法,当前使用单位ID:" + useDep.getId());
                int check = this.service.checkUseDep(useDep.getCode(), useDep.getName(), useDep.getId());
                if (check >= 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE, "保存失败,拥有重复的编码或名称", null);
                    return ret;
                } else {
                    useDep.setUpdater(userId);
                    useDep.setZtId(useDep.getOrganizationType());
                    this.service.editUseDep(useDep);
                    log.debug("编辑使用单位成功");
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS, "编辑使用单位成功", null);
                    str = logService.objectToJson(useDep);
                    this.logService.addSystemLog(0, 2000, 200, "编辑使用单位:" + str,
                            userIp, userId);

                }
            } else {
                log.debug("进入使用单位新增方法");
                int check = this.service.checkUseDep(useDep.getCode(), useDep.getName(), 0);
                if (check >= 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE, "保存失败,拥有重复的编码或名称", null);
                    return ret;
                } else {
                    useDep.setCreator(userId);
                    useDep.setDeleted(1);
                    useDep.setAddType(1);
                    useDep.setErpId(0);
                    useDep.setZtId(useDep.getOrganizationType());
                    this.service.addUseDep(useDep);
                    log.debug("新增使用单位成功");
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS, "新增使用单位成功", null);
                    str = logService.objectToJson(useDep);
                    this.logService.addSystemLog(0, 2000, 100, "新增使用单位:" + str,
                            userIp, userId);
                }
            }
        } catch (Exception e) {
            log.error("更新记录出错");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "更新出错,原因:" + e.getMessage(), null);
        }
        return ret;
    }

    /**
     * 禁用使用单位方法
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delUseDep.json")
    @ResponseBody
    public HttpResponse useDepDel(UseDepCondition condition, HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpResponse ret;
        log.debug("进入禁用使用单位方法");
        String str = "";

        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        try {

            this.service.delUseDep(condition.getIds());

            log.debug("禁用使用单位成功");
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "禁用使用单位成功", null);

            str = logService.objectToJson(condition.getIds());
            this.logService.addSystemLog(0, 2000, 300, "删除申请单位:" + str,
                    userIp, userId);
        } catch (Exception e) {
            log.error("禁用使用单位出错");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "禁用使用单位出错,原因:" + e.getMessage(), null);
        }
        return ret;
    }

    /**
     * 使用单位添加物料范围方法
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveMaterielRange.json")
    @ResponseBody
    public HttpResponse saveMaterielRange(UseDepCondition condition, HttpServletRequest request,
                                          HttpServletResponse response) {
        log.debug("进入使用单位添加物料范围方法,当前方法名:saveMaterielRange");
        HttpResponse ret;

        Integer[] arr = condition.getIds();

        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        try {
            this.service.saveMaterielRange(arr, condition, userId);
            log.debug("新增物料范围成功");
            ret = new HttpResponse(null);
            this.logService.addSystemLog(0, 2000, 100, "新增物料范围,物料范围ID:"
                    + logService.objectToJson(arr), userIp, userId);

        } catch (Exception e) {
            log.error("添加物料范围异常");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "新增物料范围失败,原因:" + e.getMessage(), null);
        }

        return ret;
    }


}
