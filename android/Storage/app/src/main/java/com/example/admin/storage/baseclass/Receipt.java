package com.example.admin.storage.baseclass;

/**
 * Created by Administrator on 2018/1/30.
 */

public class Receipt {
//    public Receipt(){
//
//    }
    /*
    定义学生的构造器，创建学生对象时定义学生的信息。
     */
//    public Receipt(String image1, String image2, String carnum, String carmod, String carcolor ){
//        this.image1 = image1;
//        this.image2 = image2;
//        this.carnum = carnum;
//        this.carmod = carmod;
//        this.carcolor = carcolor;
//
//    }
    private String rid;
    private String uuid;
    private String jieshouOrder;
    /**
     *采购订单
     */
    private String order;
    private String provider;
    //订单类型
    private String orderType;
    private String fafanghao;

    private String orderNo;
    private String jieshouTime;


    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getJieshouOrder() {
        return jieshouOrder;
    }

    public void setJieshouOrder(String jieshouOrder) {
        this.jieshouOrder = jieshouOrder;
    }

    public String getJieshouTime() {
        return jieshouTime;
    }

    public void setJieshouTime(String jieshouTime) {
        this.jieshouTime = jieshouTime;
    }

    public String getFafanghao() {
        return fafanghao;
    }

    public void setFafanghao(String fafanghao) {
        this.fafanghao = fafanghao;
    }
}
