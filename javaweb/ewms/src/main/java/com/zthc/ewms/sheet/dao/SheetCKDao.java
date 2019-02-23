package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.Condition;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.SheetCKDaoGuard;
import com.zthc.ewms.sheet.entity.ck.ManageCK;
import com.zthc.ewms.sheet.entity.enums.RenewalCostEnum;
import com.zthc.ewms.sheet.entity.guard.SheetCK;
import com.zthc.ewms.sheet.entity.guard.SheetCKDETAIL;
import drk.util.TextUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SheetCKDao extends SheetCKDaoGuard {


    /**
     * 公用列表查询方法
     *
     * @param className
     * @param key
     * @param param
     * @param condition
     * @param <T>
     * @return
     */
    public <T> LayuiPage<T> publicDetails(String className, String key, Map<String, Object> param, Condition condition) {
        LayuiPage<T> ret = new LayuiPage<>();
        String hql_count = "select count(" + key + ") ";
        String append = " from " + className + "  where 1=1 " + condition.getQueryCriteria();
        List<T> list = baseDao.findByHql(append + condition.getOrderBys(), param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(list);
        return ret;
    }

    /**
     * 保存单条，主要用于新增
     **/
    public void saveSheetCkDetail(SheetCKDETAIL obj) {
        Date now = new Date();
        //得到当前用户ID
        long currentUserId = getThreadLocal().getCurrentUserId();

        //进行新增保存
        baseDao.save(obj);
    }

    /**
     * 根据单据Id查询单据明细
     *
     * @return
     */
    public List<SheetCKDETAIL> listSheetCKdetail(Integer sheetId) {
        String hql = " from SheetCKDETAIL where sheetId = :sheetId";
        Query query = baseDao.createQuery(hql);
        query.setParameter("sheetId", sheetId);
        return query.list();
    }

    /**
     * 修改单据明细方法
     *
     * @return
     */
    public void updateSheetCKdetail(SheetCKDETAIL obj) {
        baseDao.update(obj);
    }

    // 工作流

    public int updateSheetStauts(int processInstanceId, int status) {//修改单据状态为审核中
        String hql = "update SheetCK set  status= :status where routeId=:routeId";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", status);
        query.setParameter("routeId", processInstanceId);
        return query.executeUpdate();
    }

    public long getDetailCount(Object id) {

        Map<String, Object> params = new HashMap<>();
        params.put("sheetId", id);
        Long count = baseDao.countByHql("select count(1) from SheetCKDETAIL where sheetId = :sheetId", params);


        return count;
    }

    public int updateSheetCK(Integer id, String memo, String extendString1, Integer userId) {
        String hql = "update SheetCK set  memo= :memo, updator = :updator, updatedate= :updatedate ";
        if (!StringUtils.isEmpty(extendString1)) {
            hql += " ,extendString1 = :extendString1  ";
        }
        hql += " where id=:id ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("memo", memo);
        query.setParameter("updator", userId);
        query.setParameter("updatedate", new Date());
        if (!StringUtils.isEmpty(extendString1)) {
            query.setParameter("extendString1", extendString1);
        }
        return query.executeUpdate();
    }
    /**
     * 获取出库单据
     *
     * @param sheetId
     */
    public ManageCK getManageCKOne(Integer sheetId) {
        if (!TextUtil.isNotNull(sheetId)) {
            return null;
        } else {
            return baseDao.getById(ManageCK.class, sheetId);
        }
    }
    /**
     * 更新成本状态
     *
     * @param sheetId
     * @param renewalCost
     */
    public void renewalCost(Integer sheetId, Integer renewalCost) {
        String sql = "update WZ_SHEET_CK set extendint3 = "+renewalCost+" where id = "+sheetId;
        Query query = this.baseDao.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    /**
     * 更新成本明细
     * @param id
     */
    public void renewalCostDetail(Integer id,Double noTaxPrice,Double notAxSum) {
        String sql = "update WZ_SHEETCKDETAIL set NOTAXPRICE = "+noTaxPrice+",NOTAXSUM = "+notAxSum+" where id = "+id;
        Query query = this.baseDao.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    /**
     * 获取更新成本不为更新成功的单据
     */
    public List<SheetCK> querySheetCKRenewalCost() {
        String hql = " from SheetCK where  extendint3 <> :renewalCost and status =:status";
        Query query = baseDao.createQuery(hql);
        query.setParameter("renewalCost", RenewalCostEnum.SUCCESS.getRenewalCost());
        query.setParameter("status", 41);
        return query.list();
    }

    /**
     * 根据单据ID获取明细
     *
     * @param id
     * @return
     */
    public List<SheetCKDETAIL> printCKDetails(Integer id) {

        String hql = " select new SheetCKDETAIL(scd.sheetId,scd.sheetDetailId,scd.materialId,scd.materialCode,scd.materialName," +
                "scd.materialBrand,scd.materialModel,scd.detailUnit,scd.providerDepId, scd.memo,scd.noTaxPrice," +
                "scd.notaxsum,scd.storeLocationCode, scd.storeLocationId, scd.storeLocationName,scd.detailCount, " +
                "scd.detailUnitName, scd.storeId, wh.name) " +
                "from SheetCKDETAIL scd , WareHouse wh where wh.id =scd.storeId and scd.sheetId =:sheetId";
        Query query = baseDao.createQuery(hql);
        query.setParameter("sheetId", id);
        return query.list();
    }
}