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
     * �ж��Ƿ��ظ�
     * @param code
     * @param name
     * @param id
     * @return
     */
    public int checkUseDep(String code,String name,Integer id){
        return this.dao.checkUseDep(code,name,id);
    }


    /**
     * ��ȡʹ�õ�λ�б�
     * @param useDep
     * @param condition
     * @return
     */
    public LayuiPage<UseDep> listUseDep(UseDep useDep, UseDepCondition condition){
        return this.dao.listUseDep(useDep,condition);
    }

    /**
     * ��ȡʹ�õ�λͨ�õ����б�
     * @param useDep
     * @param condition
     * @return
     */
    public LayuiPage<UseDep> listGeneral(UseDep useDep,UseDepCondition condition){
        return this.dao.listGeneral(useDep,condition);
    }

    /**
     * ��ȡʹ�õ�λ����ͨ�õ����б�
     *
     * @param useDep
     * @param condition
     * @return
     */
    public LayuiPage<UseDep> listDepartGeneral(UseDep useDep, UseDepCondition condition) {
        return this.dao.listDepartGeneral(useDep, condition);
    }

    /**
     * ��ȡ���뵥λ����ͨ�÷���
     * @param ztId
     * @param ids
     * @return
     */
    public List<UseDep> listDepartDep(Integer ztId,Integer[] ids){
        return this.dao.listDepartDep(ztId,ids);
    }

    /**
     * ��ȡ�Ѱ󶨵����Ϸ���
     * @param useId
     * @return
     */
    public List<OfficesScope> listOfficeScope(Integer useId){
        return this.dao.listOfficeScope(useId);
    }



    /**
     * ����
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
     * �޸�
     * @param useDep
     */
    @Transactional
    public void editUseDep(UseDep useDep){
        Date now = new Date();
        useDep.setUpdateDate(now);
        this.dao.editUseDep(useDep);
    }

    /**
     * ɾ��
     * @param ids
     */
    public void delUseDep(Integer[] ids){
        this.dao.logicDelete(ids);
    }

    /**
     * ʹ�õ�λ������Ϸ�Χ����
     */
    @Transactional
    public void saveMaterielRange(Integer[] arr,UseDepCondition condition,Integer userId){
        // ���Ѵ��ڵ����Ϸ�Χȫ��ɾ��
        this.dao.deleteMaterielRange(condition.getId());
        //��ȡʹ�õ�λ�µ���Ա����ɾ����Ա�µ����Ϸ�Χ
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
            //���������Χ
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

        //ƴװ�û��Ĳ�����Χ
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
     * ʹ�õ�λ�Ƴ����Ϸ�Χ����
     * @param id
     */
    @Transactional
    public void deleteMaterielRange(Integer id){
        this.dao.deleteMaterielRange(id);
    }

    /**
     * ��ȡ��������
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UseDep getOne(Integer id){
        return this.dao.getOne(id);
    }

    /**
     * ��ȡ��������
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
