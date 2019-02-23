package com.zthc.ewms.sheet.entity.rk;

import com.zthc.ewms.base.util.NumberUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "v_rkdetailsprint")
public class RkDetailPrint {

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
     * ----�Ĵ�����---
     **/
    @Column(name = "ownerType", columnDefinition = "Integer NULL COMMENT '�Ĵ�����'")
    private Integer ownerType;
    /**
     * ----�Ƿ��������к�---
     **/
    @Column(name = "enableSn", columnDefinition = "Integer NULL COMMENT '�Ƿ��������к�'")
    private Integer enableSn;
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
     * ----����string�ֶ�---
     **/
    @Column(name = "extendString1", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendString1;
    /**
     * ----�������---
     **/
    @Column(name = "detailCount", columnDefinition = "number(18,9) NULL COMMENT '�������'")
    private Double detailCount;
    /**
     * ----�Ƿ����---
     **/
    @Column(name = "jsType", columnDefinition = "nvarchar2(50) NULL COMMENT '�Ƿ����'")
    private String jsType;
    /**
     * ----���ն������---
     **/
    @Column(name = "jsCode", columnDefinition = "nvarchar2(64) NULL COMMENT '���ն������'")
    private String jsCode;
    /**
     * ----��˰����---
     **/
    @Column(name = "extendFloat1", columnDefinition = "number(18,9) NULL COMMENT '��˰����'")
    private Double extendFloat1;
    /**
     * ----�ⷿ����---
     */
    @Column(name = "houseName", columnDefinition = "nvarchar2(64) NULL COMMENT '�ⷿ����'")
    private String houseName;
    /**
     * ----��λ����---
     **/
    @Column(name = "storeLocationCode", columnDefinition = "nvarchar2(255) NULL COMMENT '��λ����'")
    private String storeLocationCode;
    /**
     * ----��λ����---
     **/
    @Column(name = "storeLocationName", columnDefinition = "nvarchar2(50) NULL COMMENT '��λ����'")
    private String storeLocationName;
    /**
     * ----��λID---
     **/
    @Column(name = "storeLocationId", columnDefinition = "Integer NULL COMMENT '��λID'")
    private Integer storeLocationId;
    /**
     * ----��������---
     **/
    @Column(name = "sumCount", columnDefinition = "number(18,9) NULL COMMENT '��������'")
    private Double sumCount;

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
    public String getNotaxPriceDouble() {
        return NumberUtils.getDouble(notaxPrice);
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
    public String getNotaxSumDouble() {
        return NumberUtils.getDouble(notaxSum);
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
    public String getTaxSumDouble() {
        return NumberUtils.getDouble(taxSum);
    }
    public Integer getIsEquipment() {
        return isEquipment;
    }

    public void setIsEquipment(Integer isEquipment) {
        this.isEquipment = isEquipment;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public Integer getEnableSn() {
        return enableSn;
    }

    public void setEnableSn(Integer enableSn) {
        this.enableSn = enableSn;
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

    public String getJsType() {
        return jsType;
    }

    public void setJsType(String jsType) {
        this.jsType = jsType;
    }

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }

    public Double getExtendFloat1() {
        return extendFloat1;
    }

    public void setExtendFloat1(Double extendFloat1) {
        this.extendFloat1 = extendFloat1;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
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

    public Integer getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(Integer storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    public Double getSumCount() {
        return sumCount;
    }

    public void setSumCount(Double sumCount) {
        this.sumCount = sumCount;
    }

    public RkDetailPrint() {
    }
}
