package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.VJcstockEntityDaoGuard;
import com.zthc.ewms.sheet.entity.query.VJcstockEntity;
import com.zthc.ewms.sheet.entity.query.VJcstockEntityCondition;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("vJcstockEntityDao")
public class VJcstockEntityDao extends VJcstockEntityDaoGuard {

    /**
     * 获取寄存材料库存查询列表
     * @param obj
     * @param condition
     * @return
     * @throws ParseException
     */
    public LayuiPage<VJcstockEntity> JCCXList(VJcstockEntity obj, VJcstockEntityCondition condition)throws ParseException {

        LayuiPage<VJcstockEntity> ret = new LayuiPage<>();
        Map<String,Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VJcstockEntity where 1 = 1";
        if(!StringUtils.isEmpty(obj.getStoreid())){
            hql += " and storeid = :storeid ";
            param.put("storeid",obj.getStoreid());
        }
        if(!StringUtils.isEmpty(obj.getMaterialname())){
            hql += " and materialname like :materialname ";
            param.put("materialname","%" + obj.getMaterialname().trim() + "%");
        }
        if(!StringUtils.isEmpty(obj.getMaterialcode())){
            hql += " and materialcode like :materialcode ";
            param.put("materialcode","%" + obj.getMaterialcode().trim() + "%");
        }
        if(!StringUtils.isEmpty(obj.getStorelocationid())){
            hql += " and storelocationid = :storelocationid ";
            param.put("storelocationid",obj.getStorelocationid());
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";

        List<VJcstockEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }
} 