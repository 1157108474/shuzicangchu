package com.zthc.ewms.system.user.entity.guard;

import com.zthc.ewms.base.util.Condition;


/**
 * 存放数据库操作条件
 */
public class UserScopeCondition extends Condition {
    private Integer[] depts;
    private Integer[] spareparts;
    private Integer[] wareHouses;
    private Integer[] useDeps;

    public Integer[] getDepts() {
        return depts;
    }

    public void setDepts(Integer[] depts) {
        this.depts = depts;
    }

    public Integer[] getSpareparts() {
        return spareparts;
    }

    public void setSpareparts(Integer[] spareparts) {
        this.spareparts = spareparts;
    }

    public Integer[] getWareHouses() {
        return wareHouses;
    }

    public void setWareHouses(Integer[] wareHouses) {
        this.wareHouses = wareHouses;
    }

    public Integer[] getUseDeps() {
        return useDeps;
    }

    public void setUseDeps(Integer[] useDeps) {
        this.useDeps = useDeps;
    }
}