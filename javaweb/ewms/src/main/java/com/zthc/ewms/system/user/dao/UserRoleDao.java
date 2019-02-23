package com.zthc.ewms.system.user.dao;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.user.dao.guard.UserRoleDaoGuard;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserCondition;
import com.zthc.ewms.system.user.entity.guard.UserRole;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class UserRoleDao extends UserRoleDaoGuard {
    /**
     * 根据角色获取用户数据,分页
     *
     * @param obj
     * @param condition
     * @return
     * @throws Exception
     */

    public LayuiPage<User> listRoleUser(Role obj, UserCondition condition) throws Exception {
        LayuiPage<User> ret = new LayuiPage<>();

        String hql = " from User u";

        hql += " where 1=1 and u.id in ( select new UserRole(userId) from UserRole ur where ur.roleCode = :roleCode) ";

        Map<String, Object> param = new HashMap<>();
        param.put("roleCode", null == obj.getRoleCode() ? "" : obj.getRoleCode());

        String totalsql = "select count(*) " + hql;
        // 排序
        List<User> users = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);
        ret.setCount(total);
        ret.setData(users);
        return ret;
    }

    /**
     * 根据角色获取用户数据,分页
     *
     * @param obj
     * @param condition
     * @return
     * @throws Exception
     */

    public LayuiPage<User> listRoleUserName(Role obj, UserCondition condition) throws Exception {
        LayuiPage<User> ret = new LayuiPage<>();
        String hqw = "select new User(id,name,code) ";
        String hql = " from User u";

        hql += " where 1=1 and u.id in ( select new UserRole(userId) from UserRole ur where ur.roleCode = :roleCode )  order by u.id ";

        Map<String, Object> param = new HashMap<>();
        param.put("roleCode", null == obj.getRoleCode() ? "" : obj.getRoleCode());

        String totalsql = "select count(*) " + hql;
        // 排序
        List<User> users = baseDao.findByHql(hqw+hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);
        ret.setCount(total);
        ret.setData(users);
        return ret;
    }

    /**
     * 根据角色获取用户数据,分页
     *
     * @param obj
     * @param condition
     * @return
     * @throws Exception
     */

    public LayuiPage<User> listNORoleUser(User obj, String roleCode, UserCondition condition) throws Exception {
        LayuiPage<User> ret = new LayuiPage<>();

        String hql = " from User u";

        hql += " where u.id not in ( select new UserRole(userId) from UserRole ur where ur.roleCode = :roleCode) ";

        Map<String, Object> param = new HashMap<>();
        param.put("roleCode", roleCode);

        //员工编号
        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and u.code like :code";
            param.put("code", "%%" + obj.getCode().trim() + "%%");
        }
        //姓名
        if (!StringUtils.isEmpty(obj.getName())) {
            hql += " and u.name like :name";
            param.put("name", "%%" + obj.getName().trim() + "%%");
        }

        //部门Id
        if (!StringUtils.isEmpty(condition.getDepartId())) {
            hql += " and u.parent.id = :parentId";
            param.put("parentId", condition.getDepartId());
        }

        String totalsql = "select count(*) " + hql;
        // 排序
        hql += " order by u.sort asc";
        List<User> users = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);
        ret.setCount(total);
        ret.setData(users);
        return ret;
    }

    /**
     * 根据角色获取用户数据,方法
     *
     * @param obj
     * @param condition
     * @return
     * @throws Exception
     */
    public List<User> listRoleUsers(Role obj, UserCondition condition) throws Exception {

        String hql = " from User u";
        hql += " where 1=1 and u.id in ( select new UserRole(userId) from UserRole ur where ur.roleCode = :roleCode) ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("roleCode", obj.getRoleCode());
        return query.list();
    }


    /**
     * 根据用户获取角色数据,分页
     *
     * @param obj
     * @param condition
     * @return
     * @throws Exception
     */

    public LayuiPage<Role> listUserRole(User obj, RoleCondition condition) throws Exception {
        LayuiPage<Role> ret = new LayuiPage<>();

        String hql = " from Role r";

        hql += " where 1=1 and r.roleCode in ( select new UserRole(roleCode) from UserRole ur where ur.userId = :userId) ";

        Map<String, Object> param = new HashMap<>();
        param.put("userId", null == obj.getId() ? 0 : obj.getId());

        String totalsql = "select count(*) " + hql;
        // 排序
        List<Role> roles = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);
        ret.setCount(total);
        ret.setData(roles);
        return ret;
    }

    /**
     * 根据用户获取角色数据,方法
     *
     * @param userId
     * @return
     */
    public List<Role> listUserRoles(Integer userId){

        String hql = " from Role r";
        hql += " where 1=1 and r.roleCode in ( select new UserRole(roleCode) from UserRole ur where  ur.userId = :userId) ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }

    /**
     * 删除此用户所有角色分配
     *
     * @param userId
     */
    public void delUserRoles(Integer userId) {
        String hql = "delete UserRole where userId = :userid";
        Query query = baseDao.createQuery(hql);
        query.setParameter("userid", userId);
        query.executeUpdate();
    }

    /**
     * 根据角色移除用户
     *
     * @param obj
     * @param condition
     */
    public void delRoleUsers(Role obj, UserCondition condition) {
        String hql = "delete UserRole where roleCode = :roleCode and userId in :userIds";
        Query query = baseDao.createQuery(hql);
        query.setParameter("roleCode", obj.getRoleCode());
        query.setParameterList("userIds", condition.getIds());
        query.executeUpdate();
    }


}