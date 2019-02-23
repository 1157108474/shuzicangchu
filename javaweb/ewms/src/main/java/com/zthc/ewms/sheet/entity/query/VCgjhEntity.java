package com.zthc.ewms.sheet.entity.query;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "v_purchaseplan")
public class VCgjhEntity {

    /**
     * ----单据ID---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT '主键'")
    private Integer id;
    /**
     * ----采购计划编号---
     **/
    @Column(name = "planCode", columnDefinition = "nvarchar2(50) NULL COMMENT '采购计划编号'")
    private String planCode;
    /**
     * ----创建时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "datetime NULL COMMENT '创建时间'")
    private Date createDate;
    /**
     * ----需求时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "needDate", columnDefinition = "datetime NULL COMMENT '需求时间'")
    private Date needDate;
    /**
     * ----申请单位ID---
     **/
    @Column(name = "applyDepId", columnDefinition = "Integer NULL COMMENT '申请单位ID'")
    private Integer applyDepId;
    /**
     * ----使用单位ID---
     **/
    @Column(name = "useDepId", columnDefinition = "Integer NULL COMMENT '使用单位ID'")
    private Integer useDepId;
    /**
     * ----物料编码---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(64) NULL COMMENT '物料编码'")
    private String materialCode;
    /**
     * ----物料描述---
     **/
    @Column(name = "materIaldes", columnDefinition = "nvarchar2(255) NULL COMMENT '物料描述'")
    private String materIaldes;
    /**
     * -------
     **/
    @Column(name = "purchaseType", columnDefinition = "Integer NULL COMMENT ''")
    private Integer purchaseType;
    /**
     * -------
     **/
    @Column(name = "planType", columnDefinition = "Integer NULL COMMENT ''")
    private Integer planType;
    @Column(name = "sourceType", columnDefinition = "nvarchar2(50) NULL COMMENT ''")
    private String sourceType;
    /**
     * ----计量单位---
     **/
    @Column(name = "unIt", columnDefinition = "nvarchar2(50) NULL COMMENT '计量单位'")
    private String unIt;
    /**
     * -------
     **/
    @Column(name = "count", columnDefinition = "number(18,9) NULL COMMENT '采购数量'")
    private Double count;
    /**
     * -------
     **/
    @Column(name = "price", columnDefinition = "number(18,9) NULL COMMENT ''")
    private Double price;
    /**
     * ----基础单位---
     **/
    @Column(name = "baseUnit", columnDefinition = "nvarchar2(50) NULL COMMENT '基础单位'")
    private String baseUnit;
    /**
     * ----基本单位数量---
     **/
    @Column(name = "baseCount", columnDefinition = "number(18,9) NULL COMMENT '基本单位数量'")
    private Double baseCount;
    /**
     * ----基本单位不含税单价---
     **/
    @Column(name = "basePrice", columnDefinition = "number(18,9) NULL COMMENT '基本单位不含税单价'")
    private Double basePrice;
    /**
     * -------
     **/
    @Column(name = "purchAseModel", columnDefinition = "nvarchar2(200) NULL COMMENT ''")
    private String purchAseModel;
    /**
     * -------
     **/
    @Column(name = "manuFacturer", columnDefinition = "nvarchar2(200) NULL COMMENT ''")
    private String manuFacturer;
    /**
     * ----更新时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "datetime NULL COMMENT '更新时间'")
    private Date updateDate;
    /**
     * ----ERP ID---
     **/
    @Column(name = "erpId", columnDefinition = "Integer NULL COMMENT 'ERP ID'")
    private Integer erpId;
    /**
     * ----订单类型---
     **/
    @Column(name = "orderType", columnDefinition = "nvarchar2(100) NULL COMMENT '订单类型'")
    private String orderType;
    /**
     * -------
     **/
    @Column(name = "consignment", columnDefinition = "Integer NULL COMMENT ''")
    private Integer consignment;
    /**
     * ----ZTID---
     **/
    @Column(name = "ztId", columnDefinition = "Integer NULL COMMENT 'ZTID'")
    private Integer ztId;
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
     * ----备用float字段---
     **/
    @Column(name = "extendFloat1", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendFloat1;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendFloat2", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendFloat2;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendFloat3", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendFloat3;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendFloat4", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendFloat4;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendFloat5", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendFloat5;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendFloat6", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendFloat6;
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
     * ----备用string字段---
     **/
    @Column(name = "extendString7", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString7;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString8", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString8;
    /**
     * ----使用单位名称---
     **/
    @Column(name = "useDepName", columnDefinition = "nvarchar2(200) NULL COMMENT '使用单位名称'")
    private String useDepName;
    /**
     * ----申请单位名称---
     **/
    @Column(name = "applyDepName", columnDefinition = "nvarchar2(200) NULL COMMENT '申请单位名称'")
    private String applyDepName;
    /**
     * ----单据状态---
     **/
    @Column(name = "status", columnDefinition = "nvarchar2(64) NULL COMMENT '单据状态'")
    private String status;

    @Column(name = "erpDetailId", columnDefinition = "Integer NULL COMMENT 'erp明细ID'")
    private Integer erpDetailId;

    public Integer getErpDetailId() {
        return erpDetailId;
    }

    public void setErpDetailId(Integer erpDetailId) {
        this.erpDetailId = erpDetailId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getNeedDate() {
        return needDate;
    }

    public void setNeedDate(Date needDate) {
        this.needDate = needDate;
    }

    public Integer getApplyDepId() {
        return applyDepId;
    }

    public void setApplyDepId(Integer applyDepId) {
        this.applyDepId = applyDepId;
    }

    public Integer getUseDepId() {
        return useDepId;
    }

    public void setUseDepId(Integer useDepId) {
        this.useDepId = useDepId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterIaldes() {
        return materIaldes;
    }

    public void setMaterIaldes(String materIaldes) {
        this.materIaldes = materIaldes;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public String getUnIt() {
        return unIt;
    }

    public void setUnIt(String unIt) {
        this.unIt = unIt;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public Double getBaseCount() {
        return baseCount;
    }

    public void setBaseCount(Double baseCount) {
        this.baseCount = baseCount;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getPurchAseModel() {
        return purchAseModel;
    }

    public void setPurchAseModel(String purchAseModel) {
        this.purchAseModel = purchAseModel;
    }

    public String getManuFacturer() {
        return manuFacturer;
    }

    public void setManuFacturer(String manuFacturer) {
        this.manuFacturer = manuFacturer;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getErpId() {
        return erpId;
    }

    public void setErpId(Integer erpId) {
        this.erpId = erpId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getConsignment() {
        return consignment;
    }

    public void setConsignment(Integer consignment) {
        this.consignment = consignment;
    }

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
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

    public Double getExtendFloat1() {
        return extendFloat1;
    }

    public void setExtendFloat1(Double extendFloat1) {
        this.extendFloat1 = extendFloat1;
    }

    public Double getExtendFloat2() {
        return extendFloat2;
    }

    public void setExtendFloat2(Double extendFloat2) {
        this.extendFloat2 = extendFloat2;
    }

    public Double getExtendFloat3() {
        return extendFloat3;
    }

    public void setExtendFloat3(Double extendFloat3) {
        this.extendFloat3 = extendFloat3;
    }

    public Double getExtendFloat4() {
        return extendFloat4;
    }

    public void setExtendFloat4(Double extendFloat4) {
        this.extendFloat4 = extendFloat4;
    }

    public Double getExtendFloat5() {
        return extendFloat5;
    }

    public void setExtendFloat5(Double extendFloat5) {
        this.extendFloat5 = extendFloat5;
    }

    public Double getExtendFloat6() {
        return extendFloat6;
    }

    public void setExtendFloat6(Double extendFloat6) {
        this.extendFloat6 = extendFloat6;
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

    public String getExtendString7() {
        return extendString7;
    }

    public void setExtendString7(String extendString7) {
        this.extendString7 = extendString7;
    }

    public String getExtendString8() {
        return extendString8;
    }

    public void setExtendString8(String extendString8) {
        this.extendString8 = extendString8;
    }

    public String getUseDepName() {
        return useDepName;
    }

    public void setUseDepName(String useDepName) {
        this.useDepName = useDepName;
    }

    public String getApplyDepName() {
        return applyDepName;
    }

    public void setApplyDepName(String applyDepName) {
        this.applyDepName = applyDepName;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VCgjhEntity() {
    }
}
