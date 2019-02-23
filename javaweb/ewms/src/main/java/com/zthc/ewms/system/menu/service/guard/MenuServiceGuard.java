package com.zthc.ewms.system.menu.service.guard;


import com.zthc.ewms.system.menu.dao.MenuDao;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.entity.guard.MenuCondition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class MenuServiceGuard {
    @Resource(name = "menuDao")
    public MenuDao dao;

    /**************************  基础方法   ***************************/

    //新增
    @Transactional
    public void saveMenu(Menu obj, MenuCondition condition) {
        this.dao.saveMenu(obj, condition);
    }

    //修改
    @Transactional
    public int updateMenu(Menu obj, MenuCondition condition) {
        return this.dao.updateMenu(obj, condition);
    }

    // 查
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Menu getMenuOne(String code) {
        return this.dao.getMenuOne(code);
    }

    // 删
    @Transactional
    public void delMenu(Long id) {
        this.dao.delMenu(id);
    }

}