package com.zthc.ewms.sheet.entity.rk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_print_dbrkdetails")
public class DbrkDetailPrint {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
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
     * ----物料编码---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '物料编码'")
    private String materialCode;
    /**
     * ----物料描述---
     **/
    @Column(name = "description", columnDefinition = "nvarchar2(500) NULL COMMENT '物料描述'")
    private String description;
    /**
     * ----物料品牌---
     **/
    @Column(name = "materialBrand", columnDefinition = "nvarchar2(255) NULL COMMENT '物料品牌'")
    private String materialBrand;
    /**
     * ----明细单位---
     **/
    @Column(name = "detailUnitName", columnDefinition = "nvarchar2(20) NULL COMMENT '明细单位'")
    private String detailUnitName;
    /**
     * ----不含税单价---
     **/
    @Column(name = "notaxPrice", columnDefinition = "number(18,9) NULL COMMENT '不含税单价'")
    private Double notaxPrice;
    /**
     * ----不含税金额---
     **/
    @Column(name = "notaxSum", columnDefinition = "number(18,9) NULL COMMENT '不含税金额'")
    private Double notaxSum;
    /**
     * ----明细ID---
     **/
    @Column(name = "detailId", columnDefinition = "Integer NULL COMMENT '明细ID'")
    private Integer detailId;
    /**
     * ----库位编码---
     **/
    @Column(name = "storeLocationCode", columnDefinition = "nvarchar2(255) NULL COMMENT '库位编码'")
    private String storeLocationCode;
    /**
     * ----分配数量---
     **/
    @Column(name = "subDetailCount", columnDefinition = "number(18,9) NULL COMMENT '分配数量'")
    private Double subDetailCount;

    public DbrkDetailPrint() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterialBrand() {
        return materialBrand;
    }

    public void setMaterialBrand(String materialBrand) {
        this.materialBrand = materialBrand;
    }

    public String getDetailUnitName() {
        return detailUnitName;
    }

    public void setDetailUnitName(String detailUnitName) {
        this.detailUnitName = detailUnitName;
    }

    public Double getNotaxPrice() {
        return notaxPrice;
    }

    public void setNotaxPrice(Double notaxPrice) {
        this.notaxPrice = notaxPrice;
    }

    public Double getNotaxSum() {
        return notaxSum;
    }

    public void setNotaxSum(Double notaxSum) {
        this.notaxSum = notaxSum;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getStoreLocationCode() {
        return storeLocationCode;
    }

    public void setStoreLocationCode(String storeLocationCode) {
        this.storeLocationCode = storeLocationCode;
    }

    public Double getSubDetailCount() {
        return subDetailCount;
    }

    public void setSubDetailCount(Double subDetailCount) {
        this.subDetailCount = subDetailCount;
    }
}
