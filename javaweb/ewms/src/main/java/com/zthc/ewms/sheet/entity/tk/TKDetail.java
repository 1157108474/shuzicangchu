package com.zthc.ewms.sheet.entity.tk;

import com.zthc.ewms.base.util.NumberUtils;
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
@Table(name="V_TKDETAILS")
public class TKDetail {
	/**----主键---**/
	@Id
	@Column(name="id", columnDefinition="Integer NULL COMMENT '主键'")
	private Integer id;
	/**----唯一标识---**/
	@Column(name="guid")
	private String guid;
//	@Column(name="houseName")
//	private String houseName;
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
	/**----物料品牌---**/
	@Column(name="materialBrand", columnDefinition="nvarchar(255) NULL COMMENT '物料品牌'")
	private String materialBrand;
	/**----物料模型---**/
	@Column(name="materialModel", columnDefinition="nvarchar(255) NULL COMMENT '物料模型'")
	private String materialModel;

	/**----明细数量---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '明细数量'")
	private Double detailCount;

	@Column(name="detailUnit", columnDefinition="Integer NULL COMMENT '状态'")
	private Integer detailUnit;
	@Column(name="currencyUnit", columnDefinition="Integer NULL COMMENT '状态'")
	private Integer currencyUnit;

	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;

	/**----供应商Id---**/
	@Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '供应商Id'")
	private Integer providerDepId;

	@Column(name="status", columnDefinition="Integer NULL COMMENT '供应商Id'")
	private Integer status;
	/**----备注---**/
	@Column(name="memo", columnDefinition="nvarchar(255) NULL COMMENT '备注'")
	private String memo;

	/**----创建人---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人'")
	private Integer creator;
	/**----创建时间---**/
	@Column(name="createDate", columnDefinition="Integer NULL COMMENT '创建时间'")
	private Date createDate;
	/**----更新人---**/
	@Column(name="updator", columnDefinition="Integer NULL COMMENT '更新人'")
	private Integer updater;
	/**----更新时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="updateDate", columnDefinition="date NULL COMMENT '更新时间'")
	private Date updateDate;

	/**----ZTID---**/
	@Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
	private Integer ztId;
	/**----不含税单价---**/
	@Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '不含税单价'")
	private Double noTaxPrice;
	/**----税率---**/
	@Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '税率'")
	private Double taxRate;
	/**
	 * ----不含税总额---
	 **/
	@Column(name="noTaxSum", columnDefinition="number(18,9) NULL COMMENT '不含税总额'")
	private Double noTaxSum;

	/**----物料分类---**/
	@Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '物料分类'")
	private String materialSpecification;

	/**----描述---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '描述'")
	private String description;

	/**----expirationTime---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="expirationTime", columnDefinition="date NULL COMMENT 'expirationTime'")
	private Date expirationTime;

	/**----库位编码---**/
	@Column(name="storeLocationCode", columnDefinition="nvarchar(255) NULL COMMENT '库位编码'")
	private String storeLocationCode;
	/**----编码---**/
	@Column(name="tagCode", columnDefinition="nvarchar(50) NULL COMMENT '编码'")
	private String tagCode;
	/**----含税单价---**/
	@Column(name="taxPrice", columnDefinition="number(18,9) NULL COMMENT '含税单价'")
	private Double taxPrice;
	/**----含税总额---**/
	@Column(name="taxSum", columnDefinition="number(18,9) NULL COMMENT '含税总额'")
	private Double taxSum;
	/**----库位ID---**/
	@Column(name="storeLocationId", columnDefinition="Integer NULL COMMENT '库位ID'")
	private Integer storeLocationId;

	@Column(name="storeLocationName")
	private String storeLocationName;

	/**----计划部门---**/
	@Column(name = "planDepartId", columnDefinition = "Integer NULL COMMENT '计划部门'")
	private Integer planDepartId;

	/**----单位---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '单位'")
	private String detailUnitName;
	/**----单位---**/
	@Column(name="unitName")
	private String unitName;
	@Column(name="houseName")
	private String houseName;

	@Column(name="providerName")
	private String providerName;

	@Column(name = "enableSn")
	private Integer enableSn;

	public Integer getEnableSn() {
		return enableSn;
	}

	public void setEnableSn(Integer enableSn) {
		this.enableSn = enableSn;
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

	public Integer getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(Integer currencyUnit) {
		this.currencyUnit = currencyUnit;
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

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public String getNoTaxPriceDouble() {
		return NumberUtils.getDouble(noTaxPrice);
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

	public String getNoTaxSumDouble() {
		return NumberUtils.getDouble(noTaxSum);
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

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getStoreLocationCode() {
		return storeLocationCode;
	}

	public void setStoreLocationCode(String storeLocationCode) {
		this.storeLocationCode = storeLocationCode;
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

	public Integer getPlanDepartId() {
		return planDepartId;
	}

	public void setPlanDepartId(Integer planDepartId) {
		this.planDepartId = planDepartId;
	}

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public TKDetail() {
	}
}