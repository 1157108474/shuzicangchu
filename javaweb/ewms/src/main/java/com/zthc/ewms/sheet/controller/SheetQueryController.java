package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.DateUtils;
import com.zthc.ewms.base.util.ExcelExport;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.entity.apply.PlanDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.sheet.entity.query.*;
import com.zthc.ewms.sheet.service.*;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.service.DepartService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.user.service.UserService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/sheet/query")
public class SheetQueryController {

    private final static Log log = Log.getLog("com.system.query.controller.ApplyControllerGuard");
    @Resource(name = "vRkcxEntityService")
    public VRkcxEntityService vRkcxEntityService;
    @Resource(name = "vKcdetailEntityService")
    public VKcdetailEntityService vKcdetailEntityService;
    @Resource(name = "vKcsubdetailEntityService")
    public VKcsubdetailEntityService vKcsubdetailEntityService;
    @Resource(name = "vCkcxEntityService")
    public VCkcxEntityService vCkcxEntityService;
    @Resource(name = "userScopeService")
    public UserScopeService userScopeService;
    @Resource(name = "departService")
    public DepartService departService;
    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;
    @Resource(name = "kcszService")
    public KcszService kcszService;
    @Resource(name = "vJcstockEntityService")
    public VJcstockEntityService vJcstockEntityService;
    @Resource(name = "sheetCGService")
    public SheetCGService sheetCGService;
    @Resource(name = "dictionaryService")
    public DictionaryService dictionaryService;
    @Resource(name = "vCgjhEntityService")
    public VCgjhEntityService vCgjhEntityService;
    @Resource(name = "sheetApplyService")
    public SheetApplyService sheetApplyService;
    @Resource(name = "userService")
    public UserService userService;

    /**
     * 进入库存查询页面
     */
    @RequestMapping(value = "/queryInventory.htm")
    public String queryInventory(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<Depart> departList = userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        Map wareHouseList = new HashMap();
        List<Depart> listDepart = departService.listDepart(1);
        List<WareHouse> wareHouse = new ArrayList<WareHouse>();
        for (Depart depart : listDepart) {
            //wareHouse = wareHouseService.getStores(depart.getZtId());
            wareHouse = wareHouseService.getStores(depart.getId());
            for (WareHouse wh : wareHouse) {
                wareHouseList.put(wh.getId(), wh.getName() + "(" + depart.getName() + ")");
            }
        }
        model.addAttribute("departList", departList);
        model.addAttribute("wareHouseList", wareHouseList);
        return "sheet/query/queryInventory";
    }

    /**
     * 进入库存查询详细页面
     */
    @RequestMapping(value = "/queryKCDetails.htm")
    public String queryKCDetails(@ModelAttribute("VKcdetailEntity") VKcdetailEntity obj, VKcdetailEntityCondition condition,
                                 HttpServletRequest request, HttpServletResponse response, Model model) {
        VKcdetailEntity kc = vKcdetailEntityService.getOne(obj);
        model.addAttribute("materialcode",  kc.getMaterialcode());
        model.addAttribute("storeid", kc.getStoreid());
        model.addAttribute("storelocationcode", obj.getStorelocationcode());
        return "sheet/query/queryKCDetails";
    }

    /**
     * 进入物资入库查询页面
     */
    @RequestMapping(value = "/queryWZSheetRKDetail.htm")
    public String queryWZSheetRKDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<Depart> departList = userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        Map wareHouseList = new HashMap();
        List<Depart> listDepart = departService.listDepart(1);
        List<WareHouse> wareHouse = new ArrayList<WareHouse>();
        for (Depart depart : listDepart) {
            //wareHouse = wareHouseService.getStores(depart.getZtId());
            wareHouse = wareHouseService.getStores(depart.getId());
            for (WareHouse wh : wareHouse) {
                wareHouseList.put(wh.getId(), wh.getName() + "(" + depart.getName() + ")");
            }
        }
        model.addAttribute("departList", departList);
        model.addAttribute("wareHouseList", wareHouseList);
        return "sheet/query/queryWZSheetRKDetail";
    }

    /**
     * 进入物资出库查询页面
     */
    @RequestMapping(value = "/queryCKCX.htm")
    public String queryCKCX(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<Depart> departList = userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        Map wareHouseList = new HashMap();
        List<Depart> listDepart = departService.listDepart(1);
        List<WareHouse> wareHouse = new ArrayList<WareHouse>();
        for (Depart depart : listDepart) {
            wareHouse = wareHouseService.getStores(depart.getId());
            for (WareHouse wh : wareHouse) {
                wareHouseList.put(wh.getId(), wh.getName() + "(" + depart.getName() + ")");
            }
        }
        model.addAttribute("departList", departList);
        model.addAttribute("wareHouseList", wareHouseList);
        return "sheet/query/queryCKCX";
    }

    @RequestMapping(value = "/queryCGDD.htm")
    public String queryCGDD(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        // 库存组织列表
        List<Depart> departList = userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum
                .ORGANIZATION.getType());
        // 是否寄售
        List<Dictionary> jsType = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.JSType.getCode());

        model.addAttribute("departList", departList);
        model.addAttribute("jsType", jsType);
        return "sheet/query/queryCGDD";
    }

    @RequestMapping(value = "/queryCGJH.htm")
    public String queryCGJH(HttpServletRequest request, Model model) {
        return "sheet/query/queryCGJH";
    }

    /**
     * 进入库存收支查询页面
     */
    @RequestMapping(value = "/queryKCSZ.htm")
    public String queryKCSZ(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<WareHouse> wareHouseList = wareHouseService.getStores(userService.getUserOne(userId).getParent().getId());
        model.addAttribute("wareHouseList", wareHouseList);
        return "sheet/query/queryKCSZ";
    }

    /**
     * 进入寄存材料库存查询页面
     */
    @RequestMapping(value = "/queryJCCX.htm")
    public String queryJCCX(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<WareHouse> wareHouseList = wareHouseService.getStores(userService.getUserOne(userId).getParent().getId());
        model.addAttribute("wareHouseList", wareHouseList);
        return "sheet/query/queryJCCX";
    }

    /**
     * 获取库存查询列表
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listQueryInventory.json")
    @ResponseBody
    public LayuiPage<VKcdetailEntity> listQueryInventory(VKcdetailEntity obj, VKcdetailEntityCondition condition,
                                                         HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取库存查询列表方法");
        //TODO:需获取当前登录人部门ID查询
        LayuiPage<VKcdetailEntity> ret = null;
        try {
            String data = request.getParameter("appFlag");
            Integer userId  ;
            if (data != null && "1".equals(data)) {
                userId = Integer.parseInt(request.getParameter("userId"));
            }else {
                HttpSession session = request.getSession();
                Object requestUserId = session.getAttribute("userId");
                userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            }
            ret = vKcdetailEntityService.KCList(userId, obj, condition);
        } catch (Exception e) {
            log.error("获取库存查询列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    /**
     * 导出库存Excel
     */
    @RequestMapping(value = "/exportKCExcel.htm")
    public void exportKCExcel(VKcdetailEntity obj, VKcdetailEntityCondition condition, HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<VKcdetailEntity> list = vKcdetailEntityService.KCExcelList(userId, obj, condition);
        String[] gridTitles = {"库存组织", "物料编码", "物料描述", "库房", "是否寄售", "供应商", "库存数量"};
        String[] coloumsKey = {"ztidname", "materialcode", "description", "housename", "ownertypename", "providername", "storecount"};
        String userName = "库存表";
        HSSFWorkbook workBook = new ExcelExport().getExcelContent(userName,list, gridTitles, coloumsKey);

        //输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();

        String userAgent = request.getHeader("User-Agent");
        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userName = java.net.URLEncoder.encode(userName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            userName = new String(userName.getBytes("UTF-8"), "ISO-8859-1");
        }
        //添加导出时间
        String dt = DateUtils.dateTimeNow();
        userName += dt;
        // 设置response的Header
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", userName + ".xls"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        workBook.write(output);
        output.close();
    }
    /**
     * 获取物资入库查询列表
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listQueryWZSheetRKDetail.json")
    @ResponseBody
    public LayuiPage<VRkcxEntity> listQueryWZSheetRKDetail(@ModelAttribute("VRkcxEntity") VRkcxEntity obj, VRkcxEntityCondition condition,
                                                           HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("进入获取物资入库查询列表方法");
        //TODO:需获取当前登录人部门ID查询
        LayuiPage<VRkcxEntity> ret = null;
        try {
            HttpSession session = request.getSession();
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            ret = vRkcxEntityService.RKCXList(userId, obj, condition);
        } catch (Exception e) {
            log.error("获取物资入库查询列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取物资出库查询列表
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listQueryCKCX.json")
    @ResponseBody
    public LayuiPage<VCkcxEntity> listQueryCKCX(@ModelAttribute("VCkcxEntity") VCkcxEntity obj, VCkcxEntityCondition condition,
                                                HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("进入获取物资出库查询列表方法");
        //TODO:需获取当前登录人部门ID查询
        LayuiPage<VCkcxEntity> ret = null;
        try {
            HttpSession session = request.getSession();
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            ret = vCkcxEntityService.CKCXList(userId, obj, condition);
        } catch (Exception e) {
            log.error("获取物资出库查询列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * 获取物资库存明细列表
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listQueryKCDetails.json")
    @ResponseBody
    public LayuiPage<VKcsubdetailEntity> listQueryKCDetails(@ModelAttribute("VKcsubdetailEntity") VKcsubdetailEntity obj, VKcsubdetailEntityCondition condition,
                                                            HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("进入获取物资库存明细列表方法,");
        //TODO:需获取当前登录人部门ID查询
        LayuiPage<VKcsubdetailEntity> ret = null;
        try {
            ret = vKcsubdetailEntityService.KCDetailList(obj, condition);
        } catch (Exception e) {
            log.error("获取物资库存明细列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * App获取物资库存明细列表
     *
     * @param
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryKCDetailsList.json")
    @ResponseBody
    public LayuiPage<VKcsubdetailEntity> queryKCDetailsList(VKcsubdetailEntityCondition condition,
                                                            HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("进入APP获取物资库存明细列表方法");
        //TODO:需获取当前登录人部门ID查询
        LayuiPage<VKcsubdetailEntity> ret = null;
        try {
            String userId = request.getParameter("userId");//手机端参数userID
            String storelocationcode = request.getParameter("storelocationcode");
            String materialcode = request.getParameter("materialcode");
            ret = vKcsubdetailEntityService.KCDetailsList(userId, storelocationcode, materialcode, condition);
        } catch (Exception e) {
            log.error("获取APP物资库存明细列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取库存收支报表
     *
     * @param
     * @param
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listQueryKCSZ.json")
    @ResponseBody
    public LayuiPage<String> listQueryKCSZ(String materialcode, String materialname, String storeid, KcszCondition condition,
                                           HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("进入获取库存收支报表方法");
        //TODO:需获取当前登录人部门ID查询
        LayuiPage ret = new LayuiPage();
        try {
            List<Map<String, Object>> list = kcszService.KCSZList(materialcode, materialname, storeid, condition);
            ret.setData(list);
            ret.setCount((long) list.size());
        } catch (Exception e) {
            log.error("获取库存收支报表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取寄存材料库存查询列表
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listQueryJCCX.json")
    @ResponseBody
    public LayuiPage<VJcstockEntity> listQueryJCCX(@ModelAttribute("VJcstockEntity") VJcstockEntity obj, VJcstockEntityCondition condition,
                                                   HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("进入获取寄存材料库存查询列表方法");
        //TODO:需获取当前登录人部门ID查询
        LayuiPage<VJcstockEntity> ret = null;
        try {
            ret = vJcstockEntityService.JCCXList(obj, condition);
        } catch (Exception e) {
            log.error("获取寄存材料库存查询列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取采购订单查询列表
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listQueryCGDD.json")
    public LayuiPage<VCgddEntity> listQueryCGDD(@ModelAttribute("VCgddEntity") VCgddEntity obj, VCgddEntityCondition
            condition, String startTime, String endTime, HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取采购订单查询列表方法,当前方法名:listQueryCGDD");
        LayuiPage<VCgddEntity> ret = null;

        //获取登录人信息
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        try {
            ret = sheetCGService.CGDDList(obj, condition, userId, startTime, endTime);
        } catch (Exception e) {
            log.error("获取采购订单查询列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 导出采购订单Excel
     */
    @RequestMapping(value = "/exportCGDDExcel.htm")
    public void exportCGDDExcel(VCgddEntity obj, VCgddEntityCondition condition, String startTime, String endTime,
                                HttpServletRequest request, HttpServletResponse response) {
        //获取登录人信息
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<VCgddEntity> list = sheetCGService.listCGDD(obj, condition, userId, startTime, endTime);

        String[] gridTitles = {"采购订单编号", "发放号", "订单类型", "库存组织编码", "库存组织名称", "物料编码", "物料描述",
                "单位", "数量", "不含税单价", "供应商", "寄售物资", "制单日期", "更新日期", "状态"};
        String[] coloumsKey = {"ordernum", "ffcode", "ordertype", "stockorgcode", "stockorgname", "materialcode",
                "description", "detailunit", "detailcount", "notaxprice", "providerdepname", "consignmentname",
                "createdate", "updatedate", "status"};
        ExcelExport.doExcelExport("采购订单.xls", list, gridTitles, coloumsKey, request, response);
    }

    /**
     * 获取采购计划查询列表
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listQueryCGJH.json")
    @ResponseBody
    public LayuiPage<VCgjhEntity> listQueryCGJH(@ModelAttribute("VCgjhEntity") VCgjhEntity obj, VCgjhEntityCondition
            condition, String startTime1, String startTime2, String endTime1, String endTime2, HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取采购计划查询列表方法,当前方法名:listQueryCGJH");
        LayuiPage<VCgjhEntity> ret = null;
        //获取登录人信息
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        try {
            ret = vCgjhEntityService.CGJHList(obj, condition, userId, startTime1, startTime2, endTime1, endTime2);
        } catch (Exception e) {
            log.error("获取采购计划查询列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 导出采购计划Excel
     */
    @RequestMapping(value = "/exportCGJHExcel.htm")
    public void exportCGJHExcel(VCgjhEntity obj, VCgjhEntityCondition condition, String startTime1, String startTime2,
                                String endTime1, String endTime2, HttpServletRequest request,
                                HttpServletResponse response) throws ParseException, IOException {
        List<VCgjhEntity> list = vCgjhEntityService.excelCGJHList(obj, condition, startTime1, startTime2, endTime1, endTime2);
        String[] gridTitles = {"计划编号", "物料编码", "物料描述", "申报单位", "使用单位", "数量", "单位", "计划制定日期",
                "需求日期", "状态"};
        String[] coloumsKey = {"planCode", "materialCode", "materIaldes", "applyDepName", "useDepName", "count", "unIt",
                "createDate", "needDate", "status"};
        ExcelExport.doExcelExport("采购计划.xls", list, gridTitles, coloumsKey, request, response);
    }

    @ResponseBody
    @RequestMapping(value = "/getDetailunitname.htm", produces = "text/html;charset=gbk")
    public String getDetailunitname(@ModelAttribute("VKcsubdetailEntity") VKcsubdetailEntity obj, VKcsubdetailEntityCondition condition,
                                    HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        return vKcsubdetailEntityService.getDetailunitname(obj, condition);
    }



    /**
     * 导出物资入库Excel
     */
    @RequestMapping(value = "/exportRKExcel.htm")
    public void exportRKExcel(@ModelAttribute("VRkcxEntity") VRkcxEntity obj, VRkcxEntityCondition condition,
                              HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException, IOException {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<VRkcxEntity> list = vRkcxEntityService.RKExcelList(userId, obj, condition);
        String[] gridTitles = {"单据编号", "物料编码", "物料名称", "物料规格", "物料型号", "含税单价", "入库数量", "库房", "库位", "物料说明", "入库时间"};
        String[] coloumsKey = {"code", "materialcode", "materialname", "materialspecification",
                "materialmodel", "taxprice", "subdetailcount", "housename", "storelocationname", "description", "submittime"};
        String userName = "物资入库表";
        HSSFWorkbook workBook = new ExcelExport().getExcelContent(userName,list, gridTitles, coloumsKey);

        //输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();

        String userAgent = request.getHeader("User-Agent");
        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userName = java.net.URLEncoder.encode(userName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            userName = new String(userName.getBytes("UTF-8"), "ISO-8859-1");
        }
        //添加导出时间
        String dt = DateUtils.dateTimeNow();
        userName += dt;
        // 设置response的Header
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", userName + ".xls"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        workBook.write(output);
        output.close();
    }

    /**
     * 导出物资出库Excel
     */
    @RequestMapping(value = "/exportCKExcel.htm")
    public void exportCKExcel(@ModelAttribute("VCkcxEntity") VCkcxEntity obj, VCkcxEntityCondition condition,
                              HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException, IOException {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<VCkcxEntity> list = vCkcxEntityService.CKExcelList(userId, obj, condition);
        String[] gridTitles = {"单据编号", "物料编码", "物料名称", "物料规格", "物料型号", "申领部门", "申领数量", "物料说明"};
        String[] coloumsKey = {"code", "materialcode", "materialname", "materialspecification",
                "materialmodel", "usdedepname", "detailcount", "description"};

        String userName = "物资出库表";

        HSSFWorkbook workBook = new ExcelExport().getExcelContent(userName,list, gridTitles, coloumsKey);

        //输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        String userAgent = request.getHeader("User-Agent");
        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userName = java.net.URLEncoder.encode(userName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            userName = new String(userName.getBytes("UTF-8"), "ISO-8859-1");
        }
        //添加导出时间
        String dt = DateUtils.dateTimeNow();
        userName += dt;
        // 设置response的Header
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", userName + ".xls"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        workBook.write(output);
        output.close();
    }

    /**
     * 导出物资收支报表Excel
     */
    @RequestMapping(value = "/exportKCSZExcel.htm")
    public void exportKCSZExcel(String materialcode, String materialname, String storeid, KcszCondition condition,
                                HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException, IOException, SQLException {
        List<Map<String, Object>> list = kcszService.KCSZList(materialcode, materialname, storeid, condition);
        String[] gridTitles = {"物料编码", "物料名称", "期初库存数量", "期初库存金额", "本期入库数量", "本期入库金额"
                , "本期出库数量", "本期出库金额", "期末库存数量", "期末库存金额"};
        String[] coloumsKey = {"materialcode", "materialname", "startcount", "startmoney", "bqrcount", "bqrkmoney"
                , "bqckcount", "bqckmoney", "qmcount", "qmmoney"};
        String userName = "库存收支报表";

        HSSFWorkbook workBook = new ExcelExport().getExcelMapContent(userName,list, gridTitles, coloumsKey);

        //输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();

        String userAgent = request.getHeader("User-Agent");
        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userName = java.net.URLEncoder.encode(userName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            userName = new String(userName.getBytes("UTF-8"), "ISO-8859-1");
        }
        //添加导出时间
        String dt = DateUtils.dateTimeNow();
        userName += dt;
        // 设置response的Header
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", userName + ".xls"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        workBook.write(output);
        output.close();
    }

    /**
     * 进入计划库存管理报表
     */
    @RequestMapping(value = "/queryPlanDetail.htm")
    public String queryPlanDetail(PlanDetail obj, HttpServletRequest request, Model model) {


        return "sheet/query/queryPlanDetail";
    }

    /**
     * 计划库存管理列表
     */
    @RequestMapping(value = "/listPlanDetail.json")
    @ResponseBody
    public LayuiPage<PlanDetail> listPlanDetail(PlanDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<PlanDetail> ret = null;
        String queryCriteria = " and  storeuseCount>=0 ";
        //查询条件
        Map<String, Object> param = new HashMap<>();
        //单位Id
        if (!StringUtils.isEmpty(obj.getUseDepId())) {
            queryCriteria += " and useDepId = :useDepId";
            param.put("useDepId", obj.getUseDepId());
        }
        //计划编号
        if (!StringUtils.isEmpty(obj.getDeptName())) {
            queryCriteria += " and deptName like :deptName";
            param.put("deptName", "%" + obj.getDeptName().trim() + "%");
        }
        //ZTID
        if (!StringUtils.isEmpty(obj.getZtId())) {
            queryCriteria += " and ztId = :ztId";
            param.put("ztId", obj.getZtId());
        }
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
            log.error("获取计划库存管理数据列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * 导出计划库存Excel
     */
    @RequestMapping(value = "/exportPlanExcel.htm")
    public void exportPlanExcel(PlanDetail obj, SheetDetailCondition condition, HttpServletRequest request,
                                HttpServletResponse response) throws ParseException, IOException {
        String queryCriteria = " and  storeuseCount>=0 ";
        //查询条件
        Map<String, Object> param = new HashMap<>();
        //单位Id
        if (!StringUtils.isEmpty(obj.getUseDepId())) {
            queryCriteria += " and useDepId = :useDepId";
            param.put("useDepId", obj.getUseDepId());
        }
        //计划编号
        if (!StringUtils.isEmpty(obj.getDeptName())) {
            queryCriteria += " and deptName like :deptName";
            param.put("deptName", "%" + obj.getDeptName().trim() + "%");
        }
        //ZTID
        if (!StringUtils.isEmpty(obj.getZtId())) {
            queryCriteria += " and ztId = :ztId";
            param.put("ztId", obj.getZtId());
        }
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
        List<PlanDetail> list = this.sheetApplyService.listDetails("PlanDetail", param, condition);
        String[] gridTitles = {"计划部门", "计划编号", "使用单位", "物料编码", "物料描述", "计划数量", "已申领数量",
                "库存数量", "库存可用数量", "单位", "计划日期"};
        String[] coloumsKey = {"deptName", "planCode", "userDepName", "materialCode", "materialDes", "planCount",
                "haveslCount", "storeCount", "storeuseCount", "unIt", "planDate"};
        ExcelExport.doExcelExport("计划库存.xls", list, gridTitles, coloumsKey, request, response);
    }

}
