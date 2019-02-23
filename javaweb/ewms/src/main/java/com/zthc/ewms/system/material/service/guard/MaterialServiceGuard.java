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
	/**************************  基础方法   ***************************/
	//增改
	@Transactional
	public void saveMaterial(Material obj, MaterialCondition condition) {
		this.dao.saveMaterial(obj, condition);
	}
	//查
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Material getMaterialOne(Integer id) {
		return this.dao.getMaterialOne(id);
	}

	//查
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Material getMaterialById(Integer id) {
		return this.dao.getMaterialById(id);
	}
	//删
	@Transactional
	public void delMaterial(Long id){
		this.dao.delMaterial(id);
	}
	
	
	/**************************  基础结束   ***************************/
	//list条件列表
	
	//update
	
	//delete
}