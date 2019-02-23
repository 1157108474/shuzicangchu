package com.zthc.ewms.system.dictionary.service.guard;

import com.zthc.ewms.system.dictionary.dao.DictionaryDao;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryCondition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class DictionaryServiceGuard {
	@Resource(name="dictionaryDao")
	public DictionaryDao dao;
	/**************************  ��������   ***************************/
	//����
	@Transactional
	public void saveDictionary(Dictionary obj, DictionaryCondition condition) {
		this.dao.saveDictionary(obj, condition);
	}
	//��
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Dictionary getDictionaryOne(Integer id) {
		return this.dao.getDictionaryOne(id);
	}
	//ɾ
	@Transactional
	public void delDictionary(Integer id){
		this.dao.delDictionary(id);
	}
	
	
	/**************************  ��������   ***************************/
	//list�����б�
	
	//update
	
	//delete
}