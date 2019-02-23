package com.zthc.ewms.system.warehouse.controller.guard;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.hckj.base.database.jdbc.tools.PageHelper;
import com.zthc.ewms.system.warehouse.dao.guard.WareHouseDaoGuard;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouseCondition;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import com.hckj.base.mvc.BaseController;
import com.hckj.base.mvc.BaseLocal;

import drk.system.Log;

import java.util.List;

public class WareHouseControllerGuard {
	
	@Resource(name="wareHouseService")
	public WareHouseService service;
	
	private final static Log log;
	
	static{
		log = Log.getLog("com.zthc.ewms.system.warehouse.controller.guard.WareHouseControllerGuard");
	}

	/**
	 * 表单提交时，字段所用前缀，只提交一个对象时，可以不写此前缀
	 */
	@InitBinder("ware")
    public void initWareHouseBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("house.");
    }
	/**
	 * 管理页面
	 */
//	@RequestMapping(value="/manageWareHouse.htm")
	@RequestMapping(method= RequestMethod.GET)
	public String manageWareHouse(@ModelAttribute("ware") WareHouse obj, WareHouseCondition condition,
								 HttpServletRequest request, HttpServletResponse response, Model model){

		return "system/ware/manageWare";
	}
	/**
	 *新增
	 */
//	@RequestMapping(value="/addWareHouse.htm")
	@RequestMapping("add")
	public String addWareHouse(@ModelAttribute("ware") WareHouse obj, WareHouseCondition condition,
							  HttpServletRequest request, HttpServletResponse response, Model model){

		return editWareHouse(null,condition,request,response,model);
	}

	/**
	 * 编辑页面
	 */
//	@RequestMapping(value="/editWareHouse.htm")
	@RequestMapping("{id}")
	public String editWareHouse(@PathVariable("id") Integer id, WareHouseCondition condition,
							   HttpServletRequest request, HttpServletResponse response, Model model){
		WareHouse WareHouse = null;
		if(id != null) {
			WareHouse = this.service.getWareHouse(id);
		}
//		List<Dictionary> units = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.WareHouseUnit.getCode());
//		model.addAttribute("units",units);

		model.addAttribute("ware", WareHouse);
		return "system/ware/editWare";
	}

}

