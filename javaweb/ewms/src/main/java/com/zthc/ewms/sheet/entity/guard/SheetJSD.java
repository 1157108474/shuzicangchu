package com.zthc.ewms.sheet.entity.guard;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="v_jsdmore")
public class SheetJSD {

    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**----接收单号---**/
    @Column(name="code", columnDefinition="nvarchar2(50) NULL COMMENT '单据编码'")
    private String code;
    /**----采购订单编号---**/
    @Column(name="ordernum", columnDefinition="nvarchar2(200) NULL COMMENT '采购订单编号'")
    private String ordernum;
    /**----供应商名称---**/
    @Column(name="extendstring1", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring1;
    /**----订单类型---**/
    @Column(name="extendstring3", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring3;
    /**----创建时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createdate", columnDefinition="datetime NULL COMMENT '创建时间'")
    private Date createdate;
    /**----库存组织编码---**/
    @Column(name="ztid", columnDefinition="Integer NULL COMMENT '库存组织编码'")
    private Integer ztid;
    /**----库存组织名称---**/
    @Column(name="extendstring2", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring2;
    /**----创建人ID---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人ID'")
    private Integer creator;
    /**----发放号---**/
    @Column(name="extendstring5", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
    private String extendstring5;
    /**----供应商ID---**/
    @Column(name="providerdepid", columnDefinition="Integer NULL COMMENT '供应商ID'")
    private Integer providerdepid;
    /**----接收数量---**/
    @Column(name="jscount", columnDefinition="Integer NULL COMMENT '接收数量'")
    private Integer jscount;
    /**----入库数量---**/
    @Column(name="rkcoumt", columnDefinition="Integer NULL COMMENT '入库数量'")
    private Integer rkcoumt;


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

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getExtendstring1() {
        return extendstring1;
    }

    public void setExtendstring1(String extendstring1) {
        this.extendstring1 = extendstring1;
    }

    public String getExtendstring3() {
        return extendstring3;
    }

    public void setExtendstring3(String extendstring3) {
        this.extendstring3 = extendstring3;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getZtid() {
        return ztid;
    }

    public void setZtid(Integer ztid) {
        this.ztid = ztid;
    }

    public String getExtendstring2() {
        return extendstring2;
    }

    public void setExtendstring2(String extendstring2) {
        this.extendstring2 = extendstring2;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getExtendstring5() {
        return extendstring5;
    }

    public void setExtendstring5(String extendstring5) {
        this.extendstring5 = extendstring5;
    }

    public Integer getProviderdepid() {
        return providerdepid;
    }

    public void setProviderdepid(Integer providerdepid) {
        this.providerdepid = providerdepid;
    }

    public Integer getJscount() {
        return jscount;
    }

    public void setJscount(Integer jscount) {
        this.jscount = jscount;
    }

    public Integer getRkcoumt() {
        return rkcoumt;
    }

    public void setRkcoumt(Integer rkcoumt) {
        this.rkcoumt = rkcoumt;
    }

    public SheetJSD() {
    }
}
