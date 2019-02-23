package com.zthc.ewms.system.GJ.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.GJ.dao.guard.VBzqEntityDaoGuard;
import com.zthc.ewms.system.GJ.entity.VBzqEntity;
import com.zthc.ewms.system.GJ.entity.VBzqEntityCondition;
import drk.util.TextUtil;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("vBzqEntityDao")
public class VBzqEntityDao extends VBzqEntityDaoGuard{
    /**
     * 获取保质期告警查询列表
     * @param obj
     * @param condition
     * @return
     * @throws
     */
    public LayuiPage<VBzqEntity> BzqList(VBzqEntity obj, VBzqEntityCondition condition,String maturity)throws ParseException {

        LayuiPage<VBzqEntity> ret = new LayuiPage<>();
        Map<String,Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VBzqEntity where 1 = 1";
        if(!StringUtils.isEmpty(obj.getMaterialcode())){
            hql += " and materialcode like :materialcode ";
            param.put("materialcode","%%" + obj.getMaterialcode().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getDescription())){
            hql += " and description like :description ";
            param.put("description","%%" + obj.getDescription().trim() + "%%");
        }

        if(!StringUtils.isEmpty(obj.getSyday())){
            hql += " and syday >= 0  and  syday <= :syday ";
            param.put("syday",obj.getSyday());
        }
        if(maturity == "1"){
            hql += " and syday <= 0 ";
        }else if (maturity == "0"){
            hql += " and syday > 0 ";
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";

        List<VBzqEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    public List<VBzqEntity> BzqExcelList(VBzqEntity obj, VBzqEntityCondition condition,String maturity)throws ParseException {

        Map<String,Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VBzqEntity where 1 = 1";
        if(!StringUtils.isEmpty(obj.getMaterialcode())){
            hql += " and materialcode like :materialcode ";
            param.put("materialcode","%%" + obj.getMaterialcode().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getDescription())){
            hql += " and description like :description ";
            param.put("description","%%" + obj.getDescription().trim() + "%%");
        }

        if(!StringUtils.isEmpty(obj.getSyday())){
            hql += " and syday >= 0  and  syday <= :syday ";
            param.put("syday",obj.getSyday());
        }
        if(maturity == "1"){
            hql += " and syday <= 0 ";
        }else if (maturity == "0"){
            hql += " and syday > 0 ";
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";

        List<VBzqEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);
        return list;
    }
    /**
     * 获取单条数据
     *
     * @param
     * @return
     */
    public VBzqEntity getOne(VBzqEntity obj) {
        VBzqEntity ret = null;
        if (!TextUtil.isNotNull(obj.getId())) {
            ret = new VBzqEntity();
        } else {
            ret = baseDao.getById(VBzqEntity.class, obj.getId());
        }
        return ret;
    }
}
