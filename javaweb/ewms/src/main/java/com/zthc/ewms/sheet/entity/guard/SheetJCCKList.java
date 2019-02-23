package com.zthc.ewms.sheet.entity.guard;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/14.
 */
@Entity
@Table(name = "V_JCCKLIST")
public class SheetJCCKList {
    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**----Ψһ��ʶ---**/
    @Column(name="guid", columnDefinition="nvarchar2(36) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
    /**----������ϸid---**/
    @Column(name="SHEETDETAILID", columnDefinition="nvarchar2(36) NULL COMMENT '������ϸid'")
    private String sheetDetailId;
    /**----����id---**/
    @Column(name="sheetid", columnDefinition="Integer NULL COMMENT '����id'")
    private String sheetid;
    /**----ZTID---**/
    @Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
    private String ztId;
    /**----���ϴ�ӡ����---**/
    @Column(name = "tagCode", columnDefinition = "nvarchar2(50) NULL COMMENT '���ϴ�ӡ����'")
    private String tagCode;
    /**----����ID---**/
    @Column(name="materialId", columnDefinition="Integer NULL COMMENT '����ID'")
    private Integer materialId;
    /**----���ϱ���---**/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '���ϱ���'")
    private String materialCode;
    /**----��������---**/
    @Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '��������'")
    private String materialName;
    /*** ----���Ϸ���ID---**/
    @Column(name = "categoryId", columnDefinition = "Integer NULL COMMENT '���Ϸ���ID'")
    private Integer categoryId;
    /*** ----�Ĵ沿��---**/
    @Column(name = "OWNERDEP", columnDefinition = "Integer NULL COMMENT '�Ĵ沿��'")
    private Integer ownerdep;
    /*** ----�ƻ�����ID---**/
    @Column(name = "PLANDEPARTID", columnDefinition = "Integer NULL COMMENT '�ƻ�����ID'")
    private Integer plandePartId;
    /**----����Ʒ��---**/
    @Column(name="materialBrand", columnDefinition="nvarchar(255) NULL COMMENT '����Ʒ��'")
    private String materialBrand;
    /**----�����ͺ�---**/
    @Column(name="materialModel", columnDefinition="nvarchar(255) NULL COMMENT '�����ͺ�'")
    private String materialModel;
    /**----���Ϲ��---**/
    @Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '���Ϲ��'")
    private String materialSpecification;
    /**----��ϸ��λ---**/
    @Column(name="DETAILUNIT", columnDefinition="nvarchar2(50) NULL COMMENT '��ϸ��λ'")
    private String detailUnit;
    /**----��ϸ��λ---**/
    @Column(name="UNITNAME", columnDefinition="nvarchar2(50) NULL COMMENT '��ϸ��λ'")
    private String unitName;
    /**----�ⷿ---**/
    @Column(name="houseName", columnDefinition="nvarchar2(50) NULL COMMENT '�ⷿ'")
    private String houseName;
    /**----�ⷿ---**/
    @Column(name="STOREID", columnDefinition="nvarchar2(50) NULL COMMENT '�ⷿ'")
    private Integer storeId;
    /**----��λid---**/
    @Column(name="storelocationId", columnDefinition="nvarchar2(50) NULL COMMENT '��λid'")
    private String storelocationId;
    /**----��λcode---**/
    @Column(name="storelocationCode", columnDefinition="nvarchar2(50) NULL COMMENT '��λcode'")
    private String storelocationCode;
    /**----��λname---**/
    @Column(name="storeLocationName", columnDefinition="nvarchar2(50) NULL COMMENT '��λ'")
    private String storelocationName;
    /**----����˰����---**/
    @Column(name="notaxprice", columnDefinition="number(18,9) NULL COMMENT '����˰����'")
    private Double notaxprice;
    /**----��˰����---**/
    @Column(name="taxprice", columnDefinition="number(18,9) NULL COMMENT '��˰����'")
    private Double taxprice;
    /**----˰��---**/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double taxRate;
    /**----��������---**/
    @Column(name="description", columnDefinition="nvarchar2(500) NULL COMMENT '��������'")
    private String description;
    /**----������ID---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '������ID'")
    private Integer creator;
    /**----����ʱ��---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createDate", columnDefinition="datetime NULL COMMENT '����ʱ��'")
    private Date createDate;
    /**----�Ĵ浥λ---**/
    @Column(name="ownerdepName", columnDefinition="nvarchar2(500) NULL COMMENT '�Ĵ浥λ'")
    private String ownerdepName;
    @Column(name="planName")
    private String planName;
    @Column(name="unseCount")
    private Double unseCount;
    @Column(name="storeCount")
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

    public String getSheetDetailId() {
        return sheetDetailId;
    }

    public void setSheetDetailId(String sheetDetailId) {
        this.sheetDetailId = sheetDetailId;
    }

    public String getSheetid() {
        return sheetid;
    }

    public void setSheetid(String sheetid) {
        this.sheetid = sheetid;
    }

    public String getZtId() {
        return ztId;
    }

    public void setZtId(String ztId) {
        this.ztId = ztId;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getOwnerdep() {
        return ownerdep;
    }

    public void setOwnerdep(Integer ownerdep) {
        this.ownerdep = ownerdep;
    }

    public Integer getPlandePartId() {
        return plandePartId;
    }

    public void setPlandePartId(Integer plandePartId) {
        this.plandePartId = plandePartId;
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

    public String getDetailUnit() {
        return detailUnit;
    }

    public void setDetailUnit(String detailUnit) {
        this.detailUnit = detailUnit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStorelocationId() {
        return storelocationId;
    }

    public void setStorelocationId(String storelocationId) {
        this.storelocationId = storelocationId;
    }

    public String getStorelocationCode() {
        return storelocationCode;
    }

    public void setStorelocationCode(String storelocationCode) {
        this.storelocationCode = storelocationCode;
    }

    public String getStorelocationName() {
        return storelocationName;
    }

    public void setStorelocationName(String storelocationName) {
        this.storelocationName = storelocationName;
    }

    public Double getNotaxprice() {
        return notaxprice;
    }

    public void setNotaxprice(Double notaxprice) {
        this.notaxprice = notaxprice;
    }

    public Double getTaxprice() {
        return taxprice;
    }

    public void setTaxprice(Double taxprice) {
        this.taxprice = taxprice;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getOwnerdepName() {
        return ownerdepName;
    }

    public void setOwnerdepName(String ownerdepName) {
        this.ownerdepName = ownerdepName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Double getUnseCount() {
        return unseCount;
    }

    public void setUnseCount(Double unseCount) {
        this.unseCount = unseCount;
    }

    public Double getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Double storeCount) {
        this.storeCount = storeCount;
    }

    public SheetJCCKList() {
    }

    public SheetJCCKList(Integer id, String guid, String sheetDetailId, String sheetid, String ztId, String tagCode, Integer materialId, String materialCode, String materialName, Integer categoryId, Integer ownerdep, Integer plandePartId, String materialBrand, String materialModel, String materialSpecification, String detailUnit, String unitName, String houseName, Integer storeId, String storelocationId, String storelocationCode, String storelocationName, Double notaxprice, Double taxprice, Double taxRate, String description, Integer creator, Date createDate, String ownerdepName, String planName, Double unseCount, Double storeCount) {
        this.id = id;
        this.guid = guid;
        this.sheetDetailId = sheetDetailId;
        this.sheetid = sheetid;
        this.ztId = ztId;
        this.tagCode = tagCode;
        this.materialId = materialId;
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.categoryId = categoryId;
        this.ownerdep = ownerdep;
        this.plandePartId = plandePartId;
        this.materialBrand = materialBrand;
        this.materialModel = materialModel;
        this.materialSpecification = materialSpecification;
        this.detailUnit = detailUnit;
        this.unitName = unitName;
        this.houseName = houseName;
        this.storeId = storeId;
        this.storelocationId = storelocationId;
        this.storelocationCode = storelocationCode;
        this.storelocationName = storelocationName;
        this.notaxprice = notaxprice;
        this.taxprice = taxprice;
        this.taxRate = taxRate;
        this.description = description;
        this.creator = creator;
        this.createDate = createDate;
        this.ownerdepName = ownerdepName;
        this.planName = planName;
        this.unseCount = unseCount;
        this.storeCount = storeCount;
    }
}
