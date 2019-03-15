package com.zthc.ewms.sheet.controller;

import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.dao.guard.SheetDaoGuard;
import com.zthc.ewms.sheet.dao.guard.SheetDetailDaoGuard;
import com.zthc.ewms.sheet.entity.apply.*;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.sheet.service.SheetApplyService;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.applyDep.entity.ApplyDep;
import com.zthc.ewms.system.applyDep.service.ApplyDepService;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.useDep.entity.UseDep;
import com.zthc.ewms.system.useDep.service.UseDepService;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import drk.system.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.activiti.engine.TaskService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/sheet/apply")
public class SheetApplyController extends SheetControllerGuard {

    private final static Log log = Log.getLog("com.system.Apply.controller.ApplyControllerGuard");
    @Resource(name = "dictionaryService")
    public DictionaryService dictionaryService;
    @Resource(name = "sheetDetailService")
    public SheetDetailService sheetDetailService;
    @Resource(name = "sheetApplyService")
    public SheetApplyService sheetApplyService;
    @Resource(name = "applyDepService")
    public ApplyDepService applyDepService;
    @Resource(name = "activitiService")
    public ActivitiService activitiService;
    @Resource(name = "taskService")
    public TaskService taskService;
    @Resource(name = "userScopeService")
    public UserScopeService userScopeService;
    @Resource(name = "useDepService")
    public UseDepService useDepService;
    //新增页面及方法

    /**
     * 进入新增物资申领单页面
     */
    @RequestMapping(value = "/apply.htm")
    public String apply(ManageApply obj, HttpServletRequest request, Model model) {

        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        Integer userId = 0, ztId = 0, useDepId = 0;
        String userName = "", useDepName = "", departName = "",userCode = "";

        if (null != userIdStr) {
            userId = Integer.valueOf(userIdStr.toString());
            userName = session.getAttribute("userName").toString();
            userCode = session.getAttribute("userCode").toString();
            useDepId = Integer.valueOf(session.getAttribute("useDepId").toString());
            useDepName = session.getAttribute("useDepName").toString();
            departName = session.getAttribute("departName").toString();
            ztId = Integer.valueOf(session.getAttribute("ztId").toString());
        }
        obj.setDepName(departName);
        obj.setPersonName(userName);
        obj.setUsedDepartId(useDepId);
        obj.setUseUnitName(useDepName);
        obj.setExtendString2(departName);
        obj.setZtId(ztId);
        //获取申领单位列表
        List<ApplyDep> applyDeps = this.applyDepService.listApplyDepZt(ztId);
        model.addAttribute("applyDeps", applyDeps);
        //查询字典，获取资金来源
        List<Dictionary> fundsSources = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());
        model.addAttribute("fundsSources", fundsSources);
        //获取菜单code
        String menuCode = request.getParameter("menuCode");
        model.addAttribute("menuCode", menuCode);
        //查第一环节按钮
        List<Dictionary> dictionaries = activitiService.getPartButton(menuCode,userCode);
        model.addAttribute("buttons", dictionaries);
        obj.setCreateDateStr(new DateTime().toString("yyyy年MM月dd日 HH:mm:ss"));
        model.addAttribute("sheet", obj);

        return "sheet/apply/apply";
    }

    /**
     * 修改页面
     *
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {

        ManageApply sheet = this.service.getApplySheetOne(id);
        //获取申领单位列表
        List<ApplyDep> applyDeps = this.applyDepService.listApplyDepZt(sheet.getZtId());
        model.addAttribute("applyDeps", applyDeps);
        //查询字典，获取资金来源
        List<Dictionary> fundsSources = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());
        model.addAttribute("fundsSources", fundsSources);
       /* if(sheet.getStatus()!=39){
            request.setAttribute("taskId", sheet.getRouteId());
            return "/system/activitiListen/showProcessCompleteEdit";
        }else {
            sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy年MM月dd日 HH:mm:ss"));
            model.addAttribute("sheet", sheet);
        }*/
        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy年MM月dd日 HH:mm:ss"));
        model.addAttribute("sheet", sheet);
        return this.getUrl(request, sheet.getRouteId().intValue() + "", model, "sheet/apply/apply", sheet.getStatus());
    }

    /**
     * 页面_编辑
     */
    @RequestMapping(value = "/editApply.htm")
    public String editApply(Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {
        ManageApply sheet = this.sheetApplyService.getApplyOne(id);
        model.addAttribute("sheet", sheet);
        //查询字典，获取资金来源
        List<Dictionary> fundsSources = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());
        model.addAttribute("fundsSources", fundsSources);
        //获取申领单位列表
        List<ApplyDep> applyDeps = this.applyDepService.listApplyDepZt(sheet.getZtId());
        model.addAttribute("applyDeps", applyDeps);
        return "sheet/apply/apply";
    }

    /**
     * 新增领料单页面_进入人员信息页面
     */
    @RequestMapping(value = "/openWorkManName.htm")
    public String openWorkManName() {
        return "sheet/apply/noPlanDetailed";
    }


    /**
     * 新增领料单页面_明细列表
     */
    @RequestMapping(value = "/listSheetApply")
    @ResponseBody
    public LayuiPage<Apply> listSheetApply(SheetDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<Apply> ret = null;
        String queryCriteria = "";
        //查询条件
        Map<String, Object> param = new HashMap<>();
        //单据ID
        queryCriteria += " and sheetId = :sheetId ";
        param.put("sheetId", obj.getSheetId());
//        if (!StringUtils.isEmpty(obj.getSheetId())) {
//            queryCriteria += " and sheetId = :sheetId ";
//            param.put("sheetId", obj.getSheetId());
//        }
        //ID
        if (!StringUtils.isEmpty(obj.getId())) {
            queryCriteria += " and id = :id ";
            param.put("id", obj.getId());
        }
        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by id desc");
        try {
            ret = this.sheetApplyService.publicDetails("Apply", "id", param, condition);
        } catch (Exception e) {
            log.error("获取物资领料数据列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 新增领料单页面_操作履历列表
     */
    @RequestMapping(value = "/listWaitTaks")
    @ResponseBody
    public LayuiPage<Apply> listWaitTaks(SheetDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取操作履历列表方法");
        LayuiPage<Apply> ret = null;
        String queryCriteria = "";
        //查询条件
        Map<String, Object> param = new HashMap<>();
        //单据ID
        if (!StringUtils.isEmpty(obj.getSheetId())) {
            queryCriteria += " and sheetId = :sheetId ";
            param.put("sheetId", obj.getSheetId());
        }
        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by id desc");
        try {
            //TODO 调用获取操作履历通用方法
            ret = new LayuiPage<>();
            //ret = this.sheetApplyService.publicDetails("Apply", "id", param, condition);
        } catch (Exception e) {
            log.error("获取操作履历列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 新增领料单页面_进入计划明细页面
     */
    @RequestMapping(value = "/openPlanDetailWindow.htm")
    public String openPlanDetailWindow(Integer sheetId, Integer ztId,Integer usedDepartId, Model model) {
        model.addAttribute("sheetId", sheetId);
        model.addAttribute("ztId", ztId);
        model.addAttribute("useDepId", usedDepartId);
        return "sheet/apply/planDetailed";
    }

    /**
     * 计划明细页面_计划明细列表
     */
    @RequestMapping(value = "/listPlanDetail.json")
    @ResponseBody
    public LayuiPage<PlanDetail> listPlanDetail(PlanDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<PlanDetail> ret = null;
        String queryCriteria = " and planCount > haveslCount and storeuseCount>0 ";
        //查询条件
        Map<String, Object> param = new HashMap<>();
        //单位Id
        if (!StringUtils.isEmpty(obj.getUseDepId())) {
            queryCriteria += " and useDepId = :useDepId";
            param.put("useDepId", obj.getUseDepId());
        }
        //ZTID
       /* if (!StringUtils.isEmpty(obj.getZtId())) {
            queryCriteria += " and ztId = :ztId";
            param.put("ztId", obj.getZtId());
        }*/
        //计划编号
        if (!StringUtils.isEmpty(obj.getPlanCode())) {
            queryCriteria += " and planCode like :planCode";
            param.put("planCode", "%" + obj.getPlanCode().trim() + "%");
        }
        //物料编码
        if (!StringUtils.isEmpty(obj.getMaterialCode())) {
            queryCriteria += " and materialCode like :materialCode";
            param.put("materialCode", "%" + obj.getMaterialCode().trim() + "%");
        }
        //物料描述
        if (!StringUtils.isEmpty(obj.getMaterialDes())) {
            queryCriteria += " and materialDes like :materialDes";
            param.put("materialDes", "%" + obj.getMaterialDes().trim() + "%");
        }
        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by id desc");
        try {
            ret = this.sheetApplyService.publicDetails("PlanDetail", "id", param, condition);
        } catch (Exception e) {
            log.error("获取计划明细数据列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 新增领料单页面_进入无计划明细页面
     */
    @RequestMapping(value = "/openNoPlanDetailWindow.htm")
    public String openNoPlanDetailWindow(Integer sheetId, Integer ztId,Integer usedDepartId, Model model) {
        model.addAttribute("sheetId", sheetId);

        model.addAttribute("ztId", ztId);
        model.addAttribute("officeId", usedDepartId);
        return "sheet/apply/noPlanDetailed";
    }

    /**
     * 无计划明细页面_无计划明细列表
     */
    @RequestMapping(value = "/listNoPlanDetail.json")
    @ResponseBody
    public LayuiPage<NoPlanDetail> listNoPlanDetail(NoPlanDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取无计划明细列表方法");
        LayuiPage<NoPlanDetail> ret = null;
        //查询条件
        String queryCriteria = " and storeCount<>0 and storeuseCount>0 ";
        Map<String, Object> param = new HashMap<>();
        //ZTID
        if (!StringUtils.isEmpty(obj.getZtId())) {
            queryCriteria += " and ztId = :ztId";
            param.put("ztId", obj.getZtId());
        }
        //单位Id
        if (!StringUtils.isEmpty(obj.getOfficesId())) {
            queryCriteria += " and officesId = :officesId";
            param.put("officesId", obj.getOfficesId());
        }
        //物料编码
        if (!StringUtils.isEmpty(obj.getMaterialCode())) {
            queryCriteria += " and materialCode like :materialCode";
            param.put("materialCode", "%" + obj.getMaterialCode() + "%");
        }
        //物料描述
        if (!StringUtils.isEmpty(obj.getDescripTion())) {
            queryCriteria += " and descripTion like :descripTion";
            param.put("descripTion", "%%" + obj.getDescripTion().trim() + "%%");
        }
        condition.setQueryCriteria(queryCriteria);
//        condition.setOrderBys(" order by materialCode ASC");
        log.error("开始时间："+new Date().toString());
        try {
            ret = this.sheetApplyService.publicDetails("NoPlanDetail", "*", param, condition);
        } catch (Exception e) {
            log.error("获取无计划明细数据列表出错！");
            log.errorPrintStacktrace(e);
        }
        log.error("结束时间："+new Date().toString());
        return ret;
    }

    /**
     * 添加明细
     */
    @RequestMapping(value = "/saveDetail.json")
    @ResponseBody
    public HttpResponse<NoPlanDetail> saveDetail(PlanDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        HttpResponse<NoPlanDetail> ret = new HttpResponse(HttpResponse.Status.FAILURE, "添加明细失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        int userId = (null == userIdStr ? 0 : Integer.parseInt(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        // 把当前操作人ID存入当前线程对象中
        BaseLocal local = SheetDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        SheetDaoGuard.setThreadLocal(local);

        JSONArray detailJson = JSONArray.fromObject(request.getParameter("details"));
        Collection collection = JSONArray.toCollection(detailJson);
        Iterator it = collection.iterator();
        SheetDetail detail = null;
        List<SheetDetail> detailList = new ArrayList<>();
        Date now = new Date();
        String locationCode;
        while (it.hasNext()) {
            JSONObject jsonObj = JSONObject.fromObject(it.next());
            detail = (SheetDetail) JSONObject.toBean(jsonObj, SheetDetail.class);
            detail.setGuid(java.util.UUID.randomUUID().toString());
            detail.setCreator(userId);
            detail.setCreateDate(now);
            detailList.add(detail);
        }
        try {
            //TODO 调用公用添加明细方法
            this.sheetDetailService.saveSheetDetails("apply", detailList);
            //设置返回参数
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "添加明细成功！", obj);
        } catch (Exception e) {
            log.error("添加明细:" + obj.getPlanCode() + "失败！");
            log.errorPrintStacktrace(e);
        }
        //日志
//        this.logService.addSystemLog(0, SystemLogEnums.LogObject.ORGANIZATION.getCode(),
//                SystemLogEnums.LogAction.ADD.getCode(), "新增组织机构:" + obj.getName(), userIp, (int) userId);
        return ret;
    }


    /**
     * 新增领料单页面_进入编辑明细页面
     */
    @RequestMapping(value = "/openEditApply.htm")
    public String openEditApply(Integer id, Integer sheetId, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("sheetId", sheetId);
        return "sheet/apply/editApply";
    }

    /**
     * 修改申领明细方法
     */
    @RequestMapping(value = "/updateApplySheet.json")
    @ResponseBody
    public HttpResponse<SheetDetail> updateApplySheet(SheetDetail obj, HttpServletRequest request) {
        HttpResponse<SheetDetail> ret = new HttpResponse(HttpResponse.Status.FAILURE, "修改明细失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        BaseLocal local = SheetDetailDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
        SheetDetailDaoGuard.setThreadLocal(local);
        try {
            int i = this.sheetDetailService.updateApplySheet(obj);
            if (i == 1) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "修改明细成功！", obj);
            }
        } catch (Exception e) {
            log.error("修改申领明细:" + obj.getId() + "失败！");
            log.errorPrintStacktrace(e);
        }
        //日志
//        this.logService.addSystemLog(0, SystemLogEnums.LogObject.ORGANIZATION.getCode(),
//                SystemLogEnums.LogAction.EDIT.getCode(), "修改组织机构:" + obj.getName(), userIp, (int) userId);
        return ret;
    }


    //管理页面及方法

    /**
     * 进入物资申领单管理页面
     */
    @RequestMapping(value = "/manageApply.htm")
    public String manageApply(ManageApply obj, HttpServletRequest request, Model model) {
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        Integer userId = 0, ztId = 0, useDepId = 0;
        String userName = "", useDepName = "", departName = "";
        if (null != userIdStr) {
            userId = Integer.valueOf(userIdStr.toString());
            userName = session.getAttribute("userName").toString();
            useDepId = Integer.valueOf(session.getAttribute("useDepId").toString());
            useDepName = session.getAttribute("useDepName").toString();
            departName = session.getAttribute("departName").toString();
            ztId = Integer.valueOf(session.getAttribute("ztId").toString());
        }
        obj.setDepName(departName);
        obj.setPersonName(userName);
        //obj.setUsedDepartId(useDepId);
        //obj.setUseUnitName(useDepName);
        obj.setExtendString2(departName);
        obj.setZtId(ztId);
        model.addAttribute("sheet", obj);
        //获取使用单位列表
        List<UseDep> useDeps = this.useDepService.listDepartDep(ztId,null);
        model.addAttribute("useDeps",useDeps);
        //获取申领单位列表
        List<ApplyDep> applyDeps = this.applyDepService.listApplyDepZt(ztId);
        model.addAttribute("applyDeps", applyDeps);
        //获取库存组织
        List<Depart> departs = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        model.addAttribute("departs", departs);
        //查询字典，获取单据状态
        List<Dictionary> dictionaries = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.ReceiptStatus.getCode());
        model.addAttribute("dictionaries", dictionaries);


        return "sheet/apply/manageApply";
    }

    /**
     * 物资申领单管理_单据列表
     */
    @RequestMapping(value = "/listManageApply")
    @ResponseBody
    public LayuiPage<ManageApply> listManageApply(ManageApply obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<ManageApply> ret = null;
        //查询条件
        String queryCriteria = "";
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        Map<String, Object> param = new HashMap<>();
        //状态
        if (!StringUtils.isEmpty(obj.getStatus()) && (obj.getStatus() != 0)) {
            queryCriteria += " and status = :status";
            param.put("status", obj.getStatus());
        }
        //状态
        if (!StringUtils.isEmpty(obj.getCode())) {
            queryCriteria += " and code = :code";
            param.put("code", obj.getCode());
        }
        //ZTID
        if (!StringUtils.isEmpty(obj.getZtId()) && (obj.getZtId() != 0)) {
            queryCriteria += " and ztId = :ztId";
            param.put("ztId", obj.getZtId());
        }
        //上级审批ID
        if (!StringUtils.isEmpty(obj.getOfficesId()) && (obj.getOfficesId() != 0)) {
            queryCriteria += " and officesId = :officesId";
            param.put("officesId", obj.getOfficesId());
        }
        //申请单位ID
        if (!StringUtils.isEmpty(obj.getApplyDepartId()) && (obj.getApplyDepartId() != 0)) {
            queryCriteria += " and applyDepartId = :applyDepartId";
            param.put("applyDepartId", obj.getApplyDepartId());
        }
        //使用单位ID
        if (!StringUtils.isEmpty(obj.getUsedDepartId()) && (obj.getUsedDepartId() != 0)) {
            queryCriteria += " and usedDepartId = :usedDepartId";
            param.put("usedDepartId", obj.getUsedDepartId());
        }
        //创建人
        if (!StringUtils.isEmpty(obj.getCreator()) && (obj.getCreator() != 0)) {
            queryCriteria += " and creator = :creator";
            param.put("creator", obj.getCreator());
        }else{
        	queryCriteria += " and creator = :creator";
            param.put("creator", userId);
        }
        //开始时间
        if (!StringUtils.isEmpty(condition.getBeginDate())) {
            queryCriteria += " and createDate >= to_date('" + condition.getBeginDate().trim() + "','yyyy-MM-dd') ";
        }
        //结束时间
        if (!StringUtils.isEmpty(condition.getEndDate())) {
            queryCriteria += " and createDate <= to_date('" + condition.getEndDate().trim() + " 23:59:59','yyyy-MM-dd HH24:MI:SS') ";
        }


        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by code desc");
        try {
            ret = this.sheetApplyService.publicDetails("ManageApply", "id", param, condition);
        } catch (Exception e) {
            log.error("获取单据列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 进入通用部门用户页面
     */
    @RequestMapping(value = "/publicDepartUser.htm")
    public String publicDepartUser() {

        return "system/user/publicDepartUser";
    }

    /**
     * 进入申领单号页面
     */
    @RequestMapping(value = "/openApply.htm")
    public String openApply(Integer ztId, Model model) {
        model.addAttribute("ztId", ztId);
        return "sheet/apply/openApply";
    }

    /**
     * 查询申领单号列表
     */
    @RequestMapping(value = "/listSqNum.json")
    @ResponseBody
    public LayuiPage<SqNum> listSqNum(SqNum obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<SqNum> ret = null;
        String queryCriteria = "and  slcount > ckcount";
        //查询条件
        Map<String, Object> param = new HashMap<>();
        //code
        if (!StringUtils.isEmpty(obj.getCode())) {
            queryCriteria += " and code like :code";
            param.put("code", "%" + obj.getCode().trim()+ "%");
        }
        //ztid
        /*if (!StringUtils.isEmpty(obj.getZtId())) {
            queryCriteria += " and ztId = :ztId";
            param.put("ztId", obj.getZtId());
        }*/
        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by id desc");
        try {
            ret = this.sheetApplyService.publicDetails("SqNum", "id", param, condition);
        } catch (Exception e) {
            log.error("获取单据列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}
