package com.zthc.ewms.sheet.entity.guard;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: ???????
 * @Package
 */
@Entity
@Table(name = "wz_sheetDetail")
public class SheetDetail implements Cloneable {

    /**
     * ----????---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT '????'")
    @SequenceGenerator(name = "generator", sequenceName = "WZSHEETDETAIL_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    private Integer id;
    /**
     * ----??????---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar(64) NULL COMMENT '??????'")
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
     * ----???????---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar(64) NULL COMMENT '???????'")
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


    @Column(name = "detailUnit", columnDefinition = "Integer NULL COMMENT '??'")
    private Integer detailUnit;

    @Column(name = "currencyUnit", columnDefinition = "nvarchar(20) NULL COMMENT '???'")
    private String currencyUnit;
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
     * ----extendInt1..??????ID---
     **/
    @Column(name = "extendInt1", columnDefinition = "Integer NULL COMMENT 'extendInt'")
    private Integer extendInt1;

    /**
     * ----extendInt2..???????---
     **/
    @Column(name = "extendInt2", columnDefinition = "Integer NULL ")
    private Integer extendInt2;

    /**
     * ----extendInt3..?????---
     **/
    @Column(name = "extendInt3", columnDefinition = "Integer NULL ")
    private Integer extendInt3;
    /**
     * ----extendInt4..??????????---
     **/
    @Column(name = "extendInt4", columnDefinition = "Integer NULL ")
    private Integer extendInt4;
    /**
     * ----extendInt5..????????---
     **/
    @Column(name = "extendInt5", columnDefinition = "Integer NULL ")
    private Integer extendInt5;
    /**
     * ----extendInt6..????????---
     **/
    @Column(name = "extendInt6", columnDefinition = "Integer NULL ")
    private Integer extendInt6;
    /**
     * ----extendInt1---
     **/
    @Column(name = "extendInt7", columnDefinition = "Integer NULL ")
    private Integer extendInt7;
    /**
     * ----extendInt1---
     **/
    @Column(name = "extendInt8", columnDefinition = "Integer NULL ")
    private Integer extendInt8;

    @Column(name = "extendfloat1", columnDefinition = "number(18,9) NULL ")
    private Double extendFloat1;
    @Column(name = "extendfloat2", columnDefinition = "number(18,9) NULL ")
    private Double extendFloat2;
    @Column(name = "extendfloat3", columnDefinition = "number(18,9) NULL ")
    private Double extendFloat3;
    @Column(name = "extendfloat4", columnDefinition = "number(18,9) NULL ")
    private Double extendFloat4;
    @Column(name = "extendfloat5", columnDefinition = "number(18,9) NULL ")
    private Double extendFloat5;
    @Column(name = "extendfloat6", columnDefinition = "number(18,9) NULL ")
    private Double extendFloat6;
    @Column(name = "extendfloat7", columnDefinition = "number(18,9) NULL ")
    private Double extendFloat7;
    @Column(name = "extendfloat8", columnDefinition = "number(18,9) NULL ")
    private Double extendFloat8;
    /**
     * ????
     */
    @Column(name = "extendstring1", columnDefinition = "nvarchar(255) NULL ")
    private String extendString1;
    @Column(name = "extendstring2", columnDefinition = "nvarchar(255) NULL ")
    private String extendString2;
    @Column(name = "extendstring3", columnDefinition = "nvarchar(255) NULL ")
    private String extendString3;
    @Column(name = "extendstring4", columnDefinition = "nvarchar(255) NULL ")
    private String extendString4;
    @Column(name = "extendstring5", columnDefinition = "nvarchar(255) NULL ")
    private String extendString5;
    @Column(name = "extendstring6", columnDefinition = "nvarchar(255) NULL ")
    private String extendString6;
    @Column(name = "extendstring7", columnDefinition = "nvarchar(255) NULL ")
    private String extendString7;
    @Column(name = "extendstring8", columnDefinition = "nvarchar(255) NULL ")
    private String extendString8;
    @Column(name = "extendstring9", columnDefinition = "nvarchar(255) NULL ")
    private String extendString9;

    @Column(name = "extendstring10", columnDefinition = "nvarchar(255) NULL ")
    private String extendString10;

    @Column(name = "extenddate1", columnDefinition = "date NULL ")
    private Date extendDate1;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "extenddate2", columnDefinition = "date NULL ")
    private Date extendDate21;
    @Column(name = "extenddate3", columnDefinition = "date NULL ")
    private Date extendDate3;
    @Column(name = "extenddate4", columnDefinition = "date NULL ")
    private Date extendDate4;


    /**
     * ----?????????---
     **/
    @Column(name = "noTaxPrice", columnDefinition = "number(18,9) NULL COMMENT '?????????'")
    private Double noTaxPrice;
    /**
     * ----???---
     **/
    @Column(name = "taxrate", columnDefinition = "number(18,9) NULL COMMENT '???'")
    private Double taxRate;
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
     * ----????---
     **/
    @Column(name = "description", columnDefinition = "nvarchar(500) NULL COMMENT '????'")
    private String description;
    /**
     * ----expirationTime---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "expirationTime", columnDefinition = "date NULL COMMENT 'expirationTime'")
    private Date expirationTime;

    /**
     * ----????????---
     **/
    @Column(name = "storeLocationCode", columnDefinition = "nvarchar(255) NULL COMMENT '????????'")
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
     * ----????ID---
     **/
    @Column(name = "storeLocationId", columnDefinition = "Integer NULL COMMENT '????ID'")
    private Integer storeLocationId;
    /**
     * ----???????---
     **/
    @Column(name = "planDepartId", columnDefinition = "Integer NULL COMMENT '???????'")
    private Integer planDepartId;


    /**
     * ----????????---
     **/
    @Column(name = "storeLocationName", columnDefinition = "nvarchar(50) NULL COMMENT '????????'")
    private String storeLocationName;
    /**
     * ----???????---
     **/
    @Column(name = "detailCount", columnDefinition = "number(18,9) NULL COMMENT '???????'")
    private Double detailCount;
    /**
     * ----????????---
     **/
    @Column(name = "isEquipment", columnDefinition = "Integer NULL COMMENT '????????'")
    private Integer isEquipment;

    /**
     * ----????---
     **/
    @Column(name = "ownerType", columnDefinition = "Integer NULL COMMENT '????'")
    private Integer ownerType;
    /**
     * ----???????????---
     **/
    @Column(name = "enableSn", columnDefinition = "Integer NULL COMMENT '???????????'")
    private Integer enableSn;
    /**
     * ----??????---
     **/
    @Column(name = "snCode", columnDefinition = "nvarchar(50) NULL COMMENT '??????'")
    private String snCode;
    /**
     * ----????????---
     **/
    @Column(name = "detailUnitName", columnDefinition = "nvarchar(20) NULL COMMENT '????????'")
    private String detailUnitName;

    @Transient
    private Integer sonId;


    private String sheetCode;
    
    
    public String getSheetCode() {
		return sheetCode;
	}

	public void setSheetCode(String sheetCode) {
		this.sheetCode = sheetCode;
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

    public Integer getDetailUnit() {
        return detailUnit;
    }

    public void setDetailUnit(Integer detailUnit) {
        this.detailUnit = detailUnit;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
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

    public void setZtId(Integer ZtId) {
        this.ztId = ZtId;
    }

    public Integer getExtendInt1() {
        return extendInt1;
    }

    public void setExtendInt1(Integer extendInt1) {
        this.extendInt1 = extendInt1;
    }

    public Integer getExtendInt2() {
        return extendInt2;
    }

    public void setExtendInt2(Integer extendInt2) {
        this.extendInt2 = extendInt2;
    }

    public Integer getExtendInt3() {
        return extendInt3;
    }

    public void setExtendInt3(Integer extendInt3) {
        this.extendInt3 = extendInt3;
    }

    public Integer getExtendInt4() {
        return extendInt4;
    }

    public void setExtendInt4(Integer extendInt4) {
        this.extendInt4 = extendInt4;
    }

    public Integer getExtendInt5() {
        return extendInt5;
    }

    public void setExtendInt5(Integer extendInt5) {
        this.extendInt5 = extendInt5;
    }

    public Integer getExtendInt6() {
        return extendInt6;
    }

    public void setExtendInt6(Integer extendInt6) {
        this.extendInt6 = extendInt6;
    }

    public Integer getExtendInt7() {
        return extendInt7;
    }

    public void setExtendInt7(Integer extendInt7) {
        this.extendInt7 = extendInt7;
    }

    public Integer getExtendInt8() {
        return extendInt8;
    }

    public void setExtendInt8(Integer extendInt8) {
        this.extendInt8 = extendInt8;
    }

    public Double getExtendFloat1() {
        return extendFloat1;
    }

    public void setExtendFloat1(Double extendFloat1) {
        this.extendFloat1 = extendFloat1;
    }

    public Double getExtendFloat2() {
        return extendFloat2;
    }

    public void setExtendFloat2(Double extendFloat2) {
        this.extendFloat2 = extendFloat2;
    }

    public Double getExtendFloat3() {
        return extendFloat3;
    }

    public void setExtendFloat3(Double extendFloat3) {
        this.extendFloat3 = extendFloat3;
    }

    public Double getExtendFloat4() {
        return extendFloat4;
    }

    public void setExtendFloat4(Double extendFloat4) {
        this.extendFloat4 = extendFloat4;
    }

    public Double getExtendFloat5() {
        return extendFloat5;
    }

    public void setExtendFloat5(Double extendFloat5) {
        this.extendFloat5 = extendFloat5;
    }

    public Double getExtendFloat6() {
        return extendFloat6;
    }

    public void setExtendFloat6(Double extendFloat6) {
        this.extendFloat6 = extendFloat6;
    }

    public Double getExtendFloat7() {
        return extendFloat7;
    }

    public void setExtendFloat7(Double extendFloat7) {
        this.extendFloat7 = extendFloat7;
    }

    public Double getExtendFloat8() {
        return extendFloat8;
    }

    public void setExtendFloat8(Double extendFloat8) {
        this.extendFloat8 = extendFloat8;
    }

    public String getExtendString1() {
        return extendString1;
    }

    public void setExtendString1(String extendString1) {
        this.extendString1 = extendString1;
    }

    public String getExtendString2() {
        return extendString2;
    }

    public void setExtendString2(String extendString2) {
        this.extendString2 = extendString2;
    }

    public String getExtendString3() {
        return extendString3;
    }

    public void setExtendString3(String extendString3) {
        this.extendString3 = extendString3;
    }

    public String getExtendString4() {
        return extendString4;
    }

    public void setExtendString4(String extendString4) {
        this.extendString4 = extendString4;
    }

    public String getExtendString5() {
        return extendString5;
    }

    public void setExtendString5(String extendString5) {
        this.extendString5 = extendString5;
    }

    public String getExtendString6() {
        return extendString6;
    }

    public void setExtendString6(String extendString6) {
        this.extendString6 = extendString6;
    }

    public String getExtendString7() {
        return extendString7;
    }

    public void setExtendString7(String extendString7) {
        this.extendString7 = extendString7;
    }

    public String getExtendString8() {
        return extendString8;
    }

    public void setExtendString8(String extendString8) {
        this.extendString8 = extendString8;
    }

    public String getExtendString9() {
        return extendString9;
    }

    public void setExtendString9(String extendString9) {
        this.extendString9 = extendString9;
    }

    public String getExtendString10() {
        return extendString10;
    }

    public void setExtendString10(String extendString10) {
        this.extendString10 = extendString10;
    }

    public Date getExtendDate1() {
        return extendDate1;
    }

    public void setExtendDate1(Date extendDate1) {
        this.extendDate1 = extendDate1;
    }

    public Date getExtendDate21() {
        return extendDate21;
    }

    public void setExtendDate21(Date extendDate21) {
        this.extendDate21 = extendDate21;
    }

    public Date getExtendDate3() {
        return extendDate3;
    }

    public void setExtendDate3(Date extendDate3) {
        this.extendDate3 = extendDate3;
    }

    public Date getExtendDate4() {
        return extendDate4;
    }

    public void setExtendDate4(Date extendDate4) {
        this.extendDate4 = extendDate4;
    }

    public Double getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(Double noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxrate) {
        this.taxRate = taxrate;
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


    public Integer getSonId() {
        return sonId;
    }

    public void setSonId(Integer sonId) {
        this.sonId = sonId;
    }

    public SheetDetail() {

    }


    public SheetDetail clone() {
        SheetDetail o = null;
        try {
            o = (SheetDetail) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

} 