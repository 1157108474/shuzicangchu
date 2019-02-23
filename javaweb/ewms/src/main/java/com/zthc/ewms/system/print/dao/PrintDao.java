package com.zthc.ewms.system.print.dao;

import com.zthc.ewms.system.print.dao.guard.PrintDaoGuard;
import com.zthc.ewms.system.print.entity.guard.Print;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrintDao extends PrintDaoGuard {


    public List<Print> getPrints(Integer[] ids) {
        String hql = " select new Print(id,reportName,reportMemo) from Print  where id in :ids  " ;
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids", ids);
        return query.list();

    }
}