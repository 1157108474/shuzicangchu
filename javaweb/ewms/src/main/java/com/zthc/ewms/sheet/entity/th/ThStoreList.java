package com.zthc.ewms.sheet.entity.th;

import javax.persistence.*;

/**  
 * @Title: �˻������ϸ
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="v_thStorelist")
public class ThStoreList {

  	/**----����---**/
  	@Id
    @Column(name="id", columnDefinition="Integer NULL COMMENT '����'")
    private Integer id;


	/**----��Ӧ��Id---**/
	@Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '��Ӧ��Id'")
	private Integer providerDepId;

	/**----���ϱ���---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '���ϱ���'")
	private String materialCode;

	/**----����---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '����'")
	private String description;

	/**----��λ---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String detailUnitName;


	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;

	/**----��λ����---**/
	@Column(name="storeLocationId", columnDefinition="nvarchar(255) NULL COMMENT '��λ����'")
	private Integer storeLocationId;

	/**----��λ����---**/
	@Column(name="storeLocationCode", columnDefinition="nvarchar(255) NULL COMMENT '��λ����'")
	private String storeLocationCode;





	@Column(name="ownerType")
	private Integer ownerType;

	@Column(name="ztId")
	private Integer ztId;
	@Column(name="storeCount")
	private Double storeCount;


	@Column(name="snCode")
	private String snCode;
	@Column(name="prividerName")
	private String providerName;

	@Column(name="wareHouseCode")
	private String wareHouseCode;

	@Column(name="unseCount")
	private Double unseCount;
	@Transient
	private Double detailCount;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProviderDepId() {
		return providerDepId;
	}

	public void setProviderDepId(Integer providerDepId) {
		this.providerDepId = providerDepId;
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getStoreLocationId() {
		return storeLocationId;
	}

	public void setStoreLocationId(Integer storeLocationId) {
		this.storeLocationId = storeLocationId;
	}

	public String getStoreLocationCode() {
		return storeLocationCode;
	}

	public void setStoreLocationCode(String storeLocationCode) {
		this.storeLocationCode = storeLocationCode;
	}

	public Integer getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}

	public Integer getZtId() {
		return ztId;
	}

	public void setZtId(Integer ztId) {
		this.ztId = ztId;
	}

	public Double getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Double storeCount) {
		this.storeCount = storeCount;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getWareHouseCode() {
		return wareHouseCode;
	}

	public void setWareHouseCode(String wareHouseCode) {
		this.wareHouseCode = wareHouseCode;
	}

	public Double getUnseCount() {
		return unseCount;
	}

	public void setUnseCount(Double unseCount) {
		this.unseCount = unseCount;
	}

	public Double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Double detailCount) {
		this.detailCount = detailCount;
	}

	public ThStoreList() {
	
  	}


}