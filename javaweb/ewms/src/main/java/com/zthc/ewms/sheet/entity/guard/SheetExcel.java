package com.zthc.ewms.sheet.entity.guard;

import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: excel
 * @Package
 */
public class SheetExcel {

    /**
     * �����֯
     */
    private String inventoryName;
    /**
     * ҵ��ʵ��
     */
    private String businessEntity;
    /**
     * ����ID
     */
    private Integer materialId;
    /**
     * ���ϱ���
     */
    private String materialCode;
    /**
     * ��������
     */
    private String materialName;
    /**
     * ���Ϸ���
     */
    private Integer cateGoryId;
    /**
     * ���Ϲ��
     */
    private String materialSpecification;

    /**
     * ��������
     */
    private String materialDescription;
    /**
     * ���ϵ�λ
     */
    private String materialUnit;
    /**
     * �ⷿId
     */
    private Integer storehouseId;
    /**
     * �ⷿ����
     */
    private String storehouseName;
    /**
     * �ⷿ����
     */
    private String storehouseCode;
    /**
     * ��λId
     */
    private Integer storeLocationId;
    /**
     * ��λ���
     */
    private String storeLocationCode;
    /**
     * ��λ����
     */
    private String storeLocationName;

    /**
     * ��������
     */
    private Double allotNumber;
    /**
     * ����
     */
    private Double num;
    /**
     * ����
     */
    private double univalent;
    /**
     * ˰��
     */
    private double taxRate;
    /**
     * ��˰���
     */
    private double taxPrice;
    /**
     * ����˰���
     */
    private double noTaxPrice;
    /**
     * �ƻ�����
     */
    private Integer projectDivisionId;
    /**
     * �Ƿ����豸
     */
    private Integer ifEquipment;
    /**
     * �Ƿ��������к�
     */
    private Integer ifSerialNumber;
    /**
     * ��������
     */
    private Date shelfLifeDate;
    /**
     * ��������
     */
    private Date productionDate;



    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getCateGoryId() {
        return cateGoryId;
    }

    public void setCateGoryId(Integer cateGoryId) {
        this.cateGoryId = cateGoryId;
    }

    public String getMaterialSpecification() {
        return materialSpecification;
    }

    public void setMaterialSpecification(String materialSpecification) {
        this.materialSpecification = materialSpecification;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    public Integer getStorehouseId() {
        return storehouseId;
    }

    public void setStorehouseId(Integer storehouseId) {
        this.storehouseId = storehouseId;
    }

    public String getStorehouseName() {
        return storehouseName;
    }

    public void setStorehouseName(String storehouseName) {
        this.storehouseName = storehouseName;
    }

    public String getStorehouseCode() {
        return storehouseCode;
    }

    public void setStorehouseCode(String storehouseCode) {
        this.storehouseCode = storehouseCode;
    }

    public Integer getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(Integer storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    public String getStoreLocationCode() {
        return storeLocationCode;
    }

    public void setStoreLocationCode(String storeLocationCode) {
        this.storeLocationCode = storeLocationCode;
    }

    public String getStoreLocationName() {
        return storeLocationName;
    }

    public void setStoreLocationName(String storeLocationName) {
        this.storeLocationName = storeLocationName;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public Double getAllotNumber() {
        return allotNumber;
    }

    public void setAllotNumber(Double allotNumber) {
        this.allotNumber = allotNumber;
    }

    public double getUnivalent() {
        return univalent;
    }

    public void setUnivalent(double univalent) {
        this.univalent = univalent;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public double getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(double noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }

    public Integer getProjectDivisionId() {
        return projectDivisionId;
    }

    public void setProjectDivisionId(Integer projectDivisionId) {
        this.projectDivisionId = projectDivisionId;
    }

    public Integer getIfEquipment() {
        return ifEquipment;
    }

    public void setIfEquipment(Integer ifEquipment) {
        this.ifEquipment = ifEquipment;
    }

    public Integer getIfSerialNumber() {
        return ifSerialNumber;
    }

    public void setIfSerialNumber(Integer ifSerialNumber) {
        this.ifSerialNumber = ifSerialNumber;
    }

    public Date getShelfLifeDate() {
        return shelfLifeDate;
    }

    public void setShelfLifeDate(Date shelfLifeDate) {
        this.shelfLifeDate = shelfLifeDate;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
}