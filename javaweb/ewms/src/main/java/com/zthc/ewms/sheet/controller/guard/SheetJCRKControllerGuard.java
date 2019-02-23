package com.zthc.ewms.sheet.controller.guard;

import com.zthc.ewms.sheet.service.SheetRKService;
import drk.system.Log;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;

public class SheetJCRKControllerGuard {

	@Resource(name= "sheetRKService")
	public SheetRKService service;

	private final static Log log;

	static{
        log = Log.getLog("com.zthc.ewms.sheet.controller.guard.SheetRKControllerGuard");
    }

	/**
	 * 表单提交时，字段所用前缀，只提交一个对象时，可以不写此前缀
	 */
	@InitBinder("rkd")
	public void initSheet_RKBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("rkd.");
	}

	/**
	 *管理页面
	 */

	/**
	 *跳转页面方法
	 */

	/**
	 *新增
	 */


	/**
	 * 编辑页面
	 */


	/**
	 * 保存记录
	 */

	/**
	 * 修改方法
	 */

	/**
	 * 查询方法
	 */

	/**
	 * 删除方法
	 */



}

