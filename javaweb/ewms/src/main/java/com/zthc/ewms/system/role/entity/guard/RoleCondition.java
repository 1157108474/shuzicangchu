package com.zthc.ewms.system.role.entity.guard;


import com.zthc.ewms.base.util.Condition;

/**
 * ������ݿ��������
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