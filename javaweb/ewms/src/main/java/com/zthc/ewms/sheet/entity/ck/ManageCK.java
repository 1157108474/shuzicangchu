package com.zthc.ewms.sheet.entity.ck;

import com.zthc.ewms.sheet.entity.enums.RenewalCostEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: ���ⵥ����
 * @Package
 */
@Entity
@Table(name = "V_CKD")
public class ManageCK {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT '����'")
    private Integer id;
    /**
     * ----����---
     **/
    @Column(name = "code", columnDefinition = "nvarchar(64) NULL COMMENT '����'")
    private String code;
    /**
     * ----��������---
     **/
    @Column(name = "typeName", columnDefinition = "nvarchar(64) NULL COMMENT 'officeName'")
    private String typeName;
    /**
     * ----��������Id---
     **/
    @Column(name = "typeId", columnDefinition = "Integer NULL COMMENT '��������Id'")
    private Integer typeId;

    /**
     * ----�����֯---
     **/
    @Column(name = "org_name", columnDefinition = "nvarchar(200) NULL COMMENT '�����֯'")
    private String orgName;

    /**
     * ----EXTENDINT3---
     **/
    @Column(name = "EXTENDINT3", columnDefinition = "Integer NULL COMMENT 'EXTENDINT3'")
    private Integer extendint3;
    @Transient
    private String renewalCostStr;

    /**
     * ----����״̬---
     **/
    @Column(name = "statusName", columnDefinition = "nvarchar(64) NULL COMMENT '����״̬'")
    private String statusName;
    /**
     * ----����״̬---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '����״̬'")
    private Integer status;
    /**
     * ----���뵥ID---
     **/
    @Column(name = "EXTENDINT1", columnDefinition = "Integer NULL COMMENT '���뵥ID'")
    private Integer extendint1;
    /**
     * ----ʹ�õ�λId---
     **/
    @Column(name = "useddepartid", columnDefinition = "Integer NULL COMMENT '���뵥ID'")
    private Integer useddepartid;
    /**
     * ----ʹ�õ�λ---
     **/
    @Column(name = "useDepName", columnDefinition = "nvarchar(200) NULL COMMENT 'ʹ�õ�λ'")
    private String useDepName;

    /**
     * ----requestUnitname---
     **/
    @Column(name = "requestUnitname", columnDefinition = "nvarchar(64) NULL COMMENT 'requestUnitname'")
    private String requestUnitname;

    /**
     * ----���뵥��---
     **/
    @Column(name = "slcode", columnDefinition = "nvarchar(64) NULL COMMENT '���뵥��'")
    private String slCode;


    /**
     * ----���뵥λ---
     **/
    @Column(name = "applydepartid", columnDefinition = "Integer NULL COMMENT '���뵥λ'")
    private Integer applydepartid;

    /**
     * ----������id---
     **/
    @Column(name = "EXTENDINT2", columnDefinition = "nvarchar(200) NULL COMMENT '������id'")
    private String extendint2;
    /**
     * ----��������---
     **/
    @Column(name = "DBCODE", columnDefinition = "nvarchar(64) NULL COMMENT '��������'")
    private String dbCode;

    /**
     * ----�Ƶ���---
     **/
    @Column(name = "personName", columnDefinition = "nvarchar(64) NULL COMMENT '�Ƶ���'")
    private String personName;
    /**
     * ----����Id---
     **/
    @Column(name = "officesId", columnDefinition = "Integer NULL COMMENT '����Id'")
    private Integer officesId;
    /**
     * ----��������---
     **/
    @Column(name = "departOfficename", columnDefinition = "nvarchar(255) NULL COMMENT '��������'")
    private String departOfficename;

    /**
     * ----ztId---
     **/
    @Column(name = "ztId", columnDefinition = "Integer NULL COMMENT 'ZtId'")
    private Integer ztId;

    /**
     * ----�ʽ���Դ---
     **/
    @Column(name = "fundsSource", columnDefinition = "Integer NULL COMMENT '�ʽ���Դ'")
    private Integer fundsSource;
    /**
     * ----��;---
     **/
    @Column(name = "extendString1", columnDefinition = "nvarchar(255) NULL ")
    private String extendString1;
    /**
     * ----extendString2---
     **/
    @Column(name = "extendString2", columnDefinition = "nvarchar(255) NULL ")
    private String extendString2;
    /**
     * ----������---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '������'")
    private Integer creator;
    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "date NULL COMMENT '����ʱ��'")
    private Date createDate;
    @Transient
    private String createDateStr;
    /**
     * ----�޸���---
     **/
    @Column(name = "updator", columnDefinition = "Integer NULL COMMENT '�޸���'")
    private Integer updator;
    /**
     * ----�޸�ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL COMMENT '�޸�ʱ��'")
    private Date updateDate;

    /**
     * ----������---
     **/
    @Column(name = "routeid", columnDefinition = "Integer NULL COMMENT '�ֵ��ID'")
    private Integer routeId;
    /**
     * ----��ע---
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
