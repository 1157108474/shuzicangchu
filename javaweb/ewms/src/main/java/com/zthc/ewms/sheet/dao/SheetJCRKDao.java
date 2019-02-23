package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.sheet.dao.guard.SheetJCRKDaoGuard;
import com.zthc.ewms.sheet.entity.guard.*;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SheetJCRKDao extends SheetJCRKDaoGuard {
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
     * 寄存入库单修改
     *
     * @param sheetDetail
     * @return
     */
    public int editSheetDatailed(SheetDetail sheetDetail) {
        String hql = " update SheetDetail set materialId=:materialId,tagCode=:tagCode,materialCode=:materialCode," +
                "materialName=:materialName,materialSpecification=:materialSpecification,materialModel=:materialModel," +
                "detailCount=:detailCount,detailUnitName=:detailUnitName,noTaxPrice=:noTaxPrice,noTaxSum=:noTaxSum," +
                "storeId=:storeId,storeLocationId=:storeLocationId,storeLocationName=:storeLocationName," +
                "storeLocationCode=:storeLocationCode,description=:description,materialBrand=:materialBrand where id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("materialId", sheetDetail.getMaterialId());
        query.setParameter("tagCode", sheetDetail.getTagCode());
        query.setParameter("materialCode", sheetDetail.getMaterialCode());
        query.setParameter("materialName", sheetDetail.getMaterialName());
        query.setParameter("materialSpecification", sheetDetail.getMaterialSpecification());
        query.setParameter("materialModel", sheetDetail.getMaterialModel());
        query.setParameter("detailCount", sheetDetail.getDetailCount());
        query.setParameter("detailUnitName", sheetDetail.getDetailUnitName());
        query.setParameter("noTaxPrice", sheetDetail.getNoTaxPrice());
        query.setParameter("noTaxSum", sheetDetail.getNoTaxSum());
        query.setParameter("storeId", sheetDetail.getStoreId());
        query.setParameter("storeLocationId", sheetDetail.getStoreLocationId());
        query.setParameter("storeLocationName", sheetDetail.getStoreLocationName());
        query.setParameter("storeLocationCode", sheetDetail.getStoreLocationCode());
        query.setParameter("description", sheetDetail.getDescription());
        query.setParameter("materialBrand", sheetDetail.getMaterialBrand());
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
        String hql = " update Sheet set ownerDep=:ownerDep,memo=:memo  where id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("ownerDep", sheet.getOwnerDep());
        query.setParameter("memo", sheet.getMemo());
        query.setParameter("id", sheet.getId());
        return query.executeUpdate();

    }
} 