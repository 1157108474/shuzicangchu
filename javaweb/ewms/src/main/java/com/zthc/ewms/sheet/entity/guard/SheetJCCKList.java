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
    /**----唯一标识---**/
    @Column(name="guid", columnDefinition="nvarchar2(36) NULL COMMENT '唯一标识'")
    private String guid;
    /**----单据明细id---**/
    @Column(name="SHEETDETAILID", columnDefinition="nvarchar2(36) NULL COMMENT '单据明细id'")
    private String sheetDetailId;
    /**----主表id---**/
    @Column(name="sheetid", columnDefinition="Integer NULL COMMENT '主表id'")
    private String sheetid;
    /**----ZTID---**/
    @Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
    private String ztId;
    /**----材料打印编码---**/
    @Column(name = "tagCode", columnDefinition = "nvarchar2(50) NULL COMMENT '材料打印编码'")
    private String tagCode;
    /**----物料ID---**/
    @Column(name="materialId", columnDefinition="Integer NULL COMMENT '物料ID'")
    private Integer materialId;
    /**----物料编码---**/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '物料编码'")
    private String materialCode;
    /**----物料名称---**/
    @Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '物料名称'")
    private String materialName;
    /*** ----物料分类ID---**/
    @Column(name = "categoryId", columnDefinition = "Integer NULL COMMENT '物料分类ID'")
    private Integer categoryId;
    /*** ----寄存部门---**/
    @Column(name = "OWNERDEP", columnDefinition = "Integer NULL COMMENT '寄存部门'")
    private Integer ownerdep;
    /*** ----计划部门ID---**/
    @Column(name = "PLANDEPARTID", columnDefinition = "Integer NULL COMMENT '计划部门ID'")
    private Integer plandePartId;
    /**----物料品牌---**/
    @Column(name="materialBrand", columnDefinition="nvarchar(255) NULL COMMENT '物料品牌'")
    private String materialBrand;
    /**----物料型号---**/
    @Column(name="materialModel", columnDefinition="nvarchar(255) NULL COMMENT '物料型号'")
    private String materialModel;
    /**----物料规格---**/
    @Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '物料规格'")
    private String materialSpecification;
    /**----明细单位---**/
    @Column(name="DETAILUNIT", columnDefinition="nvarchar2(50) NULL COMMENT '明细单位'")
    private String detailUnit;
    /**----明细单位---**/
    @Column(name="UNITNAME", columnDefinition="nvarchar2(50) NULL COMMENT '明细单位'")
    private String unitName;
    /**----库房---**/
    @Column(name="houseName", columnDefinition="nvarchar2(50) NULL COMMENT '库房'")
    private String houseName;
    /**----库房---**/
    @Column(name="STOREID", columnDefinition="nvarchar2(50) NULL COMMENT '库房'")
    private Integer storeId;
    /**----库位id---**/
    @Column(name="storelocationId", columnDefinition="nvarchar2(50) NULL COMMENT '库位id'")
    private String storelocationId;
    /**----库位code---**/
    @Column(name="storelocationCode", columnDefinition="nvarchar2(50) NULL COMMENT '库位code'")
    private String storelocationCode;
    /**----库位name---**/
    @Column(name="storeLocationName", columnDefinition="nvarchar2(50) NULL COMMENT '库位'")
    private String storelocationName;
    /**----不含税单价---**/
    @Column(name="notaxprice", columnDefinition="number(18,9) NULL COMMENT '不含税单价'")
    private Double notaxprice;
    /**----含税单价---**/
    @Column(name="taxprice", columnDefinition="number(18,9) NULL COMMENT '含税单价'")
    private Double taxprice;
    /**----税率---**/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '税率'")
    private Double taxRate;
    /**----物料描述---**/
    @Column(name="description", columnDefinition="nvarchar2(500) NULL COMMENT '物料描述'")
    private String description;
    /**----创建人ID---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人ID'")
    private Integer creator;
    /**----创建时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createDate", columnDefinition="datetime NULL COMMENT '创建时间'")
    private Date createDate;
    /**----寄存单位---**/
    @Column(name="ownerdepName", columnDefinition="nvarchar2(500) NULL COMMENT '寄存单位'")
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
