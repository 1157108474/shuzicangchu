package com.zthc.ewms.system.user.dao;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.user.dao.guard.UserDaoGuard;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserCondition;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class UserDao extends UserDaoGuard {

    /**
     * ��ȡ����
     *
     * @param obj
     * @param condition
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */

    public LayuiPage<User> listUser(User obj, UserCondition condition, String startTime, String endTime)
            throws Exception {
        LayuiPage<User> ret = new LayuiPage<>();

        String hql = " from User u ";

        hql += " where 1=1 ";

        Map<String, Object> param = new HashMap<>();
        //Ա�����
        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and code like :code";
            param.put("code", "%" + obj.getCode().trim() + "%");
        }
        //����
        if (!StringUtils.isEmpty(obj.getName())) {
            hql += " and name like :name";
            param.put("name", "%" + obj.getName().trim() + "%");
        }
        //����
        if (!StringUtils.isEmpty(obj.getName())) {
            hql += " and name like :name";
            param.put("name", "%" + obj.getName().trim() + "%");
        }
        //��������
        if (!StringUtils.isEmpty(obj.getParentName())) {
            hql += " and parent.name like :parentName";
            param.put("parentName", "%" + obj.getParentName() + "%");
        }
        //��������
        if (!StringUtils.isEmpty(obj.getOfficesName())) {
            hql += " and offices.name like :officesName";
            param.put("officesName", "%" + obj.getOfficesName() + "%");
        }
        //����
        if (!StringUtils.isEmpty(obj.getEmail())) {
            hql += " and email like :email";
            param.put("email", "%" + obj.getEmail().trim() + "%");
        }
        //����Id
        if (!StringUtils.isEmpty(condition.getDepartId())) {
            hql += " and parent.id = :parentId";
            param.put("parentId", condition.getDepartId());
        }

        String totalsql = "select count(1) " + hql;
        Long total = baseDao.countByHql(totalsql, param);
        // ����
        hql += " order by sort asc";

        List<User> users = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        ret.setCount(total);
        ret.setData(users);
        return ret;
    }

    /**
     * ���ݵ�¼��Ϣ��ѯ�û���Ϣ
     *
     * @param obj
     * @return
     */
    public User getUserLogin(User obj) {
        User user = null;
        String hql = "from User where code = :code and passWord = :passWord and status = :status";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", obj.getCode());
        query.setParameter("passWord", StringUtils.getMD5(obj.getPassWord()));
        query.setParameter("status", UserEnums.StatusEnum.ENABLE.getStatus());
        if (query.list().size() > 0) {
            user = (User) query.list().get(0);
        }
        return user;
    }

    /**
     * ���ݵ�¼��Ϣ��ѯ�û���Ϣ
     *
     * @param obj
     * @return
     */
    public int updateUserLogin(User obj) {
        // �õ���ǰʱ�䡢�û�ID���û���
        Date now = new Date();
        String hql = "update User set loginCity = :loginCity,loginIp = :loginIp,loginTime = :loginTime where 1=1 and id = :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("loginCity", obj.getLoginCity());
        query.setParameter("loginIp", obj.getLoginIp());
        query.setParameter("loginTime", now);
        query.setParameter("id", obj.getId());
        return query.executeUpdate();
    }

    /**
     * �����û���ź�Id��ȡ�û���Ϣ
     *
     * @return
     */
    public List<User> listUserCode(User obj) {
        String hql = "select new User(code) from User where code = :code and id != :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", obj.getCode());
        query.setParameter("id", obj.getId());
        return query.list();
    }


    /**
     * �����û���Ż�ȡ�û���Ϣ
     *
     * @return
     */
    public List<User> listUserCodes(String code) {
        String hql = " from User where code = '" + code + "'";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    public List<User> listUserCodesName(String code) {
        String hql = "select new User(id,name,code) from User where code = '" + code + "'";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    /**
     * ��ȡ�û���������Ϣ
     *
     * @return
     */
    public List<User> listUser() {
        String hql = " from User ";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    /**
     * ��������
     *
     * @param obj
     */
    public int updateResetPWD(User obj) {
        // �õ���ǰʱ�䡢�û�ID���û���
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        String hql = "update User set passWord = :passWord, lastChangePassWord = :lastChangePassWord, updaterUser.id = :updaterId, " +
                "updateDate = :updateDate where 1=1 and id = :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("passWord", StringUtils.getMD5(obj.getPassWord()));
        query.setParameter("lastChangePassWord", now);
        query.setParameter("updaterId", (int) currentUserId);
        query.setParameter("updateDate", now);
        query.setParameter("id", obj.getId());
        return query.executeUpdate();
    }

    /**
     * �޸ķ���
     *
     * @param obj
     */
    public void updateUserPassword(User obj) {
        Date now = new Date();
        obj.setLastChangePassWord(now);
        baseDao.update(obj);
    }

    /**
     * �����û���Ż�ȡ�û���Ϣ
     *
     * @return
     */
    public User getUserByCode(String code) {
        String hql = " from User where code = :code ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", code);
        List<?> counts = query.list();
        if (!counts.isEmpty() && counts.size() > 0) {
            return (User) counts.get(0);
        }
        return null;
    }


    /**
     * �����û����ҡ����ӻ���û�Id
     *
     * @return
     */
    public List<User> listUserIdByOfficesId(Integer officesId) {
        String hql = "select new User(id,ztId,guId) from User where offices.id = :officesId ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("officesId", officesId);
        return query.list();
    }

}