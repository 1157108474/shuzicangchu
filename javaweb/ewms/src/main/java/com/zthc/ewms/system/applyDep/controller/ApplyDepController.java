package com.zthc.ewms.system.applyDep.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.applyDep.entity.ApplyDep;
import com.zthc.ewms.system.applyDep.entity.ApplyDepCondition;
import com.zthc.ewms.system.applyDep.service.ApplyDepService;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.service.DepartService;
import com.zthc.ewms.system.log.service.LogService;
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

@Controller
@RequestMapping("/system/applyDep")
public class ApplyDepController {

    @Resource(name = "applyDepService")
    public ApplyDepService service;

    @Resource(name = "logService")
    public LogService logService;

    @Resource(name = "departService")
    public DepartService departService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.applyDep.controller.ApplyDepController");
    }

     /*-------------------------------------------------跳转方法-------------------------------------------------*/

    /**
     * 进入申请单位页面
     *
     * @return
     */
    @RequestMapping(value = "/manageApplyDep.htm")
    public String manageApplyDep() {
        return "system/applyDep/manageApplyDep";
    }

    /**
     * 进入新增修改页面
     *
     * @param applyDep
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateApplyDep.htm")
    public String updateApplyDep(@ModelAttribute("ApplyDep") ApplyDep applyDep, HttpServletRequest request,
                                 HttpServletResponse response, Model model) {
        log.debug("进入申请单位新增更新页面,当前方法为updateApplyDep,当前申请单位ID:" + applyDep.getId());
        if (StringUtils.isEmpty(applyDep.getId())) {
            log.debug("进入申请单位新增页面");
            return "system/applyDep/updateApplyDep";
        } else {
            log.debug("进入申请单位编辑页面,当前申请单位ID:" + applyDep.getId());

            ApplyDep ret = this.service.getOne(applyDep.getId());
            Depart depart = this.departService.getDepartOne(ret.getZtId());

            model.addAttribute("depName", depart.getName());
            model.addAttribute("applyDepValue", ret);

            log.debug("获取单条申请单位信息成功,跳转到页面");
            return "system/applyDep/updateApplyDep";
        }
    }

    /**
     * 申请单位通用调用页面
     *
     * @param applyDep
     * @param condition
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/generalApplyDep.htm")
    public String generalApplyDep(@ModelAttribute("ApplyDep") ApplyDep applyDep, ApplyDepCondition condition,
                                  HttpServletRequest request, HttpServletResponse response, Model model) {
        log.error("调用使用单位通用页面,当前方法:generalApplyDep");
        Integer aid = 0;
        if (!StringUtils.isEmpty(applyDep.getId()) && 0 != applyDep.getId()) {
            aid = applyDep.getId();
        }
        model.addAttribute("applydepId", aid);
        return "system/applyDep/generalApplyDep";
    }


     /*-------------------------------------------------基础方法-------------------------------------------------*/

    /**
     * 获取申请单位列表
     *
     * @param applyDep
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listApplyDep.json")
    @ResponseBody
    public LayuiPage<ApplyDep> applyDepList(@ModelAttribute("ApplyDep") ApplyDep applyDep, ApplyDepCondition condition,
                                            HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取申请单位方法,当前方法名:applyDepList");
        LayuiPage<ApplyDep> ret = null;
        try {
            ret = service.listApplyDep(applyDep, condition);
        } catch (Exception e) {
            log.error("获取申请单位列表失败");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 获取通用使用单位列表
     *
     * @param applyDep
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listApplyDepGeneral.json")
    @ResponseBody
    public LayuiPage<ApplyDep> generalList(@ModelAttribute("ApplyDep") ApplyDep applyDep, ApplyDepCondition condition,
                                           HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取通用申请单位列表方法,当前方法名:generalList");
        LayuiPage<ApplyDep> ret = null;
        try {
            ret = this.service.listGeneral(applyDep, condition);
        } catch (Exception e) {
            log.error("获取通用申请单位列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 新增修改方法
     *
     * @param applyDep
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editApplyDep.json")
    @ResponseBody
    public HttpResponse applyDepEdit(@ModelAttribute("ApplyDep") ApplyDep applyDep, HttpServletRequest request,
                                     HttpServletResponse response) {
        log.debug("进入申请单位更新方法,applyDepEdit");
        String str = "";
        HttpResponse ret;

        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Object requestUserName = session.getAttribute("userName");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());
        String userName = (null == requestUserName ? null : requestUserName.toString());

        //进入方法
        try {
            if (!StringUtils.isEmpty(applyDep.getId()) && 0 != applyDep.getId()) {
                log.debug("进入申请单位修改方法,当前申请单位ID:" + applyDep.getId());
                int check = this.service.checkApplyDep(applyDep.getCode(), applyDep.getName(), applyDep.getId());

                if (check >= 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE,"保存失败，拥有重复的编码或名称",null);

                    return ret;
                } else {
                    this.service.editApplyDep(applyDep);
                    log.debug("编辑申请单位成功");

                    ret = new HttpResponse(HttpResponse.Status.SUCCESS,"编辑申请单位成功",null);

                    str = logService.objectToJson(applyDep);
                    this.logService.addSystemLog(0, 2100, 200, "编辑申请单位:" + str,
                            userIp, userId);
                }
            } else {
                log.debug("进入申请单位新增方法");

                int check = this.service.checkApplyDep(applyDep.getCode(), applyDep.getName(), 0);
                if (check >= 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE,"保存失败,拥有重复的编码或名称",null);

                    return ret;
                } else {

                    applyDep.setCreator(userId);
                    applyDep.setCreateName(userName);
                    applyDep.setDeleted(1);
                    applyDep.setAddType(1);
                    if(StringUtils.isEmpty(applyDep.getErpId())){
                        applyDep.setErpId("");
                    }
                    this.service.addApplyDep(applyDep);

                    log.debug("新增申请单位成功");
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS,"新增申请单位成功",applyDep);

                    str = logService.objectToJson(applyDep);
                    this.logService.addSystemLog(0, 2100, 100, "新增申请单位:" + str,
                            userIp, userId);
                }
            }
        } catch (Exception e) {
            log.error("更新记录出错");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE,e.getMessage(),null);
        }
        return ret;
    }

    /**
     * 删除申请单位方法
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delApplyDep.json")
    @ResponseBody
    public HttpResponse ApplyDepDel(ApplyDepCondition condition, HttpServletRequest request,
                                           HttpServletResponse response) {
        HttpResponse ret;
        String str = "";

        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());


        try {
            str = logService.objectToJson(condition.getIds());
            log.debug("进入删除申请单位方法,当前id:"+str);

            this.service.delApplyDep(condition.getIds());
            log.debug("删除申请单位成功");

            ret = new HttpResponse(HttpResponse.Status.SUCCESS,"删除申请单位成功",null);

            this.logService.addSystemLog(0, 2100, 300, "删除申请单位:" + str,
                    userIp, userId);
        } catch (Exception e) {
            log.error("删除申请单位出错");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE,e.getMessage(),null);
        }
        return ret;
    }

}
