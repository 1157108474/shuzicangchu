package com.zthc.ewms.system.user.entity.guard;

import com.zthc.ewms.base.util.Condition;


/**
 * ������ݿ��������
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