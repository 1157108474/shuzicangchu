package com.zthc.ewms.system.user.service.guard;


import com.zthc.ewms.system.user.dao.UserScopeDao;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserScope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class UserScopeServiceGuard {
    @Resource(name = "userScopeDao")
    public UserScopeDao dao;

    /**************************  基础方法   ***************************/
    //增改
    @Transactional
    public void saveUserScope(UserScope obj) {
        this.dao.saveUserScope(obj);
    }
    @Transactional
    public void saveUserScopeUser(List<Integer> scopeIds, User user,Integer type) {
        UserScope userScope;
        for (Integer scopeId : scopeIds) {
            userScope = new UserScope();
            userScope.setGuId(user.getGuId());
            userScope.setZtId(user.getZtId());
            userScope.setPersonId(user.getId());
            userScope.setScopeId(scopeId);
            userScope.setScopeType(type);
            this.dao.saveUserScope(userScope);
        }
    }
    //查
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UserScope getUserScopeOne(Integer id) {
        return this.dao.getUserScopeOne(id);
    }

}