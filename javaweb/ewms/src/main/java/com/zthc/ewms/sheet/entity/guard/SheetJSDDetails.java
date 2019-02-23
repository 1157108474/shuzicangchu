package com.zthc.ewms.sheet.entity.guard;

import com.zthc.ewms.base.util.NumberUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "v_jsdetails")
public class SheetJSDDetails {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    @Column(name = "rn", columnDefinition = "Integer NOT NULL COMMENT 'rn'")
    private Integer rn;
    /**
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(36) NULL COMMENT '唯一标识'")
    private String guid;
    /**
     * ----????ID---
     **/
    @Column(name = "sheetId", columnDefinition = "Integer NULL COMMENT '????ID'")
    private Integer sheetId;
    /**
     * ----???????ID---
     **/
    @Column(name = "sheetDetailId", columnDefinition = "Integer NULL COMMENT '???????ID'")
    private Integer sheetDetailId;
    /**
     * ----   categoryId---
     **/
    @Column(name = "categoryId", columnDefinition = "Integer NULL COMMENT '   categoryId'")
    private Integer categoryId;
    /**
     * ----????ID---
     **/
    @Column(name = "materialId", columnDefinition = "Integer NULL COMMENT '????ID'")
    private Integer materialId;
    /**
     * ----物料编码---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar(64) NULL COMMENT '物料编码'")
    private String materialCode;
    /**
     * ----????????---
     **/
    @Column(name = "materialName", columnDefinition = "nvarchar(255) NULL COMMENT '????????'")
    private String materialName;
    /**
     * ----???????---
     **/
    @Column(name = "materialBrand", columnDefinition = "nvarchar(255) NULL COMMENT '???????'")
    private String materialBrand;
    /**
     * ----???????---
     **/
    @Column(name = "materialModel", columnDefinition = "nvarchar(255) NULL COMMENT '???????'")
    private String materialModel;
    /**
     * ----storeId---
     **/
    @Column(name = "storeId", columnDefinition = "Integer NULL COMMENT 'storeId'")
    private Integer storeId;
    /**
     * ----?????Id---
     **/
    @Column(name = "providerDepId", columnDefinition = "Integer NULL COMMENT '?????Id'")
    private Integer providerDepId;
    /**
     * ----??---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '??'")
    private Integer status;
    /**
     * ----???---
     **/
    @Column(name = "memo", columnDefinition = "nvarchar(255) NULL COMMENT '???'")
    private String memo;
    /**
     * ----??????---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '??????'")
    private Integer creator;
    /**
     * ----???????---
     **/
    @Column(name = "createDate", columnDefinition = "Date NULL COMMENT '???????'")
    private Date createDate;
    /**
     * ----??????---
     **/
    @Column(name = "updator", columnDefinition = "Integer NULL COMMENT '??????'")
    private Integer updater;
    /**
     * ----???????---
     **/
    @Column(name = "updateDate", columnDefinition = "Date NULL COMMENT '???????'")
    private Date updateDate;
    /**
     * ----ZtId---
     **/
    @Column(name = "ZtId", columnDefinition = "Integer NULL COMMENT 'ZtId'")
    private Integer ztId;
    /**
     * ----单价---
     **/
    @Column(name = "noTaxPrice", columnDefinition = "number(18,9) NULL COMMENT '单价'")
    private Double noTaxPrice;
    /**
     * ----???---
     **/
    @Column(name = "taxrate", columnDefinition = "number(18,9) NULL COMMENT '???'")
    private Double taxrate;
    /**
     * ----????????---
     **/
    @Column(name = "noTaxSum", columnDefinition = "number(18,9) NULL COMMENT '????????'")
    private Double noTaxSum;
    /**
     * ----???????---
     **/
    @Column(name = "materialSpecification", columnDefinition = "nvarchar(255) NULL COMMENT '???????'")
    private String materialSpecification;
    /**
     * ----物料描述---
     **/
    @Column(name = "description", columnDefinition = "nvarchar(500) NULL COMMENT '物料描述'")
    private String description;
    /**
     * ----expirationTime---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "expirationTime", columnDefinition = "date NULL COMMENT 'expirationTime'")
    private Date expirationTime;

    /**
     * ----??λ????---
     **/
    @Column(name = "storeLocationCode", columnDefinition = "nvarchar(255) NULL COMMENT '??λ????'")
    private String storeLocationCode;
    /**
     * ----????---
     **/
    @Column(name = "tagCode", columnDefinition = "nvarchar(50) NULL COMMENT '????'")
    private String tagCode;
    /**
     * ----???????---
     **/
    @Column(name = "taxPrice", columnDefinition = "number(18,9) NULL COMMENT '???????'")
    private Double taxPrice;
    /**
     * ----??????---
     **/
    @Column(name = "taxSum", columnDefinition = "number(18,9) NULL COMMENT '??????'")
    private Double taxSum;
    /**
     * ----??λID---
     **/
    @Column(name = "storeLocationId", columnDefinition = "Integer NULL COMMENT '??λID'")
    private Integer storeLocationId;
    /**
     * ----???????---
     **/
    @Column(name = "planDepartId", columnDefinition = "Integer NULL COMMENT '???????'")
    private Integer planDepartId;

    @Column(name = "planDepartName", columnDefinition = "nvarchar(255) NULL")
    private String planDepartName;


    /**
     * ----??λ????---
     **/
    @Column(name = "storeLocationName", columnDefinition = "nvarchar(50) NULL COMMENT '??λ????'")
    private String storeLocationName;
    /**
     * ----接收数量---
     **/
    @Column(name = "detailCount", columnDefinition = "number(18,9) NULL COMMENT '接收数量'")
    private Double detailCount;
    /**
     * ----单位---
     **/
    @Column(name = "detailUnitName", columnDefinition = "nvarchar(20) NULL COMMENT '单位'")
    private String detailUnitName;

    @Column(name = "ownerType", columnDefinition = "Integer NULL COMMENT '是否启用序列'")
    private Integer ownerType;
    /**
     * ----是否寄售---
     **/
    @Column(name = "ownerName", columnDefinition = "nvarchar(20) NULL COMMENT '是否寄售'")
    private String ownerName;
    /**
     * ----是否设备---
     **/
    @Column(name = "isEquipment", columnDefinition = "Integer NULL COMMENT '是否设备'")
    private Integer isEquipment;
    /**
     * ----是否启用序列号---
     **/
    @Column(name = "enableSn", columnDefinition = "Integer NULL COMMENT '是否启用序列号'")
    private Integer enableSn;
    /**
     * ----退货数量---
     **/
    @Column(name = "thCount", columnDefinition = "Integer NULL COMMENT '退货数量'")
    private Integer thCount;

    public String getPlanDepartName() {
        return planDepartName;
    }

    public void setPlanDepartName(String planDepartName) {
        this.planDepartName = planDepartName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRn() {
        return rn;
    }

    public void setRn(Integer rn) {
        this.rn = rn;
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
    public String getNoTaxPriceDuble() {
        return NumberUtils.getDouble(this.noTaxPrice);
    }



    public Double getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(Double taxrate) {
        this.taxrate = taxrate;
    }

    public Double getNoTaxSum() {
        return noTaxSum;
    }

    public void setNoTaxSum(Double noTaxSum) {
        this.noTaxSum = noTaxSum;
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

    public String getStoreLocationName() {
        return storeLocationName;
    }

    public void setStoreLocationName(String storeLocationName) {
        this.storeLocationName = storeLocationName;
    }

    public Double getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Double detailCount) {
        this.detailCount = detailCount;
    }

    public String getDetailUnitName() {
        return detailUnitName;
    }

    public void setDetailUnitName(String detailUnitName) {
        this.detailUnitName = detailUnitName;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public Integer getThCount() {
        return thCount;
    }

    public void setThCount(Integer thCount) {
        this.thCount = thCount;
    }

    public SheetJSDDetails() {
    }


}
