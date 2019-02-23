package com.zthc.ewms.system.dept.dao.guard;


import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.Date;

public class OrganizationDaoGuard {

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
    public void saveOrganization(Organization obj) {
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        obj.setCreateDate(now);
        obj.setCreator((int) currentUserId);
        // 进行新增保存
        baseDao.save(obj);
    }

    /**
     * 删除单条，真实删除，不常用
     **/
    public void delOrganization(Integer id) {
        baseDao.removeById(Organization.class, id);
    }

    /**
     * 获取单条，主要用于新增和修改
     **/
    public Organization getOrganizationOne(Integer id) {
        if (!TextUtil.isNotNull(id)) {
            return new Organization();
        } else {
            return baseDao.getById(Organization.class, id);
        }
    }

    /*-------------------------------------------------结束基础方法-------------------------------------------------*/
    //查询方法
    public int updateOrganization(Organization obj) {
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        String hql = "update Organization set code = :code,name = :name,type = :type,sort = :sort,status = :status," +
                "memo = :memo,parentId = :parentId,levelCount = :levelCount,levelCode = :levelCode,ztId = :ztId," +
                "companyId = :companyId,updater = :updater,updateDate = :updateDate where 1=1 and id = :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", obj.getCode());
        query.setParameter("name", obj.getName());
        query.setParameter("type", obj.getType());
        query.setParameter("sort", obj.getSort());
        query.setParameter("status", obj.getStatus());
        query.setParameter("memo", obj.getMemo());
        query.setParameter("parentId", obj.getParentId());
        query.setParameter("levelCount", obj.getLevelCount());
        query.setParameter("levelCode", obj.getLevelCode());
        query.setParameter("ztId", obj.getZtId());
        query.setParameter("companyId", obj.getCompanyId());
        query.setParameter("updateDate", now);
        query.setParameter("updater", (int) currentUserId);
        query.setParameter("id", obj.getId());

        return query.executeUpdate();

    }


}