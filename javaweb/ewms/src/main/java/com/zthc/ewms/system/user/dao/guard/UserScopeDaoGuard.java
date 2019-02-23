package com.zthc.ewms.system.user.dao.guard;


import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.user.entity.guard.UserScope;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


public class UserScopeDaoGuard {

    @Resource
    public BaseDao baseDao;

    //线程局部变量，用于存放当前用户信息等数据
    private static final ThreadLocal<BaseLocal> threadLocal = new ThreadLocal<BaseLocal>();

    /**
     * 得到当前线程（本地）数据对象
     *
     * @return
     */
    public static BaseLocal getThreadLocal() {
        if (threadLocal.get() == null) {
            return new BaseLocal();
        } else {
            return threadLocal.get();
        }
    }

    /**
     * 把数据存入当前线程（本地）对象
     *
     * @param local 设置好共享值的对象
     */
    public static void setThreadLocal(BaseLocal local) {
        threadLocal.set(local);
    }

	/*-------------------------------------------------开始基础方法-------------------------------------------------*/

    /**
     * 保存单条，主要用于新增
     **/
    public void saveUserScope(UserScope obj) {
        Date now = new Date();
        //得到当前用户ID
        long currentUserScopeId = getThreadLocal().getCurrentUserId();

        //自动处理创建时间和创建人
        obj.setCreateDate(now);
        obj.setCreator((int) currentUserScopeId);
        //自动处理特殊默认值（特殊字段）
        //进行新增保存
        baseDao.save(obj);
    }

    /**
     * 删除单条，真实删除，不常用
     **/
    public void delUserScope(Integer id) {
        baseDao.removeById(UserScope.class, id);
    }

    /**
     * 获取单条，主要用于新增和修改
     **/
    public UserScope getUserScopeOne(Integer id) {
        UserScope ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new UserScope();
            //某些字段page默认值
            //ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(UserScope.class, id);
        }
        return ret;
    }


/*-------------------------------------------------结束基础方法-------------------------------------------------*/

    /**
     * 根据用户Id删除
     **/
    public int delUserScopes(Integer personId) {
        String hql = "delete UserScope where personId = :personId";
        Query query = baseDao.createQuery(hql);
        query.setParameter("personId", personId);
        return query.executeUpdate();
    }

    /**
     * 根据用户Ids和类型删除
     **/
    public int delUserScopesByIdsAndType(List<Integer> personIds, Integer scopeType) {
        String hql = "delete UserScope where personId in :personIds and scopeType = :scopeType";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("personIds", personIds);
        query.setParameter("scopeType", scopeType);
        return query.executeUpdate();
    }

    /**
     * 批量保存
     **/
    public void saveAllUserScope(String sql) {
        Query query = baseDao.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }
} 