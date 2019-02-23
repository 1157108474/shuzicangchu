package com.zthc.ewms.sheet.entity.rk;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * �ѷ���ⷿ��λ��ϸ
 */
@Entity
@Table(name = "v_rksubdetail")
public class RkSubDetail {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----Ψһ��ʶ---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
    /**
     * ----��ϸID---
     **/
    @Column(name = "detailId", columnDefinition = "Integer NULL COMMENT '��ϸID'")
    private Integer detailId;
    /**
     * ----����---
     **/
    @Column(name = "tagCode", columnDefinition = "nvarchar(50) NULL COMMENT '����'")
    private String tagCode;
    /**
     * ----��������---
     **/
    @Column(name = "subDetailCount", columnDefinition = "number(18,9) NULL COMMENT '��������'")
    private Double subDetailCount;
    /**
     * -------
     **/
    @Column(name = "unit", columnDefinition = "Integer NULL COMMENT ''")
    private Integer unit;
    /**
     * ----�ⷿID---
     **/
    @Column(name = "storeID", columnDefinition = "Integer NULL COMMENT '�ⷿID '")
    private Integer storeID;
    /**
     * -------
     **/
    @Column(name = "storeLocationCode", columnDefinition = "nvarchar(255) NULL COMMENT '??��????'")
    private String storeLocationCode;
    /**
     * -------
     **/
    @Column(name = "storeLocationName", columnDefinition = "nvarchar(50) NULL COMMENT '??��????'")
    private String storeLocationName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "addTime", columnDefinition = "date NULL COMMENT 'addTime'")
    private Date addTime;
    /**
     * ----���кű���---
     **/
    @Column(name = "snCode", columnDefinition = "nvarchar2(50) NULL COMMENT '���кű���'")
    private String snCode;
    /**
     * ----��λID---
     **/
    @Column(name = "storeLocationId", columnDefinition = "Integer NULL COMMENT '��λID'")
    private Integer storeLocationId;
    /**
     * ----��ϸ��λ---
     **/
    @Column(name = "unitName", columnDefinition = "nvarchar2(20) NULL COMMENT '��ϸ��λ'")
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
