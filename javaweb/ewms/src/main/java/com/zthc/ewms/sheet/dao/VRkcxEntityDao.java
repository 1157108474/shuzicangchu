package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.VRkcxEntityDaoGuard;
import com.zthc.ewms.sheet.entity.query.VRkcxEntity;
import com.zthc.ewms.sheet.entity.query.VRkcxEntityCondition;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.user.service.UserService;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("vRkcxEntityDao")
public class VRkcxEntityDao extends VRkcxEntityDaoGuard {

    @Resource(name = "userScopeService")
    public UserScopeService userScopeService;

    @Resource(name = "userService")
    public UserService userService;

    /**
     * ��ȡ��������ѯ�б�
     *
     * @param obj
     * @param condition
     * @return
     * @throws ParseException
     */
    public LayuiPage<VRkcxEntity> RKCXList(Integer userId, VRkcxEntity obj, VRkcxEntityCondition condition) throws ParseException {

        LayuiPage<VRkcxEntity> ret = new LayuiPage<>();
        String hql = " from VRkcxEntity where 1 = 1";
        Map<String, Object> param = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Integer> ztids = new ArrayList<Integer>();
        if (!StringUtils.isEmpty(obj.getZtid())) {
            hql += " and ztid = :ztid ";
            param.put("ztid", obj.getZtid());
        } else {
            if (userId != null) {
                List<Depart> list = userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
                if (list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        ztids.add(list.get(i).getId());
                    }
                } else {
                	User userOne = userService.getUserOne(userId);
                    ztids.add(userService.getUserOne(userId).getZtId());
                }
                if (!StringUtils.isEmpty(ztids)) {
                    int i = 0;
                    for (Integer ztid : ztids) {
                        if (i == 0) {
                            hql += " and  ( ztId =  :ztid" + i;
                        } else {
                            hql += " or ztId =  :ztid" + i;
                        }
                        param.put("ztid" + i++, ztid);
                    }
                    hql += " ) ";
                }
            }
        }
        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and code = :code ";
            param.put("code", obj.getCode());
        }
        if (!StringUtils.isEmpty(obj.getMaterialcode())) {
            hql += " and materialcode like :materialcode ";
            param.put("materialcode", "%%" + obj.getMaterialcode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getMaterialname())) {
            hql += " and materialname like :materialname ";
            param.put("materialname", obj.getMaterialname());
        }
        if (!StringUtils.isEmpty(obj.getStoreid())) {
            hql += " and storeid like :storeid ";
            param.put("storeid", obj.getStoreid());
        }
        if (!StringUtils.isEmpty(condition.getStartTime()) && StringUtils.isEmpty(condition.getEndTime())) {
            Date start = sdf.parse(condition.getStartTime() + " 00:00:00");
            hql += " and submittime >= :starttime ";
            param.put("starttime", start);
        } else if (!StringUtils.isEmpty(condition.getStartTime()) && !StringUtils.isEmpty(condition.getEndTime())) {
            Date start = sdf.parse(condition.getStartTime() + " 00:00:00");
            Date end = sdf.parse(condition.getEndTime() + " 23:59:59");
            hql += " and submittime BETWEEN :starttime and :endtime ";
            param.put("starttime", start);
            param.put("endtime", end);
        }

        String totalsql = " select count(*) " + hql;
        // ����
        hql += " order by id ";

        List<VRkcxEntity> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    public List<VRkcxEntity> RKExcelList(Integer userId, VRkcxEntity obj, VRkcxEntityCondition condition) throws ParseException {

        Map<String, Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VRkcxEntity where 1 = 1";
        List<Integer> ztids = new ArrayList<Integer>();

        if (!StringUtils.isEmpty(obj.getZtid())) {
            hql += " and ztid = :ztid ";
            param.put("ztid", obj.getZtid());
        } else {
            if (userId != null) {
                List<Depart> list = userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
                if (list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        ztids.add(list.get(i).getId());
                    }
                } else {
                    ztids.add(userService.getUserOne(userId).getParent().getId());
                }
                if (!StringUtils.isEmpty(ztids)) {
                    int i = 0;
                    for (Integer ztid : ztids) {
                        if (i == 0) {
                            hql += " and  ( ztId =  :ztid" + i;

                        } else {
                            hql += " or ztId =  :ztid" + i;
                        }
                        param.put("ztid" + i++, ztid);
                    }
                    hql += " ) ";
                }
            }
        }
        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and code = :code ";
            param.put("code", obj.getCode());
        }
        if (!StringUtils.isEmpty(obj.getMaterialcode())) {
            hql += " and materialcode like :materialcode ";
            param.put("materialcode", "%%" + obj.getMaterialcode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getMaterialname())) {
            hql += " and materialname like :materialname ";
            param.put("materialname", "%%" + obj.getMaterialname().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getStoreid())) {
            hql += " and storeid like :storeid ";
            param.put("storeid", obj.getStoreid());
        }
        if (!StringUtils.isEmpty(condition.getStartTime()) && StringUtils.isEmpty(condition.getEndTime())) {
            Date start = sdf.parse(condition.getStartTime());
            hql += " and submittime >= :starttime ";
            param.put("starttime", start);
        } else if (!StringUtils.isEmpty(condition.getStartTime()) && !StringUtils.isEmpty(condition.getEndTime())) {
            Date start = sdf.parse(condition.getStartTime());
            Date end = sdf.parse(condition.getEndTime());
            hql += " and submittime BETWEEN :starttime and :endtime ";
            param.put("starttime", start);
            param.put("endtime", end);
        }

        String totalsql = " select count(*) " + hql;
        // ����
        hql += " order by id ";

        List<VRkcxEntity> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        return list;
    }
} 