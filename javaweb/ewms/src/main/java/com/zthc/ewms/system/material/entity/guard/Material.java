package com.zthc.ewms.system.material.entity.guard;

import com.zthc.ewms.base.util.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: 物料管理
 * @Package
 */
@Entity
@Table(name = "base_material")
public class Material {
    /**
     * ----主键---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT '主键'")
    @SequenceGenerator(name = "generator", sequenceName = "BASEMATERIAL_SEQUENCE",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    private Integer id;
    /**
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT '唯一标识'")
    private String guid;

    /**
     * ----名称---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(64) NULL COMMENT '名称/物料大类'")
    private String name;

    /**
     * ----模型---
     **/
    @Column(name = "model", columnDefinition = "nvarchar2(200) NULL COMMENT '模型'")
    private String model;

    /**
     * ----模型---
     **/
    @Column(name = "models")
    private String models;
    /**
     * ----品牌---
     **/
    @Column(name = "brand", columnDefinition = "nvarchar2(200) NULL COMMENT '品牌'")
    private String brand;
    /**
     * ----物料分类---
     **/
    @Column(name = "sparescateId", columnDefinition = "Integer NULL COMMENT '物料分类'")
    private Integer sparescateId;
    /**
     * ----单价---
     **/
    @Column(name = "price", columnDefinition = "number(18,9) NULL COMMENT '单价'")
    private Double price;
    /**
     * ----状态---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '状态'")
    private Integer status;
    /**
     * ----配置信息---
     **/
    @Column(name = "configMemo", columnDefinition = "nvarchar2(1024) NULL COMMENT '配置信息'")
    private String configMemo;
    /**
     * ----供应商---
     **/
    @Column(name = "providerId", columnDefinition = "Integer NULL COMMENT '供应商'")
    private Integer providerId;
    @Transient
    private String providerName;
    /**
     * ----备注---
     **/
    @Column(name = "memo", columnDefinition = "nvarchar2(1024) NULL COMMENT '备注'")
    private String memo;
    /**
     * ----排序---
     **/
    @Column(name = "sort", columnDefinition = "Integer NULL COMMENT '排序'")
    private Integer sort;
    /**
     * ----库存组织---
     **/
    @Column(name = "ztid", columnDefinition = "Integer NULL COMMENT '库存组织'")
    private Integer ztid;
    @Transient
    private String ztName;
    /**
     * ----编码---
     **/
    @Column(name = "code", columnDefinition = "nvarchar2(16) NULL COMMENT '编码'")
    private String code;
    /**
     * ----物料规则---
     **/
    @Column(name = "specifications", columnDefinition = "nvarchar2(255) NULL COMMENT '物料规则'")
    private String specifications;

    /**
     * ----计价单位---
     **/
    @Column(name = "unit", columnDefinition = "nvarchar2(64) NULL COMMENT '计价单位'")
    private String unit;
    @Transient
    private String unitName;
    /**
     * ----库存上限---
     **/
    @Column(name = "stockUp", columnDefinition = "NUMBER(18,9) NULL COMMENT '库存上限'")
    private Double stockUp;
    /**
     * ----库存下限---
     **/
    @Column(name = "stockDown", columnDefinition = "NUMBER(18,9) NULL COMMENT '库存下限'")
    private Double stockDown;
    /**
     * ----是否启用预警---
     **/
    @Column(name = "isUseAlarm", columnDefinition = "Integer NULL COMMENT '是否启用预警'")
    private Integer isUseAlarm;
    /**
     * ----物料描述---
     **/
    @Column(name = "description", columnDefinition = "nvarchar2(500) NULL COMMENT '物料描述'")
    private String description;
    /**
     * ----是否启用序列号---
     **/
    @Column(name = "enableSN", columnDefinition = "Integer NULL COMMENT '是否启用序列号'")
    private Integer enableSN;
    /**
     * ----新增类型---
     **/
    @Column(name = "addType", columnDefinition = "Integer NULL COMMENT '新增类型'")
    private Integer addType;

    /**
     * ----创建人---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '创建人'")
    private Integer creator;
    /**
     * ----创建时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "date NULL COMMENT '创建时间'")
    private Date createDate;
    /**
     * ----更新人---
     **/
    @Column(name = "updater", columnDefinition = "Integer NULL COMMENT '更新人'")
    private Integer updater;
    /**
     * ----更新时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL COMMENT '更新时间'")
    private Date updateDate;

    @Transient
    private String spareCode;


    public String getSpareCode() {
        return spareCode;
    }

    public void setSpareCode(String spareCode) {
        this.spareCode = spareCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdStr(String idStr) {
        this.id = StringUtils.isEmpty(idStr) ? null : Integer.parseInt(idStr);
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getSparescateId() {
        return sparescateId;
    }

    public void setSparescateId(Integer sparescateId) {
        this.sparescateId = sparescateId;
    }

    public void setSparescateIdStr(String idStr) {
        this.sparescateId = StringUtils.isEmpty(idStr) ? null : Integer.parseInt(idStr);
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPriceStr(String priceStr) {
        this.price = StringUtils.isEmpty(priceStr) ? null : Double.parseDouble(priceStr.trim());
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStatusStr(String statusStr) {
        this.status = StringUtils.isEmpty(statusStr) ? null : Integer.parseInt(statusStr);
    }

    public String getConfigMemo() {
        return configMemo;
    }

    public void setConfigMemo(String configMemo) {
        this.configMemo = configMemo;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public void setProviderIdStr(String idStr) {

        this.providerId = StringUtils.isEmpty(idStr) ? null : Integer.parseInt(idStr);
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getZtName() {
        return ztName;
    }

    public void setZtName(String ztName) {
        this.ztName = ztName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setSortStr(String sortStr) {
        this.sort = StringUtils.isEmpty(sortStr) ? null : Integer.parseInt(sortStr.trim());
    }

    public Integer getZtid() {
        return ztid;
    }

    public void setZtid(Integer ztid) {
        this.ztid = ztid;
    }

    public void setZtidStr(String idStr) {

        this.ztid = StringUtils.isEmpty(idStr) ? null : Integer.parseInt(idStr);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code != null) {
            code = code.trim();
        }
        this.code = code;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getStockUp() {
        return stockUp;
    }

    public void setStockUp(Double stockUp) {
        this.stockUp = stockUp;
    }

    public void setStockUpStr(String stockUpStr) {
        this.stockUp = StringUtils.isEmpty(stockUpStr) ? null : Double.parseDouble(stockUpStr.trim());
    }

    public Double getStockDown() {
        return stockDown;
    }

    public void setStockDown(Double stockDown) {
        this.stockDown = stockDown;
    }

    public void setStockDownStr(String stockDownStr) {
        this.stockDown = StringUtils.isEmpty(stockDownStr) ? null : Double.parseDouble(stockDownStr.trim());
    }

    public Integer getIsUseAlarm() {
        return isUseAlarm;
    }

    public void setIsUseAlarm(Integer isUseAlarm) {
        this.isUseAlarm = isUseAlarm;
    }

    public void setIsUseAlarmStr(String isUseAlarmStr) {
        this.isUseAlarm = StringUtils.isEmpty(isUseAlarmStr) ? null : Integer.parseInt(isUseAlarmStr);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEnableSN() {
        return enableSN;
    }

    public void setEnableSN(Integer enableSN) {
        this.enableSN = enableSN;
    }

    public void setEnableSN(String enableSNStr) {
        this.enableSN = StringUtils.isEmpty(enableSNStr) ? null : Integer.parseInt(enableSNStr);
    }

    public Integer getAddType() {
        return addType;
    }

    public void setAddType(Integer addType) {
        this.addType = addType;
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

    public Integer getUpdater() {
        return updater;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Material() {

    }

    public Material(Integer id) {
        this.id = id;

    }

    public Material(Integer id, String code, String description, Double price, String unit, Double stockUp, Double stockDown, Integer enableSN, Integer status,String name,String brand,String models,String specifications) {
        this.id = id;
        this.price = price;
        this.status = status;
        this.code = code;
        this.unit = unit;
        this.stockUp = stockUp;
        this.stockDown = stockDown;
        this.description = description;
        this.enableSN = enableSN;
        this.name = name;
        this.brand = brand;
        this.models = models;
        this.specifications = specifications;
    }

    public Material(Integer id, String guid, String name, String model, String brand, Integer sparescateId, Double price, Integer status, String configMemo, Integer providerId, String memo, Integer sort, Integer ztid, String code, String specifications, String unit, Double stockUp, Double stockDown, Integer isUseAlarm, String description, Integer enableSN, Integer addType) {
        this.id = id;
        this.guid = guid;
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.sparescateId = sparescateId;
        this.price = price;
        this.status = status;
        this.configMemo = configMemo;
        this.providerId = providerId;
        this.memo = memo;
        this.sort = sort;
        this.ztid = ztid;
        this.code = code;
        this.specifications = specifications;

        this.unit = unit;
        this.stockUp = stockUp;
        this.stockDown = stockDown;
        this.isUseAlarm = isUseAlarm;
        this.description = description;
        this.enableSN = enableSN;
        this.addType = addType;
    }

    public Material(Integer id, String code, String name, Integer sparescateId, String specifications, String model,
                    String brand, Double price, String unit, Double stockUp, Double stockDown,
                    Integer isUseAlarm, Integer providerId, /*String providerName, */Integer ztid,
                    String ztName, String configMemo, String description, Integer sort,
                    Integer status, String memo,String spareCode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sparescateId = sparescateId;
        this.specifications = specifications;
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.unit = unit;
        this.stockUp = stockUp;
        this.stockDown = stockDown;
        this.isUseAlarm = isUseAlarm;
        this.providerId = providerId;
//        this.providerName = providerName;
        this.ztid = ztid;
        this.ztName = ztName;
        this.configMemo = configMemo;
        this.description = description;
        this.sort = sort;
        this.status = status;
        this.memo = memo;
        this.spareCode = spareCode;
    }


    public Material(Integer id, String code, String name, Integer sparescateId, String specifications, String model,
                    String brand, Double price, String unit, Double stockUp, Double stockDown,
                    Integer isUseAlarm, Integer providerId, /*String providerName, */Integer ztid,
                    String configMemo, String description, Integer sort,
                    Integer status, String memo,String spareCode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sparescateId = sparescateId;
        this.specifications = specifications;
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.unit = unit;
        this.stockUp = stockUp;
        this.stockDown = stockDown;
        this.isUseAlarm = isUseAlarm;
        this.providerId = providerId;
//        this.providerName = providerName;
        this.ztid = ztid;
//        this.ztName = ztName;
        this.configMemo = configMemo;
        this.description = description;
        this.sort = sort;
        this.status = status;
        this.memo = memo;
        this.spareCode = spareCode;
    }


}