package com.zthc.ewms.system.spare.dao;

import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.spare.dao.guard.SparepartscateDaoGuard;
import com.zthc.ewms.system.spare.entity.guard.Sparepartscate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class SparepartscateDao extends SparepartscateDaoGuard {
    public List<Sparepartscate> listSpares(Integer parentId,Integer[] ids) {
        String hql = "select new Sparepartscate(d.id,d.code,d.name) from Sparepartscate d  " +
                "where d.status != :status and d.parentId = :parentId  ";
        if(ids != null && ids.length !=0){
            hql += " and d.id not in :ids ";
        }
        hql += " order by d.code asc";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("parentId", parentId);
        if(ids != null && ids.length !=0){
            query.setParameterList("ids",ids);
        }

        return query.list();
    }

    public List<Sparepartscate> listSpareparts(Integer parentId,Integer[] ids) {
        String hql = "select new Sparepartscate(d.id,d.code,d.name) from Sparepartscate d  " +
                "where d.status = :status and d.parentId = :parentId  ";
        if(ids != null && ids.length !=0){
            hql += " and d.id not in :ids ";
        }
        hql += " order by d.code asc";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.ENABLE.getCode());
        query.setParameter("parentId", parentId);
        if(ids != null && ids.length !=0){
            query.setParameterList("ids",ids);
        }

        return query.list();
    }
    public List<Sparepartscate> listOfficeScope(Integer useId){
        String hql_left = " select new Sparepartscate(d.id,d.code,d.name) ";
        String hql = " from Sparepartscate d ,OfficesScope o where d.status != :status and d.parentId = 0 ";
        hql += " and o.officesId = :officesid and o.scopeId = d.id ";
        hql += " order by d.code asc";
        Query query = this.baseDao.createQuery(hql_left + hql);
        query.setParameter("status",DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("officesid",useId);
        return query.list();
    }

    public Sparepartscate getSpare(Integer id,Integer level) {
       if(level>1) {
           String hql = "select new Sparepartscate(d.id,d.code,d.name,p.id,p.code,p.name, d.status,d.sort,d.memo) " +
                   " from Sparepartscate d , Sparepartscate p where  d.id=:id and d.parentId = p.id ";
           Query query = this.baseDao.createQuery(hql);
           query.setParameter("id", id);
           List<Sparepartscate> list = query.list();
           if (list != null && list.size() == 1) {
               return list.get(0);
           } else {
               return null;
           }
       }else{
           String hql = "select new Sparepartscate(d.id,d.code,d.name, d.status,d.sort,d.memo) " +
                   " from Sparepartscate d  where  d.id=:id ";
           Query query = this.baseDao.createQuery(hql);
           query.setParameter("id", id);
           List<Sparepartscate> list = query.list();
           if (list != null && list.size() == 1) {
               return list.get(0);
           } else {
               return null;
           }
       }
    }

    public int updateSpare(Sparepartscate Spare) {
        String hql = "update Sparepartscate set code = :code, name = :name,status = :status,sort = :sort, memo= :memo, " +
                "updater = :updater, updateDate= :updateDate where id=:id";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", Spare.getId());
        query.setParameter("name", Spare.getName());
        query.setParameter("code", Spare.getCode());
        query.setParameter("status", Spare.getStatus());
        query.setParameter("sort", Spare.getSort());
        query.setParameter("memo", Spare.getMemo());
        query.setParameter("updater", Spare.getUpdater());
        query.setParameter("updateDate", Spare.getUpdateDate());
        return query.executeUpdate();
    }

    //α?
    public int deleteSparepartscate(Integer id, Integer userId) {

        String hql = "update Sparepartscate set status = :status , updater = :updater , updateDate=:updateDate where id=:id";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("updater", userId);
        query.setParameter("updateDate", new Date());

        return query.executeUpdate();
    }

    public boolean checkNotExit(Sparepartscate obj) {
        String hql = "from Sparepartscate where (name = :name or code =:code) and status != :status";
        if(obj.getId() != null){
            hql +=" and id != :id";
        }
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("name",obj.getName());
        query.setParameter("code",obj.getCode());
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        if(obj.getId() != null){
            query.setParameter("id",obj.getId());
        }
        List<Sparepartscate> list = query.list();
        return list == null || list.size() == 0;
    }
    /**
     * 获取单条数据
     */
     public Sparepartscate getSparepartscate (String code) {
            Sparepartscate res = null;
            String hql = " from Sparepartscate where 1 = 1 and code = :code";
            Query query = baseDao.createQuery(hql);
            query.setParameter("code", code);
            if (null != query.list() && query.list().size() > 0) {
                res = (Sparepartscate) query.list().get(0);
            }
            return res;
        }


} 