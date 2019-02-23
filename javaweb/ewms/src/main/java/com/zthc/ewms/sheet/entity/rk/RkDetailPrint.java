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
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT '唯一标识'")
    private String guid;
    /**
     * ----单据ID---
     **/
    @Column(name = "sheetId", columnDefinition = "Integer NULL COMMENT '单据ID'")
    private Integer sheetId;
    /**
     * ----单据明细ID---
     **/
    @Column(name = "sheetDetailId", columnDefinition = "Integer NULL COMMENT '单据明细ID'")
    private Integer sheetDetailId;
    /**
     * ----物料分类ID---
     **/
    @Column(name = "categoryId", columnDefinition = "Integer NULL COMMENT '物料分类ID'")
    private Integer categoryId;
    /**
     * ----物料ID---
     **/
    @Column(name = "materialId", columnDefinition = "Integer NULL COMMENT '物料ID'")
    private Integer materialId;
    /**
     * ----物料编码---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '物料编码'")
    private String materialCode;
    /**
     * ----物料名称---
     **/
    @Column(name = "materialName", columnDefinition = "nvarchar2(255) NULL COMMENT '物料名称'")
    private String materialName;
    /**
     * ----物料品牌---
     **/
    @Column(name = "materialBrand", columnDefinition = "nvarchar2(255) NULL COMMENT '物料品牌'")
    private String materialBrand;
    /**
     * ----物料规格---
     **/
    @Column(name = "materialModel", columnDefinition = "nvarchar2(255) NULL COMMENT '物料规格'")
    private String materialModel;
    /**
     * ----物料规格---
     **/
    @Column(name = "materialSpecification", columnDefinition = "nvarchar2(255) NULL COMMENT '物料规格'")
    private String materialSpecification;
    /**
     * ----创建人ID---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '创建人ID'")
    private Integer creator;
    /**
     * ----创建时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "datetime NULL COMMENT '创建时间'")
    private Date createDate;
    /**
     * ----库存组织ID---
     **/
    @Column(name = "ztId", columnDefinition = "Integer NULL COMMENT '库存组织ID'")
    private Integer ztId;
    /**
     * ----不含税单价---
     **/
    @Column(name = "notaxPrice", columnDefinition = "number(18,9) NULL COMMENT '不含税单价'")
    private Double notaxPrice;
    /**
     * ----税率---
     **/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '税率'")
    private Double taxRate;
    /**
     * ----不含税金额---
     **/
    @Column(name = "notaxSum", columnDefinition = "number(18,9) NULL COMMENT '不含税金额'")
    private Double notaxSum;
    /**
     * ----物料描述---
     **/
    @Column(name = "description", columnDefinition = "nvarchar2(500) NULL COMMENT '物料描述'")
    private String description;
    /**
     * ----含税单价---
     **/
    @Column(name = "taxPrice", columnDefinition = "number(18,9) NULL COMMENT '含税单价'")
    private Double taxprice;
    /**
     * ----含税总金额---
     **/
    @Column(name = "taxSum", columnDefinition = "number(18,9) NULL COMMENT '含税总金额'")
    private Double taxSum;
    /**
     * ----是否为设备---
     **/
    @Column(name = "isEquipment", columnDefinition = "Integer NULL COMMENT '是否为设备'")
    private Integer isEquipment;
    /**
     * ----寄存类型---
     **/
    @Column(name = "ownerType", columnDefinition = "Integer NULL COMMENT '寄存类型'")
    private Integer ownerType;
    /**
     * ----是否启用序列号---
     **/
    @Column(name = "enableSn", columnDefinition = "Integer NULL COMMENT '是否启用序列号'")
    private Integer enableSn;
    /**
     * ----序列号编码---
     **/
    @Column(name = "snCode", columnDefinition = "nvarchar2(50) NULL COMMENT '序列号编码'")
    private String snCode;
    /**
     * ----明细单位---
     **/
    @Column(name = "detailUnitName", columnDefinition = "nvarchar2(20) NULL COMMENT '明细单位'")
    private String detailUnitName;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString1", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString1;
    /**
     * ----入库数量---
     **/
    @Column(name = "detailCount", columnDefinition = "number(18,9) NULL COMMENT '入库数量'")
    private Double detailCount;
    /**
     * ----是否寄售---
     **/
    @Column(name = "jsType", columnDefinition = "nvarchar2(50) NULL COMMENT '是否寄售'")
    private String jsType;
    /**
     * ----接收订单编号---
     **/
    @Column(name = "jsCode", columnDefinition = "nvarchar2(64) NULL COMMENT '接收订单编号'")
    private String jsCode;
    /**
     * ----含税单价---
     **/
    @Column(name = "extendFloat1", columnDefinition = "number(18,9) NULL COMMENT '含税单价'")
    private Double extendFloat1;
    /**
     * ----库房名称---
     */
    @Column(name = "houseName", columnDefinition = "nvarchar2(64) NULL COMMENT '库房名称'")
    private String houseName;
    /**
     * ----库位编码---
     **/
    @Column(name = "storeLocationCode", columnDefinition = "nvarchar2(255) NULL COMMENT '库位编码'")
    private String storeLocationCode;
    /**
     * ----库位名称---
     **/
    @Column(name = "storeLocationName", columnDefinition = "nvarchar2(50) NULL COMMENT '库位名称'")
    private String storeLocationName;
    /**
     * ----库位ID---
     **/
    @Column(name = "storeLocationId", columnDefinition = "Integer NULL COMMENT '库位ID'")
    private Integer storeLocationId;
    /**
     * ----共计数量---
     **/
    @Column(name = "sumCount", columnDefinition = "number(18,9) NULL COMMENT '共计数量'")
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
