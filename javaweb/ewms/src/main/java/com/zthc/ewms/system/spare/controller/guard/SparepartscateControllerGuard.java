package com.zthc.ewms.system.spare.controller.guard;

import com.zthc.ewms.system.spare.entity.guard.Sparepartscate;
import com.zthc.ewms.system.spare.entity.guard.SparepartscateCondition;
import com.zthc.ewms.system.spare.service.SparepartscateService;
import drk.system.Log;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SparepartscateControllerGuard {
	
	@Resource(name="sparepartscateService")
	public SparepartscateService service;
	
	private final static Log log;
	
	static{
		log = Log.getLog("com.zthc.system.spare.controller.guard.SparepartscateControllerGuard");
	}

	/**
	 * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
	 */
	@InitBinder("spare")
    public void initSparepartscateBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("spare.");
    }
	/**
	 * ����ҳ��
	 */
//	@RequestMapping(value="/manageSparepartscate.htm")
	@RequestMapping(method= RequestMethod.GET)
	public String manageSparepartscate(@ModelAttribute("spare") Sparepartscate obj, SparepartscateCondition condition,
									   HttpServletRequest request, HttpServletResponse response, Model model){
		return "system/spare/manageSpare";
	}

}

