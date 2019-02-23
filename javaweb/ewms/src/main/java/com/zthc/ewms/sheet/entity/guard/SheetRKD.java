package com.zthc.ewms.sheet.entity.guard;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="v_rkd")
public class SheetRKD {

    /**----id---**/
    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**----唯一标识---**/
    @Column(name="guid", columnDefinition="nvarchar2(64) NULL COMMENT '唯一标识'")
    private String guid;
    /**----单据名称---**/
    @Column(name="name", columnDefinition="nvarchar2(255) NULL COMMENT '单据名称'")
    private String name;
    /**----单据编码---**/
    @Column(name="code", columnDefinition="nvarchar2(50) NULL COMMENT '单据编码'")
    private String code;
    /**----分类ID---**/
    @Column(name="kindid", columnDefinition="Integer NULL COMMENT '分类ID'")
    private Integer kindid;
    /**----类型ID---**/
    @Column(name="typeid", columnDefinition="Integer NULL COMMENT '类型ID'")
    private Integer typeid;
    /**----DutyId---**/
    @Column(name="dutyid", columnDefinition="Integer NULL COMMENT 'DutyId'")
    private Integer dutyid;
    /**----部门id---**/
    @Column(name="departid", columnDefinition="Integer NULL COMMENT '部门id'")
    private Integer departid;
    /**
     * ----供应商id---
     **/
    @Column(name = "providerdepid", columnDefinition = "Integer NULL COMMENT '供应商id'")
    private Integer providerdepid;
    /**----字典表ID---**/
    @Column(name="routeid", columnDefinition="Integer NULL COMMENT '字典表ID'")
    private Integer routeid;
    /**----流程ID---**/
    @Column(name="route_stepid", columnDefinition="Integer NULL COMMENT '流程ID'")
    private Integer routeStepid;
    /**----角色ID---**/
    @Column(name="roleid", columnDefinition="Integer NULL COMMENT '角色ID'")
    private Integer roleid;
    /**----RelateSheet---**/
    @Column(name="relatesheet", columnDefinition="Integer NULL COMMENT 'RelateSheet'")
    private Integer relatesheet;
    /**----提交人ID---**/
    @Column(name="submitmanid", columnDefinition="Integer NULL COMMENT '提交人ID'")
    private Integer submitmanid;
    /**----提交时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="submittime", columnDefinition="datetime NULL COMMENT '提交时间'")
    private Date submittime;
    /**----单据状态---**/
    @Column(name="status", columnDefinition="Integer NULL COMMENT '单据状态'")
    private Integer status;
    /**----备注---**/
    @Column(name="memo", columnDefinition="nvarchar2(255) NULL COMMENT '备注'")
    private String memo;
    /**----创建人ID---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人ID'")
    private Integer creator;
    /**----创建时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createdate", columnDefinition="datetime NULL COMMENT '创建时间'")
    private Date createdate;
    /**----采购订单编号---**/
    @Column(name="ordernum", columnDefinition="nvarchar2(200) NULL COMMENT '采购订单编号'")
    private String ordernum;
    /**----接收单号---**/
    @Column(name="receivenum", columnDefinition="nvarchar2(200) NULL COMMENT '接收单号'")
    private String receivenum;
    /**----单据状态NAME---**/
    @Column(name="statusname", columnDefinition="nvarchar2(255) NULL COMMENT '单据状态NAME'")
    private String statusname;
    /**----制单人---**/
    @Column(name="personname", columnDefinition="nvarchar2(255) NULL COMMENT '制单人'")
    private String personname;
    /**---库存组织名称---**/
    @Column(name="depname", columnDefinition="nvarchar2(128) NULL COMMENT '库存组织'")
    private String depname;
    /**----供应商名称---**/
    @Column(name="extendstring1", columnDefinition="nvarchar2(255) NULL COMMENT '供应商名称'")
    private String extendstring1;
    /**----库存组织名称---**/
    @Column(name="extendstring2", columnDefinition="nvarchar2(255) NULL COMMENT '库存组织名称'")
    private String extendstring2;
    /**----订单类型---**/
    @Column(name="extendstring3", columnDefinition="nvarchar2(255) NULL COMMENT '订单类型'")
    private String extendstring3;
    /**----业务实体---**/
    @Column(name="extendstring4", columnDefinition="nvarchar2(255) NULL COMMENT '业务实体'")
    private String extendstring4;
    /**----备用string字段---**/
    @Column(name="extendstring5", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring5;
    /**----库存组织编码---**/
    @Column(name="ztid", columnDefinition="Integer NULL COMMENT '库存组织编码'")
    private Integer ztid;
    /**----采购订单ID---**/
    @Column(name="extendint1", columnDefinition="Integer NULL COMMENT '采购订单ID'")
    private Integer extendint1;
    /**----备用int字段---**/
    @Column(name="extendint2", columnDefinition="Integer NULL COMMENT '备用int字段'")
    private Integer extendint2;
    /**----调拨单号---**/
    @Column(name="dbnum", columnDefinition="nvarchar2(255) NULL COMMENT '调拨单号'")
    private String dbnum;
    /**----入库类型名称---**/
    @Column(name="typename", columnDefinition="nvarchar2(255) NULL COMMENT '入库类型名称'")
    private String typename;
    /**----ERP接收单号---**/
    @Column(name="extendstring6", columnDefinition="nvarchar2(255) NULL COMMENT 'ERP接收单号'")
    private String extendstring6;

    @Transient
    private String createDateStr;


    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    /**
     * ----????URL---
     **/
    @Column(name = "url")
    private String url;

    public SheetRKD() {
    }

    public Integer getProviderdepid() {
        return providerdepid;
    }

    public void setProviderdepid(Integer providerdepid) {
        this.providerdepid = providerdepid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getKindid() {
        return kindid;
    }

    public void setKindid(Integer kindid) {
        this.kindid = kindid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getDutyid() {
        return dutyid;
    }

    public void setDutyid(Integer dutyid) {
        this.dutyid = dutyid;
    }

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public Integer getRouteid() {
        return routeid;
    }

    public void setRouteid(Integer routeid) {
        this.routeid = routeid;
    }

    public Integer getRouteStepid() {
        return routeStepid;
    }

    public void setRouteStepid(Integer routeStepid) {
        this.routeStepid = routeStepid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getRelatesheet() {
        return relatesheet;
    }

    public void setRelatesheet(Integer relatesheet) {
        this.relatesheet = relatesheet;
    }

    public Integer getSubmitmanid() {
        return submitmanid;
    }

    public void setSubmitmanid(Integer submitmanid) {
        this.submitmanid = submitmanid;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getReceivenum() {
        return receivenum;
    }

    public void setReceivenum(String receivenum) {
        this.receivenum = receivenum;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getExtendstring1() {
        return extendstring1;
    }

    public void setExtendstring1(String extendstring1) {
        this.extendstring1 = extendstring1;
    }

    public String getExtendstring2() {
        return extendstring2;
    }

    public void setExtendstring2(String extendstring2) {
        this.extendstring2 = extendstring2;
    }

    public String getExtendstring3() {
        return extendstring3;
    }

    public void setExtendstring3(String extendstring3) {
        this.extendstring3 = extendstring3;
    }

    public String getExtendstring4() {
        return extendstring4;
    }

    public void setExtendstring4(String extendstring4) {
        this.extendstring4 = extendstring4;
    }

    public String getExtendstring5() {
        return extendstring5;
    }

    public void setExtendstring5(String extendstring5) {
        this.extendstring5 = extendstring5;
    }

    public Integer getZtid() {
        return ztid;
    }

    public void setZtid(Integer ztid) {
        this.ztid = ztid;
    }

    public Integer getExtendint1() {
        return extendint1;
    }

    public void setExtendint1(Integer extendint1) {
        this.extendint1 = extendint1;
    }

    public Integer getExtendint2() {
        return extendint2;
    }

    public void setExtendint2(Integer extendint2) {
        this.extendint2 = extendint2;
    }

    public String getDbnum() {
        return dbnum;
    }

    public void setDbnum(String dbnum) {
        this.dbnum = dbnum;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getExtendstring6() {
        return extendstring6;
    }

    public void setExtendstring6(String extendstring6) {
        this.extendstring6 = extendstring6;
    }

    ;
}
