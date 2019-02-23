package com.zthc.ewms.sheet.entity.query;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "V_KCSUBDETAIL")
public class VKcsubdetailEntity {
    private long id;
    private String guid;
    private String tagcode;
    private Long sheetid;
    private Long sheetdetailid;
    private Long materialid;
    private Long categoryid;
    private String materialcode;
    private String materialname;
    private String materialbrand;
    private String materialmodel;
    private String materialspecification;
    private String description;
    private Long storecount;
    private Long notaxprice;
    private Long taxprice;
    private Long taxrate;
    private String ordernum;
    private String receivenum;
    private String detailunit;
    private String currencyunit;
    private Long storeid;
    private Long providerdepid;
    private String storelocationcode;
    private String storelocationname;
    private Long storelocationid;
    private Long status;
    private String memo;
    private Long creator;
    private Time createdate;
    private Time expirationtime;
    private Long ztid;
    private String detailunitname;
    private String housename;
    private String housecode;
    private String personname;
    private String flname;
    private String gysname;
    private String jhname;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "GUID")
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "TAGCODE")
    public String getTagcode() {
        return tagcode;
    }

    public void setTagcode(String tagcode) {
        this.tagcode = tagcode;
    }

    @Basic
    @Column(name = "SHEETID")
    public Long getSheetid() {
        return sheetid;
    }

    public void setSheetid(Long sheetid) {
        this.sheetid = sheetid;
    }

    @Basic
    @Column(name = "SHEETDETAILID")
    public Long getSheetdetailid() {
        return sheetdetailid;
    }

    public void setSheetdetailid(Long sheetdetailid) {
        this.sheetdetailid = sheetdetailid;
    }

    @Basic
    @Column(name = "MATERIALID")
    public Long getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Long materialid) {
        this.materialid = materialid;
    }

    @Basic
    @Column(name = "CATEGORYID")
    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    @Basic
    @Column(name = "MATERIALCODE")
    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    @Basic
    @Column(name = "MATERIALNAME")
    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    @Basic
    @Column(name = "MATERIALBRAND")
    public String getMaterialbrand() {
        return materialbrand;
    }

    public void setMaterialbrand(String materialbrand) {
        this.materialbrand = materialbrand;
    }

    @Basic
    @Column(name = "MATERIALMODEL")
    public String getMaterialmodel() {
        return materialmodel;
    }

    public void setMaterialmodel(String materialmodel) {
        this.materialmodel = materialmodel;
    }

    @Basic
    @Column(name = "MATERIALSPECIFICATION")
    public String getMaterialspecification() {
        return materialspecification;
    }

    public void setMaterialspecification(String materialspecification) {
        this.materialspecification = materialspecification;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "STORECOUNT")
    public Long getStorecount() {
        return storecount;
    }

    public void setStorecount(Long storecount) {
        this.storecount = storecount;
    }

    @Basic
    @Column(name = "NOTAXPRICE")
    public Long getNotaxprice() {
        return notaxprice;
    }

    public void setNotaxprice(Long notaxprice) {
        this.notaxprice = notaxprice;
    }

    @Basic
    @Column(name = "TAXPRICE")
    public Long getTaxprice() {
        return taxprice;
    }

    public void setTaxprice(Long taxprice) {
        this.taxprice = taxprice;
    }

    @Basic
    @Column(name = "TAXRATE")
    public Long getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(Long taxrate) {
        this.taxrate = taxrate;
    }

    @Basic
    @Column(name = "ORDERNUM")
    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    @Basic
    @Column(name = "RECEIVENUM")
    public String getReceivenum() {
        return receivenum;
    }

    public void setReceivenum(String receivenum) {
        this.receivenum = receivenum;
    }

    @Basic
    @Column(name = "DETAILUNIT")
    public String getDetailunit() {
        return detailunit;
    }

    public void setDetailunit(String detailunit) {
        this.detailunit = detailunit;
    }

    @Basic
    @Column(name = "CURRENCYUNIT")
    public String getCurrencyunit() {
        return currencyunit;
    }

    public void setCurrencyunit(String currencyunit) {
        this.currencyunit = currencyunit;
    }

    @Basic
    @Column(name = "STOREID")
    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    @Basic
    @Column(name = "PROVIDERDEPID")
    public Long getProviderdepid() {
        return providerdepid;
    }

    public void setProviderdepid(Long providerdepid) {
        this.providerdepid = providerdepid;
    }

    @Basic
    @Column(name = "STORELOCATIONCODE")
    public String getStorelocationcode() {
        return storelocationcode;
    }

    public void setStorelocationcode(String storelocationcode) {
        this.storelocationcode = storelocationcode;
    }

    @Basic
    @Column(name = "STORELOCATIONNAME")
    public String getStorelocationname() {
        return storelocationname;
    }

    public void setStorelocationname(String storelocationname) {
        this.storelocationname = storelocationname;
    }

    @Basic
    @Column(name = "STORELOCATIONID")
    public Long getStorelocationid() {
        return storelocationid;
    }

    public void setStorelocationid(Long storelocationid) {
        this.storelocationid = storelocationid;
    }

    @Basic
    @Column(name = "STATUS")
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Basic
    @Column(name = "MEMO")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "CREATOR")
    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "CREATEDATE")
    public Time getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Time createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "EXPIRATIONTIME")
    public Time getExpirationtime() {
        return expirationtime;
    }

    public void setExpirationtime(Time expirationtime) {
        this.expirationtime = expirationtime;
    }

    @Basic
    @Column(name = "ZTID")
    public Long getZtid() {
        return ztid;
    }

    public void setZtid(Long ztid) {
        this.ztid = ztid;
    }

    @Basic
    @Column(name = "DETAILUNITNAME")
    public String getDetailunitname() {
        return detailunitname;
    }

    public void setDetailunitname(String detailunitname) {
        this.detailunitname = detailunitname;
    }

    @Basic
    @Column(name = "HOUSENAME")
    public String getHousename() {
        return housename;
    }

    public void setHousename(String housename) {
        this.housename = housename;
    }

    @Basic
    @Column(name = "HOUSECODE")
    public String getHousecode() {
        return housecode;
    }

    public void setHousecode(String housecode) {
        this.housecode = housecode;
    }

    @Basic
    @Column(name = "PERSONNAME")
    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    @Basic
    @Column(name = "FLNAME")
    public String getFlname() {
        return flname;
    }

    public void setFlname(String flname) {
        this.flname = flname;
    }

    @Basic
    @Column(name = "GYSNAME")
    public String getGysname() {
        return gysname;
    }

    public void setGysname(String gysname) {
        this.gysname = gysname;
    }

    @Basic
    @Column(name = "JHNAME")
    public String getJhname() {
        return jhname;
    }

    public void setJhname(String jhname) {
        this.jhname = jhname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VKcsubdetailEntity that = (VKcsubdetailEntity) o;

        if (id != that.id) return false;
        if (guid != null ? !guid.equals(that.guid) : that.guid != null) return false;
        if (tagcode != null ? !tagcode.equals(that.tagcode) : that.tagcode != null) return false;
        if (sheetid != null ? !sheetid.equals(that.sheetid) : that.sheetid != null) return false;
        if (sheetdetailid != null ? !sheetdetailid.equals(that.sheetdetailid) : that.sheetdetailid != null)
            return false;
        if (materialid != null ? !materialid.equals(that.materialid) : that.materialid != null) return false;
        if (categoryid != null ? !categoryid.equals(that.categoryid) : that.categoryid != null) return false;
        if (materialcode != null ? !materialcode.equals(that.materialcode) : that.materialcode != null) return false;
        if (materialname != null ? !materialname.equals(that.materialname) : that.materialname != null) return false;
        if (materialbrand != null ? !materialbrand.equals(that.materialbrand) : that.materialbrand != null)
            return false;
        if (materialmodel != null ? !materialmodel.equals(that.materialmodel) : that.materialmodel != null)
            return false;
        if (materialspecification != null ? !materialspecification.equals(that.materialspecification) : that.materialspecification != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (storecount != null ? !storecount.equals(that.storecount) : that.storecount != null) return false;
        if (notaxprice != null ? !notaxprice.equals(that.notaxprice) : that.notaxprice != null) return false;
        if (taxprice != null ? !taxprice.equals(that.taxprice) : that.taxprice != null) return false;
        if (taxrate != null ? !taxrate.equals(that.taxrate) : that.taxrate != null) return false;
        if (ordernum != null ? !ordernum.equals(that.ordernum) : that.ordernum != null) return false;
        if (receivenum != null ? !receivenum.equals(that.receivenum) : that.receivenum != null) return false;
        if (detailunit != null ? !detailunit.equals(that.detailunit) : that.detailunit != null) return false;
        if (currencyunit != null ? !currencyunit.equals(that.currencyunit) : that.currencyunit != null) return false;
        if (storeid != null ? !storeid.equals(that.storeid) : that.storeid != null) return false;
        if (providerdepid != null ? !providerdepid.equals(that.providerdepid) : that.providerdepid != null)
            return false;
        if (storelocationcode != null ? !storelocationcode.equals(that.storelocationcode) : that.storelocationcode != null)
            return false;
        if (storelocationname != null ? !storelocationname.equals(that.storelocationname) : that.storelocationname != null)
            return false;
        if (storelocationid != null ? !storelocationid.equals(that.storelocationid) : that.storelocationid != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
        if (expirationtime != null ? !expirationtime.equals(that.expirationtime) : that.expirationtime != null)
            return false;
        if (ztid != null ? !ztid.equals(that.ztid) : that.ztid != null) return false;
        if (detailunitname != null ? !detailunitname.equals(that.detailunitname) : that.detailunitname != null)
            return false;
        if (housename != null ? !housename.equals(that.housename) : that.housename != null) return false;
        if (housecode != null ? !housecode.equals(that.housecode) : that.housecode != null) return false;
        if (personname != null ? !personname.equals(that.personname) : that.personname != null) return false;
        if (flname != null ? !flname.equals(that.flname) : that.flname != null) return false;
        if (gysname != null ? !gysname.equals(that.gysname) : that.gysname != null) return false;
        if (jhname != null ? !jhname.equals(that.jhname) : that.jhname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (guid != null ? guid.hashCode() : 0);
        result = 31 * result + (tagcode != null ? tagcode.hashCode() : 0);
        result = 31 * result + (sheetid != null ? sheetid.hashCode() : 0);
        result = 31 * result + (sheetdetailid != null ? sheetdetailid.hashCode() : 0);
        result = 31 * result + (materialid != null ? materialid.hashCode() : 0);
        result = 31 * result + (categoryid != null ? categoryid.hashCode() : 0);
        result = 31 * result + (materialcode != null ? materialcode.hashCode() : 0);
        result = 31 * result + (materialname != null ? materialname.hashCode() : 0);
        result = 31 * result + (materialbrand != null ? materialbrand.hashCode() : 0);
        result = 31 * result + (materialmodel != null ? materialmodel.hashCode() : 0);
        result = 31 * result + (materialspecification != null ? materialspecification.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (storecount != null ? storecount.hashCode() : 0);
        result = 31 * result + (notaxprice != null ? notaxprice.hashCode() : 0);
        result = 31 * result + (taxprice != null ? taxprice.hashCode() : 0);
        result = 31 * result + (taxrate != null ? taxrate.hashCode() : 0);
        result = 31 * result + (ordernum != null ? ordernum.hashCode() : 0);
        result = 31 * result + (receivenum != null ? receivenum.hashCode() : 0);
        result = 31 * result + (detailunit != null ? detailunit.hashCode() : 0);
        result = 31 * result + (currencyunit != null ? currencyunit.hashCode() : 0);
        result = 31 * result + (storeid != null ? storeid.hashCode() : 0);
        result = 31 * result + (providerdepid != null ? providerdepid.hashCode() : 0);
        result = 31 * result + (storelocationcode != null ? storelocationcode.hashCode() : 0);
        result = 31 * result + (storelocationname != null ? storelocationname.hashCode() : 0);
        result = 31 * result + (storelocationid != null ? storelocationid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + (expirationtime != null ? expirationtime.hashCode() : 0);
        result = 31 * result + (ztid != null ? ztid.hashCode() : 0);
        result = 31 * result + (detailunitname != null ? detailunitname.hashCode() : 0);
        result = 31 * result + (housename != null ? housename.hashCode() : 0);
        result = 31 * result + (housecode != null ? housecode.hashCode() : 0);
        result = 31 * result + (personname != null ? personname.hashCode() : 0);
        result = 31 * result + (flname != null ? flname.hashCode() : 0);
        result = 31 * result + (gysname != null ? gysname.hashCode() : 0);
        result = 31 * result + (jhname != null ? jhname.hashCode() : 0);
        return result;
    }
}
