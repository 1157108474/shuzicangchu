package com.zthc.ewms.sheet.service;

import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetRKDETAIL;
import com.zthc.ewms.sheet.entity.guard.SheetRkSonDetail;
import com.zthc.ewms.sheet.service.guard.SheetRKDETAILServiceGuard;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class SheetRKDETAILService extends SheetRKDETAILServiceGuard {

    @Autowired
    SheetDetailService sheetDetailService;

    @Autowired
    WareHouseService wareHouseService;


    /**
     * @param obj
     */
    public void addSonDetail(SheetRkSonDetail obj) {
        dao.addSonDetail(obj, null);
    }

    /**
     * �����ϸ
     *
     * @param obj
     */
    @Transactional
    public void saveSheetDetail(SheetRKDETAIL obj) {
        this.dao.saveSheetDetail(obj, null);

    }

    /**
     * �����ϸ
     *
     * @param type
     * @param detailList
     */
    @Transactional
    public void saveSheetDetails(String type, List<SheetRKDETAIL> detailList) {
        SheetDetail sheetDetail;
        for (SheetRKDETAIL detail : detailList) {
            if (type.equals("ZR")) {
                sheetDetail = new SheetDetail();
                sheetDetail.setGuid(detail.getGuid());
                sheetDetail.setSheetId(detail.getSheetid());
                sheetDetail.setCategoryId(detail.getCategoryid());
                sheetDetail.setMaterialId(detail.getMaterialid());
                sheetDetail.setMaterialCode(detail.getMaterialcode());
                sheetDetail.setMaterialName(detail.getMaterialname());
                sheetDetail.setProviderDepId(detail.getProviderdepid());
                sheetDetail.setCreator(detail.getCreator());
                sheetDetail.setCreateDate(detail.getCreatedate());
                sheetDetail.setZtId(detail.getZtid());
                sheetDetail.setExtendFloat1(detail.getExtendfloat1());
                sheetDetail.setExtendString1(detail.getExtendstring1());
                sheetDetail.setNoTaxPrice(detail.getNotaxprice());
                sheetDetail.setTaxRate(detail.getTaxRate());
                sheetDetail.setNoTaxSum(detail.getNotaxsum());
                sheetDetail.setMaterialSpecification(detail.getMaterialspecification());
                sheetDetail.setDescription(detail.getDescription());
                sheetDetail.setExpirationTime(detail.getExpirationtime());
                sheetDetail.setTaxPrice(detail.getTaxprice());
                sheetDetail.setTaxSum(detail.getTaxsum());
                sheetDetail.setPlanDepartId(detail.getPlandepartid());
                sheetDetail.setDetailCount(detail.getDetailcount());
                sheetDetail.setIsEquipment(detail.getIsequipment());
                sheetDetail.setOwnerType(detail.getOwnertype());
                sheetDetail.setDetailUnitName(detail.getDetailunitname());
                sheetDetailService.saveSheetDetail(sheetDetail, null);
                detail.setSheetdetailid(sheetDetail.getId());
            }
            this.dao.saveSheetDetail(detail, null);
        }
    }


    /**
     * ���������ϸ����λ��
     *
     * @param detailList
     */
    @Transactional
    public void addSonDetail(List<SheetRkSonDetail> detailList) {
        for (SheetRkSonDetail detail : detailList) {
            this.dao.addSonDetail(detail, null);
        }
    }

    /**
     * ɾ����ϸ�����λ
     *
     * @param id
     */
    @Transactional
    public void deleteSonDetail(Integer id) {
        this.dao.deleteSonDetail(id);
    }


    @Transactional
    public void deleteSonDetailByIds(Integer[] ids) {
        this.dao.deleteSonDetailByIds(ids);
    }

    @Transactional
    public void deleteSonDetailByDetailId(Integer id) {
        this.dao.deleteSonDetailByDetailId(id);
    }

    /**
     * ɾ�������ϸ
     *
     * @param id
     */
    @Transactional
    public void deleteDetailBySheetId(Integer id) {
        this.dao.deleteDetailBySheetId(id);
    }

    public List<SheetRKDETAIL> getDetailOne(Integer rid) {
        return this.dao.getDetailOne(rid);
    }


    /**
     * ����Ĭ�Ͽ�λ
     *
     * @param detailList
     */
    @Transactional
    public void saveDefaultStoreDetails(List<SheetRKDETAIL> detailList) {
        for (SheetRKDETAIL rkdetail : detailList) {
            WareHouse ware = wareHouseService.getWareHouseCode(rkdetail.getExtendstring1());
            List<WareHouse> wareHouseList = wareHouseService.findByParentCode(rkdetail.getExtendstring1());
            //��ȡĬ�Ͽ�λ����
            String defaultStoreHouse = AppConfig.getProperty("defaultStoreHouse");
            if (wareHouseList == null || wareHouseList.size() == 0 ){
                WareHouse wareHouse = this.addWareHouse(defaultStoreHouse,ware.getId(),ware.getZtId(),rkdetail.getCreator());
                this.saveStoreDetails(rkdetail,wareHouse);
            }else if(wareHouseList.size() == 1){
                WareHouse wareHouse = wareHouseList.get(0);
                if(wareHouse.getName().equals(defaultStoreHouse)){
                    this.saveStoreDetails(rkdetail,wareHouse);
                }
            }
        }
    }

    /**
     * ����Ĭ�Ͽ�λ
     *
     * @return
     */
    @Transactional
    public WareHouse addWareHouse(String name, Integer storeId, Integer ztId, Integer creator) {
        WareHouse wareHouse =  new WareHouse();
        wareHouse.setCode(UUID.randomUUID().toString());
        wareHouse.setCode("DF"+storeId);
        wareHouse.setName(name);
        wareHouse.setParentId(storeId);
        wareHouse.setLevelCount(2);
        wareHouse.setEndFlag(1);
        wareHouse.setStatus(1);
        wareHouse.setSort(0);
        wareHouse.setSort(0);
        wareHouse.setProperty(4);
        wareHouse.setZtId(ztId);
        wareHouse.setCreator(creator);
        this.wareHouseService.saveWareHouse(wareHouse,null);
        return wareHouse;
    }

    /**
     * ������λ����
     *
     */
    @Transactional
    public void saveStoreDetails(SheetRKDETAIL rkdetail, WareHouse wareHouse) {

        Date now = new Date();
        SheetRkSonDetail sheetRkSonDetail = new SheetRkSonDetail();
        sheetRkSonDetail.setGuid(UUID.randomUUID().toString());
        sheetRkSonDetail.setDetailId(rkdetail.getId());
        sheetRkSonDetail.setSubStock(0);
        sheetRkSonDetail.setSubDetailCount(rkdetail.getDetailcount());
        //�ⷿ����λ
        sheetRkSonDetail.setStoreID(wareHouse.getParentId());
        sheetRkSonDetail.setStoreLocationId(wareHouse.getId());
        sheetRkSonDetail.setStoreLocationCode(wareHouse.getCode());
        sheetRkSonDetail.setStoreLocationName(wareHouse.getName());

        sheetRkSonDetail.setAddTime(now);
        sheetRkSonDetail.setUnitName(rkdetail.getDetailunitname());

        this.addSonDetail(sheetRkSonDetail);

    }


    
}
