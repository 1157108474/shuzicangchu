package com.zthc.ewms.sheet.entity.ck;

import org.apache.fop.util.DOM2SAX;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;

/**  
 * @Title: 出库明细
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_CKLIST")
public class VCKList {
  	/**----id---**/
	@Id
    @Column(name="id", columnDefinition="Integer NULL COMMENT 'ID'")
    private Integer id;
	/**----物料编码---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '物料编码'")
	private String materialCode;
	/**----物料名称---**/
	@Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '物料名称'")
	private String materialName;
	/**----物料规格---**/
	@Column(name="MATERIALSPECIFICATION", columnDefinition="nvarchar(255) NULL COMMENT '物料规格'")
	private String materialspecification;
	/**----物料型号---**/
	@Column(name="MATERIALMODEL", columnDefinition="nvarchar(255) NULL COMMENT '物料型号'")
	private String  materialModel;


	/**----描述---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '描述'")
	private String description;
	/**----计量单位---**/
	@Column(name="UNITNAME", columnDefinition="nvarchar(64) NULL COMMENT '计量单位'")
	private String unitName;
	/**----计划部门-**/
	@Column(name="PLANNAME", columnDefinition="nvarchar(64) NULL COMMENT '计划部门'")
	private String planName;
	/**----入库时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="CREATEDATE", columnDefinition="date NULL COMMENT '创建时间'")
	private Date createDate;

	/**----库房--**/
	@Column(name="STORELOCATIONNAME", columnDefinition="nvarchar(255) NULL COMMENT '库房'")
	private String storelocationname;
	/**----库位Id---**/
	@Column(name="STORELOCATIONID", columnDefinition="Integer NULL COMMENT '库位Id'")
	private Integer storelocationid;
	/**----库位--**/
	@Column(name="STORELOCATIONAREANAME", columnDefinition="nvarchar(255) NULL COMMENT '库位'")
	private String storelocationareaname;
	/**----库位Code--**/
	@Column(name="STORELOCATIONCODE", columnDefinition="nvarchar(255) NULL COMMENT '库位Code'")
	private String storelocationcode;

	/**----可出库数量---**/
	@Column(name="UNSECOUNT", columnDefinition="Integer NULL COMMENT '可出库数量'")
	private Double unsecount;

	/**----供应商---**/
	@Column(name="PROVIDERNAME", columnDefinition="nvarchar(255) NULL COMMENT '供应商'")
	private String providername;

	/**----DETAILUNIT---**/
	@Column(name="DETAILUNIT", columnDefinition="nvarchar(20) NULL COMMENT 'DETAILUNIT'")
	private String detailunit;
	/**----单位名称---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '单位名称'")
	private String detailUnitName;

	/**----库存数量---**/
	@Column(name="STORECOUNT", columnDefinition="Integer NULL COMMENT '库存数量'")
	private Double storecount;


	/**----ztId---**/
	@Column(name="ztId", columnDefinition="Integer NULL COMMENT 'ZtId'")
	private Integer ztId;

	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;
	/**----领料单号---**/
	@Transient
	private String type;
	/**----ownerType---**/
	@Column(name="ownerType", columnDefinition="Integer NULL COMMENT 'ownerType'")
	private Integer ownerType;
	/**----CATEGORYID---**/
	@Column(name="CATEGORYID", columnDefinition="Integer NULL COMMENT 'CATEGORYID'")
	private Integer categoryid;
	/**----MATERIALID---**/
	@Column(name="MATERIALID", columnDefinition="Integer NULL COMMENT 'MATERIALID'")
	private Integer materialid;
	/**----MATERIALBRAND---**/
	@Column(name="MATERIALBRAND", columnDefinition="nvarchar(255) NULL COMMENT 'MATERIALBRAND'")
	private String materialbrand;
	/**----TAGCODE---**/
	@Column(name="TAGCODE", columnDefinition="nvarchar(50) NULL COMMENT 'TAGCODE'")
	private String tagcode;
	/**----PLANDEPARTID---**/
	@Column(name="PLANDEPARTID", columnDefinition="Integer NULL COMMENT 'PLANDEPARTID'")
	private Integer plandepartid;
	/**----PROVIDERDEPID---**/
	@Column(name="PROVIDERDEPID", columnDefinition="Integer NULL COMMENT 'PROVIDERDEPID'")
	private Integer providerdepid;

	/**----notaxprice---**/
	@Column(name="notaxprice", columnDefinition="double NULL COMMENT 'notaxprice'")
	private Double notaxprice;

	/**----taxprice---**/
	@Column(name="taxprice", columnDefinition="double NULL COMMENT 'taxprice'")
	private Double taxprice;

	/**----taxrate---**/
	@Column(name="taxrate", columnDefinition="double NULL COMMENT 'taxrate'")
	private Double taxrate;
	/**----ownerTypeName---**/
	@Column(name="OWNERTYPENAME", columnDefinition="nvarchar(128) NULL ")
	private String ownerTypeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialspecification() {
		return materialspecification;
	}

	public void setMaterialspecification(String materialspecification) {
		this.materialspecification = materialspecification;
	}

	public String getMaterialModel() {
		return materialModel;
	}

	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStorelocationname() {
		return storelocationname;
	}

	public void setStorelocationname(String storelocationname) {
		this.storelocationname = storelocationname;
	}

	public Integer getStorelocationid() {
		return storelocationid;
	}

	public void setStorelocationid(Integer storelocationid) {
		this.storelocationid = storelocationid;
	}

	public String getStorelocationareaname() {
		return storelocationareaname;
	}

	public void setStorelocationareaname(String storelocationareaname) {
		this.storelocationareaname = storelocationareaname;
	}

	public String getStorelocationcode() {
		return storelocationcode;
	}

	public void setStorelocationcode(String storelocationcode) {
		this.storelocationcode = storelocationcode;
	}

	public Double getUnsecount() {
		return unsecount;
	}

	public void setUnsecount(Double unsecount) {
		this.unsecount = unsecount;
	}

	public String getProvidername() {
		return providername;
	}

	public void setProvidername(String providername) {
		this.providername = providername;
	}

	public String getDetailunit() {
		return detailunit;
	}

	public void setDetailunit(String detailunit) {
		this.detailunit = detailunit;
	}

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
	}

	public Double getStorecount() {
		return storecount;
	}

	public void setStorecount(Double storecount) {
		this.storecount = storecount;
	}

	public Integer getZtId() {
		return ztId;
	}

	public void setZtId(Integer ztId) {
		this.ztId = ztId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
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

	public String getMaterialbrand() {
		return materialbrand;
	}

	public void setMaterialbrand(String materialbrand) {
		this.materialbrand = materialbrand;
	}

	public String getTagcode() {
		return tagcode;
	}

	public void setTagcode(String tagcode) {
		this.tagcode = tagcode;
	}

	public Integer getPlandepartid() {
		return plandepartid;
	}

	public void setPlandepartid(Integer plandepartid) {
		this.plandepartid = plandepartid;
	}

	public Integer getProviderdepid() {
		return providerdepid;
	}

	public void setProviderdepid(Integer providerdepid) {
		this.providerdepid = providerdepid;
	}

	public Double getNotaxprice() {
		return notaxprice;
	}

	public void setNotaxprice(Double notaxprice) {
		this.notaxprice = notaxprice;
	}

	public Double getTaxprice() {
		return taxprice;
	}

	public void setTaxprice(Double taxprice) {
		this.taxprice = taxprice;
	}

	public Double getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(Double taxrate) {
		this.taxrate = taxrate;
	}

	public String getOwnerTypeName() {
		return ownerTypeName;
	}

	public void setOwnerTypeName(String ownerTypeName) {
		this.ownerTypeName = ownerTypeName;
	}

	public VCKList() {
	}
}