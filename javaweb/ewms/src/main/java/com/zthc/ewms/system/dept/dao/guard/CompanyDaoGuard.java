package com.zthc.ewms.system.dept.dao.guard;


import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.dept.entity.guard.Company;
import com.zthc.ewms.system.dept.entity.guard.CompanyCondition;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.Date;

public class CompanyDaoGuard {

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
    public void saveCompany(Company obj) {
        // 得到当前时间、用户ID、用户名
        // 自动处理创建时间和创建人
        // 进行新增保存
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        obj.setCreateDate(now);
        obj.setCreator((int) currentUserId);
        baseDao.save(obj);
    }

    /**
     * 删除单条，真实删除，不常用
     **/
    public void delCompany(Integer id) {
        baseDao.removeById(Company.class, id);
    }

    /**
     * 获取单条，主要用于新增和修改
     **/
    public Company getCompanyOne(Integer id) {
        Company ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new Company();
            // 某些字段page默认值

            // ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(Company.class, id);
        }
        return ret;
    }

    /*-------------------------------------------------结束基础方法-------------------------------------------------*/
    //修改方法
    public int updateCompany(Company obj) {
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        String hql = "update Company set code = :code,name = :name,shortName = :shortName,type = :type,sort = :sort," +
                "status = :status,memo = :memo,parentId = :parentId,levelCount = :levelCount,levelCode = :levelCode," +
                "ztId = :ztId,updater = :updater,updateDate = :updateDate where 1=1 and id = :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", obj.getCode());
        query.setParameter("name", obj.getName());
        query.setParameter("shortName", obj.getShortName());
        query.setParameter("type", obj.getType());
        query.setParameter("sort", obj.getSort());
        query.setParameter("status", obj.getStatus());
        query.setParameter("memo", obj.getMemo());
        query.setParameter("parentId", obj.getParentId());
        query.setParameter("levelCount", obj.getLevelCount());
        query.setParameter("levelCode", obj.getLevelCode());
        query.setParameter("ztId", obj.getZtId());
        query.setParameter("updateDate", now);
        query.setParameter("updater", (int) currentUserId);
        query.setParameter("id", obj.getId());

        return query.executeUpdate();

    }
} 