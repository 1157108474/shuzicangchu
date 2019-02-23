package com.zthc.ewms.system.material.service.guard;

import com.zthc.ewms.system.material.dao.MaterialDao;
import com.zthc.ewms.system.material.entity.guard.Material;
import com.zthc.ewms.system.material.entity.guard.MaterialCondition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class MaterialServiceGuard {
	@Resource(name="materialDao")
	public MaterialDao dao;
	/**************************  ��������   ***************************/
	//����
	@Transactional
	public void saveMaterial(Material obj, MaterialCondition condition) {
		this.dao.saveMaterial(obj, condition);
	}
	//��
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Material getMaterialOne(Integer id) {
		return this.dao.getMaterialOne(id);
	}

	//��
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Material getMaterialById(Integer id) {
		return this.dao.getMaterialById(id);
	}
	//ɾ
	@Transactional
	public void delMaterial(Long id){
		this.dao.delMaterial(id);
	}
	
	
	/**************************  ��������   ***************************/
	//list�����б�
	
	//update
	
	//delete
}