package com.zthc.ewms.system.dept.service.guard;

import com.zthc.ewms.system.dept.dao.CompanyDao;
import com.zthc.ewms.system.dept.entity.guard.Company;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class CompanyServiceGuard {
    @Resource(name = "companyDao")
    public CompanyDao dao;

    /**************************  基础方法   ***************************/

    //新增
    @Transactional
    public void saveCompany(Company obj) {
        this.dao.saveCompany(obj);
    }

    // 查
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Company getCompanyOne(Integer id) {
        return this.dao.getCompanyOne(id);
    }

    @Transactional
    public int updateCompany(Company obj) {
        return this.dao.updateCompany(obj);
    }

    // 删
    @Transactional
    public void delCompany(Integer id) {
        this.dao.delCompany(id);
    }

}