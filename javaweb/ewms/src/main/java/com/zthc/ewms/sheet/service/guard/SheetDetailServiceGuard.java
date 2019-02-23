package com.zthc.ewms.sheet.service.guard;

import com.zthc.ewms.sheet.dao.SheetDetailDao;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.sheet.entity.ykyw.YkywList;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SheetDetailServiceGuard {
    @Resource(name = "sheetDetailDao")
    public SheetDetailDao dao;


    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;

    /**************************  ????????   ***************************/
    //????
    @Transactional
    public void saveSheetDetail(SheetDetail obj, SheetDetailCondition condition) {
        this.dao.saveSheetDetail(obj, condition);
    }


    private List<SheetDetail> jsonStringToSheetDetail(String details, boolean appFlag, String type) throws Exception {
        JSONArray detailJson = JSONArray.fromObject(details);
        Collection collection = JSONArray.toCollection(detailJson);
        if (collection != null && !collection.isEmpty()) {
            Iterator it = collection.iterator();
            SheetDetail detail = null;
            List<SheetDetail> detailList = new ArrayList<>();
            String locationCode;
            WareHouse location;
            while (it.hasNext()) {
                JSONObject jsonObj = JSONObject.fromObject(it.next());
                detail = (SheetDetail) JSONObject.toBean(jsonObj, SheetDetail.class);
                detailList.add(detail);
//                if (appFlag && type.equals("YKYW")) {
//                    locationCode = detail.getStoreLocationCode();
//                    location = this.wareHouseService.getWareHouseByCode(locationCode);
//                    if (location == null) {
//                        throw new Exception("查不到库位：" + locationCode);
//                    } else {
//                        detail.setStoreId(location.getParentId());
//                        detail.setStoreLocationId(location.getId());
//                        detail.setStoreLocationName(location.getName());
//                    }
//                }
            }
            return detailList;
        } else {
            throw new Exception("添加明细信息为空:" + details);
        }

    }


//    @Transactional
//    public void saveYKYWSheetDetails(String details, boolean appFlag) throws Exception {
//        List<SheetDetail> detailList = this.jsonStringToSheetDetail(details, appFlag, "YKYW");
//        YkywList ykyw = null;
//        for (SheetDetail detail : detailList) {
//            ykyw = this.dao.getDetail(detail.getSheetDetailId(), "YkywList");
//            detail.setCategoryId(ykyw.getCategoryId());
//            detail.setMaterialId(ykyw.getMaterialId());
//            detail.setMaterialCode(ykyw.getMaterialCode());
//            detail.setMaterialName(ykyw.getMaterialName());
//            detail.setMaterialBrand(ykyw.getMaterialBrand());
//            detail.setMaterialModel(ykyw.getMaterialModel());
//            detail.setMaterialSpecification(ykyw.getMaterialSpecification());
//            detail.setTagCode(ykyw.getMaterialCode());
//            detail.setDetailUnitName(ykyw.getDetailUnitName());
//            detail.setExtendInt4(ykyw.getStoreId());
//            detail.setExtendInt5(ykyw.getStoreLocationId());
//            detail.setExtendString5(ykyw.getStoreLocationCode());
//            detail.setExtendString6(ykyw.getStoreLocationName());
//            detail.setProviderDepId(ykyw.getProviderDepId());
//            detail.setNoTaxPrice(ykyw.getNoTaxPrice());
//            detail.setTaxPrice(ykyw.getTaxPrice());
//            detail.setTaxRate(ykyw.getTaxRate());
//            detail.setDescription(ykyw.getDescription());
//            detail.setPlanDepartId(ykyw.getPlanDepartId());
//            detail.setId(ykyw.getIsEquipment());
//            detail.setEnableSn(ykyw.getEnableSn());
//            detail.setSnCode(ykyw.getSnCode());
//            detail.setOwnerType(ykyw.getOwnerType());
//            detail.setZtId(ykyw.getZtId());
//            this.dao.saveSheetDetail(detail, null);
//
//        }
//    }

    @Transactional
    public void saveSheetDetails(String type, List<SheetDetail> detailList) {
        for (SheetDetail detail : detailList) {
            if (type.equalsIgnoreCase("WZDBD") && detail.getEnableSn() == 1) {
                SheetDetail sub;
                for (int i = 0; i < detail.getDetailCount(); i++) {
                    sub = detail.clone();
                    sub.setDetailCount(1D);
                    this.dao.saveSheetDetail(sub, null);
                }
            } else if (type.equals(DictionaryEnums.TypeToUrlId.WZZC.getType())) {
                long storeCount =dao.countSheetStockByCode(detail.getMaterialCode(),detail.getStoreLocationId());
                if(storeCount<detail.getDetailCount()){
                    throw new RuntimeException("物料库存量不足,请重新录入");
                }
            } else {
                this.dao.saveSheetDetail(detail, null);
            }
        }
    }

    @Transactional
    public void saveYKYWSheetDetailForApp(Integer sheetId, Integer sheetDetailId, String storeLocationCode, Double
            detailCount, Integer ztId, Integer storeId) throws Exception {
        SheetDetail detail = new SheetDetail();
        detail.setSheetId(sheetId);
        detail.setSheetDetailId(sheetDetailId);
        detail.setDetailCount(detailCount);
        detail.setStoreId(storeId);
        YkywList ykyw = this.dao.getDetail(detail.getSheetDetailId(), "YkywList");
        WareHouse location = this.wareHouseService.getWareHouseByCode(storeId, storeLocationCode, ztId);
        if (location == null) {
            throw new Exception("查不到库位：" + storeLocationCode);
        } else {
            detail.setStoreLocationId(location.getId());
            detail.setStoreLocationName(location.getName());
        }
        detail.setStoreId(ykyw.getStoreId());
        detail.setCategoryId(ykyw.getCategoryId());
        detail.setMaterialId(ykyw.getMaterialId());
        detail.setMaterialCode(ykyw.getMaterialCode());
        detail.setMaterialName(ykyw.getMaterialName());
        detail.setMaterialBrand(ykyw.getMaterialBrand());
        detail.setMaterialModel(ykyw.getMaterialModel());
        detail.setMaterialSpecification(ykyw.getMaterialSpecification());
        detail.setTagCode(ykyw.getMaterialCode());
        detail.setDetailUnitName(ykyw.getDetailUnitName());
        detail.setExtendInt4(ykyw.getStoreId());
        detail.setExtendInt5(ykyw.getStoreLocationId());
        detail.setExtendString5(ykyw.getStoreLocationCode());
        detail.setExtendString6(ykyw.getStoreLocationName());
        detail.setProviderDepId(ykyw.getProviderDepId());
        detail.setNoTaxPrice(ykyw.getNoTaxPrice());
        detail.setTaxPrice(ykyw.getTaxPrice());
        detail.setTaxRate(ykyw.getTaxRate());
        detail.setDescription(ykyw.getDescription());
        detail.setPlanDepartId(ykyw.getPlanDepartId());
        detail.setId(ykyw.getIsEquipment());
        detail.setEnableSn(ykyw.getEnableSn());
        detail.setSnCode(ykyw.getSnCode());
        detail.setOwnerType(ykyw.getOwnerType());
        detail.setZtId(ykyw.getZtId());
        this.dao.saveSheetDetail(detail, null);
    }

    //??
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetDetail getSheetDetailOne(Integer id) {
        return this.dao.getSheetDetailOne(id);
    }

    //?
    @Transactional
    public void delSheetDetail(Long id) {
        this.dao.delSheetDetail(id);
    }


    /**************************  ????????   ***************************/
    //list?????б?

    //update
    @Transactional
    public String saveEwmsSheetDetails(List<SheetDetail> detailList) {
        String str = null;
        int num = 0;
        try {
            for (SheetDetail detail : detailList) {
                num++;
                this.dao.saveSheetDetail(detail, null);
                str = "OK";
            }
        } catch (Exception exception) {
            //str = num + ";" + exception.getMessage();
            str = num + ";" + "物料明细保存错误！";
        }
        return str;

    }
    //delete
}