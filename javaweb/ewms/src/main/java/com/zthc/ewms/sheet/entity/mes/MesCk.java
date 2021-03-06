package com.zthc.ewms.sheet.entity.mes;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mes_ck")
public class MesCk {

    @Id
    @SequenceGenerator(name="generator",sequenceName="MES_CK_SEQUENCE",allocationSize = 1)
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
    @Column(name="locator_id", columnDefinition="Integer NULL")
    private Integer locatorId;
    /**----库位名称---**/
    @Column(name="locator_name", columnDefinition="nvarchar(255) NULL")
    private String locatorName;
    /**----部门名称---**/
    @Column(name="dept_id", columnDefinition="Integer  NULL")
    private Integer deptId;
    /**----出库备注---**/
    @Column(name="notes", columnDefinition="nvarchar(255) NULL")
    private String notes;
    /**----用途---**/
    @Column(name="use", columnDefinition="nvarchar(255) NULL")
    private String use;
    /**----申请单位名称---**/
    @Column(name="alias_name", columnDefinition="nvarchar(255) NULL")
    private String aliasName;
    /**----出库数量---**/
    @Column(name="trans_quantity", columnDefinition="number(18,9) NULL ")
    private Double transQuantity;
    /**----出库单位---**/
    @Column(name="trans_uom", columnDefinition="nvarchar(255) NULL")
    private String transUom;
    /**----子库存编号---**/
    @Column(name="subinv_code", columnDefinition="nvarchar(255) NULL")
    private String subinvCode;
    /**----物料描述---**/
    @Column(name="item_desc", columnDefinition="nvarchar(255) NULL")
    private String itemDesc;
    /**----物料编码---**/
    @Column(name="item_no", columnDefinition="nvarchar(255) NULL")
    private String itemNo;
    /**----基本单位数量---**/
    @Column(name="quantity", columnDefinition="number(18,9) NULL ")
    private Double quantity;
    /**----基本单位---**/
    @Column(name="uom", columnDefinition="nvarchar(255) NULL")
    private String uom;
    /**----班次---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="shifts", columnDefinition="date NULL ")
    private Date shifts;
    /**----出库日期---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="trans_date", columnDefinition="date NULL ")
    private Date transDate;
    /**----批次名---**/
    @Column(name="batch_name", columnDefinition="nvarchar(255) NULL")
    private String batchName;
    /**----更新时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="update_date", columnDefinition="date NULL ")
    private Date updateDate;
    /**----创建时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="maker_date", columnDefinition="date NULL ")
    private Date makerDate;
    /**----更新人ID---**/
    @Column(name="update_by_id", columnDefinition="Integer  NULL")
    private Integer updateById;
    /**----拥有方ID---**/
    @Column(name="owning_organization_id", columnDefinition="Integer  NULL")
    private Integer owningOrganizationId;
    /**----拥有方名称---**/
    @Column(name="owning_organization_name", columnDefinition="nvarchar(255)  NULL")
    private Integer owningOrganizationName;
    /**----序列控制---**/
    @Column(name="serial_number_control_code", columnDefinition="nvarchar(255)  NULL")
    private Integer serialNumControlCode;
    /**----来源行ID：出库行ID---**/
    @Column(name="source_line_id", columnDefinition="Integer  NULL")
    private Integer sourceLineId;
    /**----来源头单号---**/
    @Column(name="source_doc_num", columnDefinition="nvarchar(255)  NULL")
    private Integer sourceDocNum;
    /**----来源头ID---**/
    @Column(name="source_header_id", columnDefinition="Integer  NULL")
    private Integer sourceHeaderId;
    /**----计划行ID---**/
    @Column(name="schedult_ln_id", columnDefinition="Integer  NULL")
    private Integer schedultLnId;
    /**----计划头ID---**/
    @Column(name="schedult_hd_id", columnDefinition="Integer  NULL")
    private Integer schedultHdId;
    /**----创建人姓名---**/
    @Column(name="maker_name", columnDefinition="nvarchar(255) NULL")
    private String makerName;
    /**----创建人ID---**/
    @Column(name="maker_id", columnDefinition="Integer  NULL")
    private Integer makerId;

    public MesCk() {
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

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Double getTransQuantity() {
        return transQuantity;
    }

    public void setTransQuantity(Double transQuantity) {
        this.transQuantity = transQuantity;
    }

    public String getTransUom() {
        return transUom;
    }

    public void setTransUom(String transUom) {
        this.transUom = transUom;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Integer getOwningOrganizationId() {
        return owningOrganizationId;
    }

    public void setOwningOrganizationId(Integer owningOrganizationId) {
        this.owningOrganizationId = owningOrganizationId;
    }

    public Integer getOwningOrganizationName() {
        return owningOrganizationName;
    }

    public void setOwningOrganizationName(Integer owningOrganizationName) {
        this.owningOrganizationName = owningOrganizationName;
    }

    public Integer getSerialNumControlCode() {
        return serialNumControlCode;
    }

    public void setSerialNumControlCode(Integer serialNumControlCode) {
        this.serialNumControlCode = serialNumControlCode;
    }

    public Integer getSourceLineId() {
        return sourceLineId;
    }

    public void setSourceLineId(Integer sourceLineId) {
        this.sourceLineId = sourceLineId;
    }

    public Integer getSourceDocNum() {
        return sourceDocNum;
    }

    public void setSourceDocNum(Integer sourceDocNum) {
        this.sourceDocNum = sourceDocNum;
    }

    public Integer getSourceHeaderId() {
        return sourceHeaderId;
    }

    public void setSourceHeaderId(Integer sourceHeaderId) {
        this.sourceHeaderId = sourceHeaderId;
    }

    public Integer getSchedultLnId() {
        return schedultLnId;
    }

    public void setSchedultLnId(Integer schedultLnId) {
        this.schedultLnId = schedultLnId;
    }

    public Integer getSchedultHdId() {
        return schedultHdId;
    }

    public void setSchedultHdId(Integer schedultHdId) {
        this.schedultHdId = schedultHdId;
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
