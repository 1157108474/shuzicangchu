package com.zthc.ewms.sheet.entity.guard;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WZ_STOCK")
public class SheetStock {

    /**
     * ----id---
     **/
    @Id
    @SequenceGenerator(name = "generator", sequenceName = "WZSTOCK_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT '唯一标识'")
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
     * ----����ID---
     **/
    @Column(name = "materialId", columnDefinition = "Integer NULL COMMENT '����ID'")
    private Integer materialId;
    /**
     * ----���ϱ���---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar(64) NULL COMMENT '���ϱ���'")
    private String materialCode;
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


    @Column(name = "detailUnit", columnDefinition = "Integer NULL COMMENT '״̬'")
    private Integer detailUnit;

    @Column(name = "currencyUnit", columnDefinition = "nvarchar(20) NULL COMMENT '��ע'")
    private String currencyUnit;
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
    /**
     * ----״̬---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '״̬'")
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
    @Column(name = "createDate", columnDefinition = "Date NULL COMMENT '����ʱ��'")
    private Date createDate;
    /**
     * ----������---
     **/
    @Column(name = "updator", columnDefinition = "Integer NULL COMMENT '������'")
    private Integer updator;
    /**
     * ----����ʱ��---
     **/
    @Column(name = "updateDate", columnDefinition = "Date NULL COMMENT '����ʱ��'")
    private Date updateDate;
    /**
     * ----ZTID---
     **/
    @Column(name = "ZTID", columnDefinition = "Integer NULL COMMENT 'ZTID'")
    private Integer ZTID;


    /**
     * ----extendInt1..ʹ�õ�λID---
     **/
    @Column(name = "extendInt1", columnDefinition = "Integer NULL COMMENT 'extendInt'")
    private Integer extendInt1;

    /**
     * ----extendInt2..�ƻ�����---
     **/
    @Column(name = "extendInt2", columnDefinition = "Integer NULL ")
    private Integer extendInt2;

    /**
     * ----extendInt3..�����---
     **/
    @Column(name = "extendInt3", columnDefinition = "Integer NULL ")
    private Integer extendInt3;
    /**
     * ----extendInt4..����������---
     **/
    @Column(name = "extendInt4", columnDefinition = "Integer NULL ")
    private Integer extendInt4;
    /**
     * ----extendInt5..��������---
     **/
    @Column(name = "extendInt5", columnDefinition = "Integer NULL ")
    private Integer extendInt5;
    /**
     * ----extendInt6..��������---
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
    /**
     * ��λ
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
     * ----��������---
     **/
    @Column(name = "orderNum", columnDefinition = "nvarchar(200) NULL COMMENT '��������'")
    private String orderNum;

    /**
     * ----���յ���---
     **/
    @Column(name = "receiveNum", columnDefinition = "nvarchar(200) NULL COMMENT '���յ���'")
    private String receiveNum;
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
     * ----��λ����---
     **/
    @Column(name = "storeLocationName", columnDefinition = "nvarchar(50) NULL COMMENT '��λ����'")
    private String storeLocationName;
    /**
     * ----�ƻ�����---
     **/
    @Column(name = "planDepartID", columnDefinition = "Integer NULL COMMENT '�ƻ�����'")
    private Integer planDepartID;
    /**
     * ----��λID---
     **/
    @Column(name = "storeLocationId", columnDefinition = "Integer NULL COMMENT '��λID'")
    private Integer storeLocationId;
    /**
     * ----   categoryId---
     **/
    @Column(name = "categoryId", columnDefinition = "Integer NULL COMMENT '   categoryId'")
    private Integer categoryId;
    /**
     * ----ownerDep---
     **/
    @Column(name = "ownerDep", columnDefinition = "Integer NULL COMMENT 'ownerDep'")
    private Integer ownerDep;
    @Column(name = "purchaseType", columnDefinition = "Integer NULL COMMENT '类型'")
    private Integer purchaseType;
    /**
     * ----�Ƿ����豸---
     **/
    @Column(name = "isEquipment", columnDefinition = "Integer NULL COMMENT '�Ƿ����豸'")
    private Integer isEquipment;
    /**
     * ----����---
     **/
    @Column(name = "ownerType", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer ownerType;
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
    /**
     * ----��λ����---
     **/
    @Column(name = "detailUnitName", columnDefinition = "nvarchar(20) NULL COMMENT '��λ����'")
    private String detailUnitName;
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
     * ----����˰����---
     **/
    @Column(name = "noTaxPrice", columnDefinition = "number(18,9) NULL COMMENT '����˰����'")
    private Double noTaxPrice;
    /**
     * ----��˰����---
     **/
    @Column(name = "taxPrice", columnDefinition = "number(18,9) NULL COMMENT '��˰����'")
    private Double taxPrice;
    @Column(name = "detailPrice", columnDefinition = "number(18,9) NULL ")
    private Double detailPrice;
    @Column(name = "detailSum", columnDefinition = "number(18,9) NULL ")
    private Double detailSum;
    @Column(name = "storeCount", columnDefinition = "number(18,9) NULL ")
    private Double storeCount;

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

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getZTID() {
        return ZTID;
    }

    public void setZTID(Integer ZTID) {
        this.ZTID = ZTID;
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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(String receiveNum) {
        this.receiveNum = receiveNum;
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

    public String getStoreLocationName() {
        return storeLocationName;
    }

    public void setStoreLocationName(String storeLocationName) {
        this.storeLocationName = storeLocationName;
    }

    public Integer getPlanDepartID() {
        return planDepartID;
    }

    public void setPlanDepartID(Integer planDepartID) {
        this.planDepartID = planDepartID;
    }

    public Integer getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(Integer storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getOwnerDep() {
        return ownerDep;
    }

    public void setOwnerDep(Integer ownerDep) {
        this.ownerDep = ownerDep;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
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

    public Double getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(Double noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }

    public Double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(Double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Double getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(Double detailPrice) {
        this.detailPrice = detailPrice;
    }

    public Double getDetailSum() {
        return detailSum;
    }

    public void setDetailSum(Double detailSum) {
        this.detailSum = detailSum;
    }

    public Double getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Double storeCount) {
        this.storeCount = storeCount;
    }

    public SheetStock() {
    }
}
