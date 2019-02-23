package com.zthc.ewms.system.dept.dao;


import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dept.dao.guard.DepartDaoGuard;

import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.entity.guard.DepartEnums;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class DepartDao extends DepartDaoGuard {
    /**
     * 获取所有部门信息
     **/
    public List<Depart> listDepart(Depart obj) {
        String hql = " from Depart d  ";
        hql += " where 1=1  ";
        //部门编号
        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and code like :code";
        }
        //部门名称
        if (!StringUtils.isEmpty(obj.getName())) {
            hql += " and name like :name";
        }
        // 排序
        hql += " order by sort asc";
        Query query = baseDao.createQuery(hql);
        //部门编号
        if (!StringUtils.isEmpty(obj.getCode())) {
            query.setParameter("code", "%%" + obj.getCode().trim() + "%%");
        }
        //部门名称
        if (!StringUtils.isEmpty(obj.getName())) {
            query.setParameter("name", "%%" + obj.getName().trim() + "%%");
        }
        return query.list();
    }

    /**
     * 根据状态获取所有部门的ID和名称
     **/
    public List<Depart> listDepartName(int state) {
        String hql = "select new Depart(id,name) from Depart  where status = :state order by sort asc";
        Query query = baseDao.createQuery(hql);
        query.setParameter("state", state);
        return query.list();
    }

    /**
     * 根据状态获取所有部门
     **/
    public List<Depart> listDepart(int state) {
        String hql = "from Depart where status = :state order by sort asc";
        Query query = baseDao.createQuery(hql);
        query.setParameter("state", state);
        return query.list();
    }

    /**
     * 根据库存组织ID获取
     *
     * @param ztId
     * @return
     */
    public Depart getDepartByZtId(Integer ztId) {
        String hql = "from Depart where ztId = :ztId ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("ztId", ztId);
        List<?> list = query.list();
        if(!list.isEmpty()&&list.size()>0){
            return (Depart) list.get(0);
        }
        return null;

    }


    public List<Depart> listPublicDepart(Depart obj) {
        String hql = " from Depart   where  status = :status  order by sort asc";

        Query query = baseDao.createQuery(hql);
        query.setParameter("status",DepartEnums.StatusEnum.ENABLE.getStatus());

        return query.list();


    }
}