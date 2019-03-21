package com.zthc.ewms.sheet.entity.apply;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: 申领单号
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_SQNUM")
public class SqNum {
	@Id
	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '主键'")
	private Integer id;
	/**----NAME---**/
	@Column(name="NAME", columnDefinition="nvarchar(255) NULL COMMENT 'NAME'")
	private String NAME;
	/**----编码---**/
	@Column(name="code", columnDefinition="nvarchar(64) NULL COMMENT '编码'")
	private String code;


	/**----物料范围ID---**/
	@Column(name="officesId", columnDefinition="Integer NULL COMMENT '物料范围ID'")
	private Integer officesId;
	/**----物料范围名称---**/
	@Column(name="officeName", columnDefinition="nvarchar(200) NULL COMMENT '物料范围名称'")
	private String officeName;
	/**----使用单位ID---**/
	@Column(name="usedDepartId", columnDefinition="Integer NULL COMMENT '使用单位ID'")
	private Integer usedDepartId;

	/**----申请单位ID---**/
	@Column(name="applyDepartId", columnDefinition="Integer NULL COMMENT '申请单位ID'")
	private Integer applyDepartId;
	/**----申请单位名称---**/
	@Column(name="applyDepartName", columnDefinition="nvarchar(200) NULL COMMENT '申请单位名称'")
	private String applyDepartName;
	/**----使用单位名称---**/
	@Column(name="usedDep", columnDefinition="nvarchar(200) NULL COMMENT 'usedDep'")
	private String usedDep;
	/**----库存组织---**/
	@Column(name="extendString2", columnDefinition="nvarchar(255) NULL ")
	private String extendString2;
	/**----ZtId---**/
	@Column(name="ZtId", columnDefinition="Integer NULL COMMENT 'ZtId'")
	private Integer ztId;
	/**----资金来源---**/
	@Column(name="fundsSource", columnDefinition="Integer NULL COMMENT ''")
	private Integer fundsSource;
	/**----用途---**/
	@Column(name="extendString1", columnDefinition="nvarchar(255) NULL ")
	private String extendString1;
	/**----申领数量---**/
	@Column(name="slcount", columnDefinition="Integer NULL ")
	private Integer slcount;
	/**----以出库数量---**/
	@Column(name="ckcount", columnDefinition="Integer NULL")
	private Integer ckcount;
	/**----备注---**/
	@Column(name="memo", columnDefinition="nvarchar(200) NULL COMMENT '备注'")
	private String memo;
	/**----使用单位---**/
	@Column(name="useddepartname", columnDefinition="nvarchar(200) NULL COMMENT '使用单位'")
	private String useddepartname;
	/**----创建时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createDate", columnDefinition="date NULL COMMENT '创建时间'")
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