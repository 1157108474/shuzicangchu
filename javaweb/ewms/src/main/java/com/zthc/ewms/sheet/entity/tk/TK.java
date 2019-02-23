package com.zthc.ewms.sheet.entity.tk;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: �˿ⵥ����
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_TKD")
public class TK {
    @Id
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

    @Column(name="ztid")
    private Integer ztId;

    @Column(name="orderNum")
    private String orderNum;

    @Column(name="receiveNum")
    private String receiveNum;

    @Column(name="usedDepartId")
    private Integer usedDepartId;

    @Column(name="storeManId")
    private Integer storeManId;

    @Column(name="usedManId")
    private Integer useManId;

    @Column(name="providerDepId")
    private Integer providerDepId;

    /**----ownerDep---**/
    @Column(name="ownerDep", columnDefinition="Integer NULL COMMENT 'ownerDep'")
    private Integer ownerDep;

    @Column(name="fundsSource")
    private Integer fundsSource;

    @Column(name="extendstring1", columnDefinition="nvarchar(255) NULL ")
    private String extendString1;

    @Column(name="extendInt1", columnDefinition="Integer NULL COMMENT 'extendInt'")
    private Integer extendInt1;
    @Column(name="extendInt3", columnDefinition="Integer NULL COMMENT 'extendInt'")
    private Integer extendInt3;
    @Column(name="officesId")
    private Integer officesId;
    @Column(name="tkCode")
    private String tkCode;

    /**----�����֯---**/
    @Column(name="ztidName", columnDefinition="nvarchar(200) NULL COMMENT '�����֯'")
    private String ztidName;
    /**----ʹ�ò���---**/
    @Column(name="useDepName")
    private String usedDepName ;
    /**----�ϼ���������---**/
    @Column(name="departOfficeName", columnDefinition="nvarchar(200) NULL COMMENT '���첿��'")
    private String departOfficeName ;
    /**----���ⵥ��---**/
    @Column(name="ckCode", columnDefinition="nvarchar(64) NULL COMMENT '���ⵥ��'")
    private String ckCode;
    /**----����״̬---**/
    @Column(name="statusName", columnDefinition="nvarchar(64) NULL COMMENT '����״̬'")
    private String statusName;
    /**----�Ƶ���---**/
    @Column(name="personName", columnDefinition="nvarchar(64) NULL COMMENT '�Ƶ���'")
    private String personName;
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

    @Column(name="url")
    private String url;
//    @Column(name="houseCode")
//    private String houseCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZtidName() {
        return ztidName;
    }

    public void setZtidName(String ztidName) {
        this.ztidName = ztidName;
    }

    public String getUsedDepName() {
        return usedDepName;
    }

    public void setUsedDepName(String usedDepName) {
        this.usedDepName = usedDepName;
    }

    public String getDepartOfficeName() {
        return departOfficeName;
    }

    public void setDepartOfficeName(String departOfficeName) {
        this.departOfficeName = departOfficeName;
    }

    public String getCkCode() {
        return ckCode;
    }

    public void setCkCode(String ckCode) {
        this.ckCode = ckCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
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

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
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

    public Integer getUseManId() {
        return useManId;
    }

    public void setUseManId(Integer useManId) {
        this.useManId = useManId;
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

    public Integer getExtendInt1() {
        return extendInt1;
    }

    public void setExtendInt1(Integer extendInt1) {
        this.extendInt1 = extendInt1;
    }

    public Integer getExtendInt3() {
        return extendInt3;
    }

    public void setExtendInt3(Integer extendInt3) {
        this.extendInt3 = extendInt3;
    }

    public Integer getOfficesId() {
        return officesId;
    }

    public void setOfficesId(Integer officesId) {
        this.officesId = officesId;
    }

    public String getTkCode() {
        return tkCode;
    }

    public void setTkCode(String tkCode) {
        this.tkCode = tkCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public String getHouseCode() {
//        return houseCode;
//    }
//
//    public void setHouseCode(String houseCode) {
//        this.houseCode = houseCode;
//    }

    public TK() {
    }


}