package com.zthc.ewms.sheet.entity.db;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**  
 * @Title: ������ϸ
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="v_Dblist")
public class DbList {

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

	@Column(name="detailUnit", columnDefinition="Integer NULL COMMENT '״̬'")
	private Integer detailUnit;
	/**----��Ӧ��Id---**/
	@Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '��Ӧ��Id'")
	private Integer providerDepId;

	@Column(name="purchaseType", columnDefinition="Integer NULL COMMENT '����'")
	private Integer purchaseType;

	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;

	/**----������---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '������'")
	private Integer creator;
	/**----����ʱ��---**/
	@Column(name="createDate", columnDefinition="Date NULL COMMENT '����ʱ��'")
	private Date createDate;

	/**----ZTID---**/
	@Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
	private Integer ZTID;


	/**----���Ϸ���---**/
	@Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '���Ϸ���'")
	private String materialSpecification;

	/**----����---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '����'")
	private String description;

		/**----����---**/
	@Column(name="tagCode", columnDefinition="nvarchar(50) NULL COMMENT '����'")
	private String tagCode;

	/**----��λID---**/
	@Column(name="storeLocationId", columnDefinition="Integer NULL COMMENT '��λID'")
	private Integer storeLocationId;
	/**----��λ����---**/
	@Column(name="storeLocationCode", columnDefinition="nvarchar(255) NULL COMMENT '��λ����'")
	private String storeLocationCode;


	/**----��λ����---**/
	@Column(name="storeLocationName", columnDefinition="nvarchar(50) NULL COMMENT '��λ����'")
	private String storeLocationName;



	/**----�������---**/
	@Column(name="storeCount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
	private Double storeCount;


	@Column(name="housename", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String houseName;

	@Column(name="unitName")
	private String unitName;

	/**----   categoryId---**/
	@Column(name="categoryId", columnDefinition="Integer NULL COMMENT '   categoryId'")
	private Integer categoryId;



	/**----�ƻ�����---**/
    @Column(name = "planDepartId", columnDefinition = "Integer NULL COMMENT '�ƻ�����'")
    private Integer planDepartId;


  	/**----����˰����---**/
    @Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '����˰����'")
    private Double noTaxPrice;
	/**----��˰����---**/
	@Column(name="taxPrice", columnDefinition="number(18,9) NULL COMMENT '��˰����'")
	private Double taxPrice;
  	/**----˰��---**/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double taxRate;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="expirationTime", columnDefinition="date NULL COMMENT 'expirationTime'")
	private Date expirationTime;


	@Column(name="planName")
	private String planName;

	@Column(name="unseCount")
	private Double unseCount;


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

	public Integer getDetailUnit() {
		return detailUnit;
	}

	public void setDetailUnit(Integer detailUnit) {
		this.detailUnit = detailUnit;
	}

	public Integer getProviderDepId() {
		return providerDepId;
	}

	public void setProviderDepId(Integer providerDepId) {
		this.providerDepId = providerDepId;
	}

	public Integer getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public String getMaterialSpecification() {
		return materialSpecification;
	}

	public void setMaterialSpecification(String materialSpecification) {
		this.materialSpecification = materialSpecification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
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

	public Double getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Double storeCount) {
		this.storeCount = storeCount;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

    public Integer getPlanDepartId() {
        return planDepartId;
    }

    public void setPlanDepartId(Integer planDepartId) {
        this.planDepartId = planDepartId;
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

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Double getUnseCount() {
		return unseCount;
	}

	public void setUnseCount(Double unseCount) {
		this.unseCount = unseCount;
	}

	public DbList() {
	
  	}


}