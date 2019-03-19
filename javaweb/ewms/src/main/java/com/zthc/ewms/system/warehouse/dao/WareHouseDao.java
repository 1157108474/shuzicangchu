package com.zthc.ewms.system.warehouse.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.warehouse.dao.guard.WareHouseDaoGuard;
import com.zthc.ewms.system.warehouse.entity.guard.Location;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouseCondition;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WareHouseDao extends WareHouseDaoGuard {

    public List<WareHouse> treeWares(Integer parentId) {
        String hql = "select new WareHouse(d.id,d.code,d.name) from WareHouse d  " +
                "where d.status != :status   and d.parentId = :parentId ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("parentId", parentId);
//        query.setParameter("property", DictionaryEnums.Property.HOUSE.getCode());
        return query.list();
    }

    public LayuiPage<WareHouse> listWareHouse(WareHouse obj, WareHouseCondition condition, Integer orgId) throws Exception {
        LayuiPage<WareHouse> ret = new LayuiPage<>();
        String hql_data = "select new WareHouse(w.id,w.code,w.name,p.code,p.name,w.levelCount,w.property,w.sort,w.status,w.memo,o.name) ";
        String hql_count = "select count(w.id) ";
        String append = " from WareHouse w , WareHouse p ,Organization o where w.status !=:status and w.parentId = p" +
                ".id and w.ztId=o.id and p.status != :status";
//                "and t.supplySystem = s.id and t.callSystem = c.id";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", DictionaryEnums.Status.DELETE.getCode());
//
        if (orgId != null) {
            append += " and o.id = :orgId";
            param.put("orgId", orgId);
        }
        if (!StringUtils.isEmpty(obj.getId()) && obj.getId() != 0) {
            append += " and (w.id = :id or w.parentId = :id) ";
            param.put("id", obj.getId());
        }
        if (!StringUtils.isEmpty(obj.getCode())) {
            append += " and w.code like :code";
            param.put("code", "%" + obj.getCode() + "%");
        }
        // ≈≈–Ú
        String order = " order by  w.id ";

        List<WareHouse> wares = baseDao.findByHql(hql_data + append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(wares);
        return ret;
    }

    public List<WareHouse> listWareHouse(Integer orgId, Integer[] ids) throws Exception {

        String hql = "select new WareHouse(w.id,w.code,w.name,w.status,w.memo)  from WareHouse w  "
                + " where w.status =:status and w.parentId = :parentId ";
        if (ids != null && ids.length != 0) {
            hql += " and w.id not in :ids ";
        }
        if (orgId != null) {
            hql += " and w.ztId = :ztid ";
        }
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.ENABLE.getCode());
        query.setParameter("parentId", 0);
        if (orgId != null) {
            query.setParameter("ztid", orgId);
        }
        if (ids != null && ids.length != 0) {
            query.setParameterList("ids", ids);
        }
        return query.list();
    }

    public int updateWareHouse(WareHouse WareHouse) {
        String hql = "update WareHouse set  name = :name,code=:code,property=:property,ztId=:ztid," +
                "sort=:sort,status=:status,memo= :memo, updater = :updater, updateDate= :updateDate where id=:id";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", WareHouse.getId());
        query.setParameter("name", WareHouse.getName());
        query.setParameter("code", WareHouse.getCode());
        query.setParameter("property", WareHouse.getProperty());
        query.setParameter("sort", WareHouse.getSort());
        query.setParameter("status", WareHouse.getStatus());
        query.setParameter("memo", WareHouse.getMemo());
        query.setParameter("ztid", WareHouse.getZtId());
        query.setParameter("updater", WareHouse.getUpdater());
        query.setParameter("updateDate", WareHouse.getUpdateDate());
        return query.executeUpdate();
    }


    //Œ±…æ
    public int deleteWareHouse(Integer[] ids, Integer userId) {

        String hql = "update WareHouse set status = :status , updater = :updater , updateDate=:updateDate where id in (:ids)";
        Query query = this.baseDao.createQuery(hql);
        query.setParameterList("ids", ids);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("updater", userId);
        query.setParameter("updateDate", new Date());

        return query.executeUpdate();
    }
    //≤È—Ø
    public List<WareHouse> findWareHouseIds(Integer[] ids) {
        String hql = "from WareHouse where id in :ids";
        Query query = this.baseDao.createQuery(hql);
        query.setParameterList("ids", ids);

        return query.list();
    }

    public boolean checkNotExit(WareHouse obj) {
        String hql = "from WareHouse where (name = :name or code =:code) and status != :status and parentId = :parentId";
        if (obj.getId() != null) {
            hql += " and id != :id";
        }
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("name", obj.getName());
        query.setParameter("code", obj.getCode());
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("parentId", obj.getParentId());
        if (obj.getId() != null) {
            query.setParameter("id", obj.getId());
        }
        List<WareHouse> list = query.list();
        return list == null || list.size() == 0;
    }

    public WareHouse getWareHouse(Integer id) {

        String hql = "select new WareHouse(w.id,w.code,w.name,p.id,p.code,p.name,w.levelCount,w.property,w.sort,w.status,w.memo,w.ztId,w.erpId,w.whId,o.name)  " +
                " from WareHouse w , WareHouse p ,Organization o  " +
                " where w.id = :id  and p.id= w.parentId and w.ztId=o.id";
//
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        List<WareHouse> list = query.list();
        if (!list.isEmpty() && list.size() > 0) {
            return list.get(0);
        }
        return null;


    }

    public LayuiPage<Location> listPrintLocation(String name, String begin, String end, WareHouseCondition condition) {
        LayuiPage<Location> ret = new LayuiPage<>();
        String hql_count = "select count(code) ";
        String append = " from Location where 1=1  ";
//                "and t.supplySystem = s.id and t.callSystem = c.id";
        Map<String, Object> param = new HashMap<String, Object>();

        if (!StringUtils.isEmpty(name)) {
            append += " and ztName like :name ";
            param.put("name", "%" + name + "%");
        }
        if (!StringUtils.isEmpty(begin)) {
            append += " and code >= :begin";
            param.put("begin", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            append += " and code <= :end";
            param.put("end", end);
        }

        // ≈≈–Ú
        String order = " order by code";

        List<Location> locations = baseDao.findByHql(append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(locations);
        return ret;
    }

    public LayuiPage<WareHouse> listLocation(Integer parentId, Integer ztId, String name,String code, String parentCode,
                                             WareHouseCondition condition) {
        LayuiPage<WareHouse> ret = new LayuiPage<>();
        String hql_data = "select new WareHouse(l.id,l.code,l.name,o.name,p.id,p.code) ";
        String hql_count = "select count(l.id) ";
        String append = " from WareHouse l ,WareHouse p,Organization o where l.status =:status and p.status =:status and l.parentId=p.id   and l.ztId = o.id ";
//                "and t.supplySystem = s.id and t.callSystem = c.id";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", DictionaryEnums.Status.ENABLE.getCode());
        if (parentId != null) {
            append += " and l.parentId = :parentId";
            param.put("parentId", parentId.intValue());
        }
        if (!StringUtils.isEmpty(parentCode)) {
            append += " and p.code = :parentCode";
            param.put("parentCode", parentCode);
        }
        if (ztId != null) {
            append += " and l.ztId = :ztId";
            param.put("ztId", ztId.intValue());
        }
        if (!StringUtils.isEmpty(name)) {
            append += " and l.name like :name";
            param.put("name", "%" + name + "%");
        }
        if (!StringUtils.isEmpty(code)) {
        	append += " and l.code like :code";
        	param.put("code", "%" + code + "%");
        }
        // ≈≈–Ú
        String order = " order by l.code";

        List<WareHouse> locations = baseDao.findByHql(hql_data + append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(locations);
        return ret;
    }

    public List<WareHouse> getStores(Integer ztId) {
        String hql = "select new WareHouse(id,code,name) from  WareHouse  where status != :status and parentId = 0 ";
        if (ztId != null) {
            hql += " and ztId = :ztId";
        }
        Query query = this.baseDao.createQuery(hql);
        if (ztId != null) {
            query.setParameter("ztId", ztId);
        }
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        return query.list();
    }

    public WareHouse getWareHouseByCode(Integer storeId, String locationCode, Integer ztId) {
        String hql = "select new WareHouse(id,code,name) from  WareHouse  where status != :status and code like :code" +
                " and  parentId=:parentId ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("code", "%" + locationCode + "%");
//        query.setParameter("ztId",ztId);
        query.setParameter("parentId", storeId);
        List list = query.list();
        if (list.size() == 1) {
            return (WareHouse) list.get(0);
        } else {
            return null;
        }
    }

    public WareHouse getWareHouseById(Integer storeId, Integer id) {
        String hql = "select new WareHouse(id,code,name) from  WareHouse  where status != :status and id = :id" +
                " and  parentId=:parentId ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("code", id);
        query.setParameter("parentId", storeId);
        List list = query.list();
        if (list.size() == 1) {
            return (WareHouse) list.get(0);
        } else {
            return null;
        }
    }

    public WareHouse getWareHouse(String locationCode, Integer storeId, Integer ztId) {
        String hql = "select new WareHouse(id,code,name,parentId) from  WareHouse  where status != :status and code " +
                "like :code and parentId=:storeId and ztId = :ztId  and  property > :property  ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("code", locationCode);
        query.setParameter("property", DictionaryEnums.Property.HOUSE.getCode());
        query.setParameter("property", DictionaryEnums.Property.HOUSE.getCode());
        query.setParameter("property", DictionaryEnums.Property.HOUSE.getCode());
        List list = query.list();
        if (list.size() == 1) {
            return (WareHouse) list.get(0);
        } else {
            return null;
        }
    }

    public WareHouse getWareHouseCode(String code) {
        String hql = "from  WareHouse  where status != :status and code = :code  ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("code", code);
        List list = query.list();
        if (list.size() == 1) {
            return (WareHouse) list.get(0);
        } else {
            return null;
        }
    }

    public WareHouse getWareHouseByCodes(String locationCode) {
        String hql = " select new WareHouse(id,code,name,parentId) from WareHouse where status != :status and code " +
                " = :code ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        query.setParameter("code", locationCode.trim());
        List list = query.list();
        if (list.size() != 0) {
            return (WareHouse) list.get(0);
        } else {
            return null;
        }
    }

    public WareHouse getWareHouseByCode(String code) {
        String hql = "select new WareHouse(w.id,w.code,w.name,p.id,p.name,p.code) " +
                " from WareHouse w, WareHouse p where w.code =:code and w.parentId = p.id and p.status != :status";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("code", code);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        List<?> list = query.list();
        if (!list.isEmpty() && list.size() > 0) {
            return (WareHouse) list.get(0);
        } else {
            return null;
        }
    }

    public WareHouse getWareHouseByCodeAndStatus(String code, int status) {
        String hql = "select new WareHouse(w.id,w.code,w.name,p.id,p.name,p.code) " +
                " from WareHouse w, WareHouse p where w.code =:code and w.parentId = p.id and p.status = :status";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("code", code);
        query.setParameter("status", status);
        List<?> list = query.list();
        if (!list.isEmpty() && list.size() > 0) {
            return (WareHouse) list.get(0);
        } else {
            return null;
        }
    }

    public List<WareHouse> findByParentId(Integer storeId) {
        String hql = "from WareHouse  where parentId = :parentId and status != :status";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("parentId", storeId);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        return query.list();

    }

    public List<WareHouse> findByParentCode(String code) {
        String hql = "from WareHouse w where  w.status != :status and w.parentId in (select war.id from WareHouse war where war.code =:code)";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("code", code);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        return query.list();

    }

    public WareHouse getWareHouseName(String name) {
        String hql = "from WareHouse  where  name=:name";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("name", name);
        List<WareHouse> list= query.list();
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
    public Long ifFindByParentCode(String code) {
        String hql = " select count(1) from WareHouse w where w.status != :status and w.parentId in (select war.id from WareHouse war where war.code =:code)";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("code", code);
        query.setParameter("status", DictionaryEnums.Status.DELETE.getCode());
        return (Long) query.list().get(0);

    }


}