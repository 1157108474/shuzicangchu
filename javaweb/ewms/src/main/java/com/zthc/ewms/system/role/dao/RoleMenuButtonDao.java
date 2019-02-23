package com.zthc.ewms.system.role.dao;


import com.zthc.ewms.system.role.dao.guard.RoleMenuButtonDaoGuard;

import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.role.entity.guard.RoleMenuButton;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class RoleMenuButtonDao extends RoleMenuButtonDaoGuard {

    /**
     * 获取所有角色-菜单-按钮
     *
     * @return
     * @throws Exception
     */
    public List<RoleMenuButton> listRoleMenuButtons(Role obj) {

        String hql = " from RoleMenuButton r where roleCode = :roleCode";
        Query query = baseDao.createQuery(hql);
        query.setParameter("roleCode", obj.getRoleCode());
        return query.list();
    }

    /**
     * 批量删除
     **/
    public int delRoleMenuButton(Role obj) {
        String hql = "delete RoleMenuButton where roleCode = :roleCodes";
        Query query = baseDao.createQuery(hql);
        query.setParameter("roleCodes", obj.getRoleCode());
        return query.executeUpdate();
    }
}