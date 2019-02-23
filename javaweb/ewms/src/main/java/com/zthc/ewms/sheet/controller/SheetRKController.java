package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetRKControllerGuard;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.rk.RkDetail;
import com.zthc.ewms.sheet.entity.rk.RkDetails;
import com.zthc.ewms.sheet.entity.rk.RkSubDetail;
import com.zthc.ewms.sheet.entity.rk.RkdList;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.sheet.service.SheetRKDETAILService;
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
import com.zthc.ewms.system.print.entity.guard.Print;
import com.zthc.ewms.system.print.service.PrintService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.user.service.UserService;
import drk.system.Log;
import org.activiti.engine.TaskService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sheet/rk")
public class SheetRKController extends SheetRKControllerGuard {

    @Resource(name = "activitiService")
    public ActivitiService activitiService;

    @Resource(name = "sheetDetailService")
    public SheetDetailService sheetDetailService;

    @Resource(name = "taskService")
    public TaskService taskService;

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

    @Resource(name = "printService")
    public PrintService printService;

    @Resource(name = "userService")
    public UserService userService;

    @Resource(name = "sheetRKDETAILService")
    public SheetRKDETAILService sheetRKDETAILService;
    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.sheet.controller.Sheet_RKController");
    }

	 /*-------------------------------------------------��ת����-------------------------------------------------*/


    @RequestMapping(value = "/wzrk.htm")
    public String wzrk(HttpServletRequest request, Model model) {
        //��ȡ��ǰ��¼����Ϣ
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
        //��ȡ�˵�Code ���ڹ�����
        String menuCode = request.getParameter("menuCode");
        List<Dictionary> dictionaries = this.activitiService.getPartButton(menuCode,userCode);

        //�����������
        List<Dictionary> rkType = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.RKTYPE.getCode());

        // print
        Integer[] ids = {164, 165, 166, 167};
        List<Print> prints = this.printService.getPrints(ids);
        model.addAttribute("printTypes", prints);
        model.addAttribute("loginUserId", userId);
        model.addAttribute("buttons", dictionaries);
        model.addAttribute("menuCode", menuCode);
        model.addAttribute("departName", departName);
        model.addAttribute("ztId", ztId);
        model.addAttribute("userName", userName);
        model.addAttribute("createDate", new DateTime().toString("yyyy��MM��dd�� HH:mm:ss"));
        model.addAttribute("rkType", rkType);
        return "sheet/rk/wzrk";
    }

    /**
     * ҳ��_�༭
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {

        //��ȡ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        SheetRKD sheet = this.sheetService.getRkSheetOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreatedate()).toString("yyyy��MM��dd�� HH:mm:ss"));
        model.addAttribute("loginUserId", userId);
        model.addAttribute("sheet", sheet);
        return this.getUrl(request, sheet.getRouteid().intValue() + "", model, sheet, "sheet/rk/wzrk");


    }


    @RequestMapping(value = "/manageWzrk.htm")
    public String manageWzrk(HttpServletRequest request, Model model) {

        //��ȡ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        //��ȡ����״̬�ֵ��
        List<Dictionary> dictionaries = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType
                .ReceiptStatus.getCode());
        model.addAttribute("orderStatus", dictionaries);

        //��ȡ��Ա��Χ
        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum
                .ORGANIZATION.getType());

        //�����������
        List<Dictionary> rkType = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.
                RKTYPE.getCode());

        model.addAttribute("rkType", rkType);
        model.addAttribute("orderStatus", dictionaries);
        model.addAttribute("userId", userId);
        model.addAttribute("departList", departList);

        return "sheet/rk/manageWzrk";
    }

    @RequestMapping(value = "/generalJSD.htm")
    public String generalJSD(HttpServletRequest request, Model model) {
        return "sheet/rk/generalJSD";
    }

    @RequestMapping(value = "/addDetails.htm")
    public String addDetails(HttpServletRequest request, Model model) {
        model.addAttribute("code", request.getParameter("jsCode"));
        return "sheet/rk/detailed";
    }

    @RequestMapping(value = "/generalRKD.htm")
    public String generalRKD(HttpServletRequest request, Model model) {
        model.addAttribute("creator", request.getParameter("creator"));
        return "sheet/rk/generalRKD";
    }

    @RequestMapping(value = "/getOriginalLocation.htm")
    public String getOriginalLocation(Integer rid,Integer extendInt1,  HttpServletRequest request, Model model) {

        RkDetails data = this.sheetService.getRkDetails(rid);
        SheetStock stock = this.service.getOriginalLocation(data.getMaterialCode(), data.getStoreID());

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
        model.addAttribute("extendInt1",extendInt1);
        model.addAttribute("storeValue", data);

        return "sheet/rk/editDetail";
    }

	 /*-------------------------------------------------��������-------------------------------------------------*/

    /**
     * ��ȡ������ⵥ�����б�
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listManageRk.json")
    @ResponseBody
    public LayuiPage<SheetRKD> manageRkList(SheetRKD obj, SheetCondition condition, HttpServletRequest request,
                                            HttpServletResponse response) {
        log.debug("�����ȡ������ⵥ�����б���,��ǰ������:manageRkList");
        //TODO:����ݲ��Ž��в�ѯ
        LayuiPage<SheetRKD> ret = null;
        try {
            HttpSession session = request.getSession();
            String begin = request.getParameter("begin");
            String end = request.getParameter("end");
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

            //app��ʶ
            if (!StringUtils.isEmpty(obj.getCreator())) {
                userId = obj.getCreator();
            }

            ret = this.service.manageRkList(obj, condition, userId, begin, end);
        } catch (Exception e) {
            log.error("��ȡ������ⵥ�����б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ���ʽ��յ��б�
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listJSDList.json")
    @ResponseBody
    public LayuiPage<SheetJSD> generalJSDList(Sheet obj, SheetCondition condition, HttpServletRequest request,
                                              HttpServletResponse response) {
        log.debug("�����ȡ���ʽ��յ��б���,��ǰ������:manageRkList");
        LayuiPage<SheetJSD> ret = null;
        try {
            ret = this.service.generalJSDList(obj, condition);
        } catch (Exception e) {
            log.error("��ȡ��ȡ���ʽ��յ��б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ������ⵥ������ϸ�б�
     *
     * @param rkDetail
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listDetails.json")
    @ResponseBody
    public LayuiPage<RkDetail> detailsList(@ModelAttribute("RkDetail") RkDetail rkDetail, SheetRKCondition condition,
                                           HttpServletRequest request, HttpServletResponse response) {
        log.debug("�����ȡ������ⵥ������ϸ�б���,��ǰ������:detailsList");
        LayuiPage<RkDetail> ret = null;
        try {
            ret = this.service.detailsList(rkDetail, condition);
        } catch (Exception e) {
            log.error("��ȡ��ȡ������ⵥ������ϸ�б���");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ������ⵥҳ����ϸ�б�
     *
     * @param rkDetails
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listRkDetails.json")
    @ResponseBody
    public LayuiPage<RkDetails> rkDetailList(@ModelAttribute("RkDetails") RkDetails rkDetails, SheetRKCondition
            condition,
                                             HttpServletRequest request, HttpServletResponse response) {
        log.debug("�����ȡ������ⵥҳ����ϸ�б���,��ǰ������:rkDetailList");
        LayuiPage<RkDetails> ret = null;
        try {
            ret = this.service.rkDetailList(rkDetails, condition);
        } catch (Exception e) {
            log.error("��ȡ��ȡ������ⵥҳ����ϸ�б���");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��������
     */
    @RequestMapping(method = RequestMethod.POST, value = "{type}")
    @ResponseBody
    public HttpResponse saveSheet(@PathVariable("type") String type, String menuCode, SheetRK obj, SheetRKCondition
            condition, HttpServletRequest request) {
        log.debug("��������" + type);
        HttpResponse ret;
        try {
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
                    Date now = new Date();
                    HttpSession session = request.getSession();
                    if (session.getAttribute("userId") != null) {
                        userId = Integer.parseInt(session.getAttribute("userId").toString());
                        departId = Integer.parseInt(session.getAttribute("departId").toString());
                        ztId = Integer.parseInt(session.getAttribute("ztId").toString());
                        userIp = session.getAttribute("userIp").toString();
                        userCode = session.getAttribute("userCode").toString();
                    }

                    obj.setGuid(java.util.UUID.randomUUID().toString());
                    obj.setName(formTemplate.getFormTemName());
                    obj.setKindId(dic.getId());
                    obj.setDepartId(departId);
                    obj.setStatus(DictionaryEnums.Status.SHEETING.getCode());
                    obj.setCreator(userId);
                    obj.setZtId(ztId);
                    obj.setCreateDate(now);
                    //���ʱ��
                    obj.setSubmitTime(now);

                    //appFlag
                    String appFlag = request.getParameter("appFlag");

                    if (appFlag != null && "1".equals(appFlag)) {
                        Object orderId = null == request.getParameter("orderId") ? 0 : request.getParameter("orderId");
                        Integer orderSheetId = Integer.parseInt(orderId.toString());
                        Object ad = null == request.getParameter("userId") ? userId : request.getParameter("userId");
                        userId = Integer.parseInt(ad.toString());
                        menuCode = "receipt_details";
                        userIp = "app";
                        User user = this.userService.getUserOne(userId);
                        if (null != user) {
                            ztId = user.getZtId();
                            userCode = user.getCode();
                            Depart depart = user.getParent();
                            obj.setZtId(ztId);
                            if (null != depart) {
                                departId = depart.getId();
                                obj.setDepartId(departId);
                                obj.setZtId(departId);
                            }
                        } else {
                            return new HttpResponse(HttpResponse.Status.FAILURE, "�û���Ϣ����", null);
                        }

                        if (orderSheetId == null || orderSheetId == 0) {
                            return new HttpResponse(HttpResponse.Status.FAILURE, "orderId����ȷ,�޷����ɶ���", null);
                        } else {

                            Sheet sheet = this.sheetService.getSheetOne(orderSheetId);
                            obj.setReceiveNum(sheet.getCode());
                            obj.setExtendInt1(sheet.getExtendInt1());
                            obj.setOrderNum(sheet.getOrderNum());
                            obj.setExtendInt2(sheet.getExtendInt2());
                            obj.setExtendString3(sheet.getExtendString3());
                            obj.setExtendString4(sheet.getExtendString4());
                            obj.setExtendString2(sheet.getExtendString2());
                            obj.setExtendString1(sheet.getExtendString1());
                            obj.setExtendString5(sheet.getExtendString5());
                            //������ʱ��
                            obj.setSubmitTime(now);
                            obj.setTypeId(789);
                        }
                        obj.setCreator(userId);
                    }

                    this.service.saveSheet(obj, menuCode, type, userCode, condition);
                    logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums
                                    .LogAction.ADD.getCode(),
                            "��������:" + obj.getName(), userIp, userId);
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

    /**
     * ������ⵥ�޸ĵ���
     *
     * @param obj
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editSheet.json")
    @ResponseBody
    public HttpResponse editSheet(@ModelAttribute("SheetRK") SheetRK obj, HttpServletRequest request,
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

            obj.setUpdator(userId);
            this.service.editSheet(obj);
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_STORAGE.getCode(),
                    SystemLogEnums.LogAction.EDIT.getCode(), "�޸�������ⵥ:" + obj.getId(), userIp, userId);

            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "�������:" + e.getMessage(), null);
        }
        return ret;
    }


    /**
     * ��ȡ������ⵥ�б�
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listRKDList.json")
    @ResponseBody
    public LayuiPage<RkdList> generalRKDList(@ModelAttribute("RkdList") RkdList obj, SheetRKCondition condition,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
        log.debug("������������ȡ��ⵥ�б���,��ǰ������:RkdList");
        LayuiPage<RkdList> ret = null;
        try {
            ret = this.service.generalRKDList(obj, condition);
        } catch (Exception e) {
            log.error("��ȡ������ⵥ������ϸ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;

    }

    /**
     * ��������ȡ�ѷ�����ϸ�б�
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
        log.debug("������������ȡ�ѷ�����ϸ�б���,��ǰ������:getSonDetail");
        LayuiPage<RkSubDetail> ret = null;
        try {
            ret = this.service.getSonDetail(subDetail, condition);
        } catch (Exception e) {
            log.error("��ȡ��������ȡ�ѷ�����ϸ�б�ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

//    /**
//     * ����ύǰ�жϣ��жϵ�ǰ��ϸ�Ƿ�ȫ�����䣩
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/isEqualsCount.json")
//    @ResponseBody
//    public HttpResponse isEqualsCount(HttpServletRequest request, HttpServletResponse response) {
//        log.debug("��������������ǰ���鷽��,��ǰ������:isEqualsCount");
//        HttpResponse ret;
//        try {
//            Integer sheetId = request.getParameter("sid") == null ? 0 : Integer.parseInt(request.getParameter("sid"));
//            int result = this.service.isEqualsCount(sheetId);
//            if (result == 1) {
//                ret = new HttpResponse(null);
//            } else {
//                ret = new HttpResponse(HttpResponse.Status.FAILURE, "����ϸû�з����λ�������ύ��", null);
//            }
//        } catch (Exception e) {
//            log.error("����������ǰ���鷽���쳣");
//            log.errorPrintStacktrace(e);
//            ret = new HttpResponse(HttpResponse.Status.FAILURE, "�����쳣:" + e.getMessage(), null);
//        }
//        return ret;
//    }

    /**
     * ���ʽ���ɾ����ϸ����
     *
     * @param condition
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/deleteSheetRkDetails.json", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse deleteDetails(SheetDetailCondition condition, HttpServletRequest request, HttpServletResponse
            response)
            throws IOException {
        log.debug("�����������ɾ����ϸ����,��ǰ������:deleteDetails");
        HttpResponse ret;
        try {
            //��ȡ��ǰ��¼����Ϣ
            HttpSession session = request.getSession();
            Object requestUserIp = session.getAttribute("userIp");
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            String userIp = (null == requestUserIp ? null : requestUserIp.toString());

            this.sheetDetailService.delRKDetails(condition);
            this.sheetRKDETAILService.deleteSonDetailByIds(condition.getIds());
            String str = logService.objectToJson(condition.getIds());
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_STORAGE.getCode(),
                    SystemLogEnums.LogAction.DELETE.getCode(), "ɾ�����������ϸ:" + str, userIp, userId);
            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("ɾ�����ʽ�����ϸʧ��");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ������:" + e.getMessage(), null);
        }
        return ret;
    }


}

	
