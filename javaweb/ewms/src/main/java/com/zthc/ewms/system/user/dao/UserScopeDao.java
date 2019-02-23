package com.zthc.ewms.system.user.dao;


import com.zthc.ewms.system.user.dao.guard.UserScopeDaoGuard;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserScopeDao extends UserScopeDaoGuard {

    /**
     * 根据用户ID获取组织机构
     *
     * @return
     * @throws Exception
     */
    public <T> List<T> listUserScopes(Integer personId, String table, int scopeType) {

        String hql = " from " + table + " d where 1=1 and d.id in ( select new UserScope(scopeId) from UserScope us where us.personId = :personId " +
                "and us.scopeType = :scopeType) ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("personId", personId);
        query.setParameter("scopeType", scopeType);
        return query.list();
    }


    public List<Integer> listUserScopeZTID(Integer personId, int scopeType) {

        String hql = "select scopeId from UserScope us where us.personId = :personId and us.scopeType = :scopeType) ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("personId", personId);
        query.setParameter("scopeType", scopeType);
        return query.list();
    }

}