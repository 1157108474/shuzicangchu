package com.zthc.ewms.sheet.entity.db;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: 调拨明细
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="v_Dbdlist")
public class DbdList {

  	/**----主键---**/
  	@Id
    @Column(name="id", columnDefinition="Integer NULL COMMENT '主键'")
    private Integer id;

	/**----物料编码---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '物料编码'")
	private String materialCode;

	/**----描述---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '描述'")
	private String description;

	@Column(name="detailunitName")
	private String detailUnitName;

	/**----库存数量---**/
	@Column(name="storeCount")
	private Double storeCount;

	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;

	/**----物料ID---**/
	@Column(name="materialId", columnDefinition="Integer NULL COMMENT '物料ID'")
	private Integer materialId;

	/**----物料名称---**/
	@Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '物料名称'")
	private String materialName;


	/**----ZTID---**/
	@Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
	private Integer ZTID;

	@Column(name="enableSn", columnDefinition="Integer NULL COMMENT '   categoryId'")
	private Integer enableSn;

	/**----供应商Id---**/
	@Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '供应商Id'")
	private Integer providerDepId;

	@Column(name="ownerType")
	private String ownerType;

	@Column(name="wareHouseCode")
	private String wareHouseCode;

	@Column(name="providerName")
	private String providerName;

	@Column(name="housename", columnDefinition="nvarchar(20) NULL COMMENT '单位'")
	private String houseName;
	@Column(name="isCount")
	private Double isCount;

	@Transient
	private Double detailCount;

	@Transient
	private Integer newLocation;


    @Transient
    private Integer newStoreId;

	@Transient
	private Integer newLocationId ;
	@Transient
	private String newLocationCode;

    public Integer getNewStoreId() {
        return newStoreId;
    }

    public void setNewStoreId(Integer newStoreId) {
        this.newStoreId = newStoreId;
    }

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

	public Double getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Double storeCount) {
		this.storeCount = storeCount;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Integer getZTID() {
		return ZTID;
	}

	public void setZTID(Integer ZTID) {
		this.ZTID = ZTID;
	}

	public Integer getEnableSn() {
		return enableSn;
	}

	public void setEnableSn(Integer enableSn) {
		this.enableSn = enableSn;
	}

	public Integer getProviderDepId() {
		return providerDepId;
	}

	public void setProviderDepId(Integer providerDepId) {
		this.providerDepId = providerDepId;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getWareHouseCode() {
		return wareHouseCode;
	}

	public void setWareHouseCode(String wareHouseCode) {
		this.wareHouseCode = wareHouseCode;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public Double getIsCount() {
		return isCount;
	}

	public void setIsCount(Double isCount) {
		this.isCount = isCount;
	}

	public Double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Double detailCount) {
		this.detailCount = detailCount;
	}

	public Integer getNewLocation() {
		return newLocation;
	}

	public void setNewLocation(Integer newLocation) {
		this.newLocation = newLocation;
	}


	public Integer getNewLocationId() {
		return newLocationId;
	}

	public void setNewLocationId(Integer newLocationId) {
		this.newLocationId = newLocationId;
	}

	public String getNewLocationCode() {
		return newLocationCode;
	}

	public void setNewLocationCode(String newLocationCode) {
		this.newLocationCode = newLocationCode;
	}

	public DbdList() {
	
  	}


}