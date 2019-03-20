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
     * ----Ψһ��ʶ---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(36) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
    /**
     * ----ERP ID---
     **/
    @Column(name = "erpid", columnDefinition = "nvarchar2(50) NULL COMMENT 'ERP ID'")
    private String erpid;
    /**
     * ----�ɹ�����ID---
     **/
    @Column(name = "orderid", columnDefinition = "Integer NULL COMMENT '�ɹ�����ID'")
    private Integer orderid;
    /**
     * ----�ɹ���������---
     **/
    @Column(name = "ordernum", columnDefinition = "nvarchar2(50) NULL COMMENT '�ɹ���������'")
    private String ordernum;
    /**
     * ----��˾ID---
     **/
    @Column(name = "businessid", columnDefinition = "Integer NULL COMMENT '��˾ID'")
    private Integer businessid;
    /**
     * ----ERP�ⷿ��֯---
     **/
    @Column(name = "erpstockorgid", columnDefinition = "Integer NULL COMMENT 'ERP�ⷿ��֯'")
    private Integer erpstockorgid;
    /**
     * ----�ⷿ��֯ID---
     **/
    @Column(name = "stockorgid", columnDefinition = "Integer NULL COMMENT '�ⷿ��֯ID'")
    private Integer stockorgid;
    /**----�ѽ�������---**/
    @Column(name="isCount", columnDefinition="number(18,9) NULL COMMENT '�ɽ�������'")
    private Double isCount;
    /**----δ��������---**/
    @Column(name="noCount", columnDefinition="number(18,9) NULL COMMENT '�ɽ�������'")
    private Double noCount;
    /**
     * ----�ⷿ��֯����---
     **/
    @Column(name = "stockorgcode", columnDefinition = "nvarchar2(50) NULL COMMENT '�ⷿ��֯����'")
    private String stockorgcode;
    /**
     * ----ERP��Ӧ��ID---
     **/
    @Column(name = "erpproviderdepid", columnDefinition = "Integer NULL COMMENT 'ERP��Ӧ��ID'")
    private Integer erpproviderdepid;
    /**
     * ----��Ӧ��ID---
     **/
    @Column(name = "providerdepid", columnDefinition = "Integer NULL COMMENT '��Ӧ��ID'")
    private Integer providerdepid;
    /**
     * ----��Ӧ�̱���---
     **/
    @Column(name = "providerdepcode", columnDefinition = "nvarchar2(50) NULL COMMENT '��Ӧ�̱���'")
    private String providerdepcode;
    /**
     * ----��Ӧ������---
     **/
    @Column(name = "providerdepname", columnDefinition = "nvarchar2(250) NULL COMMENT '��Ӧ������'")
    private String providerdepname;
    /**
     * ----��Ӧ�̵�ַID---
     **/
    @Column(name = "providerplaceid", columnDefinition = "Integer NULL COMMENT '��Ӧ�̵�ַID'")
    private Integer providerplaceid;
    /**
     * ----��Ӧ�̵�ַ����---
     **/
    @Column(name = "providerplacecode", columnDefinition = "nvarchar2(50) NULL COMMENT '��Ӧ�̵�ַ����'")
    private String providerplacecode;
    /**
     * ----����ID---
     **/
    @Column(name = "issueid", columnDefinition = "Integer NULL COMMENT '����ID'")
    private Integer issueid;
    /**
     * ----���ź�---
     **/
    @Column(name = "ffcode", columnDefinition = "Integer NULL COMMENT '���ź�'")
    private Integer ffcode;
    /**
     * ----����ID---
     **/
    @Column(name = "fyid", columnDefinition = "Integer NULL COMMENT '����ID'")
    private Integer fyid;
    /**
     * ----ERP�к�---
     **/
    @Column(name = "erprownum", columnDefinition = "nvarchar2(50) NULL COMMENT 'ERP�к�'")
    private String erprownum;
    /**
     * ----�ɹ������к�---
     **/
    @Column(name = "orderrowid", columnDefinition = "nvarchar2(50) NULL COMMENT '�ɹ������к�'")
    private String orderrowid;
    /**
     * ----���ϱ���---
     **/
    @Column(name = "materialcode", columnDefinition = "nvarchar2(50) NULL COMMENT '���ϱ���'")
    private String materialcode;
    /**
     * ----��������---
     **/
    @Column(name = "description", columnDefinition = "nvarchar2(500) NULL COMMENT '��������'")
    private String description;
    /**
     * ----��ϸ��λ---
     **/
    @Column(name = "detailunit", columnDefinition = "nvarchar2(50) NULL COMMENT '��ϸ��λ'")
    private String detailunit;
    /**
     * ----��ϸ����---
     **/
    @Column(name = "detailcount", columnDefinition = "number(18,9) NULL COMMENT '��ϸ����'")
    private Double detailcount;
    /**
     * ----����˰����---
     **/
    @Column(name = "notaxprice", columnDefinition = "number(18,9) NULL COMMENT '����˰����'")
    private Double notaxprice;
    /**
     * ----����˰�ܽ��---
     **/
    @Column(name = "notaxsum", columnDefinition = "number(18,9) NULL COMMENT '����˰�ܽ��'")
    private Double notaxsum;
    /**
     * ----������λ---
     **/
    @Column(name = "baseunit", columnDefinition = "nvarchar2(50) NULL COMMENT '������λ'")
    private String baseunit;
    /**
     * ----������λ����---
     **/
    @Column(name = "baseunitcount", columnDefinition = "number(18,9) NULL COMMENT '������λ����'")
    private Double baseunitcount;
    /**
     * ----������λ����˰����---
     **/
    @Column(name = "baseunitprice", columnDefinition = "number(18,9) NULL COMMENT '������λ����˰����'")
    private Double baseunitprice;
    /**
     * ----������λ����˰�ܽ��---
     **/
    @Column(name = "baseunitsum", columnDefinition = "number(18,9) NULL COMMENT '������λ����˰�ܽ��'")
    private Double baseunitsum;
    /**
     * ----˰��---
     **/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double taxRate;
    /**
     * ----�Ƿ����---
     **/
    @Column(name = "consignment", columnDefinition = "Integer NULL COMMENT '�Ƿ����'")
    private Integer consignment;
    /**
     * ----����ID---
     **/
    @Column(name = "departid", columnDefinition = "Integer NULL COMMENT '����ID'")
    private Integer departid;
    /**
     * ----������ID---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '������ID'")
    private Integer creator;
    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createdate", columnDefinition = "datetime NULL COMMENT '����ʱ��'")
    private Date createdate;
    /**
     * ----������ID---
     **/
    @Column(name = "updator", columnDefinition = "Integer NULL COMMENT '������ID'")
    private Integer updator;
    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updatedate", columnDefinition = "datetime NULL COMMENT '����ʱ��'")
    private Date updatedate;
    /**
     * ----����int�ֶ�---
     **/
    @Column(name = "extendint1", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
    private Integer extendint1;
    /**
     * ----����int�ֶ�---
     **/
    @Column(name = "extendint2", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
    private Integer extendint2;
    /**
     * ----����int�ֶ�---
     **/
    @Column(name = "extendint3", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
    private Integer extendint3;
    /**
     * ----����int�ֶ�---
     **/
    @Column(name = "extendint4", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
    private Integer extendint4;
    /**
     * ----����int�ֶ�---
     **/
    @Column(name = "extendint5", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
    private Integer extendint5;
    /**
     * ----����int�ֶ�---
     **/
    @Column(name = "extendint6", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
    private Integer extendint6;
    /**
     * ----����float�ֶ�---
     **/
    @Column(name = "extendfloat1", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
    private Double extendfloat1;
    /**
     * ----����float�ֶ�---
     **/
    @Column(name = "extendfloat2", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
    private Double extendfloat2;
    /**
     * ----����float�ֶ�---
     **/
    @Column(name = "extendfloat3", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
    private Double extendfloat3;
    /**
     * ----����float�ֶ�---
     **/
    @Column(name = "extendfloat4", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
    private Double extendfloat4;
    /**
     * ----����float�ֶ�---
     **/
    @Column(name = "extendfloat5", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
    private Double extendfloat5;
    /**
     * ----����float�ֶ�---
     **/
    @Column(name = "extendfloat6", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
    private Double extendfloat6;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring1", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring1;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring2", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring2;
    /**
     * ----״̬---
     **/
    @Column(name = "status", columnDefinition = "nvarchar2(64) NULL COMMENT '״̬'")
    private String status;
    /**
     * ----��������---
     **/
    @Column(name = "ordertype", columnDefinition = "nvarchar2(64) NULL COMMENT '״̬'")
    private String ordertype;
    /**
     * ----�����֯����---
     **/
    @Column(name = "stockorgname", columnDefinition = "nvarchar2(64) NULL COMMENT '�����֯����'")
    private String stockorgname;
    /**
     * ----�Ƿ����---
     **/
    @Column(name = "consignmentname", columnDefinition = "nvarchar2(64) NULL COMMENT '�Ƿ����'")
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
