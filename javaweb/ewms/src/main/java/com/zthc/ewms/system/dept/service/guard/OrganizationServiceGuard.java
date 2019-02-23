package com.zthc.ewms.system.dept.service.guard;

import com.zthc.ewms.system.dept.dao.OrganizationDao;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class OrganizationServiceGuard {
    @Resource(name = "organizationDao")
    public OrganizationDao dao;

    /**************************  ��������   ***************************/

    //����
    @Transactional
    public void saveOrganization(Organization obj) {
        this.dao.saveOrganization(obj);
    }

    // ��
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Organization getOrganizationOne(Integer id) {
        return this.dao.getOrganizationOne(id);
    }

    //�޸�
    @Transactional
    public int updateOrganization(Organization obj) {
        return this.dao.updateOrganization(obj);
    }

    // ɾ
    @Transactional
    public void delOrganization(Integer id) {
        this.dao.delOrganization(id);
    }

}