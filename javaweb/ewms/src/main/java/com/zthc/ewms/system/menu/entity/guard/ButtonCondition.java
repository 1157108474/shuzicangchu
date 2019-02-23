package com.zthc.ewms.system.menu.entity.guard;

import com.zthc.ewms.base.util.Condition;


/**
 * 存放数据库操作条件
 */
public class ButtonCondition extends Condition {

    private String[] codes;

    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }
}