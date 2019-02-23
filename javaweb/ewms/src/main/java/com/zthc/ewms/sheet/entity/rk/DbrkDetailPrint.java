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
     * ----���ϱ���---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '���ϱ���'")
    private String materialCode;
    /**
     * ----��������---
     **/
    @Column(name = "description", columnDefinition = "nvarchar2(500) NULL COMMENT '��������'")
    private String description;
    /**
     * ----����Ʒ��---
     **/
    @Column(name = "materialBrand", columnDefinition = "nvarchar2(255) NULL COMMENT '����Ʒ��'")
    private String materialBrand;
    /**
     * ----��ϸ��λ---
     **/
    @Column(name = "detailUnitName", columnDefinition = "nvarchar2(20) NULL COMMENT '��ϸ��λ'")
    private String detailUnitName;
    /**
     * ----����˰����---
     **/
    @Column(name = "notaxPrice", columnDefinition = "number(18,9) NULL COMMENT '����˰����'")
    private Double notaxPrice;
    /**
     * ----����˰���---
     **/
    @Column(name = "notaxSum", columnDefinition = "number(18,9) NULL COMMENT '����˰���'")
    private Double notaxSum;
    /**
     * ----��ϸID---
     **/
    @Column(name = "detailId", columnDefinition = "Integer NULL COMMENT '��ϸID'")
    private Integer detailId;
    /**
     * ----��λ����---
     **/
    @Column(name = "storeLocationCode", columnDefinition = "nvarchar2(255) NULL COMMENT '��λ����'")
    private String storeLocationCode;
    /**
     * ----��������---
     **/
    @Column(name = "subDetailCount", columnDefinition = "number(18,9) NULL COMMENT '��������'")
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
