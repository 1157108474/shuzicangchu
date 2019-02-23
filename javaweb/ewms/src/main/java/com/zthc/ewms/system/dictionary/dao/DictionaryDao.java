package com.zthc.ewms.system.dictionary.dao;

import com.zthc.ewms.system.dictionary.dao.guard.DictionaryDaoGuard;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class DictionaryDao extends DictionaryDaoGuard {
    public List<Dictionary> listDics() {
        String hql = "select new Dictionary(id,code,name,parent) from Dictionary d " +
                "where d.status != :status and d.id!=0  ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        return query.list();
    }

    public List<Dictionary> listDicsByParentId(int parentId) {
        String hql = "select new Dictionary(id,code,name,parent) from Dictionary d " +
                "where d.status = :status and d.parent.id =:parentId order by d.id";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.ENABLE.getCode());
        query.setParameter("parentId", parentId);
        List<Dictionary> dics =  query.list();
        return dics;
    }


    public Dictionary getDic(Integer id) {

        String hql = "select new Dictionary(d.id,d.code,d.name,parent, d.status,d.sort,d.memo) " +
                " from Dictionary d  where  d.id=:id ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        List<Dictionary> list = query.list();
        if (list != null && list.size() == 1) {
            return list.get(0);

        } else {
            return null;
        }
    }

    public Dictionary getDicButton(Integer id) {

        String hql = "select new Dictionary(d.id,d.code,d.name, d.btnFun) " +
                " from Dictionary d  where  d.id=:id order by d.id";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        List<Dictionary> list = query.list();
        if (list != null && list.size() == 1) {
            return list.get(0);

        } else {
            return null;
        }
    }

    public int updateDic(Dictionary dic) {
        String hql = "update Dictionary set code = :code, name = :name,status = :status,sort = :sort, memo= :memo, " +
                "updater = :updater, updateDate= :updateDate where id=:id";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", dic.getId());
        query.setParameter("name", dic.getName());
        query.setParameter("code", dic.getCode());
        query.setParameter("status", dic.getStatus());
        query.setParameter("sort", dic.getSort());
        query.setParameter("memo", dic.getMemo());
        query.setParameter("updater", dic.getUpdater());
        query.setParameter("updateDate", dic.getUpdateDate());
        return query.executeUpdate();
    }

    //αɾ
    public int deleteDictionary(Integer id, Integer userId) {

        String hql = "update Dictionary set status = :status , updater = :updater , updateDate=:updateDate where id=:id";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("updater", userId);
        query.setParameter("updateDate", new Date());

        return query.executeUpdate();
    }

    public boolean checkNotExit(Dictionary obj) {
        String hql = "from Dictionary where (name = :name or code =:code) and status != :status ";
        if (obj.getId() != null) {
            hql += " and id != :id";
        }
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("name", obj.getName());
        query.setParameter("code", obj.getCode());
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        if (obj.getId() != null) {
            query.setParameter("id", obj.getId());
        }

        List<Dictionary> list = query.list();
        return list == null || list.size() == 0;
    }

    public Dictionary getDictionaryByCode(String code) {
        String hql = "from Dictionary where  code =:code and status != :status ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("code", code);

        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());

        List<Dictionary> list = query.list();
        if (list != null && list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public Dictionary getDictionaryByName(String name) {
        String hql = "from Dictionary where  name =:name and status != :status ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("name", name);

        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());

        List<Dictionary> list = query.list();
        if (list != null && list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

}