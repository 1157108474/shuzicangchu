package com.zthc.ewms.system.GJ.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.GJ.dao.guard.VSxxEntityDaoGuard;
import com.zthc.ewms.system.GJ.entity.VSxxEntity;
import com.zthc.ewms.system.GJ.entity.VSxxEntityCondition;
import drk.util.TextUtil;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("vSxxEntityDao")
public class VSxxEntityDao extends VSxxEntityDaoGuard {
    /**
     * 获取保质期告警查询列表
     * @param obj
     * @param condition
     * @return
     * @throws
     */
    public LayuiPage<VSxxEntity> SxxList(VSxxEntity obj, VSxxEntityCondition condition, String gjtype)throws ParseException {

        LayuiPage<VSxxEntity> ret = new LayuiPage<>();
        Map<String,Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VSxxEntity where 1 = 1";
        if(!StringUtils.isEmpty(obj.getCode())){
            hql += " and code like :code ";
            param.put("code","%%" + obj.getCode().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getDescription())){
            hql += " and description like :description ";
            param.put("description","%%" + obj.getDescription().trim() + "%%");
        }

        if(!StringUtils.isEmpty(obj.getModels())){
            hql += " and models like :models ";
            param.put("models","%%" + obj.getModels().trim() + "%%");
        }
        if(!StringUtils.isEmpty(gjtype)){
            if(gjtype.equals("1")){
                hql += " and con  >= stockup ";
            }else if (gjtype.equals("0")){
                hql += " and con  <=  stockdown ";
            }
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";

        List<VSxxEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    public List<VSxxEntity> SxxExcelList(VSxxEntity obj, VSxxEntityCondition condition,String gjtype)throws ParseException {

        Map<String,Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VSxxEntity where 1 = 1";
        if(!StringUtils.isEmpty(obj.getCode())){
            hql += " and code like :code ";
            param.put("code","%%" + obj.getCode().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getDescription())){
            hql += " and description like :description ";
            param.put("description","%%" + obj.getDescription().trim() + "%%");
        }

        if(!StringUtils.isEmpty(obj.getModels())){
            hql += " and models like :models ";
            param.put("models","%%" + obj.getModels().trim() + "%%");
        }
        if(!StringUtils.isEmpty(gjtype)){
            if(gjtype.equals("1")){
                hql += " and con  >= stockup ";
            }else if (gjtype.equals("0")){
                hql += " and con  <=  stockdown ";
            }
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";

        List<VSxxEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);
        return list;
    }
    /**
     * 获取单条数据
     *
     * @param
     * @return
     */
    public VSxxEntity getOne(VSxxEntity obj) {
        VSxxEntity ret = null;
        if (!TextUtil.isNotNull(obj.getId())) {
            ret = new VSxxEntity();
        } else {
            ret = baseDao.getById(VSxxEntity.class, obj.getId());
        }
        return ret;
    }
}
