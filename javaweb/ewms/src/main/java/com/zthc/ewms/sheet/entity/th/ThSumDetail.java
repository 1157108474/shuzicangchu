package com.zthc.ewms.sheet.entity.th;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * @Title: ������ϸ
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_THSUMMARYDETAILS")
public class ThSumDetail {

  	/**----����---**/
  	@Id
    @Column(name="id", columnDefinition="Integer NULL COMMENT '����'")
    private Integer id;

  	/**----����ID---**/
    @Column(name="sheetId", columnDefinition="Integer NULL COMMENT '����ID'")
    private Integer sheetId;

  	/**----���ϱ���---**/
    @Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '���ϱ���'")
    private String materialCode;



	/**----��ϸ����---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
	private Double detailCount;



	/**----storeId---**/
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
	private Integer storeId;


	/**----ZTID---**/
	@Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
	private Integer ztId;
	/**----����˰---**/
	@Column(name="noTaxSum", columnDefinition="number(18,9) NULL COMMENT '����˰����'")
	private Double noTaxSum;

	/**----����˰����---**/
	@Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '����˰����'")
	private Double noTaxPrice;


	/**----����---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '����'")
	private String description;



	/**----����---**/
	@Column(name="tagCode", columnDefinition="nvarchar(50) NULL COMMENT '����'")
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