package com.zthc.ewms.system.menu.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.menu.entity.guard.Button;
import com.zthc.ewms.system.menu.entity.guard.ButtonCondition;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.Date;

public class ButtonDaoGuard {

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
    public void saveButton(Button obj) {
        Date now = new Date();
        // �õ���ǰ�û�ID
        long currentUserId = getThreadLocal().getCurrentUserId();
        // �Զ�������ʱ��ʹ�����
        obj.setCreator((int) currentUserId);
        obj.setCreateDate(now);
        // �Զ���������Ĭ��ֵ�������ֶΣ�

        // ������������
        baseDao.save(obj);

    }

    /**
     * ɾ����������ʵɾ����������
     **/
    public void delButton(Long id) {
        baseDao.removeById(Button.class, id);
    }

    /**
     * ��ȡ��������Ҫ�����������޸�
     **/
    public Button getButtonOne(String code) {
        Button ret = null;
        if (!TextUtil.isNotNull(code)) {
            ret = new Button();
            // ĳЩ�ֶ�pageĬ��ֵ

            // ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(Button.class, code);
        }
        return ret;
    }

    /*-------------------------------------------------������������-------------------------------------------------*/
    public int updateButton(Button obj, ButtonCondition condition) {
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        String hql = "update Button set name = :name,url = :url,authIdentity = :authIdentity,status = :status," +
                "sort = :sort,updater = :updater,updateDate = :updateDate where 1=1 and code = :code";
        Query query = baseDao.createQuery(hql);
        query.setParameter("name", obj.getName());
        query.setParameter("url", obj.getUrl());
        query.setParameter("authIdentity", obj.getAuthIdentity());
        query.setParameter("status", obj.getStatus());
        query.setParameter("sort", obj.getSort());
        query.setParameter("updater", (int) currentUserId);
        query.setParameter("updateDate", now);
        query.setParameter("code", obj.getCode());

        return query.executeUpdate();
    }

    /**
     * ����ID����ɾ��
     **/
    public int delButton(ButtonCondition condition) {
        String hql = "delete Button where code in :codes";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("codes", condition.getCodes());
        return query.executeUpdate();
    }
    //��ѯ����

} 