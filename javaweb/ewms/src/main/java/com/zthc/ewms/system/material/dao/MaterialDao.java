package com.zthc.ewms.system.material.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.material.dao.guard.MaterialDaoGuard;
import com.zthc.ewms.system.material.entity.guard.Material;
import com.zthc.ewms.system.material.entity.guard.MaterialCondition;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MaterialDao extends MaterialDaoGuard {
    public LayuiPage<Material> listMaterial(Material obj, MaterialCondition condition, Integer orgId,String spareCode) throws Exception {
        LayuiPage<Material> ret = new LayuiPage<>();
/*
        String hql_data = "select new Material(m.id,m.code,m.description,m.price,m.unit,m.stockUp,m.stockDown,m.enableSN,m.status,m.name,m.brand,m.models,m.specifications) ";
*/

        String hql_count = "select count(m.id) ";
        //String append = " from Material m , Organization o where m.status !=:status and m.ztid = o.ztId ";
        String append = " from Material m where m.status !=:status";// and o.id = :orgId";
//                "and t.supplySystem = s.id and t.callSystem = c.id";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", DictionaryEnums.Status.DELETE.getCode());
//        param.put("orgId", orgId);
        if (!StringUtils.isEmpty(spareCode)) {
            append += " and m.code like  :spareCode";
            param.put("spareCode", spareCode + "%");
        }
        if (obj.getSparescateId()!= null) {
            append += " and m.sparescateId =  :sparescateId";
            param.put("sparescateId", obj.getSparescateId());
        }
        if (!StringUtils.isEmpty(obj.getCode())) {
            append += " and m.code like :code";
            param.put("code", "%" + obj.getCode() + "%");
        }
        if (!StringUtils.isEmpty(obj.getDescription())) {
            append += " and m.description like :description";
            param.put("description", "%" + obj.getDescription() + "%");
        }
        if (!StringUtils.isEmpty(obj.getName())) {
            append += " and m.name like :name";
            param.put("name", "%" + obj.getName() + "%");
        }

//        if(!StringUtils.isEmpty(name)){
//            append +="and name like '%:name%'";
//            param.put("name",name.trim());
//        }
        // ≈≈–Ú
        String order = " order by  m.id desc";

        List<Material> materials = baseDao.findByHql( append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(materials);
        return ret;
    }
    public int updateMaterial(Material material) {
        String hql = "update Material set  name = :name,code=:code,specifications=:specifications," +
                "model=:model,brand=:brand,price=:price,unit=:unit,stockUp=:stockUp,stockDown=:stockDown," +
                "isUseAlarm=:isUseAlarm,providerId=:providerId,ztid=:ztid,configMemo=:configMemo, " +
                "status= :status,description=:description,sort=:sort," +
                "updater = :updater, updateDate= :updateDate where id=:id";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", material.getId());
        query.setParameter("name", material.getName());
        query.setParameter("code",material.getCode());
        query.setParameter("specifications",material.getSpecifications());
        query.setParameter("model",material.getModel());
        query.setParameter("brand",material.getBrand());
        query.setParameter("price",material.getPrice());
        query.setParameter("unit",material.getUnit());
        query.setParameter("stockUp",material.getStockUp());
        query.setParameter("stockDown",material.getStockDown());
        query.setParameter("isUseAlarm",material.getIsUseAlarm());
        query.setParameter("providerId",material.getProviderId());
        query.setParameter("ztid",material.getZtid());
        query.setParameter("configMemo",material.getConfigMemo());
        query.setParameter("status",material.getStatus());
        query.setParameter("description",material.getDescription());
        query.setParameter("sort",material.getSort());
        query.setParameter("updater",material.getUpdater());
        query.setParameter("updateDate",material.getUpdateDate());
        return query.executeUpdate();
    }


    //Œ±…æ
    public int deleteMaterial(Integer[] ids, Integer userId) {

        String hql = "update Material set status = :status , updater = :updater , updateDate=:updateDate where id in (:ids)";
        Query query = this.baseDao.createQuery(hql);
        query.setParameterList("ids", ids);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("updater", userId);
        query.setParameter("updateDate", new Date());

        return query.executeUpdate();
    }

    public boolean checkNotExit(Material obj) {
//        String hql = "from Material where ( name = :name or  code =:code)  and status != :status";
        String hql = "from Material where  code =:code and status != :status";
        if (obj.getId() != null) {
            hql += " and id != :id ";
        }
        if (obj.getZtid() != null) {
            hql += " and ztid = :ztid ";
        }else{
            hql += " and ztid is null ";
        }

        Query query = this.baseDao.createQuery(hql);
        //  query.setParameter("name", obj.getName());
        query.setParameter("code", obj.getCode());
        if (obj.getZtid() != null) {
            query.setParameter("ztid", obj.getZtid());
        }
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        if (obj.getId() != null) {
            query.setParameter("id", obj.getId());
        }
        List<Material> list = query.list();
        return list == null || list.size() == 0;
    }

    public Material getMaterial(Integer id) {

//        String hql = "select new Material(m.id,m.code,m.name,m.sparescateId,m.specifications,m.model,m.brand," +
//                "m.price,m.unit,m.stockUp,m.stockDown,m.isUseAlarm,m.providerId, m.ztid," +
//                "o.name,m.configMemo,m.description,m.sort,m.status,m.memo,p.code)  " +
//                "from Material m , Organization o,  Sparepartscate p " +
//                "where m.id =:id and m.ztid = o.ztId  and m.sparescateId = p.id";
       //20180915  »• ztid
        String hql = "select new Material(m.id,m.code,m.name,m.sparescateId,m.specifications,m.model,m.brand," +
                "m.price,m.unit,m.stockUp,m.stockDown,m.isUseAlarm,m.providerId, m.ztid," +
                "m.configMemo,m.description,m.sort,m.status,m.memo,p.code)  " +
                "from Material m , Sparepartscate p " +
                "where m.id =:id   and m.sparescateId = p.id";
//
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        return (Material) query.list().get(0);


    }

    public Material getMaterialByCode(String code) {
        String hql = "from Material where code =:code order by status desc";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("code",code);
        List<Material> list = query.list();
        if(list!=null && list.size()>0){
            return  list.get(0);
        }else{
            return null;
        }
    }
}