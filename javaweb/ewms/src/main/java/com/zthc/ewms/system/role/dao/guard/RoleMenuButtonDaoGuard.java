package com.zthc.ewms.system.role.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.role.entity.guard.RoleMenuButton;
import com.zthc.ewms.system.role.entity.guard.RoleMenuButtonCondition;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.Date;

public class RoleMenuButtonDaoGuard {

    @Resource
    public BaseDao baseDao;

    private static final ThreadLocal<BaseLocal> threadLocal = new ThreadLocal<BaseLocal>();


    public static BaseLocal getThreadLocal() {
        if (threadLocal.get() == null) {
            return new BaseLocal();
        } else {
            return threadLocal.get();
        }
    }


    public static void setThreadLocal(BaseLocal local) {
        threadLocal.set(local);
    }

	/*-------------------------------------------------???????????-------------------------------------------------*/

    public void saveRoleMenuButton(RoleMenuButton obj, RoleMenuButtonCondition condition) {
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
//        obj.setCreateDate(now);
//        obj.setCreator((int) currentUserId);
        baseDao.save(obj);
    }

    public void delRoleMenuButton(String RoleMenuButtonCode) {
        baseDao.removeById(RoleMenuButton.class, RoleMenuButtonCode);
    }

    public RoleMenuButton getRoleMenuButtonOne(String RoleMenuButtonCode) {
        RoleMenuButton ret = null;
        if (!TextUtil.isNotNull(RoleMenuButtonCode)) {
            ret = new RoleMenuButton();
            // ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
        } else {
            ret = baseDao.getById(RoleMenuButton.class, RoleMenuButtonCode);
        }
        return ret;
    }


}