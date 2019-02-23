package com.zthc.ewms.system.role.dao;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.role.dao.guard.RoleDaoGuard;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class RoleDao extends RoleDaoGuard {

    public LayuiPage<Role> listRole(Role obj, RoleCondition condition) throws Exception {
        LayuiPage<Role> ret = new LayuiPage<>();

        String hql = " from Role r  ";

        hql += " where 1=1 ";

        Map<String, Object> param = new HashMap<>();
        // 角色名称
        if (!StringUtils.isEmpty(obj.getRoleName())) {
            hql += " and roleName like :roleName";
            param.put("roleName", "%%" + obj.getRoleName().trim() + "%%");
        }

        String totalsql = "select count(*) " + hql;
        // 排序
        hql += " order by sort asc";

        List<Role> roles = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);
        ret.setCount(total);
        ret.setData(roles);
        return ret;
    }

    /**
     * 获取所有角色
     *
     * @return
     * @throws Exception
     */
    public List<Role> listRoles() throws Exception {

        String hql = " from Role r  ";

        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    /**
     * 获取所有角色
     *
     * @return
     * @throws Exception
     */
    public List<Role> listRoleNames(){

        String hql = "select  new Role(roleCode,roleName ) from Role r  ";

        Query query = baseDao.createQuery(hql);
        return query.list();
    }
    public LayuiPage<Role> listRoleName(Role obj, RoleCondition condition) throws Exception {
        LayuiPage<Role> ret = new LayuiPage<>();
        String hqlCount = "select new Role(roleCode,roleName )  ";
        String hql = " from Role r  ";

        hql += " where 1=1 ";

        Map<String, Object> param = new HashMap<>();
        // 角色名称
        if (!StringUtils.isEmpty(obj.getRoleName())) {
            hql += " and roleName like :roleName";
            param.put("roleName", "%%" + obj.getRoleName().trim() + "%%");
        }

        String totalsql = "select count(*) " + hql;
        // 排序
        hql += " order by sort asc";

        List<Role> roles = baseDao.findByHql(hqlCount+hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);
        ret.setCount(total);
        ret.setData(roles);
        return ret;
    }
    /**
     * 获取角色名
     **/
    public List<Role> listRolesName() {
        String hql = "select new Role(id,roleName) from Role ";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

}