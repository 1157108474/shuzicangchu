package com.zthc.ewms.sheet.entity.guard;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wz_sheetrksubdetail")
public class SheetRkSonDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "WZSHEETRKSUBDETAIL_SEQUENCE", allocationSize = 1)
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT '唯一标识'")
    private String guid;
    /**
     * ----单据明细ID---
     **/
    @Column(name = "detailId", columnDefinition = "Integer NULL COMMENT '单据明细ID'")
    private Integer detailId;
    /**
     * ----标签编码---
     **/
    @Column(name = "tagCode", columnDefinition = "nvarchar(50) NULL COMMENT '标签编码'")
    private String tagCode;
    /**
     * ----单据明细ID---
     **/
    @Column(name = "subStock", columnDefinition = "Integer NULL COMMENT '单据明细ID'")
    private Integer subStock;
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
     * ----接收单ID---
     **/
    @Column(name = "extendInt1", columnDefinition = "Integer NULL COMMENT '接收单ID'")
    private Integer extendInt1;
    /**
     * ----调拨单ID---
     **/
    @Column(name = "extendInt2", columnDefinition = "Integer NULL COMMENT '调拨单ID'")
    private Integer extendInt2;
    /**
     * ----备用int字段---
     **/
    @Column(name = "extendInt3", columnDefinition = "Integer NULL COMMENT '备用int字段'")
    private Integer extendInt3;
    /**
     * ----备用int字段---
     **/
    @Column(name = "extendInt4", columnDefinition = "Integer NULL COMMENT '备用int字段'")
    private Integer extendInt4;
    /**
     * ----备用int字段---
     **/
    @Column(name = "extendInt5", columnDefinition = "Integer NULL COMMENT '备用int字段'")
    private Integer extendInt5;
    /**
     * ----备用int字段---
     **/
    @Column(name = "extendInt6", columnDefinition = "Integer NULL COMMENT '备用int字段'")
    private Integer extendInt6;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString1", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString1;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString2", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString2;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString3", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString3;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString4", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString4;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString5", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString5;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString6", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString6;
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
    /**
     * ----序列号编码---
     **/
    @Column(name = "snCode", columnDefinition = "nvarchar2(50) NULL COMMENT '序列号编码'")
    private String snCode;

    public SheetRkSonDetail() {
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

    public Integer getSubStock() {
        return subStock;
    }

    public void setSubStock(Integer subStock) {
        this.subStock = subStock;
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

    public Integer getExtendInt1() {
        return extendInt1;
    }

    public void setExtendInt1(Integer extendInt1) {
        this.extendInt1 = extendInt1;
    }

    public Integer getExtendInt2() {
        return extendInt2;
    }

    public void setExtendInt2(Integer extendInt2) {
        this.extendInt2 = extendInt2;
    }

    public Integer getExtendInt3() {
        return extendInt3;
    }

    public void setExtendInt3(Integer extendInt3) {
        this.extendInt3 = extendInt3;
    }

    public Integer getExtendInt4() {
        return extendInt4;
    }

    public void setExtendInt4(Integer extendInt4) {
        this.extendInt4 = extendInt4;
    }

    public Integer getExtendInt5() {
        return extendInt5;
    }

    public void setExtendInt5(Integer extendInt5) {
        this.extendInt5 = extendInt5;
    }

    public Integer getExtendInt6() {
        return extendInt6;
    }

    public void setExtendInt6(Integer extendInt6) {
        this.extendInt6 = extendInt6;
    }

    public String getExtendString1() {
        return extendString1;
    }

    public void setExtendString1(String extendString1) {
        this.extendString1 = extendString1;
    }

    public String getExtendString2() {
        return extendString2;
    }

    public void setExtendString2(String extendString2) {
        this.extendString2 = extendString2;
    }

    public String getExtendString3() {
        return extendString3;
    }

    public void setExtendString3(String extendString3) {
        this.extendString3 = extendString3;
    }

    public String getExtendString4() {
        return extendString4;
    }

    public void setExtendString4(String extendString4) {
        this.extendString4 = extendString4;
    }

    public String getExtendString5() {
        return extendString5;
    }

    public void setExtendString5(String extendString5) {
        this.extendString5 = extendString5;
    }

    public String getExtendString6() {
        return extendString6;
    }

    public void setExtendString6(String extendString6) {
        this.extendString6 = extendString6;
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

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }
}
