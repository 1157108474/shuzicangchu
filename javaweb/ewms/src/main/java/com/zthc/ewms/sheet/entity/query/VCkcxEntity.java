package com.zthc.ewms.sheet.entity.query;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "V_CKCX")
public class VCkcxEntity {
    private long id;
    private String guid;
    private String tagcode;
    private Long sheetid;
    private Long sheetdetailid;
    private Long categoryid;
    private Long materialid;
    private String materialcode;
    private String materialname;
    private String materialbrand;
    private String materialmodel;
    private String materialspecification;
    private String description;
    private Long detailcount;
    private Long detailunit;
    private Long storeid;
    private Long storelocationid;
    private String storelocationname;
    private String storelocationcode;
    private String code;
    private Long useddepartid;
    private String usdedepname;
    private Long ztid;
    private String url;
    private String kindName;
    /**----创建时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="submittime", columnDefinition="date NULL COMMENT '创建时间'")
	private Date submittime;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    
   


	public Date getSubmittime() {
		return submittime;
	}





	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
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
    @Column(name = "CATEGORYID")
    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
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
    @Column(name = "DETAILCOUNT")
    public Long getDetailcount() {
        return detailcount;
    }

    public void setDetailcount(Long detailcount) {
        this.detailcount = detailcount;
    }

    @Basic
    @Column(name = "DETAILUNIT")
    public Long getDetailunit() {
        return detailunit;
    }

    public void setDetailunit(Long detailunit) {
        this.detailunit = detailunit;
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
    @Column(name = "STORELOCATIONID")
    public Long getStorelocationid() {
        return storelocationid;
    }

    public void setStorelocationid(Long storelocationid) {
        this.storelocationid = storelocationid;
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
    @Column(name = "STORELOCATIONCODE")
    public String getStorelocationcode() {
        return storelocationcode;
    }

    public void setStorelocationcode(String storelocationcode) {
        this.storelocationcode = storelocationcode;
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
    @Column(name = "USEDDEPARTID")
    public Long getUseddepartid() {
        return useddepartid;
    }

    public void setUseddepartid(Long useddepartid) {
        this.useddepartid = useddepartid;
    }

    @Basic
    @Column(name = "USDEDEPNAME")
    public String getUsdedepname() {
        return usdedepname;
    }

    public void setUsdedepname(String usdedepname) {
        this.usdedepname = usdedepname;
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
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Basic
    @Column(name = "KINDNAME")
    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VCkcxEntity that = (VCkcxEntity) o;

        if (id != that.id) return false;
        if (guid != null ? !guid.equals(that.guid) : that.guid != null) return false;
        if (tagcode != null ? !tagcode.equals(that.tagcode) : that.tagcode != null) return false;
        if (sheetid != null ? !sheetid.equals(that.sheetid) : that.sheetid != null) return false;
        if (sheetdetailid != null ? !sheetdetailid.equals(that.sheetdetailid) : that.sheetdetailid != null)
            return false;
        if (categoryid != null ? !categoryid.equals(that.categoryid) : that.categoryid != null) return false;
        if (materialid != null ? !materialid.equals(that.materialid) : that.materialid != null) return false;
        if (materialcode != null ? !materialcode.equals(that.materialcode) : that.materialcode != null) return false;
        if (materialname != null ? !materialname.equals(that.materialname) : that.materialname != null) return false;
        if (materialbrand != null ? !materialbrand.equals(that.materialbrand) : that.materialbrand != null)
            return false;
        if (materialmodel != null ? !materialmodel.equals(that.materialmodel) : that.materialmodel != null)
            return false;
        if (materialspecification != null ? !materialspecification.equals(that.materialspecification) : that.materialspecification != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (detailcount != null ? !detailcount.equals(that.detailcount) : that.detailcount != null) return false;
        if (detailunit != null ? !detailunit.equals(that.detailunit) : that.detailunit != null) return false;
        if (storeid != null ? !storeid.equals(that.storeid) : that.storeid != null) return false;
        if (storelocationid != null ? !storelocationid.equals(that.storelocationid) : that.storelocationid != null)
            return false;
        if (storelocationname != null ? !storelocationname.equals(that.storelocationname) : that.storelocationname != null)
            return false;
        if (storelocationcode != null ? !storelocationcode.equals(that.storelocationcode) : that.storelocationcode != null)
            return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (useddepartid != null ? !useddepartid.equals(that.useddepartid) : that.useddepartid != null) return false;
        if (usdedepname != null ? !usdedepname.equals(that.usdedepname) : that.usdedepname != null) return false;
        if (ztid != null ? !ztid.equals(that.ztid) : that.ztid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (guid != null ? guid.hashCode() : 0);
        result = 31 * result + (tagcode != null ? tagcode.hashCode() : 0);
        result = 31 * result + (sheetid != null ? sheetid.hashCode() : 0);
        result = 31 * result + (sheetdetailid != null ? sheetdetailid.hashCode() : 0);
        result = 31 * result + (categoryid != null ? categoryid.hashCode() : 0);
        result = 31 * result + (materialid != null ? materialid.hashCode() : 0);
        result = 31 * result + (materialcode != null ? materialcode.hashCode() : 0);
        result = 31 * result + (materialname != null ? materialname.hashCode() : 0);
        result = 31 * result + (materialbrand != null ? materialbrand.hashCode() : 0);
        result = 31 * result + (materialmodel != null ? materialmodel.hashCode() : 0);
        result = 31 * result + (materialspecification != null ? materialspecification.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (detailcount != null ? detailcount.hashCode() : 0);
        result = 31 * result + (detailunit != null ? detailunit.hashCode() : 0);
        result = 31 * result + (storeid != null ? storeid.hashCode() : 0);
        result = 31 * result + (storelocationid != null ? storelocationid.hashCode() : 0);
        result = 31 * result + (storelocationname != null ? storelocationname.hashCode() : 0);
        result = 31 * result + (storelocationcode != null ? storelocationcode.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (useddepartid != null ? useddepartid.hashCode() : 0);
        result = 31 * result + (usdedepname != null ? usdedepname.hashCode() : 0);
        result = 31 * result + (ztid != null ? ztid.hashCode() : 0);
        return result;
    }
}
