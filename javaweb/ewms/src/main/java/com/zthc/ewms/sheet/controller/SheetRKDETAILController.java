package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetRKDETAILControllerGuard;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetRKDETAIL;
import com.zthc.ewms.sheet.entity.guard.SheetRkSonDetail;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.sheet.service.SheetService;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.service.UserService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.AppConfig;
import drk.system.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

;

@Controller
@RequestMapping("/sheet/rkDetail")
public class SheetRKDETAILController extends SheetRKDETAILControllerGuard {

    @Resource(name = "sheetDetailService")
    public SheetDetailService sheetDetailService;

    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;

    @Resource(name = "logService")
    public LogService logService;

    @Resource(name = "sheetService")
    public SheetService sheetService;

    @Resource(name = "dictionaryService")
    public DictionaryService dictionaryService;

    @Resource(name = "userService")
    public UserService userService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.sheet.controller.Sheet_RKDETAILController");
    }

    /*-------------------------------------------------��ת����-------------------------------------------------*/



    /*-------------------------------------------------��������-------------------------------------------------*/

    /**
     * ִ�з���_�����ϸ
     */
    @RequestMapping(value = "/add{type}Details", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse addDetails(@PathVariable("type") String type, HttpServletRequest request) {
        HttpResponse ret;
        log.debug("�����ϸ");
        int num6 = DictionaryEnums.JsType.IsJs.getCode();
        //��ȡĬ�Ͽ�λ����
        String defaultStoreHouse = AppConfig.getProperty("defaultStoreHouse");
        try {
            Integer userId = 0;
            String userIp = "";
            HttpSession session = request.getSession();
            String data = request.getParameter("appFlag");
            if (data != null && "1".equals(data)) {
                // �ֻ�APP �� �������
                Object userIdObj = request.getParameter("userId");
                Object uid = (null == userIdObj) ? session.getAttribute("userId") : userIdObj;
                userId = Integer.parseInt(uid.toString());
                userIp = "app";
                String rid = request.getParameter("rid");//��ⵥ��ID(SheetRK)
                String sid = request.getParameter("sid");//��������ID(Sheet)
                String rkId = request.getParameter("rkId");
                String dId = request.getParameter("detailId");

                List<SheetDetail> list;
                if (!StringUtils.isEmpty(rkId) && !StringUtils.isEmpty(sid)) {
                    list = this.sheetService.getDetaiList(Integer.parseInt(sid), Integer.parseInt(dId));
                } else {
                    list = this.sheetService.getDetailOne(Integer.parseInt(sid));
                }
                List<SheetRKDETAIL> detailList = new ArrayList<>();
                List<SheetRkSonDetail> sonList = new ArrayList<>();
                SheetRKDETAIL detail = new SheetRKDETAIL();
                WareHouse wareHouse;
                WareHouse ware;
                SheetRkSonDetail sonDetail = new SheetRkSonDetail();
                if (list.size() != 0 && null != list) {
                    if (!StringUtils.isEmpty(rid) && !StringUtils.isEmpty(sid)) {
                        userIp = session.getAttribute("userIp").toString();
                        //������ϸ֮ǰ��ɾ��rkDetail
                        this.service.deleteDetailBySheetId(Integer.parseInt(rid));
                        //������ϸ֮ǰ��ɾ��rkSubDetail
                        this.service.deleteSonDetailByDetailId(Integer.parseInt(rid));
                    }
                    for (int i = 0; i < list.size(); i++) {
                        User user = this.userService.getUserOne(userId);
                        if (null != user) {
                            detail.setZtid(user.getZtId());
                        } else {
                            return new HttpResponse(HttpResponse.Status.FAILURE, "�û���Ϣ����", null);
                        }
                        detail.setGuid(java.util.UUID.randomUUID().toString());
                        detail.setCreator(userId);
                        detail.setCreatedate(new Date());
                        if (!StringUtils.isEmpty(rkId)) {
                            detail.setSheetid(Integer.parseInt(rkId));
                        } else {
                            detail.setSheetid(Integer.parseInt(rid));
                        }
                        detail.setSheetdetailid(list.get(i).getId());
                        detail.setExtendint1(list.get(i).getSheetDetailId());
                        detail.setCategoryid(list.get(i).getCategoryId());
                        detail.setMaterialid(list.get(i).getMaterialId());
                        detail.setMaterialcode(list.get(i).getMaterialCode());
                        detail.setMaterialname(list.get(i).getMaterialName());
//                        detail.setMaterialbrand(list.get(i).getMaterialBrand());
                        detail.setMaterialmodel(list.get(i).getMaterialModel());
                        detail.setMaterialspecification(list.get(i).getMaterialSpecification());
                        detail.setNotaxprice(list.get(i).getNoTaxPrice());
                        detail.setNotaxsum(list.get(i).getNoTaxSum());
                        detail.setTaxsum(list.get(i).getTaxSum());
                        detail.setTaxRate(list.get(i).getTaxRate());
                        detail.setDescription(list.get(i).getDescription());
                        detail.setSncode(list.get(i).getSnCode());
                        detail.setTagcode(list.get(i).getTagCode());
                        detail.setDetailunitname(list.get(i).getDetailUnitName());
                        detail.setDetailcount(list.get(i).getDetailCount());
                        if (!StringUtils.isEmpty(rkId) && !StringUtils.isEmpty(sid)) {
                            if (StringUtils.isEmpty(list.get(i).getTaxRate())) {
                                list.get(i).setTaxRate(0.0);
                            }
                            detail.setDetailcount(Double.parseDouble(request.getParameter("detailCount")));
                            detail.setNotaxsum(Double.parseDouble(request.getParameter("detailCount")) * list.get(i)
                                    .getNoTaxPrice());
                            detail.setTaxsum(list.get(i).getNoTaxPrice() * (list.get(i).getTaxRate() + 1) * Double
                                    .parseDouble(request.getParameter("detailCount")));
                            detail.setExtendfloat1(list.get(i).getNoTaxPrice() * (list.get(i).getNoTaxPrice() + 1));
                        }
                        detail.setIsequipment(list.get(i).getIsEquipment());
                        detail.setEnablesn(list.get(i).getEnableSn());
                        detail.setOwnertype(list.get(i).getOwnerType());
                        detail.setProviderdepid(list.get(i).getProviderDepId());


                        detail.setExtendstring1(request.getParameter("extendstring1"));
                        detail.setExtendstring10(list.get(i).getExtendString10());
                        //����ⷿ
                        /*if (storeEntity.getCode().equals("IsJS")) {
                            detail.setExtendstring1("J001");
                        } else if (storeEntity.getCode().equals("NoJs")) {
                            if (list.get(i).getIsEquipment() == 1) {
                                detail.setExtendstring1("S001");
                            } else {
                                detail.setExtendstring1("C001");
                            }
                        } else if (storeEntity.getCode().equals("ZSP")) {
                            detail.setExtendstring1(list.get(i).getExtendString1());
                        }*/
                        if (data != null && "1".equals(data)) {
                            ware = this.wareHouseService.getWareHouseCode(request.getParameter("extendstring1"));
                            if (ware == null) {
                                return new HttpResponse(HttpResponse.Status.FAILURE, "ƥ�䲻���ⷿ", null);
                            } else {
                                detail.setStoreid(ware.getId());
                            }
                        }
                        if (detail.getOwnertype() == num6) {
                            WareHouse wareh = wareHouseService.getWareHouseName("���ۿ�");
                            if (wareh != null) {
                                detail.setStoreid(wareh.getId());
                                detail.setExtendstring1(wareh.getCode());
                            }
                        } else {
                            detail.setStoreid(list.get(i).getStoreId());
                            detail.setExtendstring1(request.getParameter("extendstring1"));
                        }
                        // ����ⷿ��λ
                        String appStoreCode;
                        if (!StringUtils.isEmpty(rkId) && !StringUtils.isEmpty(sid)) {

                            appStoreCode = request.getParameter("storeLocationCode");
                            if (!StringUtils.isEmpty(list.get(i).getStoreId())) {
                                wareHouse = this.wareHouseService.getWareHouseByCode(detail.getStoreid(), appStoreCode, null);
                                if (StringUtils.isEmpty(wareHouse)) {
                                    List<WareHouse> wareHouseList = wareHouseService.findByParentCode(detail.getExtendstring1());
                                    if (wareHouseList == null || wareHouseList.size() == 0) {
                                        WareHouse wareHouse1 = this.service.addWareHouse(defaultStoreHouse, detail.getStoreid(), detail.getZtid(), detail.getCreator());
                                        sonDetail.setStoreLocationCode(wareHouse1.getCode());
                                        sonDetail.setStoreLocationId(wareHouse1.getId());
                                        sonDetail.setStoreLocationName(wareHouse1.getName());
                                        sonDetail.setStoreID(wareHouse1.getParentId());
                                        detail.setStorelocationcode(wareHouse1.getCode());
                                        detail.setStorelocationid(wareHouse1.getId());
                                        detail.setStorelocationname(wareHouse1.getName());
                                    } else if (wareHouseList.size() == 1) {
                                        WareHouse wareHouse1 = wareHouseList.get(0);
                                        if (wareHouse.getName().equals(defaultStoreHouse)) {
                                            sonDetail.setStoreLocationCode(wareHouse1.getCode());
                                            sonDetail.setStoreLocationId(wareHouse1.getId());
                                            sonDetail.setStoreLocationName(wareHouse1.getName());
                                            sonDetail.setStoreID(wareHouse1.getParentId());
                                            detail.setStorelocationcode(wareHouse1.getCode());
                                            detail.setStorelocationid(wareHouse1.getId());
                                            detail.setStorelocationname(wareHouse1.getName());
                                        } else {
                                            return new HttpResponse(HttpResponse.Status.FAILURE, "��ǰ�ⷿ�²����ڴ˿�λ", null);
                                        }
                                    } else {
                                        return new HttpResponse(HttpResponse.Status.FAILURE, "��ǰ�ⷿ�²����ڴ˿�λ", null);
                                    }
                                } else {
                                    sonDetail.setStoreLocationCode(appStoreCode);
                                    sonDetail.setStoreLocationId(wareHouse.getId());
                                    sonDetail.setStoreLocationName(wareHouse.getName());
                                    sonDetail.setStoreID(wareHouse.getParentId());
                                    detail.setStorelocationcode(wareHouse.getCode());
                                    detail.setStorelocationid(wareHouse.getId());
                                    detail.setStorelocationname(wareHouse.getName());
                                }
                            } else {
                                wareHouse = this.wareHouseService.getWareHouseByCode(detail.getStoreid(), appStoreCode, null);
                                if (StringUtils.isEmpty(wareHouse)) {
                                    List<WareHouse> wareHouseList = wareHouseService.findByParentCode(detail.getExtendstring1());
                                    if (wareHouseList == null || wareHouseList.size() == 0) {
                                        WareHouse wareHouse1 = this.service.addWareHouse(defaultStoreHouse, detail.getStoreid(), detail.getZtid(), detail.getCreator());
                                        sonDetail.setStoreLocationCode(wareHouse1.getCode());
                                        sonDetail.setStoreLocationId(wareHouse1.getId());
                                        sonDetail.setStoreLocationName(wareHouse1.getName());
                                        sonDetail.setStoreID(wareHouse1.getParentId());
                                        detail.setStorelocationcode(wareHouse1.getCode());
                                        detail.setStorelocationid(wareHouse1.getId());
                                        detail.setStorelocationname(wareHouse1.getName());
                                    } else if (wareHouseList.size() == 1) {
                                        WareHouse wareHouse1 = wareHouseList.get(0);
                                        if (wareHouse.getName().equals(defaultStoreHouse)) {
                                            sonDetail.setStoreLocationCode(wareHouse1.getCode());
                                            sonDetail.setStoreLocationId(wareHouse1.getId());
                                            sonDetail.setStoreLocationName(wareHouse1.getName());
                                            sonDetail.setStoreID(wareHouse1.getParentId());
                                            detail.setStorelocationcode(wareHouse1.getCode());
                                            detail.setStorelocationid(wareHouse1.getId());
                                            detail.setStorelocationname(wareHouse1.getName());
                                        } else {
                                            return new HttpResponse(HttpResponse.Status.FAILURE, "��ǰ�ⷿ�²����ڴ˿�λ", null);
                                        }
                                    } else {
                                        return new HttpResponse(HttpResponse.Status.FAILURE, "��ǰ�ⷿ�²����ڴ˿�λ", null);
                                    }
                                } else {
                                    sonDetail.setStoreLocationCode(appStoreCode);
                                    sonDetail.setStoreLocationId(wareHouse.getId());
                                    sonDetail.setStoreLocationName(wareHouse.getName());
                                    sonDetail.setStoreID(wareHouse.getParentId());
                                    detail.setStorelocationcode(wareHouse.getCode());
                                    detail.setStorelocationid(wareHouse.getId());
                                    detail.setStorelocationname(wareHouse.getName());
                                    detail.setStoreid(wareHouse.getParentId());
                                }
                            }

                            sonDetail.setTagCode(list.get(i).getTagCode());
                            sonDetail.setSubStock(0);
                            sonDetail.setSnCode(list.get(i).getSnCode());
                            sonDetail.setGuid(detail.getGuid());
                            sonDetail.setAddTime(new Date());
                            sonDetail.setUnitName(list.get(i).getDetailUnitName());
                            sonDetail.setSubDetailCount(Double.parseDouble(request.getParameter("detailCount")));
                            detail.setStorelocationcode(appStoreCode);
                        }
                        sonList.add(sonDetail);
                        detailList.add(detail);
                    }
                    this.service.saveSheetDetails(type, detailList);
                    logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums
                                    .LogAction.ADD_DETAIL.getCode(),
                            type + "����:" + detail.getId(), userIp, userId);

                    if (!StringUtils.isEmpty(rkId) && !StringUtils.isEmpty(sid) && sonList.size() != 0) {
                        for (int i = 0; i < detailList.size(); i++) {
                            sonList.get(i).setDetailId(detailList.get(i).getId());
                            this.service.addSonDetail(sonList);
                        }
                        logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_STORAGE.getCode(), SystemLogEnums
                                .LogAction.ADD_SONDETAIL.getCode(), "��ⵥ�ݷ���ⷿ��λ:", userIp, userId);
                    }
                } else {
                    return new HttpResponse(HttpResponse.Status.FAILURE, "��ѯ������Ӧ��ϸ", null);
                }
            } else {
                if (session.getAttribute("userId") != null) {
                    userId = Integer.parseInt(session.getAttribute("userId").toString());
                    userIp = session.getAttribute("userIp").toString();
                }
                User user = this.userService.getUserOne(userId);
                if (null == user) {
                    return new HttpResponse(HttpResponse.Status.FAILURE, "�û���Ϣ����", null);
                }
                String getdetails = request.getParameter("details");
                JSONArray detailJson = JSONArray.fromObject(getdetails);
                Collection collection = JSONArray.toCollection(detailJson);
                if (collection != null && !collection.isEmpty()) {
                    Iterator it = collection.iterator();
                    SheetRKDETAIL detail = null;
                    List<SheetRKDETAIL> detailList = new ArrayList<>();
                    Date now = new Date();
                    while (it.hasNext()) {
                        JSONObject jsonObj = JSONObject.fromObject(it.next());
                        detail = (SheetRKDETAIL) JSONObject.toBean(jsonObj, SheetRKDETAIL.class);
                        detail.setGuid(java.util.UUID.randomUUID().toString());
                        detail.setCreator(userId);
                        detail.setCreatedate(now);
                        if (detail.getOwnertype() == num6) {
                            WareHouse wareHouse = wareHouseService.getWareHouseName("���ۿ�");
                            if (wareHouse != null) {
                                detail.setExtendstring1(wareHouse.getCode());
                                detail.setStoreid(wareHouse.getId());
                            }
                        }
                        detailList.add(detail);
                    }

                    this.service.saveSheetDetails(type, detailList);
                    this.service.saveDefaultStoreDetails(detailList);
                    logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums
                                    .LogAction.ADD_DETAIL.getCode(),
                            type + "����:" + detail.getId(), userIp, userId);
                }
            }
            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }

    /**
     * ������������ϸ
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addSonDetail.json")
    @ResponseBody
    public HttpResponse addSonDetail(HttpServletRequest request, HttpServletResponse response) {
        HttpResponse ret;
        log.debug("����������������ϸ����,��ǰ������:addSonDetail");
        try {

            //��ȡ��ǰ��¼����Ϣ
            HttpSession session = request.getSession();
            Object requestUserIp = session.getAttribute("userIp");
            Object requestDepartName = session.getAttribute("departName");
            Object requestUserName = session.getAttribute("userName");
            Object requestUserId = session.getAttribute("userId");
            String departName = (null == requestDepartName ? null : requestDepartName.toString());
            Integer userId = (null == requestUserId ? null : Integer.parseInt(requestUserId.toString()));
            String userIp = (null == requestUserIp ? null : requestUserIp.toString());
            String userName = (null == requestUserName ? null : requestUserName.toString());

            JSONArray detailJson = JSONArray.fromObject(request.getParameter("details"));
            Collection collection = JSONArray.toCollection(detailJson);
            if (collection != null && !collection.isEmpty()) {
                Iterator it = collection.iterator();
                SheetRkSonDetail detail = null;
                List<SheetRkSonDetail> detailList = new ArrayList<>();
                Date now = new Date();
                while (it.hasNext()) {
                    JSONObject jsonObj = JSONObject.fromObject(it.next());
                    detail = (SheetRkSonDetail) JSONObject.toBean(jsonObj, SheetRkSonDetail.class);
                    detail.setGuid(java.util.UUID.randomUUID().toString());
                    detail.setAddTime(now);
                    detailList.add(detail);
                }
                this.service.addSonDetail(detailList);
                logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_STORAGE.getCode(), SystemLogEnums
                        .LogAction.ADD_SONDETAIL.getCode(), "��ⵥ�ݷ���ⷿ��λ:" + detail.getId(), userIp, userId);
            }
            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }


    /**
     * ɾ����ϸ�ѷ����λ
     *
     * @param sonDetail
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteSonDetail.json")
    @ResponseBody
    public HttpResponse deleteSonDetail(@ModelAttribute("SheetRkSonDetail") SheetRkSonDetail sonDetail,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        log.debug("�����������ɾ����ϸ�ѷ����λ����,��ǰ������:deleteSonDetail");
        HttpResponse ret;
        try {

            //��ȡ��ǰ��¼����Ϣ
            HttpSession session = request.getSession();
            Object requestUserIp = session.getAttribute("userIp");
            Object requestDepartName = session.getAttribute("departName");
            Object requestUserName = session.getAttribute("userName");
            Object requestUserId = session.getAttribute("userId");
            String departName = (null == requestDepartName ? null : requestDepartName.toString());
            Integer userId = (null == requestUserId ? null : Integer.parseInt(requestUserId.toString()));
            String userIp = (null == requestUserIp ? null : requestUserIp.toString());
            String userName = (null == requestUserName ? null : requestUserName.toString());

            this.service.deleteSonDetail(sonDetail.getId());
            logService.addSystemLog(1, SystemLogEnums.LogObject.MATERIAL_STORAGE.getCode(), SystemLogEnums.LogAction
                            .DELETE.getCode(),
                    "ɾ���ѷ�����ϸ��λ:" + sonDetail.getId(), userIp, userId);
            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("ɾ����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }


}

	
