package com.zthc.ewms.sheet.entity.rk;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 已分配库房库位明细
 */
@Entity
@Table(name = "v_rksubdetail")
public class RkSubDetail {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT '唯一标识'")
    private String guid;
    /**
     * ----明细ID---
     **/
    @Column(name = "detailId", columnDefinition = "Integer NULL COMMENT '明细ID'")
    private Integer detailId;
    /**
     * ----编码---
     **/
    @Column(name = "tagCode", columnDefinition = "nvarchar(50) NULL COMMENT '编码'")
    private String tagCode;
    /**
     * ----分配数量---
     **/
    @Column(name = "subDetailCount", columnDefinition = "number(18,9) NULL COMMENT '分配数量'")
    private Double subDetailCount;
    /**
     * -------
     **/
    @Column(name = "unit", columnDefinition = "Integer NULL COMMENT ''")
    private Integer unit;
    /**
     * ----库房ID---
     **/
    @Column(name = "storeID", columnDefinition = "Integer NULL COMMENT '库房ID '")
    private Integer storeID;
    /**
     * -------
     **/
    @Column(name = "storeLocationCode", columnDefinition = "nvarchar(255) NULL COMMENT '??λ????'")
    private String storeLocationCode;
    /**
     * -------
     **/
    @Column(name = "storeLocationName", columnDefinition = "nvarchar(50) NULL COMMENT '??λ????'")
    private String storeLocationName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "addTime", columnDefinition = "date NULL COMMENT 'addTime'")
    private Date addTime;
    /**
     * ----序列号编码---
     **/
    @Column(name = "snCode", columnDefinition = "nvarchar2(50) NULL COMMENT '序列号编码'")
    private String snCode;
    /**
     * ----库位ID---
     **/
    @Column(name = "storeLocationId", columnDefinition = "Integer NULL COMMENT '库位ID'")
    private Integer storeLocationId;
    /**
     * ----明细单位---
     **/
    @Column(name = "unitName", columnDefinition = "nvarchar2(20) NULL COMMENT '明细单位'")
    private String unitName;

    public RkSubDetail() {
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
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

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public Double getSubDetailCount() {
        return subDetailCount;
    }

    public void setSubDetailCount(Double subDetailCount) {
        this.subDetailCount = subDetailCount;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(Integer storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
