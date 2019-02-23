package com.zthc.ewms.sheet.entity.pd;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="WZ_PDDETAIL")
public class WzpdDetail {

  	/**----主键---**/
  	@Id
	@SequenceGenerator(name="generator",sequenceName="wzpddetail_sequence")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
    private Integer id;
  	/**----唯一标识---**/
    @Column(name="guid")
    private String guid;
	/**----编码---**/
	@Column(name="tagCode")
	private String tagCode;
	/**----单据ID---**/
	@Column(name="sheetId")
	private Integer sheetId;
	/**----单据明细ID---**/
	@Column(name="sheetDetailId")
	private Integer sheetDetailId;
	@Column(name="categoryId")
	private int categoryId;
	@Column(name="materialId")
	private int materialId;
	@Column(name="materialCode")
	private String materialCode;
	@Column(name="materialName")
	private String materialName;
	@Column(name="materialBrand")
	private String materialBrand;
	@Column(name="materialModel")
	private String materialModel;
	@Column(name="materialSpecification")
	private String materialSpecification;
	@Column(name="description")
	private String description;
	@Column(name="detailUnit")
	private int detailUnit;
	@Column(name="currencyUnit")
	private String currencyUnit;
	@Column(name="storeId")
	private int storeId;
	@Column(name="storeLocationCode")
	private String storeLocationCode;
	@Column(name="storeLocationId")
	private int storeLocationId;
	@Column(name="storeLocationName")
	private String storeLocationName;//库位
	@Column(name="providerDepId")
	private int providerDepId;
	@Column(name="planDepartID")
	private int planDepartID;
	@Column(name="notaxPrice")
	private double notaxPrice;
	@Column(name="taxRate")
	private double taxRate;
	@Column(name="noTaxSum")
	private double noTaxSum;
	@Column(name="taxPrice")
	private double taxPrice;
	@Column(name="taxSum")
	private double taxSum;
	@Column(name="status")
	private int status;
	@Column(name="memo")
	private String memo;
	@Column(name="creator")
	private int creator;
	@Column(name="createDate")
	private Date createDate;
	@Column(name="UPDATOR")
	private int updator;
	@Column(name="updateDate")
	private Date updateDate;
	@Column(name="expirationTime")
	private Date expirationTime;
	@Column(name="stockResult")
	private int stockResult;//盘点结果
	@Column(name="stockStatus")
	private int stockStatus;//盘点状态
	@Column(name="stockUnnormalInfo")
	private String stockUnnormalInfo;
	@Column(name="ztId")
	private int ztId;
	@Column(name="extendInt1")
	private int extendInt1;
	@Column(name="extendInt2")
	private int extendInt2;
	@Column(name="extendInt3")
	private int extendInt3;
	@Column(name="extendInt4")
	private int extendInt4;
	@Column(name="extendInt5")
	private int extendInt5;
	@Column(name="extendInt6")
	private int extendInt6;
	@Column(name="extendInt7")
	private int extendInt7;
	@Column(name="extendInt8")
	private int extendInt8;
	@Column(name="extendFloat1")
	private double extendFloat1;
	@Column(name="extendFloat2")
	private double extendFloat2;
	@Column(name="extendFloat3")
	private double extendFloat3;
	@Column(name="extendFloat4")
	private double extendFloat4;
	@Column(name="extendFloat5")
	private double extendFloat5;
	@Column(name="extendFloat6")
	private double extendFloat6;
	@Column(name="extendFloat7")
	private double extendFloat7;
	@Column(name="extendFloat8")
	private double extendFloat8;
	@Column(name="extendString1")
	private String extendString1;//盘点人姓名
	@Column(name="extendString2")
	private String extendString2;
	@Column(name="extendString3")
	private String extendString3;
	@Column(name="extendString4")
	private String extendString4;
	@Column(name="extendString5")
	private String extendString5;
	@Column(name="extendString6")
	private String extendString6;
	@Column(name="extendString7")
	private String extendString7;
	@Column(name="extendString8")
	private String extendString8;
	@Column(name="extendString9")
	private String extendString9;
	@Column(name="extendString10")
	private String extendString10;
	@Column(name="extendDate1")
	private Date extendDate1;
	@Column(name="extendDate2")
	private Date extendDate2;
	@Column(name="extendDate3")
	private Date extendDate3;
	@Column(name="extendDate4")
	private Date extendDate4;
	@Column(name="stockDate")
	private Date stockDate;//盘点日期
	@Column(name="stockMan")
	private int stockMan;//盘点人ID
	@Column(name="detailCount")
	private double detailCount;
	@Column(name="sysCount")
	private double sysCount;//库存数量
	@Column(name="detailUnitName")
	private String detailUnitName;
	@Column(name="isEquipment")
	private int isEquipment;
	@Column(name="enableSN")
	private int enableSN;
	@Column(name="snCode")
	private String snCode;
	@Column(name="ownerType")
	private int ownerType;
	@Transient
	private int rownum;

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

	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDetailUnit() {
		return detailUnit;
	}

	public void setDetailUnit(int detailUnit) {
		this.detailUnit = detailUnit;
	}

	public String getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreLocationCode() {
		return storeLocationCode;
	}

	public void setStoreLocationCode(String storeLocationCode) {
		this.storeLocationCode = storeLocationCode;
	}

	public int getStoreLocationId() {
		return storeLocationId;
	}

	public void setStoreLocationId(int storeLocationId) {
		this.storeLocationId = storeLocationId;
	}

	public String getStoreLocationName() {
		return storeLocationName;
	}

	public void setStoreLocationName(String storeLocationName) {
		this.storeLocationName = storeLocationName;
	}

	public int getProviderDepId() {
		return providerDepId;
	}

	public void setProviderDepId(int providerDepId) {
		this.providerDepId = providerDepId;
	}

	public int getPlanDepartID() {
		return planDepartID;
	}

	public void setPlanDepartID(int planDepartID) {
		this.planDepartID = planDepartID;
	}

	public double getNotaxPrice() {
		return notaxPrice;
	}

	public void setNotaxPrice(double notaxPrice) {
		this.notaxPrice = notaxPrice;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public double getNoTaxSum() {
		return noTaxSum;
	}

	public void setNoTaxSum(double noTaxSum) {
		this.noTaxSum = noTaxSum;
	}

	public double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public double getTaxSum() {
		return taxSum;
	}

	public void setTaxSum(double taxSum) {
		this.taxSum = taxSum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getUpdator() {
		return updator;
	}

	public void setUpdator(int updator) {
		this.updator = updator;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public int getStockResult() {
		return stockResult;
	}

	public void setStockResult(int stockResult) {
		this.stockResult = stockResult;
	}

	public int getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getStockUnnormalInfo() {
		return stockUnnormalInfo;
	}

	public void setStockUnnormalInfo(String stockUnnormalInfo) {
		this.stockUnnormalInfo = stockUnnormalInfo;
	}

	public int getZtId() {
		return ztId;
	}

	public void setZtId(int ztId) {
		this.ztId = ztId;
	}

	public int getExtendInt1() {
		return extendInt1;
	}

	public void setExtendInt1(int extendInt1) {
		this.extendInt1 = extendInt1;
	}

	public int getExtendInt2() {
		return extendInt2;
	}

	public void setExtendInt2(int extendInt2) {
		this.extendInt2 = extendInt2;
	}

	public int getExtendInt3() {
		return extendInt3;
	}

	public void setExtendInt3(int extendInt3) {
		this.extendInt3 = extendInt3;
	}

	public int getExtendInt4() {
		return extendInt4;
	}

	public void setExtendInt4(int extendInt4) {
		this.extendInt4 = extendInt4;
	}

	public int getExtendInt5() {
		return extendInt5;
	}

	public void setExtendInt5(int extendInt5) {
		this.extendInt5 = extendInt5;
	}

	public int getExtendInt6() {
		return extendInt6;
	}

	public void setExtendInt6(int extendInt6) {
		this.extendInt6 = extendInt6;
	}

	public int getExtendInt7() {
		return extendInt7;
	}

	public void setExtendInt7(int extendInt7) {
		this.extendInt7 = extendInt7;
	}

	public int getExtendInt8() {
		return extendInt8;
	}

	public void setExtendInt8(int extendInt8) {
		this.extendInt8 = extendInt8;
	}

	public double getExtendFloat1() {
		return extendFloat1;
	}

	public void setExtendFloat1(double extendFloat1) {
		this.extendFloat1 = extendFloat1;
	}

	public double getExtendFloat2() {
		return extendFloat2;
	}

	public void setExtendFloat2(double extendFloat2) {
		this.extendFloat2 = extendFloat2;
	}

	public double getExtendFloat3() {
		return extendFloat3;
	}

	public void setExtendFloat3(double extendFloat3) {
		this.extendFloat3 = extendFloat3;
	}

	public double getExtendFloat4() {
		return extendFloat4;
	}

	public void setExtendFloat4(double extendFloat4) {
		this.extendFloat4 = extendFloat4;
	}

	public double getExtendFloat5() {
		return extendFloat5;
	}

	public void setExtendFloat5(double extendFloat5) {
		this.extendFloat5 = extendFloat5;
	}

	public double getExtendFloat6() {
		return extendFloat6;
	}

	public void setExtendFloat6(double extendFloat6) {
		this.extendFloat6 = extendFloat6;
	}

	public double getExtendFloat7() {
		return extendFloat7;
	}

	public void setExtendFloat7(double extendFloat7) {
		this.extendFloat7 = extendFloat7;
	}

	public double getExtendFloat8() {
		return extendFloat8;
	}

	public void setExtendFloat8(double extendFloat8) {
		this.extendFloat8 = extendFloat8;
	}

	public String getExtendString1() {
		return extendString1;
	}

	public void setExtendString1(String extendString1) {
		this.extendString1 = extendString1;
	}

	public String getExtendString2() {
		return extendString2;
	}

	public void setExtendString2(String extendString2) {
		this.extendString2 = extendString2;
	}

	public String getExtendString3() {
		return extendString3;
	}

	public void setExtendString3(String extendString3) {
		this.extendString3 = extendString3;
	}

	public String getExtendString4() {
		return extendString4;
	}

	public void setExtendString4(String extendString4) {
		this.extendString4 = extendString4;
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

	public String getExtendString7() {
		return extendString7;
	}

	public void setExtendString7(String extendString7) {
		this.extendString7 = extendString7;
	}

	public String getExtendString8() {
		return extendString8;
	}

	public void setExtendString8(String extendString8) {
		this.extendString8 = extendString8;
	}

	public String getExtendString9() {
		return extendString9;
	}

	public void setExtendString9(String extendString9) {
		this.extendString9 = extendString9;
	}

	public String getExtendString10() {
		return extendString10;
	}

	public void setExtendString10(String extendString10) {
		this.extendString10 = extendString10;
	}

	public Date getExtendDate1() {
		return extendDate1;
	}

	public void setExtendDate1(Date extendDate1) {
		this.extendDate1 = extendDate1;
	}

	public Date getExtendDate2() {
		return extendDate2;
	}

	public void setExtendDate2(Date extendDate2) {
		this.extendDate2 = extendDate2;
	}

	public Date getExtendDate3() {
		return extendDate3;
	}

	public void setExtendDate3(Date extendDate3) {
		this.extendDate3 = extendDate3;
	}

	public Date getExtendDate4() {
		return extendDate4;
	}

	public void setExtendDate4(Date extendDate4) {
		this.extendDate4 = extendDate4;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public int getStockMan() {
		return stockMan;
	}

	public void setStockMan(int stockMan) {
		this.stockMan = stockMan;
	}

	public double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(double detailCount) {
		this.detailCount = detailCount;
	}

	public double getSysCount() {
		return sysCount;
	}

	public void setSysCount(double sysCount) {
		this.sysCount = sysCount;
	}

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
	}

	public int getIsEquipment() {
		return isEquipment;
	}

	public void setIsEquipment(int isEquipment) {
		this.isEquipment = isEquipment;
	}

	public int getEnableSN() {
		return enableSN;
	}

	public void setEnableSN(int enableSN) {
		this.enableSN = enableSN;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public int getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(int ownerType) {
		this.ownerType = ownerType;
	}


	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public WzpdDetail() {
	}

	public WzpdDetail(String guid, String tagCode, Integer sheetId, Integer sheetDetailId, int categoryId, int materialId, String materialCode, String materialName, String materialBrand, String materialModel, String materialSpecification, String description, int detailUnit, String currencyUnit, int storeId, String storeLocationCode, int storeLocationId, String storeLocationName, int providerDepId, int planDepartID, double notaxPrice, double taxRate, double noTaxSum, double taxPrice, double taxSum, int status, String memo, int creator, Date createDate, int updator, Date updateDate, Date expirationTime, int stockResult, int stockStatus, String stockUnnormalInfo, int ztId, int extendInt1, int extendInt2, int extendInt3, int extendInt4, int extendInt5, int extendInt6, int extendInt7, int extendInt8, double extendFloat1, double extendFloat2, double extendFloat3, double extendFloat4, double extendFloat5, double extendFloat6, double extendFloat7, double extendFloat8, String extendString1, String extendString2, String extendString3, String extendString4, String extendString5, String extendString6, String extendString7, String extendString8, String extendString9, String extendString10, Date extendDate1, Date extendDate2, Date extendDate3, Date extendDate4, Date stockDate, int stockMan, double detailCount, double sysCount, String detailUnitName, int isEquipment, int enableSN, String snCode, int ownerType) {
		this.guid = guid;
		this.tagCode = tagCode;
		this.sheetId = sheetId;
		this.sheetDetailId = sheetDetailId;
		this.categoryId = categoryId;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialBrand = materialBrand;
		this.materialModel = materialModel;
		this.materialSpecification = materialSpecification;
		this.description = description;
		this.detailUnit = detailUnit;
		this.currencyUnit = currencyUnit;
		this.storeId = storeId;
		this.storeLocationCode = storeLocationCode;
		this.storeLocationId = storeLocationId;
		this.storeLocationName = storeLocationName;
		this.providerDepId = providerDepId;
		this.planDepartID = planDepartID;
		this.notaxPrice = notaxPrice;
		this.taxRate = taxRate;
		this.noTaxSum = noTaxSum;
		this.taxPrice = taxPrice;
		this.taxSum = taxSum;
		this.status = status;
		this.memo = memo;
		this.creator = creator;
		this.createDate = createDate;
		this.updator = updator;
		this.updateDate = updateDate;
		this.expirationTime = expirationTime;
		this.stockResult = stockResult;
		this.stockStatus = stockStatus;
		this.stockUnnormalInfo = stockUnnormalInfo;
		this.ztId = ztId;
		this.extendInt1 = extendInt1;
		this.extendInt2 = extendInt2;
		this.extendInt3 = extendInt3;
		this.extendInt4 = extendInt4;
		this.extendInt5 = extendInt5;
		this.extendInt6 = extendInt6;
		this.extendInt7 = extendInt7;
		this.extendInt8 = extendInt8;
		this.extendFloat1 = extendFloat1;
		this.extendFloat2 = extendFloat2;
		this.extendFloat3 = extendFloat3;
		this.extendFloat4 = extendFloat4;
		this.extendFloat5 = extendFloat5;
		this.extendFloat6 = extendFloat6;
		this.extendFloat7 = extendFloat7;
		this.extendFloat8 = extendFloat8;
		this.extendString1 = extendString1;
		this.extendString2 = extendString2;
		this.extendString3 = extendString3;
		this.extendString4 = extendString4;
		this.extendString5 = extendString5;
		this.extendString6 = extendString6;
		this.extendString7 = extendString7;
		this.extendString8 = extendString8;
		this.extendString9 = extendString9;
		this.extendString10 = extendString10;
		this.extendDate1 = extendDate1;
		this.extendDate2 = extendDate2;
		this.extendDate3 = extendDate3;
		this.extendDate4 = extendDate4;
		this.stockDate = stockDate;
		this.stockMan = stockMan;
		this.detailCount = detailCount;
		this.sysCount = sysCount;
		this.detailUnitName = detailUnitName;
		this.isEquipment = isEquipment;
		this.enableSN = enableSN;
		this.snCode = snCode;
		this.ownerType = ownerType;
	}
}