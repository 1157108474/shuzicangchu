package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.BeanUtils;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.apply.ManageApply;
import com.zthc.ewms.sheet.entity.ck.*;
import com.zthc.ewms.sheet.entity.db.DbDetail;
import com.zthc.ewms.sheet.entity.enums.RenewalCostEnum;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.service.SheetApplyService;
import com.zthc.ewms.sheet.service.SheetCKService;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.sheet.service.SheetService;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.applyDep.entity.ApplyDep;
import com.zthc.ewms.system.applyDep.service.ApplyDepService;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.service.FormTemplateService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.service.UserService;
import drk.system.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/sheet/ck")
public class SheetCKController extends SheetControllerGuard {

    @Resource(name = "sheetCKService")
    public SheetCKService sheetCKService;


    private final static Log log = Log.getLog("com.hckj.ewms.sheet.controller.SheetCKControllerGuard");
    @Resource(name = "dictionaryService")
    public DictionaryService dictionaryService;
    @Resource(name = "sheetService")
    public SheetService sheetService;
    @Autowired
    SheetApplyService sheetApplyService;
    @Resource(name = "applyDepService")
    public ApplyDepService applyDepService;
    @Resource(name = "activitiService")
    public ActivitiService activitiService;
    @Resource(name = "sheetDetailService")
    public SheetDetailService sheetDetailService;

    @Resource(name = "dictionaryService")
    public DictionaryService dicService;
    @Resource(name = "formTemplateService")
    public FormTemplateService formTemplateService;
    @Resource(name = "userService")
    public UserService userService;

    @Resource(name = "logService")
    public LogService logService;


    /**
     * �������ʳ��ⵥҳ��
     */
    @RequestMapping(value = "/sheetCK.htm")
    public String sheetCK(ManageCK obj, HttpServletRequest request, Model model) {
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        Integer userId = 0, ztId = 0, useDepId = 0;
        String userName = "", useDepName = "", departName = "",userCode = "";
        if (null != userIdStr) {
            userCode = session.getAttribute("userCode").toString();

            userId = Integer.valueOf(userIdStr.toString());
            userName = session.getAttribute("userName").toString();
            useDepId = Integer.valueOf(session.getAttribute("useDepId").toString());
            useDepName = session.getAttribute("useDepName").toString();
            departName = session.getAttribute("departName").toString();
            ztId = Integer.valueOf(session.getAttribute("ztId").toString());
        }

        if (null == obj.getId()) {
            //		obj.setDepName(departName);
            obj.setPersonName(userName);
            //		obj.setUseUnitName(useDepName);
            obj.setOrgName(departName);
            obj.setExtendString2(departName);
            obj.setZtId(ztId);
        }
        //��ȡ�ֵ�
        //��ȡ���쵥λ�б�
        List<ApplyDep> applyDeps = this.applyDepService.listApplyDepZt(ztId);
        model.addAttribute("applyDeps", applyDeps);
        //�ʽ���Դ
        List<Dictionary> fundSources = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());
        model.addAttribute("fundSources", fundSources);
        //��������
        List<Dictionary> ckTypes = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.CKTYPE.getCode());
        model.addAttribute("ckTypes", ckTypes);
        //��ȡ�˵�code
        String menuCode = request.getParameter("menuCode");
        model.addAttribute("menuCode", menuCode);
        //���һ���ڰ�ť
        List<Dictionary> dictionaries = activitiService.getPartButton(menuCode,userCode);//���һ���ڰ�ť
        model.addAttribute("buttons", dictionaries);
        obj.setCreateDateStr(new DateTime().toString("yyyy��MM��dd�� HH:mm:ss"));
        model.addAttribute("sheet", obj);
        return "sheet/ck/sheetCK";
    }


    /**
     * ҳ��-��ϸ�б�
     */
    @RequestMapping(value = "/listSheetCK.json")
    @ResponseBody
    public LayuiPage<CK> listSheetCK(CK obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<CK> ret = null;
        String queryCriteria = "";
        //��ѯ����
        Map<String, Object> param = new HashMap<>();
        //����ID
        queryCriteria += " and sheetId = :sheetId ";
        param.put("sheetId", obj.getSheetId());

        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by sheetId asc");
        try {
            ret = this.sheetCKService.publicDetails("CK", "sheetId", param, condition);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �޸ķ���
     *
     * @param id
     * @param condition
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {

        //��ȡ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        ManageCK sheet = this.sheetService.getCkSheetOne(id);


        //��ȡ�ֵ�
        //��ȡ���쵥λ�б�
        List<ApplyDep> applyDeps = this.applyDepService.listApplyDepZt(sheet.getZtId());
        model.addAttribute("applyDeps", applyDeps);
        //�ʽ���Դ
        List<Dictionary> fundSources = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());
        model.addAttribute("fundSources", fundSources);
        //��������
        List<Dictionary> ckTypes = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.CKTYPE.getCode());
        model.addAttribute("ckTypes", ckTypes);
        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy��MM��dd�� HH:mm:ss"));

        model.addAttribute("loginUserId", userId);
        model.addAttribute("sheet", sheet);
        return this.getUrl(request, sheet.getRouteId().intValue() + "", model, "sheet/ck/sheetCK", sheet.getStatus());
    }


    /**
     * ������������
     */
    @RequestMapping(value = "/saveSheetCK.json")
    @ResponseBody
    public HttpResponse saveSheetCK(String type, SheetCK obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("��������" + type);
        HttpResponse ret;
        try {
            String menuCode = request.getParameter("menuCode");
            Dictionary dic = this.dicService.getDictionaryByType(type);
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

                        ManageApply apply = sheetApplyService.getApplyOne(obj.getExtendint1());
                        obj.setFundssource(apply.getFundsSource());
                        obj.setUseddepartid(apply.getUsedDepartId());
                        obj.setExtendint1(apply.getId());
                        obj.setApplydepartid(apply.getApplyDepartId());
                        //��������
                        List<Dictionary> ckTypes = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.CKTYPE.getCode());
                        String typeName = request.getParameter("typeName");
                        for (Dictionary ckType : ckTypes) {
                            if (ckType.getName().equals(typeName)) {
                                obj.setTypeid(ckType.getId());
                                break;
                            }
                        }
                        User user = this.userService.getUserOne(userId);
                        if (null != user) {
                            ztId = user.getZtId();
                            userCode = user.getCode();
                            Depart depart = user.getParent();
                            if (null != depart) {
                                departId = depart.getId();
                                obj.setExtendstring2(depart.getName());
                            }
                          /*  UseDep offices = user.getOffices();
                            if (null != offices) {
                                obj.setApplydepartid(offices.getId());
                            }*/
                        } else {
                            return new HttpResponse(HttpResponse.Status.FAILURE, "�û�������" + userId, null);
                        }
                        Integer useddepartid = Integer.valueOf(request.getParameter("useddepartid"));
                        obj.setUseddepartid(useddepartid);
                    } else {
                        if (session.getAttribute("userId") != null) {
                            userId = Integer.parseInt(session.getAttribute("userId").toString());
                            departId = Integer.parseInt(session.getAttribute("departId").toString());
                            ztId = Integer.parseInt(session.getAttribute("ztId").toString());
                            userIp = session.getAttribute("userIp").toString();
                            userCode = session.getAttribute("userCode").toString();
                        }
                    }
                    obj.setDepartid(departId);
                    obj.setGuid(java.util.UUID.randomUUID().toString());
                    obj.setName(formTemplate.getFormTemName());
                    obj.setKindid(dic.getId());
                    obj.setStatus(DictionaryEnums.Status.SHEETING.getCode());
                    if (!type.equals(SystemLogEnums.LogObject.MATERIAL_RETURN.getType())) {
                        obj.setZtid(ztId);
                    }
                    obj.setCreator(userId);
                    obj.setCreatedate(new Date());
//                    obj.setId(1111);
//                    obj.setCode("11111");
                    //���³ɱ�
                    obj.setExtendint3(RenewalCostEnum.UPDATE.getRenewalCost());
                    this.sheetService.saveSheetCK(obj, menuCode, type, userCode, condition);
                    logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums
                                    .LogAction.ADD.getCode(),
                            "��������:" + obj.getName(), userIp, userId);
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS, "���ݱ���ɹ���", obj);
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
     * �����޸� ���ƿ���λ/����/�˻�/�˿⣩
     */
    @RequestMapping(method = RequestMethod.PUT, value = "{type}/{id}")
    @ResponseBody
    public HttpResponse saveOrUpdate(@PathVariable("type") String type, @PathVariable("id") Integer id, HttpServletRequest request, Model model) {

        HttpResponse ret;
        log.debug("�޸��ύ" + type + id);
        Integer userId = 0;
        String userIp = "";
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
            userIp = session.getAttribute("userIp").toString();
        }
        String memo = request.getParameter("memo");
        String extendString1 = request.getParameter("extendString1");
        try {
            this.sheetCKService.updateSheetCK(id, memo, extendString1, userId);
            logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums.LogAction.EDIT.getCode(),
                    "�޸�:" + id.intValue(), userIp, userId);
            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }


    /**
     * ���ʳ��ⵥ����ҳ��
     */
    @RequestMapping(value = "/manageSheetCK.htm")
    public String manageSheetCK(ManageCK obj, HttpServletRequest request, Model model) {

        //��ȡ��¼�ˡ���¼��Ip
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
        if (null == obj.getId()) {
            obj.setPersonName(userName);
            obj.setCreator(userId);
            obj.setOrgName(departName);
            obj.setExtendString2(departName);
            obj.setZtId(ztId);
        }
        List<Dictionary> cktypes = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.CKTYPE.getCode());
        model.addAttribute("cktypes", cktypes);
        List<Dictionary> receiptTypes = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.ReceiptStatus.getCode());
        model.addAttribute("receiptTypes", receiptTypes);

        model.addAttribute("sheet", obj);
        return "sheet/ck/manageSheetCK";
    }

    /**
     * ����ҳ�浥���б�
     */
    @RequestMapping(value = "/listManageCK.json")
    @ResponseBody
    public LayuiPage<ManageCK> listManageCK(ManageCK obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<ManageCK> ret = null;
        //��ѯ����
        String queryCriteria = "";
        Map<String, Object> param = new HashMap<>();

        //code
        if (!StringUtils.isEmpty(obj.getCode())) {
            queryCriteria += " and code like :code";
            param.put("code", "%" + obj.getCode() + "%");
        }
        //������
        if (!StringUtils.isEmpty(obj.getCreator()) && (obj.getCreator() != 0)) {
            queryCriteria += " and creator = :creator";
            param.put("creator", obj.getCreator());
        }
        //ZTID
        if (!StringUtils.isEmpty(obj.getZtId()) && (obj.getZtId() != 0)) {
            queryCriteria += " and ztId = :ztId";
            param.put("ztId", obj.getZtId());
        }
        //�Ƿ���³ɱ�
        if (!StringUtils.isEmpty(obj.getExtendint3()) ) {
            if(obj.getExtendint3()==RenewalCostEnum.UPDATE.getRenewalCost()){
                queryCriteria += " and (extendint3 is null or extendint3 = :extendint3)";
            }else {
                queryCriteria += " and extendint3 = :extendint3";
            }
            param.put("extendint3", obj.getExtendint3());
        }
        //����״̬
        if (!StringUtils.isEmpty(obj.getStatus()) && obj.getStatus() != 0) {
            queryCriteria += " and status = :status";
            param.put("status", obj.getStatus());
        }
        //��ʼʱ��
        if (!StringUtils.isEmpty(condition.getBeginDate())) {
            queryCriteria += " and createDate >= to_date('" + condition.getBeginDate().trim() + "','yyyy-MM-dd') ";
        }
        //����ʱ��
        if (!StringUtils.isEmpty(condition.getEndDate())) {
            queryCriteria += " and createDate <= to_date('" + condition.getEndDate().trim() + " 23:59:59','yyyy-MM-dd HH24:MI:SS') ";
        }
        //��������
        if (!StringUtils.isEmpty(obj.getTypeId()) && obj.getTypeId() != 0) {
            queryCriteria += " and typeId = :typeId";
            param.put("typeId", obj.getTypeId());
        }
        String data = request.getParameter("appFlag");
        if (data != null && "1".equals(data)) {
            //��ѯ�������������
            Dictionary slck = this.dictionaryService.getDictionaryByCode("slck");
            queryCriteria += " and typeId = :typeId";
            param.put("typeId", slck.getId());
        }


        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by createDate desc");
        try {
            ret = this.sheetCKService.publicDetails("ManageCK", "id", param, condition);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ɾ������
     */
    @RequestMapping(value = "/delSheetCk.json")
    @ResponseBody
    public HttpResponse delSheetCk(Sheet obj, HttpServletRequest request) {
        HttpResponse<Sheet> ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ������ʧ�ܣ�", obj);
        try {
            this.sheetCKService.delSheetCK(obj.getId());
            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ�����ݳɹ���", obj);
        } catch (Exception e) {
            log.error("ɾ������ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ����ֱ�ӳ�����ϸҳ��
     */
    @RequestMapping(value = "/openZJCKDetailWindow.htm")
    public String openZJCKDetailWindow(Integer ztId, Model model) {
        model.addAttribute("ztId", ztId);
        return "sheet/ck/zjCKDetailed";
    }

    /**
     * �������������ϸҳ��
     */
    @RequestMapping(value = "/openSLCKDetailWindow.htm")
    public String openSLCKDetailWindow(Integer ztId, String code, Model model) {
        model.addAttribute("ztId", ztId);
        model.addAttribute("code", code);
        return "sheet/ck/applyCKDetailed";
    }

    /**
     * ���������ϸ�б�
     */
    @RequestMapping(value = "/listSQCKDetail.json")
    @ResponseBody
    public LayuiPage<VSQCKList> listSQCKDetail(VSQCKList obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ��ϸ�б���");
        LayuiPage<VSQCKList> ret = null;
        //��ѯ����
        String queryCriteria = " and allowCount >0 ";
        Map<String, Object> param = new HashMap<>();
//		//ZTID
//		if (!StringUtils.isEmpty(obj.getZtId())) {
//			queryCriteria += " and ztId = :ztId";
//			param.put("ztId", obj.getZtId());
//		}
        //���쵥��
        if (!StringUtils.isEmpty(obj.getCode())) {
            queryCriteria += " and code like :code";
            param.put("code", "%%" + obj.getCode().trim() + "%%");
        }
        //���ϱ���
        if (!StringUtils.isEmpty(obj.getMaterialCode())) {
            queryCriteria += " and materialCode like :materialCode";
            param.put("materialCode", "%%" + obj.getMaterialCode().trim() + "%%");
        }
        //��������
        if (!StringUtils.isEmpty(obj.getDescription())) {
            queryCriteria += " and description like :description";
            param.put("description", "%%" + obj.getDescription().trim() + "%%");
        }

        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by id asc");
        try {
            ret = this.sheetCKService.publicDetails("VSQCKList", "id", param, condition);
        } catch (Exception e) {
            log.error("��ȡ��ϸ�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��������Ϣ�б�
     */
    @RequestMapping(value = "/listCldetails.json")
    @ResponseBody
    public LayuiPage<VCKList> listCldetails(VCKList obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("���������������Ϣ�б���");
        LayuiPage<VCKList> ret = null;
        //��ѯ����
        String queryCriteria = " and unsecount>0 and storecount>0 ";
        Map<String, Object> param = new HashMap<>();

        //��λCode
        if (!StringUtils.isEmpty(obj.getStorelocationcode())) {
            queryCriteria += " and storelocationcode like :storelocationcode";
            param.put("storelocationcode", "%" + obj.getStorelocationcode() + "%");
        }

        //�ⷿ
        if (!StringUtils.isEmpty(obj.getStoreId())) {
            queryCriteria += " and storeId = :storeId";
            param.put("storeId", obj.getStoreId());
        }
        //�ⷿ
        if (!StringUtils.isEmpty(obj.getStorelocationname())) {
            queryCriteria += " and storelocationname like :storelocationname";
            param.put("storelocationname", "%" + obj.getStorelocationname() + "%");
        }
        //ZTID
        if (!StringUtils.isEmpty(obj.getZtId())) {
            queryCriteria += " and ztId = :ztId";
            param.put("ztId", obj.getZtId());
        }
        //���ϱ���
        if (!StringUtils.isEmpty(obj.getMaterialCode())) {
            queryCriteria += " and materialCode like :materialCode";
            param.put("materialCode", "%" + obj.getMaterialCode().trim() + "%");

        }
            /*//���ϵ���
            if (!StringUtils.isEmpty(obj.getOrdeNnum())) {
				queryCriteria += " and ordeNnum like :ordeNnum";
				param.put("ordeNnum", "%%" + obj.getOrdeNnum().trim() + "%%");
			}*/
        //����
            /*if (!StringUtils.isEmpty(obj.getType())) {
                queryCriteria += " and CANUSECOUNT>0";
			}*/
        String str = " and ownerType=708 ";
        condition.setQueryCriteria(queryCriteria + str);
        condition.setOrderBys(" order by createDate asc");
        try {
            ret = this.sheetCKService.publicDetails("VCKList", "id", param, condition);
            if (ret.getData().size() == 0) {
                str = " and ownerType=610 ";
                condition.setQueryCriteria(queryCriteria + str);
                ret = this.sheetCKService.publicDetails("VCKList", "id", param, condition);
                if (ret.getData().size() == 0) {
                    str = " and ownerType=609 ";
                    condition.setQueryCriteria(queryCriteria + str);
                    ret = this.sheetCKService.publicDetails("VCKList", "id", param, condition);
                }
            }
        } catch (Exception e) {
            log.error("��ȡ�����������Ϣ�б����");
            log.errorPrintStacktrace(e);
        }
        return ret;

    }


    /**
     * �������������ϸҳ��
     */
    @RequestMapping(value = "/openDBCKDetailWindow.htm")
    public String openDBCKDetailWindow(Integer ztId, String code, Model model) {
        model.addAttribute("ztId", ztId);
        model.addAttribute("code", code);
        return "sheet/ck/dbCKDetailed";
    }

    /**
     * ���������б�
     */
    @RequestMapping(value = "/listDbDetails.json")
    @ResponseBody
    public LayuiPage<DbDetail> listDbDetails(DbDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("��������б���");
        LayuiPage<DbDetail> ret = null;
        //��ѯ����
        String queryCriteria = " ";
        Map<String, Object> param = new HashMap<>();
        //����Id
        queryCriteria += " and code = :code";
        param.put("code", obj.getCode());
        //���ϱ���
        if (!StringUtils.isEmpty(obj.getMaterialCode())) {
            queryCriteria += " and materialCode like :materialCode";
            param.put("materialCode", "%%" + obj.getMaterialCode() + "%%");
        }
        //��������
        if (!StringUtils.isEmpty(obj.getDescription())) {
            queryCriteria += " and description like :description";
            param.put("description", "%%" + obj.getDescription().trim() + "%%");
        }
        //����
        /*if (!StringUtils.isEmpty(obj.getType())) {
            queryCriteria += " and CANUSECOUNT>0";
		}*/

        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by id asc");
        try {
            ret = this.sheetCKService.publicDetails("DbDetail", "id", param, condition);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * �����ϸ
     */
    @RequestMapping(value = "/saveDetail.json")
    @ResponseBody
    public HttpResponse saveDetail(HttpServletRequest request) {
        Date now = new Date();

        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        int userId = (null == userIdStr ? 0 : Integer.parseInt(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());


        List<SheetCKDETAIL> detailList = new ArrayList<>();
        String appFlag = request.getParameter("appFlag");
        if (appFlag != null && "1".equals(appFlag)) {
            SheetCKDETAIL detail = new SheetCKDETAIL();
            userId = Integer.valueOf(request.getParameter("userId"));
            int sheetId = Integer.valueOf(request.getParameter("sheetId"));
            int sheetDetailId = Integer.valueOf(request.getParameter("sheetDetailId"));
            int ztId = Integer.valueOf(request.getParameter("ztId"));
            int extendInt5 = Integer.valueOf(request.getParameter("extendInt5"));
            String materialCode = request.getParameter("materialCode");
            int extendint2 = Integer.valueOf(request.getParameter("extendint2"));
            Double detailCount = Double.valueOf(request.getParameter("detailCount"));
            String sncode = request.getParameter("sncode");
            detail.setExtendstring1(sncode);
            detail.setSheetId(sheetId);
            detail.setSheetDetailId(sheetDetailId);
            detail.setZtId(ztId);
            detail.setMaterialCode(materialCode);
            detail.setExtendint2(extendint2);
            detail.setDetailCount(detailCount);
            detail.setExtendInt5(extendInt5);

            VCKList vckList = this.sheetCKService.getVCKListOne(extendint2);
            detail.setCategoryId(vckList.getCategoryid());
            detail.setMaterialId(vckList.getMaterialid());
            detail.setMaterialName(vckList.getMaterialName());
            detail.setMaterialBrand(vckList.getMaterialbrand());
            detail.setMaterialModel(vckList.getMaterialModel());
            detail.setMaterialSpecification(vckList.getMaterialspecification());
            detail.setDetailUnitName(vckList.getDetailunit());
            detail.setStoreId(vckList.getStoreId());
            detail.setStoreLocationId(vckList.getStorelocationid());
            detail.setStoreLocationName(vckList.getStorelocationareaname());
            detail.setStoreLocationCode(vckList.getStorelocationcode());
            detail.setDescription(vckList.getDescription());
            detail.setTagCode(vckList.getTagcode());
            detail.setPlanDepartId(vckList.getPlandepartid());
            detail.setNoTaxPrice(0.0);
            detail.setTaxPrice(0.0);
            detail.setTaxRate(0.0);
            detail.setProviderDepId(vckList.getProviderdepid());
            detail.setDetailUnitName(vckList.getDetailUnitName());
            detail.setOwnerType(vckList.getOwnerType());

            detail.setGuid(java.util.UUID.randomUUID().toString());
            detail.setCreator(userId);
            detail.setCreatedate(now);
            detailList.add(detail);
        } else {
            JSONArray detailJson = JSONArray.fromObject(request.getParameter("details"));
            Collection collection = JSONArray.toCollection(detailJson);
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                SheetCKDETAIL detail = new SheetCKDETAIL();
                JSONObject jsonObj = JSONObject.fromObject(it.next());
                detail = (SheetCKDETAIL) JSONObject.toBean(jsonObj, SheetCKDETAIL.class);
                VCKList vckList = this.sheetCKService.getVCKListOne(detail.getExtendint2());
                if (null != vckList) {
                    detail.setCategoryId(vckList.getCategoryid());
                    detail.setMaterialId(vckList.getMaterialid());
                    detail.setMaterialName(vckList.getMaterialName());
                    detail.setMaterialBrand(vckList.getMaterialbrand());
                    detail.setMaterialSpecification(vckList.getMaterialspecification());
                    detail.setDetailUnitName(vckList.getDetailunit());
                    detail.setStoreId(vckList.getStoreId());
                    detail.setStoreLocationId(vckList.getStorelocationid());
                    detail.setStoreLocationName(vckList.getStorelocationareaname());
                    detail.setStoreLocationCode(vckList.getStorelocationcode());
                    detail.setDescription(vckList.getDescription());
                    detail.setTagCode(vckList.getTagcode());
                    detail.setPlanDepartId(vckList.getPlandepartid());
                    detail.setNoTaxPrice(0.0);
                    detail.setTaxPrice(0.0);
                    detail.setTaxRate(0.0);
                    detail.setProviderDepId(vckList.getProviderdepid());
                    detail.setDetailUnitName(vckList.getDetailUnitName());
                    detail.setOwnerType(vckList.getOwnerType());
                } else {
                    return new HttpResponse(HttpResponse.Status.FAILURE, "�����ϸʧ��,ѡ����Id����,extendint2��" + detail.getExtendint2(), null);

                }
                detail.setGuid(java.util.UUID.randomUUID().toString());
                detail.setCreator(userId);
                detail.setCreatedate(now);
                detailList.add(detail);
            }
        }

        try {
            //TODO ���ù��������ϸ����
            this.sheetCKService.saveSheetCkDetail(detailList);
            logService.addSystemLog(1, SystemLogEnums.LogObject.getByType("CKD").getCode(), SystemLogEnums
                            .LogAction.ADD_DETAIL.getCode(),
                    "sheet_CK" + "������ϸ:", userIp, userId);
            //���÷��ز���
            return new HttpResponse(HttpResponse.Status.SUCCESS, "�����ϸ�ɹ���", null);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            log.errorPrintStacktrace(e);
           return new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        } catch (Exception e) {
            log.error("�����ϸ:ʧ�ܣ�");
            log.errorPrintStacktrace(e);
            return new HttpResponse(HttpResponse.Status.FAILURE, "�����ϸʧ�ܣ�", null);
        }
    }

    /**
     * �𵥷���
     */
    @RequestMapping(value = "/splitSheet.json")
    @ResponseBody
    public HttpResponse splitSheet(Integer sheetId, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ʧ�ܣ�", null);
        List<SheetCKDETAIL> list = new ArrayList<SheetCKDETAIL>();
        List<SheetCKDETAIL> list2 = new ArrayList<SheetCKDETAIL>();
        List<SheetCKDETAIL> list3 = new ArrayList<SheetCKDETAIL>();
        try {
            List<SheetCKDETAIL> sheetCKDETAILS = this.sheetCKService.listSheetCKdetail(sheetId);
            SheetCK sheetCK = this.sheetCKService.getSheetCKOne(sheetId);
            for (SheetCKDETAIL detail : sheetCKDETAILS) {
                switch (detail.getOwnerType()) {
                    case 0x261:
                        list3.add(detail);
                        break;
                    case 610:
                        list.add(detail);
                        break;
                    case 0x2c4:
                        list2.add(detail);
                        break;
                }
            }
            if (list.isEmpty() && list2.isEmpty() && list3.isEmpty()) {
                return new HttpResponse(HttpResponse.Status.FAILURE, "�˵������", null);
            }
            if (list3.size() > 0) {
                this.assemblingSheet("����", list3, sheetCK, request);
            }
            if (list.size() > 0) {
                this.assemblingSheet("����", list, sheetCK, request);
            }
            if (list2.size() > 0) {
                this.assemblingSheet("��Ʒ", list2, sheetCK, request);
            }
            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�𵥳ɹ���", null);
        } catch (Exception e) {
            log.error("��ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    //��
    public void assemblingSheet(String name, List<SheetCKDETAIL> list, SheetCK sheetCK, HttpServletRequest request) {
        int num = this.createSheet(name, sheetCK, request);
        for (SheetCKDETAIL detail : list) {
            detail.setSheetId(num);
            this.sheetCKService.updateSheetCKdetail(detail);
        }
    }

    //��
    public int createSheet(String sheetName, SheetCK ck, HttpServletRequest request) {
        SheetCK sheetCK = new SheetCK();
        BeanUtils.copyProperties(ck, sheetCK);
        try {
            String data = request.getParameter("appFlag");
            if (null != data && "1".equals(data)) {
                sheetCK.setId(null);
            } else {
                JSONObject detailJson = JSONObject.fromObject(request.getParameter("details"));
                sheetCK = (SheetCK) JSONObject.toBean(detailJson, SheetCK.class);
            }
            String menuCode = "sheet_CK";
            Dictionary dic = this.dicService.getDictionaryByTypeCK("CKD");
            if (dic == null) {
                return -1;
                // ret = new HttpResponse(HttpResponse.Status.FAILURE, "�����ֵ��в��Ҳ�����Ӧ����", null);
            } else {
                List<FormTemplate> formTemplates = this.formTemplateService.getFromTemBydicIDCk(dic.getId().toString());
                if (formTemplates == null || formTemplates.size() != 1) {
                    return -1;
                    //  ret = new HttpResponse(HttpResponse.Status.FAILURE, "δ���õ���", null);
                } else {
                    FormTemplate formTemplate = formTemplates.get(0);
                    Integer userId = 0;
                    Integer departId = 0;
                    Integer ztId = 0;
                    String userIp = "";
                    String userCode = "80000179";
                    HttpSession session = request.getSession();

                    boolean appFlag = false;
                    if (data != null && "1".equals(data)) {
                        appFlag = true;
                        userId = Integer.parseInt(request.getParameter("userId"));
                        userIp = "app";
                        //��������
                        List<Dictionary> ckTypes = this.dictionaryService.getDicListByParentIdCK(DictionaryEnums.DicType.CKTYPE.getCode());
                        String typeName = request.getParameter("typeName");
                        for (Dictionary ckType : ckTypes) {
                            if (ckType.getName().equals(typeName)) {
                                sheetCK.setTypeid(ckType.getId());
                                break;
                            }
                        }
                        User user = this.userService.getUserOneCK(userId);
                        if (null != user) {
                            ztId = user.getZtId();
                            userCode = user.getCode();
                            Depart depart = user.getParent();
                            if (null == depart) {
                                departId = depart.getId();
                            }
                        } else {
//                            return new HttpResponse(HttpResponse.Status.FAILURE, "�û�������" + userId, null);
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
                    sheetCK.setDepartid(departId);
                    sheetCK.setGuid(java.util.UUID.randomUUID().toString());
                    sheetCK.setName(sheetName + formTemplate.getFormTemName());
                    sheetCK.setKindid(dic.getId());
                    sheetCK.setStatus(DictionaryEnums.Status.SHEETING.getCode());
                    if (!"sheet_CK".equals(SystemLogEnums.LogObject.MATERIAL_RETURN.getType())) {
                        sheetCK.setZtid(ztId);
                    }
                    sheetCK.setCreator(userId);
                    sheetCK.setCreatedate(new Date());
//                    obj.setId(1111);
//                    obj.setCode("11111");
                    this.sheetService.saveSheetCK(sheetCK, menuCode, "sheet_CK", userCode, null);
                    logService.addSystemLog(1, SystemLogEnums.LogObject.getByType("CKD").getCode(),
                            SystemLogEnums.LogAction.ADD.getCode(), "��������:" + sheetCK.getName(), userIp, userId);
//                    ret = new HttpResponse(HttpResponse.Status.SUCCESS, "���ݱ���ɹ���", obj);
                }
            }
        } catch (Exception e) {
            log.error("�����ϸ:ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return sheetCK.getId();
    }

    /**
     * ������ⵥ��ҳ��
     */
    @RequestMapping(value = "/openCKOder.htm")
    public String openCKOder() {
        return "sheet/ck/CKOrder";
    }

    /**
     * ҳ��-��ϸ�б�
     */
    @RequestMapping(value = "/listCKOder.json")
    @ResponseBody
    public LayuiPage<CKNum> listCKOder(CKNum obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<CKNum> ret = null;
        String queryCriteria = "";
        //��ѯ����
        Map<String, Object> param = new HashMap<>();
        //code
        if (!StringUtils.isEmpty(obj.getCksheetCode())) {
            queryCriteria += " and cksheetCode = :cksheetCode";
            param.put("cksheetCode", obj.getCksheetCode());
        }
        //id
        if (!StringUtils.isEmpty(obj.getId())) {
            queryCriteria += " and id = :id";
            param.put("id", obj.getId());
        }
        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by id desc");
        try {
            ret = this.sheetCKService.publicDetails("CKNum", "id", param, condition);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ�Ƶ��еĳ��ⵥ����
     */
    @RequestMapping(value = "/listTouchingCK")
    @ResponseBody
    public LayuiPage<ManageCK> listTouchingCK(ManageCK obj, SheetCondition condition, HttpServletRequest request) {
        LayuiPage<ManageCK> ret = null;
        String queryCriteria = "and statusName = '�Ƶ���' ";
        //��ѯ����
        Map<String, Object> param = new HashMap<>();
        //code
//		if (!StringUtils.isEmpty(obj.getCode())) {
//			queryCriteria += " and code = :code";
//			param.put("code", obj.getCode());
//		}
        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by id desc");
        try {
            ret = this.sheetCKService.publicDetails("ManageCK", "id", param, condition);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ɾ����ϸ
     */
    @RequestMapping(value = "/delSheetDetails.json")
    @ResponseBody
    public HttpResponse<SheetCKDETAILCondition> delSheetDetails(SheetCKDETAILCondition condition, HttpServletRequest request) {
        HttpResponse<SheetCKDETAILCondition> ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ����ϸʧ�ܣ�", condition);
        try {
            this.sheetCKService.delDetails(condition);

            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ����ϸ�ɹ���", condition);
        } catch (Exception e) {
            log.error("ɾ����ϸʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ͬ������
     */
    @RequestMapping(value = "/postLLCKSheet.json")
    @ResponseBody
    public HttpResponse<Integer> postLLCKSheet(SheetCK obj, HttpServletRequest request) {

        HttpResponse<Integer> ret = new HttpResponse(HttpResponse.Status.FAILURE, "ͬ��ʧ�ܣ�", 1);
        int num = 0;
//        if (ConfigurationManager.AppSettings["UseVPN"].ToString() == "true") {
        try {
            SheetCK llData = this.sheetCKService.getSheetCKOne(obj.getId());
            if ((llData != null) && (llData.getTypeid() != 0x304)) {
                List<SheetCKDETAIL> detailList = this.sheetCKService.listSheetCKdetail(obj.getId());
                /*num = this.sheetCKService.pPushLLCKData(llData, detailList);*/
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ͬ���ɹ���", num);
            } else {
                num = 1;
            }
        } catch (Exception e) {
            log.error("ͬ��ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
//        } else {
//            ret = new HttpResponse(HttpResponse.Status.FAILURE, "ͬ��ʧ�ܣ�", num);
//
//        }
        return ret;
    }

    /**
     * ���³ɱ�
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/renewalCost.json")
    @ResponseBody
    public HttpResponse renewalCost(Integer id, HttpServletRequest request) {
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        SheetCK sheetCK = sheetCKService.getSheetCKOne(id);

        int userId = (null == userIdStr ? 0 : Integer.parseInt(userIdStr.toString()));
        try {
            sheetCKService.asynRenewalCost(sheetCK,userId);
            return new HttpResponse(HttpResponse.Status.SUCCESS, "���³ɹ���",null);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            return new HttpResponse(HttpResponse.Status.FAILURE, "����ʧ�ܣ�",null);
        }catch (Exception e) {
            log.error("����ʧ�ܣ�");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            return new HttpResponse(HttpResponse.Status.FAILURE, "���³ɹ���",null);
        }
    }

}

	
