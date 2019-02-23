package com.zthc.ewms.sheet.entity.ck;

import com.zthc.ewms.sheet.entity.enums.RenewalCostEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: 出库单管理
 * @Package
 */
@Entity
@Table(name = "V_CKD")
public class ManageCK {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT '主键'")
    private Integer id;
    /**
     * ----编码---
     **/
    @Column(name = "code", columnDefinition = "nvarchar(64) NULL COMMENT '编码'")
    private String code;
    /**
     * ----出库类型---
     **/
    @Column(name = "typeName", columnDefinition = "nvarchar(64) NULL COMMENT 'officeName'")
    private String typeName;
    /**
     * ----出库类型Id---
     **/
    @Column(name = "typeId", columnDefinition = "Integer NULL COMMENT '出库类型Id'")
    private Integer typeId;

    /**
     * ----库存组织---
     **/
    @Column(name = "org_name", columnDefinition = "nvarchar(200) NULL COMMENT '库存组织'")
    private String orgName;

    /**
     * ----EXTENDINT3---
     **/
    @Column(name = "EXTENDINT3", columnDefinition = "Integer NULL COMMENT 'EXTENDINT3'")
    private Integer extendint3;
    @Transient
    private String renewalCostStr;

    /**
     * ----单据状态---
     **/
    @Column(name = "statusName", columnDefinition = "nvarchar(64) NULL COMMENT '单据状态'")
    private String statusName;
    /**
     * ----单据状态---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '单据状态'")
    private Integer status;
    /**
     * ----申请单ID---
     **/
    @Column(name = "EXTENDINT1", columnDefinition = "Integer NULL COMMENT '申请单ID'")
    private Integer extendint1;
    /**
     * ----使用单位Id---
     **/
    @Column(name = "useddepartid", columnDefinition = "Integer NULL COMMENT '申请单ID'")
    private Integer useddepartid;
    /**
     * ----使用单位---
     **/
    @Column(name = "useDepName", columnDefinition = "nvarchar(200) NULL COMMENT '使用单位'")
    private String useDepName;

    /**
     * ----requestUnitname---
     **/
    @Column(name = "requestUnitname", columnDefinition = "nvarchar(64) NULL COMMENT 'requestUnitname'")
    private String requestUnitname;

    /**
     * ----申请单号---
     **/
    @Column(name = "slcode", columnDefinition = "nvarchar(64) NULL COMMENT '申请单号'")
    private String slCode;


    /**
     * ----申请单位---
     **/
    @Column(name = "applydepartid", columnDefinition = "Integer NULL COMMENT '申请单位'")
    private Integer applydepartid;

    /**
     * ----调拨单id---
     **/
    @Column(name = "EXTENDINT2", columnDefinition = "nvarchar(200) NULL COMMENT '调拨单id'")
    private String extendint2;
    /**
     * ----调拨单号---
     **/
    @Column(name = "DBCODE", columnDefinition = "nvarchar(64) NULL COMMENT '调拨单号'")
    private String dbCode;

    /**
     * ----制单人---
     **/
    @Column(name = "personName", columnDefinition = "nvarchar(64) NULL COMMENT '制单人'")
    private String personName;
    /**
     * ----科室Id---
     **/
    @Column(name = "officesId", columnDefinition = "Integer NULL COMMENT '科室Id'")
    private Integer officesId;
    /**
     * ----科室名称---
     **/
    @Column(name = "departOfficename", columnDefinition = "nvarchar(255) NULL COMMENT '科室名称'")
    private String departOfficename;

    /**
     * ----ztId---
     **/
    @Column(name = "ztId", columnDefinition = "Integer NULL COMMENT 'ZtId'")
    private Integer ztId;

    /**
     * ----资金来源---
     **/
    @Column(name = "fundsSource", columnDefinition = "Integer NULL COMMENT '资金来源'")
    private Integer fundsSource;
    /**
     * ----用途---
     **/
    @Column(name = "extendString1", columnDefinition = "nvarchar(255) NULL ")
    private String extendString1;
    /**
     * ----extendString2---
     **/
    @Column(name = "extendString2", columnDefinition = "nvarchar(255) NULL ")
    private String extendString2;
    /**
     * ----创建人---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '创建人'")
    private Integer creator;
    /**
     * ----创建时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "date NULL COMMENT '创建时间'")
    private Date createDate;
    @Transient
    private String createDateStr;
    /**
     * ----修改人---
     **/
    @Column(name = "updator", columnDefinition = "Integer NULL COMMENT '修改人'")
    private Integer updator;
    /**
     * ----修改时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL COMMENT '修改时间'")
    private Date updateDate;

    /**
     * ----工作流---
     **/
    @Column(name = "routeid", columnDefinition = "Integer NULL COMMENT '字典表ID'")
    private Integer routeId;
    /**
     * ----备注---
     **/
    @Column(name = "memo", columnDefinition = "nvarchar(255) NULL ")
    private String memo;


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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUseDepName() {
        return useDepName;
    }

    public void setUseDepName(String useDepName) {
        this.useDepName = useDepName;
    }

    public Integer getExtendint3() {
        return extendint3;
    }

    public void setExtendint3(Integer extendint3) {
        this.extendint3 = extendint3;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getExtendint1() {
        return extendint1;
    }

    public void setExtendint1(Integer extendint1) {
        this.extendint1 = extendint1;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getExtendint2() {
        return extendint2;
    }

    public void setExtendint2(String extendint2) {
        this.extendint2 = extendint2;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getOfficesId() {
        return officesId;
    }

    public void setOfficesId(Integer officesId) {
        this.officesId = officesId;
    }

    public String getDepartOfficename() {
        return departOfficename;
    }

    public void setDepartOfficename(String departOfficename) {
        this.departOfficename = departOfficename;
    }

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

    public String getExtendString2() {
        return extendString2;
    }

    public void setExtendString2(String extendString2) {
        this.extendString2 = extendString2;
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

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
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

    public Integer getUseddepartid() {
        return useddepartid;
    }

    public void setUseddepartid(Integer useddepartid) {
        this.useddepartid = useddepartid;
    }

    public Integer getApplydepartid() {
        return applydepartid;
    }

    public void setApplydepartid(Integer applydepartid) {
        this.applydepartid = applydepartid;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRequestUnitname() {
        return requestUnitname;
    }

    public void setRequestUnitname(String requestUnitname) {
        this.requestUnitname = requestUnitname;
    }

    public String getDbCode() {
        return dbCode;
    }

    public void setDbCode(String dbCode) {
        this.dbCode = dbCode;
    }

    public String getRenewalCostStr() {
        return RenewalCostEnum.getValue(this.extendint3);
    }

    public void setRenewalCostStr(String renewalCostStr) {
        this.renewalCostStr = renewalCostStr;
    }

    public ManageCK() {
    }


}
