package com.zthc.ewms.system.spare.service.guard;

import javax.annotation.Resource;

import com.zthc.ewms.system.spare.dao.SparepartscateDao;
import com.zthc.ewms.system.spare.entity.guard.Sparepartscate;
import com.zthc.ewms.system.spare.entity.guard.SparepartscateCondition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SparepartscateServiceGuard {
	@Resource(name="sparepartscateDao")
	public SparepartscateDao dao;
	/**************************  基础方法   ***************************/
	//增改
	@Transactional
	public void saveSparepartscate(Sparepartscate obj, SparepartscateCondition condition) {
		this.dao.saveSparepartscate(obj, condition);
	}
	//查
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Sparepartscate getSparepartscateOne(Integer id) {
		return this.dao.getSparepartscateOne(id);
	}
	//删
	@Transactional
	public void delSparepartscate(Long id){
		this.dao.delSparepartscate(id);
	}
	
	
	/**************************  基础结束   ***************************/
	//list条件列表

	
	//update
	@Transactional
	public int updateSpareOne(Sparepartscate obj, SparepartscateCondition condition){
		return this.dao.updateSpareOne(obj,condition);
	}
	
	//delete
}