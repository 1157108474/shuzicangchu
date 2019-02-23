package com.zthc.ewms.sheet.dao;



import com.hckj.base.database.hibernate.BaseDao;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.entity.mes.MesCk;
import com.zthc.ewms.sheet.entity.mes.MesCkCondition;
import com.zthc.ewms.sheet.entity.mes.MesRk;
import com.zthc.ewms.sheet.entity.mes.MesRkCondition;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class MesDao{

    @Resource(name = "baseDao")
    private BaseDao baseDao;

    public LayuiPage<MesRk> queryMesRkList(MesRk obj, MesRkCondition condition){

        LayuiPage<MesRk> ret = new LayuiPage<>();
        Map<String,Object> param = new HashMap<>();

        String hql = " from MesRk where 1 = 1";

        if(!StringUtils.isEmpty(obj.getLocatorCode())){
            hql += " and locatorCode like :locatorCode ";
            param.put("locatorCode","%%" + obj.getLocatorCode().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getLocatorName())){
            hql += " and locatorName like :locatorName ";
            param.put("locatorName","&&" + obj.getLocatorName().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getLocatorId())){
            hql += " and locatorId = :locatorId ";
            param.put("locatorId",obj.getLocatorId());
        }
        if(!StringUtils.isEmpty(obj.getAssetNumber())){
            hql += " and assetNumber like :assetNumber ";
            param.put("assetNumber", "%%" + obj.getAssetNumber().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getItemNo())){
            hql += " and itemNo like :itemNo ";
            param.put("itemNo","%%" + obj.getItemNo().trim() + "%%");
        }

        String totalsql = " select count(1) " + hql;
        hql += " order by id desc ";

        List<MesRk> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    public LayuiPage<MesCk> queryMesCkList(MesCk obj, MesCkCondition condition){

        LayuiPage<MesCk> ret = new LayuiPage<>();
        Map<String,Object> param = new HashMap<>();

        String hql = " from MesCk where 1 = 1";

        if(!StringUtils.isEmpty(obj.getSubinvCode())){
            hql += " and subinvCode like :subinvCode ";
            param.put("subinvCode","%%" + obj.getSubinvCode().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getLocatorName())){
            hql += " and locatorName like :locatorName ";
            param.put("locatorName","&&" + obj.getLocatorName().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getLocatorId())){
            hql += " and locatorId = :locatorId ";
            param.put("locatorId",obj.getLocatorId());
        }
        if(!StringUtils.isEmpty(obj.getAssetNumber())){
            hql += " and assetNumber like :assetNumber ";
            param.put("assetNumber", "%%" + obj.getAssetNumber().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getItemNo())){
            hql += " and itemNo like :itemNo ";
            param.put("itemNo","%%" + obj.getItemNo().trim() + "%%");
        }


        String totalsql = " select count(1) " + hql;
        hql += " order by id desc ";

        List<MesCk> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        ret.setData(list);
        ret.setCount(total);
        return ret;
    }

}