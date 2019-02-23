package com.zthc.ewms.sheet.controller.guard;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hckj.base.mvc.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.hckj.base.database.jdbc.tools.PageHelper;
import com.zthc.ewms.sheet.dao.guard.SheetDaoGuard;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;
import com.zthc.ewms.sheet.service.SheetService;
import com.hckj.base.mvc.BaseController;
import com.hckj.base.mvc.BaseLocal;

import drk.system.Log;

import java.util.List;

public class SheetControllerGuard {
	
	@Resource(name="sheetService")
	public SheetService service;


	@Resource(name = "sheetDetailService")
	public SheetDetailService detailService;

	@Resource(name = "activitiService")
	public ActivitiService activitiService;


	@Resource(name = "taskService")
	public TaskService taskService;

	@Resource(name = "dictionaryService")
	public DictionaryService dictionaryService;


	@Resource(name = "logService")
	public LogService logService;


	private final static Log log;
	
	static{
		log = Log.getLog("com.zthc.ewms.sheet.controller.guard.SheetControllerGuard");
	}

	/**
	 * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
	 */
	@InitBinder("sheet")
    public void initSheetBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("sheet.");
    }
	/**
	 * ����ҳ��
	 */
	@RequestMapping(value="/manageSheet.htm")
	public String manageSheet(@ModelAttribute("sheet") Sheet obj, SheetCondition condition,
			HttpServletRequest request, HttpServletResponse response, Model model){
		return "ewms/sheet/view/manageSheet";
	}
	/**
	*����
	*/
	@RequestMapping(value="/addSheet.htm")
	public String addSheet(@ModelAttribute("sheet") Sheet obj, SheetCondition condition,
			HttpServletRequest request, HttpServletResponse response, Model model){
		return editSheet(obj,condition,request,response,model);
	}
	
	/**
	 * �༭ҳ��
	 */
	@RequestMapping(value="/editSheet.htm")
	public String editSheet(@ModelAttribute("sheet") Sheet obj, SheetCondition condition,
			HttpServletRequest request, HttpServletResponse response, Model model){
		Sheet sheet = null ;//this.service.getSheetOne(obj.getId());
		model.addAttribute("sheet", sheet);
		return "ewms/sheet/view/editSheet";
	}
	/**
	 * ɾ����ϸ
	 */
	@RequestMapping(value = "/delSheet.json")
	@ResponseBody
	public HttpResponse<Sheet> delSheet(Sheet obj, HttpServletRequest request) {
		HttpResponse<Sheet> ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ������ʧ�ܣ�", obj);
		try {
			this.service.delSheet(obj.getId());
			//���÷��ز���
			ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ�����ݳɹ���", obj);
		} catch (Exception e) {
			log.error("ɾ������ʧ�ܣ�");
			log.errorPrintStacktrace(e);
		}
		return ret;
	}

    public String getUrl(HttpServletRequest request, String routeId, Model model, String resUrl, int status) {
        Task task = null;
        String taskId = null;
		String oper = request.getParameter("oper");
		List<Dictionary> dictionaries = null;
		if (StringUtils.isEmpty(oper)) {
			taskId = request.getParameter("taskId");
			task = taskService.createTaskQuery().taskId(taskId).singleResult();
			if (task == null) {
				request.setAttribute("taskId", taskId);
				return "/system/activitiListen/showProcessComplete";
			}
			dictionaries = activitiService.getPartButtonOnStar(taskId);
			model.addAttribute("buttons", dictionaries);
		} else if ("edit".equals(oper)) {
            if (status != 39) {
                request.setAttribute("taskId", routeId);
                return "/system/activitiListen/showProcessCompleteEdit";
            } else {
                taskId = this.activitiService.getTaskByPi(routeId);
                dictionaries = activitiService.getPartButtonOnStar(taskId);
                model.addAttribute("buttons", dictionaries);
            }
        } else if ("show".equals(oper)) {
            //TODO
			taskId =  this.activitiService.getTaskByPi(routeId);

		}
		model.addAttribute("taskId", taskId);
		return resUrl;
	}

}

