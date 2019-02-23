package com.zthc.ewms.system.menu.dao;


import com.zthc.ewms.system.menu.dao.guard.MenuDaoGuard;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.entity.guard.MenuButtonEnums;
import com.zthc.ewms.system.menu.entity.guard.MenuCondition;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MenuDao extends MenuDaoGuard {

    /**
     * 获取所有菜单数据
     *
     * @return
     */
    public List<Menu> listMenus() {
        String hql = " from Menu where 1=1 and status != :status  order by sort asc ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("status",MenuButtonEnums.StatusEnum.DELETE.getStatus());
        return query.list();
    }
    /**
     * 获取所有菜单数据
     *
     * @return
     */
    public List<Menu> listMenuStatus() {
        String hql = " from Menu where 1=1 and status = :status  order by sort asc ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("status", MenuButtonEnums.StatusEnum.ENABLE.getStatus());
        return query.list();
    }
    /**
     * 批量删除
     **/
    public int delMenu(MenuCondition condition) {
        String hql = "delete Menu where code in :codes or menu.code in :menuCodes";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("codes", condition.getCodes());
        query.setParameterList("menuCodes", condition.getCodes());
        return query.executeUpdate();
    }

    /**
     * 根据菜单编号获取菜单信息
     *
     * @return
     */
    public List<Menu> listMenuCode(Menu obj) {
        String hql = "select new Menu(code) from Menu where code = :code";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", obj.getCode());
        return query.list();
    }


    /**
     * 根据类型获取菜单
     *
     * @return
     */
    public List<Menu> listFatherMenus(int type) {
        String hql = " from Menu where type = :type";
        Query query = baseDao.createQuery(hql);
        query.setParameter("type", type);
        return query.list();
    }

    /**
     * 根据用户ID查询角色，根据角色获取菜单
     *
     * @return
     */
    public List<Menu> listMenus(Integer userId) {
        String sql = "SELECT DISTINCT m.* FROM BASE_MENU m " +
                "WHERE m.status = " + MenuButtonEnums.StatusEnum.ENABLE.getStatus() +
                " and  m.code IN (SELECT rmp.MENUCODE FROM BASE_ROLEMENU rmp " +
                "WHERE rmp.ROLECODE IN (SELECT DISTINCT ur.ROLECODE FROM BASE_USERROLES ur " +
                "WHERE ur.USERID = " + userId + ")) order by sort asc";
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        query.addEntity(Menu.class);
        return query.list();
    }

    /**
     * 根据用户ID查询角色，根据角色获取父级菜单
     *
     * @return
     */
    public List<Menu> listSuperiorMenu(Long userId) {
        String sql = "SELECT DISTINCT menu.* FROM sys_menu menu " +
                "WHERE menu.id IN (SELECT m.superior_menu_id FROM sys_menu m " +
                "WHERE m.id IN (SELECT rmp.menu_id FROM sys_role_menu_permissions rmp " +
                "WHERE rmp.role_id IN (SELECT ur.role_id FROM sys_user_role ur " +
                "WHERE ur.user_id = " + userId + "))) order by sort asc";
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        query.addEntity(Menu.class);
        return query.list();
    }

    /**
     * 根据菜单删除所有权限分配
     */
    public void delMenuResourceMenuId(MenuCondition condition) {
        String hql = "delete RoleMenu where menuCode in :menuCodes";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("menuCodes", condition.getCodes());
        query.executeUpdate();
    }

    /**
     * 根据类型获取菜单
     *
     * @return
     */
    public List<Menu> listMenusCodes(String[] codes) {
        String hql = " from Menu where code in :codes";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("codes", codes);
        return query.list();
    }
    /**
     * 根据获取单条数据
     */
     public Menu getMenuCode (String code) {
            Menu res = null;
            String hql = " from Menu where 1 = 1 and code = :code";
            Query query = baseDao.createQuery(hql);
            query.setParameter("code", code);
            if (null != query.list() && query.list().size() > 0) {
                res = (Menu) query.list().get(0);
            }
            return res;
        }
}