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

 /*-------------------------------------------------��ת����-------------------------------------------------*/

    /**
     * �����������ʽ��յ�ҳ��
     *
     * @param condition
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/order.htm")
    public String order(SheetCGCondition condition, HttpServletRequest request, Model model) {

        log.debug("�����������ʽ��յ�ҳ��,��ǰ������:order");
        //��ȡ��ǰ��¼����Ϣ
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
//		��ȡ�˵�Code ���ڹ�����
        String menuCode = request.getParameter("menuCode");
        List<Dictionary> dictionaries = this.activitiService.getPartButton(menuCode,userCode);

        //�����sheetID,��ȡ��Ϣ����
        if (!StringUtils.isEmpty(condition.getSheetId())) {
            ManageOrder manageOrder = this.service.getOrderOne(condition.getSheetId());
            model.addAttribute("sheetValue", manageOrder);
        }

        model.addAttribute("buttons", dictionaries);
        model.addAttribute("menuCode", menuCode);
        model.addAttribute("departName", departName);
        model.addAttribute("userName", userName);
        model.addAttribute("createDate", new DateTime().toString("yyyy��MM��dd�� HH:mm:ss"));
        //���ʽ���ģ���ӡ����
        model.addAttribute("printTypes", PrintEnums.WZJSEnum.getMap());

        return "sheet/order/order";
    }

    /**
     * ҳ��_�༭
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {
        ManageOrder sheet = this.service.getOrderOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreatedate()).toString("yyyy��MM��dd�� HH:mm:ss"));
        model.addAttribute("sheetValue", sheet);
        //���ʽ���ģ���ӡ����
        model.addAttribute("printTypes", PrintEnums.WZJSEnum.getMap());
        return this.getUrl(request, sheet.getRouteid().intValue() + "", model, "sheet/order/order");
    }

    @RequestMapping(value = "/manageOrder.htm")
    public String manageOrder(HttpServletRequest request, Model model) {

        //��ȡ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        //��ȡ����״̬�ֵ��
        List<Dictionary> dictionaries = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType
                .ReceiptStatus.getCode());

        //��ȡ��Ա��Χ
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

        log.debug("������òɹ�����ͨ��ҳ��,��ǰ������:generalOrder");
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
        log.debug("����鿴���ʽ�����ϸ��ʷ��¼ҳ��,��ǰ������:orderHistory");

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
        log.debug("���������ɹ�������ϸҳ��,��ǰ������:generalOrder");
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

        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        //��ȡ��Ա��Χ
        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum
                .ORGANIZATION.getType());

        if (!StringUtils.isEmpty(condition.getSheetId())) {
            RkDetail sheet = this.sheetService.getRkDetail(condition.getSheetId());
            model.addAttribute("sheet", sheet);
        }

        model.addAttribute("departList", departList);
        return "sheet/order/addPlan";
    }

	 /*-------------------------------------------------��������-------------------------------------------------*/

    /**
     * ��ȡ�ɹ�����ͨ�õ����б�
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

        log.debug("�����ȡ�ɹ�����ͨ�÷���,��ǰ������:generalList");
        //��ȡ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        LayuiPage<Order> ret = null;
        try {
            ret = this.service.generalList(condition);
        } catch (Exception e) {
            log.error("��ȡͨ�òɹ������б���ʧ��");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * ��ȡ���ʽ��յ������б�
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
        log.debug("�����ȡ���ʽ��յ������б���,��ǰ������:manageOrderList");

        //��ȡ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");
        //�ֻ�APP
        if (!StringUtils.isEmpty(obj.getCreator())) {
            userId = obj.getCreator();
        }
        LayuiPage<ManageOrder> ret = null;
        try {
            ret = this.service.manageOrderList(obj, condition, userId, begin, end);
        } catch (Exception e) {
            log.error("��ȡ���ʽ��յ������б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ���ʽ��յ�������ϸ�б�
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
        log.debug("�����ȡ���ʽ��յ�������ϸ�б���,��ǰ������:DetailsList");
        LayuiPage<OrderDetails> ret = null;
        try {
            ret = this.service.detailsList(details, condition);
        } catch (Exception e) {
            log.error("��ȡ��ȡ���ʽ��յ�������ϸ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ���ʽ��յ���ϸ��־�б�
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
        log.debug("�����ȡ���ʽ��յ���ϸ��־�б���,��ǰ������:orderHistoryList");
        LayuiPage<OrderHistory> ret = null;
        try {
            ret = this.service.orderHistoryList(history.getRelationGuid(), condition);
        } catch (Exception e) {
            log.error("��ȡ��ȡ���ʽ��յ���ϸ��־�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ���ʽ��յ���ϸ�б�
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
        log.debug("�����ȡ���ʽ��յ���ϸ�б���,��ǰ������:detailsJSDList");
        LayuiPage<SheetJSDDetails> ret = null;
        try {
            ret = this.service.detailsJSDList(condition);
        } catch (Exception e) {
            log.error("��ȡ��ȡ���ʽ��յ���ϸ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * ���ʽ����޸ĵ���
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
        log.debug("�������ʽ����޸ĵ��ݷ���,��ǰ������:editSheet");
        HttpResponse ret;
        try {
            //��ȡ��ǰ��¼����Ϣ
            HttpSession session = request.getSession();
            Object requestUserIp = session.getAttribute("userIp");
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            String userIp = (null == requestUserIp ? null : requestUserIp.toString());

            sheet.setUpdater(userId);
            this.service.editSheet(sheet);
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_RECEIVING.getCode(),
                    SystemLogEnums.LogAction.EDIT.getCode(), "�޸����ʽ��յ�:" + sheet.getId(), userIp, userId);

            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "�������:" + e.getMessage(), null);
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
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "�������:" + e.getMessage(), null);
        }
        return ret;
    }

    /**
     * ���ʽ���ɾ����ϸ����
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
        log.debug("�������ʽ���ɾ����ϸ����,��ǰ������:deleteDetails");
        HttpResponse ret;
        try {
            //��ȡ��ǰ��¼����Ϣ
            HttpSession session = request.getSession();
            Object requestUserIp = session.getAttribute("userIp");
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            String userIp = (null == requestUserIp ? null : requestUserIp.toString());

            this.sheetDetailService.delDetails(condition);
            String str = logService.objectToJson(condition.getIds());
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_RECEIVING.getCode(),
                    SystemLogEnums.LogAction.DELETE.getCode(), "ɾ�����ʽ�����ϸ:" + str, userIp, userId);
            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("ɾ�����ʽ�����ϸʧ��");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ������:" + e.getMessage(), null);
        }
        return ret;
    }

    /**
     * ��ȡ���ʽ��յ��ݸ���
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
            log.error("��ȡ���ʽ��յ��ݸ���ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    @RequestMapping(value = "/uploadFile.json", method = RequestMethod.POST)
    public void uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse
            response) {

        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        // �ϴ���ʶ��0���ɹ�  1��ʧ�ܣ�
        int code = 0;
        // ����msg
        String msg ;
        // ��ȡͼƬԭ������
        String imageName = file.getOriginalFilename();
        // ��ȡϵͳָ��·��
        String path = AppConfig.getProperty("filepath.absolutePath");
        // ��ʱ���Ϊ������
        String newName = System.currentTimeMillis() + imageName.substring(imageName.lastIndexOf("."));

        // �����ļ�����
        File files = new File(path, newName);
        // ��ȡ�ļ�ָ��Ŀ¼,���û���򴴽�
        if (!files.getParentFile().exists()) {
            files.getParentFile().mkdirs();
        }

        try {
            // �ϴ��ļ�
            file.transferTo(new File(path, newName));
        } catch (IOException e) {
            log.error("��д�쳣");
            log.errorPrintStacktrace(e);
            code = 1;
            msg = "��д�쳣";
        } catch (IllegalStateException e) {
            log.error("��д�쳣");
            log.errorPrintStacktrace(e);
            code = 1;
            msg = "�﷨����";
        }
        msg = "�ϴ��ɹ�";
        String json = JSONObject.toJSONString(new FileUploadResponse(code, msg, AppConfig.getProperty("filepath" +
                ".virtualPath") + "/" + newName, imageName));
        BaseController.print(response, json);
    }

    /**
     * ���渽����Ϣ�����ݿ�
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
        log.debug("�������ʽ��ձ��渽������,��ǰ������:saveFile");
        HttpResponse ret;

        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        try {

            if (this.sheetService.checkFile(file.getFileName()) > 0) {
                return new HttpResponse(HttpResponse.Status.FAILURE, "���������ظ�,��������д", null);
            } else {
                file.setGuid(java.util.UUID.randomUUID().toString());
                file.setFileType("1");
                file.setStatus(1);
                file.setCreator(userId);
                file.setCreateDate(new Date());

                this.sheetService.saveFile(file);
                this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_RECEIVING.getCode(),
                        SystemLogEnums.LogAction.UPLOAD_FILE.getCode(), "�ϴ����ʽ��ո���,sheetid:" + file.getAttachRelateId(),
                        userIp, userId);
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�ϴ������ɹ�", null);
            }
        } catch (Exception e) {
            log.error("���渽��ʧ�ܣ�");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "���渽���쳣", null);
        }
        return ret;
    }

    @RequestMapping(value = "/deleteFile.json", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse deleteFile(@ModelAttribute("AttachFile") AttachFile attachFile, HttpServletRequest request,
                                   HttpServletResponse response) {
        log.debug("�������ʽ���ɾ����������,��ǰ������:deleteFile");
        HttpResponse ret;

        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());
        try {
            // ɾ�����ݿ�����
            this.sheetService.deleteFile(attachFile.getId());

            // ɾ��ʵ���ļ�,��ȡ�ļ�ʵ������
            String str = attachFile.getFilePath();
            str = str.substring(str.lastIndexOf("/"), str.length());
            File file = new File(AppConfig.getProperty("filepath.absolutePath") + str);

            // �ж��ļ�·������Ӧ���ļ��Ƿ����,������һ���ļ�,ɾ��
            if (file.exists() && file.isFile()) {
                if (!file.delete()) {
                    System.out.println("ɾ���ļ�" + str + "ʧ�ܣ�");
                    ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ������ʧ��", null);
                } else {
                    System.out.println("ɾ���ļ�" + str + "�ɹ���");
                    this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_RECEIVING.getCode(),
                            SystemLogEnums.LogAction.DELETE.getCode(), "ɾ�������ɹ�:" + str, userIp, userId);
                }
            }
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ�������ɹ�", null);
        } catch (Exception e) {
            log.error("ɾ������ʧ�ܣ�");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ�������쳣", null);
        }
        return ret;
    }


    /*-------------------------------------------------�ֻ�App�ӿ�-------------------------------------------------*/

    /**
     * ���浥��
     */
    @RequestMapping(method = RequestMethod.POST, value = "{type}")
    @ResponseBody
    public HttpResponse saveSheet(@PathVariable("type") String type, Sheet obj, SheetCondition
            condition, HttpServletRequest request) {
        log.debug("��������" + type);
        HttpResponse ret;
        try {
            String menuCode = request.getParameter("menuCode");
            Dictionary dic = this.dictionaryService.getDictionaryByType(type);
            if (dic == null) {
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "�����ֵ��в��Ҳ�����Ӧ����", null);

            } else {
                List<FormTemplate> formTemplates = this.formTemplateService.getFromTemBydicID(dic.getId().toString());
                if (formTemplates == null || formTemplates.size() != 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE, "δ���õ���", null);
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
                            return new HttpResponse(HttpResponse.Status.FAILURE, "�û�������" + userId, null);
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
                        obj.setExtendString4("���ɹ���úԶ����Դ�������޹�˾");
                        obj.setExtendString1(order.getProviderdepname());
                        obj.setProviderDepId(order.getProviderdepid());
                        obj.setExtendString2(order.getStockorgname());
                    }
                    this.sheetService.saveSheet(obj, menuCode, type, userCode, condition);
                    logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums
                                    .LogAction.ADD.getCode(), "��������:" + obj.getName(), "app", userId);
                    ret = new HttpResponse(obj);
                }
            }
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }
}

	
