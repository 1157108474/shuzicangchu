package com.zthc.ewms.system.dept.service.guard;

import com.zthc.ewms.system.dept.dao.DepartDao;

import com.zthc.ewms.system.dept.entity.guard.Depart;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class DepartServiceGuard {
    @Resource(name = "departDao")
    public DepartDao dao;

    /**************************  基础方法   ***************************/

    //新增
    @Transactional
    public void saveDepart(Depart obj) {
        this.dao.saveDepart(obj);
    }

    // 查
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Depart getDepartOne(Integer id) {
        return this.dao.getDepartOne(id);
    }

    // 删
    @Transactional
    public void delDepart(Long id) {
        this.dao.delDepart(id);
    }

}