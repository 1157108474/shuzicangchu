package com.zthc.ewms.sheet.entity.guard;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: 寄存出库
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_JCCKD")
public class SheetJCCK {

	/**----主键---**/
	@Id
	@GeneratedValue(generator="customKey")
	@GenericGenerator(name="customKey", strategy="com.hckj.base.database.hibernate.CustomKey")
	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '主键'")
	private Integer id;
	/**----唯一标识---**/
	@Column(name="guid", columnDefinition="nvarchar(64) NULL COMMENT '唯一标识'")
	private String guid;
	/**----名称---**/
	@Column(name="name", columnDefinition="nvarchar(64) NULL COMMENT '名称'")
	private String name;
	/**----编码---**/
	@Column(name="code", columnDefinition="nvarchar(64) NULL COMMENT '编码'")
	private String code;
	/**----单据分类---**/
	@Column(name="kindId", columnDefinition="Integer NULL COMMENT '单据分类'")
	private Integer kindId;
	/**----type---**/
	@Column(name="typeId", columnDefinition="Integer NULL COMMENT 'type'")
	private Integer typeId;
	/**----duty---**/
	@Column(name="dutyId", columnDefinition="nvarchar(64) NULL COMMENT 'duty'")
	private String dutyId;
	/**----部门ID---**/
	@Column(name="departId", columnDefinition="Integer NULL COMMENT '部门ID'")
	private Integer departId;
	/**----routeId---**/
	@Column(name="routeId", columnDefinition="Integer NULL COMMENT 'routeId'")
	private Integer routeId;
	/**----relateSheet---**/
	@Column(name="route_STEPID", columnDefinition="Integer NULL COMMENT 'relateSheet'")
	private Integer routeStepId;
	@Transient
	private String createDateStr;

	@Column(name="ROLEID")
	private Integer roleId;
	@Column(name="RELATESHEET")
	private Integer relateSheet;
	@Column(name="SUBMITMANID")
	private Integer submitManId;
	@Column(name="SUBMITTIME")
	private Date submitTime;
	@Column(name="STATUS")
	private Integer status;
	@Column(name="MEMO")
	private String memo;
	@Column(name="CREATOR")
	private Integer creator;
	@Column(name="CREATEDATE")
	private Date createDate;
	@Column(name="ZTID")
	private Integer ztId;
	@Column(name="OWNERDEP")
	private Integer ownerDep;
	@Column(name="URL")
	private String url;
	@Column(name="OWNERDEPNAME")
	private String ownerDepName;
	@Column(name="PERSONNAME")
	private String personName;
	@Column(name="STATUSNAME")
	private String StatusName;
	@Column(name="DEPNAME")
	private String depName;
	@Column(name="USEDMANID")
	private Integer usedManId;
	@Column(name="USENAME")
	private String useName;
	@Column(name="STOREMANID")
	private Integer storeManId;
	@Column(name="STOREMANNAME")
	private String storeManName;


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getKindId() {
		return kindId;
	}

	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getDutyId() {
		return dutyId;
	}

	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public Integer getRouteStepId() {
		return routeStepId;
	}

	public void setRouteStepId(Integer routeStepId) {
		this.routeStepId = routeStepId;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRelateSheet() {
		return relateSheet;
	}

	public void setRelateSheet(Integer relateSheet) {
		this.relateSheet = relateSheet;
	}

	public Integer getSubmitManId() {
		return submitManId;
	}

	public void setSubmitManId(Integer submitManId) {
		this.submitManId = submitManId;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
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

	public Integer getZtId() {
		return ztId;
	}

	public void setZtId(Integer ztId) {
		this.ztId = ztId;
	}

	public Integer getOwnerDep() {
		return ownerDep;
	}

	public void setOwnerDep(Integer ownerDep) {
		this.ownerDep = ownerDep;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOwnerDepName() {
		return ownerDepName;
	}

	public void setOwnerDepName(String ownerDepName) {
		this.ownerDepName = ownerDepName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getStatusName() {
		return StatusName;
	}

	public void setStatusName(String statusName) {
		StatusName = statusName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Integer getUsedManId() {
		return usedManId;
	}

	public void setUsedManId(Integer usedManId) {
		this.usedManId = usedManId;
	}

	public String getUseName() {
		return useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
	}

	public Integer getStoreManId() {
		return storeManId;
	}

	public void setStoreManId(Integer storeManId) {
		this.storeManId = storeManId;
	}

	public String getStoreManName() {
		return storeManName;
	}

	public void setStoreManName(String storeManName) {
		this.storeManName = storeManName;
	}

	public SheetJCCK() {
	}

	public SheetJCCK(String guid, String name, String code, Integer kindId, Integer typeId, String dutyId, Integer departId, Integer routeId, Integer routeStepId, String createDateStr, Integer roleId, Integer relateSheet, Integer submitManId, Date submitTime, Integer status, String memo, Integer creator, Date createDate, Integer ztId, Integer ownerDep, String url, String ownerDepName, String personName, String statusName, String depName, Integer usedManId, String useName, Integer storeManId, String storeManName) {
		this.guid = guid;
		this.name = name;
		this.code = code;
		this.kindId = kindId;
		this.typeId = typeId;
		this.dutyId = dutyId;
		this.departId = departId;
		this.routeId = routeId;
		this.routeStepId = routeStepId;
		this.createDateStr = createDateStr;
		this.roleId = roleId;
		this.relateSheet = relateSheet;
		this.submitManId = submitManId;
		this.submitTime = submitTime;
		this.status = status;
		this.memo = memo;
		this.creator = creator;
		this.createDate = createDate;
		this.ztId = ztId;
		this.ownerDep = ownerDep;
		this.url = url;
		this.ownerDepName = ownerDepName;
		this.personName = personName;
		StatusName = statusName;
		this.depName = depName;
		this.usedManId = usedManId;
		this.useName = useName;
		this.storeManId = storeManId;
		this.storeManName = storeManName;
	}
}