package com.zthc.ewms.sheet.entity.db;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**  
 * @Title: 单据明细
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="v_DbdDetails")
public class DbDetail {

  	/**----主键---**/
  	@Id
    @Column(name="id", columnDefinition="Integer NULL COMMENT '主键'")
    private Integer id;
  	/**----唯一标识---**/
    @Column(name="guid")
    private String guid;

	@Column(name="code")
	private String code;
  	/**----单据ID---**/
    @Column(name="sheetId", columnDefinition="Integer NULL COMMENT '单据ID'")
    private Integer sheetId;
  	/**----单据明细ID---**/
    @Column(name="sheetDetailId", columnDefinition="Integer NULL COMMENT '单据明细ID'")
    private Integer sheetDetailId;
  	/**----   categoryId---**/
    @Column(name="categoryId", columnDefinition="Integer NULL COMMENT '   categoryId'")
    private Integer categoryId;
  	/**----物料ID---**/
    @Column(name="materialId", columnDefinition="Integer NULL COMMENT '物料ID'")
    private Integer materialId;
  	/**----物料编码---**/
    @Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '物料编码'")
    private String materialCode;
  	/**----物料名称---**/
    @Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '物料名称'")
    private String materialName;

	/**----明细数量---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '明细数量'")
	private Double detailCount;


	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;


  	/**----供应商Id---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '供应商Id'")
    private Integer providerDepId;
	@Column(name="status")
	private Integer status;

	/**----备注---**/
	@Column(name="memo", columnDefinition="nvarchar(255) NULL COMMENT '备注'")
	private String memo;

	/**----创建人---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人'")
	private Integer creator;
	/**----创建时间---**/
	@Column(name="createDate", columnDefinition="Date NULL COMMENT '创建时间'")
	private Date createDate;

	/**----ZTID---**/
	@Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
	private Integer ZTID;
	/**----描述---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '描述'")
	private String description;

	/**----单位---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '单位'")
	private String detailUnitName;


	@Column(name="extendInt3", columnDefinition="Integer NULL COMMENT 'ERPDetailId'")
	private Integer extendInt3;

	@Column(name="parCode")
	private String parCode;
	@Column(name="houseName")
	private String houseName;
	@Column(name="houseCode")
	private String houseCode;
	@Column(name="providerName")
	private String providerName;
	@Column(name="storeLocationCode")
	private String storeLocationCode;

	@Column(name="storeLocationId")
	private Integer storeLocationId;

/*已出库数量*/
	@Column(name="ytCount")
	private Double ytCount;
	/*可调数量*/
	@Column(name="isCount")
	private Double isCount;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSheetId() {
		return sheetId;
	}

	public void setSheetId(Integer sheetId) {
		this.sheetId = sheetId;
	}

	public Integer getSheetDetailId() {
		return sheetDetailId;
	}

	public void setSheetDetailId(Integer sheetDetailId) {
		this.sheetDetailId = sheetDetailId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
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

	public Double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Double detailCount) {
		this.detailCount = detailCount;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getProviderDepId() {
		return providerDepId;
	}

	public void setProviderDepId(Integer providerDepId) {
		this.providerDepId = providerDepId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getZTID() {
		return ZTID;
	}

	public void setZTID(Integer ZTID) {
		this.ZTID = ZTID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
	}


	public String getParCode() {
		return parCode;
	}

	public void setParCode(String parCode) {
		this.parCode = parCode;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getStoreLocationCode() {
		return storeLocationCode;
	}

	public void setStoreLocationCode(String storeLocationCode) {
		this.storeLocationCode = storeLocationCode;
	}

	public Double getYtCount() {
		return ytCount;
	}

	public void setYtCount(Double ytCount) {
		this.ytCount = ytCount;
	}

	public Double getIsCount() {
		return isCount;
	}

	public void setIsCount(Double isCount) {
		this.isCount = isCount;
	}



	public Integer getExtendInt3() {
		return extendInt3;
	}

	public void setExtendInt3(Integer extendInt3) {
		this.extendInt3 = extendInt3;
	}

	public DbDetail() {
	
  	}

	public Integer getStoreLocationId() {
		return storeLocationId;
	}

	public void setStoreLocationId(Integer storeLocationId) {
		this.storeLocationId = storeLocationId;
	}
}