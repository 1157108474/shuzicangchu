package com.zthc.ewms.system.dept.entity.guard;


import com.zthc.ewms.base.util.Condition;

/**
 * 存放数据库操作条件
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