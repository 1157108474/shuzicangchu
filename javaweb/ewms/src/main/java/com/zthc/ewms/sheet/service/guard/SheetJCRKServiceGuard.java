package com.zthc.ewms.sheet.service.guard;

import com.zthc.ewms.sheet.dao.SheetDao;
import com.zthc.ewms.sheet.dao.SheetJCRKDao;
import com.zthc.ewms.sheet.dao.SheetRKDao;
import com.zthc.ewms.system.activitiManage.service.ProcessManageService;

import javax.annotation.Resource;

public class SheetJCRKServiceGuard {
	@Resource(name= "sheetJCRKDao")
	public SheetJCRKDao dao;

    @Resource(name = "sheetDao")
    public SheetDao sheetDao;

    /**************************  基础方法   ***************************/
	/**保存单条，主要用于新增**/


	/**保存单条，主要用于新增**/


	/**修改、更新方法*/


	/**查询方法**/


	/**删除方法**/

}