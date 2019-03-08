package com.zthc.ewms.sheet.entity.ykyw;

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
@Table(name="v_ywdetails")
public class YkywDetail {

  	/**----����---**/
  	@Id
    @Column(name="id", columnDefinition="Integer NULL COMMENT '����'")
    private Integer id;
  	/**----Ψһ��ʶ---**/
    @Column(name="guid")
    private String guid;
  	/**----����ID---**/
    @Column(name="sheetId", columnDefinition="Integer NULL COMMENT '����ID'")
    private Integer sheetId;
  	/**----������ϸID---**/
    @Column(name="sheetDetailId", columnDefinition="Integer NULL COMMENT '������ϸID'")
    private Integer sheetDetailId;
  	/**----   categoryId---**/
    @Column(name="categoryId", columnDefinition="Integer NULL COMMENT '   categoryId'")
    private Integer categoryId;
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
	/**----��ϸ����---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
	private Double detailCount;

	@Column(name="detailUnit", columnDefinition="Integer NULL COMMENT '״̬'")
	private Integer detailUnit;
	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;
	/**----��λ����---**/
	@Column(name="storeLocationCode", columnDefinition="nvarchar(255) NULL COMMENT '��λ����'")
	private String storeLocationCode;
	/**----��λID---**/
	@Column(name="storeLocationId", columnDefinition="Integer NULL COMMENT '��λID'")
	private Integer storeLocationId;
	/**----��λ����---**/
	@Column(name="storeLocationName", columnDefinition="nvarchar(50) NULL COMMENT '��λ����'")
	private String storeLocationName;



  	/**----��Ӧ��Id---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '��Ӧ��Id'")
    private Integer providerDepId;
	/**----��ע---**/
	@Column(name="memo", columnDefinition="nvarchar(255) NULL COMMENT '��ע'")
	private String memo;

	/**----������---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '������'")
	private Integer creator;
	/**----����ʱ��---**/
	@Column(name="createDate", columnDefinition="Date NULL COMMENT '����ʱ��'")
	private Date createDate;

    /**
     * ----ZtId---
     **/
    @Column(name = "ZtId", columnDefinition = "Integer NULL COMMENT 'ZtId'")
    private Integer ztId;
    /**
     * ----����˰����---
     **/
    @Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '����˰����'")
	private Double noTaxPrice;
	/**----˰��---**/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double taxRate;
    /**----����˰�ܶ�---**/
    @Column(name="noTaxSum", columnDefinition="number(18,9) NULL COMMENT '����˰�ܶ�'")
	private Double noTaxSum;

	/**----����---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '����'")
	private String description;


	/**----expirationTime---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="expirationTime", columnDefinition="date NULL COMMENT 'expirationTime'")
	private Date expirationTime;
	/**----����---**/
	@Column(name="tagCode", columnDefinition="nvarchar(50) NULL COMMENT '����'")
	private String tagCode;
	/**----��˰����---**/
	@Column(name="taxPrice", columnDefinition="number(18,9) NULL COMMENT '��˰����'")
	private Double taxPrice;
	/**----��˰�ܶ�---**/
	@Column(name="taxSum", columnDefinition="number(18,9) NULL COMMENT '��˰�ܶ�'")
	private Double taxSum;

	@Column(name="extendInt4", columnDefinition="Integer NULL ")
	private Integer extendInt4;  	/**----extendInt1---**/
	@Column(name="extendInt5", columnDefinition="Integer NULL ")
	private Integer extendInt5;  	/**----extendInt1---**/

	@Column(name="extendstring5", columnDefinition="nvarchar(255) NULL ")
    private String extendString5;
    @Column(name = "extendstring6", columnDefinition = "nvarchar(255) NULL ")
    private String extendString6;


	/**----��λ---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String detailUnitName;



	@Column(name="newhousename", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String newHouseName;
	@Column(name="newhouseCode", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String newHouseCode;


	@Column(name="oldhousename", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String oldHouseName;
	@Column(name="oldhouseCode", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String oldHouseCode;

	@Column(name="personname", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String personName;

	@Column(name="providerDepname", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
	private String providerDepName;


	public String getNewHouseCode() {
		return newHouseCode;
	}

	public void setNewHouseCode(String newHouseCode) {
		this.newHouseCode = newHouseCode;
	}

	public String getOldHouseCode() {
		return oldHouseCode;
	}

	public void setOldHouseCode(String oldHouseCode) {
		this.oldHouseCode = oldHouseCode;
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

	public Double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Double detailCount) {
		this.detailCount = detailCount;
	}

	public Integer getDetailUnit() {
		return detailUnit;
	}

	public void setDetailUnit(Integer detailUnit) {
		this.detailUnit = detailUnit;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreLocationCode() {
		return storeLocationCode;
	}

	public void setStoreLocationCode(String storeLocationCode) {
		this.storeLocationCode = storeLocationCode;
	}

	public Integer getStoreLocationId() {
		return storeLocationId;
	}

	public void setStoreLocationId(Integer storeLocationId) {
		this.storeLocationId = storeLocationId;
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

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ZtId) {
        this.ztId = ZtId;
    }

	public Double getNoTaxPrice() {
		return noTaxPrice;
	}

	public void setNoTaxPrice(Double noTaxPrice) {
		this.noTaxPrice = noTaxPrice;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

	public Double getNoTaxSum() {
		return noTaxSum;
	}

	public void setNoTaxSum(Double noTaxSum) {
		this.noTaxSum = noTaxSum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	public Double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Double getTaxSum() {
		return taxSum;
	}

	public void setTaxSum(Double taxSum) {
		this.taxSum = taxSum;
	}

	public Integer getExtendInt4() {
		return extendInt4;
	}

	public void setExtendInt4(Integer extendInt4) {
		this.extendInt4 = extendInt4;
	}

	public Integer getExtendInt5() {
		return extendInt5;
	}

	public void setExtendInt5(Integer extendInt5) {
		this.extendInt5 = extendInt5;
    }

    public String getExtendString5() {
        return extendString5;
    }

    public void setExtendString5(String extendString5) {
        this.extendString5 = extendString5;
    }

    public String getExtendString6() {
        return extendString6;
    }

    public void setExtendString6(String extendString6) {
        this.extendString6 = extendString6;
    }

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
	}

	public String getNewHouseName() {
		return newHouseName;
	}

	public void setNewHouseName(String newHouseName) {
		this.newHouseName = newHouseName;
	}

	public String getOldHouseName() {
		return oldHouseName;
	}

	public void setOldHouseName(String oldHouseName) {
		this.oldHouseName = oldHouseName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getProviderDepName() {
		return providerDepName;
	}

	public void setProviderDepName(String providerDepName) {
		this.providerDepName = providerDepName;
	}

	public YkywDetail() {
	
  	}
  

} 