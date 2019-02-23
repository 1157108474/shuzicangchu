package com.zthc.ewms.sheet.service.guard;

import javax.annotation.Resource;

import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.sheet.entity.guard.SheetStock;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.activitiManage.service.ProcessManageService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zthc.ewms.sheet.dao.SheetDao;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;

import java.util.Map;

public class SheetServiceGuard {
	@Resource(name="sheetDao")
	public SheetDao dao;
	@Resource(name="sheetDetailService")
	public SheetDetailService sheetDetailService;
	@Resource(name="processManageService")
	public ProcessManageService processManageService;
	/**************************  ��������   ***************************/

	//��
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Sheet getSheetOne(Integer id) {
		return this.dao.getSheetOne(id);
	}
	//��
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public SheetStock getSheetStockOne(Integer id) {
		return this.dao.getSheetStockOne(id);
	}
	//ɾ
	@Transactional
	public void delSheet(Integer id){
		this.dao.delSheet(id);
		this.sheetDetailService.delSheetDetail(id);
	}
	
	
	/**************************  ��������   ***************************/
	//list�����б�
	
	//update
	
	//delete
}