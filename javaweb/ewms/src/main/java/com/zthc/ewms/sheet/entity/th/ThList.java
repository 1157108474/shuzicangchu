package com.zthc.ewms.sheet.entity.th;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**  
 * @Title: 退货添加明细
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="v_thlist")
public class ThList {


	@Id
	@Column(name="realId", columnDefinition="Integer NULL COMMENT '主键'")
	private Integer realId;

	public Integer getRealId() {
		return realId;
	}

	public void setRealId(Integer realId) {
		this.realId = realId;
	}

	/**----主键---**/
//	@Id
    @Column(name="id", columnDefinition="Integer NULL COMMENT '主键'")
    private Integer id;
  	/**----唯一标识---**/
    @Column(name="guid", columnDefinition="nvarchar(64) NULL COMMENT '唯一标识'")
    private String guid;
  	/**----单据ID---**/
    @Column(name="sheetId", columnDefinition="Integer NULL COMMENT '单据ID'")
    private Integer sheetId;
  	/**----单据明细ID---**/
    @Column(name="sheetDetailId", columnDefinition="Integer NULL COMMENT '单据明细ID'")
    private Integer sheetDetailId;

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

	/**----物料分类---**/
	@Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '物料分类'")
	private String materialSpecification;

	@Column(name="detailUnit", columnDefinition="Integer NULL COMMENT '状态'")
	private Integer detailUnit;


  	/**----供应商Id---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '供应商Id'")
    private Integer providerDepId;

  	/**----状态---**/
    @Column(name="status", columnDefinition="Integer NULL COMMENT '状态'")
    private Integer status;
  	/**----备注---**/
    @Column(name="memo", columnDefinition="nvarchar(255) NULL COMMENT '备注'")
    private String memo;
	/**----创建人---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人'")
	private Integer creator;
	/**----创建时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createDate", columnDefinition="date NULL COMMENT '创建时间'")
	private Date createDate;

  	/**----ZTID---**/
    @Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
    private Integer ZTID;

  	/**----不含税单价---**/
    @Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '不含税单价'")
    private Double noTaxPrice;

  	/**----税率---**/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '税率'")
    private Double taxRate;
    @Column(name = "notaxsum", columnDefinition = "number(18,9) NULL COMMENT '税率'")
    private Double noTaxSum;

  	/**----描述---**/
    @Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '描述'")
    private String description;
	/**----expirationTime---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="expirationTime", columnDefinition="date NULL COMMENT 'expirationTime'")
	private Date expirationTime;
	@Column(name="taxprice", columnDefinition="number(18,9) NULL COMMENT '税率'")
	private Double taxPrice;

	@Column(name="taxsum")
	private Double taxSum;

	/**----计划部门---**/
    @Column(name = "planDepartId", columnDefinition = "Integer NULL COMMENT '计划部门'")
    private Integer planDepartId;

	/**----明细数量---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '明细数量'")
	private Double detailCount;


	/**----是否启用序列---**/
	@Column(name="isEquipment", columnDefinition="Integer NULL COMMENT '是否启用序列'")
	private Integer isEquipment;

	/**----是否启用序列---**/
	@Column(name="enableSn", columnDefinition="Integer NULL COMMENT '是否启用序列'")
	private Integer enableSn;
	/**----序列号---**/
	@Column(name="snCode", columnDefinition="nvarchar(50) NULL COMMENT '序列号'")
	private String snCode;

	@Column(name="ownerType", columnDefinition="Integer NULL COMMENT '是否启用序列'")
	private Integer ownerType;

	/**----单位---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '单位'")
	private String detailUnitName;

	@Column(name="extendstring1", columnDefinition="nvarchar(255) NULL ")
	private String extendString1;
	@Column(name="extendfloat1", columnDefinition="number(18,9) NULL ")
	private Double extendFloat1;
	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;

	@Column(name="subDetailCount", columnDefinition="number(18,9)")
	private Double subDetailCount;

	@Column(name="sheetCode", columnDefinition="nvarchar(20) NULL COMMENT '单位'")
	private String sheetCode;

	@Column(name="orderNum", columnDefinition="nvarchar(20) NULL COMMENT '单位'")
	private String orderNum;
	@Column(name="sonId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer sonId;

	@Column(name="storeUsedCount", columnDefinition="number(18,9)")
	private Double storeUsedCount;
	@Column(name="ytCount", columnDefinition="number(18,9)")
	private Double ytCount;
	/**----ERP行号---**/
	@Column(name="erpRowNum", columnDefinition="nvarchar2(50) NULL COMMENT 'ERP行号'")
	private String erpRowNum;

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

    public Integer getPlanDepartId() {
        return planDepartId;
    }

    public void setPlanDepartId(Integer planDepartId) {
        this.planDepartId = planDepartId;
    }

	public Double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Double detailCount) {
		this.detailCount = detailCount;
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

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
	}

	public String getExtendString1() {
		return extendString1;
	}

	public void setExtendString1(String extendString1) {
		this.extendString1 = extendString1;
	}

	public Double getExtendFloat1() {
		return extendFloat1;
	}

	public void setExtendFloat1(Double extendFloat1) {
		this.extendFloat1 = extendFloat1;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Double getSubDetailCount() {
		return subDetailCount;
	}

	public void setSubDetailCount(Double subDetailCount) {
		this.subDetailCount = subDetailCount;
	}

	public String getSheetCode() {
		return sheetCode;
	}

	public void setSheetCode(String sheetCode) {
		this.sheetCode = sheetCode;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getSonId() {
		return sonId;
	}

	public void setSonId(Integer sonId) {
		this.sonId = sonId;
	}

	public Double getStoreUsedCount() {
		return storeUsedCount;
	}

	public void setStoreUsedCount(Double storeUsedCount) {
		this.storeUsedCount = storeUsedCount;
	}

	public Double getYtCount() {
		return ytCount;
	}

	public void setYtCount(Double ytCount) {
		this.ytCount = ytCount;
	}

	public String getErpRowNum() {
		return erpRowNum;
	}

	public void setErpRowNum(String erpRowNum) {
		this.erpRowNum = erpRowNum;
	}

	public ThList() {
	
  	}


}