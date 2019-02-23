package com.zthc.ewms.sheet.controller.guard;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.entity.guard.SheetRKD;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.sheet.service.SheetRKService;
import com.zthc.ewms.sheet.service.SheetService;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import drk.system.Log;

import java.util.List;

public class SheetRKControllerGuard {

	@Resource(name= "sheetRKService")
	public SheetRKService service;

    @Resource(name = "sheetService")
    public SheetService sheetService;


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

    public String getUrl(HttpServletRequest request, String routeId, Model model, SheetRKD obj, String resUrl) {
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
            taskId = this.activitiService.getTaskByPi(routeId);
            dictionaries = activitiService.getPartButtonOnStar(taskId);

            if (obj.getStatus() != 39) {
                request.setAttribute("taskId", obj.getRouteid());
                return "/system/activitiListen/showProcessCompleteEdit";
            }
            //获取审核人信息
            task = taskService.createTaskQuery().taskId(taskId).singleResult();
            String assignee = task.getAssignee();

            model.addAttribute("taskUserId", assignee);
            model.addAttribute("buttons", dictionaries);
        } else if ("show".equals(oper)) {
            taskId = this.activitiService.getTaskByPi(routeId);
            request.setAttribute("taskId", obj.getRouteid());
        }
        model.addAttribute("taskId", taskId);
        return resUrl;
    }


}

