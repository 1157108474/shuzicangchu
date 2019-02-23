package com.zthc.ewms.base.hibernate;

import com.hckj.base.database.hibernate.BaseDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class ComDao<T> {
    @Resource
    public BaseDao baseDao;

    /**
     * �б��ѯ����
     * @param hql:hql���
     * @param param:��ѯ������ֵ
     * @return
     */
    public List<T> list(String hql, Map<String, Object> param) {
        Query query = baseDao.createQuery(hql);
        if (param != null && !param.isEmpty()) {
            for (String key : param.keySet()) {
                query.setParameter(key, param.get(key));
            }
        }
        return query.list();
    }


}
