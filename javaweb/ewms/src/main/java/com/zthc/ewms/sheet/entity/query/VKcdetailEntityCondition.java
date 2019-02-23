package com.zthc.ewms.sheet.entity.query;
import com.zthc.ewms.base.util.Condition;

/**
 * 存放数据库操作条件
 *
 */
public class VKcdetailEntityCondition extends Condition{

    private Long startarea;

    private Long endarea;

    public Long getStartarea() {
        return startarea;
    }

    public void setStartarea(Long startarea) {
        this.startarea = startarea;
    }

    public Long getEndarea() {
        return endarea;
    }

    public void setEndarea(Long endarea) {
        this.endarea = endarea;
    }

}