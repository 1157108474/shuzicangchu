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
     * �����б��ѯ����
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
     * ���浥������Ҫ��������
     **/
    public void saveSheetCkDetail(SheetCKDETAIL obj) {
        Date now = new Date();
        //�õ���ǰ�û�ID
        long currentUserId = getThreadLocal().getCurrentUserId();

        //������������
        baseDao.save(obj);
    }

    /**
     * ���ݵ���Id��ѯ������ϸ
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
     * �޸ĵ�����ϸ����
     *
     * @return
     */
    public void updateSheetCKdetail(SheetCKDETAIL obj) {
        baseDao.update(obj);
    }

    // ������

    public int updateSheetStauts(int processInstanceId, int status) {//�޸ĵ���״̬Ϊ�����
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
     * ��ȡ���ⵥ��
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
     * ���³ɱ�״̬
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
     * ���³ɱ���ϸ
     * @param id
     */
    public void renewalCostDetail(Integer id,Double noTaxPrice,Double notAxSum) {
        String sql = "update WZ_SHEETCKDETAIL set NOTAXPRICE = "+noTaxPrice+",NOTAXSUM = "+notAxSum+" where id = "+id;
        Query query = this.baseDao.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    /**
     * ��ȡ���³ɱ���Ϊ���³ɹ��ĵ���
     */
    public List<SheetCK> querySheetCKRenewalCost() {
        String hql = " from SheetCK where  extendint3 <> :renewalCost and status =:status";
        Query query = baseDao.createQuery(hql);
        query.setParameter("renewalCost", RenewalCostEnum.SUCCESS.getRenewalCost());
        query.setParameter("status", 41);
        return query.list();
    }

    /**
     * ���ݵ���ID��ȡ��ϸ
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