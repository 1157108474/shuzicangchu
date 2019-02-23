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
	/**************************  基础方法   ***************************/
	//增改
	@Transactional
	public void saveDictionary(Dictionary obj, DictionaryCondition condition) {
		this.dao.saveDictionary(obj, condition);
	}
	//查
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Dictionary getDictionaryOne(Integer id) {
		return this.dao.getDictionaryOne(id);
	}
	//删
	@Transactional
	public void delDictionary(Integer id){
		this.dao.delDictionary(id);
	}
	
	
	/**************************  基础结束   ***************************/
	//list条件列表
	
	//update
	
	//delete
}