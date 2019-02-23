package com.zthc.ewms.sheet.entity.guard;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: ���������ϸ
 * @Package
 */
@Entity
@Table(name = "WZ_SHEETRKDETAIL")
public class SheetRKDETAIL {

    /**
     * ----id---
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "WZSHEETRKDETAIL_SEQUENCE", allocationSize = 1)
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----Ψһ��ʶ---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
    /**
     * ----����ID---
     **/
    @Column(name = "sheetid", columnDefinition = "Integer NULL COMMENT '����ID'")
    private Integer sheetid;

    /*@Column(name = "extendInt1")
    private Integer extendInt1;*/
    /**
     * ----������ϸID---
     **/
    @Column(name = "sheetdetailid", columnDefinition = "Integer NULL COMMENT '������ϸID'")
    private Integer sheetdetailid;
    /**
     * ----���Ϸ���ID---
     **/
    @Column(name = "categoryid", columnDefinition = "Integer NULL COMMENT '���Ϸ���ID'")
    private Integer categoryid;
    /**
     * ----����ID---
     **/
    @Column(name = "materialid", columnDefinition = "Integer NULL COMMENT '����ID'")
    private Integer materialid;
    /**
     * ----���ϱ���---
     **/
    @Column(name = "materialcode", columnDefinition = "nvarchar2(50) NULL COMMENT '���ϱ���'")
    private String materialcode;
    /**
     * ----��������---
     **/
    @Column(name = "materialname", columnDefinition = "nvarchar2(255) NULL COMMENT '��������'")
    private String materialname;
    /**
     * ----����Ʒ��---
     **/
    @Column(name = "materialbrand", columnDefinition = "nvarchar2(255) NULL COMMENT '����Ʒ��'")
    private String materialbrand;
    /**
     * ----���Ϲ��---
     **/
    @Column(name = "materialmodel", columnDefinition = "nvarchar2(255) NULL COMMENT '���Ϲ��'")
    private String materialmodel;
    /**
     * ----��ϸ��λ---
     **/
    @Column(name = "detailunit", columnDefinition = "Integer NULL COMMENT '��ϸ��λ'")
    private Integer detailunit;
    /**
     * ----��λ��λ---
     **/
    @Column(name = "currencyunit", columnDefinition = "nvarchar2(20) NULL COMMENT '��λ��λ'")
    private String currencyunit;
    /**
     * ----�ⷿID---
     **/
    @Column(name = "storeid", columnDefinition = "Integer NULL COMMENT '�ⷿID'")
    private Integer storeid;
    /**
     * ----��Ӧ��ID---
     **/
    @Column(name = "providerdepid", columnDefinition = "Integer NULL COMMENT '��Ӧ��ID'")
    private Integer providerdepid;
    /**
     * ----״̬---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '״̬'")
    private Integer status;
    /**
     * ----��ע---
     **/
    @Column(name = "memo", columnDefinition = "nvarchar2(255) NULL COMMENT '��ע'")
    private String memo;
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
     * ----�����֯ID---
     **/
    @Column(name = "ztid", columnDefinition = "Integer NULL COMMENT '�����֯ID'")
    private Integer ztid;
    /**
     * ----SheetDeatils ID---
     **/
    @Column(name = "extendint1", columnDefinition = "Integer NULL COMMENT 'SheetDeatilsID '")
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
     * ----����int�ֶ�---
     **/
    @Column(name = "extendint7", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
    private Integer extendint7;
    /**
     * ----����int�ֶ�---
     **/
    @Column(name = "extendint8", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
    private Integer extendint8;
    /**
     * ----��˰����---
     **/
    @Column(name = "extendfloat1", columnDefinition = "number(18,9) NULL COMMENT '��˰����'")
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
     * ----����float�ֶ�---
     **/
    @Column(name = "extendfloat7", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
    private Double extendfloat7;
    /**
     * ----����float�ֶ�---
     **/
    @Column(name = "extendfloat8", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
    private Double extendfloat8;
    /**
     * ----�ⷿ����---
     **/
    @Column(name = "extendstring1", columnDefinition = "nvarchar2(255) NULL COMMENT '�ⷿ����'")
    private String extendstring1;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring2", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring2;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring3", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring3;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring4", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring4;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring5", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring5;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring6", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring6;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring7", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring7;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring8", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring8;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring9", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring9;
    /**
     * ----����string�ֶ�---
     **/
    @Column(name = "extendstring10", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendstring10;
    /**
     * ----����date�ֶ�---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "extenddate1", columnDefinition = "datetime NULL COMMENT '����date�ֶ�'")
    private Date extenddate1;
    /**
     * ----����date�ֶ�---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "extenddate2", columnDefinition = "datetime NULL COMMENT '����date�ֶ�'")
    private Date extenddate2;
    /**
     * ----����date�ֶ�---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "extenddate3", columnDefinition = "datetime NULL COMMENT '����date�ֶ�'")
    private Date extenddate3;
    /**
     * ----����date�ֶ�---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "extenddate4", columnDefinition = "datetime NULL COMMENT '����date�ֶ�'")
    private Date extenddate4;
    /**
     * ----����˰����---
     **/
    @Column(name = "notaxprice", columnDefinition = "number(18,9) NULL COMMENT '����˰����'")
    private Double notaxprice;
    /**
     * ----˰��---
     **/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double taxRate;
    /**
     * ----����˰���---
     **/
    @Column(name = "notaxsum", columnDefinition = "number(18,9) NULL COMMENT '����˰���'")
    private Double notaxsum;
    /**
     * ----���Ϲ��---
     **/
    @Column(name = "materialspecification", columnDefinition = "nvarchar2(255) NULL COMMENT '���Ϲ��'")
    private String materialspecification;
    /**
     * ----��������---
     **/
    @Column(name = "description", columnDefinition = "nvarchar2(500) NULL COMMENT '��������'")
    private String description;
    /**
     * ----�ʱ�����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "expirationtime", columnDefinition = "datetime NULL COMMENT '�ʱ�����ʱ��'")
    private Date expirationtime;
    /**
     * ----��λ����---
     **/
    @Column(name = "storelocationcode", columnDefinition = "nvarchar2(255) NULL COMMENT '��λ����'")
    private String storelocationcode;
    /**
     * ----��ǩ����---
     **/
    @Column(name = "tagcode", columnDefinition = "nvarchar2(50) NULL COMMENT '��ǩ����'")
    private String tagcode;
    /**
     * ----��˰����---
     **/
    @Column(name = "taxprice", columnDefinition = "number(18,9) NULL COMMENT '��˰����'")
    private Double taxprice;
    /**
     * ----��˰�ܽ��---
     **/
    @Column(name = "taxsum", columnDefinition = "number(18,9) NULL COMMENT '��˰�ܽ��'")
    private Double taxsum;
    /**
     * ----��λID---
     **/
    @Column(name = "storelocationid", columnDefinition = "Integer NULL COMMENT '��λID'")
    private Integer storelocationid;
    /**
     * ----�ƻ�����ID---
     **/
    @Column(name = "plandepartid", columnDefinition = "Integer NULL COMMENT '�ƻ�����ID'")
    private Integer plandepartid;
    /**
     * ----��λ����---
     **/
    @Column(name = "storelocationname", columnDefinition = "nvarchar2(50) NULL COMMENT '��λ����'")
    private String storelocationname;
    /**
     * ----�������---
     **/
    @Column(name = "detailcount", columnDefinition = "number(18,9) NULL COMMENT '�������'")
    private Double detailcount;
    /**
     * ----�Ƿ�Ϊ�豸---
     **/
    @Column(name = "isequipment", columnDefinition = "Integer NULL COMMENT '�Ƿ�Ϊ�豸'")
    private Integer isequipment;
    /**
     * ----�Ĵ�����---
     **/
    @Column(name = "ownertype", columnDefinition = "Integer NULL COMMENT '�Ĵ�����'")
    private Integer ownertype;
    /**
     * ----�Ƿ��������к�---
     **/
    @Column(name = "enablesn", columnDefinition = "Integer NULL COMMENT '�Ƿ��������к�'")
    private Integer enablesn;
    /**
     * ----���кű���---
     **/
    @Column(name = "sncode", columnDefinition = "nvarchar2(50) NULL COMMENT '���кű���'")
    private String sncode;
    /**
     * ----��ϸ��λ---
     **/
    @Column(name = "detailunitname", columnDefinition = "nvarchar2(20) NULL COMMENT '��ϸ��λ'")
    private String detailunitname;

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

    public Integer getSheetid() {
        return sheetid;
    }

    public void setSheetid(Integer sheetid) {
        this.sheetid = sheetid;
    }

    public Integer getSheetdetailid() {
        return sheetdetailid;
    }

    public void setSheetdetailid(Integer sheetdetailid) {
        this.sheetdetailid = sheetdetailid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Integer materialid) {
        this.materialid = materialid;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getMaterialbrand() {
        return materialbrand;
    }

    public void setMaterialbrand(String materialbrand) {
        this.materialbrand = materialbrand;
    }

    public String getMaterialmodel() {
        return materialmodel;
    }

    public void setMaterialmodel(String materialmodel) {
        this.materialmodel = materialmodel;
    }

    public Integer getDetailunit() {
        return detailunit;
    }

    public void setDetailunit(Integer detailunit) {
        this.detailunit = detailunit;
    }

    public String getCurrencyunit() {
        return currencyunit;
    }

    public void setCurrencyunit(String currencyunit) {
        this.currencyunit = currencyunit;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Integer getProviderdepid() {
        return providerdepid;
    }

    public void setProviderdepid(Integer providerdepid) {
        this.providerdepid = providerdepid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public Integer getZtid() {
        return ztid;
    }

    public void setZtid(Integer ztid) {
        this.ztid = ztid;
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

    public Integer getExtendint7() {
        return extendint7;
    }

    public void setExtendint7(Integer extendint7) {
        this.extendint7 = extendint7;
    }

    public Integer getExtendint8() {
        return extendint8;
    }

    public void setExtendint8(Integer extendint8) {
        this.extendint8 = extendint8;
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

    public Double getExtendfloat7() {
        return extendfloat7;
    }

    public void setExtendfloat7(Double extendfloat7) {
        this.extendfloat7 = extendfloat7;
    }

    public Double getExtendfloat8() {
        return extendfloat8;
    }

    public void setExtendfloat8(Double extendfloat8) {
        this.extendfloat8 = extendfloat8;
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

    public String getExtendstring3() {
        return extendstring3;
    }

    public void setExtendstring3(String extendstring3) {
        this.extendstring3 = extendstring3;
    }

    public String getExtendstring4() {
        return extendstring4;
    }

    public void setExtendstring4(String extendstring4) {
        this.extendstring4 = extendstring4;
    }

    public String getExtendstring5() {
        return extendstring5;
    }

    public void setExtendstring5(String extendstring5) {
        this.extendstring5 = extendstring5;
    }

    public String getExtendstring6() {
        return extendstring6;
    }

    public void setExtendstring6(String extendstring6) {
        this.extendstring6 = extendstring6;
    }

    public String getExtendstring7() {
        return extendstring7;
    }

    public void setExtendstring7(String extendstring7) {
        this.extendstring7 = extendstring7;
    }

    public String getExtendstring8() {
        return extendstring8;
    }

    public void setExtendstring8(String extendstring8) {
        this.extendstring8 = extendstring8;
    }

    public String getExtendstring9() {
        return extendstring9;
    }

    public void setExtendstring9(String extendstring9) {
        this.extendstring9 = extendstring9;
    }

    public String getExtendstring10() {
        return extendstring10;
    }

    public void setExtendstring10(String extendstring10) {
        this.extendstring10 = extendstring10;
    }

    public Date getExtenddate1() {
        return extenddate1;
    }

    public void setExtenddate1(Date extenddate1) {
        this.extenddate1 = extenddate1;
    }

    public Date getExtenddate2() {
        return extenddate2;
    }

    public void setExtenddate2(Date extenddate2) {
        this.extenddate2 = extenddate2;
    }

    public Date getExtenddate3() {
        return extenddate3;
    }

    public void setExtenddate3(Date extenddate3) {
        this.extenddate3 = extenddate3;
    }

    public Date getExtenddate4() {
        return extenddate4;
    }

    public void setExtenddate4(Date extenddate4) {
        this.extenddate4 = extenddate4;
    }

    public String getMaterialspecification() {
        return materialspecification;
    }

    public void setMaterialspecification(String materialspecification) {
        this.materialspecification = materialspecification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpirationtime() {
        return expirationtime;
    }

    public void setExpirationtime(Date expirationtime) {
        this.expirationtime = expirationtime;
    }

    public String getStorelocationcode() {
        return storelocationcode;
    }

    public void setStorelocationcode(String storelocationcode) {
        this.storelocationcode = storelocationcode;
    }

    public String getTagcode() {
        return tagcode;
    }

    public void setTagcode(String tagcode) {
        this.tagcode = tagcode;
    }

    public Integer getStorelocationid() {
        return storelocationid;
    }

    public void setStorelocationid(Integer storelocationid) {
        this.storelocationid = storelocationid;
    }

    public Integer getPlandepartid() {
        return plandepartid;
    }

    public void setPlandepartid(Integer plandepartid) {
        this.plandepartid = plandepartid;
    }

    public String getStorelocationname() {
        return storelocationname;
    }

    public void setStorelocationname(String storelocationname) {
        this.storelocationname = storelocationname;
    }

    public Double getNotaxprice() {
        return notaxprice;
    }

    public void setNotaxprice(Double notaxprice) {
        this.notaxprice = notaxprice;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getNotaxsum() {
        return notaxsum;
    }

    public void setNotaxsum(Double notaxsum) {
        this.notaxsum = notaxsum;
    }

    public Double getTaxprice() {
        return taxprice;
    }

    public void setTaxprice(Double taxprice) {
        this.taxprice = taxprice;
    }

    public Double getTaxsum() {
        return taxsum;
    }

    public void setTaxsum(Double taxsum) {
        this.taxsum = taxsum;
    }

    public Double getDetailcount() {
        return detailcount;
    }

    public void setDetailcount(Double detailcount) {
        this.detailcount = detailcount;
    }

    public Integer getIsequipment() {
        return isequipment;
    }

    public void setIsequipment(Integer isequipment) {
        this.isequipment = isequipment;
    }

    public Integer getOwnertype() {
        return ownertype;
    }

    public void setOwnertype(Integer ownertype) {
        this.ownertype = ownertype;
    }

    public Integer getEnablesn() {
        return enablesn;
    }

    public void setEnablesn(Integer enablesn) {
        this.enablesn = enablesn;
    }

    public String getSncode() {
        return sncode;
    }

    public void setSncode(String sncode) {
        this.sncode = sncode;
    }

    public String getDetailunitname() {
        return detailunitname;
    }

    public void setDetailunitname(String detailunitname) {
        this.detailunitname = detailunitname;
    }

    /*public Integer getExtendInt1() {
        return extendInt1;
    }

    public void setExtendInt1(Integer extendInt1) {
        this.extendInt1 = extendInt1;
    }*/

    public SheetRKDETAIL() {

    }

    public SheetRKDETAIL(Integer id) {
        this.id = id;
    }
}