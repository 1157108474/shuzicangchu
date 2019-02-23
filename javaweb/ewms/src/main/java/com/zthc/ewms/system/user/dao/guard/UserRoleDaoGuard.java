package com.zthc.ewms.system.user.dao.guard;


import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.user.entity.guard.UserRole;
import drk.util.TextUtil;

import javax.annotation.Resource;
import java.util.Date;


public class UserRoleDaoGuard {

    @Resource
    public BaseDao baseDao;

    //�ֲ߳̾����������ڴ�ŵ�ǰ�û���Ϣ������
    private static final ThreadLocal<BaseLocal> threadLocal = new ThreadLocal<>();

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
    public void saveUserRole(UserRole obj) {
        //�õ���ǰ�û�ID

        //�Զ�������ʱ��ʹ�����
        //�Զ���������Ĭ��ֵ�������ֶΣ�

        //������������
        baseDao.save(obj);
    }

    /**
     * ɾ����������ʵɾ����������
     **/
    public void delUserRole(Long id) {
        baseDao.removeById(UserRole.class, id);
    }

    /**
     * ��ȡ��������Ҫ�����������޸�
     **/
    public UserRole getUserRoleOne(Long id) {
        UserRole ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new UserRole();
            //ĳЩ�ֶ�pageĬ��ֵ
            //ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(UserRole.class, id);
        }
        return ret;
    }
/*-------------------------------------------------������������-------------------------------------------------*/

    //ɾ������

    //�޸ġ����·���

    //��ѯ����

} 