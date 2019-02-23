package com.zthc.ewms.sheet.entity.guard;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "v_jsd")
public class ManageOrder {

    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT 'id'")
    private Integer id;

    /**----唯一标识---**/
    @Column(name="guid", columnDefinition="nvarchar2(36) NULL COMMENT '唯一标识'")
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

    /**----更新人ID---**/
    @Column(name="updator", columnDefinition="Integer NULL COMMENT '更新人ID'")
    private Integer updator;

    /**----更新时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="updatedate", columnDefinition="datetime NULL COMMENT '更新时间'")
    private Date updatedate;

    /**----库存组织编码---**/
    @Column(name="ztid", columnDefinition="Integer NULL COMMENT '库存组织编码'")
    private Integer ztid;

    /**
     * ----采购订单ID---
     **/
    @Column(name="extendint1", columnDefinition="Integer NULL COMMENT '备用int字段'")
    private Integer extendint1;

    /**----供应商名称---**/
    @Column(name="extendstring1", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring1;

    /**----库存组织名称---**/
    @Column(name="extendstring2", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring2;

    /**----订单类型---**/
    @Column(name="extendstring3", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring3;

    /**----备用string字段---**/
    @Column(name="extendstring4", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring4;

    /**----发放号---**/
    @Column(name="extendstring5", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring5;

    /**----行号---**/
    @Column(name="extendstring10", columnDefinition="nvarchar2(255) NULL COMMENT '行号'")
    private String extendString10;

    /**----采购订单编号---**/
    @Column(name="ordernum", columnDefinition="nvarchar2(200) NULL COMMENT '采购订单编号'")
    private String ordernum;

    /**----供应商ID---**/
    @Column(name="providerdepid", columnDefinition="Integer NULL COMMENT '供应商ID'")
    private Integer providerdepid;

    /**----制单人---**/
    @Column(name="personname", columnDefinition="nvarchar2(128) NULL COMMENT '制单人'")
    private String personname;

    /**----单据状态---**/
    @Column(name="statusname", columnDefinition="nvarchar2(128) NULL COMMENT '单据状态'")
    private String statusname;

    /**---库存组织名称---**/
    @Column(name="depname", columnDefinition="nvarchar2(128) NULL COMMENT '库存组织'")
    private String depname;

    @Transient
    private String createDateStr;

    @Column(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
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

    public String getExtendString10() {
        return extendString10;
    }

    public void setExtendString10(String extendString10) {
        this.extendString10 = extendString10;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getProviderdepid() {
        return providerdepid;
    }

    public void setProviderdepid(Integer providerdepid) {
        this.providerdepid = providerdepid;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public ManageOrder() {
    }
}
