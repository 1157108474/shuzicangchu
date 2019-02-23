package com.zthc.ewms.system.warehouse.service.guard;

import com.zthc.ewms.system.warehouse.dao.WareHouseDao;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouseCondition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class WareHouseServiceGuard {
	@Resource(name="wareHouseDao")
	public WareHouseDao dao;
	/**************************  ��������   ***************************/
	//����
	@Transactional
	public void saveWareHouse(WareHouse obj, WareHouseCondition condition) {
		this.dao.saveWareHouse(obj, condition);
	}
	//��
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public WareHouse getWareHouseOne(Integer id) {
		return this.dao.getWareHouseOne(id);
	}

	//��
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public WareHouse getWareHouseById(Integer id) {
		return this.dao.getWareHouseById(id);
	}

	//ɾ
	@Transactional
	public void delWareHouse(Long id){
		this.dao.delWareHouse(id);
	}


	/**************************  ��������   ***************************/
	//list�����б�
	
	//update
	
	//delete
}