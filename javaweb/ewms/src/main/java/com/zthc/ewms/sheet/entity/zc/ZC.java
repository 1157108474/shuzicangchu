package com.zthc.ewms.sheet.entity.zc;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Title: 杂出单
 * @Package
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_ZCD")
public class ZC {
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
    @Column(name="relateSheet", columnDefinition="Integer NULL COMMENT 'relateSheet'")
    private Integer relateSheet;
    /**----提交人ID---**/
    @Column(name="submitManId", columnDefinition="Integer NULL COMMENT '提交人ID'")
    private Integer submitManId;
    /**----提交时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="submitTime", columnDefinition="date NULL COMMENT '提交时间'")
    private Date submitTime;
    /**----状态---**/
    @Column(name="status", columnDefinition="Integer NULL COMMENT '状态'")
    private Integer status;
    /**----备注---**/
    @Column(name="memo", columnDefinition="nvarchar(200) NULL COMMENT '备注'")
    private String memo;
    /**----创建人---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人'")
    private Integer creator;
    /**----创建时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createDate", columnDefinition="date NULL COMMENT '创建时间'")
    private Date createDate;
    @Transient
    private String createDateStr;


    /**----ZTID---**/
    @Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
    private Integer ztId;
    /**----订单编码---**/
    @Column(name="orderNum", columnDefinition="NVARCHAR2(200) NULL COMMENT '订单编码'")
    private String orderNum;

    /**----接收单号---**/
    @Column(name="receiveNum", columnDefinition="NVARCHAR2(200) NULL COMMENT '接收单号'")
    private String receiveNum;

    @Column(name="storeManId", columnDefinition="Integer NULL COMMENT 'storeManId'")
    private Integer storeManId;
    /**----使用人---**/
    @Column(name="usedManId", columnDefinition="Integer NULL COMMENT '使用人'")
    private Integer usedManId;
    /**----供应商ID---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '供应商ID'")
    private Integer providerDepId;

    /**----ownerDep---**/
    @Column(name="ownerDep", columnDefinition="Integer NULL COMMENT 'ownerDep'")
    private Integer ownerDep;





    @Column(name="extendstring1", columnDefinition="nvarchar(255) NULL ")
    private String extendString1;
    @Column(name="extendstring2", columnDefinition="nvarchar(255) NULL ")
    private String extendString2;
    @Column(name="extendstring3", columnDefinition="nvarchar(255) NULL ")
    private String extendString3;
    @Column(name="extendstring4", columnDefinition="nvarchar(255) NULL ")
    private String extendString4;
    @Column(name="extendstring5", columnDefinition="nvarchar(255) NULL ")
    private String extendString5;
    @Column(name="extendstring6", columnDefinition="nvarchar(255) NULL ")
    private String extendString6;
    /**----extendInt1---**/
    @Column(name="extendInt1", columnDefinition="Integer NULL COMMENT 'extendInt'")
    private Integer extendInt1;


    @Column(name="statusName", columnDefinition="nvarchar(200) NULL COMMENT '备注'")
    private String statusName;
    @Column(name="personName", columnDefinition="nvarchar(200) NULL COMMENT '备注'")
    private String personName;
    @Column(name="providerName")
    private String providerName;
    @Column(name="depName", columnDefinition="nvarchar(200) NULL COMMENT '备注'")
    private String depName;

//	@Column(name="houseCode")
//	private String houseCode;


    @Column(name="extendstring7", columnDefinition="nvarchar(255) NULL ")
    private String extendString7;

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

    public String getExtendString1() {
        return extendString1;
    }

    public void setExtendString1(String extendString1) {
        this.extendString1 = extendString1;
    }

    public String getExtendString2() {
        return extendString2;
    }

    public void setExtendString2(String extendString2) {
        this.extendString2 = extendString2;
    }

    public String getExtendString3() {
        return extendString3;
    }

    public void setExtendString3(String extendString3) {
        this.extendString3 = extendString3;
    }

    public String getExtendString4() {
        return extendString4;
    }

    public void setExtendString4(String extendString4) {
        this.extendString4 = extendString4;
    }

    public String getExtendString5() {
        return extendString5;
    }

    public void setExtendString5(String extendString5) {
        this.extendString5 = extendString5;
    }

    public String getExtendString6() {
        return extendString6;
    }

    public void setExtendString6(String extendString6) {
        this.extendString6 = extendString6;
    }

    public Integer getExtendInt1() {
        return extendInt1;
    }

    public void setExtendInt1(Integer extendInt1) {
        this.extendInt1 = extendInt1;
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

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

//	public String getHouseCode() {
//		return houseCode;
//	}
//
//	public void setHouseCode(String houseCode) {
//		this.houseCode = houseCode;
//	}


    public String getExtendString7() {
        return extendString7;
    }

    public void setExtendString7(String extendString7) {
        this.extendString7 = extendString7;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ZC() {

    }
}
