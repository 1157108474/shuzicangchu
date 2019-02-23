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
     * ��ȡ���в�����Ϣ
     **/
    public List<Depart> listDepart(Depart obj) {
        String hql = " from Depart d  ";
        hql += " where 1=1  ";
        //���ű��
        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and code like :code";
        }
        //��������
        if (!StringUtils.isEmpty(obj.getName())) {
            hql += " and name like :name";
        }
        // ����
        hql += " order by sort asc";
        Query query = baseDao.createQuery(hql);
        //���ű��
        if (!StringUtils.isEmpty(obj.getCode())) {
            query.setParameter("code", "%%" + obj.getCode().trim() + "%%");
        }
        //��������
        if (!StringUtils.isEmpty(obj.getName())) {
            query.setParameter("name", "%%" + obj.getName().trim() + "%%");
        }
        return query.list();
    }

    /**
     * ����״̬��ȡ���в��ŵ�ID������
     **/
    public List<Depart> listDepartName(int state) {
        String hql = "select new Depart(id,name) from Depart  where status = :state order by sort asc";
        Query query = baseDao.createQuery(hql);
        query.setParameter("state", state);
        return query.list();
    }

    /**
     * ����״̬��ȡ���в���
     **/
    public List<Depart> listDepart(int state) {
        String hql = "from Depart where status = :state order by sort asc";
        Query query = baseDao.createQuery(hql);
        query.setParameter("state", state);
        return query.list();
    }

    /**
     * ���ݿ����֯ID��ȡ
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