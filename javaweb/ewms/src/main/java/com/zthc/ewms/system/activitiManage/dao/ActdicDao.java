package com.zthc.ewms.system.activitiManage.dao;

import com.hckj.base.database.hibernate.BaseDao;
import com.zthc.ewms.system.activitiManage.entity.ActDic;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ActdicDao{


    @Resource
    public BaseDao baseDao;


    public List<ActDic> findAll() {
        String hql = "from ActDic";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    public ActDic getActDicBydicID(String id) {
        String hql = "from ActDic where dicID = "+id;
        Query query = baseDao.createQuery(hql);
        if( query.list().size()>0){
            ActDic actDic = (ActDic) query.list().get(0);
            return actDic;
        }else{
            return null;
        }
    }

    public void insertActDic(ActDic actDic) {
        baseDao.save(actDic);
    }

    public void updateActDicBymodelId(String modelId,String id) {
        String hql = "from ActDic a where a.modelID = " + modelId;
        Query query = baseDao.createQuery(hql);
        ActDic actDic = (ActDic) query.list().get(0);
        actDic.setDepId(id);
        baseDao.update(actDic);
    }

    public ActDic getDicBydepid(String id) {
        String hql = "from ActDic a  where a.depId = " + id;
        Query query = baseDao.createQuery(hql);
        ActDic actDic = (ActDic) query.list().get(0);
        return actDic;
    }

    public List<ActDic> getActDicByModelID(String modelId) {
        String hql = "from ActDic a  where a.modelID = " + modelId;
        Query query = baseDao.createQuery(hql);
        return query.list();
    }
}
