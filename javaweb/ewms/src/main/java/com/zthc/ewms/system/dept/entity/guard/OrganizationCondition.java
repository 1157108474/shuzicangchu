package com.zthc.ewms.system.dept.entity.guard;


import com.zthc.ewms.base.util.Condition;

/**
 * ������ݿ��������
 */
public class OrganizationCondition extends Condition {
    private Long[] ids;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}