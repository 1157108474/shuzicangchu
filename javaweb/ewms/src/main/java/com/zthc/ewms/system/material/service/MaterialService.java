package com.zthc.ewms.system.material.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.material.entity.guard.Material;
import com.zthc.ewms.system.material.entity.guard.MaterialCondition;
import com.zthc.ewms.system.material.service.guard.MaterialServiceGuard;
import com.zthc.ewms.system.provider.dao.ProviderDao;
import com.zthc.ewms.system.provider.entity.Provider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class MaterialService extends MaterialServiceGuard {
    @Resource(name="providerDao")
    public ProviderDao providerDao;
    /**
     * ��ȡ�б�����
     **/
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public LayuiPage<Material> listMaterial(Material obj, MaterialCondition condition, Integer orgId,String spareCode) throws Exception {
        return this.dao.listMaterial(obj, condition,orgId,spareCode);
    }

    //�޸�
    @Transactional
    public void updateMaterial(Material material) {
        if(this.dao.updateMaterial(material) !=1 ){
            throw new RuntimeException("�޸�"+material.getName()+"�����쳣");
        }

    }

    @Transactional
    public Material getMaterialByCode(String code) {
          return this.dao.getMaterialByCode(code);
    }

    //αɾ
    @Transactional
    public void deleteMaterial(String str,Integer userId){
        Integer[] ids = StringUtils.parseStringToIntegers(str);
        int count = ids.length;
        if(this.dao.deleteMaterial(ids,userId)!= count){
            throw new RuntimeException("ɾ����¼�쳣,��ˢ�º�����");
        }

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean checkNotExit(Material obj) {
        return this.dao.checkNotExit(obj);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Material getMaterial(Integer id) {
       Material material = this.dao.getMaterial(id);
       Integer providerId = material.getProviderId();
       if(providerId!=null){
           Provider provider = providerDao.getOne(providerId);
           if(provider.getId() != null){
              material.setProviderName(provider.getName());
           }
       }
       return material;
    }
}
