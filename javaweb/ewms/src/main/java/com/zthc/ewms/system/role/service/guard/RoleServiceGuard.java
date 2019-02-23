package com.zthc.ewms.system.role.service.guard;


import com.zthc.ewms.system.role.dao.RoleDao;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class RoleServiceGuard {
    @Resource(name = "roleDao")
    public RoleDao dao;

    /**************************  基础方法   ***************************/

    //新增
    @Transactional
    public void saveRole(Role obj, RoleCondition condition) {
        this.dao.saveRole(obj, condition);
    }

    //修改
    @Transactional
    public int updateRole(Role obj, RoleCondition condition) {
        return this.dao.updateRole(obj, condition);
    }
    //修改
    @Transactional
    public void update(Role obj) {
        this.dao.update(obj);
    }

    // 查
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Role getRoleOne(String roleCode) {
        return this.dao.getRoleOne(roleCode);
    }

    // 删
    @Transactional
    public void Role(String roleCode) {
        this.dao.delRole(roleCode);
    }

}