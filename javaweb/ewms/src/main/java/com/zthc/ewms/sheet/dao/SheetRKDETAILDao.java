package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.sheet.dao.guard.SheetRKDETAILDaoGuard;
import com.zthc.ewms.sheet.entity.guard.SheetRKDETAIL;
import com.zthc.ewms.sheet.entity.guard.SheetRKDETAILCondition;
import com.zthc.ewms.sheet.entity.guard.SheetRkSonDetail;
import com.zthc.ewms.sheet.entity.rk.RkDetails;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SheetRKDETAILDao extends SheetRKDETAILDaoGuard {

    public void saveSheetDetail(SheetRKDETAIL obj, SheetRKDETAILCondition condition) {
        baseDao.save(obj);
    }

    public void addSonDetail(SheetRkSonDetail obj, SheetRKDETAILCondition condition) {
        baseDao.save(obj);
    }

    /**
     * 删除已分配的明细
     *
     * @param id
     */
    public void deleteSonDetail(Integer id) {
        String hql = " delete SheetRkSonDetail where 1 = 1 and id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }


    public void deleteSonDetailByIds(Integer[] ids) {
        String hql = " delete SheetRkSonDetail where detailId in :ids";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }

    public List<RkDetails> getDetailId(Integer sheetId) {
        String hql = " from RkDetails where and sheetId = :sheetid ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("sheetid", sheetId);
        List<RkDetails> list = query.list();
        return list;
    }

    public void deleteSonDetailByDetailId(Integer id) {
        String hql = "delete SheetRkSonDetail where detailId in (select id from SheetRKDETAIL where sheetid = :id" + ")";
        Query query = baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void deleteDetailBySheetId(Integer id) {
        String hql = " delete SheetRKDETAIL where 1 = 1 and sheetid = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public List<SheetRKDETAIL> getDetailOne(Integer rid) {
        String hql = " from SheetRKDETAIL where sheetid = :rid ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("rid", rid);
        List<SheetRKDETAIL> ret = query.list();
        return ret;
    }


}