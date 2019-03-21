package com.zthc.ewms.sheet.entity.apply;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: ���쵥��
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_SQNUM")
public class SqNum {
	@Id
	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '����'")
	private Integer id;
	/**----NAME---**/
	@Column(name="NAME", columnDefinition="nvarchar(255) NULL COMMENT 'NAME'")
	private String NAME;
	/**----����---**/
	@Column(name="code", columnDefinition="nvarchar(64) NULL COMMENT '����'")
	private String code;


	/**----���Ϸ�ΧID---**/
	@Column(name="officesId", columnDefinition="Integer NULL COMMENT '���Ϸ�ΧID'")
	private Integer officesId;
	/**----���Ϸ�Χ����---**/
	@Column(name="officeName", columnDefinition="nvarchar(200) NULL COMMENT '���Ϸ�Χ����'")
	private String officeName;
	/**----ʹ�õ�λID---**/
	@Column(name="usedDepartId", columnDefinition="Integer NULL COMMENT 'ʹ�õ�λID'")
	private Integer usedDepartId;

	/**----���뵥λID---**/
	@Column(name="applyDepartId", columnDefinition="Integer NULL COMMENT '���뵥λID'")
	private Integer applyDepartId;
	/**----���뵥λ����---**/
	@Column(name="applyDepartName", columnDefinition="nvarchar(200) NULL COMMENT '���뵥λ����'")
	private String applyDepartName;
	/**----ʹ�õ�λ����---**/
	@Column(name="usedDep", columnDefinition="nvarchar(200) NULL COMMENT 'usedDep'")
	private String usedDep;
	/**----�����֯---**/
	@Column(name="extendString2", columnDefinition="nvarchar(255) NULL ")
	private String extendString2;
	/**----ZtId---**/
	@Column(name="ZtId", columnDefinition="Integer NULL COMMENT 'ZtId'")
	private Integer ztId;
	/**----�ʽ���Դ---**/
	@Column(name="fundsSource", columnDefinition="Integer NULL COMMENT ''")
	private Integer fundsSource;
	/**----��;---**/
	@Column(name="extendString1", columnDefinition="nvarchar(255) NULL ")
	private String extendString1;
	/**----��������---**/
	@Column(name="slcount", columnDefinition="Integer NULL ")
	private Integer slcount;
	/**----�Գ�������---**/
	@Column(name="ckcount", columnDefinition="Integer NULL")
	private Integer ckcount;
	/**----��ע---**/
	@Column(name="memo", columnDefinition="nvarchar(200) NULL COMMENT '��ע'")
	private String memo;
	/**----ʹ�õ�λ---**/
	@Column(name="useddepartname", columnDefinition="nvarchar(200) NULL COMMENT 'ʹ�õ�λ'")
	private String useddepartname;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createDate", columnDefinition="date NULL COMMENT '����ʱ��'")
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getApplyDepartName() {
		return applyDepartName;
	}

	public void setApplyDepartName(String applyDepartName) {
		this.applyDepartName = applyDepartName;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getOfficesId() {
		return officesId;
	}

	public void setOfficesId(Integer officesId) {
		this.officesId = officesId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Integer getUsedDepartId() {
		return usedDepartId;
	}

	public void setUsedDepartId(Integer usedDepartId) {
		this.usedDepartId = usedDepartId;
	}

	public Integer getApplyDepartId() {
		return applyDepartId;
	}

	public void setApplyDepartId(Integer applyDepartId) {
		this.applyDepartId = applyDepartId;
	}

	public String getUsedDep() {
		return usedDep;
	}

	public void setUsedDep(String usedDep) {
		this.usedDep = usedDep;
	}

	public String getExtendString2() {
		return extendString2;
	}

	public void setExtendString2(String extendString2) {
		this.extendString2 = extendString2;
	}

	public Integer getZtId() {
		return ztId;
	}

	public void setZtId(Integer ztId) {
		this.ztId = ztId;
	}

	public Integer getFundsSource() {
		return fundsSource;
	}

	public void setFundsSource(Integer fundsSource) {
		this.fundsSource = fundsSource;
	}

	public String getExtendString1() {
		return extendString1;
	}

	public void setExtendString1(String extendString1) {
		this.extendString1 = extendString1;
	}

	public Integer getSlcount() {
		return slcount;
	}

	public void setSlcount(Integer slcount) {
		this.slcount = slcount;
	}

	public Integer getCkcount() {
		return ckcount;
	}

	public void setCkcount(Integer ckcount) {
		this.ckcount = ckcount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUseddepartname() {
		return useddepartname;
	}

	public void setUseddepartname(String useddepartname) {
		this.useddepartname = useddepartname;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public SqNum() {
	}
}