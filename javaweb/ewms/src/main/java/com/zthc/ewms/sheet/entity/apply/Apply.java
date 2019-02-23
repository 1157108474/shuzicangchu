package com.zthc.ewms.sheet.entity.apply;

import javax.persistence.*;

/**  
 * @Title: ������ϸ
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="v_slotherdetails")
public class Apply {
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
	/**----����---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '����'")
	private String description;
	/***--��λ--**/
	@Column(name="extendstring1", columnDefinition="nvarchar(255) NULL ")
	private String extendString1;
	/**----��λ����---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '��λ����'")
	private String detailUnitName;
	/**----������ϸID---**/
	@Column(name="sheetDetailId", columnDefinition="Integer NULL COMMENT '������ϸID'")
	private Integer sheetDetailId;
	/**----��ϸ����---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
	private Double detailCount;
	/**----���к�---**/
	@Column(name="snCode", columnDefinition="nvarchar(50) NULL COMMENT '���к�'")
	private String snCode;
	/**----����ID---**/
	@Column(name="materialId", columnDefinition="Integer NULL COMMENT '����ID'")
	private Integer materialId;

	@Column(name="detailUnit", columnDefinition="Integer NULL COMMENT '״̬'")
	private Integer detailUnit;
    @Column(name = "extendString2", columnDefinition = "nvarchar(255) NULL ")
    private String extendString2;
    /**----�ѳ�������---**/
	@Column(name="ckCount", columnDefinition="Integer NULL COMMENT '�ѳ�������'")
	private Integer ckCount;

	@Column(name="ztid", columnDefinition="Integer NULL COMMENT '����ID'")
	private Integer ztId;

	@Column(name="extendInt7", columnDefinition="Integer NULL COMMENT 'ERPDetailId'")
	private Integer extendInt7;
    @Transient
    private Integer applyCount;
    @Transient
    private String address;
    //�ⷿid������ͼ��ͬ�����
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT '�ⷿid'")
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