package com.zthc.ewms.sheet.entity.query;

import javax.persistence.*;

@Entity
@Table(name = "V_KCDETAIL")
public class VKcdetailEntity {

    private Long id;
    private String materialcode;
    private String materialname;
    private String materialbrand;
    private String materialmodel;
    private String materialspecification;
    private Long storeid;
    private String description;
    private Long ztid;
    private Long ownertype;
    private String ownertypename;
    private Long providerdepid;
    private String providername;
    private String ztidname;
    private Double storecount;
    private String housename;
    private String flname;
    private String storelocationcode;
    private String detailunitname;
    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Column(name = "STOREID")
    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
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
    @Column(name = "ZTID")
    public Long getZtid() {
        return ztid;
    }

    public void setZtid(Long ztid) {
        this.ztid = ztid;
    }

    @Basic
    @Column(name = "OWNERTYPE")
    public Long getOwnertype() {
        return ownertype;
    }

    public void setOwnertype(Long ownertype) {
        this.ownertype = ownertype;
    }

    @Basic
    @Column(name = "OWNERTYPENAME")
    public String getOwnertypename() {
        return ownertypename;
    }

    public void setOwnertypename(String ownertypename) {
        this.ownertypename = ownertypename;
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
    @Column(name = "PROVIDERNAME")
    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    @Basic
    @Column(name = "ZTIDNAME")
    public String getZtidname() {
        return ztidname;
    }

    public void setZtidname(String ztidname) {
        this.ztidname = ztidname;
    }

    @Basic
    @Column(name = "STORECOUNT")
    public Double getStorecount() {
        return storecount;
    }

    public void setStorecount(Double storecount) {
        this.storecount = storecount;
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
    @Column(name = "FLNAME")
    public String getFlname() {
        return flname;
    }

    public void setFlname(String flname) {
        this.flname = flname;
    }
    @Basic
    @Column(name="STORELOCATIONCODE")
    public String getStorelocationcode() {
        return storelocationcode;
    }

    public void setStorelocationcode(String storelocationcode) {
        this.storelocationcode = storelocationcode;
    }
    @Basic
    @Column(name="STORELOCATIONNAME")
    public String getDetailunitname() {
        return detailunitname;
    }

    public void setDetailunitname(String detailunitname) {
        this.detailunitname = detailunitname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VKcdetailEntity that = (VKcdetailEntity) o;

        if (materialcode != null ? !materialcode.equals(that.materialcode) : that.materialcode != null) return false;
        if (materialname != null ? !materialname.equals(that.materialname) : that.materialname != null) return false;
        if (materialbrand != null ? !materialbrand.equals(that.materialbrand) : that.materialbrand != null)
            return false;
        if (materialmodel != null ? !materialmodel.equals(that.materialmodel) : that.materialmodel != null)
            return false;
        if (materialspecification != null ? !materialspecification.equals(that.materialspecification) : that.materialspecification != null)
            return false;
        if (storeid != null ? !storeid.equals(that.storeid) : that.storeid != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (ztid != null ? !ztid.equals(that.ztid) : that.ztid != null) return false;
        if (ownertype != null ? !ownertype.equals(that.ownertype) : that.ownertype != null) return false;
        if (ownertypename != null ? !ownertypename.equals(that.ownertypename) : that.ownertypename != null)
            return false;
        if (providerdepid != null ? !providerdepid.equals(that.providerdepid) : that.providerdepid != null)
            return false;
        if (providername != null ? !providername.equals(that.providername) : that.providername != null) return false;
        if (ztidname != null ? !ztidname.equals(that.ztidname) : that.ztidname != null) return false;
        if (storecount != null ? !storecount.equals(that.storecount) : that.storecount != null) return false;
        if (housename != null ? !housename.equals(that.housename) : that.housename != null) return false;
        if (flname != null ? !flname.equals(that.flname) : that.flname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = materialcode != null ? materialcode.hashCode() : 0;
        result = 31 * result + (materialname != null ? materialname.hashCode() : 0);
        result = 31 * result + (materialbrand != null ? materialbrand.hashCode() : 0);
        result = 31 * result + (materialmodel != null ? materialmodel.hashCode() : 0);
        result = 31 * result + (materialspecification != null ? materialspecification.hashCode() : 0);
        result = 31 * result + (storeid != null ? storeid.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ztid != null ? ztid.hashCode() : 0);
        result = 31 * result + (ownertype != null ? ownertype.hashCode() : 0);
        result = 31 * result + (ownertypename != null ? ownertypename.hashCode() : 0);
        result = 31 * result + (providerdepid != null ? providerdepid.hashCode() : 0);
        result = 31 * result + (providername != null ? providername.hashCode() : 0);
        result = 31 * result + (ztidname != null ? ztidname.hashCode() : 0);
        result = 31 * result + (storecount != null ? storecount.hashCode() : 0);
        result = 31 * result + (housename != null ? housename.hashCode() : 0);
        result = 31 * result + (flname != null ? flname.hashCode() : 0);
        return result;
    }
}
