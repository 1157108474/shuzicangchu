package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.VKcdetailEntityDaoGuard;
import com.zthc.ewms.sheet.entity.query.VKcdetailEntity;
import com.zthc.ewms.sheet.entity.query.VKcdetailEntityCondition;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.user.service.UserService;
import drk.util.TextUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("vKcdetailEntityDao")
public class VKcdetailEntityDao extends VKcdetailEntityDaoGuard {

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
    public LayuiPage<VKcdetailEntity> KCList(Integer userId,VKcdetailEntity obj, VKcdetailEntityCondition condition)throws ParseException {
        LayuiPage<VKcdetailEntity> ret = new LayuiPage<>();
        Map<String,Object> param = new HashMap<>();
        String hql = "from VKcdetailEntity where 1 = 1";
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

        if(!StringUtils.isEmpty(obj.getMaterialcode())){
            hql += " and materialcode like :materialcode ";
            param.put("materialcode", "%"+obj.getMaterialcode().trim()+"%");
        }
        if(!StringUtils.isEmpty(obj.getMaterialname())){
            hql += " and materialname like :materialname ";
            param.put("materialname", "%"+obj.getMaterialname().trim()+"%");
        }
        if(!StringUtils.isEmpty(obj.getFlname())){
            hql += " and flname like :flname ";
            param.put("flname", "%"+obj.getFlname().trim()+"%");
        }
        if(!StringUtils.isEmpty(obj.getProviderdepid())){
            hql += " and providerdepid = :providerdepid ";
            param.put("providerdepid", obj.getProviderdepid());
        }
        if(!StringUtils.isEmpty(obj.getDescription())){
            hql += " and description like :description ";
            param.put("description", "%"+obj.getDescription().trim()+"%");
        }
        if(!StringUtils.isEmpty(obj.getStoreid())){
            hql += " and storeid = :storeid ";
            param.put("storeid", obj.getStoreid());
        }
        if(!StringUtils.isEmpty(obj.getOwnertype())){
            hql += " and ownertype = :ownertype ";
            param.put("ownertype", obj.getOwnertype());
        }
        if(!StringUtils.isEmpty(condition.getStartarea()) && StringUtils.isEmpty(condition.getEndarea())){
            hql += " and storecount >= :startarea ";
            param.put("startarea", condition.getStartarea());
        }else if (!StringUtils.isEmpty(condition.getStartarea()) && !StringUtils.isEmpty(condition.getEndarea())){
            hql += " and storecount BETWEEN :startarea and :endarea ";
            param.put("startarea", condition.getStartarea());
            param.put("endarea", condition.getEndarea());
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id ";
        List<VKcdetailEntity> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }
    /**
     * 获取单条数据
     *
     * @param
     * @return
     */
    public VKcdetailEntity getOne(VKcdetailEntity obj) {
        VKcdetailEntity ret = null;
        if (!TextUtil.isNotNull(obj.getId())) {
            ret = new VKcdetailEntity();
        } else {
            ret = baseDao.getById(VKcdetailEntity.class, obj.getId());
        }
        return ret;
    }

    /**
     * 导出库存Excel
     */
    public List<VKcdetailEntity> KCExcelList(Integer userId,VKcdetailEntity obj, VKcdetailEntityCondition condition){
        List<VKcdetailEntity> list = new ArrayList<VKcdetailEntity>();
        String hql = "from VKcdetailEntity where 1 = 1";
        Map<String,Object> param = new HashMap<>();
        List<Integer> ztids = new ArrayList<Integer>();

        if(!StringUtils.isEmpty(obj.getZtid())){
            hql += " and ztid = :ztid ";
            param.put("ztid", obj.getZtid());
        }else{
            if(userId!=null){
                List<Depart> departList =userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
                if(departList.size()!=0){
                    for(int i=0;i<departList.size();i++){
                        ztids.add(departList.get(i).getId());
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

        if(!StringUtils.isEmpty(obj.getMaterialcode())){
            hql += " and materialcode like :materialcode ";
            param.put("materialcode", "%"+obj.getMaterialcode().trim()+"%");
        }
        if(!StringUtils.isEmpty(obj.getMaterialname())){
            hql += " and materialname like :materialname ";
            param.put("materialname", "%"+obj.getMaterialname().trim()+"%");
        }
        if(!StringUtils.isEmpty(obj.getFlname())){
            hql += " and flname like :flname ";
            param.put("flname", "%"+obj.getFlname().trim()+"%");
        }
        if(!StringUtils.isEmpty(obj.getProviderdepid())){
            hql += " and providerdepid = :providerdepid ";
            param.put("providerdepid", obj.getProviderdepid());
        }
        if(!StringUtils.isEmpty(obj.getDescription())){
            hql += " and description like :description ";
            param.put("description", "%"+obj.getDescription().trim()+"%");
        }
        if(!StringUtils.isEmpty(obj.getStoreid())){
            hql += " and storeid = :storeid ";
            param.put("storeid", obj.getStoreid());
        }
        if(!StringUtils.isEmpty(obj.getOwnertype())){
            hql += " and ownertype = :ownertype ";
            param.put("ownertype", obj.getOwnertype());
        }
        if(!StringUtils.isEmpty(condition.getStartarea()) && StringUtils.isEmpty(condition.getEndarea())){
            hql += " and storecount >= :startarea ";
            param.put("startarea", condition.getStartarea());
        }else if (!StringUtils.isEmpty(condition.getStartarea()) && !StringUtils.isEmpty(condition.getEndarea())){
            hql += " and storecount BETWEEN :startarea and :endarea ";
            param.put("startarea", condition.getStartarea());
            param.put("endarea", condition.getEndarea());
        }

        // 排序
        hql += " order by id ";
        list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        return list;
    }
} 