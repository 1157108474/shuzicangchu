package com.zthc.ewms.sheet.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="v_orderinfo")
public class Order {

    @Id
    @Column(name = "id", columnDefinition = "nvarchar2(50) NULL COMMENT 'id'")
    private String id;
    /**----erpId---**/
    @Column(name="erpid", columnDefinition="nvarchar2(50) NULL COMMENT 'erpId'")
    private String erpid;
    /**----采购订单编码---**/
    @Column(name="ordernum", columnDefinition="nvarchar2(50) NULL COMMENT '采购订单编码'")
    private String ordernum;

    /**----供应商名称---**/
    @Column(name="providerdepname", columnDefinition="nvarchar2(250) NULL COMMENT '供应商名称'")
    private String providerdepname;

    /**----库房组织ID---**/
    @Column(name="stockorgid", columnDefinition="Integer NULL COMMENT '库房组织ID'")
    private Integer stockorgid;

    /**----订单类型---**/
    @Column(name = "ordertype", columnDefinition = "varchar2(50) NULL COMMENT '订单类型'")
    private String ordertype;

    /**----发放号---**/
    @Column(name="issuecode", columnDefinition="Integer NULL COMMENT '发放号'")
    private Integer issuecode;

    /**----组织名称---**/
    @Column(name = "stockorgname", columnDefinition = "nvarchar2(128) NULL COMMENT '名称'")
    private String stockorgname;

    /**----供应商ID---**/
    @Column(name="providerdepid", columnDefinition="Integer NULL COMMENT '供应商ID'")
    private Integer providerdepid;

    /**----采购数量---**/
    @Column(name="allcount", columnDefinition="Integer NULL COMMENT '采购数量'")
    private Integer allcount;

    /**----可接收数量---**/
    @Column(name="canusedcount", columnDefinition="Integer NULL COMMENT '可接收数量'")
    private Integer canusedcount;
    @Column(name="fyid")
    private Integer fyid;

    @Column(name="orderId")
    private Integer orderId;


    @Column(name="issueid")
    private Integer issueid;



    @Column(name="ERPROWNUM")
    private String erpRowNum;

    @Column(name="ORDERROWID")
    private String orderRowId;


    /*private Integer orderInfoId;*/


    public String getErpid() {
        return erpid;
    }

    public void setErpid(String erpid) {
        this.erpid = erpid;
    }

    public Integer getFyid() {
        return fyid;
    }

    public void setFyid(Integer fyid) {
        this.fyid = fyid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getErpRowNum() {
        return erpRowNum;
    }

    public void setErpRowNum(String erpRowNum) {
        this.erpRowNum = erpRowNum;
    }

    public String getOrderRowId() {
        return orderRowId;
    }

    public void setOrderRowId(String orderRowId) {
        this.orderRowId = orderRowId;
    }

    public Integer getIssueid() {
        return issueid;
    }

    public void setIssueid(Integer issueid) {
        this.issueid = issueid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getProviderdepname() {
        return providerdepname;
    }

    public void setProviderdepname(String providerdepname) {
        this.providerdepname = providerdepname;
    }

    public Integer getStockorgid() {
        return stockorgid;
    }

    public void setStockorgid(Integer stockorgid) {
        this.stockorgid = stockorgid;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public Integer getIssuecode() {
        return issuecode;
    }

    public void setIssuecode(Integer issuecode) {
        this.issuecode = issuecode;
    }

    public String getStockorgname() {
        return stockorgname;
    }

    public void setStockorgname(String stockorgname) {
        this.stockorgname = stockorgname;
    }

    public Integer getProviderdepid() {
        return providerdepid;
    }

    public void setProviderdepid(Integer providerdepid) {
        this.providerdepid = providerdepid;
    }

    public Integer getAllcount() {
        return allcount;
    }

    public void setAllcount(Integer allcount) {
        this.allcount = allcount;
    }

    public Integer getCanusedcount() {
        return canusedcount;
    }

    public void setCanusedcount(Integer canusedcount) {
        this.canusedcount = canusedcount;
    }

    /*public Integer getOrderInfoId(){ return orderInfoId;}

    public void setOrderInfoId(Integer infoId){ this.orderInfoId = infoId;}*/

    public Order() {
    }

    public Order(String id,String erpid, String ordernum, String providerdepname, Integer stockorgid, String ordertype, Integer
            issuecode, String stockorgname, Integer providerdepid) {
        this.id = id;
        this.erpid = erpid;
        this.ordernum = ordernum;
        this.providerdepname = providerdepname;
        this.stockorgid = stockorgid;
        this.ordertype = ordertype;
        this.issuecode = issuecode;
        this.stockorgname = stockorgname;
        this.providerdepid = providerdepid;

    }
    public Order(String id) {
        this.id = id;
    }
}
