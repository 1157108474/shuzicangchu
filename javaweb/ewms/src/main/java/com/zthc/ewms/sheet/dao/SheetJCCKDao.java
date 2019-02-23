package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.SheetJCCKDaoGuard;
import com.zthc.ewms.sheet.entity.guard.*;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SheetJCCKDao extends SheetJCCKDaoGuard {
    /**
     * 保存单据
     *
     * @param obj
     * @param condition
     */
    public void saveSheet(SheetRK obj, SheetRKCondition condition) {
        baseDao.save(obj);
    }

    /**
     * 寄存出库单修改
     *
     * @param sheetDetail
     * @return
     */
    public int editSheetDatailed(SheetDetail sheetDetail) {
        String hql = " update SheetDetail set materialId=:materialId,materialCode=:materialCode," +
                "materialName=:materialName,materialSpecification=:materialSpecification,materialModel=:materialModel," +
                "detailCount=:detailCount,detailUnitName=:detailUnitName where id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("materialId", sheetDetail.getMaterialId());
        query.setParameter("materialCode", sheetDetail.getMaterialCode());
        query.setParameter("materialName", sheetDetail.getMaterialName());
        query.setParameter("materialSpecification", sheetDetail.getMaterialSpecification());
        query.setParameter("materialModel", sheetDetail.getMaterialModel());
        query.setParameter("detailCount", sheetDetail.getDetailCount());
        query.setParameter("detailUnitName", sheetDetail.getDetailUnitName());
        query.setParameter("id", sheetDetail.getId());
        return query.executeUpdate();

    }
    /**
     * 寄存入库单修改
     *
     * @param sheet
     * @return
     */
    public int editSheet(Sheet sheet) {
        String hql = " update Sheet set ownerDep=:ownerDep,usedManId=:usedManId,storeManId=:storeManId,memo=:memo  where id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("ownerDep", sheet.getOwnerDep());
        query.setParameter("usedManId", sheet.getUsedManId());
        query.setParameter("storeManId", sheet.getStoreManId());
        query.setParameter("memo", sheet.getMemo());
        query.setParameter("id", sheet.getId());
        return query.executeUpdate();

    }
    /**
     * 获取寄存物资出库单新增明细列表
     *
     * @param sheetJCCKList
     * @param condition
     * @return
     */
    public LayuiPage<SheetJCCKList> detailsList(SheetJCCKList sheetJCCKList, SheetCKCondition condition) {

        LayuiPage<SheetJCCKList> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql = " from SheetJCCKList where 1 = 1";

        if (!StringUtils.isEmpty(sheetJCCKList.getMaterialCode())) {
            hql += " and materialCode like :materialCode ";
            param.put("materialCode", "%%" + sheetJCCKList.getMaterialCode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(sheetJCCKList.getMaterialName())) {
            hql += " and materialName like :materialName ";
            param.put("materialName", "%%" + sheetJCCKList.getMaterialName().trim() + "%%");
        }

        String totalsql = " select count(1) " + hql;

        List<SheetJCCKList> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }
} 