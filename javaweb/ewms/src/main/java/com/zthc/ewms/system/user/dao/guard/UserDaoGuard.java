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
    public void saveUser(User obj) {
        Date now = new Date();
        //得到当前用户ID
        long currentUserId = getThreadLocal().getCurrentUserId();

        //自动处理创建时间和创建人
        obj.setCreateDate(now);
        obj.setCreator((int) currentUserId);
        //自动处理特殊默认值（特殊字段）
        //进行新增保存
        baseDao.save(obj);
    }

    /**
     * 删除单条，真实删除，不常用
     **/
    public void delUser(Integer id) {
        baseDao.removeById(User.class, id);
    }

    /**
     * 获取单条，主要用于新增和修改
     **/
    public User getUserOne(Integer id) {
        if (!TextUtil.isNotNull(id)) {
            return new User();
        } else {
            return baseDao.getById(User.class, id);
        }
    }


/*-------------------------------------------------结束基础方法-------------------------------------------------*/

    /**
     * 修改方法
     *
     * @param obj
     */
    public int updateUser(User obj) {
        // 得到当前时间、用户ID、用户名
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
     * 批量删除
     **/
    public int delUsers(UserCondition condition) {
        String hql = "delete User where id in :ids";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids", condition.getIds());
        return query.executeUpdate();
    }

} 