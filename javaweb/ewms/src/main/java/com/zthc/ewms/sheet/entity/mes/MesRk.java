package com.zthc.ewms.sheet.entity.mes;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mes_rk")
public class MesRk {

    @Id
    @SequenceGenerator(name="generator",sequenceName="MES_RK_SEQUENCE",allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="generator")
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT '????'")
    private Integer id;
    /**----资产编号---**/
    @Column(name="asset_number", columnDefinition="nvarchar(255) NULL")
    private String assetNumber;
    /**----库位编码---**/
    @Column(name="locator_code", columnDefinition="nvarchar(50) NULL")
    private String locatorCode;
    /**----库位ID---**/
    @Column(name="locator_id", columnDefinition="Integer NOT NULL")
    private Integer locatorId;
    /**----库位名称---**/
    @Column(name="locator_name", columnDefinition="nvarchar(255) NULL")
    private String locatorName;
    /**----库房编码---**/
    @Column(name="subinv_code", columnDefinition="nvarchar(255) NULL")
    private String subinvCode;
    /**----物料描述---**/
    @Column(name="item_desc", columnDefinition="nvarchar(255) NULL")
    private String itemDesc;
    /**----物料编码---**/
    @Column(name="item_no", columnDefinition="nvarchar(255) NULL")
    private String itemNo;
    /**----入库基本单位数量---**/
    @Column(name="quantity", columnDefinition="number(18,9) NULL ")
    private Double quantity;
    /**----入库基本单位---**/
    @Column(name="uom", columnDefinition="nvarchar(255) NULL")
    private String uom;
    /**----班次---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="shifts", columnDefinition="date NULL COMMENT '班次'")
    private Date shifts;
    /**----批次---**/
    @Column(name="batch_name", columnDefinition="nvarchar(255) NULL")
    private String batchName;
    /**----更新时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="update_date", columnDefinition="date NULL COMMENT '???????'")
    private Date updateDate;
    /**----创建时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="maker_date", columnDefinition="date NULL COMMENT '???????'")
    private Date makerDate;
    /**----更新人ID---**/
    @Column(name="update_by_id", columnDefinition="Integer NOT NULL")
    private Integer updateById;
    /**----生产装置ID---**/
    @Column(name="production_id", columnDefinition="Integer NOT NULL")
    private Integer productionId;
    /**----生产装置名称---**/
    @Column(name="production_name", columnDefinition="nvarchar(255) NULL")
    private String productionName;
    /**----创建人姓名---**/
    @Column(name="maker_name", columnDefinition="nvarchar(255) NULL")
    private String makerName;
    /**----创建人ID---**/
    @Column(name="maker_id", columnDefinition="Integer NOT NULL")
    private Integer makerId;

    public MesRk() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getLocatorCode() {
        return locatorCode;
    }

    public void setLocatorCode(String locatorCode) {
        this.locatorCode = locatorCode;
    }

    public Integer getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Integer locatorId) {
        this.locatorId = locatorId;
    }

    public String getLocatorName() {
        return locatorName;
    }

    public void setLocatorName(String locatorName) {
        this.locatorName = locatorName;
    }

    public String getSubinvCode() {
        return subinvCode;
    }

    public void setSubinvCode(String subinvCode) {
        this.subinvCode = subinvCode;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Date getShifts() {
        return shifts;
    }

    public void setShifts(Date shifts) {
        this.shifts = shifts;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getMakerDate() {
        return makerDate;
    }

    public void setMakerDate(Date makerDate) {
        this.makerDate = makerDate;
    }

    public Integer getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Integer updateById) {
        this.updateById = updateById;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public Integer getMakerId() {
        return makerId;
    }

    public void setMakerId(Integer makerId) {
        this.makerId = makerId;
    }
}
