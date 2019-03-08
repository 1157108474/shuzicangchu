package com.zthc.ewms.sheet.entity.th;

import com.zthc.ewms.base.util.NumberUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: ������ϸ
 * @Package
 */
@Entity
@Table(name = "v_thdetails")
public class ThDetail {

    /**
     * ----����---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer id;
    /**
     * ----Ψһ��ʶ---
     **/
    @Column(name = "guid")
    private String guid;
    @Column(name = "houseName")
    private String houseName;
    @Column(name = "houseCode")
    private String houseCode;
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
     * ----   categoryId---
     **/
    @Column(name = "categoryId", columnDefinition = "Integer NULL COMMENT '   categoryId'")
    private Integer categoryId;
    /**
     * ----����ID---
     **/
    @Column(name = "materialId", columnDefinition = "Integer NULL COMMENT '����ID'")
    private Integer materialId;
    /**
     * ----���ϱ���---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar(64) NULL COMMENT '���ϱ���'")
    private String materialCode;
    /**
     * ----��������---
     **/
    @Column(name = "materialName", columnDefinition = "nvarchar(255) NULL COMMENT '��������'")
    private String materialName;
    /**
     * ----����Ʒ��---
     **/
    @Column(name = "materialBrand", columnDefinition = "nvarchar(255) NULL COMMENT '����Ʒ��'")
    private String materialBrand;
    /**
     * ----����ģ��---
     **/
    @Column(name = "materialModel", columnDefinition = "nvarchar(255) NULL COMMENT '����ģ��'")
    private String materialModel;
    /**
     * ----��ϸ����---
     **/
    @Column(name = "detailCount", columnDefinition = "number(18,9) NULL COMMENT '��ϸ����'")
    private Double detailCount;

    @Column(name = "detailUnit", columnDefinition = "Integer NULL COMMENT '״̬'")
    private Integer detailUnit;
    @Column(name = "currencyUnit", columnDefinition = "Integer NULL COMMENT '״̬'")
    private Integer currencyUnit;
    /**
     * ----storeId---
     **/
    @Column(name = "storeId", columnDefinition = "Integer NULL COMMENT 'storeId'")
    private Integer storeId;
    /**
     * ----��Ӧ��Id---
     **/
    @Column(name = "providerDepId", columnDefinition = "Integer NULL COMMENT '��Ӧ��Id'")
    private Integer providerDepId;

    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '��Ӧ��Id'")
    private Integer status;
    /**
     * ----��ע---
     **/
    @Column(name = "memo", columnDefinition = "nvarchar(255) NULL COMMENT '��ע'")
    private String memo;

    /**
     * ----������---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '������'")
    private Integer creator;
    /**
     * ----����ʱ��---
     **/
    @Column(name = "createDate", columnDefinition = "Integer NULL COMMENT '����ʱ��'")
    private Date createDate;
    /**
     * ----������---
     **/
    @Column(name = "updator", columnDefinition = "Integer NULL COMMENT '������'")
    private Integer updater;
    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL COMMENT '����ʱ��'")
    private Date updateDate;

    /**
     * ----ZTID---
     **/
    @Column(name = "ZTID", columnDefinition = "Integer NULL COMMENT 'ZTID'")
    private Integer ztId;
    /**
     * ----����˰����---
     **/
    @Column(name = "noTaxPrice", columnDefinition = "number(18,9) NULL COMMENT '����˰����'")
    private Double noTaxPrice;
    /**
     * ----˰��---
     **/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double taxRate;
    /**
     * ----����˰�ܶ�---
     **/
    @Column(name = "noTaxSum", columnDefinition = "number(18,9) NULL COMMENT '����˰�ܶ�'")
    private Double noTaxSum;

    /**
     * ----���Ϸ���---
     **/
    @Column(name = "materialSpecification", columnDefinition = "nvarchar(255) NULL COMMENT '���Ϸ���'")
    private String materialSpecification;

    /**
     * ----����---
     **/
    @Column(name = "description", columnDefinition = "nvarchar(500) NULL COMMENT '����'")
    private String description;
    /**
     * ----expirationTime---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "expirationTime", columnDefinition = "date NULL COMMENT 'expirationTime'")
    private Date expirationTime;

    /**
     * ----��λ����---
     **/
    @Column(name = "storeLocationCode", columnDefinition = "nvarchar(255) NULL COMMENT '��λ����'")
    private String storeLocationCode;
    /**
     * ----����---
     **/
    @Column(name = "tagCode", columnDefinition = "nvarchar(50) NULL COMMENT '����'")
    private String tagCode;
    /**
     * ----��˰����---
     **/
    @Column(name = "taxPrice", columnDefinition = "number(18,9) NULL COMMENT '��˰����'")
    private Double taxPrice;
    /**
     * ----��˰�ܶ�---
     **/
    @Column(name = "taxSum", columnDefinition = "number(18,9) NULL COMMENT '��˰�ܶ�'")
    private Double taxSum;
    /**
     * ----��λID---
     **/
    @Column(name = "storeLocationId", columnDefinition = "Integer NULL COMMENT '��λID'")
    private Integer storeLocationId;
    /**
     * ----�ƻ�����---
     **/
    @Column(name = "planDepartId", columnDefinition = "Integer NULL COMMENT '�ƻ�����'")
    private Integer planDepartId;


    /**
     * ----��λ---
     **/
    @Column(name = "detailUnitName", columnDefinition = "nvarchar(20) NULL COMMENT '��λ'")
    private String detailUnitName;

    /**
     * ----�Ƿ����豸---
     **/
    @Column(name = "isEquipment", columnDefinition = "Integer NULL COMMENT '�Ƿ����豸'")
    private Integer isEquipment;
    /**
     * ----���Ϲ���---
     **/
    @Column(name = "specifications", columnDefinition = "nvarchar2(255) NULL COMMENT '���Ϲ���'")
    private String specifications;
    /**
     * ----�Ƿ���������---
     **/
    @Column(name = "enableSn", columnDefinition = "Integer NULL COMMENT '�Ƿ���������'")
    private Integer enableSn;
    /**
     * ----���к�---
     **/
    @Column(name = "snCode", columnDefinition = "nvarchar(50) NULL COMMENT '���к�'")
    private String snCode;

    @Column(name = "extendInt8")
    private Integer extendInt8;

    public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public Integer getExtendInt8() {
        return extendInt8;
    }

    public void setExtendInt8(Integer extendInt8) {
        this.extendInt8 = extendInt8;
    }

    @Column(name = "extendfloat1", columnDefinition = "number(18,9) NULL ")
    private Double extendFloat1;


    @Column(name = "storeLocationName")
    private String storeLocationName;

    @Column(name = "flName")
    private String flName;
    @Column(name = "jsCode")
    private String jsCode;


    @Column(name = "jsGuid")
    private String jsGuid;

    @Column(name = "ERPROWNUM")
    private String erpRownum;


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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
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

    public Double getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Double detailCount) {
        this.detailCount = detailCount;
    }

    public Integer getDetailUnit() {
        return detailUnit;
    }

    public void setDetailUnit(Integer detailUnit) {
        this.detailUnit = detailUnit;
    }

    public Integer getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(Integer currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public Integer getUpdater() {
        return updater;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

    public Double getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(Double noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }
    public String getNoTaxPriceDouble() {
        return NumberUtils.getDouble(noTaxPrice);
    }



    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getNoTaxSum() {
        return noTaxSum;
    }

    public void setNoTaxSum(Double noTaxSum) {
        this.noTaxSum = noTaxSum;
    }
    public String getNoTaxSumDouble() {
        return NumberUtils.getDouble(noTaxSum);
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

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getStoreLocationCode() {
        return storeLocationCode;
    }

    public void setStoreLocationCode(String storeLocationCode) {
        this.storeLocationCode = storeLocationCode;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public Double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(Double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Double getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(Double taxSum) {
        this.taxSum = taxSum;
    }

    public Integer getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(Integer storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    public Integer getPlanDepartId() {
        return planDepartId;
    }

    public void setPlanDepartId(Integer planDepartId) {
        this.planDepartId = planDepartId;
    }

    public String getDetailUnitName() {
        return detailUnitName;
    }

    public void setDetailUnitName(String detailUnitName) {
        this.detailUnitName = detailUnitName;
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

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public Double getExtendFloat1() {
        return extendFloat1;
    }

    public void setExtendFloat1(Double extendFloat1) {
        this.extendFloat1 = extendFloat1;
    }

    public String getStoreLocationName() {
        return storeLocationName;
    }

    public void setStoreLocationName(String storeLocationName) {
        this.storeLocationName = storeLocationName;
    }

    public String getFlName() {
        return flName;
    }

    public void setFlName(String flName) {
        this.flName = flName;
    }

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }

    public String getJsGuid() {
        return jsGuid;
    }

    public void setJsGuid(String jsGuid) {
        this.jsGuid = jsGuid;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getErpRownum() {
        return erpRownum;
    }

    public void setErpRownum(String erpRownum) {
        this.erpRownum = erpRownum;
    }

    public ThDetail() {

    }


} 