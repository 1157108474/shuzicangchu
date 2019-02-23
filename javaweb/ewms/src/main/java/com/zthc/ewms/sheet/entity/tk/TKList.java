package com.zthc.ewms.sheet.entity.tk;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: �˿ⵥ����
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_TKList")
public class TKList {
    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT '����'")
    private Integer id;
    /**----Ψһ��ʶ---**/
    @Column(name="guid", columnDefinition="nvarchar(64) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;

    @Column(name="sheetId")
    private Integer sheetId;

    @Column(name="sheetDetailId")
    private Integer sheetDetailId;

    @Column(name="categoryId")
    private Integer categoryId;


    /**----����ID---**/
    @Column(name="materialId", columnDefinition="Integer NULL COMMENT '����ID'")
    private Integer materialId;
    /**----���ϱ���---**/
    @Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '���ϱ���'")
    private String materialCode;
    /**----��������---**/
    @Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '��������'")
    private String materialName;
    /**----����Ʒ��---**/
    @Column(name="materialBrand", columnDefinition="nvarchar(255) NULL COMMENT '����Ʒ��'")
    private String materialBrand;
    /**----����ģ��---**/
    @Column(name="materialModel", columnDefinition="nvarchar(255) NULL COMMENT '����ģ��'")
    private String materialModel;


    /**----���Ϸ���---**/
    @Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '���Ϸ���'")
    private String materialSpecification;


    @Column(name="unseCount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
    private Double unseCount;
    @Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
    private Double detailCount;

    @Column(name="detailUnit", columnDefinition="Integer NULL COMMENT '״̬'")
    private Integer detailUnit;

    /**----storeId---**/
    @Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
    private Integer storeId;

    /**----��λID---**/
    @Column(name="storeLocationId", columnDefinition="Integer NULL COMMENT '��λID'")
    private Integer storeLocationId;
    /**----��λ����---**/
    @Column(name="storeLocationCode", columnDefinition="nvarchar(255) NULL COMMENT '��λ����'")
    private String storeLocationCode;


    /**----��λ����---**/
    @Column(name="storeLocationName", columnDefinition="nvarchar(50) NULL COMMENT '��λ����'")
    private String storeLocationName;


    /**----��Ӧ��Id---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '��Ӧ��Id'")
    private Integer providerDepId;
    /**----������---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '������'")
    private Integer creator;
    /**----����ʱ��---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createDate", columnDefinition="date NULL COMMENT '����ʱ��'")
    private Date createDate;


    /**----����˰����---**/
    @Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '����˰����'")
    private Double noTaxPrice;

    /**----˰��---**/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double taxRate;
    @Column(name = "notaxsum", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double noTaxSum;

    /**----����---**/
    @Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '����'")
    private String description;
    /**----expirationTime---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="expirationTime", columnDefinition="date NULL COMMENT 'expirationTime'")
    private Date expirationTime;
    @Column(name="tagCode", columnDefinition="nvarchar(255) NULL COMMENT '��ע'")
    private String tagCode;
    @Column(name="taxprice", columnDefinition="number(18,9) NULL COMMENT '˰��'")
    private Double taxPrice;

    @Column(name="taxsum")
    private Double taxSum;
    /**----�ƻ�����---**/
    @Column(name = "planDepartId", columnDefinition = "Integer NULL COMMENT '�ƻ�����'")
    private Integer planDepartId;

    @Column(name="houseName")
    private String houseName ;


    /**----ʹ�ò���---**/
    @Column(name="usedDepName")
    private String usedDepName ;



    /**----ʹ�õ�λID---**/
    @Column(name="usedDepartId", columnDefinition="Integer NULL COMMENT 'ʹ�õ�λID'")
    private Integer usedDepartId;

    /**----��λ---**/
    @Column(name="unitName", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
    private String unitName;

    /**----ʹ�ò���---**/
    @Column(name="sheetCode")
    private String sheetCode ;

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

    public Double getUnseCount() {
        return unseCount;
    }

    public void setUnseCount(Double unseCount) {
        this.unseCount = unseCount;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public Integer getProviderDepId() {
        return providerDepId;
    }

    public void setProviderDepId(Integer providerDepId) {
        this.providerDepId = providerDepId;
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

    public Double getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(Double noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
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

    public Integer getPlanDepartId() {
        return planDepartId;
    }

    public void setPlanDepartId(Integer planDepartId) {
        this.planDepartId = planDepartId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getUsedDepName() {
        return usedDepName;
    }

    public void setUsedDepName(String usedDepName) {
        this.usedDepName = usedDepName;
    }

    public Integer getUsedDepartId() {
        return usedDepartId;
    }

    public void setUsedDepartId(Integer usedDepartId) {
        this.usedDepartId = usedDepartId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSheetCode() {
        return sheetCode;
    }

    public void setSheetCode(String sheetCode) {
        this.sheetCode = sheetCode;
    }

    public TKList() {
    }


}