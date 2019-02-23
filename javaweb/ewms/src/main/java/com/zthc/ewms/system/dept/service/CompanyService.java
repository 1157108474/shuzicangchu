package com.zthc.ewms.system.dept.service;


import com.zthc.ewms.system.dept.entity.guard.Company;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.service.guard.CompanyServiceGuard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends CompanyServiceGuard {


    /**
     * ���ݹ�˾��Ż�ȡ��˾��Ϣ
     *
     * @return
     */
    public List<Company> listCompanyCode(Company obj) {
        return this.dao.listCompanyCode(obj);
    }


}
