package com.zthc.ewms.sheet.controller.guard;

import javax.annotation.Resource;

import com.zthc.ewms.sheet.service.SheetRKDETAILService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import drk.system.Log;

public class SheetRKDETAILControllerGuard {

	@Resource(name= "sheetRKDETAILService")
	public SheetRKDETAILService service;

	private final static Log log;

	static{
        log = Log.getLog("com.zthc.ewms.sheet.controller.guard.SheetRKDETAILControllerGuard");
    }

	/**
	 * 表单提交时，字段所用前缀，只提交一个对象时，可以不写此前缀
	 */
	@InitBinder("rkdetail")
	public void initSheet_RKDETAILBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("rkdetail.");
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

