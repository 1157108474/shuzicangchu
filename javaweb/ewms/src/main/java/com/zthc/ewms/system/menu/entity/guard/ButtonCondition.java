package com.zthc.ewms.system.menu.entity.guard;

import com.zthc.ewms.base.util.Condition;


/**
 * ������ݿ��������
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