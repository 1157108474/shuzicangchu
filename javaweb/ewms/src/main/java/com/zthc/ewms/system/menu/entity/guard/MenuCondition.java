package com.zthc.ewms.system.menu.entity.guard;

import com.zthc.ewms.base.util.Condition;


/**
 * ������ݿ��������
 */
public class MenuCondition extends Condition {


    private Long[] ids;
    private String[] codes;


    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }


}