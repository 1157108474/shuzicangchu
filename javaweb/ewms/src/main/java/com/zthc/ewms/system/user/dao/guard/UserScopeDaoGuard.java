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

    //�ֲ߳̾����������ڴ�ŵ�ǰ�û���Ϣ������
    private static final ThreadLocal<BaseLocal> threadLocal = new ThreadLocal<BaseLocal>();

    /**
     * �õ���ǰ�̣߳����أ����ݶ���
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
     * �����ݴ��뵱ǰ�̣߳����أ�����
     *
     * @param local ���úù���ֵ�Ķ���
     */
    public static void setThreadLocal(BaseLocal local) {
        threadLocal.set(local);
    }

	/*-------------------------------------------------��ʼ��������-------------------------------------------------*/

    /**
     * ���浥������Ҫ��������
     **/
    public void saveUserScope(UserScope obj) {
        Date now = new Date();
        //�õ���ǰ�û�ID
        long currentUserScopeId = getThreadLocal().getCurrentUserId();

        //�Զ�������ʱ��ʹ�����
        obj.setCreateDate(now);
        obj.setCreator((int) currentUserScopeId);
        //�Զ���������Ĭ��ֵ�������ֶΣ�
        //������������
        baseDao.save(obj);
    }

    /**
     * ɾ����������ʵɾ����������
     **/
    public void delUserScope(Integer id) {
        baseDao.removeById(UserScope.class, id);
    }

    /**
     * ��ȡ��������Ҫ�����������޸�
     **/
    public UserScope getUserScopeOne(Integer id) {
        UserScope ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new UserScope();
            //ĳЩ�ֶ�pageĬ��ֵ
            //ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(UserScope.class, id);
        }
        return ret;
    }


/*-------------------------------------------------������������-------------------------------------------------*/

    /**
     * �����û�Idɾ��
     **/
    public int delUserScopes(Integer personId) {
        String hql = "delete UserScope where personId = :personId";
        Query query = baseDao.createQuery(hql);
        query.setParameter("personId", personId);
        return query.executeUpdate();
    }

    /**
     * �����û�Ids������ɾ��
     **/
    public int delUserScopesByIdsAndType(List<Integer> personIds, Integer scopeType) {
        String hql = "delete UserScope where personId in :personIds and scopeType = :scopeType";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("personIds", personIds);
        query.setParameter("scopeType", scopeType);
        return query.executeUpdate();
    }

    /**
     * ��������
     **/
    public void saveAllUserScope(String sql) {
        Query query = baseDao.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }
} 