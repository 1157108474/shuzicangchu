package com.zthc.ewms.base.page;

import java.util.List;

public class LayuiPage<T> {
    private int code;

    private String msg;

    private Long count;

    private String orderBys; //ÅÅÐòÌõ¼þ

    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }






    public String getOrderBys() {
        return orderBys;
    }

    public void setOrderBy(String orderBys) {
        this.orderBys = orderBys;
    }
}
