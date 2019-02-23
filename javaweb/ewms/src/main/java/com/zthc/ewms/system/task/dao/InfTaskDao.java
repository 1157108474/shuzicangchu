package com.zthc.ewms.system.task.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.task.entity.guard.InfTask;
import com.zthc.ewms.system.task.entity.guard.InfTaskCondition;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.zthc.ewms.system.task.dao.guard.InfTaskDaoGuard;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InfTaskDao extends InfTaskDaoGuard {
    public LayuiPage<InfTask> listInfTask(InfTaskCondition condition,String name) throws Exception {
        LayuiPage<InfTask> ret = new LayuiPage<>();
        String hql_data = "select new InfTask(t.id,t.name,t.triggerKind,t.kind,t.supply,t.call,t.syncKind,t.remark) ";
        String hql_count = "select count(t.id) " ;
        String append = " from InfTask t where t.status !=:status " ;

//                "and t.supplySystem = s.id and t.callSystem = c.id";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", DictionaryEnums.Status.DELETE.getCode());

        if(!StringUtils.isEmpty(name)){
            append +="and t.name like :name";
            param.put("name","%"+name.trim()+"%");
        }
        // 排序
        String order = " order by t.id desc";

        List<InfTask> tasks = baseDao.findByHql(hql_data + append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append , param);
        ret.setCount(total);
        ret.setData(tasks);
        return ret;
    }

    public int updateTask(InfTask task) {
        String hql = "update InfTask set  name = :name,triggerKind=:trigger,kind=:kind," +
                "call=:call,supply=:supply,syncKind=:sync, remark= :remark, " +
                "updater = :updater, updateDate= :updateDate where id=:id" ;
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id",task.getId());
        query.setParameter("name",task.getName());
        query.setParameter("trigger",task.getTriggerKind());
        query.setParameter("kind",task.getKind());
        query.setParameter("call",task.getCall());
        query.setParameter("supply",task.getSupply());
        query.setParameter("sync",task.getSyncKind());
        query.setParameter("remark",task.getRemark());
        query.setParameter("updater",task.getUpdater());
        query.setParameter("updateDate",task.getUpdateDate());
        return query.executeUpdate();
    }


    //伪删
    public int deleteTask(Integer[] ids,Integer userId) {

        String hql = "update InfTask set status = :status , updater = :updater , updateDate=:updateDate where id in (:ids)";
        Query query = this.baseDao.createQuery(hql);
        query.setParameterList("ids",ids);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("updater",userId);
        query.setParameter("updateDate",new Date());

        return query.executeUpdate();
    }

    public boolean checkNotExit(InfTask obj) {
        String hql = "from InfTask where name = :name and status != :status ";
        if(obj.getId() != null){
            hql +=" and id != :id";
        }
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("name",obj.getName());
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        if(obj.getId() != null){
            query.setParameter("id",obj.getId());
        }
        List<InfTask> list = query.list();
        return list == null || list.size() == 0;
    }
} 