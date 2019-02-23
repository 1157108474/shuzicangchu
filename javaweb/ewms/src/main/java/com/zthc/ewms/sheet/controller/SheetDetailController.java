package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetDetailControllerGuard;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.sheet.entity.order.OrderDetails;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/sheet/detail")
public class SheetDetailController extends SheetDetailControllerGuard {

	private final static Log log;
	
	static{
		log = Log.getLog("com.zthc.ewms.sheetdetail.controller.SheetDetailController");
	}

    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;

	@Override
	//��������ʡ�Ե�
	public String editSheetDetail(@ModelAttribute("detail") SheetDetail obj, SheetDetailCondition condition,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		//����ĳЩ���ݵ�Ĭ�ϸ�ֵ���������б�ѡ�PageHelper.getChoiseJson("userstatus", false)����ʵ��;
		//String userRole = "{'take':'1','show':'����Ա'},{'take':'2','show':'����Ա'}";
		//model.addAttribute("userRole", userRole);
		
		return super.editSheetDetail(obj, condition, request, response, model);
	}
	/**********************************************************/

    /**
     * ִ�з���_�����ϸ
     */
    @RequestMapping(value = "/add{type}Details", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse addDetails(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse
            response) {

        HttpResponse ret;
        log.debug("�����ϸ");
        try {
            Integer userId = 0;
            String userIp = "";
            HttpSession session = request.getSession();
            String data = request.getParameter("appFlag");
            boolean appFlag = false;
            if (data != null && "1".equals(data)) {
                appFlag = true;
                userId = Integer.parseInt(request.getParameter("userId"));
                userIp = "app";
            }else {
                if (session.getAttribute("userId") != null) {
                    userId = Integer.parseInt(session.getAttribute("userId").toString());
                    userIp = session.getAttribute("userIp").toString();
                }
            }
            JSONArray detailJson = JSONArray.fromObject(request.getParameter("details"));
            Collection collection = JSONArray.toCollection(detailJson);
            if (collection != null && !collection.isEmpty()) {
                Iterator it = collection.iterator();
                SheetDetail detail = null;
                List<SheetDetail> detailList = new ArrayList<>();
                Date now = new Date();
                String locationCode;
                WareHouse location;
                Double detailCount;
                while (it.hasNext()) {
                    JSONObject jsonObj = JSONObject.fromObject(it.next());
                    detail = (SheetDetail) JSONObject.toBean(jsonObj, SheetDetail.class);
                    if (detail == null) {
                        detail = new SheetDetail();
                    }
                    detail.setGuid(java.util.UUID.randomUUID().toString());
                    detail.setCreator(userId);
                    detail.setCreateDate(now);
                    detailList.add(detail);
//                    if(appFlag && type.equalsIgnoreCase("YKYW")){
//                        locationCode = detail.getStoreLocationCode();
//                        location = this.wareHouseService.getWareHouseByCode(locationCode);
//                        if (location==null){
//                           return new HttpResponse(HttpResponse.Status.FAILURE, "�鲻����λ��"+locationCode, null);
//                        }else{
//                            detail.setStoreId(location.getParentId());
//                            detail.setStoreLocationId(location.getId());
//                            detail.setStoreLocationName(location.getName());
//                        }
//                    } else
                        if (appFlag && type.equalsIgnoreCase("WZJS")) {
                        detail.setSheetId(Integer.parseInt(request.getParameter("sheetId")));
                        detail.setSheetDetailId(Integer.parseInt(request.getParameter("sheetDetailId")));
                        detailCount = Double.parseDouble(request.getParameter("detailCount"));
                        Integer isEquiment = Integer.parseInt(request.getParameter("isEquipment"));
                        Integer enableSn = Integer.parseInt(request.getParameter("enableSn"));
                        Integer sheetDetailId = Integer.parseInt(request.getParameter("sheetDetailId"));
                        Integer materialId = null;
                        String mId = request.getParameter("materialId");
                        if(!StringUtils.isEmpty(materialId)){
                            materialId = Integer.parseInt(mId);
                        }
                        OrderDetails orderDetail = this.sheetService.getOrderInfoOne(sheetDetailId);
                        detail.setCategoryId(orderDetail.getSparescateId());
                        detail.setMaterialId(orderDetail.getMaterialId());
                        detail.setMaterialCode(orderDetail.getMaterialCode());
                        detail.setMaterialName(orderDetail.getMaterialName());
                        detail.setMaterialBrand(orderDetail.getMaterialBrand());
                        detail.setMaterialModel(orderDetail.getMaterialModel());
                        detail.setMaterialSpecification(orderDetail.getMaterialSpecification());
                        detail.setDetailUnitName(orderDetail.getBaseunit());
                        detail.setNoTaxPrice(orderDetail.getBaseunitprice());
                        detail.setNoTaxSum(orderDetail.getBaseunitprice() * detailCount);
                        detail.setTaxSum(orderDetail.getBaseunitprice() * (orderDetail.getTaxRate() + 1) * detailCount);
                        detail.setTaxRate(orderDetail.getTaxRate());
                        detail.setDescription(orderDetail.getDescription());
                        detail.setDetailCount(detailCount);
                        detail.setIsEquipment(isEquiment);
                        detail.setEnableSn(enableSn);
                        detail.setZtId(orderDetail.getStockorgid());
                        detail.setOwnerType(orderDetail.getConsignment());
                        detail.setProviderDepId(orderDetail.getProviderdepid());
                        detail.setExtendString1(orderDetail.getExtendstring1());
                        detail.setExtendString10(orderDetail.getErprownum());
                        detailList.add(detail);
                    }
                }
                this.service.saveSheetDetails(type,detailList);
                /*logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums
                                .LogAction.ADD_DETAIL.getCode(),
                        type + "����:" + detail.getId(), userIp, userId);*/
            }
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "����ɹ�", null);
        }catch (RuntimeException e) {
            log.error(e.getMessage());
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }
    /**
     * ɾ��
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "{type}")
    public HttpResponse deleteDetail(@PathVariable("type") String type, SheetDetailCondition condition, HttpServletRequest request) {
        String ids = request.getParameter("ids");
        log.debug("ɾ��"+type+"��ϸ�ύ��id in  " + ids);
        HttpResponse ret;
        Integer userId = 0;
        String userIp = "";
        try {
            this.service.delDetails(condition);
            logService.addSystemLog(0, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums.LogAction.DELETE_DETAIL.getCode(),
                    "ɾ������:id =" +condition.getIds(), userIp, userId);

            ret = new HttpResponse(condition);
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), condition);
        }
        return ret;
    }
}	
	
	
