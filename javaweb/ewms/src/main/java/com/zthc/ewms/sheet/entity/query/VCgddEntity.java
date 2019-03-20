package com.zthc.ewms.sheet.entity.query;

import com.zthc.ewms.base.util.NumberUtils;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "v_orderdetails")
public class VCgddEntity {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(36) NULL COMMENT '唯一标识'")
    private String guid;
    /**
     * ----ERP ID---
     **/
    @Column(name = "erpid", columnDefinition = "nvarchar2(50) NULL COMMENT 'ERP ID'")
    private String erpid;
    /**
     * ----采购订单ID---
     **/
    @Column(name = "orderid", columnDefinition = "Integer NULL COMMENT '采购订单ID'")
    private Integer orderid;
    /**
     * ----采购订单编码---
     **/
    @Column(name = "ordernum", columnDefinition = "nvarchar2(50) NULL COMMENT '采购订单编码'")
    private String ordernum;
    /**
     * ----公司ID---
     **/
    @Column(name = "businessid", columnDefinition = "Integer NULL COMMENT '公司ID'")
    private Integer businessid;
    /**
     * ----ERP库房组织---
     **/
    @Column(name = "erpstockorgid", columnDefinition = "Integer NULL COMMENT 'ERP库房组织'")
    private Integer erpstockorgid;
    /**
     * ----库房组织ID---
     **/
    @Column(name = "stockorgid", columnDefinition = "Integer NULL COMMENT '库房组织ID'")
    private Integer stockorgid;
    /**----已接收数量---**/
    @Column(name="isCount", columnDefinition="number(18,9) NULL COMMENT '可接收数量'")
    private Double isCount;
    /**----未接收数量---**/
    @Column(name="noCount", columnDefinition="number(18,9) NULL COMMENT '可接收数量'")
    private Double noCount;
    /**
     * ----库房组织编码---
     **/
    @Column(name = "stockorgcode", columnDefinition = "nvarchar2(50) NULL COMMENT '库房组织编码'")
    private String stockorgcode;
    /**
     * ----ERP供应商ID---
     **/
    @Column(name = "erpproviderdepid", columnDefinition = "Integer NULL COMMENT 'ERP供应商ID'")
    private Integer erpproviderdepid;
    /**
     * ----供应商ID---
     **/
    @Column(name = "providerdepid", columnDefinition = "Integer NULL COMMENT '供应商ID'")
    private Integer providerdepid;
    /**
     * ----供应商编码---
     **/
    @Column(name = "providerdepcode", columnDefinition = "nvarchar2(50) NULL COMMENT '供应商编码'")
    private String providerdepcode;
    /**
     * ----供应商名称---
     **/
    @Column(name = "providerdepname", columnDefinition = "nvarchar2(250) NULL COMMENT '供应商名称'")
    private String providerdepname;
    /**
     * ----供应商地址ID---
     **/
    @Column(name = "providerplaceid", columnDefinition = "Integer NULL COMMENT '供应商地址ID'")
    private Integer providerplaceid;
    /**
     * ----供应商地址名称---
     **/
    @Column(name = "providerplacecode", columnDefinition = "nvarchar2(50) NULL COMMENT '供应商地址名称'")
    private String providerplacecode;
    /**
     * ----发放ID---
     **/
    @Column(name = "issueid", columnDefinition = "Integer NULL COMMENT '发放ID'")
    private Integer issueid;
    /**
     * ----发放号---
     **/
    @Column(name = "ffcode", columnDefinition = "Integer NULL COMMENT '发放号'")
    private Integer ffcode;
    /**
     * ----发运ID---
     **/
    @Column(name = "fyid", columnDefinition = "Integer NULL COMMENT '发运ID'")
    private Integer fyid;
    /**
     * ----ERP行号---
     **/
    @Column(name = "erprownum", columnDefinition = "nvarchar2(50) NULL COMMENT 'ERP行号'")
    private String erprownum;
    /**
     * ----采购订单行号---
     **/
    @Column(name = "orderrowid", columnDefinition = "nvarchar2(50) NULL COMMENT '采购订单行号'")
    private String orderrowid;
    /**
     * ----物料编码---
     **/
    @Column(name = "materialcode", columnDefinition = "nvarchar2(50) NULL COMMENT '物料编码'")
    private String materialcode;
    /**
     * ----物料描述---
     **/
    @Column(name = "description", columnDefinition = "nvarchar2(500) NULL COMMENT '物料描述'")
    private String description;
    /**
     * ----明细单位---
     **/
    @Column(name = "detailunit", columnDefinition = "nvarchar2(50) NULL COMMENT '明细单位'")
    private String detailunit;
    /**
     * ----明细数量---
     **/
    @Column(name = "detailcount", columnDefinition = "number(18,9) NULL COMMENT '明细数量'")
    private Double detailcount;
    /**
     * ----不含税单价---
     **/
    @Column(name = "notaxprice", columnDefinition = "number(18,9) NULL COMMENT '不含税单价'")
    private Double notaxprice;
    /**
     * ----不含税总金额---
     **/
    @Column(name = "notaxsum", columnDefinition = "number(18,9) NULL COMMENT '不含税总金额'")
    private Double notaxsum;
    /**
     * ----基础单位---
     **/
    @Column(name = "baseunit", columnDefinition = "nvarchar2(50) NULL COMMENT '基础单位'")
    private String baseunit;
    /**
     * ----基本单位数量---
     **/
    @Column(name = "baseunitcount", columnDefinition = "number(18,9) NULL COMMENT '基本单位数量'")
    private Double baseunitcount;
    /**
     * ----基本单位不含税单价---
     **/
    @Column(name = "baseunitprice", columnDefinition = "number(18,9) NULL COMMENT '基本单位不含税单价'")
    private Double baseunitprice;
    /**
     * ----基本单位不含税总金额---
     **/
    @Column(name = "baseunitsum", columnDefinition = "number(18,9) NULL COMMENT '基本单位不含税总金额'")
    private Double baseunitsum;
    /**
     * ----税率---
     **/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '税率'")
    private Double taxRate;
    /**
     * ----是否寄售---
     **/
    @Column(name = "consignment", columnDefinition = "Integer NULL COMMENT '是否寄售'")
    private Integer consignment;
    /**
     * ----部门ID---
     **/
    @Column(name = "departid", columnDefinition = "Integer NULL COMMENT '部门ID'")
    private Integer departid;
    /**
     * ----创建人ID---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '创建人ID'")
    private Integer creator;
    /**
     * ----创建时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createdate", columnDefinition = "datetime NULL COMMENT '创建时间'")
    private Date createdate;
    /**
     * ----更新人ID---
     **/
    @Column(name = "updator", columnDefinition = "Integer NULL COMMENT '更新人ID'")
    private Integer updator;
    /**
     * ----更新时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updatedate", columnDefinition = "datetime NULL COMMENT '更新时间'")
    private Date updatedate;
    /**
     * ----备用int字段---
     **/
    @Column(name = "extendint1", columnDefinition = "Integer NULL COMMENT '备用int字段'")
    private Integer extendint1;
    /**
     * ----备用int字段---
     **/
    @Column(name = "extendint2", columnDefinition = "Integer NULL COMMENT '备用int字段'")
    private Integer extendint2;
    /**
     * ----备用int字段---
     **/
    @Column(name = "extendint3", columnDefinition = "Integer NULL COMMENT '备用int字段'")
    private Integer extendint3;
    /**
     * ----备用int字段---
     **/
    @Column(name = "extendint4", columnDefinition = "Integer NULL COMMENT '备用int字段'")
    private Integer extendint4;
    /**
     * ----备用int字段---
     **/
    @Column(name = "extendint5", columnDefinition = "Integer NULL COMMENT '备用int字段'")
    private Integer extendint5;
    /**
     * ----备用int字段---
     **/
    @Column(name = "extendint6", columnDefinition = "Integer NULL COMMENT '备用int字段'")
    private Integer extendint6;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendfloat1", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendfloat1;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendfloat2", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendfloat2;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendfloat3", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendfloat3;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendfloat4", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendfloat4;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendfloat5", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendfloat5;
    /**
     * ----备用float字段---
     **/
    @Column(name = "extendfloat6", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
    private Double extendfloat6;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendstring1", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring1;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendstring2", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring2;
    /**
     * ----状态---
     **/
    @Column(name = "status", columnDefinition = "nvarchar2(64) NULL COMMENT '状态'")
    private String status;
    /**
     * ----订单类型---
     **/
    @Column(name = "ordertype", columnDefinition = "nvarchar2(64) NULL COMMENT '状态'")
    private String ordertype;
    /**
     * ----库存组织名称---
     **/
    @Column(name = "stockorgname", columnDefinition = "nvarchar2(64) NULL COMMENT '库存组织名称'")
    private String stockorgname;
    /**
     * ----是否寄售---
     **/
    @Column(name = "consignmentname", columnDefinition = "nvarchar2(64) NULL COMMENT '是否寄售'")
    private String consignmentname;

    
    
    public Double getNoCount() {
		return noCount;
	}

	public void setNoCount(Double noCount) {
		this.noCount = noCount;
	}

	public Double getIsCount() {
		return isCount;
	}

	public void setIsCount(Double isCount) {
		this.isCount = isCount;
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

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
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

    public Double getDetailcount() {
        return detailcount;
    }

    public void setDetailcount(Double detailcount) {
        this.detailcount = detailcount;
    }

    public Double getNotaxprice() {
        return notaxprice;
    }

    public void setNotaxprice(Double notaxprice) {
        this.notaxprice = notaxprice;
    }

    public String getNotaxpriceDouble() {
        return NumberUtils.getDouble(notaxprice);
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

    public Integer getExtendint1() {
        return extendint1;
    }

    public void setExtendint1(Integer extendint1) {
        this.extendint1 = extendint1;
    }

    public Integer getExtendint2() {
        return extendint2;
    }

    public void setExtendint2(Integer extendint2) {
        this.extendint2 = extendint2;
    }

    public Integer getExtendint3() {
        return extendint3;
    }

    public void setExtendint3(Integer extendint3) {
        this.extendint3 = extendint3;
    }

    public Integer getExtendint4() {
        return extendint4;
    }

    public void setExtendint4(Integer extendint4) {
        this.extendint4 = extendint4;
    }

    public Integer getExtendint5() {
        return extendint5;
    }

    public void setExtendint5(Integer extendint5) {
        this.extendint5 = extendint5;
    }

    public Integer getExtendint6() {
        return extendint6;
    }

    public void setExtendint6(Integer extendint6) {
        this.extendint6 = extendint6;
    }

    public Double getExtendfloat1() {
        return extendfloat1;
    }

    public void setExtendfloat1(Double extendfloat1) {
        this.extendfloat1 = extendfloat1;
    }

    public Double getExtendfloat2() {
        return extendfloat2;
    }

    public void setExtendfloat2(Double extendfloat2) {
        this.extendfloat2 = extendfloat2;
    }

    public Double getExtendfloat3() {
        return extendfloat3;
    }

    public void setExtendfloat3(Double extendfloat3) {
        this.extendfloat3 = extendfloat3;
    }

    public Double getExtendfloat4() {
        return extendfloat4;
    }

    public void setExtendfloat4(Double extendfloat4) {
        this.extendfloat4 = extendfloat4;
    }

    public Double getExtendfloat5() {
        return extendfloat5;
    }

    public void setExtendfloat5(Double extendfloat5) {
        this.extendfloat5 = extendfloat5;
    }

    public Double getExtendfloat6() {
        return extendfloat6;
    }

    public void setExtendfloat6(Double extendfloat6) {
        this.extendfloat6 = extendfloat6;
    }

    public String getExtendstring1() {
        return extendstring1;
    }

    public void setExtendstring1(String extendstring1) {
        this.extendstring1 = extendstring1;
    }

    public String getExtendstring2() {
        return extendstring2;
    }

    public void setExtendstring2(String extendstring2) {
        this.extendstring2 = extendstring2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getStockorgname() {
        return stockorgname;
    }

    public void setStockorgname(String stockorgname) {
        this.stockorgname = stockorgname;
    }

    public String getConsignmentname() {
        return consignmentname;
    }

    public void setConsignmentname(String consignmentname) {
        this.consignmentname = consignmentname;
    }

    public Integer getFfcode() {
        return ffcode;
    }

    public void setFfcode(Integer ffcode) {
        this.ffcode = ffcode;
    }

    public VCgddEntity() {
    }
}
