package com.zthc.ewms.sheet.entity.ykyw;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: �ƿ���λ��ϸ
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="v_ywlist")
public class YkywList {

  	/**----����---**/
  	@Id
    @Column(name="id", columnDefinition="Integer NULL COMMENT '����'")
    private Integer id;
  	/**----Ψһ��ʶ---**/
    @Column(name="guid", columnDefinition="nvarchar(64) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
  	/**----����ID---**/
    @Column(name="sheetId", columnDefinition="Integer NULL COMMENT '����ID'")
    private Integer sheetId;
  	/**----������ϸID---**/
    @Column(name="sheetDetailId", columnDefinition="Integer NULL COMMENT '������ϸID'")
    private Integer sheetDetailId;

	/**----����---**/
	@Column(name="tagCode", columnDefinition="nvarchar(50) NULL COMMENT '����'")
	private String tagCode;

	/**----����ID---**/
	@Column(name="materialId", columnDefinition="Integer NULL COMMENT '����ID'")
	private Integer materialId;
	/**----���ϱ���---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '���ϱ���'")
	private String materialCode;
	/**----��������---**/
	@Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '��������'")
	private String materialName;
	/**----����Ʒ��---**/
	@Column(name="materialBrand", columnDefinition="nvarchar(255) NULL COMMENT '����Ʒ��'")
	private String materialBrand;
	/**----����ģ��---**/
	@Column(name="materialModel", columnDefinition="nvarchar(255) NULL COMMENT '����ģ��'")
	private String materialModel;


	/**----���Ϸ���---**/
	@Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '���Ϸ���'")
	private String materialSpecification;

	/**----�������---**/
	@Column(name="storeCount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
	private Double storeCount;

	@Column(name="detailUnit", columnDefinition="Integer NULL COMMENT '״̬'")
	private Integer detailUnit;

	@Column(name="currencyUnit", columnDefinition="nvarchar(20) NULL COMMENT '��ע'")
	private String currencyUnit;


	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;




	/**----��λID---**/
	@Column(name="storeLocationId", columnDefinition="Integer NULL COMMENT '��λID'")
	private Integer storeLocationId;
	/**----��λ����---**/
	@Column(name="storeLocationCode", columnDefinition="nvarchar(255) NULL COMMENT '��λ����'")
	private String storeLocationCode;


	/**----��λ����---**/
	@Column(name="storeLocationName", columnDefinition="nvarchar(50) NULL COMMENT '��λ����'")
	private String storeLocationName;


  	/**----��Ӧ��Id---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '��Ӧ��Id'")
    private Integer providerDepId;

  	/**----״̬---**/
    @Column(name="status", columnDefinition="Integer NULL COMMENT '״̬'")
    private Integer status;
  	/**----��ע---**/
    @Column(name="memo", columnDefinition="nvarchar(255) NULL COMMENT '��ע'")
    private String memo;


    /**
     * ----ZtId---
     **/
    @Column(name = "ZtId", columnDefinition = "Integer NULL COMMENT 'ZtId'")
    private Integer ztId;


  	/**----����˰����---**/
    @Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '����˰����'")
    private Double noTaxPrice;
	/**----��˰����---**/
	@Column(name="taxPrice", columnDefinition="number(18,9) NULL COMMENT '��˰����'")
	private Double taxPrice;
  	/**----˰��---**/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double taxRate;


  	/**----����---**/
    @Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '����'")
    private String description;

	@Column(name="orderNum", columnDefinition="nvarchar(200) NULL COMMENT '���к�'")
	private String orderNum;

	@Column(name="receiveNum", columnDefinition="nvarchar(200) NULL COMMENT '���к�'")
	private String receiveNum;

	/**----�ƻ�����---**/
    @Column(name = "planDepartId", columnDefinition = "Integer NULL COMMENT '�ƻ�����'")
    private Integer planDepartId;



	/**----   categoryId---**/
	@Column(name="categoryId", columnDefinition="Integer NULL COMMENT '   categoryId'")
	private Integer categoryId;

	/**----����---**/
	@Column(name="detailPrice", columnDefinition="number(18,9) NULL COMMENT '��˰����'")
	private Double detailPrice;





	/**----��ϸ����---**/
	@Column(name="detailSum", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
	private Double detailSum;




	@Column(name="ownerDep", columnDefinition="Integer NULL COMMENT '����'")
	private Integer ownerDep;

	@Column(name="purchaseType", columnDefinition="Integer NULL COMMENT '����'")
	private Integer purchaseType;
	/**----������---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '������'")
	private Integer creator;
	/**----����ʱ��---**/
	@Column(name="createDate", columnDefinition="date NULL COMMENT '����ʱ��'")
	private Date createDate;

	/**----��λ---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String detailUnitName;


	@Column(name="housename", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String houseName;


	@Column(name="housecode", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String houseCode;

	/**----�Ƿ���������---**/
	@Column(name="isEquipment", columnDefinition="Integer NULL COMMENT '�Ƿ���������'")
	private Integer isEquipment;

	/**----�Ƿ���������---**/
	@Column(name="enableSn", columnDefinition="Integer NULL COMMENT '�Ƿ���������'")
	private Integer enableSn;
	/**----���к�---**/
	@Column(name="snCode", columnDefinition="nvarchar(50) NULL COMMENT '���к�'")
	private String snCode;

	@Column(name="ownerType", columnDefinition="Integer NULL COMMENT '�Ƿ���������'")
	private Integer ownerType;

	@Column(name="isCount", columnDefinition="number(18,9)")
	private Double isCount;
	@Column(name="providerDepName", columnDefinition="nvarchar(50) NULL COMMENT '���к�'")
	private String providerDepName;

    @Column(name = "expirationTime")
    private Date expirationTime;




	@Transient
	private String newLocation;

	@Transient
	private String newStore;
	@Transient
	private Integer newLocationId ;

	@Transient
	private Integer newStoreId;

	@Transient
	private String newLocationCode;

	@Transient
	private Double detailCount;

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

	public String getNewLocation() {
		return newLocation;
	}

	public void setNewLocation(String newLocation) {
		this.newLocation = newLocation;
	}

	public String getNewStore() {
		return newStore;
	}

	public void setNewStore(String newStore) {
		this.newStore = newStore;
	}

	public Integer getNewLocationId() {
		return newLocationId;
	}

	public void setNewLocationId(Integer newLocationId) {
		this.newLocationId = newLocationId;
	}

	public Integer getNewStoreId() {
		return newStoreId;
	}

	public void setNewStoreId(Integer newStoreId) {
		this.newStoreId = newStoreId;
	}

	public String getNewLocationCode() {
		return newLocationCode;
	}

	public void setNewLocationCode(String newLocationCode) {
		this.newLocationCode = newLocationCode;
	}

	public Double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Double detailCount) {
		this.detailCount = detailCount;
	}

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

	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
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

	public String getMaterialBrand() {
		return materialBrand;
	}

	public void setMaterialBrand(String materialBrand) {
		this.materialBrand = materialBrand;
	}

	public String getMaterialModel() {
		return materialModel;
	}

	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}

	public String getMaterialSpecification() {
		return materialSpecification;
	}

	public void setMaterialSpecification(String materialSpecification) {
		this.materialSpecification = materialSpecification;
	}

	public Double getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Double storeCount) {
		this.storeCount = storeCount;
	}

	public Integer getDetailUnit() {
		return detailUnit;
	}

	public void setDetailUnit(Integer detailUnit) {
		this.detailUnit = detailUnit;
	}

	public String getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
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

	public String getStoreLocationName() {
		return storeLocationName;
	}

	public void setStoreLocationName(String storeLocationName) {
		this.storeLocationName = storeLocationName;
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

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

	public Double getNoTaxPrice() {
		return noTaxPrice;
	}

	public void setNoTaxPrice(Double noTaxPrice) {
		this.noTaxPrice = noTaxPrice;
	}

	public Double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
    }

    public Integer getPlanDepartId() {
        return planDepartId;
    }

    public void setPlanDepartId(Integer planDepartId) {
        this.planDepartId = planDepartId;
    }

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Double getDetailPrice() {
		return detailPrice;
	}

	public void setDetailPrice(Double detailPrice) {
		this.detailPrice = detailPrice;
	}

	public Double getDetailSum() {
		return detailSum;
	}

	public void setDetailSum(Double detailSum) {
		this.detailSum = detailSum;
	}

	public Integer getOwnerDep() {
		return ownerDep;
	}

	public void setOwnerDep(Integer ownerDep) {
		this.ownerDep = ownerDep;
	}

	public Integer getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
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

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
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

	public Integer getIsEquipment() {
		return isEquipment;
	}

	public void setIsEquipment(Integer isEquipment) {
		this.isEquipment = isEquipment;
	}

	public Integer getEnableSn() {
		return enableSn;
	}

	public void setEnableSn(Integer enableSn) {
		this.enableSn = enableSn;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public Integer getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}

	public Double getIsCount() {
		return isCount;
	}

	public void setIsCount(Double isCount) {
		this.isCount = isCount;
	}

	public String getProviderDepName() {
		return providerDepName;
	}

	public void setProviderDepName(String providerDepName) {
		this.providerDepName = providerDepName;
	}

	public YkywList() {

    }

//	public YkywList(Integer id, Integer guid, Integer sheetId, Integer sheetDetailId, String tagCode, Integer
// materialId, String materialCode, String materialName, String materialBrand, String materialModel, String
// materialSpecification, Double storeCount, Integer detailUnit, String currencyUnit, Integer storeId, Integer
// storeLocationId, String storeLocationCode, String storeLocationName, Integer providerDepId, Integer status, String
// memo, Integer ZtId, Double noTaxPrice, Double taxPrice, Double taxRate, String description, String orderNum,
// String receiveNum, Integer planDepartId, Integer categoryId, Double detailPrice, Double detailSum, Integer
// ownerDep, Integer purchaseType, Integer creator, Date createDate, String detailUnitName, String houseName,
// String houseCode, Integer isEquipment, Integer enableSn, String snCode, Integer ownerType, Double isCount, String
// providerDepName) {
//		this.id = id;
//		this.guid = guid;
//		this.sheetId = sheetId;
//		this.sheetDetailId = sheetDetailId;
//		this.tagCode = tagCode;
//		this.materialId = materialId;
//		this.materialCode = materialCode;
//		this.materialName = materialName;
//		this.materialBrand = materialBrand;
//		this.materialModel = materialModel;
//		this.materialSpecification = materialSpecification;
//		this.storeCount = storeCount;
//		this.detailUnit = detailUnit;
//		this.currencyUnit = currencyUnit;
//		this.storeId = storeId;
//		this.storeLocationId = storeLocationId;
//		this.storeLocationCode = storeLocationCode;
//		this.storeLocationName = storeLocationName;
//		this.providerDepId = providerDepId;
//		this.status = status;
//		this.memo = memo;
//		this.ZtId = ZtId;
//		this.noTaxPrice = noTaxPrice;
//		this.taxPrice = taxPrice;
//		this.taxRate = taxRate;
//		this.description = description;
//		this.orderNum = orderNum;
//		this.receiveNum = receiveNum;
//		this.planDepartId = planDepartId;
//		this.categoryId = categoryId;
//		this.detailPrice = detailPrice;
//		this.detailSum = detailSum;
//		this.ownerDep = ownerDep;
//		this.purchaseType = purchaseType;
//		this.creator = creator;
//		this.createDate = createDate;
//		this.detailUnitName = detailUnitName;
//		this.houseName = houseName;
//		this.houseCode = houseCode;
//		this.isEquipment = isEquipment;
//		this.enableSn = enableSn;
//		this.snCode = snCode;
//		this.ownerType = ownerType;
//		this.isCount = isCount;
//		this.providerDepName = providerDepName;
//	}
}