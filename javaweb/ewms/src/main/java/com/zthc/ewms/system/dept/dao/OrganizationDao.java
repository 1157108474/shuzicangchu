package com.zthc.ewms.system.dept.dao;


import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dept.dao.guard.OrganizationDaoGuard;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class OrganizationDao extends OrganizationDaoGuard {

    /**
     * ��ȡ������֯��Ϣ
     **/
    public List<Organization> listOrganization(Organization obj) {
        String hql = " from Organization o  ";

        hql += " where 1=1 and status = :status and parentid=0";

        Map<String, Object> param = new HashMap<>();
        //���ű��
        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and code like :code";
        }
        //��������
        if (!StringUtils.isEmpty(obj.getName())) {
            hql += " and name like :name";
            param.put("name", "%%" + obj.getName().trim() + "%%");
        }
        // ����
        hql += " order by sort asc";

        Query query = baseDao.createQuery(hql);
        query.setParameter("status",DictionaryEnums.Status.ENABLE.getCode());
        //���ű��
        if (!StringUtils.isEmpty(obj.getCode())) {
            query.setParameter("code", "%%" + obj.getCode().trim() + "%%");
        }
        //��������
        if (!StringUtils.isEmpty(obj.getName())) {
            query.setParameter("name", "%%" + obj.getName().trim() + "%%");
            param.put("name", "%%" + obj.getName().trim() + "%%");
        }
        return query.list();
    }

    /**
     * ������֯��Ż�ȡ��֯��Ϣ
     *
     * @return
     */
    public List<Organization> listOrganizationCode(Organization obj) {
        String hql = "select new Organization(code) from Organization where code = :code and id != :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", obj.getCode());
        query.setParameter("id", obj.getId());
        return query.list();
    }

    public List<Organization> listOrganization() {
        String hql = " from Organization ";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }
    public List<Organization> getOrganizationByCode(String[] strings) {
        String hql = "from Organization where code in :code ";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("code", strings);
       
        return query.list();
    }
    public Organization getOrganizationBy(String extendInt1) {
        Organization res = null;
        String hql = "from Organization where extendint1 = :extendint1 ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("extendint1", extendInt1);
        if(null !=query.list()&&query.list().size()>0){
            res = (Organization) query.list().get(0);
        }
        return res;
    }
}