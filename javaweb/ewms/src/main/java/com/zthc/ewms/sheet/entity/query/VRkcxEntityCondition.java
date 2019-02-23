package com.zthc.ewms.sheet.entity.query;
import com.zthc.ewms.base.util.Condition;

/**
 * 存放数据库操作条件
 *
 */
public class VRkcxEntityCondition extends Condition{

    private String startTime;

    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}