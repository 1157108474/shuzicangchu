package com.zthc.ewms.sheet.entity.guard;
import com.zthc.ewms.base.util.Condition;

import java.util.Date;
/**
 * ������ݿ��������
 *
 */
public class SheetDetailCondition extends Condition {

    protected Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}