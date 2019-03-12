package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.VCkcxEntityDaoGuard;
import com.zthc.ewms.sheet.entity.query.VCkcxEntity;
import com.zthc.ewms.sheet.entity.query.VCkcxEntityCondition;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.user.service.UserService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("vCkcxEntityDao")
public class VCkcxEntityDao extends VCkcxEntityDaoGuard {

    @Resource(name = "userScopeService")
    public UserScopeService userScopeService;

    @Resource(name = "userService")
    public UserService userService;
    /**
     * 获取物资入库查询列表
     * @param obj
     * @param condition
     * @return
     * @throws ParseException
     */
    public LayuiPage<VCkcxEntity> CKCXList(Integer userId,VCkcxEntity obj, VCkcxEntityCondition condition)throws ParseException {

        LayuiPage<VCkcxEntity> ret = new LayuiPage<>();
        Map<String,Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VCkcxEntity where 1 = 1";
        List<Integer> ztids = new ArrayList<Integer>();

        if(!StringUtils.isEmpty(obj.getZtid())){
            hql += " and ztid = :ztid ";
            param.put("ztid", obj.getZtid());
        }else{
            if(userId!=null){
                List<Depart> list =userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
                if(list.size()!=0){
                    for(int i=0;i<list.size();i++){
                        ztids.add(list.get(i).getId());
                    }
                }else{
                    ztids.add(userService.getUserOne(userId).getZtId());
                }
                if(!StringUtils.isEmpty(ztids)){
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
        if(!StringUtils.isEmpty(obj.getCode())){
            hql += " and code like :code ";
            param.put("code","%" + obj.getCode().trim() + "%");
        }
        if(!StringUtils.isEmpty(obj.getStoreid())){
            hql += " and storeid = :storeid ";
            param.put("storeid",obj.getStoreid());
        }
        if(!StringUtils.isEmpty(obj.getUseddepartid())){
            hql += " and useddepartid = :useddepartid ";
            param.put("useddepartid",obj.getUseddepartid());
        }
        if(!StringUtils.isEmpty(obj.getMaterialname())){
            hql += " and materialname like :materialname ";
            param.put("materialname","%" + obj.getMaterialname().trim() + "%");
        }
        if(!StringUtils.isEmpty(obj.getMaterialcode())){
            hql += " and materialcode like :materialcode ";
            param.put("materialcode","%" + obj.getMaterialcode().trim() + "%");
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";

        List<VCkcxEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    public List<VCkcxEntity> CKExcelList(Integer userId,VCkcxEntity obj, VCkcxEntityCondition condition)throws ParseException {

        Map<String,Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql = " from VCkcxEntity where 1 = 1";
        List<Integer> ztids = new ArrayList<Integer>();

        if(!StringUtils.isEmpty(obj.getZtid())){
            hql += " and ztid = :ztid ";
            param.put("ztid", obj.getZtid());
        }else{
            if(userId!=null){
                List<Depart> list =userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
                if(list.size()!=0){
                    for(int i=0;i<list.size();i++){
                        ztids.add(list.get(i).getId());
                    }
                }else{
                    ztids.add(userService.getUserOne(userId).getParent().getId());
                }
                if(!StringUtils.isEmpty(ztids)){
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
        if(!StringUtils.isEmpty(obj.getCode())){
            hql += " and code = :code ";
            param.put("code",obj.getCode());
        }
        if(!StringUtils.isEmpty(obj.getStoreid())){
            hql += " and storeid like :storeid ";
            param.put("storeid",obj.getStoreid());
        }
        if(!StringUtils.isEmpty(obj.getUseddepartid())){
            hql += " and useddepartid = :useddepartid ";
            param.put("useddepartid",obj.getUseddepartid());
        }
        if(!StringUtils.isEmpty(obj.getMaterialname())){
            hql += " and materialname like :materialname ";
            param.put("materialname","%%" + obj.getMaterialname().trim() + "%%");
        }
        if(!StringUtils.isEmpty(obj.getMaterialcode())){
            hql += " and materialcode like :materialcode ";
            param.put("materialcode","%%" + obj.getMaterialcode().trim() + "%%");
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";

        List<VCkcxEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        return list;
    }
} 