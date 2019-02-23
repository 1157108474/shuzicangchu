package com.zthc.ewms.system.applyDep.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.applyDep.dao.ApplyDepDao;
import com.zthc.ewms.system.applyDep.entity.ApplyDep;
import com.zthc.ewms.system.applyDep.entity.ApplyDepCondition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ApplyDepService {

    @Resource(name = "applyDepDao")
    public ApplyDepDao dao;

    /**
     * ��ȡ���뵥λ�б�
     * @param applyDep
     * @param condition
     * @return
     */
    public LayuiPage<ApplyDep> listApplyDep(ApplyDep applyDep, ApplyDepCondition condition){
        return this.dao.listApplyDep(applyDep,condition);
    }

    /**
     * ��ȡ���뵥λͨ�õ����б�
     * @param applyDep
     * @param condition
     * @return
     */
    public LayuiPage<ApplyDep> listGeneral(ApplyDep applyDep,ApplyDepCondition condition){
        return this.dao.listGeneral(applyDep,condition);
    }

    /**
     * ͨ��ztid�������뵥λ�б�
     * @param ztId
     * @return
     */
    @Transactional
    public List<ApplyDep> listApplyDepZt(Integer ztId){
        return this.dao.listApplyDepZt(ztId);
    }

    /**
     * ����
     * @param applyDep
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addApplyDep(ApplyDep applyDep){
        Date now = new Date();
        applyDep.setCreateDate(now);
        this.dao.saveApplyDep(applyDep);
    }

    /**
     * �޸�
     * @param applyDep
     */
    @Transactional
    public void editApplyDep(ApplyDep applyDep){
        this.dao.editApplyDep(applyDep);
    }

    /**
     * ɾ��
     * @param ids
     */
    public void delApplyDep(Integer[] ids){
        this.dao.logicDelete(ids);
    }

    /**
     * �ж��Ƿ��ظ�
     * @param code
     * @param name
     * @param id
     * @return
     */
    public int checkApplyDep(String code,String name,Integer id){
        return this.dao.checkApplyDep(code,name,id);
    }

    /**
     * ��ȡ��������
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ApplyDep getOne(Integer id){
        return this.dao.getOne(id);
    }

    /**
     * ��ȡ��������
     * @param code
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ApplyDep getApplyDepCode(String code){
        return this.dao.getApplyDepCode(code);
    }

}
