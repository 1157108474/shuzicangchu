package com.zthc.ewms.sheet.entity.order;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="v_jslist")
public class OrderDetails {

    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**----唯一标识---**/
    @Column(name="guid", columnDefinition="nvarchar2(36) NULL COMMENT '唯一标识'")
    private String guid;
    /**----ERP ID---**/
    @Column(name="erpid", columnDefinition="nvarchar2(50) NULL COMMENT 'ERP ID'")
    private String erpid;
    /**----采购订单 ID---**/
    @Column(name = "orderid", columnDefinition = "Integer NOT NULL COMMENT 'orderid'")
    private Integer orderid;
    /**----采购订单编码---**/
    @Column(name="ordernum", columnDefinition="nvarchar2(50) NULL COMMENT '采购订单编码'")
    private String ordernum;
    /**----公司ID---**/
    @Column(name="businessid", columnDefinition="Integer NULL COMMENT '公司ID'")
    private Integer businessid;
    /**----ERP库房组织---**/
    @Column(name="erpstockorgid", columnDefinition="Integer NULL COMMENT 'ERP库房组织'")
    private Integer erpstockorgid;
    /**----库房组织ID---**/
    @Column(name="stockorgid", columnDefinition="Integer NULL COMMENT '库房组织ID'")
    private Integer stockorgid;
    /**----库房组织编码---**/
    @Column(name="stockorgcode", columnDefinition="nvarchar2(50) NULL COMMENT '库房组织编码'")
    private String stockorgcode;
    /**----ERP供应商ID---**/
    @Column(name="erpproviderdepid", columnDefinition="Integer NULL COMMENT 'ERP供应商ID'")
    private Integer erpproviderdepid;
    /**----供应商ID---**/
    @Column(name="providerdepid", columnDefinition="Integer NULL COMMENT '供应商ID'")
    private Integer providerdepid;
    /**----供应商编码---**/
    @Column(name="providerdepcode", columnDefinition="nvarchar2(50) NULL COMMENT '供应商编码'")
    private String providerdepcode;
    /**----供应商名称---**/
    @Column(name="providerdepname", columnDefinition="nvarchar2(250) NULL COMMENT '供应商名称'")
    private String providerdepname;
    /**----供应商地址ID---**/
    @Column(name="providerplaceid", columnDefinition="Integer NULL COMMENT '供应商地址ID'")
    private Integer providerplaceid;
    /**----供应商地址名称---**/
    @Column(name="providerplacecode", columnDefinition="nvarchar2(50) NULL COMMENT '供应商地址名称'")
    private String providerplacecode;
    /**----发放ID---**/
    @Column(name="issueid", columnDefinition="Integer NULL COMMENT '发放ID'")
    private Integer issueid;
    /**----发放号---**/
    @Column(name="issuecode", columnDefinition="Integer NULL COMMENT '发放号'")
    private Integer issuecode;
    /**----发运ID---**/
    @Column(name="fyid", columnDefinition="Integer NULL COMMENT '发运ID'")
    private Integer fyid;
    /**----ERP行号---**/
    @Column(name="erprownum", columnDefinition="nvarchar2(50) NULL COMMENT 'ERP行号'")
    private String erprownum;
    /**----采购订单行号---**/
    @Column(name="orderrowid", columnDefinition="nvarchar2(50) NULL COMMENT '采购订单行号'")
    private String orderrowid;
    /**----物料编码---**/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '物料编码'")
    private String materialCode;
    /**----XXXX---**/
    @Column(name="parcode", columnDefinition="Integer NULL COMMENT ''")
    private Integer parcode;
    /**----物料描述---**/
    @Column(name="description", columnDefinition="nvarchar2(500) NULL COMMENT '物料描述'")
    private String description;
    /**----明细单位---**/
    @Column(name="detailunit", columnDefinition="nvarchar2(50) NULL COMMENT '明细单位'")
    private String detailunit;
//    /**----明细数量---**/
//    @Column(name="detailcount", columnDefinition="number(18,9) NULL COMMENT '明细数量'")
//    private Double detailcount;
    /**----不含税单价---**/
    @Column(name="notaxprice", columnDefinition="number(18,9) NULL COMMENT '不含税单价'")
    private Double notaxprice;
    /**----不含税总金额---**/
    @Column(name="notaxsum", columnDefinition="number(18,9) NULL COMMENT '不含税总金额'")
    private Double notaxsum;
    /**----基础单位---**/
    @Column(name="baseunit", columnDefinition="nvarchar2(50) NULL COMMENT '基础单位'")
    private String baseunit;
    /**----接收数量---**/
    @Column(name="baseunitcount", columnDefinition="number(18,9) NULL COMMENT '基本单位数量'")
    private Double baseunitcount;
    /**----基本单位不含税单价---**/
    @Column(name="baseunitprice", columnDefinition="number(18,9) NULL COMMENT '基本单位不含税单价'")
    private Double baseunitprice;
    /**----基本单位不含税总金额---**/
    @Column(name="baseunitsum", columnDefinition="number(18,9) NULL COMMENT '基本单位不含税总金额'")
    private Double baseunitsum;
    /**----税率---**/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '税率'")
    private Double taxRate;
    /**----是否寄售---**/
    @Column(name="consignment", columnDefinition="Integer NULL COMMENT '是否寄售'")
    private Integer consignment;
    /**----物料名称---**/
    @Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '物料名称'")
    private String materialName;
    /**----物料规格---**/
    @Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '物料规格'")
    private String materialSpecification;
    /**----物料型号---**/
    @Column(name="materialModel", columnDefinition="nvarchar(255) NULL COMMENT '物料型号'")
    private String materialModel;
    /**----物料品牌---**/
    @Column(name="materialBrand", columnDefinition="nvarchar(255) NULL COMMENT '物料品牌'")
    private String materialBrand;
    /**----物料ID---**/
    @Column(name="materialId", columnDefinition="Integer NULL COMMENT '物料ID'")
    private Integer materialId;
    /**----备件分类ID---**/
    @Column(name="sparescateId", columnDefinition="Integer NULL COMMENT '备件分类ID'")
    private Integer sparescateId;
    /**----备用string字段---**/
    @Column(name="extendstring1", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring1;
    /**----可接收数量---**/
    @Column(name="isCount", columnDefinition="number(18,9) NULL COMMENT '可接收数量'")
    private Double isCount;
    /**----部门ID---**/
    @Column(name="departid", columnDefinition="Integer NULL COMMENT '部门ID'")
    private Integer departid;
    /**----创建人ID---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人ID'")
    private Integer creator;
    /**----创建时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createdate", columnDefinition="datetime NULL COMMENT '创建时间'")
    private Date createdate;
    /**----更新人ID---**/
    @Column(name="updator", columnDefinition="Integer NULL COMMENT '更新人ID'")
    private Integer updator;
    /**----更新时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="updatedate", columnDefinition="datetime NULL COMMENT '更新时间'")
    private Date updatedate;
    /**----订单类型---**/
    @Column(name="ordertype", columnDefinition="nvarchar2(50) NULL COMMENT '订单类型'")
    private String ordertype;
    /**----是否寄售---**/
    @Column(name="jstype", columnDefinition="nvarchar2(50) NULL COMMENT '是否寄售'")
    private String jstype;
    /**
     * ----是否存在---
     **/
    @Transient
    private String isEnableSN;

    @Transient
    private Integer isEquipment;

    @Transient
    private Integer enableSn;

    @Transient
    private Integer jscount;
//    /**----组织名称---**/
//    @Column(name = "stockorgname", columnDefinition = "nvarchar2(128) NULL COMMENT '名称'")
//    private String stockorgname;


    public Integer getJscount() {
        return jscount;
    }

    public void setJscount(Integer jscount) {
        this.jscount = jscount;
    }

    public Integer getEnableSn() {
        return enableSn;
    }

    public void setEnableSn(Integer enableSn) {
        this.enableSn = enableSn;
    }

    public Integer getIsEquipment() {
        return isEquipment;
    }

    public void setIsEquipment(Integer isEquipment) {
        this.isEquipment = isEquipment;
    }

    public String getIsEnableSN() {
        return isEnableSN;
    }

    public void setIsEnableSN(String isEnableSN) {
        this.isEnableSN = isEnableSN;
    }

    public OrderDetails() {
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

    public String getErpid() {
        return erpid;
    }

    public void setErpid(String erpid) {
        this.erpid = erpid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getErpstockorgid() {
        return erpstockorgid;
    }

    public void setErpstockorgid(Integer erpstockorgid) {
        this.erpstockorgid = erpstockorgid;
    }

    public Integer getStockorgid() {
        return stockorgid;
    }

    public void setStockorgid(Integer stockorgid) {
        this.stockorgid = stockorgid;
    }

    public String getStockorgcode() {
        return stockorgcode;
    }

    public void setStockorgcode(String stockorgcode) {
        this.stockorgcode = stockorgcode;
    }

    public Integer getErpproviderdepid() {
        return erpproviderdepid;
    }

    public void setErpproviderdepid(Integer erpproviderdepid) {
        this.erpproviderdepid = erpproviderdepid;
    }

    public Integer getProviderdepid() {
        return providerdepid;
    }

    public void setProviderdepid(Integer providerdepid) {
        this.providerdepid = providerdepid;
    }

    public String getProviderdepcode() {
        return providerdepcode;
    }

    public void setProviderdepcode(String providerdepcode) {
        this.providerdepcode = providerdepcode;
    }

    public String getProviderdepname() {
        return providerdepname;
    }

    public void setProviderdepname(String providerdepname) {
        this.providerdepname = providerdepname;
    }

    public Integer getProviderplaceid() {
        return providerplaceid;
    }

    public void setProviderplaceid(Integer providerplaceid) {
        this.providerplaceid = providerplaceid;
    }

    public String getProviderplacecode() {
        return providerplacecode;
    }

    public void setProviderplacecode(String providerplacecode) {
        this.providerplacecode = providerplacecode;
    }

    public Integer getIssueid() {
        return issueid;
    }

    public void setIssueid(Integer issueid) {
        this.issueid = issueid;
    }

    public Integer getIssuecode() {
        return issuecode;
    }

    public void setIssuecode(Integer issuecode) {
        this.issuecode = issuecode;
    }

    public Integer getFyid() {
        return fyid;
    }

    public void setFyid(Integer fyid) {
        this.fyid = fyid;
    }

    public String getErprownum() {
        return erprownum;
    }

    public void setErprownum(String erprownum) {
        this.erprownum = erprownum;
    }

    public String getOrderrowid() {
        return orderrowid;
    }

    public void setOrderrowid(String orderrowid) {
        this.orderrowid = orderrowid;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Integer getParcode() {
        return parcode;
    }

    public void setParcode(Integer parcode) {
        this.parcode = parcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailunit() {
        return detailunit;
    }

    public void setDetailunit(String detailunit) {
        this.detailunit = detailunit;
    }

//    public Double getDetailcount() {
//        return detailcount;
//    }
//
//    public void setDetailcount(Double detailcount) {
//        this.detailcount = detailcount;
//    }

    public Double getNotaxprice() {
        return notaxprice;
    }

    public void setNotaxprice(Double notaxprice) {
        this.notaxprice = notaxprice;
    }

    public Double getNotaxsum() {
        return notaxsum;
    }

    public void setNotaxsum(Double notaxsum) {
        this.notaxsum = notaxsum;
    }

    public String getBaseunit() {
        return baseunit;
    }

    public void setBaseunit(String baseunit) {
        this.baseunit = baseunit;
    }

    public Double getBaseunitcount() {
        return baseunitcount;
    }

    public void setBaseunitcount(Double baseunitcount) {
        this.baseunitcount = baseunitcount;
    }

    public Double getBaseunitprice() {
        return baseunitprice;
    }

    public void setBaseunitprice(Double baseunitprice) {
        this.baseunitprice = baseunitprice;
    }

    public Double getBaseunitsum() {
        return baseunitsum;
    }

    public void setBaseunitsum(Double baseunitsum) {
        this.baseunitsum = baseunitsum;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getConsignment() {
        return consignment;
    }

    public void setConsignment(Integer consignment) {
        this.consignment = consignment;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialSpecification() {
        return materialSpecification;
    }

    public void setMaterialSpecification(String materialSpecification) {
        this.materialSpecification = materialSpecification;
    }

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public String getMaterialBrand() {
        return materialBrand;
    }

    public void setMaterialBrand(String materialBrand) {
        this.materialBrand = materialBrand;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getSparescateId() {
        return sparescateId;
    }

    public void setSparescateId(Integer sparescateId) {
        this.sparescateId = sparescateId;
    }

    public String getExtendstring1() {
        return extendstring1;
    }

    public void setExtendstring1(String extendstring1) {
        this.extendstring1 = extendstring1;
    }

    public Double getIsCount() {
        return isCount;
    }

    public void setIsCount(Double isCount) {
        this.isCount = isCount;
    }

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getJstype() {
        return jstype;
    }

    public void setJstype(String jstype) {
        this.jstype = jstype;
    }

//    public String getStockorgname() {
//        return stockorgname;
//    }
//
//    public void setStockorgname(String stockorgname) {
//        this.stockorgname = stockorgname;
//    }
}
