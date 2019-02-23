package com.zthc.ewms.sheet.entity.apply;

import javax.persistence.*;

/**  
 * @Title: 单据明细
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="v_slotherdetails")
public class Apply {
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
	/**----描述---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '描述'")
	private String description;
	/***--单位--**/
	@Column(name="extendstring1", columnDefinition="nvarchar(255) NULL ")
	private String extendString1;
	/**----单位名称---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '单位名称'")
	private String detailUnitName;
	/**----单据明细ID---**/
	@Column(name="sheetDetailId", columnDefinition="Integer NULL COMMENT '单据明细ID'")
	private Integer sheetDetailId;
	/**----明细数量---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '明细数量'")
	private Double detailCount;
	/**----序列号---**/
	@Column(name="snCode", columnDefinition="nvarchar(50) NULL COMMENT '序列号'")
	private String snCode;
	/**----物料ID---**/
	@Column(name="materialId", columnDefinition="Integer NULL COMMENT '物料ID'")
	private Integer materialId;

	@Column(name="detailUnit", columnDefinition="Integer NULL COMMENT '状态'")
	private Integer detailUnit;
    @Column(name = "extendString2", columnDefinition = "nvarchar(255) NULL ")
    private String extendString2;
    /**----已出库数量---**/
	@Column(name="ckCount", columnDefinition="Integer NULL COMMENT '已出库数量'")
	private Integer ckCount;

	@Column(name="ztid", columnDefinition="Integer NULL COMMENT '账套ID'")
	private Integer ztId;

	@Column(name="extendInt7", columnDefinition="Integer NULL COMMENT 'ERPDetailId'")
	private Integer extendInt7;
    @Transient
    private Integer applyCount;
    @Transient
    private String address;
    //库房id，在视图中同理加上
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT '库房id'")
	private Integer storeId;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtendString1() {
		return extendString1;
	}

	public void setExtendString1(String extendString1) {
		this.extendString1 = extendString1;
	}

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
	}

	public Integer getSheetDetailId() {
		return sheetDetailId;
	}

	public void setSheetDetailId(Integer sheetDetailId) {
		this.sheetDetailId = sheetDetailId;
	}

	public Double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Double detailCount) {
		this.detailCount = detailCount;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public Integer getDetailUnit() {
		return detailUnit;
	}

	public void setDetailUnit(Integer detailUnit) {
		this.detailUnit = detailUnit;
	}

	public String getExtendString2() {
		return extendString2;
	}

	public void setExtendString2(String extendString2) {
		this.extendString2 = extendString2;
	}

    public Integer getCkCount() {
        return ckCount;
    }

    public void setCkCount(Integer ckCount) {
        this.ckCount = ckCount;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public Integer getZtId() {
		return ztId;
	}

	public void setZtId(Integer ztId) {
		this.ztId = ztId;
	}

	public Integer getExtendInt7() {
		return extendInt7;
	}

	public void setExtendInt7(Integer extendInt7) {
		this.extendInt7 = extendInt7;
	}

	public Integer getStoreId(){
    	return storeId;
	}

	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}


	public Apply() {
	}
}