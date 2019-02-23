package com.zthc.ewms.sheet.entity.guard;

import com.zthc.ewms.base.util.Condition;

/**
 * 存放数据库操作条件
 *
 */
public class SheetRKCondition extends Condition{
    private Integer sheetId;

    private String providerName;

    private String startTime;

    private String detailsGUID;

    private String endTime;

    private Integer uid;

    public Integer getSheetId() {
        return sheetId;
    }

    public void setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDetailsGUID() {
        return detailsGUID;
    }

    public void setDetailsGUID(String detailsGUID) {
        this.detailsGUID = detailsGUID;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}