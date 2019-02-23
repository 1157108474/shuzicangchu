package com.zthc.ewms.sheet.entity.guard;

import com.zthc.ewms.base.util.NumberUtils;
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
@Table(name = "V_JCRKDetails")
public class SheetJCRKDetails {
    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**----唯一标识---**/
    @Column(name="guid", columnDefinition="nvarchar2(36) NULL COMMENT '唯一标识'")
    private String guid;
    /**----主表id---**/
    @Column(name="sheetid", columnDefinition="Integer NULL COMMENT '主表id'")
    private String sheetid;
    /**----单据明细id---**/
    @Column(name="SHEETDETAILID", columnDefinition="nvarchar2(36) NULL COMMENT '单据明细id'")
    private String SHEETDETAILID;
    /**----ZTID---**/
    @Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
    private String ZTID;
    /**----材料打印编码---**/
    @Column(name = "tagCode", columnDefinition = "nvarchar2(50) NULL COMMENT '材料打印编码'")
    private String tagCode;
    /**----物料ID---**/
    @Column(name="materialId", columnDefinition="Integer NULL COMMENT '物料ID'")
    private Integer materialId;
    /*** ----物料分类ID---**/
    @Column(name = "categoryId", columnDefinition = "Integer NULL COMMENT '物料分类ID'")
    private Integer categoryId;
    /**----物料编码---**/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '物料编码'")
    private String materialCode;
    /**----物料名称---**/
    @Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '物料名称'")
    private String materialName;
    /**----物料类型---**/
    @Column(name="typeName", columnDefinition="nvarchar(255) NULL COMMENT '物料类型'")
    private String typeName;
    /**----物料品牌---**/
    @Column(name="materialBrand", columnDefinition="nvarchar(255) NULL COMMENT '物料品牌'")
    private String materialBrand;
    /**----物料型号---**/
    @Column(name="materialModel", columnDefinition="nvarchar(255) NULL COMMENT '物料型号'")
    private String materialModel;
    /**----物料规格---**/
    @Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '物料规格'")
    private String materialSpecification;
    /**----明细数量---**/
    @Column(name="detailcount", columnDefinition="number(18,9) NULL COMMENT '明细数量'")
    private Double detailcount;
    /**----明细单位---**/
    @Column(name="detailUnitName", columnDefinition="nvarchar2(50) NULL COMMENT '明细单位'")
    private String detailUnitName;
    /**----库房---**/
    @Column(name="STOREID", columnDefinition="nvarchar2(50) NULL COMMENT '库房'")
    private Integer STOREID;
    /**----库房---**/
    @Column(name="houseName", columnDefinition="nvarchar2(50) NULL COMMENT '库房'")
    private String houseName;
    /**----库位id---**/
    @Column(name="STORELOCATIONID", columnDefinition="nvarchar2(50) NULL COMMENT '库位id'")
    private String STORELOCATIONID;
    /**----库位name---**/
    @Column(name="storeLocationName", columnDefinition="nvarchar2(50) NULL COMMENT '库位name'")
    private String storeLocationName;
    /**----库位code---**/
    @Column(name="STORELOCATIONCODE", columnDefinition="nvarchar2(50) NULL COMMENT '库位code'")
    private String STORELOCATIONCODE;
    /**----不含税单价---**/
    @Column(name="notaxprice", columnDefinition="number(18,9) NULL COMMENT '不含税单价'")
    private Double notaxprice;
    /**----含税单价---**/
    @Column(name="taxprice", columnDefinition="number(18,9) NULL COMMENT '含税单价'")
    private Double taxprice;
    /**----税率---**/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '税率'")
    private Double taxRate;
    /**----不含税总金额---**/
    @Column(name="notaxsum", columnDefinition="number(18,9) NULL COMMENT '不含税总金额'")
    private Double notaxsum;
    /**----含税总金额---**/
    @Column(name="taxsum", columnDefinition="number(18,9) NULL COMMENT '不含税总金额'")
    private Double taxsum;
    /**----物料描述---**/
    @Column(name="description", columnDefinition="nvarchar2(500) NULL COMMENT '物料描述'")
    private String description;
    /**----创建人ID---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人ID'")
    private Integer creator;
    /**----创建时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createdate", columnDefinition="datetime NULL COMMENT '创建时间'")
    private Date createdate;
    /**----状态---**/
    @Column(name="STATUS", columnDefinition="Integer NULL COMMENT '状态'")
    private Integer STATUS;
    /**----备注---**/
    @Column(name="MEMO", columnDefinition="nvarchar2(500) NULL COMMENT '备注'")
    private String MEMO;
    /**----单位---**/
    @Column(name="UNITNAME", columnDefinition="nvarchar2(50) NULL COMMENT '单位'")
    private String UNITNAME;

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

    public String getSheetid() {
        return sheetid;
    }

    public void setSheetid(String sheetid) {
        this.sheetid = sheetid;
    }

    public String getZTID() {
        return ZTID;
    }

    public void setZTID(String ZTID) {
        this.ZTID = ZTID;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public Double getDetailcount() {
        return detailcount;
    }

    public void setDetailcount(Double detailcount) {
        this.detailcount = detailcount;
    }

    public String getDetailUnitName() {
        return detailUnitName;
    }

    public void setDetailUnitName(String detailUnitName) {
        this.detailUnitName = detailUnitName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStoreLocationName() {
        return storeLocationName;
    }

    public void setStoreLocationName(String storeLocationName) {
        this.storeLocationName = storeLocationName;
    }

    public Double getNotaxprice() {
        return notaxprice;
    }

    public void setNotaxprice(Double notaxprice) {
        this.notaxprice = notaxprice;
    }

    public String getNotaxpriceDouble() {
        return NumberUtils.getDouble(notaxprice);
    }


    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getNotaxsum() {
        return notaxsum;
    }

    public void setNotaxsum(Double notaxsum) {
        this.notaxsum = notaxsum;
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getSHEETDETAILID() {
        return SHEETDETAILID;
    }

    public void setSHEETDETAILID(String SHEETDETAILID) {
        this.SHEETDETAILID = SHEETDETAILID;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSTOREID() {
        return STOREID;
    }

    public void setSTOREID(Integer STOREID) {
        this.STOREID = STOREID;
    }

    public String getSTORELOCATIONID() {
        return STORELOCATIONID;
    }

    public void setSTORELOCATIONID(String STORELOCATIONID) {
        this.STORELOCATIONID = STORELOCATIONID;
    }

    public String getSTORELOCATIONCODE() {
        return STORELOCATIONCODE;
    }

    public void setSTORELOCATIONCODE(String STORELOCATIONCODE) {
        this.STORELOCATIONCODE = STORELOCATIONCODE;
    }

    public Double getTaxprice() {
        return taxprice;
    }

    public void setTaxprice(Double taxprice) {
        this.taxprice = taxprice;
    }

    public Double getTaxsum() {
        return taxsum;
    }

    public void setTaxsum(Double taxsum) {
        this.taxsum = taxsum;
    }

    public Integer getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }

    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }

    public String getUNITNAME() {
        return UNITNAME;
    }

    public void setUNITNAME(String UNITNAME) {
        this.UNITNAME = UNITNAME;
    }
}
