package com.zthc.ewms.system.user.service.guard;


import com.zthc.ewms.system.useDep.service.UseDepService;
import com.zthc.ewms.system.user.dao.UserDao;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserCondition;
import com.zthc.ewms.system.user.entity.guard.UserEnums.ScopeTypeEnum;
import com.zthc.ewms.system.user.service.UserScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class UserServiceGuard {
    @Resource(name = "userDao")
    public UserDao dao;
    @Autowired
    private UseDepService useDepService;
    @Autowired
    private UserScopeService userScopeService;

    /**************************  基础方法   ***************************/
    //增改
    @Transactional
    public void saveUser(User obj) {
        this.dao.saveUser(obj);
        List<Integer> scopeIds = useDepService.listOfficeScopeByOfficesId(obj.getOfficesId());
        this.userScopeService.saveUserScopeUser(scopeIds,obj, ScopeTypeEnum.SPAREPARTSCATE.getType());
    }

    //查
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public User getUserOne(Integer id) {
        return this.dao.getUserOne(id);
    }

    public User getUserOneCK(Integer id) {
        return this.dao.getUserOne(id);
    }

    //删
    @Transactional
    public void delUser(Integer id) {
        this.dao.delUser(id);
    }

    /**
     * 修改方法
     *
     * @return
     */
    @Transactional
    public int updateUser(User user) {
        List<Integer> scopeIds = useDepService.listOfficeScopeByOfficesId(user.getOfficesId());
        this.userScopeService.saveUserScopeUser(scopeIds,user, ScopeTypeEnum.SPAREPARTSCATE.getType());
        return this.dao.updateUser(user);
    }

    /**
     * 批量删除
     *
     * @param condition
     * @return
     */
    public int delUsers(UserCondition condition) {
        return this.dao.delUsers(condition);
    }

    /**
     * 修改方法
     *
     * @return
     */
    @Transactional
    public void updateUserPassword(User user) {
        this.dao.updateUserPassword(user);
    }

}