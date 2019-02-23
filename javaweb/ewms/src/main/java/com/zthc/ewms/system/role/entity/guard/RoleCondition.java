package com.zthc.ewms.system.role.entity.guard;


import com.zthc.ewms.base.util.Condition;

/**
 * 存放数据库操作条件
 */
public class RoleCondition extends Condition {

    private String[] roleCodes;

    public String[] getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(String[] roleCodes) {
        this.roleCodes = roleCodes;
    }
}