package com.zthc.ewms.system.log.entity;
import com.zthc.ewms.base.util.Condition;

/**
 * ������ݿ��������
 *
 */
public class SystemLogCondition extends Condition{

    public Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}