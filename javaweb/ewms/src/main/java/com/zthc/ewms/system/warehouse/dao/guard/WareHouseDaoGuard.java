package com.zthc.ewms.system.warehouse.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouseCondition;
import drk.util.TextUtil;

import javax.annotation.Resource;
import java.util.Date;

public class WareHouseDaoGuard {

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
    public void saveWareHouse(WareHouse obj, WareHouseCondition condition) {
        Date now = new Date();
        //�õ���ǰ�û�ID
        long currentUserId = getThreadLocal().getCurrentUserId();

        //�Զ�������ʱ��ʹ�����

        //�Զ���������Ĭ��ֵ�������ֶΣ�

        //������������
        baseDao.save(obj);
    }

    /**
     * ɾ����������ʵɾ����������
     **/
    public void delWareHouse(Long id) {
        baseDao.removeById(WareHouse.class, id);
    }

    /**
     * ��ȡ��������Ҫ�����������޸�
     **/
    public WareHouse getWareHouseOne(Integer id) {
        WareHouse ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new WareHouse();
            //ĳЩ�ֶ�pageĬ��ֵ

            //ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(WareHouse.class, id);
        }
        return ret;
    }

    /**
     * ��ȡ��������Ҫ�����������޸�
     **/
    public WareHouse getWareHouseById(Integer id) {
        if (TextUtil.isNotNull(id)) {
            return baseDao.getById(WareHouse.class, id);
        }
        return null;
    }
	
/*-------------------------------------------------������������-------------------------------------------------*/

    //ɾ������

    //�޸ġ����·���

    //��ѯ����

} 