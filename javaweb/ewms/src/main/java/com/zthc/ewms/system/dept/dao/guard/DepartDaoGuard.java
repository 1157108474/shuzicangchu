package com.zthc.ewms.system.dept.dao.guard;


import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.entity.guard.DepartCondition;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.Date;

public class DepartDaoGuard {

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
    public void saveDepart(Depart obj) {
        // �õ���ǰʱ�䡢�û�ID���û���
        // �Զ�������ʱ��ʹ�����
        // ������������
        baseDao.save(obj);

    }

    /**
     * ɾ����������ʵɾ����������
     **/
    public void delDepart(Long id) {
        baseDao.removeById(Depart.class, id);
    }

    /**
     * ��ȡ��������Ҫ�����������޸�
     **/
    public Depart getDepartOne(Integer id) {
        Depart ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new Depart();
            // ĳЩ�ֶ�pageĬ��ֵ

            // ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(Depart.class, id);
        }
        return ret;
    }
/*-------------------------------------------------������������-------------------------------------------------*/
    //��ѯ����

} 