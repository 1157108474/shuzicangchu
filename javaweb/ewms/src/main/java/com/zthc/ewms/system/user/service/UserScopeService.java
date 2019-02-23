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
     * �����û�ID��ȡ����
     *
     * @return
     */
    @Transactional
    public <T> List<T> listUserScopes(Integer personId, String table, int scopeType) {
        return this.dao.listUserScopes(personId, table, scopeType);
    }

    /**
     * ���������Χ
     *
     * @param obj
     * @param condition
     */
    @Transactional
    public void saveUserScopes(User obj, UserScopeCondition condition) {
        //ɾ��
        this.dao.delUserScopes(obj.getId());
        UserScope userScope;
        //������֯����
        for (Integer id : condition.getDepts()) {
            userScope = new UserScope();
            userScope.setGuId(obj.getGuId());
            userScope.setZtId(obj.getZtId());
            userScope.setPersonId(obj.getId());
            userScope.setScopeId(id);
            userScope.setScopeType(UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
            this.dao.saveUserScope(userScope);
        }
        //����ⷿ����
        for (Integer id : condition.getWareHouses()) {
            userScope = new UserScope();
            userScope.setGuId(obj.getGuId());
            userScope.setZtId(obj.getZtId());
            userScope.setPersonId(obj.getId());
            userScope.setScopeId(id);
            userScope.setScopeType(UserEnums.ScopeTypeEnum.WAREHOUSE.getType());
            this.dao.saveUserScope(userScope);
        }
        //�������Ϸ�Χ
        for (Integer id : condition.getSpareparts()) {
            userScope = new UserScope();
            userScope.setGuId(obj.getGuId());
            userScope.setZtId(obj.getZtId());
            userScope.setPersonId(obj.getId());
            userScope.setScopeId(id);
            userScope.setScopeType(UserEnums.ScopeTypeEnum.SPAREPARTSCATE.getType());
            this.dao.saveUserScope(userScope);
        }
        //������Ͻ����
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
     * �����û�Ids������ɾ��
     **/
    @Transactional
    public void delUserScopesByIdsAndType(List<Integer> personIds ,Integer scopeType) {
        this.dao.delUserScopesByIdsAndType(personIds,scopeType);
    }

    /**
     * ��������
     **/
    @Transactional
    public void saveAllUserScope(String sql) {
        this.dao.saveAllUserScope(sql);
    }

}
