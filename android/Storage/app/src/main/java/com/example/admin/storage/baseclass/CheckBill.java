package com.example.admin.storage.baseclass;

/**
 * Created by shang on 2018/4/24.
 */

public class CheckBill {
    private int id;
    private String uuid;
    private String orderNumber;
    private String creator;
    private String storage;
    private String totalNum;
    private String alreadyNum;
    private String createTime;




    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getAlreadyNum() {
        return alreadyNum;
    }

    public void setAlreadyNum(String alreadyNum) {
        this.alreadyNum = alreadyNum;
    }
}
