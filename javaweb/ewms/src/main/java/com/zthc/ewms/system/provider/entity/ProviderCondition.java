package com.zthc.ewms.system.provider.entity;
import com.zthc.ewms.base.util.Condition;

/**
 * ������ݿ��������
 *
 */
public class ProviderCondition extends Condition{
    public Integer[] ids;

    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}