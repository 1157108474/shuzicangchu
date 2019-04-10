package com.zthc.ewms.system.useDep.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.useDep.dao.UseDepDao;
import com.zthc.ewms.system.useDep.entity.OfficesScope;
import com.zthc.ewms.system.useDep.entity.UseDep;
import com.zthc.ewms.system.useDep.entity.UseDepCondition;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserEnums.ScopeTypeEnum;
import com.zthc.ewms.system.user.entity.guard.UserScope;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UseDepService {

    @Resource(name = "useDepDao")
    public UseDepDao dao;

    @Autowired
    private UserService userService;
    @Autowired
    private UserScopeService userScopeService;
    /**
     * 判断是否重复
     * @param code
     * @param name
     * @param id
     * @return
     */
    public int checkUseDep(String code,String name,Integer id){
        return this.dao.checkUseDep(code,name,id);
    }


    /**
     * 获取使用单位列表
     * @param useDep
     * @param condition
     * @return
     */
    public LayuiPage<UseDep> listUseDep(UseDep useDep, UseDepCondition condition){
        return this.dao.listUseDep(useDep,condition);
    }

    /**
     * 获取使用单位通用调用列表
     * @param useDep
     * @param condition
     * @return
     */
    public LayuiPage<UseDep> listGeneral(UseDep useDep,UseDepCondition condition){
        return this.dao.listGeneral(useDep,condition);
    }

    /**
     * 获取使用单位科室通用调用列表
     *
     * @param useDep
     * @param condition
     * @return
     */
    public LayuiPage<UseDep> listDepartGeneral(UseDep useDep, UseDepCondition condition) {
        return this.dao.listDepartGeneral(useDep, condition);
    }

    /**
     * 获取申请单位科室通用方法
     * @param ztId
     * @param ids
     * @return
     */
    public List<UseDep> listDepartDep(Integer ztId,Integer[] ids){
        return this.dao.listDepartDep(ztId,ids);
    }

    /**
     * 获取已绑定的物料分类
     * @param useId
     * @return
     */
    public List<OfficesScope> listOfficeScope(Integer useId){
        return this.dao.listOfficeScope(useId);
    }



    /**
     * 新增
     * @param useDep
     */
    @Transactional
    public void addUseDep(UseDep useDep){
        Date now = new Date();
        useDep.setUpdateDate(now);
        useDep.setCreateDate(now);
        this.dao.saveUseDep(useDep);
    }

    /**
     * 修改
     * @param useDep
     */
    @Transactional
    public void editUseDep(UseDep useDep){
        Date now = new Date();
        useDep.setUpdateDate(now);
        this.dao.editUseDep(useDep);
    }

    /**
     * 删除
     * @param ids
     */
    public void delUseDep(Integer[] ids){
        this.dao.logicDelete(ids);
    }

    /**
     * 使用单位添加物料范围方法
     */
    @Transactional
    public void saveMaterielRange(Integer[] arr,UseDepCondition condition,Integer userId){
        // 将已存在的物料范围全部删除
        this.dao.deleteMaterielRange(condition.getId());
        //获取使用单位下的人员，并删除人员下的物料范围
        List<User> users =  userService.listUserIdByOfficesId(condition.getId());
        List<Integer> userIds = new ArrayList<>();
        for (User user : users) {
            userIds.add(user.getId());
        }
        Integer scopeType = ScopeTypeEnum.SPAREPARTSCATE.getType();
        if(userIds.size()>0){
        	this.userScopeService.delUserScopesByIdsAndType(userIds,scopeType);
        }
        Date now = new Date();
        OfficesScope officesScope;
        UserScope userScope ;
        for (Integer integer : arr) {
            officesScope = new OfficesScope();
            officesScope.setCreateDate(now);
            officesScope.setOfficesId(condition.getId());
            officesScope.setScopeType(3);
            officesScope.setScopeId(integer);
            officesScope.setZtId(condition.getZtId());
            officesScope.setCreator(userId);
            officesScope.setAddType(1);
            this.dao.saveMaterielRange(officesScope);
            //保存操作范围
            for (User user : users) {
                    userScope = new UserScope();
                    userScope.setGuId(user.getGuId());
                    userScope.setZtId(user.getZtId());
                    userScope.setPersonId(user.getId());
                    userScope.setScopeId(integer);
                    userScope.setScopeType(scopeType);
                    this.userScopeService.saveUserScope(userScope);
            }
        }

        //拼装用户的操作范围
       /* StringBuffer sql = new StringBuffer();
        String nowStr = DateUtils.parseDateToStr(DateUtils.DD_MM_YYYY_HH_MM_SS,now);
        StringBuffer sqlLeft = new StringBuffer("Insert into base_person_scope(ID,PERSONID,SCOPETYPE,SCOPEID,CREATOR,CREATEDATE,ZTID,GUID) select ");
        for (User user : users) {
            for (Integer integer : arr) {
                sql.append(sqlLeft);
                sql.append(" BASE_PERSONSCOPE_SEQUENCE.nextval").append(",")
                        .append(user.getId()).append(",")
                        .append(scopeType).append(",")
                        .append(integer).append(",")
                        .append(userId).append(",")
                        .append("'").append(nowStr).append("',")
                        .append(user.getZtId()).append(",'")
                        .append(user.getGuId()).append("' from dual;");
            }
        }
        this.userScopeService.saveAllUserScope(sql.toString());*/
    }

    /**
     * 使用单位移除物料范围方法
     * @param id
     */
    @Transactional
    public void deleteMaterielRange(Integer id){
        this.dao.deleteMaterielRange(id);
    }

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UseDep getOne(Integer id){
        return this.dao.getOne(id);
    }

    /**
     * 获取单条数据
     * @param code
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UseDep getUseDepCode(String code){
        return this.dao.getUseDepCode(code);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<OfficesScope> listOfficesScopes (Integer scopeId,Integer ztId) {
        return this.dao.listOfficesScopes(scopeId,ztId);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Integer> listOfficeScopeByOfficesId (Integer officesId) {
        return this.dao.listOfficeScopeByOfficesId(officesId);
    }


}
