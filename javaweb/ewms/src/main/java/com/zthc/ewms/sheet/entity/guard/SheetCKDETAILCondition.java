package com.zthc.ewms.sheet.entity.guard;
import com.zthc.ewms.base.util.Condition;

/**
 * 存放数据库操作条件
 *
 */
public class SheetCKDETAILCondition extends Condition{
    protected Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}