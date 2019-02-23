package com.zthc.ewms.system.task.service.guard;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zthc.ewms.system.task.dao.InfTaskDao;
import com.zthc.ewms.system.task.entity.guard.InfTask;
import com.zthc.ewms.system.task.entity.guard.InfTaskCondition;
import com.hckj.base.mvc.BaseListPage;

public class InfTaskServiceGuard {
	@Resource(name="infTaskDao")
	public InfTaskDao dao;
	/**************************  基础方法   ***************************/
	//增改
	@Transactional
	public void saveInfTask(InfTask obj, InfTaskCondition condition) {
		this.dao.saveInfTask(obj, condition);
	}
	//查
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public InfTask getInfTaskOne(Integer id) {
		return this.dao.getInfTaskOne(id);
	}
	//删
	@Transactional
	public void delInfTask(Long id){
		this.dao.delInfTask(id);
	}
	
	
	/**************************  基础结束   ***************************/
	//list条件列表
	
	//update
	
	//delete
}