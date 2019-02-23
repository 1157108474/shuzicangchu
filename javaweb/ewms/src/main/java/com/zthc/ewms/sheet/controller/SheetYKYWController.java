package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.sheet.entity.ykyw.Ykyw;
import com.zthc.ewms.sheet.entity.ykyw.YkywDetail;
import com.zthc.ewms.sheet.entity.ykyw.YkywList;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.user.service.UserService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;
import net.sf.json.JSONException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sheet/ykyw")
public class SheetYKYWController extends SheetControllerGuard {


    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;

    @Resource(name = "userScopeService")
    public UserScopeService userScopeService;
    @Resource(name = "userService")
    public UserService userService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.sheet.controller.SheetYWYKController");
    }


    /**
     * 页面_新增
     */
    @RequestMapping("add")
    public String add(@ModelAttribute("sheet") Ykyw obj, SheetCondition condition,
                      HttpServletRequest request, HttpServletResponse response, Model model) {

        HttpSession session = request.getSession();
        String userName = "";
        String departName = "";
        Integer userId = 0;

        String userCode = "";
        if (session.getAttribute("userId") != null) {
            userCode = session.getAttribute("userCode").toString();
            userId = Integer.valueOf(session.getAttribute("userId").toString());
            userName = session.getAttribute("userName").toString();
            departName = session.getAttribute("departName").toString();
        }
        obj.setDepName(departName);
        obj.setPersonName(userName);
        //   obj.setCreateDate(new Date());
        obj.setCreateDateStr(new DateTime().toString("yyyy年MM月dd日 HH:mm:ss"));
        model.addAttribute("sheet", obj);
        String menuCode = request.getParameter("menuCode");
        model.addAttribute("menuCode", menuCode);

        List<Dictionary> dictionaries = activitiService.getPartButton(menuCode,userCode);//查第一环节按钮
        model.addAttribute("buttons", dictionaries);
        return "sheet/ykyw/ykyw";
    }


    /**
     * 页面_编辑
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {
        Ykyw sheet = this.service.getYkywSheetOne(id);
        int status = sheet.getStatus();

            sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy年MM月dd日 HH:mm:ss"));
            model.addAttribute("sheet", sheet);

//        String oper = request.getParameter("oper");
//        String taskId = null;
//        Task task = null;
//        if (StringUtils.isEmpty(oper)) {
//            taskId = request.getParameter("taskId");
//            task = taskService.createTaskQuery().taskId(taskId).singleResult();
//            if (task == null) {
//                request.setAttribute("taskId", taskId);
//                return "/system/activitiListen/showPeocessComplete";
//            }
//            List<Dictionary> dictionaries = activitiService.getPartButtonOnStar(taskId);
//            model.addAttribute("buttons", dictionaries);
//        } else if ("edit".equals(oper)) {
//            taskId = this.activitiService.getTaskByPi(sheet.getRouteId().intValue()+"");
//            List<Dictionary> dictionaries = activitiService.getPartButtonOnStar(taskId);
//            model.addAttribute("buttons", dictionaries);
//        } else if ("show".equals(oper)) {
//            //TODO
//            taskId =  this.activitiService.getTaskByPi(sheet.getRouteId().intValue()+"");
//
//        }
//
//        model.addAttribute("taskId", taskId);
//
//        return "sheet/ykyw/ykyw";

        return this.getUrl(request, sheet.getRouteId().intValue() + "", model, "sheet/ykyw/ykyw", status);


    }

    /**
     * 页面_已添加明细列表
     */
    @RequestMapping(value = "details", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<YkywDetail> getDetails(SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取已添加明细列表方法");
        LayuiPage<YkywDetail> ret = null;
        String sheetId = null;
        try {
            sheetId = request.getParameter("sheetId");
            ret = this.detailService.sheetDetails(sheetId, condition, "YkywDetail");
        } catch (Exception e) {
            log.error("获取已添加明细数据列表出错！sheetId：" + sheetId);
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }


    /**
     * 添加明细按钮事件，打开添加明细页面
     */
    @RequestMapping("addDetail")
    public String openAddDetail(@ModelAttribute("sheet") Sheet obj, SheetCondition condition,
                                HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Integer ztId = null;
        if (session.getAttribute("userId") != null) {
            ztId = Integer.parseInt(session.getAttribute("ztId").toString());
        }
        List<WareHouse> list = wareHouseService.getStores(ztId);
        model.addAttribute("stores", list);
        return "sheet/ykyw/addDetail";
    }

    @RequestMapping(value = "getAddDetails", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<YkywList> listDetails(SheetDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<YkywList> ret = null;
        try {
            Integer ztid = null;
            HttpSession session = request.getSession();
            if (session.getAttribute("ztId") != null) {
                ztid = Integer.parseInt(session.getAttribute("ztId").toString());
            }
            String data = request.getParameter("providerId");
            if (!StringUtils.isEmpty(data)) {
                obj.setProviderDepId(Integer.parseInt(data));
            }
            data = request.getParameter("storeId");
            if (!StringUtils.isEmpty(data)) {
                obj.setStoreId(Integer.parseInt(data));
            }
            data = request.getParameter("locationId");
            if (!StringUtils.isEmpty(data)) {
                obj.setStoreLocationId(Integer.parseInt(data));
            }
            ret = this.detailService.addDetailList(obj, condition, ztid, "YkywList");
        } catch (Exception e) {
            log.error("获取任务管理数据列表出错！");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }

//     /**
//      * 单据修改,该至 sheetController
//     */
//    @RequestMapping(method = RequestMethod.PUT)
//    @ResponseBody
//    public HttpResponse saveOrUpate(HttpServletRequest request, HttpServletResponse response, Model model) {
//
//        HttpResponse ret;
//        log.debug("修改提交YKYW");
//        try {
//
//
//            Integer userId = 0;
//            String userIp = "";
//            HttpSession session = request.getSession();
//            if (session.getAttribute("userId") != null) {
//                userId = Integer.parseInt(session.getAttribute("userId").toString());
//                userIp = session.getAttribute("userIp").toString();
//            }
//            Integer id = Integer.parseInt(request.getParameter("id"));
//            String memo = request.getParameter("memo");
//            this.service.updateYkywSheet(id, memo, userId);
//            logService.addSystemLog(0, SystemLogEnums.LogObject.MOVE_HOUSE.getCode(), SystemLogEnums.LogAction.EDIT.getCode(),
//                    "修改移库移位单:" + id.intValue(), userIp, userId);
//            ret = new HttpResponse(null);
//        } catch (Exception e) {
//            log.error("保存记录出错！");
//            log.errorPrintStacktrace(e);
//            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
//        }
//        return ret;
//    }

    /**
     * 页面_管理
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manageYKYW(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Integer userId = 0;
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        }
        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        model.addAttribute("departList", departList);

        List<Dictionary> statusList = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.ReceiptStatus.getCode());
        model.addAttribute("statusList", statusList);
        return "sheet/ykyw/manageYkyw";
    }

    /**
     * 管理页面单据列表
     */
    @RequestMapping(value = "sheets", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<Ykyw> getSheets(Sheet obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<Ykyw> ret = null;
        try {
            String data = request.getParameter("appFlag");
            String begin = null;
            String end = null;
            boolean appFlag = false;
            Integer userId = 0;
            if (data != null && "1".equals(data)) {
                appFlag = true;
                userId = Integer.parseInt(request.getParameter("userId"));
            } else {
                if (request.getSession().getAttribute("userId") != null) {
                    userId = (Integer) request.getSession().getAttribute("userId");
                }
                begin = request.getParameter("begin");
                end = request.getParameter("end");
            }
            ret = this.service.sheetList(obj, condition, "Ykyw", userId, begin, end, appFlag);
        } catch (Exception e) {
            log.error("获取单据列表出错！");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }


//    /**
//     * 执行方法_添加明细
//     */
//    @RequestMapping(value = "/addDetails", method = RequestMethod.POST)
//    @ResponseBody
//    public HttpResponse addDetails(HttpServletRequest request, HttpServletResponse
//            response, Model model) {
//        HttpResponse ret;
//        log.debug("添加明细");
//        String details = "";
//        try {
//            Integer userId = 0;
//            String userIp = "";
//            HttpSession session = request.getSession();
//            String data = request.getParameter("appFlag");
//            boolean appFlag = false;
//            if (data != null && "1".equals(data)) {
//                appFlag = true;
//                userId = Integer.parseInt(request.getParameter("userId"));
//                userIp = "app";
//            } else {
//                if (session.getAttribute("userId") != null) {
//                    userId = Integer.parseInt(session.getAttribute("userId").toString());
//                    userIp = session.getAttribute("userIp").toString();
//                }
//            }
//            details = request.getParameter("details");
//            log.debug("details" + details);
//            if (StringUtils.isEmpty(details)) {
//                return new HttpResponse(HttpResponse.Status.FAILURE, "明细数据为空", null);
//            }
//
//            this.detailService.saveYKYWSheetDetails(details, appFlag);
//            logService.addSystemLog(1, SystemLogEnums.LogObject.MOVE_HOUSE.getCode(), SystemLogEnums
//                            .LogAction.ADD_DETAIL.getCode(),
//                    "移库移位单据添加明细", userIp, userId);
//
//            ret = new HttpResponse(null);
//        } catch (JSONException e) {
//            log.error("保存记录出错！");
//            log.errorPrintStacktrace(e);
//
//            ret = new HttpResponse(HttpResponse.Status.FAILURE, "解析明细JSON出错", null);
//        } catch (Exception e) {
//            log.error("保存记录出错！");
//            log.errorPrintStacktrace(e);
//            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
//        }
//        return ret;
//    }
    /**
     * 执行方法_添加明细——app
     */
    @RequestMapping(value = "/addDetailForApp", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse addDetailForApp(HttpServletRequest request, HttpServletResponse
            response, Model model) {
        HttpResponse ret;
        log.debug("添加明细 from app");

        try {
            Integer  userId = Integer.parseInt(request.getParameter("userId"));
            User user =this.userService.getUserOne(userId);
            if(user == null){
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "用户不存在", null);
                return ret;
            }
            String   userIp = "app";
            Integer sheetId = Integer.parseInt(request.getParameter("sheetId"));
            Integer sheetDetailId = Integer.parseInt(request.getParameter("sheetDetailId"));
            String storeLocationCode = request.getParameter("storeLocationCode");
            Double detailCount = Double.parseDouble(request.getParameter("detailCount"));
            Integer storeId = Integer.parseInt(request.getParameter("storeId"));

            this.detailService.saveYKYWSheetDetailForApp(sheetId, sheetDetailId, storeLocationCode, detailCount,
                    user.getZtId(),storeId);
            logService.addSystemLog(1, SystemLogEnums.LogObject.MOVE_HOUSE.getCode(), SystemLogEnums
                            .LogAction.ADD_DETAIL.getCode(),
                    "移库移位单据添加明细app", userIp, userId);

            ret = new HttpResponse("成功");
        } catch (JSONException e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "解析明细JSON出错", "解析明细JSON出错");
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), e.getMessage());
        }
        return ret;
    }

    /**
     * 更新成本
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/renewalCost.json")
    @ResponseBody
    public HttpResponse renewalCost(Integer id, HttpServletRequest request) {
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        Ykyw ykyw = service.getYkywSheetOne(id);
        int userId = (null == userIdStr ? 0 : Integer.parseInt(userIdStr.toString()));
        try {
            service.asynRenewalCostYkyw(ykyw,userId);
            return new HttpResponse(HttpResponse.Status.SUCCESS, "更新移库移位成功！",null);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            return new HttpResponse(HttpResponse.Status.FAILURE, "更新移库移位失败！",null);
        }catch (Exception e) {
            log.error("更新失败！");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            return new HttpResponse(HttpResponse.Status.FAILURE, "更新移库移位成功！",null);
        }
    }
}


