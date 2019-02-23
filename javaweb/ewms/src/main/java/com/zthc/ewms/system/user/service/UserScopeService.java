package com.zthc.ewms.system.user.service;


import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.entity.guard.UserScope;
import com.zthc.ewms.system.user.entity.guard.UserScopeCondition;
import com.zthc.ewms.system.user.service.guard.UserScopeServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserScopeService extends UserScopeServiceGuard {

    /**
     * 根据用户ID获取操作
     *
     * @return
     */
    @Transactional
    public <T> List<T> listUserScopes(Integer personId, String table, int scopeType) {
        return this.dao.listUserScopes(personId, table, scopeType);
    }

    /**
     * 保存操作范围
     *
     * @param obj
     * @param condition
     */
    @Transactional
    public void saveUserScopes(User obj, UserScopeCondition condition) {
        //删除
        this.dao.delUserScopes(obj.getId());
        UserScope userScope;
        //保存组织部门
        for (Integer id : condition.getDepts()) {
            userScope = new UserScope();
            userScope.setGuId(obj.getGuId());
            userScope.setZtId(obj.getZtId());
            userScope.setPersonId(obj.getId());
            userScope.setScopeId(id);
            userScope.setScopeType(UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
            this.dao.saveUserScope(userScope);
        }
        //保存库房库区
        for (Integer id : condition.getWareHouses()) {
            userScope = new UserScope();
            userScope.setGuId(obj.getGuId());
            userScope.setZtId(obj.getZtId());
            userScope.setPersonId(obj.getId());
            userScope.setScopeId(id);
            userScope.setScopeType(UserEnums.ScopeTypeEnum.WAREHOUSE.getType());
            this.dao.saveUserScope(userScope);
        }
        //保存物料范围
        for (Integer id : condition.getSpareparts()) {
            userScope = new UserScope();
            userScope.setGuId(obj.getGuId());
            userScope.setZtId(obj.getZtId());
            userScope.setPersonId(obj.getId());
            userScope.setScopeId(id);
            userScope.setScopeType(UserEnums.ScopeTypeEnum.SPAREPARTSCATE.getType());
            this.dao.saveUserScope(userScope);
        }
        //保存下辖科室
        for (Integer id : condition.getUseDeps()) {
            userScope = new UserScope();
            userScope.setGuId(obj.getGuId());
            userScope.setZtId(obj.getZtId());
            userScope.setPersonId(obj.getId());
            userScope.setScopeId(id);
            userScope.setScopeType(UserEnums.ScopeTypeEnum.USEDEP.getType());
            this.dao.saveUserScope(userScope);
        }
    }

    /**
     * 根据用户Ids和类型删除
     **/
    @Transactional
    public void delUserScopesByIdsAndType(List<Integer> personIds ,Integer scopeType) {
        this.dao.delUserScopesByIdsAndType(personIds,scopeType);
    }

    /**
     * 批量保存
     **/
    @Transactional
    public void saveAllUserScope(String sql) {
        this.dao.saveAllUserScope(sql);
    }

}
