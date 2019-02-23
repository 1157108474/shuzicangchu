package com.zthc.ewms.system.GJ.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "V_BZQ")
public class VBzqEntity {
    private long id;
    private String materialcode;
    private String materialname;
    private String description;
    private String materialmodel;
    private Long storecount;
    private Time createdate;
    private Time expirationtime;
    private Long syday;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Column(name = "STORECOUNT")
    public Long getStorecount() {
        return storecount;
    }

    public void setStorecount(Long storecount) {
        this.storecount = storecount;
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
    @Column(name = "SYDAY")
    public Long getSyday() {
        return syday;
    }

    public void setSyday(Long syday) {
        this.syday = syday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VBzqEntity that = (VBzqEntity) o;

        if (materialcode != null ? !materialcode.equals(that.materialcode) : that.materialcode != null) return false;
        if (materialname != null ? !materialname.equals(that.materialname) : that.materialname != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (materialmodel != null ? !materialmodel.equals(that.materialmodel) : that.materialmodel != null)
            return false;
        if (storecount != null ? !storecount.equals(that.storecount) : that.storecount != null) return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
        if (expirationtime != null ? !expirationtime.equals(that.expirationtime) : that.expirationtime != null)
            return false;
        if (syday != null ? !syday.equals(that.syday) : that.syday != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = materialcode != null ? materialcode.hashCode() : 0;
        result = 31 * result + (materialname != null ? materialname.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (materialmodel != null ? materialmodel.hashCode() : 0);
        result = 31 * result + (storecount != null ? storecount.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + (expirationtime != null ? expirationtime.hashCode() : 0);
        result = 31 * result + (syday != null ? syday.hashCode() : 0);
        return result;
    }
}
