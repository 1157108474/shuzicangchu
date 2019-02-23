package com.zthc.ewms.system.task.controller.guard;

import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.task.entity.guard.InfTask;
import com.zthc.ewms.system.task.entity.guard.InfTaskCondition;
import com.zthc.ewms.system.task.service.InfTaskService;
import drk.system.Log;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class InfTaskControllerGuard {
	
	@Resource(name="dictionaryService")
	public DictionaryService dictionaryService;

	@Resource(name="infTaskService")
	public InfTaskService service;
	
	private final static Log log;
	
	static{
		log = Log.getLog("com.zthc.ewms.system.task.controller.guard.InfTaskControllerGuard");
	}

	/**
	 * 表单提交时，字段所用前缀，只提交一个对象时，可以不写此前缀
	 */
	@InitBinder("task")
    public void initInfTaskBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("task.");
    }
	/**
	 * 管理页面
	 */
//	@RequestMapping(value="/manageInfTask.htm")
	@RequestMapping(method= RequestMethod.GET)
	public String manageInfTask(@ModelAttribute("task") InfTask obj, InfTaskCondition condition,
			HttpServletRequest request, HttpServletResponse response, Model model){

		return "system/task/manageInfTask";
	}
	/**
	*新增
	*/
//	@RequestMapping(value="/addInfTask.htm")
	@RequestMapping("add")
	public String addInfTask(@ModelAttribute("task") InfTask obj, InfTaskCondition condition,
			HttpServletRequest request, HttpServletResponse response, Model model){

		return editInfTask(null,condition,request,response,model);
	}
	
	/**
	 * 编辑页面
	 */
//	@RequestMapping(value="/editInfTask.htm")
	@RequestMapping("{id}")
	public String editInfTask(@PathVariable("id") Integer id, InfTaskCondition condition,
			HttpServletRequest request, HttpServletResponse response, Model model){
		InfTask task = null;
		if(id != null) {
			task = this.service.getInfTaskOne(id);
		}
		List<Dictionary> systems = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.RelateSystem.getCode());
		model.addAttribute("systems",systems);
		model.addAttribute("task", task);
		return "system/task/editInfTask";
	}
	


	
	
	/****************************添加方法****************************************/
	//select条件列表
	
	//update
	//delete
	
	//jump
}

