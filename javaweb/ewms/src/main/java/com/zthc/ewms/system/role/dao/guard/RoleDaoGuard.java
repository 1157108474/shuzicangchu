package com.zthc.ewms.system.role.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.Date;

public class RoleDaoGuard {

    @Resource
    public BaseDao baseDao;

    private static final ThreadLocal<BaseLocal> threadLocal = new ThreadLocal<BaseLocal>();


    public static BaseLocal getThreadLocal() {
        if (threadLocal.get() == null) {
            return new BaseLocal();
        } else {
            return threadLocal.get();
        }
    }


    public static void setThreadLocal(BaseLocal local) {
        threadLocal.set(local);
    }

	/*-------------------------------------------------???????????-------------------------------------------------*/

    public void saveRole(Role obj, RoleCondition condition) {
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        obj.setCreateDate(now);
        obj.setCreator((int) currentUserId);
        baseDao.save(obj);
    }

    public void delRole(String roleCode) {
        baseDao.removeById(Role.class, roleCode);
    }
    public void update(Role role) {
        baseDao.update(role);
    }

    public Role getRoleOne(String roleCode) {
        Role ret = null;
        if (!TextUtil.isNotNull(roleCode)) {
            ret = new Role();
            ret.setSort(100);

            // ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(Role.class, roleCode);
        }
        return ret;
    }


    public int updateRole(Role obj, RoleCondition condition) {
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        String hql = "update Role set roleName = :roleName,roleType = :roleType,enabled = :enabled,sort = :sort," +
                "remark = :remark,updator = :updator,updateDate = :updateDate where 1=1 and roleCode = :roleCode";
        Query query = baseDao.createQuery(hql);
        query.setParameter("roleName", obj.getRoleName());
        query.setParameter("roleType", obj.getRoleType());
        query.setParameter("enabled", obj.getEnabled());
        query.setParameter("sort", obj.getSort());
        query.setParameter("remark", obj.getRemark());
        query.setParameter("updateDate", now);
        query.setParameter("updator", (int) currentUserId);
        query.setParameter("roleCode", obj.getRoleCode());

        return query.executeUpdate();
    }

    /**
     * ÅúÁ¿É¾³ý
     **/
    public int delRoles(RoleCondition condition) {
        String hql = "delete Role where roleCode in :roleCodes";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("roleCodes", condition.getRoleCodes());
        return query.executeUpdate();
    }


} 