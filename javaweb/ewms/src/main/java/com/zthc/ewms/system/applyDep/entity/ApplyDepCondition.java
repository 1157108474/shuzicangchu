package com.zthc.ewms.system.applyDep.entity;
import com.zthc.ewms.base.util.Condition;


/**
 * ������ݿ��������
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