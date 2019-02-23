package com.zthc.ewms.system.GJ.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "V_SXX")
public class VSxxEntity {
    private long id;
    private String guid;
    private String name;
    private String model;
    private String brand;
    private Long sparescateid;
    private Long price;
    private Long status;
    private String configmemo;
    private Long providerid;
    private String memo;
    private Long sort;
    private Long creator;
    private Time createdate;
    private Long updater;
    private Time updatedate;
    private Long ztid;
    private Long extendint1;
    private Long extendint2;
    private Long extendint3;
    private Long extendint4;
    private Long extendfloat1;
    private Long extendfloat2;
    private Long extendfloat3;
    private Long extendfloat4;
    private String extendstring1;
    private String extendstring2;
    private String extendstring3;
    private String extendstring4;
    private String extendstring5;
    private String code;
    private String specifications;
    private String models;
    private String unit;
    private Long stockup;
    private Long stockdown;
    private Long isusealarm;
    private String description;
    private Long con;

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
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "MODEL")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "BRAND")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "SPARESCATEID")
    public Long getSparescateid() {
        return sparescateid;
    }

    public void setSparescateid(Long sparescateid) {
        this.sparescateid = sparescateid;
    }

    @Basic
    @Column(name = "PRICE")
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
    @Column(name = "CONFIGMEMO")
    public String getConfigmemo() {
        return configmemo;
    }

    public void setConfigmemo(String configmemo) {
        this.configmemo = configmemo;
    }

    @Basic
    @Column(name = "PROVIDERID")
    public Long getProviderid() {
        return providerid;
    }

    public void setProviderid(Long providerid) {
        this.providerid = providerid;
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
    @Column(name = "SORT")
    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
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
    @Column(name = "UPDATER")
    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    @Basic
    @Column(name = "UPDATEDATE")
    public Time getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Time updatedate) {
        this.updatedate = updatedate;
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
    @Column(name = "EXTENDINT1")
    public Long getExtendint1() {
        return extendint1;
    }

    public void setExtendint1(Long extendint1) {
        this.extendint1 = extendint1;
    }

    @Basic
    @Column(name = "EXTENDINT2")
    public Long getExtendint2() {
        return extendint2;
    }

    public void setExtendint2(Long extendint2) {
        this.extendint2 = extendint2;
    }

    @Basic
    @Column(name = "EXTENDINT3")
    public Long getExtendint3() {
        return extendint3;
    }

    public void setExtendint3(Long extendint3) {
        this.extendint3 = extendint3;
    }

    @Basic
    @Column(name = "EXTENDINT4")
    public Long getExtendint4() {
        return extendint4;
    }

    public void setExtendint4(Long extendint4) {
        this.extendint4 = extendint4;
    }

    @Basic
    @Column(name = "EXTENDFLOAT1")
    public Long getExtendfloat1() {
        return extendfloat1;
    }

    public void setExtendfloat1(Long extendfloat1) {
        this.extendfloat1 = extendfloat1;
    }

    @Basic
    @Column(name = "EXTENDFLOAT2")
    public Long getExtendfloat2() {
        return extendfloat2;
    }

    public void setExtendfloat2(Long extendfloat2) {
        this.extendfloat2 = extendfloat2;
    }

    @Basic
    @Column(name = "EXTENDFLOAT3")
    public Long getExtendfloat3() {
        return extendfloat3;
    }

    public void setExtendfloat3(Long extendfloat3) {
        this.extendfloat3 = extendfloat3;
    }

    @Basic
    @Column(name = "EXTENDFLOAT4")
    public Long getExtendfloat4() {
        return extendfloat4;
    }

    public void setExtendfloat4(Long extendfloat4) {
        this.extendfloat4 = extendfloat4;
    }

    @Basic
    @Column(name = "EXTENDSTRING1")
    public String getExtendstring1() {
        return extendstring1;
    }

    public void setExtendstring1(String extendstring1) {
        this.extendstring1 = extendstring1;
    }

    @Basic
    @Column(name = "EXTENDSTRING2")
    public String getExtendstring2() {
        return extendstring2;
    }

    public void setExtendstring2(String extendstring2) {
        this.extendstring2 = extendstring2;
    }

    @Basic
    @Column(name = "EXTENDSTRING3")
    public String getExtendstring3() {
        return extendstring3;
    }

    public void setExtendstring3(String extendstring3) {
        this.extendstring3 = extendstring3;
    }

    @Basic
    @Column(name = "EXTENDSTRING4")
    public String getExtendstring4() {
        return extendstring4;
    }

    public void setExtendstring4(String extendstring4) {
        this.extendstring4 = extendstring4;
    }

    @Basic
    @Column(name = "EXTENDSTRING5")
    public String getExtendstring5() {
        return extendstring5;
    }

    public void setExtendstring5(String extendstring5) {
        this.extendstring5 = extendstring5;
    }

    @Basic
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "SPECIFICATIONS")
    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    @Basic
    @Column(name = "MODELS")
    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    @Basic
    @Column(name = "UNIT")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "STOCKUP")
    public Long getStockup() {
        return stockup;
    }

    public void setStockup(Long stockup) {
        this.stockup = stockup;
    }

    @Basic
    @Column(name = "STOCKDOWN")
    public Long getStockdown() {
        return stockdown;
    }

    public void setStockdown(Long stockdown) {
        this.stockdown = stockdown;
    }

    @Basic
    @Column(name = "ISUSEALARM")
    public Long getIsusealarm() {
        return isusealarm;
    }

    public void setIsusealarm(Long isusealarm) {
        this.isusealarm = isusealarm;
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
    @Column(name = "CON")
    public Long getCon() {
        return con;
    }

    public void setCon(Long con) {
        this.con = con;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VSxxEntity that = (VSxxEntity) o;

        if (id != that.id) return false;
        if (guid != null ? !guid.equals(that.guid) : that.guid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (sparescateid != null ? !sparescateid.equals(that.sparescateid) : that.sparescateid != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (configmemo != null ? !configmemo.equals(that.configmemo) : that.configmemo != null) return false;
        if (providerid != null ? !providerid.equals(that.providerid) : that.providerid != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (sort != null ? !sort.equals(that.sort) : that.sort != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
        if (updater != null ? !updater.equals(that.updater) : that.updater != null) return false;
        if (updatedate != null ? !updatedate.equals(that.updatedate) : that.updatedate != null) return false;
        if (ztid != null ? !ztid.equals(that.ztid) : that.ztid != null) return false;
        if (extendint1 != null ? !extendint1.equals(that.extendint1) : that.extendint1 != null) return false;
        if (extendint2 != null ? !extendint2.equals(that.extendint2) : that.extendint2 != null) return false;
        if (extendint3 != null ? !extendint3.equals(that.extendint3) : that.extendint3 != null) return false;
        if (extendint4 != null ? !extendint4.equals(that.extendint4) : that.extendint4 != null) return false;
        if (extendfloat1 != null ? !extendfloat1.equals(that.extendfloat1) : that.extendfloat1 != null) return false;
        if (extendfloat2 != null ? !extendfloat2.equals(that.extendfloat2) : that.extendfloat2 != null) return false;
        if (extendfloat3 != null ? !extendfloat3.equals(that.extendfloat3) : that.extendfloat3 != null) return false;
        if (extendfloat4 != null ? !extendfloat4.equals(that.extendfloat4) : that.extendfloat4 != null) return false;
        if (extendstring1 != null ? !extendstring1.equals(that.extendstring1) : that.extendstring1 != null)
            return false;
        if (extendstring2 != null ? !extendstring2.equals(that.extendstring2) : that.extendstring2 != null)
            return false;
        if (extendstring3 != null ? !extendstring3.equals(that.extendstring3) : that.extendstring3 != null)
            return false;
        if (extendstring4 != null ? !extendstring4.equals(that.extendstring4) : that.extendstring4 != null)
            return false;
        if (extendstring5 != null ? !extendstring5.equals(that.extendstring5) : that.extendstring5 != null)
            return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (specifications != null ? !specifications.equals(that.specifications) : that.specifications != null)
            return false;
        if (models != null ? !models.equals(that.models) : that.models != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (stockup != null ? !stockup.equals(that.stockup) : that.stockup != null) return false;
        if (stockdown != null ? !stockdown.equals(that.stockdown) : that.stockdown != null) return false;
        if (isusealarm != null ? !isusealarm.equals(that.isusealarm) : that.isusealarm != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (con != null ? !con.equals(that.con) : that.con != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (guid != null ? guid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (sparescateid != null ? sparescateid.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (configmemo != null ? configmemo.hashCode() : 0);
        result = 31 * result + (providerid != null ? providerid.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + (updater != null ? updater.hashCode() : 0);
        result = 31 * result + (updatedate != null ? updatedate.hashCode() : 0);
        result = 31 * result + (ztid != null ? ztid.hashCode() : 0);
        result = 31 * result + (extendint1 != null ? extendint1.hashCode() : 0);
        result = 31 * result + (extendint2 != null ? extendint2.hashCode() : 0);
        result = 31 * result + (extendint3 != null ? extendint3.hashCode() : 0);
        result = 31 * result + (extendint4 != null ? extendint4.hashCode() : 0);
        result = 31 * result + (extendfloat1 != null ? extendfloat1.hashCode() : 0);
        result = 31 * result + (extendfloat2 != null ? extendfloat2.hashCode() : 0);
        result = 31 * result + (extendfloat3 != null ? extendfloat3.hashCode() : 0);
        result = 31 * result + (extendfloat4 != null ? extendfloat4.hashCode() : 0);
        result = 31 * result + (extendstring1 != null ? extendstring1.hashCode() : 0);
        result = 31 * result + (extendstring2 != null ? extendstring2.hashCode() : 0);
        result = 31 * result + (extendstring3 != null ? extendstring3.hashCode() : 0);
        result = 31 * result + (extendstring4 != null ? extendstring4.hashCode() : 0);
        result = 31 * result + (extendstring5 != null ? extendstring5.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (specifications != null ? specifications.hashCode() : 0);
        result = 31 * result + (models != null ? models.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (stockup != null ? stockup.hashCode() : 0);
        result = 31 * result + (stockdown != null ? stockdown.hashCode() : 0);
        result = 31 * result + (isusealarm != null ? isusealarm.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (con != null ? con.hashCode() : 0);
        return result;
    }
}
