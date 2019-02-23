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
    public void saveMenu(Menu obj, MenuCondition condition) {
        Date now = new Date();
        // 得到当前用户ID、姓名
        long currentUserId = getThreadLocal().getCurrentUserId();

        // 自动处理创建时间和创建人
        obj.setCreator((int) currentUserId);
        obj.setCreateDate(now);
        // 自动处理特殊默认值（特殊字段）

        // 进行新增保存
        baseDao.save(obj);
    }

    /**
     * 删除单条，真实删除，不常用
     **/
    public void delMenu(Long id) {
        baseDao.removeById(Menu.class, id);
    }

    /**
     * 获取单条，主要用于新增和修改
     **/
    public Menu getMenuOne(String code) {
        Menu ret = null;
        if (!TextUtil.isNotNull(code)) {
            ret = new Menu();
            // 某些字段page默认值

            // ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(Menu.class, code);
        }
        return ret;
    }
/*-------------------------------------------------结束基础方法-------------------------------------------------*/

    /**
     * 自定义更新，
     *
     * @param obj       基本查询参数
     * @param condition 特殊查询参数
     * @return 更新记录数
     */
    public int updateMenu(Menu obj, MenuCondition condition) {
        Date now = new Date();
        // 得到当前用户ID
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

    //删除方法
    //查询方法

} 