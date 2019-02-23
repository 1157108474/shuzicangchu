package com.zthc.ewms.system.user.entity.guard;


import com.zthc.ewms.base.util.Condition;

/**
 * 存放数据库操作条件
 */
public class UserCondition extends Condition {

    private Integer[] ids;
    private Integer departId;
    private String departName;
    private String source;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int getPageTotal() {
        return pageTotal;
    }

    @Override
    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
}