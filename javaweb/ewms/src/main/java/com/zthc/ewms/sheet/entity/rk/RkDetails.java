package com.zthc.ewms.sheet.entity.rk;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * ���-ҳ����ϸ�б�
 */
@Entity
@Table(name = "v_rkdetails")
public class RkDetails {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----Ψһ��ʶ---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
    /**
     * ----����ID---
     **/
    @Column(name = "sheetId", columnDefinition = "Integer NULL COMMENT '����ID'")
    private Integer sheetId;
    /**
     * ----������ϸID---
     **/
    @Column(name = "sheetDetailId", columnDefinition = "Integer NULL COMMENT '������ϸID'")
    private Integer sheetDetailId;
    /**
     * ----���Ϸ���ID---
     **/
    @Column(name = "categoryId", columnDefinition = "Integer NULL COMMENT '���Ϸ���ID'")
    private Integer categoryId;
    /**
     * ----����ID---
     **/
    @Column(name = "materialId", columnDefinition = "Integer NULL COMMENT '����ID'")
    private Integer materialId;
    /**
     * ----���ϱ���---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '���ϱ���'")
    private String materialCode;
    /**
     * ----��������---
     **/
    @Column(name = "materialName", columnDefinition = "nvarchar2(255) NULL COMMENT '��������'")
    private String materialName;
    /**
     * ----����Ʒ��---
     **/
    @Column(name = "materialBrand", columnDefinition = "nvarchar2(255) NULL COMMENT '����Ʒ��'")
    private String materialBrand;
    /**
     * ----���Ϲ��---
     **/
    @Column(name = "materialModel", columnDefinition = "nvarchar2(255) NULL COMMENT '���Ϲ��'")
    private String materialModel;
    /**
     * ----���Ϲ��---
     **/
    @Column(name = "materialSpecification", columnDefinition = "nvarchar2(255) NULL COMMENT '���Ϲ��'")
    private String materialSpecification;
    /**
     * ----������ID---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '������ID'")
    private Integer creator;
    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "datetime NULL COMMENT '����ʱ��'")
    private Date createDate;
    /**
     * ----�����֯ID---
     **/
    @Column(name = "ztId", columnDefinition = "Integer NULL COMMENT '�����֯ID'")
    private Integer ztId;
    /**
     * ----����˰����---
     **/
    @Column(name = "notaxPrice", columnDefinition = "number(18,9) NULL COMMENT '����˰����'")
    private Double notaxPrice;
    /**
     * ----˰��---
     **/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double taxRate;
    /**
     * ----����˰���---
     **/
    @Column(name = "notaxSum", columnDefinition = "number(18,9) NULL COMMENT '����˰���'")
    private Double notaxSum;
    /**
     * ----��������---
     **/
    @Column(name = "description", columnDefinition = "nvarchar2(500) NULL COMMENT '��������'")
    private String description;
    /**
     * ----��˰����---
     **/
    @Column(name = "taxPrice", columnDefinition = "number(18,9) NULL COMMENT '��˰����'")
    private Double taxprice;
    /**
     * ----��˰�ܽ��---
     **/
    @Column(name = "taxSum", columnDefinition = "number(18,9) NULL COMMENT '��˰�ܽ��'")
    private Double taxSum;
    /**
     * ----�Ƿ�Ϊ�豸---
     **/
    @Column(name = "isEquipment", columnDefinition = "Integer NULL COMMENT '�Ƿ�Ϊ�豸'")
    private Integer isEquipment;
    /**
     * ----�Ƿ��������к�---
     **/
    @Column(name = "enableSn", columnDefinition = "Integer NULL COMMENT '�Ƿ��������к�'")
    private Integer enableSn;
    /**
     * ----�Ĵ�����---
     **/
    @Column(name = "ownerType", columnDefinition = "Integer NULL COMMENT '�Ĵ�����'")
    private Integer ownerType;
    /**
     * ----���кű���---
     **/
    @Column(name = "snCode", columnDefinition = "nvarchar2(50) NULL COMMENT '���кű���'")
    private String snCode;
    /**
     * ----��ϸ��λ---
     **/
    @Column(name = "detailUnitName", columnDefinition = "nvarchar2(20) NULL COMMENT '��ϸ��λ'")
    private String detailUnitName;
    /**
     * ----�ⷿ����---
     **/
    @Column(name = "extendString1", columnDefinition = "nvarchar2(255) NULL COMMENT '�ⷿ����'")
    private String extendString1;
    /**
     * ----�ⷿ����---
     **/
    @Column(name = "warehouseName", columnDefinition = "nvarchar2(255) NULL COMMENT '�ⷿ����'")
    private String warehouseName;

    /**
     * ----�������---
     **/
    @Column(name = "detailCount", columnDefinition = "number(18,9) NULL COMMENT '�������'")
    private Double detailCount;
    /**
     * ----��������---
     **/
    @Column(name = "subTotalCount", columnDefinition = "Integer NULL COMMENT '��������'")
    private Integer subTotalCount;
    /**
     * ----�Ƿ����---
     **/
    @Column(name = "jsType", columnDefinition = "nvarchar2(50) NULL COMMENT '�Ƿ����'")
    private String jsType;
    /**
     * ----��˰�ϼ�---
     **/
    @Column(name = "extendFloat1", columnDefinition = "number(18,9) NULL COMMENT '��˰�ϼ�'")
    private Double extendFloat1;
    /**
     * ----��λ����---
     **/
    @Column(name = "locationName", columnDefinition = "nvarchar2(255) NULL COMMENT '���кű���'")
    private String locationName;
    /**
     * ---- ---
     **/
    @Column(name = "sumCount", columnDefinition = "number(18,9) NULL COMMENT ' '")
    private Double sumCount;
    /**
     * ---- ---
     **/
    @Column(name = "storeID", columnDefinition = "Integer NULL COMMENT ' '")
    private Integer storeID;

    /**
     * �ɷ�������
     **/
    @Transient
    private Double kfpsl;
    @Transient
    private String fpkw;
    @Transient
    private String yskw;

    @Column(name = "providerName", columnDefinition = "nvarchar2(255) NULL COMMENT '��Ӧ������'")
    private String providerName;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getFpkw() {
        return fpkw;
    }

    public void setFpkw(String fpkw) {
        this.fpkw = fpkw;
    }

    public String getYskw() {
        return yskw;
    }

    public void setYskw(String yskw) {
        this.yskw = yskw;
    }

    public Double getKfpsl() {
        return kfpsl;
    }

    public void setKfpsl(Double kfpsl) {
        this.kfpsl = kfpsl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getSheetId() {
        return sheetId;
    }

    public void setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
    }

    public Integer getSheetDetailId() {
        return sheetDetailId;
    }

    public void setSheetDetailId(Integer sheetDetailId) {
        this.sheetDetailId = sheetDetailId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getMaterialBrand() {
        return materialBrand;
    }

    public void setMaterialBrand(String materialBrand) {
        this.materialBrand = materialBrand;
    }

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public String getMaterialSpecification() {
        return materialSpecification;
    }

    public void setMaterialSpecification(String materialSpecification) {
        this.materialSpecification = materialSpecification;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

    public Double getNotaxPrice() {
        return notaxPrice;
    }

    public void setNotaxPrice(Double notaxPrice) {
        this.notaxPrice = notaxPrice;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getNotaxSum() {
        return notaxSum;
    }

    public void setNotaxSum(Double notaxSum) {
        this.notaxSum = notaxSum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTaxprice() {
        return taxprice;
    }

    public void setTaxprice(Double taxprice) {
        this.taxprice = taxprice;
    }

    public Double getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(Double taxSum) {
        this.taxSum = taxSum;
    }

    public Integer getIsEquipment() {
        return isEquipment;
    }

    public void setIsEquipment(Integer isEquipment) {
        this.isEquipment = isEquipment;
    }

    public Integer getEnableSn() {
        return enableSn;
    }

    public void setEnableSn(Integer enableSn) {
        this.enableSn = enableSn;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getDetailUnitName() {
        return detailUnitName;
    }

    public void setDetailUnitName(String detailUnitName) {
        this.detailUnitName = detailUnitName;
    }

    public String getExtendString1() {
        return extendString1;
    }

    public void setExtendString1(String extendString1) {
        this.extendString1 = extendString1;
    }

    public Double getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Double detailCount) {
        this.detailCount = detailCount;
    }

    public Integer getSubTotalCount() {
        return subTotalCount;
    }

    public void setSubTotalCount(Integer subTotalCount) {
        this.subTotalCount = subTotalCount;
    }

    public String getJsType() {
        return jsType;
    }

    public void setJsType(String jsType) {
        this.jsType = jsType;
    }

    public Double getExtendFloat1() {
        return extendFloat1;
    }

    public void setExtendFloat1(Double extendFloat1) {
        this.extendFloat1 = extendFloat1;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getSumCount() {
        return sumCount;
    }

    public void setSumCount(Double sumCount) {
        this.sumCount = sumCount;
    }

    public Integer getStoreID() {
        return storeID;
    }

    public void setStoreID(Integer storeID) {
        this.storeID = storeID;
    }

    public RkDetails() {
    }
}
