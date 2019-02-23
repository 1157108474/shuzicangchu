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
     * 获取单条数据
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Provider getOne(Integer id){
        return this.dao.getOne(id);
    }

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Provider getProvider(Integer id){
        return this.dao.getProvider(id);
    }


    /**
     * 判断是否重复
     * @param code
     * @param name
     * @param id
     * @return
     */
    public int checkProvider(String code,String name,Integer id){
        return this.dao.checkProvider(code,name,id);
    }

    /**
     * 获取供应商单条数据
     * @param name
     * @return
     */
    public Provider getProviderOne(String name){
        return this.dao.getProviderOneById(name);
    }

    /**
     * 获取供应商列表
     * @param obj
     * @param condition
     * @return
     * @throws Exception
     */
    public LayuiPage<Provider> listProviders(Provider obj, ProviderCondition condition)throws Exception{
        return this.dao.listProviders(obj,condition);
    }

    /**
     * 获取通用供应商列表
     * @param provider
     * @param condition
     * @return
     */
    public LayuiPage<Provider> listGeneral(Provider provider,ProviderCondition condition){
        return this.dao.listGeneral(provider,condition);
    }

    /**
     * 新增
     * @param provider
     */
    @Transactional
    public void addProvider(Provider provider){
        Date now = new Date();
        provider.setCreateDate(now);
        this.dao.saveProvider(provider);
    }

    /**
     * 修改
     * @param provider
     */
    @Transactional
    public void editProvider(Provider provider){
        Date now = new Date();
        provider.setUpdateDate(now);

        this.dao.editProvider(provider);
    }

    /**
     * 删除
     * @param ids
     */
    @Transactional
    public void delProvider(Integer[] ids){
        this.dao.logicDelete(ids);
        //TODO：需要跟库房对接，当禁用供应商时，自动断货
    }










}
