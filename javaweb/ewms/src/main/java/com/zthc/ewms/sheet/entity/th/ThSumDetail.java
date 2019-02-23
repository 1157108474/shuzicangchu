package com.zthc.ewms.sheet.entity.th;

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
@Table(name="V_THSUMMARYDETAILS")
public class ThSumDetail {

  	/**----主键---**/
  	@Id
    @Column(name="id", columnDefinition="Integer NULL COMMENT '主键'")
    private Integer id;

  	/**----单据ID---**/
    @Column(name="sheetId", columnDefinition="Integer NULL COMMENT '单据ID'")
    private Integer sheetId;

  	/**----物料编码---**/
    @Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '物料编码'")
    private String materialCode;



	/**----明细数量---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '明细数量'")
	private Double detailCount;



	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;


	/**----ZTID---**/
	@Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
	private Integer ztId;
	/**----不含税---**/
	@Column(name="noTaxSum", columnDefinition="number(18,9) NULL COMMENT '不含税单价'")
	private Double noTaxSum;

	/**----不含税单价---**/
	@Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '不含税单价'")
	private Double noTaxPrice;


	/**----描述---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '描述'")
	private String description;



	/**----编码---**/
	@Column(name="tagCode", columnDefinition="nvarchar(50) NULL COMMENT '编码'")
	private String tagCode;



	@Column(name="wareHouseCode")
	private String wareHouseCode;

	public Integer getId() {
		return id;
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

	public Integer getZtId() {
		return ztId;
	}

	public void setZtId(Integer ztId) {
		this.ztId = ztId;
	}

	public Double getNoTaxSum() {
		return noTaxSum;
	}

	public void setNoTaxSum(Double noTaxSum) {
		this.noTaxSum = noTaxSum;
	}

	public Double getNoTaxPrice() {
		return noTaxPrice;
	}

	public void setNoTaxPrice(Double noTaxPrice) {
		this.noTaxPrice = noTaxPrice;
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

	public String getWareHouseCode() {
		return wareHouseCode;
	}

	public void setWareHouseCode(String wareHouseCode) {
		this.wareHouseCode = wareHouseCode;
	}

	public ThSumDetail() {
	
  	}
  

} 