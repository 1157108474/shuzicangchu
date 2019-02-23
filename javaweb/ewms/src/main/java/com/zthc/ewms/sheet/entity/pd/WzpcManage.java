package com.zthc.ewms.sheet.entity.pd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name="V_KCPD")
public class WzpcManage {

	@Id
  	@Column(name="id")
  	private Integer id;
    @Column(name="guid")
    private String guid;
    @Column(name="name")
    private String name;
    @Column(name="code")
    private String code;
    @Column(name="kindId")
    private Integer kindId;
    @Column(name="typeId")
    private Integer typeId;
    @Column(name="dutyId")
    private String dutyId;
    @Column(name="departId")
    private Integer departId;
    @Column(name="routeId")
    private Integer routeId;
	@Column(name="ROUTE_STEPID")
	private Integer route_stepId;
	@Column(name="ROLEID")
	private Integer rileId;
	@Column(name="RELATESHEET")
	private Integer relateSheet;
	@Column(name="SUBMITMANID")
	private Integer submitManId;
	@Column(name="SUBMITTIME")
	private Date submitTime;
	@Column(name="STATUS")
	private Integer status;
	@Column(name="memo")
	private String memo;
	@Column(name="creator")
	private Integer creator;
	@Column(name="createDate")
	private Date createDate;
	@Column(name="ZtId")
	private Integer ztId;
	@Column(name="extendInt1")
	private Integer extendInt1;//sheet表中的盘点单库房
	@Column(name="extendInt2")
	private Integer extendInt2;
	@Column(name="extendInt3")
	private Integer extendInt3;//单据明细已盘count
	@Column(name="extendstring1")
	private String extendString1;
	@Column(name="PERSONNAME")
	private String personName;
	@Column(name="HOUSENAME")
	private String houseName;
	@Column(name="PDTYPE")
	private String pdType;
	@Column(name="STATUSNAME")
	private String statusName;
	@Column(name="DEPNAME")
	private String depName;
	@Column(name="URL")
	private String url;
	@Column(name="SUMDETAILCOUNT")
	private Integer sumDetailCount;

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

	public Integer getRoute_stepId() {
		return route_stepId;
	}

	public void setRoute_stepId(Integer route_stepId) {
		this.route_stepId = route_stepId;
	}

	public Integer getRileId() {
		return rileId;
	}

	public void setRileId(Integer rileId) {
		this.rileId = rileId;
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

	public Integer getExtendInt1() {
		return extendInt1;
	}

	public void setExtendInt1(Integer extendInt1) {
		this.extendInt1 = extendInt1;
	}

	public Integer getExtendInt2() {
		return extendInt2;
	}

	public void setExtendInt2(Integer extendInt2) {
		this.extendInt2 = extendInt2;
	}

	public String getExtendString1() {
		return extendString1;
	}

	public void setExtendString1(String extendString1) {
		this.extendString1 = extendString1;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getPdType() {
		return pdType;
	}

	public void setPdType(String pdType) {
		this.pdType = pdType;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSumDetailCount() {
		return sumDetailCount;
	}

	public Integer getExtendInt3() {
		return extendInt3;
	}

	public void setExtendInt3(Integer extendInt3) {
		this.extendInt3 = extendInt3;
	}

	public void setSumDetailCount(Integer sumDetailCount) {
		this.sumDetailCount = sumDetailCount;
	}

	public WzpcManage() {
	}


}