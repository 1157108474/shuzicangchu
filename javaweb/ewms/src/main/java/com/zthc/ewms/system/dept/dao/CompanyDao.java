package com.zthc.ewms.system.dept.dao;


import com.zthc.ewms.system.dept.dao.guard.CompanyDaoGuard;
import com.zthc.ewms.system.dept.entity.guard.Company;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CompanyDao extends CompanyDaoGuard {

    /**
     * ���ݹ�˾��Ż�ȡ��˾��Ϣ
     *
     * @return
     */
    public List<Company> listCompanyCode(Company obj) {
        String hql = "select new Company(code) from Company where code = :code and id != :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", obj.getCode());
        query.setParameter("id", obj.getId());
        return query.list();
    }
}