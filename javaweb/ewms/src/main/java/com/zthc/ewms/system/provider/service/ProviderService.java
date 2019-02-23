package com.zthc.ewms.system.provider.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.provider.dao.ProviderDao;
import com.zthc.ewms.system.provider.entity.Provider;
import com.zthc.ewms.system.provider.entity.ProviderCondition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ProviderService {
    @Resource(name = "providerDao")
    public ProviderDao dao;

    /**
     * ��ȡ��������
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Provider getOne(Integer id){
        return this.dao.getOne(id);
    }

    /**
     * ��ȡ��������
     *
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Provider getProvider(Integer id){
        return this.dao.getProvider(id);
    }


    /**
     * �ж��Ƿ��ظ�
     * @param code
     * @param name
     * @param id
     * @return
     */
    public int checkProvider(String code,String name,Integer id){
        return this.dao.checkProvider(code,name,id);
    }

    /**
     * ��ȡ��Ӧ�̵�������
     * @param name
     * @return
     */
    public Provider getProviderOne(String name){
        return this.dao.getProviderOneById(name);
    }

    /**
     * ��ȡ��Ӧ���б�
     * @param obj
     * @param condition
     * @return
     * @throws Exception
     */
    public LayuiPage<Provider> listProviders(Provider obj, ProviderCondition condition)throws Exception{
        return this.dao.listProviders(obj,condition);
    }

    /**
     * ��ȡͨ�ù�Ӧ���б�
     * @param provider
     * @param condition
     * @return
     */
    public LayuiPage<Provider> listGeneral(Provider provider,ProviderCondition condition){
        return this.dao.listGeneral(provider,condition);
    }

    /**
     * ����
     * @param provider
     */
    @Transactional
    public void addProvider(Provider provider){
        Date now = new Date();
        provider.setCreateDate(now);
        this.dao.saveProvider(provider);
    }

    /**
     * �޸�
     * @param provider
     */
    @Transactional
    public void editProvider(Provider provider){
        Date now = new Date();
        provider.setUpdateDate(now);

        this.dao.editProvider(provider);
    }

    /**
     * ɾ��
     * @param ids
     */
    @Transactional
    public void delProvider(Integer[] ids){
        this.dao.logicDelete(ids);
        //TODO����Ҫ���ⷿ�Խӣ������ù�Ӧ��ʱ���Զ��ϻ�
    }










}
