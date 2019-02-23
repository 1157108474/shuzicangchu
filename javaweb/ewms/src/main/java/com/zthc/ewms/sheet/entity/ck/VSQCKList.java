package com.zthc.ewms.sheet.entity.ck;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * @Title: ���������ϸ
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_SQCKLIST")
public class VSQCKList {
  	/**----id---**/
	@Id
    @Column(name="id", columnDefinition="Integer NULL COMMENT 'ID'")
    private Integer id;
	/**----���ϱ���---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '���ϱ���'")
	private String materialCode;
	/**----����---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '����'")
	private String description;
	/**----��λ����---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '��λ����'")
	private String detailUnitName;
	/**----���ϵ���---**/
	@Column(name="code", columnDefinition="nvarchar(64) NULL COMMENT '���ϵ���'")
	private String code;
	/**----ʹ�ò���---**/
	@Column(name="name", columnDefinition="nvarchar(200) NULL COMMENT 'ʹ�ò���'")
	private String name;
	/**---�ƻ�����----**/
	@Column(name="sncode", columnDefinition="nvarchar(50) NULL COMMENT '�ƻ�����'")
	private String sncode;
	/**----δ������---**/
	@Column(name="allowCount", columnDefinition="number(18,9) NULL COMMENT 'δ������'")
	private double allowCount;
	/**----��������---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '��������'")
	private double detailCount;
	/**----ztId---**/
	@Column(name="ztId", columnDefinition="Integer NULL COMMENT 'ZtId'")
	private Integer ztId;
	/**----�ɹ��ƻ�ID---**/
	@Column(name="sheetDetailId", columnDefinition="Integer NULL COMMENT 'sheetDetailId'")
	private Integer sheetDetailId;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAllowCount() {
		return allowCount;
	}

	public void setAllowCount(double allowCount) {
		this.allowCount = allowCount;
	}

	public double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(double detailCount) {
		this.detailCount = detailCount;
	}

	public Integer getZtId() {
		return ztId;
	}

	public void setZtId(Integer ztId) {
		this.ztId = ztId;
	}

	public String getSncode() {
		return sncode;
	}

	public void setSncode(String sncode) {
		this.sncode = sncode;
	}

	public Integer getSheetDetailId() {
		return sheetDetailId;
	}

	public void setSheetDetailId(Integer sheetDetailId) {
		this.sheetDetailId = sheetDetailId;
	}

	public VSQCKList() {
	}
}