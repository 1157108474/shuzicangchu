package com.zthc.ewms.base.util;

public abstract class Condition {

    protected String orderBys;
    protected int pageNum = 1;
    protected int pageTotal = 20;
    protected String queryCriteria;
//    protected int page = 1;
//    protected int limit = 10;

    public Condition() {
    }

    public String getOrderBys() {
        return this.orderBys;
    }

    public void setOrderBys(String orderBys) {
        this.orderBys = orderBys;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageTotal() {
        return this.pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }


    public void setPage(int page) {
//        this.page = page;
        this.pageNum = page;
    }


    public void setLimit(int limit) {
//        this.limit = limit;
        this.pageTotal = limit;
    }

    public String getQueryCriteria() {
        return queryCriteria;
    }

    public void setQueryCriteria(String queryCriteria) {
        this.queryCriteria = queryCriteria;
    }
}
