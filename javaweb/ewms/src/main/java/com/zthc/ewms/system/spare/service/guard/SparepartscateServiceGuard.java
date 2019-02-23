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
	/**************************  ��������   ***************************/
	//����
	@Transactional
	public void saveSparepartscate(Sparepartscate obj, SparepartscateCondition condition) {
		this.dao.saveSparepartscate(obj, condition);
	}
	//��
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Sparepartscate getSparepartscateOne(Integer id) {
		return this.dao.getSparepartscateOne(id);
	}
	//ɾ
	@Transactional
	public void delSparepartscate(Long id){
		this.dao.delSparepartscate(id);
	}
	
	
	/**************************  ��������   ***************************/
	//list�����б�

	
	//update
	@Transactional
	public int updateSpareOne(Sparepartscate obj, SparepartscateCondition condition){
		return this.dao.updateSpareOne(obj,condition);
	}
	
	//delete
}