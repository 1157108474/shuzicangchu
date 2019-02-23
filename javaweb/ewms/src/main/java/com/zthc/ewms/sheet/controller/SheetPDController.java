package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.FileUploadResponse;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.ExcelExport;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.sheet.entity.pd.KcpdjsDetail;
import com.zthc.ewms.sheet.entity.pd.KcpdxhDetail;
import com.zthc.ewms.sheet.entity.pd.WzpcManage;
import com.zthc.ewms.sheet.entity.pd.WzpdDetail;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.sheet.service.SheetPDService;
import com.zthc.ewms.sheet.service.SheetService;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplateCondition;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.service.UserService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/sheet/pd")
public class SheetPDController extends SheetControllerGuard {

    @Autowired
    private TaskService taskService;

    @Resource(name = "sheetDetailService")
    public SheetDetailService detailService;

    @Resource(name = "activitiService")
    public ActivitiService activitiService;

    @Resource(name = "logService")
    public LogService logService;

    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;

    @Resource
    public DictionaryService dictionaryService;
    @Resource
    private SheetService sheetService;
    @Resource
    private SheetPDService sheetPDService;
    @Resource
    private UserService userService;

    private final static Log log = Log.getLog("com.zthc.ewms.sheet.controller.SheetPDController");


    /**
     * 页面_新增
     */
    @RequestMapping(value = "/add.htm")
    public String add(@ModelAttribute("sheet") Sheet obj, SheetCondition condition, HttpServletRequest request, HttpServletResponse response) {

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
        List<WareHouse> wareHouses = wareHouseService.getStores((Integer) session.getAttribute("departId"));
        List<Dictionary> pdTypes = dictionaryService.getDicListByParentId(DictionaryEnums.DicType.PDTYPE.getCode());
        request.setAttribute("pdTypes", pdTypes);
        request.setAttribute("wareHouses", wareHouses);
        request.setAttribute("depart", departName);
        request.setAttribute("creator", userName);
        request.setAttribute("createDate", new DateTime().toString("yyyy/MM/dd HH:mm:ss"));
        request.setAttribute("edit", true);

        String menuCode = request.getParameter("menuCode");
        //查第一环节按钮
        List<Dictionary> dictionaries = activitiService.getPartButton(menuCode,userCode);
        request.setAttribute("menuCode", menuCode);
        request.setAttribute("buttons", dictionaries);
        return "sheet/pd/sheetPd";
    }
    /**
     * 页面_编辑
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        WzpcManage sheet = sheetService.getWzpcSheetOne(id);
        //sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy/MM/dd HH:mm:ss"));
        model.addAttribute("sheet", sheet);
        String oper = request.getParameter("oper");
        String taskId = null;
        Task task;
        if (StringUtils.isEmpty(oper)) {
            List<WareHouse> wareHouses = wareHouseService.getStores((Integer) session.getAttribute("departId"));
            List<Dictionary> pdTypes = dictionaryService.getDicListByParentId(DictionaryEnums.DicType.PDTYPE.getCode());
            taskId = request.getParameter("taskId");
            task = taskService.createTaskQuery().taskId(taskId).singleResult();
            if (task == null) {
                request.setAttribute("taskId", taskId);
                return "/system/activitiListen/showProcessComplete";
            }
            List<Dictionary> dictionaries = activitiService.getPartButtonOnStar(taskId);
            model.addAttribute("buttons", dictionaries);
            request.setAttribute("wareHouses", wareHouses);
            request.setAttribute("pdTypes", pdTypes);
        } else if ("edit".equals(oper)) {
            if (sheet.getStatus() != 39) {
                request.setAttribute("taskId", sheet.getRouteId());
                return "/system/activitiListen/showProcessCompleteEdit";
            } else {
                List<WareHouse> wareHouses = wareHouseService.getStores((Integer) session.getAttribute("departId"));
                List<Dictionary> pdTypes = dictionaryService.getDicListByParentId(DictionaryEnums.DicType.PDTYPE.getCode());
                taskId = activitiService.getTaskByPi(sheet.getRouteId().intValue() + "");
                List<Dictionary> dictionaries = activitiService.getPartButtonOnStar(taskId);
                model.addAttribute("buttons", dictionaries);
                request.setAttribute("wareHouses", wareHouses);
                request.setAttribute("pdTypes", pdTypes);
            }
        } else if ("show".equals(oper)) {
            taskId = this.activitiService.getTaskByPi(sheet.getRouteId().intValue() + "");
            List<WareHouse> wareHouses = wareHouseService.getStores((Integer) session.getAttribute("departId"));
            List<Dictionary> pdTypes = dictionaryService.getDicListByParentId(DictionaryEnums.DicType.PDTYPE.getCode());
            request.setAttribute("wareHouses", wareHouses);
            request.setAttribute("pdTypes", pdTypes);
        }
        model.addAttribute("taskId", taskId);
        return "sheet/pd/sheetPdAct";
    }

    @RequestMapping(value = "/wzpdDetailApp.json")
    @ResponseBody
    public void wzpdDetailApp(SheetDetailCondition condition, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String sheetId = request.getParameter("sheetId");
        PrintWriter out = response.getWriter();
        JSONObject s = new JSONObject();
        try {
            LayuiPage<WzpdDetail> ret = this.detailService.sheetDetails(sheetId, condition, "WzpdDetail");
            WzpcManage wzpcManage = sheetService.getWzpcSheetOne(Integer.parseInt(sheetId));
            Object obj = JSONArray.fromObject(ret.getData().toArray());
            s.put("code", 0);
            s.put("count", ret.getCount());
            s.put("detailCount", wzpcManage.getSumDetailCount());
            s.put("ypCount", wzpcManage.getExtendInt3());
            s.put("data", obj);
        } catch (NumberFormatException e) {
            s.put("code", 1);
            e.printStackTrace();
        }
        out.println(s);
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/wzpdDetail.json")
    @ResponseBody
    public LayuiPage<WzpdDetail> wzpdDetail(SheetDetailCondition condition, HttpServletRequest request, HttpServletResponse response) {
        String sheetId = request.getParameter("sheetId");
        LayuiPage<WzpdDetail> ret = new LayuiPage<>();
        if (sheetId == null || "".equals(sheetId)) {
            ret.setCount((long) 0);
            ret.setData(null);
            return ret;
        } else {
            String page = request.getParameter("page");
            String limit = request.getParameter("limit");
            if (page == null && limit == null && "".equals(page) && "".equals(limit)) {
                condition.setPageNum(Integer.parseInt(page));
                condition.setPageTotal(Integer.parseInt(limit));
            }
            ret = this.detailService.sheetDetails(sheetId, condition, "WzpdDetail");
            return ret;
        }
    }

    @RequestMapping(value = "/getxhpdStock.htm")
    public String getxhpdStock(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String extendInt1 = new String(request.getParameter("extendInt1").getBytes("iso8859-1"), "utf-8");
        request.setAttribute("storeID", extendInt1.split("-")[0]);
        request.setAttribute("storeName", extendInt1.split("-")[1]);
        request.setAttribute("extendInt2", request.getParameter("extendInt2"));
        return "sheet/pd/getxhpdStock";
    }

    @RequestMapping(value = "/getjspdStock.htm")
    public String getjspdStock(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String extendInt1 = new String(request.getParameter("extendInt1").getBytes("iso8859-1"), "utf-8");
        request.setAttribute("storeID", extendInt1.split("-")[0]);
        request.setAttribute("storeName", extendInt1.split("-")[1]);
        request.setAttribute("extendInt2", request.getParameter("extendInt2"));
        return "sheet/pd/getjspdStock";
    }

    @RequestMapping(value = "/getjspdStockDetail.json")
    @ResponseBody
    public LayuiPage<KcpdjsDetail> getjspdStockDetail(FormTemplateCondition condition, HttpServletRequest request, HttpServletResponse response) {
        String materialCode = request.getParameter("materialCode");
        String description = request.getParameter("description");
        String storeID = request.getParameter("storeID");
        String providerdepid = request.getParameter("providerDepId");
        Map<String, Object> params = new HashMap<>();
        if (materialCode != null && !"".equals(materialCode)) {
            params.put("materialCode", materialCode);
        }
        if (description != null && !"".equals(description)) {
            params.put("description", description);
        }
        if (storeID != null && !"".equals(storeID)) {
            params.put("storeID", storeID);
        }
        if (providerdepid != null && !"".equals(providerdepid)) {
            params.put("providerdepid", providerdepid);
        }
        LayuiPage<KcpdjsDetail> ret = sheetPDService.getjspdStockDetailList(condition, params);
        return ret;
    }

    @RequestMapping(value = "/getxhpdStockDetail.json")
    @ResponseBody
    public LayuiPage<KcpdxhDetail> getxhpdStockDetail(FormTemplateCondition condition, HttpServletRequest request, HttpServletResponse response) {
        String materialCode = request.getParameter("materialCode");
        String description = request.getParameter("description");
        String storeID = request.getParameter("storeID");
        String providerdepid = request.getParameter("providerDepId");
        String code1 = request.getParameter("wlfl1");
        String code2 = request.getParameter("wlfl2");
        String storeLocationName1 = request.getParameter("storeLocationID1");
        String storeLocationName2 = request.getParameter("storeLocationID2");
        Map<String, Object> params = new HashMap<>();
        if (materialCode != null && !"".equals(materialCode)) {
            params.put("materialCode", materialCode);
        }
        if (description != null && !"".equals(description)) {
            params.put("description", description);
        }
        if (storeID != null && !"".equals(storeID)) {
            params.put("storeID", storeID);
        }
        if (providerdepid != null && !"".equals(providerdepid)) {
            params.put("providerdepid", providerdepid);
        }
        if (code1 != null && !"".equals(code1)) {
            params.put("code1", code1);
        }
        if (code2 != null && !"".equals(code2)) {
            params.put("code2", code2);
        }
        if (storeLocationName1 != null && !"".equals(storeLocationName1)) {
            params.put("storeLocationName1", storeLocationName1);
        }
        if (storeLocationName2 != null && !"".equals(storeLocationName2)) {
            params.put("storeLocationName2", storeLocationName2);
        }
        LayuiPage<KcpdxhDetail> ret = sheetPDService.getxhpdStockDetailList(condition, params);
        return ret;
    }

    @RequestMapping(value = "/addKCPDDetails.json")
    @ResponseBody
    public HttpResponse addKCPDDetails(HttpServletRequest request, HttpServletResponse response) {
        HttpResponse ret;
        log.debug("添加盘点明细");
        try {
            Integer userId = 0;
            String userIp = "";
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                userId = Integer.parseInt(session.getAttribute("userId").toString());
                userIp = session.getAttribute("userIp").toString();
            }
            JSONArray detailJson = JSONArray.fromObject(request.getParameter("details"));
            Collection collection = JSONArray.toCollection(detailJson);

            if (collection != null && !collection.isEmpty()) {
                Iterator it = collection.iterator();
                KcpdxhDetail kcpdxhDetail;
                List<WzpdDetail> detailList = new ArrayList<>();
                while (it.hasNext()) {
                    WzpdDetail wzpdDetail = new WzpdDetail();
                    JSONObject jsonObj = JSONObject.fromObject(it.next());
                    kcpdxhDetail = (KcpdxhDetail) JSONObject.toBean(jsonObj, KcpdxhDetail.class);
                    wzpdDetail.setGuid(java.util.UUID.randomUUID().toString());
                    wzpdDetail.setTagCode(kcpdxhDetail.getMaterialcode());
                    wzpdDetail.setSheetId(kcpdxhDetail.getSheetId());
                    wzpdDetail.setMaterialCode(kcpdxhDetail.getMaterialcode());
                    wzpdDetail.setDescription(kcpdxhDetail.getDescription());
                    wzpdDetail.setSysCount(kcpdxhDetail.getStorecount());
                    //wzpdDetail.setDetailCount((double)0);
                    wzpdDetail.setStoreId(kcpdxhDetail.getStoreid());
                    wzpdDetail.setStoreLocationId(kcpdxhDetail.getStorelocationid());
                    wzpdDetail.setStoreLocationCode(kcpdxhDetail.getStorelocationcode());
                    wzpdDetail.setStoreLocationName(kcpdxhDetail.getStorelocationname());
                    wzpdDetail.setStockResult(0);
                    //wzpdDetail.setStockStatus(0);
                    wzpdDetail.setCreateDate(new Date());
                    wzpdDetail.setCreator(userId);
                    wzpdDetail.setZtId(kcpdxhDetail.getZtid());
                    wzpdDetail.setDetailUnitName(kcpdxhDetail.getDetailunitname());
                    wzpdDetail.setEnableSN(kcpdxhDetail.getEnablesn());
                    wzpdDetail.setProviderDepId(kcpdxhDetail.getProviderdepid());
                    detailList.add(wzpdDetail);
                }
                sheetPDService.saveSheetDetails(detailList);
                logService.addSystemLog(1, SystemLogEnums.LogObject.getByType("KCPD").getCode(),
                        SystemLogEnums.LogAction.ADD_DETAIL.getCode(),"KCPD单据:" + detailList.get(0).getSheetId(),
                        userIp, userId);
            }

            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }

    @RequestMapping(value = "/KCPDSheetEdit.json")
    @ResponseBody
    public HttpResponse KCPDSheetEdit(HttpServletRequest request, HttpServletResponse response) {
        HttpResponse ret;
        log.debug("修改提交盘点单据");
        Integer userId = 0;
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        }
        String id = request.getParameter("id");
        String extendInt1 = request.getParameter("extendInt1");
        String extendString1 = request.getParameter("extendString1");
        String extendInt2 = request.getParameter("extendInt2");
        String memo = request.getParameter("memo");
        int i = sheetService.editPDSheet(Integer.parseInt(id), Integer.parseInt(extendInt1), extendString1, Integer.parseInt(extendInt2), memo, userId);
        if (i == 1) {
            ret = new HttpResponse(HttpResponse.Status.SUCCESS);
        } else {
            ret = new HttpResponse(HttpResponse.Status.FAILURE);
        }
        return ret;
    }

    @RequestMapping(value = "/deletePDDetail.json")
    @ResponseBody
    public HttpResponse deletePDDetail(HttpServletRequest request, HttpServletResponse response) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE);
        log.debug("删除盘点单据明细");
        JSONArray detailJson = JSONArray.fromObject(request.getParameter("ids"));
        Integer[] idArray = new Integer[detailJson.size()];
        for (int i = 0; i < detailJson.size(); i++) {
            idArray[i] = detailJson.getInt(i);
        }
        try {
            sheetPDService.deletePDDetail(idArray);
            ret = new HttpResponse(HttpResponse.Status.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @RequestMapping(value = "/manageKcpd.htm")
    public String manageKcpd(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<WareHouse> wareHouses = wareHouseService.getStores((Integer) session.getAttribute("departId"));
        request.setAttribute("wareHouses", wareHouses);
        return "sheet/pd/manageKcpd";
    }

    @RequestMapping(value = "/manageKcpd.json")
    @ResponseBody
    public LayuiPage<WzpcManage> manageKcpd(FormTemplateCondition condition, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String extendInt1 = request.getParameter("extendInt1");
        String creator = request.getParameter("workManId");
        String code = request.getParameter("code");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        Object ztid = session.getAttribute("ztId");
        String ztId = null;
        if (ztid != null) {
            ztId = ztid.toString();
        }

        //手机端参数userID
        String userId = request.getParameter("userId");
        Map<String, Object> params = new HashMap<>();
        if (extendInt1 != null && !"".equals(extendInt1)) {
            params.put("extendInt1", Integer.parseInt(extendInt1));
        }
        if (code != null && !"".equals(code)) {
            params.put("code", code);
        }
        if (beginDate != null && !"".equals(beginDate)) {
            params.put("beginDate", beginDate);
        }
        if (endDate != null && !"".equals(endDate)) {
            params.put("endDate", endDate);
        }
        //不是手机端
        if (userId == null) {
            if (creator != null && !"".equals(creator)) {
                params.put("creator", Integer.parseInt(creator));
            }
            if (ztId != null && !"".equals(ztId)) {
                params.put("ztId", Integer.parseInt(ztId));
            }
        } else {
            //手机端
            params.put("status", 39);
            params.put("creator", Integer.parseInt(userId));
            params.put("ztId", userService.getUserOne(Integer.parseInt(userId)).getZtId());
            String page = request.getParameter("page");
            String limit = request.getParameter("limit");
            if (page == null && limit == null &&"".equals(page) &&  "".equals(limit) ) {
                condition.setPageNum(Integer.parseInt(page));
                condition.setPageTotal(Integer.parseInt(limit));
            }
        }
        LayuiPage<WzpcManage> wzpcManageLayuiPage = sheetPDService.manageKcpd(condition, params);
        return wzpcManageLayuiPage;
    }

    /**
     * 盘点单Excel
     *
     * @param request
     * @param ztid
     * @return
     */
    public HSSFWorkbook exportManageKcpd(HttpServletRequest request, Object ztid) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("盘点管理表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        sheet.setColumnWidth(0, 10000);
        sheet.setColumnWidth(1, 7000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 7000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 4500);
        sheet.setColumnWidth(6, 4500);

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("单据编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("盘点名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("库存组织");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("单据状态");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("制单人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("提交时间");
        cell.setCellStyle(style);

        String extendInt1 = request.getParameter("extendInt1");
        String creator = request.getParameter("workManId");
        String code = request.getParameter("code");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");

        String ztId = null;
        if (ztid != null) {
            ztId = ztid.toString();
        }
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotEmpty(extendInt1)) {
            params.put("extendInt1", Integer.parseInt(extendInt1));
        }
        if (StringUtils.isNotEmpty(code)) {
            params.put("code", code);
        }
        if (StringUtils.isNotEmpty(beginDate)) {
            params.put("beginDate", beginDate);
        }
        if (StringUtils.isNotEmpty(endDate)) {
            params.put("endDate", endDate);
        }
        if (StringUtils.isNotEmpty(creator)) {
            params.put("creator", Integer.parseInt(creator));
        }
        if (StringUtils.isNotEmpty(ztId)) {
            params.put("ztId", Integer.parseInt(ztId));
        }
        List<WzpcManage> list = sheetPDService.manageKcpdExport(params);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        int size = list.size();
        for (int i = 0; i < size; i++) {
            row = sheet.createRow(i + 1);
            WzpcManage wzpcManage = list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(wzpcManage.getCode());
            row.createCell((short) 1).setCellValue(wzpcManage.getName());
            row.createCell((short) 2).setCellValue(wzpcManage.getHouseName());
            row.createCell((short) 3).setCellValue(wzpcManage.getStatusName());
            row.createCell((short) 4).setCellValue(wzpcManage.getPersonName());
            row.createCell((short) 5).setCellValue(wzpcManage.getCreateDate());
           /* cell = row.createCell((short) 5);
            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(wzpcManage.getCreateDate()));*/
        }

        return wb;

    }

    /**
     * 导出盘点单管理
     *
     * @param request
     * @param response
     * @throws IOException
     */
    /*@ResponseBody*/
    @RequestMapping(value = "/expManageKcpd.json")
    public void expManageKcpd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("进入导出盘点单expManageKcpd方法");
        HttpSession session = request.getSession();
        Object ztid = session.getAttribute("ztId");
        try {
            String encodedFileName = ExcelExport.encodeFileNameForDownload(request, "库存盘点单.xls");
            HSSFWorkbook book = exportManageKcpd(request, ztid);
            String userIp = (null == session.getAttribute("userIp") ?
                    null : session.getAttribute("userIp").toString());
            int userIdLog = (null == session.getAttribute("userId") ?
                    0 : Integer.parseInt(session.getAttribute("userId").toString()));
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.ACTIVITI_OPERATION.getCode(), SystemLogEnums.LogAction.EXPORT.getCode(),
                    "导出库存盘点单:", userIp, userIdLog);

            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);
            book.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码类型:" + e.getMessage());
        } catch (IOException e) {
            log.error("文件导出错误" + e.getMessage());
        }
    }


    @RequestMapping(value = "/editDetailCount.htm")
    public String editDetailCount(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String sysCount = request.getParameter("sysCount");
        request.setAttribute("id", id);
        request.setAttribute("sysCount", sysCount);
        return "sheet/pd/editDetailCount";
    }

    @ResponseBody
    @RequestMapping(value = "/editDetail.json")
    public HttpResponse editDetail(HttpServletRequest request, HttpServletResponse response) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "修改失败", null);
        String id = request.getParameter("id");
        String sysCount = request.getParameter("sysCount");
        String detailCount = request.getParameter("detailCount").trim();
        int idint = Integer.parseInt(id);
        double detailCountint = Double.parseDouble(detailCount);
        double sysCountint = Double.parseDouble(sysCount);
        int stockResult = 0;
        if (detailCountint > sysCountint) {
            stockResult = 1;
        } else if (detailCountint < sysCountint) {
            stockResult = -1;
        }
        HttpSession session = request.getSession();
        String userId ;
        String userName ;
        if (null != session.getAttribute("userId")) {
            userId = session.getAttribute("userId").toString();
            userName = session.getAttribute("userName").toString();
        } else {
            userId = request.getParameter("userId");
            userName = userService.getUserOne(Integer.parseInt(userId)).getName();
        }
        int data = sheetPDService.editPdDetail(idint, detailCountint, Integer.parseInt(userId), userName, stockResult);
        if (data > 0) {
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "修改成功", null);
        }
        return ret;
    }

    @RequestMapping(value = "/addPY.htm")
    public String addPY(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        WzpdDetail wzpdDetail = sheetPDService.getDetail(Integer.parseInt(id));
        HttpSession session = request.getSession();
        int ztId = 0;
        if (session.getAttribute("userId") != null) {
            ztId = Integer.parseInt(session.getAttribute("ztId").toString());
        }
        List<WareHouse> list = wareHouseService.getStores(ztId);
        request.setAttribute("stores", list);
        request.setAttribute("wzpdDetail", wzpdDetail);
        return "sheet/pd/addPY";
    }

    @ResponseBody
    @RequestMapping(value = "/addPYdata.json")
    public HttpResponse addPYdata(HttpServletRequest request, HttpServletResponse response) {
        HttpResponse ret ;
        String sheetId = request.getParameter("sheetId");
        String ztId = request.getParameter("ztId");
        String tagCode = request.getParameter("tagCode");
        String detailUnitName = request.getParameter("detailUnitName");
        String description = request.getParameter("description");
        //上架数量
        String sysCount = request.getParameter("sysCount");
        //供应商id
        String providerId = request.getParameter("providerId");
        String storeId = request.getParameter("storeId");
        String storeLocationId = request.getParameter("storeLocationId");
        String storeLocationCode = request.getParameter("storeLocationCode");
        String storeLocationName = request.getParameter("storeLocationName");
        String enableSN = request.getParameter("enableSN");
        String snCode = request.getParameter("snCode");


        int userId = 0;
        String userName = "";
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
            userName = session.getAttribute("userName").toString();
        } else {
            userId = Integer.parseInt(request.getParameter("userId"));
            User user = userService.getUserOne(userId);
            userName = user.getName();
            ztId = user.getZtId().toString();
        }
        Date now = new Date();
        try {
            WzpdDetail wzpdDetail = new WzpdDetail();
            wzpdDetail.setSheetId(Integer.parseInt(sheetId));
            wzpdDetail.setTagCode(tagCode);
            wzpdDetail.setMaterialCode(tagCode);
            if (ztId != null && !"".equals(ztId)) {
                wzpdDetail.setZtId(Integer.parseInt(ztId));
            }
            wzpdDetail.setDetailUnitName(detailUnitName);
            wzpdDetail.setDescription(description);
            wzpdDetail.setSysCount(Double.parseDouble(sysCount));
            if (providerId != null && !"".equals(providerId)) {
                wzpdDetail.setProviderDepId(Integer.parseInt(providerId));
            }
            if (storeId != null && !"".equals(storeId)) {
                wzpdDetail.setStoreId(Integer.parseInt(storeId));
            }
            if (storeLocationId != null && !"".equals(storeLocationId)) {
                wzpdDetail.setStoreLocationId(Integer.parseInt(storeLocationId));
            }
            wzpdDetail.setStoreLocationCode(storeLocationCode);
            wzpdDetail.setStoreLocationName(storeLocationName);
            if (enableSN != null && !"".equals(enableSN)) {
                wzpdDetail.setEnableSN(Integer.parseInt(enableSN));
            }
            wzpdDetail.setSnCode(snCode);

            wzpdDetail.setGuid(UUID.randomUUID().toString());
            wzpdDetail.setCreator(userId);
            wzpdDetail.setStockMan(userId);
            wzpdDetail.setExtendString1(userName);
            wzpdDetail.setStockStatus(1);
            wzpdDetail.setStockResult(0);
            wzpdDetail.setCreateDate(now);
            wzpdDetail.setStockDate(now);
            wzpdDetail.setMemo("盘盈数据");

            List<WzpdDetail> wzpdDetails = new ArrayList<>();
            wzpdDetails.add(wzpdDetail);
            sheetPDService.saveSheetDetails(wzpdDetails);
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "添加成功", null);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }

    /**
     * 导出盘点明细
     *
     * @param request
     * @return
     */
    /*@ResponseBody*/
    @RequestMapping(value = "/expKcpdResult.json")
    public void expKcpdResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("进入导出盘点结果expKcpdResult方法");
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        WzpcManage wzpcManage = sheetPDService.getWzpcManageOne(id);
        try {
            String encodedFileName = ExcelExport.encodeFileNameForDownload(request, wzpcManage.getName() + System.currentTimeMillis() + ".xls");
            HSSFWorkbook book = exportKcpdResult(request, wzpcManage, id);
            String userIp = (null == session.getAttribute("userIp") ?
                    null : session.getAttribute("userIp").toString());
            int userIdLog = (null == session.getAttribute("userId") ?
                    0 : Integer.parseInt(session.getAttribute("userId").toString()));
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.ACTIVITI_OPERATION.getCode(), SystemLogEnums.LogAction.EXPORT.getCode(),
                    "导出盘点结果:", userIp, userIdLog);
            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);
            book.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码类型:" + e.getMessage());
        } catch (IOException e) {
            log.error("文件导出错误" + e.getMessage());
        }
    }


    /**
     * 导出盘点明细Excel
     *
     * @param request
     * @return
     */
    public HSSFWorkbook exportKcpdResult(HttpServletRequest request, WzpcManage wzpcManage, String id) {

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("盘点结果表一");

        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        //设置字体大小
        font.setFontHeightInPoints((short) 20);
        HSSFFont font2 = wb.createFont();
        font2.setFontName("黑体");
        //设置字体大小
        font2.setFontHeightInPoints((short) 16);
        HSSFRow row0 = sheet.createRow((short) 0);
        row0.setHeight((short) 600);
        HSSFCell cell0 = row0.createCell((short) 0);
        cell0.setCellValue(wzpcManage.getName());
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

        HSSFRow row = sheet.createRow((short) 1);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER);
        sheet.setColumnWidth(0, 10000);
        sheet.setColumnWidth(1, 10000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 7000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 4500);
        sheet.setColumnWidth(6, 4500);
        sheet.setColumnWidth(7, 4500);
        sheet.setColumnWidth(8, 4500);
        sheet.setColumnWidth(9, 4500);
        cell0.setCellStyle(style);
        style.setFont(font2);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("物料编码");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("物料描述");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("单位");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("库房");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("库存数量");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("盘点数量");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("盘点日期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("盘点人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("盘点结果");
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("备注");
        cell.setCellStyle(style);
        List<WzpdDetail> wzpdDetails = this.sheetPDService.sheetDetailsNoPage(id);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        int size = wzpdDetails.size();
        String stockDateSt;
        String stockResult;
        for (int i = 0; i < size; i++) {
            row = sheet.createRow(i + 2);
            WzpdDetail wzpdDetail = wzpdDetails.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(wzpdDetail.getMaterialCode());
            row.getCell((short) 0).setCellStyle(style);
            row.createCell((short) 1).setCellValue(wzpdDetail.getDescription());
            row.getCell((short) 1).setCellStyle(style);
            row.createCell((short) 2).setCellValue(wzpdDetail.getDetailUnitName());
            row.getCell((short) 2).setCellStyle(style);
            row.createCell((short) 3).setCellValue(wzpdDetail.getStoreLocationName());
            row.getCell((short) 3).setCellStyle(style);
            row.createCell((short) 4).setCellValue(wzpdDetail.getSysCount());
            row.getCell((short) 4).setCellStyle(style);
            if (wzpdDetail.getDetailCount() == 0) {
                row.createCell((short) 5).setCellValue("");
            } else {
                row.createCell((short) 5).setCellValue(wzpdDetail.getDetailCount());
            }
            row.getCell((short) 5).setCellStyle(style);
            stockDateSt = wzpdDetail.getStockDate() == null ? "" : new SimpleDateFormat("yyyy-mm-dd").format(wzpdDetail.getStockDate());
            row.createCell((short) 6).setCellValue(stockDateSt);
            row.getCell((short) 6).setCellStyle(style);
            row.createCell((short) 7).setCellValue(wzpcManage.getPersonName());
            row.getCell((short) 7).setCellStyle(style);
            stockResult = "正常";
            if (wzpdDetail.getStockResult() == 1) {
                stockResult = "盘盈";
            } else if (wzpdDetail.getStockResult() == -1) {
                stockResult = "盘亏";
            }
            row.createCell((short) 8).setCellValue(stockResult);
            row.getCell((short) 8).setCellStyle(style);
            row.createCell((short) 9).setCellValue(wzpdDetail.getMemo());
            row.getCell((short) 9).setCellStyle(style);
        }
        return wb;
    }

    /**
     * 导入盘点结果
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/importPdResult.json")
    @ResponseBody
    public FileUploadResponse importPdResult(MultipartFile file, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        Integer userId = (null == userIdStr ? 0 : Integer.parseInt(userIdStr.toString()));
        Object userNameStr = session.getAttribute("userName");
        String userName = (null == userIdStr ? "" : userNameStr.toString());
        try {
            this.sheetPDService.importPdResult(file, userId, userName);
            return new FileUploadResponse(FileUploadResponse.Code.SUCCESS.getCode(), "导入成功!", "");
        } catch (RuntimeException e) {
            log.errorPrintStacktrace(e);
            return new FileUploadResponse(FileUploadResponse.Code.FAILURE.getCode(), e.getMessage(), "");
        } catch (Exception e) {
            log.errorPrintStacktrace(e);
            return new FileUploadResponse(FileUploadResponse.Code.FAILURE.getCode(), "保存失败，其他错误", "");
        }
    }

}



