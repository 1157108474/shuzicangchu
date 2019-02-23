package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.hibernate.ComDao;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.Condition;
import com.zthc.ewms.sheet.dao.guard.SheetApplyDaoGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SheetApplyDao extends SheetApplyDaoGuard {
    @Autowired
    ComDao comDao;
    /**
     * 公用列表查询方法
     * @param className
     * @param key
     * @param param
     * @param condition
     * @param <T>
     * @return
     */
    public <T> LayuiPage<T> publicDetails(String className,String key, Map<String, Object> param, Condition condition) {
        LayuiPage<T> ret = new LayuiPage<>();
        String hql_count = "select count("+key+") ";
        String append = " from "+ className +"  where 1=1 "+condition.getQueryCriteria();
        List<T> list = baseDao.findByHql( append + condition.getOrderBys(), param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(list);
        return ret;
    }

    /**
     * 公用列表查询方法
     * @param className
     * @param param
     * @param condition
     * @param <T>
     * @return
     */
    public <T> List<T> listDetails(String className, Map<String, Object> param, Condition condition) {
        String append = " from "+ className +"  where 1=1 "+condition.getQueryCriteria();
        return comDao.list(append + condition.getOrderBys(),param);
    }

}