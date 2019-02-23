package com.zthc.ewms.system.menu.dao;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.menu.dao.guard.ButtonDaoGuard;
import com.zthc.ewms.system.menu.entity.guard.Button;
import com.zthc.ewms.system.menu.entity.guard.ButtonCondition;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ButtonDao extends ButtonDaoGuard {

    public LayuiPage<Button> listButton(Button obj, ButtonCondition condition) throws Exception {
        LayuiPage<Button> ret = new LayuiPage<>();

        String hql = " from Button bu  ";

        hql += " where 1=1 and menuCode = :menuCode";

        Map<String, Object> param = new HashMap<>();
        param.put("menuCode", obj.getMenuCode());

        String totalsql = "select count(*) " + hql;
        // 排序
        hql += " order by sort asc";

        List<Button> Button = baseDao.findByHql(hql, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);
        ret.setCount(total);
        ret.setData(Button);
        return ret;
    }


    /**
     * 根据菜单ID批量删除
     **/
    public int delButtonMenuId(Long[] menuIds) {
        String hql = "delete Button where menuId in :menuIds";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("menuIds", menuIds);
        return query.executeUpdate();
    }

    /**
     * 根据菜单编号获取菜单信息
     *
     * @return
     */
    public List<Button> listButtonCode(Button obj) {
        String hql = "select new Button(code) from Button where code = :code";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", obj.getCode());
        return query.list();
    }

    /**
     * 获取所有权限
     *
     * @return
     */
    public List<Button> listButton() {
        String hql = " from Button ";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    /**
     * 获取所有权限
     *
     * @return
     */
    public List<Button> listButtons() {
        String hql = " from Button b where b.menuCode in (select new Menu(code) from Menu )";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    /**
     * 根据用户ID查询角色，根据角色获取权限
     *
     * @return
     */
    public List<Button> listButton(Long userId) {
        String sql = "SELECT DISTINCT p.* FROM sys_Button p  " +
                "WHERE p.id IN (SELECT rmp.Button_id FROM sys_role_menu_Button rmp " +
                "WHERE rmp.role_id IN (SELECT ur.role_id FROM sys_user_role ur " +
                "WHERE ur.user_id = " + userId + "))";
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        query.addEntity(Button.class);
        return query.list();
    }

    /**
     * 根据用户ID查询角色，根据角色和菜单ID获取权限
     *
     * @return
     */
    public List<Button> listButton(Long userId, Long menuId) {
        String sql = "SELECT DISTINCT p.* FROM sys_Button p  " +
                "WHERE p.menu_id = " + menuId + " AND p.id IN (SELECT rmp.Button_id FROM sys_role_menu_Button rmp " +
                "WHERE rmp.role_id IN (SELECT ur.role_id FROM sys_user_role ur " +
                "WHERE ur.user_id = " + userId + "))";
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        query.addEntity(Button.class);
        return query.list();
    }

    /**
     * 根据权限删除所有权限分配
     *
     * @param ButtonIds
     */
    public void delMenuResourceButtonId(Long[] ButtonIds) {
        String hql = "delete RoleMenu where ButtonId in :ButtonIds";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ButtonIds", ButtonIds);
        query.executeUpdate();
    }
}