package com.zthc.ewms.sheet.service.guard;

import com.zthc.ewms.sheet.dao.SheetCGDao;
import com.zthc.ewms.sheet.entity.guard.ManageOrder;
import com.zthc.ewms.sheet.entity.order.Order;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class SheetCGServiceGuard {
	@Resource(name= "sheetCGDao")
	public SheetCGDao dao;
	/**************************  基础方法   ***************************/
	/**保存单条，主要用于新增**/


	/**保存单条，主要用于新增**/


	/**修改、更新方法*/


	/**查询方法**/

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ManageOrder getOrderOne(Integer sheetId){
		return this.dao.getOrderOne(sheetId);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Order getOrderOneById(Integer sheetId){
		return this.dao.getOrderOneById(sheetId);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getSheetCGNameOne(Integer id){
		return this.dao.getSheetCGNameOne(id);
	}

	/**删除方法**/

}