package com.zthc.ewms.system.activitiManage.controller;

import com.zthc.ewms.system.activitiManage.controller.guard.ProcessManageControllerGuard;
import com.zthc.ewms.system.activitiManage.service.ProcessManageService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.service.FormTemplateService;
import drk.system.Log;
import net.sf.json.JSONArray;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/processManage")
public class ProcessManageController{

	@Resource(name="repositoryService")
	public RepositoryService repositoryService;

	@Resource(name="historyService")
	public HistoryService historyService;

	@Resource(name="taskService")
	public TaskService taskService;

	@Resource(name="runtimeService")
	public RuntimeService runtimeService;

	@Resource(name="processManageService")
	public ProcessManageService processManageService;

	@Resource(name="formTemplateService")
	public FormTemplateService formTemplateService;

	private final static Log log;
	
	static{
		log = Log.getLog("com.zthc.ewms.system.dictionary.controller.ProcessManageController");
	}
	@RequestMapping(value = "/processManage.htm")
	public String activitiManage(HttpServletRequest request, HttpServletResponse response) {

		List<Deployment> deployments = new ArrayList<Deployment>();
		List<ProcessDefinition> list = repositoryService
				.createProcessDefinitionQuery()
				.orderByProcessDefinitionVersion().desc()
				.list();

		for (ProcessDefinition processDefinition : list) {
			String deploymentId = processDefinition.getDeploymentId();
			Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
			deployments.add( deployment);
		}
		request.setAttribute("processDefinitions", list);
			request.setAttribute("deployments", deployments);
		return "system/activitiManage/showProcess";
	}


	@RequestMapping(value = "/processDelete.htm")
	public String delete(@RequestParam("deploymentId") String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
		return "redirect:/system/processManage/processManage.htm";
	}
	@RequestMapping(value = "/processDeleteProcessInstance.htm")
	public String processDeleteProcessInstance(@RequestParam("deploymentId") String deploymentId) {
		/*ProcessInstance processInstances = runtimeService.createProcessInstanceQuery()
				.deploymentId(deploymentId)
				.singleResult();
		if(pi==null){
			//该流程实例已经完成了
			historyService.deleteHistoricProcessInstance(processInstanceId);
		}else{
			//该流程实例未结束的
			runtimeService.deleteProcessInstance(processInstanceId,"");
			historyService.deleteHistoricProcessInstance(processInstanceId);//(顺序不能换)
		}
		repositoryService.deleteDeployment(deploymentId, true);*/
		return "redirect:/system/processManage/processManage.htm";
	}

	//启动流程实例  index.jsp页面的测试
	@RequestMapping(value = "/startProIns.htm")
	@ResponseBody
	public void startProIns(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String menuCode = request.getParameter("menuCode");
		String userCode = "80000179";//request.getParameter("userCode");
		Map<String,Object> map = processManageService.startProcessInstance(menuCode,userCode);//返回流程实例ID,当前任务ID
		//在这里处理业务，讲流程实例ID放到单据中，改变单据状态
		out.println(map.get("taskId"));
		out.flush();
		out.close();
	}
}	
	
	
