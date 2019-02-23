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
	/**************************  ��������   ***************************/
	//����
	@Transactional
	public void saveInfTask(InfTask obj, InfTaskCondition condition) {
		this.dao.saveInfTask(obj, condition);
	}
	//��
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public InfTask getInfTaskOne(Integer id) {
		return this.dao.getInfTaskOne(id);
	}
	//ɾ
	@Transactional
	public void delInfTask(Long id){
		this.dao.delInfTask(id);
	}
	
	
	/**************************  ��������   ***************************/
	//list�����б�
	
	//update
	
	//delete
}