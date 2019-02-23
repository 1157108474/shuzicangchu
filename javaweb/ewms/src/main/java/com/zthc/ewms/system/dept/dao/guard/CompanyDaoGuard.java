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
    public void saveCompany(Company obj) {
        // �õ���ǰʱ�䡢�û�ID���û���
        // �Զ�������ʱ��ʹ�����
        // ������������
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        obj.setCreateDate(now);
        obj.setCreator((int) currentUserId);
        baseDao.save(obj);
    }

    /**
     * ɾ����������ʵɾ����������
     **/
    public void delCompany(Integer id) {
        baseDao.removeById(Company.class, id);
    }

    /**
     * ��ȡ��������Ҫ�����������޸�
     **/
    public Company getCompanyOne(Integer id) {
        Company ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new Company();
            // ĳЩ�ֶ�pageĬ��ֵ

            // ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(Company.class, id);
        }
        return ret;
    }

    /*-------------------------------------------------������������-------------------------------------------------*/
    //�޸ķ���
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