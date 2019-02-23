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
     * 库存组织
     */
    private String inventoryName;
    /**
     * 业务实体
     */
    private String businessEntity;
    /**
     * 物料ID
     */
    private Integer materialId;
    /**
     * 物料编码
     */
    private String materialCode;
    /**
     * 物料名称
     */
    private String materialName;
    /**
     * 物料分类
     */
    private Integer cateGoryId;
    /**
     * 物料规格
     */
    private String materialSpecification;

    /**
     * 物料描述
     */
    private String materialDescription;
    /**
     * 物料单位
     */
    private String materialUnit;
    /**
     * 库房Id
     */
    private Integer storehouseId;
    /**
     * 库房名称
     */
    private String storehouseName;
    /**
     * 库房编码
     */
    private String storehouseCode;
    /**
     * 库位Id
     */
    private Integer storeLocationId;
    /**
     * 库位编号
     */
    private String storeLocationCode;
    /**
     * 库位名称
     */
    private String storeLocationName;

    /**
     * 分配数量
     */
    private Double allotNumber;
    /**
     * 数量
     */
    private Double num;
    /**
     * 单价
     */
    private double univalent;
    /**
     * 税率
     */
    private double taxRate;
    /**
     * 含税金额
     */
    private double taxPrice;
    /**
     * 不含税金额
     */
    private double noTaxPrice;
    /**
     * 计划部门
     */
    private Integer projectDivisionId;
    /**
     * 是否是设备
     */
    private Integer ifEquipment;
    /**
     * 是否启用序列号
     */
    private Integer ifSerialNumber;
    /**
     * 保质期限
     */
    private Date shelfLifeDate;
    /**
     * 生产日期
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