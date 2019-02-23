package com.zthc.ewms.sheet.entity.apply;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "V_PRINT_LLD")
public class ApplyPrint {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;


    @Column(name = "BMPERSON", columnDefinition = "nvarchar2(64) NULL")
    private String bmperson;
    @Column(name = "ZNPERSON", columnDefinition = "nvarchar2(64) NULL")
    private String znperson;
    @Column(name = "FGPERSON", columnDefinition = "nvarchar2(64) NULL")
    private String fgperson;
    /**
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT '唯一标识'")
    private String guid;
    /**
     * ----单据名称---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(255) NULL COMMENT '单据名称'")
    private String name;
    /**
     * ----单据编码---
     **/
    @Column(name = "code", columnDefinition = "nvarchar2(50) NULL COMMENT '单据编码'")
    private String code;
    /**
     * ----usedDepartId---
     **/
    @Column(name = "usedDepartId", columnDefinition = "Integer NULL COMMENT 'usedDepartId'")
    private Integer usedDepartId;

    /**
     * ----采购订单编号---
     **/
    @Column(name = "orderNum", columnDefinition = "nvarchar2(200) NULL COMMENT '采购订单编号'")
    private String orderNum;

    /**
     * ----制单人---
     **/
    @Column(name = "personName", columnDefinition = "nvarchar2(255) NULL COMMENT '制单人'")
    private String personName;
    /**
     * ----单据状态NAME---
     **/
    @Column(name = "statusName", columnDefinition = "nvarchar2(255) NULL COMMENT '单据状态NAME'")
    private String statusName;
    /**
     * ---库存组织名称---
     **/
    @Column(name = "depName", columnDefinition = "nvarchar2(128) NULL COMMENT '库存组织'")
    private String depName;
    /**
     * ---申请单位---
     **/
    @Column(name = "APPLYUNITNAME", columnDefinition = "nvarchar2(128) NULL COMMENT '库存组织'")
    private String applyUnitName;
    /**
     * ---使用单位---
     **/
    @Column(name = "USEUNITNAME", columnDefinition = "nvarchar2(128) NULL ")
    private String useunitName;
    /**
     * ---资金来源---
     **/
    @Column(name = "FUNDSNAME", columnDefinition = "nvarchar2(128) NULL ")
    private String fundsName;
    /**
     * ----供应商id---
     **/
    @Column(name = "providerDepId", columnDefinition = "Integer NULL COMMENT '供应商id'")
    private Integer providerDepId;
    /**
     * ----接收单ID---
     **/
    @Column(name = "extendInt1", columnDefinition = "Integer NULL COMMENT '接收单ID'")
    private Integer extendInt1;

    /**
     * ----分类ID---
     **/
    @Column(name = "kindId", columnDefinition = "Integer NULL COMMENT '分类ID'")
    private Integer kindId;
    /**
     * ----类型ID---
     **/
    @Column(name = "typeId", columnDefinition = "Integer NULL COMMENT '类型ID'")
    private Integer typeId;
    /**
     * ----DutyId---
     **/
    @Column(name = "dutyId", columnDefinition = "Integer NULL COMMENT 'DutyId'")
    private Integer dutyId;
    /**
     * ----部门id---
     **/
    @Column(name = "departId", columnDefinition = "Integer NULL COMMENT '部门id'")
    private Integer departId;
    /**
     * ----字典表ID---
     **/
    @Column(name = "routeId", columnDefinition = "Integer NULL COMMENT '字典表ID'")
    private Integer routeId;
    /**
     * ----流程ID---
     **/
    @Column(name = "route_stepid", columnDefinition = "Integer NULL COMMENT '流程ID'")
    private Integer routeStepid;
    /**
     * ----角色ID---
     **/
    @Column(name = "roleId", columnDefinition = "Integer NULL COMMENT '角色ID'")
    private Integer roleId;
    /**
     * ----RelateSheet---
     **/
    @Column(name = "relateSheet", columnDefinition = "Integer NULL COMMENT 'RelateSheet'")
    private Integer relateSheet;
    /**
     * ----提交人ID---
     **/
    @Column(name = "submitManId", columnDefinition = "Integer NULL COMMENT '提交人ID'")
    private Integer submitManId;
    /**
     * ----提交时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "submitTime", columnDefinition = "datetime NULL COMMENT '提交时间'")
    private Date submitTime;
    /**
     * ----单据状态---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '单据状态'")
    private Integer status;
    /**
     * ----备注---
     **/
    @Column(name = "memo", columnDefinition = "nvarchar2(255) NULL COMMENT '备注'")
    private String memo;
    /**
     * ----创建人ID---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '创建人ID'")
    private Integer creator;

    @Transient
    private String createDateStr;
    /**
     * ----创建时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "datetime NULL COMMENT '创建时间'")
    private Date createDate;

    /**
     * ----更新人ID---
     **/
    @Column(name = "updator", columnDefinition = "Integer NULL COMMENT '更新人ID'")
    private Integer updator;
    /**
     * ----更新时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "datetime NULL COMMENT '更新时间'")
    private Date updateDate;
    /**
     * ----库存组织编码---
     **/
    @Column(name = "ztId", columnDefinition = "Integer NULL COMMENT '库存组织编码'")
    private Integer ztId;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString1", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString1;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString2", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString2;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString3", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString3;
    /**
     * ----备用string字段---
     **/
    @Column(name = "extendString4", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString4;
    /**
     * ----备用string字段---
     *
    @Column(name = "extendString5", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendString5;*/


    public String getBmperson() {
        return bmperson;
    }

    public void setBmperson(String bmperson) {
        this.bmperson = bmperson;
    }

    public String getZnperson() {
        return znperson;
    }

    public void setZnperson(String znperson) {
        this.znperson = znperson;
    }

    public String getFgperson() {
        return fgperson;
    }

    public void setFgperson(String fgperson) {
        this.fgperson = fgperson;
    }

    public Integer getUsedDepartId() {
        return usedDepartId;
    }

    public void setUsedDepartId(Integer usedDepartId) {
        this.usedDepartId = usedDepartId;
    }


    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

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

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
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

    public Integer getRouteStepid() {
        return routeStepid;
    }

    public void setRouteStepid(Integer routeStepid) {
        this.routeStepid = routeStepid;
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

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
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



    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

/*    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }*/

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public Integer getProviderDepId() {
        return providerDepId;
    }

    public void setProviderDepId(Integer providerDepId) {
        this.providerDepId = providerDepId;
    }

    public Integer getExtendInt1() {
        return extendInt1;
    }

    public void setExtendInt1(Integer extendInt1) {
        this.extendInt1 = extendInt1;
    }

    public String getUseunitName() {
        return useunitName;
    }

    public void setUseunitName(String useunitName) {
        this.useunitName = useunitName;
    }

    public String getFundsName() {
        return fundsName;
    }

    public void setFundsName(String fundsName) {
        this.fundsName = fundsName;
    }

    public String getApplyUnitName() {
        return applyUnitName;
    }

    public void setApplyUnitName(String applyUnitName) {
        this.applyUnitName = applyUnitName;
    }

    public ApplyPrint() {
    }
}
