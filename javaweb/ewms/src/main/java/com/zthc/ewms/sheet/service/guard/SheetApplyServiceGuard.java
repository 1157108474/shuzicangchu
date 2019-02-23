package com.zthc.ewms.sheet.service.guard;

import com.zthc.ewms.sheet.dao.SheetApplyDao;
import com.zthc.ewms.sheet.entity.apply.ManageApply;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class SheetApplyServiceGuard {
	@Resource(name="sheetApplyDao")
	public SheetApplyDao dao;
	/**************************  ��������   ***************************/
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ManageApply getApplyOne(Integer sheetId){
		return this.dao.getApplyOne(sheetId);
	}
	
	/**************************  ��������   ***************************/
	//list�����б�
	
	//update
	
	//delete
}