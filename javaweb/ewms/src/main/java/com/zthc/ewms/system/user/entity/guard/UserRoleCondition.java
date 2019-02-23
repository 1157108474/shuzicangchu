package com.zthc.ewms.system.user.entity.guard;

import com.zthc.ewms.base.util.Condition;


/**
 * 存放数据库操作条件
 */
public class UserRoleCondition extends Condition {
    private String[] roleCodes;

    public String[] getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(String[] roleCodes) {
        this.roleCodes = roleCodes;
    }

}