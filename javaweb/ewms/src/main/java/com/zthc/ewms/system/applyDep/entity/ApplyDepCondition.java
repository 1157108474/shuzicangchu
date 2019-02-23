package com.zthc.ewms.system.applyDep.entity;
import com.zthc.ewms.base.util.Condition;


/**
 * 存放数据库操作条件
 *
 */
public class ApplyDepCondition extends Condition{

    public Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}