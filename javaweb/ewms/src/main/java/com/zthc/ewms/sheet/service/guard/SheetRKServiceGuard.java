package com.zthc.ewms.sheet.service.guard;

import com.zthc.ewms.sheet.dao.SheetDao;
import com.zthc.ewms.sheet.dao.SheetRKDETAILDao;
import com.zthc.ewms.sheet.dao.SheetRKDao;
import com.zthc.ewms.system.activitiManage.service.ProcessManageService;

import javax.annotation.Resource;

public class SheetRKServiceGuard {
	@Resource(name= "sheetRKDao")
	public SheetRKDao dao;

    @Resource(name = "sheetDao")
    public SheetDao sheetDao;

    @Resource(name = "processManageService")
    public ProcessManageService processManageService;

    @Resource(name = "sheetRKDETAILDao")
    public SheetRKDETAILDao sheetRKDETAILDao;
    /**************************  ��������   ***************************/
	/**���浥������Ҫ��������**/


	/**���浥������Ҫ��������**/


	/**�޸ġ����·���*/


	/**��ѯ����**/


	/**ɾ������**/

}