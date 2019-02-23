package com.zthc.ewms.sheet.controller.guard;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.service.SheetCGService;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.sheet.service.SheetService;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.log.service.LogService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


import drk.system.Log;

import java.util.List;

public class SheetCGControllerGuard {
	
	@Resource(name= "sheetCGService")
	public SheetCGService service;

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
        log = Log.getLog("com.zthc.ewms.sheet.controller.guard.SheetCKControllerGuard");
    }

    public String getUrl(HttpServletRequest request, String routeId, Model model, String resUrl) {
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
            model.addAttribute("buttons", dictionaries);
        } else if ("show".equals(oper)) {
            //TODO
            taskId = this.activitiService.getTaskByPi(routeId);

        }
        model.addAttribute("taskId", taskId);
        return resUrl;
    }

	
	
}

