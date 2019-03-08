package com.zthc.ewms.sheet.entity.ck;

import com.zthc.ewms.base.util.NumberUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * @Title: 单据明细
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_CKDETAILS")
public class CK {

  	/**----id---**/
	@Id
	@Column(name="id", columnDefinition="Integer NULL COMMENT 'id'")
	private Integer id;
	/**----单据ID---**/
    @Column(name="sheetId", columnDefinition="Integer NULL COMMENT '单据ID'")
    private Integer sheetId;
	/**----物料编码---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '物料编码'")
	private String materialCode;
	/**----描述---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '描述'")
	private String description;
	/**----单位名称---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '单位名称'")
	private String detailUnitName;
	/**----出库数量---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '出库数量'")
	private double detailCount;
	/**----库房---**/
	@Column(name="houseName", columnDefinition="nvarchar(200) NULL COMMENT '库房'")
	private String houseName;
	/**----库房编码---**/
	@Column(name="houseCode", columnDefinition="nvarchar(200) NULL COMMENT '库房编码'")
	private String houseCode;

	/**
	 * 计划编号
	 */
	@Column(name="extendstring1", columnDefinition="nvarchar(255) NULL ")
	private String extendString1;

	/**----不含税单价---**/
	@Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '不含税单价'")
	private Double noTaxPrice;

	/**----不含税总额---**/
	@Column(name="noTaxSum", columnDefinition="number(18,9) NULL COMMENT '不含税总额'")
	private Double noTaxSum;

	public Integer getId() {
		return id;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSheetId() {
		return sheetId;
	}

	public void setSheetId(Integer sheetId) {
		this.sheetId = sheetId;
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

	public double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(double detailCount) {
		this.detailCount = detailCount;
	}

	public String getExtendString1() {
		return extendString1;
	}

	public void setExtendString1(String extendString1) {
		this.extendString1 = extendString1;
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

	public Double getNoTaxSum() {
		return noTaxSum;
	}

	public void setNoTaxSum(Double noTaxSum) {
		this.noTaxSum = noTaxSum;
	}

	public String getNoTaxSumDouble() {
		return NumberUtils.getDouble(noTaxSum);
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public CK() {
	}
}