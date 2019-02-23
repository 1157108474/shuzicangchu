package com.zthc.ewms.system.menu.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.entity.guard.MenuCondition;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.Date;

public class MenuDaoGuard {

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
    public void saveMenu(Menu obj, MenuCondition condition) {
        Date now = new Date();
        // �õ���ǰ�û�ID������
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
    public void delMenu(Long id) {
        baseDao.removeById(Menu.class, id);
    }

    /**
     * ��ȡ��������Ҫ�����������޸�
     **/
    public Menu getMenuOne(String code) {
        Menu ret = null;
        if (!TextUtil.isNotNull(code)) {
            ret = new Menu();
            // ĳЩ�ֶ�pageĬ��ֵ

            // ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(Menu.class, code);
        }
        return ret;
    }
/*-------------------------------------------------������������-------------------------------------------------*/

    /**
     * �Զ�����£�
     *
     * @param obj       ������ѯ����
     * @param condition �����ѯ����
     * @return ���¼�¼��
     */
    public int updateMenu(Menu obj, MenuCondition condition) {
        Date now = new Date();
        // �õ���ǰ�û�ID
        long currentUserId = getThreadLocal().getCurrentUserId();
        String hql = "update Menu set name = :name,menu.code = :menuCode,type = :type,sort = :sort,icon = :icon, " +
                "url = :url,authIdentity = :authIdentity,status = :status,buttonMode = :buttonMode,memo = :memo," +
                "updater = :updater,updateDate =:updateDate where 1=1 and code = :code";
        Query query = baseDao.createQuery(hql);
        query.setParameter("name", obj.getName());
        query.setParameter("menuCode", obj.getMenu().getCode());
        query.setParameter("type", obj.getType());
        query.setParameter("sort", obj.getSort());
        query.setParameter("icon", obj.getIcon());
        query.setParameter("url", obj.getUrl());
        query.setParameter("authIdentity", obj.getAuthIdentity());
        query.setParameter("status", obj.getStatus());
        query.setParameter("buttonMode", obj.getButtonMode());
        query.setParameter("memo", obj.getMemo());
        query.setParameter("updater", (int) currentUserId);
        query.setParameter("updateDate", now);
        query.setParameter("code", obj.getCode());
        return query.executeUpdate();
    }

    //ɾ������
    //��ѯ����

} 