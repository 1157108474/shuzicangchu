package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.VKcsubdetailEntityDaoGuard;
import com.zthc.ewms.sheet.entity.query.VKcsubdetailEntity;
import com.zthc.ewms.sheet.entity.query.VKcsubdetailEntityCondition;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.service.UserService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("vKcsubdetailEntityDao")
public class VKcsubdetailEntityDao extends VKcsubdetailEntityDaoGuard {

    @Resource(name = "userService")
    public UserService userService;

    /**
     * 获取物资入库查询列表
     * @param obj
     * @param condition
     * @return
     * @throws ParseException
     */
    public LayuiPage<VKcsubdetailEntity> KCDetailList(VKcsubdetailEntity obj, VKcsubdetailEntityCondition condition)throws ParseException {

        LayuiPage<VKcsubdetailEntity> ret = new LayuiPage<>();
        Map<String,Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VKcsubdetailEntity where 1 = 1";

        if(!StringUtils.isEmpty(obj.getMaterialcode())){
            hql += " and materialcode like :materialcode ";
            param.put("materialcode","%%" + obj.getMaterialcode().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getStoreid())){
            hql += " and storeid like :storeid ";
            param.put("storeid",obj.getStoreid());
        }
        if(!StringUtils.isEmpty(obj.getStorelocationcode())){
            hql += " and storelocationcode = :storelocationcode ";
            param.put("storelocationcode",obj.getStorelocationcode());
        }
        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";

        List<VKcsubdetailEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    public LayuiPage<VKcsubdetailEntity> KCDetailsList(String userId,String storelocationcode,String materialcode,VKcsubdetailEntityCondition condition)throws ParseException {

        LayuiPage<VKcsubdetailEntity> ret = new LayuiPage<>();
        Map<String,Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VKcsubdetailEntity where 1 = 1";
        if(userId != null){
            User user = userService.getUserOne(Integer.valueOf(userId));
            if(!StringUtils.isEmpty(user.getZtId())){
                hql += " and ztid = :ztid ";
                param.put("ztid",user.getZtId().longValue());
            }
            if(!StringUtils.isEmpty(materialcode)){
                hql += " and materialcode like :materialcode ";
                param.put("materialcode","%%" + materialcode + "%%");
            }
            if(!StringUtils.isEmpty(storelocationcode)){
                hql += " and storelocationcode like :storelocationcode ";
                param.put("storelocationcode",storelocationcode);
            }
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";

        List<VKcsubdetailEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    public String getDetailunitname(VKcsubdetailEntity obj, VKcsubdetailEntityCondition condition)throws ParseException {

        Map<String,Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VKcsubdetailEntity where 1 = 1";
        if(!StringUtils.isEmpty(obj.getMaterialcode())){
            hql += " and materialcode like :materialcode ";
            param.put("materialcode","%%" + obj.getMaterialcode().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getStoreid())){
            hql += " and storeid like :storeid ";
            param.put("storeid",obj.getStoreid());
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";

        List<VKcsubdetailEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        return list.get(0).getDetailunitname();
    }
} 