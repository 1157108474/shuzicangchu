package com.zthc.ewms.sheet.dao;

import com.hckj.base.database.hibernate.BaseDao;
import com.zthc.ewms.base.hibernate.ComDao;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.entity.query.VCgjhEntity;
import com.zthc.ewms.sheet.entity.query.VCgjhEntityCondition;
import drk.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("vCgjhEntityDao")
public class VCgjhEntityDao {

    @Resource
    public BaseDao baseDao;
    @Autowired
    ComDao comDao;

    public LayuiPage<VCgjhEntity> CGJHList(VCgjhEntity obj, VCgjhEntityCondition condition, Integer userId, String
            startTime1,String startTime2, String endTime1, String endTime2) throws ParseException {

        LayuiPage<VCgjhEntity> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String hql = " from VCgjhEntity where 1 = 1 ";

        if (!StringUtils.isEmpty(obj.getPlanCode())) {
            hql += " and planCode like :planCode ";
            param.put("planCode", "%%" + obj.getPlanCode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getMaterialCode())) {
            hql += " and materialCode like :materialCode ";
            param.put("materialCode", "%%" + obj.getMaterialCode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getMaterIaldes())) {
            hql += " and materIaldes like :materIaldes ";
            param.put("materIaldes", "%%" + obj.getMaterIaldes().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getApplyDepId()) && !StringUtils.isEmpty(obj.getApplyDepName())) {
            hql += " and applyDepId = :applyDepId ";
            param.put("applyDepId", obj.getApplyDepId());
        }
        if (!StringUtils.isEmpty(obj.getUseDepId()) && !StringUtils.isEmpty(obj.getUseDepName())) {
            hql += " and useDepId = :useDepId ";
            param.put("useDepId", obj.getUseDepId());
        }
        if (!StringUtils.isEmpty(obj.getStatus())) {
            hql += " and status = :status";
            param.put("status", obj.getStatus());
        }
        // 需求日期
        if (!StringUtils.isEmpty(startTime1) && StringUtils.isEmpty(endTime1)) {
            Date start = sdf.parse(startTime1 + " 00:00:00");
            hql += " and needDate >= :startTime ";
            param.put("startTime", start);
        } else if (StringUtils.isEmpty(startTime1) && !StringUtils.isEmpty(endTime1)) {
            Date end = sdf.parse(endTime1 + " 23:59:59");
            hql += "and needDate <= :endTime ";
            param.put("endTime", end);
        } else if (!StringUtils.isEmpty(startTime1) && !StringUtils.isEmpty(endTime1)) {
            Date start = sdf.parse(startTime1 + " 00:00:00");
            Date end = sdf.parse(endTime1 + " 23:59:59");
            hql += " and needDate BETWEEN :starttime and :endtime ";
            param.put("starttime", start);
            param.put("endtime", end);
        }
        //制单日期
        if (!StringUtils.isEmpty(startTime2) && StringUtils.isEmpty(endTime2)) {
            Date start = sdf.parse(startTime2 + " 00:00:00");
            hql += " and createDate >= :startTime2 ";
            param.put("startTime2", start);
        } else if (StringUtils.isEmpty(startTime2) && !StringUtils.isEmpty(endTime2)) {
            Date end = sdf.parse(endTime2 + " 23:59:59");
            hql += "and createDate <= :endTime2 ";
            param.put("endTime2", end);
        } else if (!StringUtils.isEmpty(startTime2) && !StringUtils.isEmpty(endTime2)) {
            Date start = sdf.parse(startTime2 + " 00:00:00");
            Date end = sdf.parse(endTime2 + " 23:59:59");
            hql += " and createDate BETWEEN :startTime2 and :endTime2 ";
            param.put("startTime2", start);
            param.put("endTime2", end);
        }

        String totalsql = " select count(1) " + hql;

        List<VCgjhEntity> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    public List<VCgjhEntity> excelCGJHList(VCgjhEntity obj, VCgjhEntityCondition condition,  String
            startTime1,String startTime2, String endTime1, String endTime2) throws ParseException {

        Map<String, Object> param = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String hql = " from VCgjhEntity where 1 = 1 ";

        if (!StringUtils.isEmpty(obj.getPlanCode())) {
            hql += " and planCode like :planCode ";
            param.put("planCode", "%%" + obj.getPlanCode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getMaterialCode())) {
            hql += " and materialCode like :materialCode ";
            param.put("materialCode", "%%" + obj.getMaterialCode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getMaterIaldes())) {
            hql += " and materIaldes like :materIaldes ";
            param.put("materIaldes", "%%" + obj.getMaterIaldes().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getApplyDepId()) && !StringUtils.isEmpty(obj.getApplyDepName())) {
            hql += " and applyDepId = :applyDepId ";
            param.put("applyDepId", obj.getApplyDepId());
        }
        if (!StringUtils.isEmpty(obj.getUseDepId()) && !StringUtils.isEmpty(obj.getUseDepName())) {
            hql += " and useDepId = :useDepId ";
            param.put("useDepId", obj.getUseDepId());
        }
        if (!StringUtils.isEmpty(obj.getStatus())) {
            hql += " and status = :status";
            param.put("status", obj.getStatus());
        }
        // 需求日期
        if (!StringUtils.isEmpty(startTime1) && StringUtils.isEmpty(endTime1)) {
            Date start = sdf.parse(startTime1 + " 00:00:00");
            hql += " and needDate >= :startTime ";
            param.put("startTime", start);
        } else if (StringUtils.isEmpty(startTime1) && !StringUtils.isEmpty(endTime1)) {
            Date end = sdf.parse(endTime1 + " 23:59:59");
            hql += "and needDate <= :endTime ";
            param.put("endTime", end);
        } else if (!StringUtils.isEmpty(startTime1) && !StringUtils.isEmpty(endTime1)) {
            Date start = sdf.parse(startTime1 + " 00:00:00");
            Date end = sdf.parse(endTime1 + " 23:59:59");
            hql += " and needDate BETWEEN :starttime and :endtime ";
            param.put("starttime", start);
            param.put("endtime", end);
        }
        //制单日期
        if (!StringUtils.isEmpty(startTime2) && StringUtils.isEmpty(endTime2)) {
            Date start = sdf.parse(startTime2 + " 00:00:00");
            hql += " and createDate >= :startTime2 ";
            param.put("startTime2", start);
        } else if (StringUtils.isEmpty(startTime2) && !StringUtils.isEmpty(endTime2)) {
            Date end = sdf.parse(endTime2 + " 23:59:59");
            hql += "and createDate <= :endTime2 ";
            param.put("endTime2", end);
        } else if (!StringUtils.isEmpty(startTime2) && !StringUtils.isEmpty(endTime2)) {
            Date start = sdf.parse(startTime2 + " 00:00:00");
            Date end = sdf.parse(endTime2 + " 23:59:59");
            hql += " and createDate BETWEEN :startTime2 and :endTime2 ";
            param.put("startTime2", start);
            param.put("endTime2", end);
        }

        String totalsql = " select count(1) " + hql;

        return comDao.list(hql, param);
    }

    public VCgjhEntity getOne(Integer id) {
        VCgjhEntity ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new VCgjhEntity();
        } else {
            ret = baseDao.getById(VCgjhEntity.class, id);
        }
        return ret;
    }
}
