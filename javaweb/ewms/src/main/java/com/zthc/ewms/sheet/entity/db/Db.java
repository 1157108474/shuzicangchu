package com.zthc.ewms.sheet.entity.db;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: ���ݱ�
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_DBD")
public class Db {

  	/**----����---**/
	@Id
	@GeneratedValue(generator="customKey")
  	@GenericGenerator(name="customKey", strategy="com.hckj.base.database.hibernate.CustomKey")
  	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '����'")
  	private Integer id;
  	/**----Ψһ��ʶ---**/
    @Column(name="guid", columnDefinition="nvarchar(64) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
  	/**----����---**/
    @Column(name="name", columnDefinition="nvarchar(64) NULL COMMENT '����'")
    private String name;
  	/**----����---**/
    @Column(name="code", columnDefinition="nvarchar(64) NULL COMMENT '����'")
    private String code;
  	/**----���ݷ���---**/
    @Column(name="kindId", columnDefinition="Integer NULL COMMENT '���ݷ���'")
    private Integer kindId;
  	/**----type---**/
    @Column(name="typeId", columnDefinition="Integer NULL COMMENT 'type'")
    private Integer typeId;
  	/**----duty---**/
    @Column(name="dutyId", columnDefinition="nvarchar(64) NULL COMMENT 'duty'")
    private String dutyId;
  	/**----����ID---**/
    @Column(name="departId", columnDefinition="Integer NULL COMMENT '����ID'")
    private Integer departId;
  	/**----routeId---**/
    @Column(name="routeId", columnDefinition="Integer NULL COMMENT 'routeId'")
    private Integer routeId;
  	/**----relateSheet---**/
    @Column(name="relateSheet", columnDefinition="Integer NULL COMMENT 'relateSheet'")
    private Integer relateSheet;
  	/**----�ύ��ID---**/
    @Column(name="submitManId", columnDefinition="Integer NULL COMMENT '�ύ��ID'")
    private Integer submitManId;
  	/**----�ύʱ��---**/
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  	@Column(name="submitTime", columnDefinition="date NULL COMMENT '�ύʱ��'")
  	private Date submitTime;
  	/**----״̬---**/
    @Column(name="status", columnDefinition="Integer NULL COMMENT '״̬'")
    private Integer status;
  	/**----��ע---**/
    @Column(name="memo", columnDefinition="nvarchar(200) NULL COMMENT '��ע'")
    private String memo;
  	/**----������---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '������'")
    private Integer creator;
  	/**----����ʱ��---**/
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  	@Column(name="createDate", columnDefinition="date NULL COMMENT '����ʱ��'")
  	private Date createDate;
	@Transient
	private String createDateStr;
  	/**----������---**/
    @Column(name="updator", columnDefinition="Integer NULL COMMENT '������'")
    private Integer updater;
  	/**----����ʱ��---**/
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  	@Column(name="updateDate", columnDefinition="date NULL COMMENT '����ʱ��'")
  	private Date updateDate;
  	/**----ZTID---**/
    @Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
    private Integer ztId;
  	/**----extendInt1---**/
    @Column(name="extendInt1", columnDefinition="Integer NULL COMMENT 'extendInt'")
    private Integer extendInt1;

	/**----extendInt1---**/
	@Column(name="extendInt2", columnDefinition="Integer NULL ")
	private Integer extendInt2;




  	/**----��������---**/
    @Column(name="orderNum", columnDefinition="NVARCHAR2(200) NULL COMMENT '��������'")
    private String orderNum;

	/**----���յ���---**/
	@Column(name="receiveNum", columnDefinition="NVARCHAR2(200) NULL COMMENT '���յ���'")
	private String receiveNum;

  	/**----ʹ�õ�λID---**/
    @Column(name="usedDepartId", columnDefinition="Integer NULL COMMENT 'ʹ�õ�λID'")
    private Integer usedDepartId;
	/**----storeManId---**/
	@Column(name="storeManId", columnDefinition="Integer NULL COMMENT 'storeManId'")
	private Integer storeManId;
	/**----ʹ����---**/
	@Column(name="usedManId", columnDefinition="Integer NULL COMMENT 'ʹ����'")
	private Integer usedManId;
  	/**----��Ӧ��ID---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '��Ӧ��ID'")
    private Integer providerDepId;

	/**----ownerDep---**/
	@Column(name="ownerDep", columnDefinition="Integer NULL COMMENT 'ownerDep'")
	private Integer ownerDep;

	@Column(name="personName", columnDefinition="nvarchar(200) NULL COMMENT '��ע'")
	private String personName;


	@Column(name="outOrgName", columnDefinition="nvarchar(200) NULL COMMENT '��ע'")
	private String outOrgName;


	@Column(name="intoOrgName", columnDefinition="nvarchar(200) NULL COMMENT '��ע'")
	private String intoOrgName;

	@Column(name="depName", columnDefinition="nvarchar(200) NULL COMMENT '��ע'")
	private String depName;

	@Column(name="statusName", columnDefinition="nvarchar(200) NULL COMMENT '��ע'")
	private String statusName;


	@Column(name="extendInt4" , columnDefinition="int NULL COMMENT 'ERPID'")
	private Integer extendInt4;

	@Column(name="url")
	private String url;

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

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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


	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
	}

	public Integer getUsedDepartId() {
		return usedDepartId;
	}

	public void setUsedDepartId(Integer usedDepartId) {
		this.usedDepartId = usedDepartId;
	}

	public Integer getStoreManId() {
		return storeManId;
	}

	public void setStoreManId(Integer storeManId) {
		this.storeManId = storeManId;
	}

	public Integer getUsedManId() {
		return usedManId;
	}

	public void setUsedManId(Integer usedManId) {
		this.usedManId = usedManId;
	}

	public Integer getProviderDepId() {
		return providerDepId;
	}

	public void setProviderDepId(Integer providerDepId) {
		this.providerDepId = providerDepId;
	}

	public Integer getOwnerDep() {
		return ownerDep;
	}

	public void setOwnerDep(Integer ownerDep) {
		this.ownerDep = ownerDep;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getOutOrgName() {
		return outOrgName;
	}

	public void setOutOrgName(String outOrgName) {
		this.outOrgName = outOrgName;
	}

	public String getIntoOrgName() {
		return intoOrgName;
	}

	public void setIntoOrgName(String intoOrgName) {
		this.intoOrgName = intoOrgName;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Db() {
	
  	}

	public Integer getExtendInt4() {
		return extendInt4;
	}

	public void setExtendInt4(Integer extendInt4) {
		this.extendInt4 = extendInt4;
	}
}