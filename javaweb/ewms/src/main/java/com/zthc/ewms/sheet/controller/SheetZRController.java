package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.FileUploadResponse;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.rk.RkSubDetail;
import com.zthc.ewms.sheet.entity.th.ThStoreList;
import com.zthc.ewms.sheet.entity.zr.SheetZRD;
import com.zthc.ewms.sheet.entity.zr.ZrDetails;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.sheet.service.SheetRKDETAILService;
import com.zthc.ewms.sheet.service.SheetRKService;
import com.zthc.ewms.sheet.service.SheetService;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;
import org.activiti.engine.task.Task;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sheet/zr")
public class SheetZRController extends SheetControllerGuard {

    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;

    @Resource(name = "userScopeService")
    private UserScopeService userScopeService;

    @Resource(name = "sheetRKService")
    private SheetRKService sheetRKService;

    @Resource(name = "sheetService")
    private SheetService sheetService;

    @Resource(name = "sheetRKDETAILService")
    private SheetRKDETAILService sheetRKDETAILService;

    @Resource(name = "sheetDetailService")
    private SheetDetailService sheetDetailService;


    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.sheet.controller.SheetZrController");
    }


    /**
     * 页面_新增
     */
    @RequestMapping("add")
    public String add(HttpServletRequest request, Model model) {
        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestDepartName = session.getAttribute("departName");
        Object requestUserName = session.getAttribute("userName");
        Object requestDepartId = session.getAttribute("ztId");
        Object requestUserId = session.getAttribute("userId");
        Object requestuserCode = session.getAttribute("userCode");
        String userCode = (null == requestuserCode ? null : requestuserCode.toString());
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String departName = (null == requestDepartName ? null : requestDepartName.toString());
        String userName = (null == requestUserName ? null : requestUserName.toString());
        String ztId = (null == requestDepartId ? null : requestDepartId.toString());

        //获取菜单Code 用于工作流
        String menuCode = request.getParameter("menuCode");
        List<Dictionary> dictionaries = this.activitiService.getPartButton(menuCode,userCode);

//        //获取人员范围
//        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum
//                .ORGANIZATION.getType());

        model.addAttribute("ztId", ztId);
        model.addAttribute("loginUserId", userId);
        model.addAttribute("buttons", dictionaries);
        model.addAttribute("menuCode", menuCode);
        model.addAttribute("departName", departName);
        model.addAttribute("ztId", ztId);
        model.addAttribute("userName", userName);
        model.addAttribute("createDate", new DateTime().toString("yyyy年MM月dd日 HH:mm:ss"));
        return "sheet/zr/zr";
    }

    /**
     * 页面_编辑
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {

        //获取登录人信息
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        SheetZRD sheet = this.sheetService.getZrSheetOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreatedate()).toString("yyyy年MM月dd日 HH:mm:ss"));
        model.addAttribute("loginUserId", userId);
        model.addAttribute("sheet", sheet);
        return this.getUrl(request, sheet.getRouteid().intValue() + "", model, sheet, "sheet/zr/zr");

    }

    @RequestMapping(value = "/manageWzzr.htm")
    public String manageWzrk(HttpServletRequest request, Model model) {

        //获取登录人信息
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        //获取单据状态字典表
        List<Dictionary> dictionaries = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType
                .ReceiptStatus.getCode());
        model.addAttribute("orderStatus", dictionaries);

        //获取人员范围
        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum
                .ORGANIZATION.getType());

        model.addAttribute("orderStatus", dictionaries);
        model.addAttribute("userId", userId);
        model.addAttribute("departList", departList);

        return "sheet/zr/manageZr";
    }

    @RequestMapping(value = "/addDetails.htm")
    public String addDetails(String providerId, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));


        Integer ztId = null;
        if (session.getAttribute("userId") != null) {
            ztId = Integer.parseInt(session.getAttribute("ztId").toString());
        }

        //获取人员范围
        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum
                .ORGANIZATION.getType());

        model.addAttribute("departList", departList);
        List<WareHouse> list = wareHouseService.getStores(ztId);
        model.addAttribute("stores", list);

        model.addAttribute("providerId", providerId);

        return "sheet/zr/detailed";
    }

    /*-------------------------------------------------基础方法-------------------------------------------------*/

    /**
     * 获取物资入库单管理列表
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listManageZr.json")
    @ResponseBody
    public LayuiPage<SheetZRD> manageZrList(@ModelAttribute("SheetZRD") SheetZRD obj, SheetCondition condition,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        log.debug("进入获取物资入库单管理列表方法,当前方法名:manageRkList");
        LayuiPage<SheetZRD> ret = null;
        try {
            HttpSession session = request.getSession();
            String begin = request.getParameter("begin");
            String end = request.getParameter("end");
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            ret = this.sheetRKService.manageZrList(obj, condition, userId, begin, end);
        } catch (Exception e) {
            log.error("获取物资入库单管理列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取物资入库单页面明细列表
     *
     * @param zrDetails
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listZrDetails.json")
    @ResponseBody
    public LayuiPage<ZrDetails> rkDetailList(@ModelAttribute("ZrDetails") ZrDetails zrDetails, SheetRKCondition
            condition,
                                             HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取物资入库单页面明细列表方法,当前方法名:rkDetailList");
        LayuiPage<ZrDetails> ret = null;
        try {
            ret = this.sheetRKService.zrDetailList(zrDetails, condition);
        } catch (Exception e) {
            log.error("获取获取物资入库单页面明细列表方法");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    @RequestMapping(value = "getStockDetails", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<ThStoreList> getStockDetails(SheetDetail obj, SheetDetailCondition condition, HttpServletRequest
            request) {
        log.debug("进入获取库存列表方法");
        LayuiPage<ThStoreList> ret = null;
        try {
            Integer ztid = null;
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                ztid = Integer.parseInt(session.getAttribute("user").toString());
            }
            String search = request.getParameter("search");
            if (search == null || !search.equals("1")) {
                ret = new LayuiPage<>();
                ret.setCount(0L);
                ret.setData(null);
            } else {
                ret = this.detailService.addDetailList(obj, condition, ztid, "ThStoreList");
            }
        } catch (Exception e) {
            log.error("获取库存数据列表出错！");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }


    /**
     * 页面_管理
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manageMaterial(@ModelAttribute("material") Sheet obj, SheetCondition condition,
                                 HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Integer userId = 0;
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        }

        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum
                .ORGANIZATION.getType());
        model.addAttribute("departList", departList);

        List<Dictionary> statusList = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType
                .ReceiptStatus.getCode());
        model.addAttribute("statusList", statusList);
        return "sheet/zc/manageZc";
    }

    public String getUrl(HttpServletRequest request, String routeId, Model model, SheetZRD obj, String resUrl) {
        Task task = null;
        String taskId = null;
        String oper = request.getParameter("oper");
        List<Dictionary> dictionaries = null;
        if (StringUtils.isEmpty(oper)) {
            taskId = request.getParameter("taskId");
            task = taskService.createTaskQuery().taskId(taskId).singleResult();
            if (task == null) {
                request.setAttribute("taskId", taskId);
                return "/system/activitiListen/showProcessComplete";
            }
            dictionaries = activitiService.getPartButtonOnStar(taskId);
            model.addAttribute("buttons", dictionaries);
        } else if ("edit".equals(oper)) {
            taskId = this.activitiService.getTaskByPi(routeId);
            dictionaries = activitiService.getPartButtonOnStar(taskId);

            if (obj.getStatus() != 39) {
                request.setAttribute("taskId", obj.getRouteid());
                return "/system/activitiListen/showProcessCompleteEdit";
            }
            //获取审核人信息
            task = taskService.createTaskQuery().taskId(taskId).singleResult();
            String assignee = task.getAssignee();

            model.addAttribute("taskUserId", assignee);
            model.addAttribute("buttons", dictionaries);
        } else if ("show".equals(oper)) {
            taskId = this.activitiService.getTaskByPi(routeId);
            request.setAttribute("taskId", obj.getRouteid());
        }
        model.addAttribute("taskId", taskId);
        return resUrl;
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "delete-{id}")
    public HttpResponse deleteZrSheet(@PathVariable("id") Integer id, HttpServletRequest request) {
        log.debug("删除ZR:" + "提交，id = " + id);
        HttpResponse ret;
        Integer userId = 0;
        String userIp = "";
        String type = "ZR";
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                userId = Integer.parseInt(session.getAttribute("userId").toString());
                userIp = session.getAttribute("userIp").toString();
            }
            logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums.LogAction
                            .DELETE.getCode(),
                    "删除单据:id =" + id, userIp, userId);

            this.sheetRKService.deleteZrSheet(id);
            ret = new HttpResponse(id);
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), id);
        }
        return ret;
    }

    @RequestMapping(value = "/getOriginalLocation.htm")
    public String getOriginalLocation(HttpServletRequest request, Model model) {


        Integer rid = Integer.valueOf(request.getParameter("rid").toString());

        ZrDetails data = this.sheetService.getZrDetails(rid);
        SheetStock stock = this.sheetRKService.getOriginalLocation(data.getMaterialCode(), data.getStoreID());

        if (data.getSumCount() > 0) {
            data.setKfpsl(data.getDetailCount() - data.getSumCount());
        } else {
            data.setKfpsl(data.getDetailCount());
        }
        if (stock != null) {
            data.setYskw(stock.getStoreLocationCode());
            data.setFpkw(stock.getStoreLocationId().toString());
            model.addAttribute("StoreLocationCode", stock.getStoreLocationCode());
        }
        model.addAttribute("storeValue", data);


        return "sheet/rk/editDetail";
    }

    /**
     * 物资杂入获取已分配明细列表
     *
     * @param subDetail
     * @param condition
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSonDetail.json")
    @ResponseBody
    public LayuiPage<RkSubDetail> getSonDetail(@ModelAttribute("RkSubDetail") RkSubDetail subDetail, SheetRKCondition
            condition,
                                               HttpServletRequest request) {
        log.debug("进入物资入库获取已分配明细列表方法,当前方法名:getSonDetail");
        LayuiPage<RkSubDetail> ret = null;
        try {
            ret = this.sheetRKService.getSonDetail(subDetail, condition);
        } catch (Exception e) {
            log.error("获取物资入库获取已分配明细列表失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 物资杂入删除明细方法
     *
     * @param condition
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/deleteSheetZRDetails.json", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse deleteDetails(SheetDetailCondition condition, HttpServletRequest request, HttpServletResponse
            response)
            throws IOException {
        log.debug("进入物资杂入删除明细方法,当前方法名:deleteDetails");
        HttpResponse ret;
        try {
            //获取当前登录人信息
            HttpSession session = request.getSession();
            Object requestUserIp = session.getAttribute("userIp");
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            String userIp = (null == requestUserIp ? null : requestUserIp.toString());

            this.sheetDetailService.delZRDetails(condition);
            this.sheetRKDETAILService.deleteSonDetailByIds(condition.getIds());
            String str = logService.objectToJson(condition.getIds());
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_STORAGE.getCode(),
                    SystemLogEnums.LogAction.DELETE.getCode(), "删除物资入库明细:" + str, userIp, userId);
            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("删除物资接收明细失败");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除出错:" + e.getMessage(), null);
        }
        return ret;
    }
    /**
     * 导入库存结果
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/importKCResult.json")
    @ResponseBody
    public FileUploadResponse importKCResult(MultipartFile file, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        Integer userId = (null == userIdStr ? 0 : Integer.parseInt(userIdStr.toString()));
        Object departIdStr = session.getAttribute("departId");
        Integer departId = (null == departIdStr ? 0 : Integer.parseInt(departIdStr.toString()));
        Object ztIdStr = session.getAttribute("ztId");
        Integer ztId =  (null == ztIdStr ? 0 : Integer.parseInt(ztIdStr.toString()));
        try {
            String[] str = this.sheetDetailService.importKCResult(file, userId, departId,ztId);
            if(str[0].equals("1")){
                return new FileUploadResponse(FileUploadResponse.Code.SUCCESS.getCode(), "导入成功!", "");
            }else {
                return new FileUploadResponse(FileUploadResponse.Code.FAILURE.getCode(), "部分数据失败；"+str[1], "");
            }
        } catch (IOException e) {
            log.errorPrintStacktrace(e);
            return new FileUploadResponse(FileUploadResponse.Code.FAILURE.getCode(), "文件解析失败!", "");
        } catch (Exception e) {
            log.errorPrintStacktrace(e);
            return new FileUploadResponse(FileUploadResponse.Code.FAILURE.getCode(), "保存失败，其他错误", "");
        }
    }
}

	
