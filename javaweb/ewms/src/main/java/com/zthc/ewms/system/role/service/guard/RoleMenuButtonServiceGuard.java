package com.zthc.ewms.system.role.service.guard;


import com.zthc.ewms.system.role.dao.RoleMenuButtonDao;
import com.zthc.ewms.system.role.entity.guard.RoleMenuButton;
import com.zthc.ewms.system.role.entity.guard.RoleMenuButtonCondition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class RoleMenuButtonServiceGuard {
    @Resource(name = "roleMenuButtonDao")
    public RoleMenuButtonDao dao;

    /**************************  基础方法   ***************************/

    //新增
    @Transactional
    public void saveRoleMenuButton(RoleMenuButton obj, RoleMenuButtonCondition condition) {
        this.dao.saveRoleMenuButton(obj, condition);
    }

    // 查
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RoleMenuButton getRoleMenuButtonOne(String RoleMenuButtonCode) {
        return this.dao.getRoleMenuButtonOne(RoleMenuButtonCode);
    }

    // 删
    @Transactional
    public void RoleMenuButton(String RoleMenuButtonCode) {
        this.dao.delRoleMenuButton(RoleMenuButtonCode);
    }

}