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
     * �������ѯҳ��
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
     * �������ѯ��ϸҳ��
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
     * ������������ѯҳ��
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
     * �������ʳ����ѯҳ��
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

        // �����֯�б�
        List<Depart> departList = userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum
                .ORGANIZATION.getType());
        // �Ƿ����
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
     * ��������֧��ѯҳ��
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
     * ����Ĵ���Ͽ���ѯҳ��
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
     * ��ȡ����ѯ�б�
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
        log.debug("�����ȡ����ѯ�б���");
        //TODO:���ȡ��ǰ��¼�˲���ID��ѯ
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
            log.error("��ȡ����ѯ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    /**
     * �������Excel
     */
    @RequestMapping(value = "/exportKCExcel.htm")
    public void exportKCExcel(VKcdetailEntity obj, VKcdetailEntityCondition condition, HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<VKcdetailEntity> list = vKcdetailEntityService.KCExcelList(userId, obj, condition);
        String[] gridTitles = {"�����֯", "���ϱ���", "��������", "�ⷿ", "�Ƿ����", "��Ӧ��", "�������"};
        String[] coloumsKey = {"ztidname", "materialcode", "description", "housename", "ownertypename", "providername", "storecount"};
        String userName = "����";
        HSSFWorkbook workBook = new ExcelExport().getExcelContent(userName,list, gridTitles, coloumsKey);

        //���Excel�ļ�
        OutputStream output = response.getOutputStream();
        response.reset();

        String userAgent = request.getHeader("User-Agent");
        // ���IE������IEΪ�ں˵��������
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userName = java.net.URLEncoder.encode(userName, "UTF-8");
        } else {
            // ��IE������Ĵ���
            userName = new String(userName.getBytes("UTF-8"), "ISO-8859-1");
        }
        //��ӵ���ʱ��
        String dt = DateUtils.dateTimeNow();
        userName += dt;
        // ����response��Header
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", userName + ".xls"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        workBook.write(output);
        output.close();
    }
    /**
     * ��ȡ��������ѯ�б�
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
        log.debug("�����ȡ��������ѯ�б���");
        //TODO:���ȡ��ǰ��¼�˲���ID��ѯ
        LayuiPage<VRkcxEntity> ret = null;
        try {
            HttpSession session = request.getSession();
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            ret = vRkcxEntityService.RKCXList(userId, obj, condition);
        } catch (Exception e) {
            log.error("��ȡ��������ѯ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ���ʳ����ѯ�б�
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
        log.debug("�����ȡ���ʳ����ѯ�б���");
        //TODO:���ȡ��ǰ��¼�˲���ID��ѯ
        LayuiPage<VCkcxEntity> ret = null;
        try {
            HttpSession session = request.getSession();
            Object requestUserId = session.getAttribute("userId");
            Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
            ret = vCkcxEntityService.CKCXList(userId, obj, condition);
        } catch (Exception e) {
            log.error("��ȡ���ʳ����ѯ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * ��ȡ���ʿ����ϸ�б�
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
        log.debug("�����ȡ���ʿ����ϸ�б���,");
        //TODO:���ȡ��ǰ��¼�˲���ID��ѯ
        LayuiPage<VKcsubdetailEntity> ret = null;
        try {
            ret = vKcsubdetailEntityService.KCDetailList(obj, condition);
        } catch (Exception e) {
            log.error("��ȡ���ʿ����ϸ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * App��ȡ���ʿ����ϸ�б�
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
        log.debug("����APP��ȡ���ʿ����ϸ�б���");
        //TODO:���ȡ��ǰ��¼�˲���ID��ѯ
        LayuiPage<VKcsubdetailEntity> ret = null;
        try {
            String userId = request.getParameter("userId");//�ֻ��˲���userID
            String storelocationcode = request.getParameter("storelocationcode");
            String materialcode = request.getParameter("materialcode");
            ret = vKcsubdetailEntityService.KCDetailsList(userId, storelocationcode, materialcode, condition);
        } catch (Exception e) {
            log.error("��ȡAPP���ʿ����ϸ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ�����֧����
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
        log.debug("�����ȡ�����֧������");
        //TODO:���ȡ��ǰ��¼�˲���ID��ѯ
        LayuiPage ret = new LayuiPage();
        try {
            List<Map<String, Object>> list = kcszService.KCSZList(materialcode, materialname, storeid, condition);
            ret.setData(list);
            ret.setCount((long) list.size());
        } catch (Exception e) {
            log.error("��ȡ�����֧������ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ�Ĵ���Ͽ���ѯ�б�
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
        log.debug("�����ȡ�Ĵ���Ͽ���ѯ�б���");
        //TODO:���ȡ��ǰ��¼�˲���ID��ѯ
        LayuiPage<VJcstockEntity> ret = null;
        try {
            ret = vJcstockEntityService.JCCXList(obj, condition);
        } catch (Exception e) {
            log.error("��ȡ�Ĵ���Ͽ���ѯ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ�ɹ�������ѯ�б�
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
        log.debug("�����ȡ�ɹ�������ѯ�б���,��ǰ������:listQueryCGDD");
        LayuiPage<VCgddEntity> ret = null;

        //��ȡ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        try {
            ret = sheetCGService.CGDDList(obj, condition, userId, startTime, endTime);
        } catch (Exception e) {
            log.error("��ȡ�ɹ�������ѯ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �����ɹ�����Excel
     */
    @RequestMapping(value = "/exportCGDDExcel.htm")
    public void exportCGDDExcel(VCgddEntity obj, VCgddEntityCondition condition, String startTime, String endTime,
                                HttpServletRequest request, HttpServletResponse response) {
        //��ȡ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<VCgddEntity> list = sheetCGService.listCGDD(obj, condition, userId, startTime, endTime);

        String[] gridTitles = {"�ɹ��������", "���ź�", "��������", "�����֯����", "�����֯����", "���ϱ���", "��������",
                "��λ", "����", "����˰����", "��Ӧ��", "��������", "�Ƶ�����", "��������", "״̬"};
        String[] coloumsKey = {"ordernum", "ffcode", "ordertype", "stockorgcode", "stockorgname", "materialcode",
                "description", "detailunit", "detailcount", "notaxprice", "providerdepname", "consignmentname",
                "createdate", "updatedate", "status"};
        ExcelExport.doExcelExport("�ɹ�����.xls", list, gridTitles, coloumsKey, request, response);
    }

    /**
     * ��ȡ�ɹ��ƻ���ѯ�б�
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
        log.debug("�����ȡ�ɹ��ƻ���ѯ�б���,��ǰ������:listQueryCGJH");
        LayuiPage<VCgjhEntity> ret = null;
        //��ȡ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));

        try {
            ret = vCgjhEntityService.CGJHList(obj, condition, userId, startTime1, startTime2, endTime1, endTime2);
        } catch (Exception e) {
            log.error("��ȡ�ɹ��ƻ���ѯ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �����ɹ��ƻ�Excel
     */
    @RequestMapping(value = "/exportCGJHExcel.htm")
    public void exportCGJHExcel(VCgjhEntity obj, VCgjhEntityCondition condition, String startTime1, String startTime2,
                                String endTime1, String endTime2, HttpServletRequest request,
                                HttpServletResponse response) throws ParseException, IOException {
        List<VCgjhEntity> list = vCgjhEntityService.excelCGJHList(obj, condition, startTime1, startTime2, endTime1, endTime2);
        String[] gridTitles = {"�ƻ����", "���ϱ���", "��������", "�걨��λ", "ʹ�õ�λ", "����", "��λ", "�ƻ��ƶ�����",
                "��������", "״̬"};
        String[] coloumsKey = {"planCode", "materialCode", "materIaldes", "applyDepName", "useDepName", "count", "unIt",
                "createDate", "needDate", "status"};
        ExcelExport.doExcelExport("�ɹ��ƻ�.xls", list, gridTitles, coloumsKey, request, response);
    }

    @ResponseBody
    @RequestMapping(value = "/getDetailunitname.htm", produces = "text/html;charset=gbk")
    public String getDetailunitname(@ModelAttribute("VKcsubdetailEntity") VKcsubdetailEntity obj, VKcsubdetailEntityCondition condition,
                                    HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        return vKcsubdetailEntityService.getDetailunitname(obj, condition);
    }



    /**
     * �����������Excel
     */
    @RequestMapping(value = "/exportRKExcel.htm")
    public void exportRKExcel(@ModelAttribute("VRkcxEntity") VRkcxEntity obj, VRkcxEntityCondition condition,
                              HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException, IOException {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<VRkcxEntity> list = vRkcxEntityService.RKExcelList(userId, obj, condition);
        String[] gridTitles = {"���ݱ��", "���ϱ���", "��������", "���Ϲ��", "�����ͺ�", "��˰����", "�������", "�ⷿ", "��λ", "����˵��", "���ʱ��"};
        String[] coloumsKey = {"code", "materialcode", "materialname", "materialspecification",
                "materialmodel", "taxprice", "subdetailcount", "housename", "storelocationname", "description", "submittime"};
        String userName = "��������";
        HSSFWorkbook workBook = new ExcelExport().getExcelContent(userName,list, gridTitles, coloumsKey);

        //���Excel�ļ�
        OutputStream output = response.getOutputStream();
        response.reset();

        String userAgent = request.getHeader("User-Agent");
        // ���IE������IEΪ�ں˵��������
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userName = java.net.URLEncoder.encode(userName, "UTF-8");
        } else {
            // ��IE������Ĵ���
            userName = new String(userName.getBytes("UTF-8"), "ISO-8859-1");
        }
        //��ӵ���ʱ��
        String dt = DateUtils.dateTimeNow();
        userName += dt;
        // ����response��Header
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", userName + ".xls"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        workBook.write(output);
        output.close();
    }

    /**
     * �������ʳ���Excel
     */
    @RequestMapping(value = "/exportCKExcel.htm")
    public void exportCKExcel(@ModelAttribute("VCkcxEntity") VCkcxEntity obj, VCkcxEntityCondition condition,
                              HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException, IOException {
        HttpSession session = request.getSession();
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        List<VCkcxEntity> list = vCkcxEntityService.CKExcelList(userId, obj, condition);
        String[] gridTitles = {"���ݱ��", "���ϱ���", "��������", "���Ϲ��", "�����ͺ�", "���첿��", "��������", "����˵��"};
        String[] coloumsKey = {"code", "materialcode", "materialname", "materialspecification",
                "materialmodel", "usdedepname", "detailcount", "description"};

        String userName = "���ʳ����";

        HSSFWorkbook workBook = new ExcelExport().getExcelContent(userName,list, gridTitles, coloumsKey);

        //���Excel�ļ�
        OutputStream output = response.getOutputStream();
        response.reset();
        String userAgent = request.getHeader("User-Agent");
        // ���IE������IEΪ�ں˵��������
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userName = java.net.URLEncoder.encode(userName, "UTF-8");
        } else {
            // ��IE������Ĵ���
            userName = new String(userName.getBytes("UTF-8"), "ISO-8859-1");
        }
        //��ӵ���ʱ��
        String dt = DateUtils.dateTimeNow();
        userName += dt;
        // ����response��Header
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", userName + ".xls"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        workBook.write(output);
        output.close();
    }

    /**
     * ����������֧����Excel
     */
    @RequestMapping(value = "/exportKCSZExcel.htm")
    public void exportKCSZExcel(String materialcode, String materialname, String storeid, KcszCondition condition,
                                HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException, IOException, SQLException {
        List<Map<String, Object>> list = kcszService.KCSZList(materialcode, materialname, storeid, condition);
        String[] gridTitles = {"���ϱ���", "��������", "�ڳ��������", "�ڳ������", "�����������", "���������"
                , "���ڳ�������", "���ڳ�����", "��ĩ�������", "��ĩ�����"};
        String[] coloumsKey = {"materialcode", "materialname", "startcount", "startmoney", "bqrcount", "bqrkmoney"
                , "bqckcount", "bqckmoney", "qmcount", "qmmoney"};
        String userName = "�����֧����";

        HSSFWorkbook workBook = new ExcelExport().getExcelMapContent(userName,list, gridTitles, coloumsKey);

        //���Excel�ļ�
        OutputStream output = response.getOutputStream();
        response.reset();

        String userAgent = request.getHeader("User-Agent");
        // ���IE������IEΪ�ں˵��������
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userName = java.net.URLEncoder.encode(userName, "UTF-8");
        } else {
            // ��IE������Ĵ���
            userName = new String(userName.getBytes("UTF-8"), "ISO-8859-1");
        }
        //��ӵ���ʱ��
        String dt = DateUtils.dateTimeNow();
        userName += dt;
        // ����response��Header
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", userName + ".xls"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        workBook.write(output);
        output.close();
    }

    /**
     * ����ƻ���������
     */
    @RequestMapping(value = "/queryPlanDetail.htm")
    public String queryPlanDetail(PlanDetail obj, HttpServletRequest request, Model model) {


        return "sheet/query/queryPlanDetail";
    }

    /**
     * �ƻ��������б�
     */
    @RequestMapping(value = "/listPlanDetail.json")
    @ResponseBody
    public LayuiPage<PlanDetail> listPlanDetail(PlanDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<PlanDetail> ret = null;
        String queryCriteria = " and  storeuseCount>=0 ";
        //��ѯ����
        Map<String, Object> param = new HashMap<>();
        //��λId
        if (!StringUtils.isEmpty(obj.getUseDepId())) {
            queryCriteria += " and useDepId = :useDepId";
            param.put("useDepId", obj.getUseDepId());
        }
        //�ƻ����
        if (!StringUtils.isEmpty(obj.getDeptName())) {
            queryCriteria += " and deptName like :deptName";
            param.put("deptName", "%" + obj.getDeptName().trim() + "%");
        }
        //ZTID
        if (!StringUtils.isEmpty(obj.getZtId())) {
            queryCriteria += " and ztId = :ztId";
            param.put("ztId", obj.getZtId());
        }
        //�ƻ����
        if (!StringUtils.isEmpty(obj.getPlanCode())) {
            queryCriteria += " and planCode like :planCode";
            param.put("planCode", "%" + obj.getPlanCode().trim() + "%");
        }
        //���ϱ���
        if (!StringUtils.isEmpty(obj.getMaterialCode())) {
            queryCriteria += " and materialCode like :materialCode";
            param.put("materialCode", "%" + obj.getMaterialCode().trim() + "%");
        }
        //��������
        if (!StringUtils.isEmpty(obj.getMaterialDes())) {
            queryCriteria += " and materialDes like :materialDes";
            param.put("materialDes", "%" + obj.getMaterialDes().trim() + "%");
        }
        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by id desc");
        try {
            ret = this.sheetApplyService.publicDetails("PlanDetail", "id", param, condition);
        } catch (Exception e) {
            log.error("��ȡ�ƻ������������б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * �����ƻ����Excel
     */
    @RequestMapping(value = "/exportPlanExcel.htm")
    public void exportPlanExcel(PlanDetail obj, SheetDetailCondition condition, HttpServletRequest request,
                                HttpServletResponse response) throws ParseException, IOException {
        String queryCriteria = " and  storeuseCount>=0 ";
        //��ѯ����
        Map<String, Object> param = new HashMap<>();
        //��λId
        if (!StringUtils.isEmpty(obj.getUseDepId())) {
            queryCriteria += " and useDepId = :useDepId";
            param.put("useDepId", obj.getUseDepId());
        }
        //�ƻ����
        if (!StringUtils.isEmpty(obj.getDeptName())) {
            queryCriteria += " and deptName like :deptName";
            param.put("deptName", "%" + obj.getDeptName().trim() + "%");
        }
        //ZTID
        if (!StringUtils.isEmpty(obj.getZtId())) {
            queryCriteria += " and ztId = :ztId";
            param.put("ztId", obj.getZtId());
        }
        //�ƻ����
        if (!StringUtils.isEmpty(obj.getPlanCode())) {
            queryCriteria += " and planCode like :planCode";
            param.put("planCode", "%" + obj.getPlanCode().trim() + "%");
        }
        //���ϱ���
        if (!StringUtils.isEmpty(obj.getMaterialCode())) {
            queryCriteria += " and materialCode like :materialCode";
            param.put("materialCode", "%" + obj.getMaterialCode().trim() + "%");
        }
        //��������
        if (!StringUtils.isEmpty(obj.getMaterialDes())) {
            queryCriteria += " and materialDes like :materialDes";
            param.put("materialDes", "%" + obj.getMaterialDes().trim() + "%");
        }
        condition.setQueryCriteria(queryCriteria);
        condition.setOrderBys(" order by id desc");
        List<PlanDetail> list = this.sheetApplyService.listDetails("PlanDetail", param, condition);
        String[] gridTitles = {"�ƻ�����", "�ƻ����", "ʹ�õ�λ", "���ϱ���", "��������", "�ƻ�����", "����������",
                "�������", "����������", "��λ", "�ƻ�����"};
        String[] coloumsKey = {"deptName", "planCode", "userDepName", "materialCode", "materialDes", "planCount",
                "haveslCount", "storeCount", "storeuseCount", "unIt", "planDate"};
        ExcelExport.doExcelExport("�ƻ����.xls", list, gridTitles, coloumsKey, request, response);
    }

}
