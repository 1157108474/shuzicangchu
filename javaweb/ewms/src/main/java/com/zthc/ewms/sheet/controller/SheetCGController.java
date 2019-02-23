package com.zthc.ewms.sheet.controller;

import com.alibaba.fastjson.JSONObject;
import com.hckj.base.mvc.BaseController;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.FileUploadResponse;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetCGControllerGuard;
import com.zthc.ewms.sheet.entity.file.AttachFile;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.order.Order;
import com.zthc.ewms.sheet.entity.order.OrderDetails;
import com.zthc.ewms.sheet.entity.order.OrderHistory;
import com.zthc.ewms.sheet.entity.rk.RkDetail;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.sheet.service.SheetService;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.service.FormTemplateService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.print.entity.guard.PrintEnums;
import com.zthc.ewms.system.print.service.PrintService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.user.service.UserService;
import drk.system.AppConfig;
import drk.system.Log;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sheet/wzjs")
public class SheetCGController extends SheetCGControllerGuard {

    @Resource(name = "activitiService")
    public ActivitiService activitiService;

    @Resource(name = "sheetDetailService")
    public SheetDetailService sheetDetailService;

//    @Resource(name = "taskService")
//    public TaskService taskService;

    @Resource(name = "dictionaryService")
    public DictionaryService dictionaryService;

    @Resource(name = "userScopeService")
    public UserScopeService userScopeService;

    @Resource(name = "logService")
    public LogService logService;

    @Resource(name = "sheetService")
    public SheetService sheetService;

    @Resource(name = "formTemplateService")
    public FormTemplateService formTemplateService;

    @Resource(name = "userService")
    public UserService userService;

    @Resource(name = "printService")
    public PrintService printService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.sheet.controller.SheetCGController");
    }

 /*-------------------------------------------------跳转方法-------------------------------------------------*/

    /**
     * 进入新增物资接收单页面
     *
     * @param condition
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/order.htm")
    public String order(SheetCGCondition condition, HttpServletRequest request, Model model) {

        log.debug("进入新增物资接收单页面,当前方法名:order");
        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestDepartName = session.getAttribute("departName");
        Object requestUserName = session.getAttribute("userName");
        Object requestuserCode = session.getAttribute("userCode");
        String userCode = (null == requestuserCode ? null : requestuserCode.toString());
        String departName = (null == requestDepartName ? null : requestDepartName.toString());
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());
        String userName = (null == requestUserName ? null : requestUserName.toString());
        Object userIdStr = session.getAttribute("userId");
        int userId = Integer.valueOf(userIdStr.toString());
//		获取菜单Code 用于工作流
        String menuCode = request.getParameter("menuCode");
        List<Dictionary> dictionaries = this.activitiService.getPartButton(menuCode,userCode);

        //如果有sheetID,获取信息加载
        if (!StringUtils.isEmpty(condition.getSheetId())) {
            ManageOrder manageOrder = this.service.getOrderOne(condition.getSheetId());
            model.addAttribute("sheetValue", manageOrder);
        }

        model.addAttribute("buttons", dictionaries);
        model.addAttribute("menuCode", menuCode);
        model.addAttribute("departName", departName);
        model.addAttribute("userName", userName);
        model.addAttribute("createDate", new DateTime().toString("yyyy年MM月dd日 HH:mm:ss"));
        //物资接收模板打印类型
        model.addAttribute("printTypes", PrintEnums.WZJSEnum.getMap());

        return "sheet/order/order";
    }

    /**
     * 页面_编辑
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {
        ManageOrder sheet = this.service.getOrderOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreatedate()).toString("yyyy年MM月dd日 HH:mm:ss"));
        model.addAttribute("sheetValue", sheet);
        //物资接收模板打印类型
        model.addAttribute("printTypes", PrintEnums.WZJSEnum.getMap());
        return this.getUrl(request, sheet.getRouteid().intValue() + "", model, "sheet/order/order");
    }

    @RequestMapping(value = "/manageOrder.htm")
    public String manageOrder(HttpServletRequest request, Model model) {

        //获取登录人信息
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        //获取单据状态字典表
        List<Dictionary> dictionaries = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType
                .ReceiptStatus.getCode());

        //获取人员范围
        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum
                .ORGANIZATION.getType());

        model.addAttribute("orderStatus", dictionaries);
        model.addAttribute("userId", userId);
        model.addAttribute("departList", departList);

        return "sheet/order/manageOrder";
    }

    @RequestMapping(value = "/generalOrder.htm")
    public String generalOrder(SheetCGCondition condition, HttpServletRequest request, HttpServletResponse response,
                               Model model) {

        log.debug("进入调用采购订单通用页面,当前方法名:generalOrder");
        String orderId = "";
        if (!StringUtils.isEmpty(condition.getOrderId())) {
            orderId = condition.getOrderId();
        }

        model.addAttribute("orderId", orderId);
        return "sheet/order/generalOrder";
    }

    @RequestMapping(value = "/orderHistory.htm")
    public String orderHistory(SheetCGCondition condition, HttpServletRequest request, HttpServletResponse response,
                               Model model) {
        log.debug("进入查看物资接收明细历史记录页面,当前方法名:orderHistory");

        String relationGuid = "";
        if (condition.getDetailsGUID() != null && condition.getDetailsGUID() != "") {
            relationGuid = condition.getDetailsGUID();
        }
        model.addAttribute("detailsGUID", relationGuid);
        return "sheet/order/orderHistory";
    }

    @RequestMapping(value = "/addDetails.htm")
    public String addDetails(SheetCGCondition condition, HttpServletRequest request, HttpServletResponse response,
                             Model model) {
        log.debug("进入新增采购订单明细页面,当前方法名:generalOrder");
        model.addAttribute("orderNum", condition.getOrderNum());
        return "sheet/order/detailed";
    }

    @RequestMapping(value = "/addFile.htm")
    public String addFile(SheetCGCondition condition, HttpServletRequest request, HttpServletResponse response,
                          Model model) {
        model.addAttribute("sheetId", condition.getSheetId());
        return "sheet/order/generalFile";
    }

    @RequestMapping(value = "/addPlanOther.htm")
    public String addPlanOther(SheetCGCondition condition, HttpServletRequest request, Model model) {

        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        //获取人员范围
        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum
                .ORGANIZATION.getType());

        if (!StringUtils.isEmpty(condition.getSheetId())) {
            RkDetail sheet = this.sheetService.getRkDetail(condition.getSheetId());
            model.addAttribute("sheet", sheet);
        }

        model.addAttribute("departList", departList);
        return "sheet/order/addPlan";
    }

	 /*-------------------------------------------------基础方法-------------------------------------------------*/

    /**
     * 获取采购订单通用调用列表
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listOrderGeneral.json", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<Order> generalList(SheetCGCondition condition, HttpServletRequest request,
                                        HttpServletResponse response) {

        log.debug("进入获取采购订单通用方法,当前方法名:generalList");
        //获取登录人信息
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        LayuiPage<Order> ret = null;
        try {
            ret = this.service.generalList(condition);
        } catch (Exception e) {
            log.error("获取通用采购订单列表方法失败");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * 获取物资接收单管理列表
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listManageOrder.json", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<ManageOrder> manageOrderList(@ModelAttribute("ManageOrder") ManageOrder obj, SheetCGCondition
            condition,HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取物资接收单管理列表方法,当前方法名:manageOrderList");

        //获取登录人信息
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");
        //手机APP
        if (!StringUtils.isEmpty(obj.getCreator())) {
            userId = obj.getCreator();
        }
        LayuiPage<ManageOrder> ret = null;
        try {
            ret = this.service.manageOrderList(obj, condition, userId, begin, end);
        } catch (Exception e) {
            log.error("获取物资接收单管理列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取物资接收单新增明细列表
     *
     * @param details
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listDetails.json", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<OrderDetails> detailsList(@ModelAttribute("OrderDetails") OrderDetails details, SheetCGCondition
            condition, HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取物资接收单新增明细列表方法,当前方法名:DetailsList");
        LayuiPage<OrderDetails> ret = null;
        try {
            ret = this.service.detailsList(details, condition);
        } catch (Exception e) {
            log.error("获取获取物资接收单新增明细列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取物资接收单明细日志列表
     *
     * @param history
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listOrderHistory.json", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<OrderHistory> orderHistoryList(@ModelAttribute("OrderHistory") OrderHistory history,
                                                    SheetCGCondition condition,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        log.debug("进入获取物资接收单明细日志列表方法,当前方法名:orderHistoryList");
        LayuiPage<OrderHistory> ret = null;
        try {
            ret = this.service.orderHistoryList(history.getRelationGuid(), condition);
        } catch (Exception e) {
            log.error("获取获取物资接收单明细日志列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取物资接收单明细列表
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listJSDDetails.json", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<SheetJSDDetails> detailsJSDList(SheetCGCondition condition, HttpServletRequest request,
                                                     HttpServletResponse response) {
        log.debug("进入获取物资接收单明细列表方法,当前方法名:detailsJSDList");
        LayuiPage<SheetJSDDetails> ret = null;
        try {
            ret = this.service.detailsJSDList(condition);
        } catch (Exception e) {
            log.error("获取获取物资接收单明细列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * 物资接收修改单据
     *
     * @param sheet
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editSheet.json", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse editSheet(@ModelAttribute("Sheet") Sheet sheet, HttpServletRequest request,
                                  HttpServletResponse response) {
        log.debug("进入物资接收修改单据方法,当前方法名:editSheet");
        HttpResponse ret;
        try {
            //获取当前登录人信息
            HttpSession session = request.getSession();
            Object requestUserIp = session.getAttribute("userIp");
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            String userIp = (null == requestUserIp ? null : requestUserIp.toString());

            sheet.setUpdater(userId);
            this.service.editSheet(sheet);
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_RECEIVING.getCode(),
                    SystemLogEnums.LogAction.EDIT.getCode(), "修改物资接收单:" + sheet.getId(), userIp, userId);

            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("保存记录出错");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "保存出错:" + e.getMessage(), null);
        }
        return ret;
    }

    @RequestMapping(value = "/planOthers.json", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse savePlanOther(@ModelAttribute("SheetDetail") SheetDetail detail, HttpServletRequest request,
                                      HttpServletResponse response) {
        HttpResponse ret;
        try {
            this.service.savePlanOther(detail);
            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("保存记录出错");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "保存出错:" + e.getMessage(), null);
        }
        return ret;
    }

    /**
     * 物资接收删除明细方法
     *
     * @param condition
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/deleteSheetDetails.json", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse deleteDetails(SheetDetailCondition condition, HttpServletRequest request, HttpServletResponse
            response) throws IOException {
        log.debug("进入物资接收删除明细方法,当前方法名:deleteDetails");
        HttpResponse ret;
        try {
            //获取当前登录人信息
            HttpSession session = request.getSession();
            Object requestUserIp = session.getAttribute("userIp");
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            String userIp = (null == requestUserIp ? null : requestUserIp.toString());

            this.sheetDetailService.delDetails(condition);
            String str = logService.objectToJson(condition.getIds());
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_RECEIVING.getCode(),
                    SystemLogEnums.LogAction.DELETE.getCode(), "删除物资接收明细:" + str, userIp, userId);
            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("删除物资接收明细失败");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除出错:" + e.getMessage(), null);
        }
        return ret;
    }

    /**
     * 获取物资接收单据附件
     *
     * @param condition
     * @param conditions
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listFile.json", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<AttachFile> sheetFileList(SheetCGCondition condition, SheetCondition conditions,
                                               HttpServletRequest request, HttpServletResponse response) {
        LayuiPage<AttachFile> ret = null;
        try {
            ret = this.sheetService.sheetFileList(condition.getSheetId(), conditions);
        } catch (Exception e) {
            log.error("获取物资接收单据附件失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    @RequestMapping(value = "/uploadFile.json", method = RequestMethod.POST)
    public void uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse
            response) {

        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        // 上传标识（0：成功  1：失败）
        int code = 0;
        // 返回msg
        String msg ;
        // 获取图片原来名字
        String imageName = file.getOriginalFilename();
        // 获取系统指定路径
        String path = AppConfig.getProperty("filepath.absolutePath");
        // 以时间戳为新名字
        String newName = System.currentTimeMillis() + imageName.substring(imageName.lastIndexOf("."));

        // 创建文件对象
        File files = new File(path, newName);
        // 获取文件指定目录,如果没有则创建
        if (!files.getParentFile().exists()) {
            files.getParentFile().mkdirs();
        }

        try {
            // 上传文件
            file.transferTo(new File(path, newName));
        } catch (IOException e) {
            log.error("读写异常");
            log.errorPrintStacktrace(e);
            code = 1;
            msg = "读写异常";
        } catch (IllegalStateException e) {
            log.error("读写异常");
            log.errorPrintStacktrace(e);
            code = 1;
            msg = "语法错误";
        }
        msg = "上传成功";
        String json = JSONObject.toJSONString(new FileUploadResponse(code, msg, AppConfig.getProperty("filepath" +
                ".virtualPath") + "/" + newName, imageName));
        BaseController.print(response, json);
    }

    /**
     * 保存附件信息到数据库
     *
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveFile.json", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse saveFile(@ModelAttribute("AttachFile") AttachFile file, HttpServletRequest request,
                                 HttpServletResponse response) {
        log.debug("进入物资接收保存附件方法,当前方法名:saveFile");
        HttpResponse ret;

        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        try {

            if (this.sheetService.checkFile(file.getFileName()) > 0) {
                return new HttpResponse(HttpResponse.Status.FAILURE, "附件名字重复,请重新填写", null);
            } else {
                file.setGuid(java.util.UUID.randomUUID().toString());
                file.setFileType("1");
                file.setStatus(1);
                file.setCreator(userId);
                file.setCreateDate(new Date());

                this.sheetService.saveFile(file);
                this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_RECEIVING.getCode(),
                        SystemLogEnums.LogAction.UPLOAD_FILE.getCode(), "上传物资接收附件,sheetid:" + file.getAttachRelateId(),
                        userIp, userId);
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "上传附件成功", null);
            }
        } catch (Exception e) {
            log.error("保存附件失败！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "保存附件异常", null);
        }
        return ret;
    }

    @RequestMapping(value = "/deleteFile.json", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse deleteFile(@ModelAttribute("AttachFile") AttachFile attachFile, HttpServletRequest request,
                                   HttpServletResponse response) {
        log.debug("进入物资接收删除附件方法,当前方法名:deleteFile");
        HttpResponse ret;

        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());
        try {
            // 删除数据库数据
            this.sheetService.deleteFile(attachFile.getId());

            // 删除实际文件,获取文件实际名称
            String str = attachFile.getFilePath();
            str = str.substring(str.lastIndexOf("/"), str.length());
            File file = new File(AppConfig.getProperty("filepath.absolutePath") + str);

            // 判断文件路径所对应的文件是否存在,并且是一个文件,删除
            if (file.exists() && file.isFile()) {
                if (!file.delete()) {
                    System.out.println("删除文件" + str + "失败！");
                    ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除附件失败", null);
                } else {
                    System.out.println("删除文件" + str + "成功！");
                    this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_RECEIVING.getCode(),
                            SystemLogEnums.LogAction.DELETE.getCode(), "删除附件成功:" + str, userIp, userId);
                }
            }
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "删除附件成功", null);
        } catch (Exception e) {
            log.error("删除附件失败！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除附件异常", null);
        }
        return ret;
    }


    /*-------------------------------------------------手机App接口-------------------------------------------------*/

    /**
     * 保存单据
     */
    @RequestMapping(method = RequestMethod.POST, value = "{type}")
    @ResponseBody
    public HttpResponse saveSheet(@PathVariable("type") String type, Sheet obj, SheetCondition
            condition, HttpServletRequest request) {
        log.debug("新增保存" + type);
        HttpResponse ret;
        try {
            String menuCode = request.getParameter("menuCode");
            Dictionary dic = this.dictionaryService.getDictionaryByType(type);
            if (dic == null) {
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "数据字典中查找不到相应数据", null);

            } else {
                List<FormTemplate> formTemplates = this.formTemplateService.getFromTemBydicID(dic.getId().toString());
                if (formTemplates == null || formTemplates.size() != 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE, "未配置单据", null);
                } else {
                    FormTemplate formTemplate = formTemplates.get(0);
                    Integer userId = 0;
                    Integer departId = 0;
                    Integer ztId = 0;
                    String userIp = "";
                    String userCode = "80000179";
                    HttpSession session = request.getSession();
                    String data = request.getParameter("appFlag");
                    boolean appFlag = false;
                    if (data != null && "1".equals(data)) {
                        appFlag = true;
                        userId = Integer.parseInt(request.getParameter("userId"));
                        userIp = "app";
                        User user = this.userService.getUserOne(userId);
                        if (null != user) {
                            ztId = user.getZtId();
                            userCode = user.getCode();
                            Depart depart = user.getParent();
                            if (null == depart) {
                                departId = depart.getId();
                            }
                        } else {
                            return new HttpResponse(HttpResponse.Status.FAILURE, "用户不存在" + userId, null);
                        }
                    } else {
                        if (session.getAttribute("userId") != null) {
                            userId = Integer.parseInt(session.getAttribute("userId").toString());
                            departId = Integer.parseInt(session.getAttribute("departId").toString());
                            ztId = Integer.parseInt(session.getAttribute("ztId").toString());
                            userIp = session.getAttribute("userIp").toString();
                            userCode = session.getAttribute("userCode").toString();
                        }
                    }
                    obj.setDepartId(ztId);
                    obj.setGuid(java.util.UUID.randomUUID().toString());
                    obj.setName(formTemplate.getFormTemName());
                    obj.setKindId(dic.getId());
                    obj.setStatus(DictionaryEnums.Status.SHEETING.getCode());
                    obj.setZtId(ztId);
                    obj.setCreator(userId);
                    obj.setCreateDate(new Date());
                    if (data != null && "1".equals(data)) {
                        String orderId = request.getParameter("orderId");
                        Order order = this.sheetService.getOrderOne(orderId);
                        obj.setOrderNum(order.getOrdernum());
                        obj.setExtendInt1(Integer.parseInt(orderId));
                        obj.setExtendString5(order.getIssuecode().toString());
                        obj.setExtendString3(order.getOrdertype());
                        obj.setExtendString4("内蒙古中煤远兴能源化工有限公司");
                        obj.setExtendString1(order.getProviderdepname());
                        obj.setProviderDepId(order.getProviderdepid());
                        obj.setExtendString2(order.getStockorgname());
                    }
                    this.sheetService.saveSheet(obj, menuCode, type, userCode, condition);
                    logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums
                                    .LogAction.ADD.getCode(), "新增单据:" + obj.getName(), "app", userId);
                    ret = new HttpResponse(obj);
                }
            }
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }
}

	
