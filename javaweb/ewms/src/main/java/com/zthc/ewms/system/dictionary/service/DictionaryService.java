package com.zthc.ewms.system.dictionary.service;

import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.guard.DictionaryServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;


@Service
public class DictionaryService extends DictionaryServiceGuard {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Dictionary> getDicTree() {
        List<Dictionary> tree = new ArrayList<Dictionary>();
        //��ȡdic�б�
        List<Dictionary> dics = this.dao.listDics();

//        int fatherId;
        for (Dictionary father : dics) {
            if (father.getParent().getId() == 0) {
                tree.add(father);
//                fatherId = father.getId();
//                for (Dictionary son : dics) {
//                    if (son.getParent().getId() == fatherId) {
//                        father.getChildren().add(son);
//
//                    }
//                }
                findChildren(father,dics);
            }
        }
        return tree;
    }

    private void findChildren( Dictionary parent,List<Dictionary> dics) {
        int parentId = parent.getId();
        for(Dictionary dic : dics){
            if(dic.getParent().getId() == parentId){
                   parent.getChildren().add(dic);
                   findChildren(dic,dics);
            }
        }
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Dictionary> getDicListByParentId(int parentId) {
        return this.dao.listDicsByParentId(parentId);
    }
    public List<Dictionary> getDicListByParentIdCK(int parentId) {
        return this.dao.listDicsByParentId(parentId);
    }
    //�޸�
    @Transactional
    public void updateDic(Dictionary dic) {
        if(this.dao.updateDic(dic) !=1 ){
            throw new RuntimeException("�޸�"+dic.getName()+"�����쳣");
        }

    }

    //��
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Dictionary getDic(Integer id) {
        return this.dao.getDic(id);
    }
    //��ѯ�ֵ�Ĺ��ð�ť����
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Dictionary getDicButton(Integer id) {
        return this.dao.getDicButton(id);
    }

    //αɾ
    @Transactional
    public void deleteDictionary(Integer id,Integer userId){
        if(this.dao.deleteDictionary(id,userId)!=1){
            throw new RuntimeException("ɾ����¼�쳣id="+id);
        }

    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean checkNotExit(Dictionary obj) {
       return this.dao.checkNotExit(obj);
    }


    /**
     * ���ݱ����dic
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Dictionary getDictionaryByType(String type) {
//        return this.dao.getDictionaryByCode(type);
        return this.dao.getDictionaryOne(DictionaryEnums.TypeToUrlId.getIdByType(type));

    }

    /**
     * ���ݱ����dic
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Dictionary getDictionaryByCode(String type) {
        return this.dao.getDictionaryByCode(type);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Dictionary getDictionaryByName(String name) {
        return this.dao.getDictionaryByName(name);

    }
    public Dictionary getDictionaryByTypeCK(String type) {
//        return this.dao.getDictionaryByCode(type);
        return this.dao.getDictionaryOne(DictionaryEnums.TypeToUrlId.getIdByType(type));

    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Dictionary> listDics() {
        return this.dao.listDics();

    }

}
