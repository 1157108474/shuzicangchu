package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.sheet.entity.th.*;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.print.entity.guard.PrintEnums;
import com.zthc.ewms.system.print.service.PrintService;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import drk.system.Log;
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
@RequestMapping("/sheet/th")
public class SheetTHController extends SheetControllerGuard {

    @Resource(name = "printService")
    public PrintService printService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.sheet.controller.SheetTHController");
    }


    /**
     * 页面_新增
     */
    @RequestMapping("add")
    public String add(@ModelAttribute("sheet") Th obj, SheetCondition condition,
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
        /*Integer[] ids = {184, 185, 182, 183};
        List<Print> prints = this.printService.getPrints(ids);
        model.addAttribute("printTypes", prints);*/
        model.addAttribute("printTypes", PrintEnums.WZTHEnum.getMap());
        String menuCode = request.getParameter("menuCode");
        model.addAttribute("menuCode", menuCode);

        List<Dictionary> dictionaries = activitiService.getPartButton(menuCode,userCode);//查第一环节按钮
        model.addAttribute("buttons", dictionaries);
        return "sheet/th/th";
    }


    /**
     * 页面_编辑
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {
        Th sheet = this.service.getThSheetOne(id);

        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy年MM月dd日 HH:mm:ss"));
        model.addAttribute("sheet", sheet);
        /*Integer[] ids = {184, 185, 182, 183};
        /*List<Print> prints = this.printService.getPrints(ids);
        model.addAttribute("printTypes", prints);*/

        model.addAttribute("printTypes", PrintEnums.WZTHEnum.getMap());


        return this.getUrl(request, sheet.getRouteId().intValue() + "", model, "sheet/th/th", sheet.getStatus());

    }

    /**
     * 页面_明细
     */
    @RequestMapping(value = "details", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<ThSumDetail> getDetails(SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取已添加明细列表方法");
        LayuiPage<ThSumDetail> ret = null;
        String sheetId = null;
        try {
            sheetId = request.getParameter("sheetId");
            ret = this.detailService.sheetDetails(sheetId, condition, "ThSumDetail");
        } catch (Exception e) {
            log.error("获取已添加明细数据列表出错！sheetId：" + sheetId);
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }


    /**
     * 页面_明细—詳情
     */
    @RequestMapping(value = "subdetails", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<ThDetail> getSubDetails(SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取已添加明细列表方法");
        LayuiPage<ThDetail> ret = null;
        String sheetId = null;
        try {
            sheetId = request.getParameter("sheetId");


//            String data = request.getParameter("storeId");
//            if (data == null) {
            ret = this.detailService.sheetDetails(sheetId, condition, "ThDetail");
//            } else {
//                String materialCode = request.getParameter("materialCode");
//                Integer storeId = Integer.parseInt(data);
//                ret = this.detailService.sheetSubDetails(sheetId, materialCode, storeId, condition, "ThDetail");
//
//            }
        } catch (Exception e) {
            log.error("获取已添加明细数据列表出错！sheetId：" + sheetId);
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }


    /**
     * 页面_添加明细
     */
    @RequestMapping("openAddDetail")
    public String addDetail(@ModelAttribute("sheet") Sheet obj, SheetCondition condition,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
        return "sheet/th/addDetail";
    }

    @RequestMapping(value = "addDetails", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<ThList> listDetails(SheetDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<ThList> ret = null;
        try {
            Integer ztid = null;
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                ztid = Integer.parseInt(session.getAttribute("user").toString());
            }
            String data = request.getParameter("sheetId");
//            if(!StringUtils.isEmpty(data)){
//                obj.setSheetId(Integer.parseInt(data));
//            }
            ret = this.detailService.addDetailList(obj, condition, ztid, "ThList");
        } catch (Exception e) {
            log.error("获取任务管理数据列表出错！");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }


    @RequestMapping(value = "getStockDetails", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<ThStoreList> getStockDetails(SheetDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
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

    @Resource(name = "userScopeService")
    public UserScopeService userScopeService;

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

        return "sheet/th/manageTh";
    }

    /**
     * 管理页面单据列表
     */
    @RequestMapping(value = "sheets", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<Th> getDetails(Sheet obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<Th> ret = null;
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
            ret = this.service.sheetList(obj, condition, "Th", userId, begin, end, appFlag);
        } catch (Exception e) {
            log.error("获取单据列表出错！");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }

}


