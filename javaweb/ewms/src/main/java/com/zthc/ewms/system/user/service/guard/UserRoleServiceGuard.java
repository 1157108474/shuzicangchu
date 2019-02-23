package com.zthc.ewms.system.user.service.guard;


import com.zthc.ewms.system.user.dao.UserRoleDao;
import com.zthc.ewms.system.user.entity.guard.UserRole;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class UserRoleServiceGuard {
    @Resource(name = "userRoleDao")
    public UserRoleDao dao;

    /**************************  基础方法   ***************************/
    //增改
    @Transactional
    public void saveUserRole(UserRole obj) {
        this.dao.saveUserRole(obj);
    }

    //查
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UserRole getUserRoleOne(Long id) {
        return this.dao.getUserRoleOne(id);
    }

    //删
    @Transactional
    public void delUserRole(Long id) {
        this.dao.delUserRole(id);
    }

}