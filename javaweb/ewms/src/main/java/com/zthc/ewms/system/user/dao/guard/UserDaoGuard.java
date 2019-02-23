package com.zthc.ewms.system.user.dao.guard;


import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserCondition;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.Date;


public class UserDaoGuard {

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
    public void saveUser(User obj) {
        Date now = new Date();
        //�õ���ǰ�û�ID
        long currentUserId = getThreadLocal().getCurrentUserId();

        //�Զ�������ʱ��ʹ�����
        obj.setCreateDate(now);
        obj.setCreator((int) currentUserId);
        //�Զ���������Ĭ��ֵ�������ֶΣ�
        //������������
        baseDao.save(obj);
    }

    /**
     * ɾ����������ʵɾ����������
     **/
    public void delUser(Integer id) {
        baseDao.removeById(User.class, id);
    }

    /**
     * ��ȡ��������Ҫ�����������޸�
     **/
    public User getUserOne(Integer id) {
        if (!TextUtil.isNotNull(id)) {
            return new User();
        } else {
            return baseDao.getById(User.class, id);
        }
    }


/*-------------------------------------------------������������-------------------------------------------------*/

    /**
     * �޸ķ���
     *
     * @param obj
     */
    public int updateUser(User obj) {
        // �õ���ǰʱ�䡢�û�ID���û���
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        String hql = "update User set name = :name, code = :code, spell = :spell, sex = :sex, email = :email," +
                "parent.id = :parentId,offices.id = :officesId,phone = :phone,qq = :qq,sort = :sort, " +
                "userType = :userType,status = :status,isSingleLogin = :isSingleLogin,memo = :memo," +
                "updateDate = :updateDate,updaterUser.id = :updaterId where 1=1 and id = :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("name", obj.getName());
        query.setParameter("code", obj.getCode());
        query.setParameter("spell", obj.getSpell());
        query.setParameter("sex", obj.getSex());
        query.setParameter("email", obj.getEmail());
        query.setParameter("parentId", obj.getParent().getId());
        query.setParameter("officesId", obj.getOffices().getId());
        query.setParameter("phone", obj.getPhone());
        query.setParameter("qq", obj.getQq());
        query.setParameter("sort", obj.getSort());
        query.setParameter("userType", obj.getUserType());
        query.setParameter("status", obj.getStatus());
        query.setParameter("isSingleLogin", obj.getIsSingleLogin());
        query.setParameter("memo", obj.getMemo());
        query.setParameter("updateDate", now);
        query.setParameter("updaterId", (int) currentUserId);
        query.setParameter("id", obj.getId());
        return query.executeUpdate();
    }

    /**
     * ����ɾ��
     **/
    public int delUsers(UserCondition condition) {
        String hql = "delete User where id in :ids";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids", condition.getIds());
        return query.executeUpdate();
    }

} 