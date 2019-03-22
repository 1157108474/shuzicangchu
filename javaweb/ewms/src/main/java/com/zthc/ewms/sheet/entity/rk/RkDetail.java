package com.zthc.ewms.sheet.entity.rk;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * ���-������ϸ�б�
 */
@Entity
@Table(name = "v_jsrklist")
public class RkDetail {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----Ψһ��ʶ---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
    /**
     * ----���ն������---
     **/
    @Column(name = "jsCode", columnDefinition = "nvarchar2(64) NULL COMMENT '���ն������'")
    private String jsCode;
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
     * ----��Ӧ��ID---
     **/
    @Column(name = "providerDepId", columnDefinition = "Integer NULL COMMENT '��Ӧ��ID'")
    private Integer providerDepId;
    /**
     * ----״̬---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '״̬'")
    private Integer status;
    /**
     * ----��ע---
     **/
    @Column(name = "memo", columnDefinition = "nvarchar2(255) NULL COMMENT '��ע'")
    private String memo;
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
     * ----expirationTime---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "expirationTime", columnDefinition = "date NULL COMMENT 'expirationTime'")
    private Date expirationTime;

    @Column(name = "extenddate2", columnDefinition = "date NULL ")
    private Date extendDate2;

    /**
     * ----�ƻ���������---
     **/
    @Column(name = "planDepName", columnDefinition = "nvarchar2(255) NULL COMMENT '�ƻ���������'")
    private String planDepName;

    /**
     * ----�ƻ���������---
     **/
    @Column(name = "planDepartId", columnDefinition = "nvarchar2(255) NULL COMMENT '�ƻ���������'")
    private Integer planDepartId;
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
     * ----���Ϲ��---
     **/
    @Column(name = "materialSpecification", columnDefinition = "nvarchar2(255) NULL COMMENT '���Ϲ��'")
    private String materialSpecification;
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
     * ----�������---
     **/
    @Column(name = "detailCount", columnDefinition = "number(18,9) NULL COMMENT '�������'")
    private Double detailCount;
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
     * ----�Ƿ����---
     **/
    @Column(name = "jsType", columnDefinition = "nvarchar2(50) NULL COMMENT '�Ƿ����'")
    private String jsType;
    /**
     * ----�Ƿ����CODE---
     **/
    @Column(name = "jsTypeCode", columnDefinition = "nvarchar2(50) NULL COMMENT '�Ƿ����CODE'")
    private String jsTypeCode;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendString1", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendString1;

    /**
     * ----��������---
     **/
    @Column(name = "jsCount", columnDefinition = "Integer NULL COMMENT '�ɽ�������'")
    private Double jsCount;
    /**
     * ----���������---
     **/
    @Column(name = "kCount", columnDefinition = "Integer NULL COMMENT '�ɽ�������'")
    private Double kCount;
    /**
     * ----�ɽ�������---
     **/
    @Column(name = "isCount", columnDefinition = "Integer NULL COMMENT '�ɽ�������'")
    private Double isCount;

    @Column(name = "providerName", columnDefinition = "nvarchar2(255) NULL COMMENT '��Ӧ������'")
    private String providerName;

    @Column(name = "extendInt1")
    private Integer extendInt1;
    /**----ERP�к�---**/
    @Column(name="erpRowNum", columnDefinition="nvarchar2(50) NULL COMMENT 'ERP�к�'")
    private String erpRowNum;
    @Transient
    private Integer storeId;

    @Transient
    private String storeName;

    @Transient
    private String wareHouseName;
    @Transient
    private String wareHouseCode;


    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getExtendInt1() {
        return extendInt1;
    }

    public void setExtendInt1(Integer extendInt1) {
        this.extendInt1 = extendInt1;
    }

    public Integer getPlanDepartId() {
        return planDepartId;
    }

    public void setPlanDepartId(Integer planDepartId) {
        this.planDepartId = planDepartId;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Date getExtendDate2() {
        return extendDate2;
    }

    public void setExtendDate2(Date extendDate2) {
        this.extendDate2 = extendDate2;
    }

    public String getPlanDepName() {
        return planDepName;
    }

    public void setPlanDepName(String planDepName) {
        this.planDepName = planDepName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
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

    public Integer getProviderDepId() {
        return providerDepId;
    }

    public void setProviderDepId(Integer providerDepId) {
        this.providerDepId = providerDepId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getMaterialSpecification() {
        return materialSpecification;
    }

    public void setMaterialSpecification(String materialSpecification) {
        this.materialSpecification = materialSpecification;
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

    public Double getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Double detailCount) {
        this.detailCount = detailCount;
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

    public String getJsType() {
        return jsType;
    }

    public void setJsType(String jsType) {
        this.jsType = jsType;
    }

    public String getJsTypeCode() {
        return jsTypeCode;
    }

    public void setJsTypeCode(String jsTypeCode) {
        this.jsTypeCode = jsTypeCode;
    }

    public String getExtendString1() {
        return extendString1;
    }

    public void setExtendString1(String extendString1) {
        this.extendString1 = extendString1;
    }

    public Double getJsCount() {
        return jsCount;
    }

    public void setJsCount(Double jsCount) {
        this.jsCount = jsCount;
    }

    public Double getkCount() {
        return kCount;
    }

    public void setkCount(Double kCount) {
        this.kCount = kCount;
    }

    public Double getIsCount() {
        return isCount;
    }

    public void setIsCount(Double isCount) {
        this.isCount = isCount;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getWareHouseCode() {
        return wareHouseCode;
    }

    public void setWareHouseCode(String wareHouseCode) {
        this.wareHouseCode = wareHouseCode;
    }

    public String getErpRowNum() {
        return erpRowNum;
    }

    public void setErpRowNum(String erpRowNum) {
        this.erpRowNum = erpRowNum;
    }

    public RkDetail() {
    }
}
