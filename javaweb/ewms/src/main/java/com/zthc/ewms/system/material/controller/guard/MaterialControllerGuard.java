package com.zthc.ewms.system.material.controller.guard;

import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.material.entity.guard.Material;
import com.zthc.ewms.system.material.entity.guard.MaterialCondition;
import com.zthc.ewms.system.material.service.MaterialService;
import drk.system.Log;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MaterialControllerGuard {
	
	@Resource(name="materialService")
	public MaterialService service;
	@Resource(name="dictionaryService")
	public DictionaryService dictionaryService;
	private final static Log log;
	
	static{
		log = Log.getLog("com.zthc.ewms.system.material.controller.guard.MaterialControllerGuard");
	}

	/**
	 * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
	 */
    @InitBinder("material")
    public void initMaterialBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("material.");
    }
	/**
	 * ����ҳ��
	 */
//	@RequestMapping(value="/manageMaterial.htm")
	@RequestMapping(method= RequestMethod.GET)
	public String manageMaterial(@ModelAttribute("material") Material obj, MaterialCondition condition,
								HttpServletRequest request, HttpServletResponse response, Model model){

		return "system/material/manageMaterial";
	}
	/**
	 *����
	 */
//	@RequestMapping(value="/addMaterial.htm")
	@RequestMapping("add")
	public String addMaterial(@ModelAttribute("material") Material obj, MaterialCondition condition,
							 HttpServletRequest request, HttpServletResponse response, Model model){

		return editMaterial(null,condition,request,response,model);
	}

	/**
	 * �༭ҳ��
	 */
//	@RequestMapping(value="/editMaterial.htm")
	@RequestMapping("{id}")
	public String editMaterial(@PathVariable("id") Integer id, MaterialCondition condition,
							  HttpServletRequest request, HttpServletResponse response, Model model){
		Material material = null;
		if(id != null) {
			material = this.service.getMaterial(id);
		}else{
			material = new Material();
			material.setSparescateId(Integer.parseInt(request.getParameter("spareId")));
			material.setSpareCode(request.getParameter("spareCode"));
		}
		List<Dictionary> units = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.MaterialUnit.getCode());
		model.addAttribute("units",units);

		model.addAttribute("material", material);
		return "system/material/editMaterial";
	}




	
}

