package com.zthc.ewms.sheet.entity.guard;

import com.zthc.ewms.base.util.Condition;
/**
 * ������ݿ��������
 *
 */
public class SheetCondition extends Condition {
	protected String beginDate;
	protected String endDate;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}