package com.zthc.ewms.sheet.entity.rk;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 入库-页面明细列表
 */
@Entity
@Table(name = "V_JSRKDETAILS")
public class JsrkDetails {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;


    /**
     * ----单据明细ID---
     **/
    @Column(name = "detailId", columnDefinition = "Integer NULL COMMENT '单据明细ID'")
    private Integer detailId;
    @Column(name = "subdetailcount")
    private Double subDetailCount;

    @Column(name = "storeID")
    private Integer storeID;
    @Column(name = "storeLocationCode")
    private String storeLocationCode;

    @Column(name = "storeLocationName")
    private String storeLocationName;

    @Column(name = "sncode")
    private String sncode;


    @Column(name = "unitName")
    private String unitName;


    /**
     * ----物料编码---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '物料编码'")
    private String materialCode;
    /**
     * ----物料名称---
     **/
    @Column(name = "description")
    private String description;

    @Column(name = "isEquipment")
    private Integer isEquipment;

    @Column(name = "extendString1")
    private String extendString1;
    @Column(name = "code")
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Double getSubDetailCount() {
        return subDetailCount;
    }

    public void setSubDetailCount(Double subDetailCount) {
        this.subDetailCount = subDetailCount;
    }

    public Integer getStoreID() {
        return storeID;
    }

    public void setStoreID(Integer storeID) {
        this.storeID = storeID;
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

    public String getSncode() {
        return sncode;
    }

    public void setSncode(String sncode) {
        this.sncode = sncode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public Integer getIsEquipment() {
        return isEquipment;
    }

    public void setIsEquipment(Integer isEquipment) {
        this.isEquipment = isEquipment;
    }

    public String getExtendString1() {
        return extendString1;
    }

    public void setExtendString1(String extendString1) {
        this.extendString1 = extendString1;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JsrkDetails() {
    }
}
