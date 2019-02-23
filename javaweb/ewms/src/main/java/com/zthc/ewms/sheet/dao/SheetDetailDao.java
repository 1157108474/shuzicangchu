package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.Condition;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.SheetDetailDaoGuard;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.sheet.entity.guard.SheetStock;
import com.zthc.ewms.sheet.entity.order.OrderDetails;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SheetDetailDao extends SheetDetailDaoGuard {

    public <T> LayuiPage<T> addDetailList(SheetDetail obj, SheetDetailCondition condition, Integer ztId, String className) {
        LayuiPage<T> ret = new LayuiPage<>();
        String hql_count = "select count(id) ";
        String append = " from " + className;

        if ("YkywList".equals(className)) {
            append += "  where isCount > 0 and houseCode is not null ";
        }else if("DbdList".equals(className)){
            append += "  where isCount>0 ";
        } else if ("ThList".equals(className)) {
            append += "  where subDetailCount >  ytCount";
        } else if ("ZJTKDetail".equals(className)) {
            append += "  where detailCount >  haveTkCount";
        } else {
            append += "  where 1=1 ";
        }


        Map<String, Object> param = new HashMap<String, Object>();
        if (ztId != null) {
            append += " and ztId = :ztId  ";
            param.put("ztId", ztId);
        }
//        param.put("orgId", orgId);
        if (obj.getStoreId() != null) {
            append += " and storeId =  :storeId";
            param.put("storeId", obj.getStoreId());
        }
        if (obj.getStoreLocationId() != null) {
            append += " and storeLocationId =  :storeLocationId";
            param.put("storeLocationId", obj.getStoreLocationId());
        }
        if (!StringUtils.isEmpty(obj.getMaterialCode())) {
            append += " and materialCode like :materialcode";
            param.put("materialcode", "%" + obj.getMaterialCode().trim() + "%");
        }
        if(!StringUtils.isEmpty(obj.getDescription())){
            append += " and description like :description";
            param.put("description", "%" + obj.getDescription().trim() + "%");
        }
        if (obj.getProviderDepId() != null) {
            append += " and providerDepId = :providerDepId";
            param.put("providerDepId", obj.getProviderDepId());
        }
        if(obj.getSheetId() != null){
            append += " and sheetId = :sheetId";
            param.put("sheetId", obj.getSheetId());
        }

        if(obj.getOwnerType()!=null){
            append += " and ownerType = :ownerType";
            param.put("ownerType", obj.getOwnerType());
        }

//        if(obj.getSonId()!=null){
//            append += " and sonId = :sonId";
//            param.put("sonId", obj.getSonId());
//        }

        if(obj.getSnCode()!=null && !"".equals(obj.getSnCode())){
            append += " and snCode = :snCode";
            param.put("snCode", obj.getSnCode());
        }
        // 排序
        String order = " order by  id desc";

        List<T> list = baseDao.findByHql(append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(list);
        return ret;
    }


    public <T> LayuiPage<T> sheetDetails(Integer sheetId, SheetDetailCondition condition, String className) {
        LayuiPage<T> ret = new LayuiPage<>();
//        String hql_data = "";
        String hql_count = "select count(id) ";
        String append = " from " + className + "  where sheetId = :sheetId  ";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("sheetId", sheetId);

        // 排序
        String order = " order by  id desc";

        List<T> list = baseDao.findByHql(append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(list);
        return ret;
    }

    public <T> List<T> sheetDetails(Integer sheetId, String className) {

        String hql = " from " + className + "  where sheetId = :sheetId  ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("sheetId",sheetId);

        return query.list();
    }
    public <T> LayuiPage<T> sheetSubDetails(Integer sheetId,String materialCode,Integer storeId, SheetDetailCondition condition, String className) {
        LayuiPage<T> ret = new LayuiPage<>();
//        String hql_data = "";
        String hql_count = "select count(id) ";
        String append = " from " + className + "  where sheetId = :sheetId and materialCode = :materialCode and storeId = :storeId ";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("sheetId", sheetId);
        param.put("materialCode", materialCode);
        param.put("storeId", storeId);

        // 排序
        String order = " order by  id desc";

        List<T> list = baseDao.findByHql(append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(list);
        return ret;
    }


    public <T> LayuiPage<T> listDetails(Map<String, Object> param, Condition condition, String className) {
        LayuiPage<T> ret = new LayuiPage<>();
//        String hql_data = "";
        String hql_count = "select count(id) ";
        String append = " from " + className + "  where 1=1  ";
        append += condition.getQueryCriteria();

        // 排序
        String order = " order by  id desc";

        List<T> list = baseDao.findByHql(append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(list);
        return ret;
    }

    /**
     * 修改物料申领明细
     *
     * @param obj
     * @return
     */
    public int updateApplySheet(SheetDetail obj) {
        Date now = new Date();
        long currentUserId = getThreadLocal().getCurrentUserId();
        String hql = "update SheetDetail set extendString2 = :extendString2,updator = :updator,updateDate = " +
                ":updateDate " +
                "where 1=1 and id = :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("extendString2", obj.getExtendString2());
        query.setParameter("updator", (int) currentUserId);
        query.setParameter("updateDate", now);
        query.setParameter("id", obj.getId());

        return query.executeUpdate();
    }

    /**
     * 删除明细方法
     **/
    public int delDetails(SheetDetailCondition condition) {
        String hql = "delete SheetDetail where id in :ids";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids", condition.getIds());
        return query.executeUpdate();
    }

    /**
     * 删除明细方法
     **/
    public int delRKDetails(SheetDetailCondition condition) {
        String hql = "delete SheetRKDETAIL where id in :ids";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids", condition.getIds());
        return query.executeUpdate();
    }

    public int delZRDetails(SheetDetailCondition condition) {
        String hql = "delete SheetRKDETAIL where id in :ids";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids", condition.getIds());
        return query.executeUpdate();
    }

    /**
     * 删除单据明细方法
     **/
    public void delSheetDetail(Integer sheetId) {
        String hql = "delete SheetRKDETAIL where sheetId = :sheetId";
        Query query = baseDao.createQuery(hql);
        query.setParameter("sheetId", sheetId);
        query.executeUpdate();
    }
    public int delDetailsBySheetId(Integer id) {
        String hql = "delete SheetDetail where sheetId = :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("id",id);
        return query.executeUpdate();
    }

    public <T> T getDetail(Integer id,String className ) {
        String hql = " from " + className + "  where id=:id  ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("id",id);
        return (T)query.list().get(0);
    }

    public <T> List<T> printDetails(Integer id, String where, String className) {
        String hql = " from " + className + "  where sheetId=:id  ";
        if(where !=null){
            hql +=" and "+ where;
        }
        Query query = baseDao.createQuery(hql);
        query.setParameter("id", id);
        return query.list();
    }

    public List<OrderDetails> printOrderDetails(String orderNum) {
        String hql = " from OrderDetails where ordernum = :ordernum ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("ordernum", orderNum);
        return query.list();
    }

    public List<OrderDetails> getOrderDetailOne(String ordernum) {
        String hql = " from OrderDetails where ordernum = :ordernum";
        Query query = baseDao.createQuery(hql);
        query.setParameter("ordernum", ordernum);
        return query.list();
    }

    /**
     * 获取库存数量
     * @param materialCode
     * @return
     */
    public long countSheetStockByCode(String materialCode,Integer storeLocationId){
        String hql ="select count(storeCount) from SheetStock where materialCode=:materialCode and storeLocationId=:storeLocationId";
        Query query = baseDao.createQuery(hql);
        query.setParameter("materialCode",materialCode);
        query.setParameter("storeLocationId",storeLocationId);


        long count = (long) query.list().get(0);
        return count;
    }


    /**保存单条，主要用于新增**/
    public void saveSheetStock(SheetStock obj) {
        baseDao.save(obj);
    }
}