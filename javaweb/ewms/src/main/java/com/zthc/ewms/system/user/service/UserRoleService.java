package com.zthc.ewms.system.user.service;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserCondition;
import com.zthc.ewms.system.user.entity.guard.UserRole;
import com.zthc.ewms.system.user.entity.guard.UserRoleCondition;
import com.zthc.ewms.system.user.service.guard.UserRoleServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRoleService extends UserRoleServiceGuard {
    /**
     * ���ݽ�ɫ��ȡ�û�����,��ҳ
     *
     * @param obj
     * @param condition
     * @return
     * @throws Exception
     */
    @Transactional
    public LayuiPage<User> listRoleUser(Role obj, UserCondition condition) throws Exception {
        return this.dao.listRoleUser(obj, condition);
    }
    @Transactional
    public LayuiPage<User> listRoleUserName(Role obj, UserCondition condition) throws Exception {
        return this.dao.listRoleUserName(obj, condition);
    }
    @Transactional
    public LayuiPage<User> listNoRoleUser(User obj,String roleCode, UserCondition condition) throws Exception {
        return this.dao.listNORoleUser(obj,roleCode,condition);
    }
    /**
     * ���ݽ�ɫ��ȡ�û�����
     *
     * @param obj
     * @param condition
     * @return
     * @throws Exception
     */
    @Transactional
    public List<User> listRoleUsers(Role obj, UserCondition condition) throws Exception {
        return this.dao.listRoleUsers(obj, condition);
    }

    /**
     * �����û���ȡ��ɫ����,��ҳ
     *
     * @param obj
     * @param condition
     * @return
     * @throws Exception
     */
    @Transactional
    public LayuiPage<Role> listUserRole(User obj, RoleCondition condition) throws Exception {
        return this.dao.listUserRole(obj, condition);
    }

    /**
     * �����û���ȡ��ɫ����
     *
     * @return
     */
    @Transactional
    public List<Role> listUserRoles(Integer userId) {
        return this.dao.listUserRoles(userId);
    }

    /**
     * ɾ�����û����н�ɫ����
     *
     * @param userId
     */
    public void delUserRoles(Integer userId) {
        this.dao.delUserRoles(userId);
    }

    /**
     * �����ɫ�û���¼
     *
     * @param obj
     * @param condition
     * @throws Exception
     */
    @Transactional
    public void saveRoleUsers(Role obj, UserCondition condition) throws Exception {
        for (Integer id : condition.getIds()) {
            UserRole userRole = new UserRole();
            userRole.setRoleCode(obj.getRoleCode());
            userRole.setUserId(id);
            this.dao.saveUserRole(userRole);
        }
    }

    /**
     * ���ݽ�ɫ�Ƴ��û�
     *
     * @param obj
     * @param condition
     * @throws Exception
     */
    @Transactional
    public void delRoleUsers(Role obj, UserCondition condition) {
        this.dao.delRoleUsers(obj, condition);
    }

    /**
     * �����û���ɫ��¼
     *
     * @param obj
     * @param condition
     * @throws Exception
     */
    @Transactional
    public void saveUserRoles(User obj, UserRoleCondition condition) throws Exception {
        this.dao.delUserRoles(obj.getId());

        for (String roleCode : condition.getRoleCodes()) {
            UserRole userRole = new UserRole();
            userRole.setRoleCode(roleCode);
            userRole.setUserId(obj.getId());
            this.dao.saveUserRole(userRole);
        }
    }
}
