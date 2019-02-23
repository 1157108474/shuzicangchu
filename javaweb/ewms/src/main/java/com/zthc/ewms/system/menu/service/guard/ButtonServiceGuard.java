package com.zthc.ewms.system.menu.service.guard;

import com.zthc.ewms.system.menu.dao.ButtonDao;
import com.zthc.ewms.system.menu.entity.guard.Button;
import com.zthc.ewms.system.menu.entity.guard.ButtonCondition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class ButtonServiceGuard {
    @Resource(name = "buttonDao")
    public ButtonDao dao;

    /**************************  基础方法   ***************************/

    //新增
    @Transactional
    public void saveButton(Button obj) {
        this.dao.saveButton(obj);
    }

    //修改
    @Transactional
    public int updateButton(Button obj, ButtonCondition condition) {
        return this.dao.updateButton(obj, condition);
    }

    // 查
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Button getButtonOne(String id) {
        return this.dao.getButtonOne(id);
    }

    // 删
    @Transactional
    public void delButton(Long id) {
        this.dao.delButton(id);
    }

}