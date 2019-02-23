package com.zthc.ewms.system.role.entity.guard;


import com.zthc.ewms.base.util.Condition;

/**
 * 存放数据库操作条件
 */
public class RoleMenuButtonCondition extends Condition {


    private String[] menuIds;
    private String[] buttonIds;

    public String[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String[] menuIds) {
        this.menuIds = menuIds;
    }

    public String[] getButtonIds() {
        return buttonIds;
    }

    public void setButtonIds(String[] buttonIds) {
        this.buttonIds = buttonIds;
    }
}