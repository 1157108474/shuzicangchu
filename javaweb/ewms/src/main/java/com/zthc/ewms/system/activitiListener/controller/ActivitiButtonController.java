package com.zthc.ewms.system.activitiListener.controller;


import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.service.FormTemplateService;
import com.zthc.ewms.system.role.entity.guard.Role;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.activiti.bpmn.model.*;
import org.activiti.engine.*;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/system/activitiButton")
public class ActivitiButtonController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private HistoryService historyService;

    @Resource(name = "dictionaryService")
    public DictionaryService dictionaryService;
    @Resource
    public ActivitiService activitiService;

    //查询当前环节按钮权限
    @RequestMapping(value = "/findActPartPermission.htm")
    public void findActPartPermission(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String taskId = request.getParameter("taskId");
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        //2：获取流程定义ID
        String processDefinitionId = task.getProcessDefinitionId();
        //3：查询ProcessDefinitionEntiy对象
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        //使用任务对象Task获取流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .singleResult();
        //获取当前活动的id
        String activityId = pi.getActivityId();
        //4：获取当前的活动
        ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
        TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl.getActivityBehavior()).getTaskDefinition();
        Set<Expression> permissions = taskDefinition.getCandidateGroupIdExpressions();
        List<Dictionary> dictionaries = new ArrayList<>();
        Object[] dicButtons = permissions.toArray();
        Arrays.sort(dicButtons);
        for (int i = 0; i < dicButtons.length; i++) {
            Dictionary dictionary = dictionaryService.getDicButton((int) dicButtons[i]);
            dictionaries.add(dictionary);
        }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"parent"});
        Object obj = JSONArray.fromObject(dictionaries.toArray(), jsonConfig);
        out.println(obj);
        out.flush();
        out.close();
    }

    //判断任务是否已完成
    @ResponseBody
    @RequestMapping("/isCompletedTask.htm")
    public void isCompletedTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        boolean obj = false;
        String taskId = request.getParameter("taskId");
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        if (null != task) {
            obj = false;
        } else {
            obj = true;//已完成
        }
        out.println(obj);
        out.flush();
        out.close();
    }

    //点击任务ID查询每条待办详情
    @RequestMapping(value = "/getOnePro.htm")
    public String getOnePro(HttpServletRequest request, HttpServletResponse response) {
        String taskId = request.getParameter("taskId");
        /*Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        if(null != task) {
            //2：获取流程定义ID
            String processDefinitionId = task.getProcessDefinitionId();
            //3：查询ProcessDefinitionEntiy对象
            ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
            //使用任务对象Task获取流程实例ID
            String processInstanceId = task.getProcessInstanceId();
            //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
            ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                    .processInstanceId(processInstanceId)//使用流程实例ID查询
                    .singleResult();
            //获取当前活动的id
            String activityId = pi.getActivityId();
            //4：获取当前的活动
            ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
            TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl.getActivityBehavior()).getTaskDefinition();
            Set<Expression> permissions = taskDefinition.getCandidateGroupIdExpressions();
            List<Dictionary> dictionaries = new ArrayList<>();
            int[] dicButtons = new int[permissions.size()];
            int i = 0;
            for (Expression per : permissions) {
                dicButtons[i] = Integer.parseInt(per.getExpressionText());
                i++;
            Dictionary dictionary = dictionaryService.getDicButton(Integer.parseInt(per.getExpressionText()));
            dictionaries.add(dictionary);
            }
            //对按钮进行排序
            Arrays.sort(dicButtons);
            for (int j = 0; j < dicButtons.length; j++) {
                Dictionary dictionary = dictionaryService.getDicButton(dicButtons[j]);
                dictionaries.add(dictionary);
            }
            request.setAttribute("taskId", taskId);
            request.setAttribute("premissionButton", dictionaries);
            return "system/activitiListen/processDetail";
        }else{*/
        request.setAttribute("taskId", taskId);
        return "system/activitiListen/showProcessComplete";
        //}
    }

    //退回任务到上环节
    @RequestMapping(value = "/taskRollBack.htm")
    public void taskRollBack(HttpServletRequest request, HttpServletResponse response) {
        String taskId = request.getParameter("taskId");
        try {
            Map<String, Object> variables;
            // 取得当前任务
            HistoricTaskInstance currTask = historyService
                    .createHistoricTaskInstanceQuery().taskId(taskId)
                    .singleResult();
            // 取得流程实例
            ProcessInstance instance = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(currTask.getProcessInstanceId())
                    .singleResult();
            if (instance == null) {

                //流程结束
            }
            variables = instance.getProcessVariables();
            // 取得流程定义
            ProcessDefinitionEntity definition = (ProcessDefinitionEntity) (repositoryService.getProcessDefinition(currTask
                    .getProcessDefinitionId()));

            if (definition == null) {

                //log.error("流程定义未找到");
                return;
            }
            // 取得上一步活动
            ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
                    .findActivity(currTask.getTaskDefinitionKey());
            List<PvmTransition> nextTransitionList = currActivity
                    .getIncomingTransitions();
            // 清除当前活动的出口
            List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
            List<PvmTransition> pvmTransitionList = currActivity//当前活动出口
                    .getOutgoingTransitions();
            for (PvmTransition pvmTransition : pvmTransitionList) {
                oriPvmTransitionList.add(pvmTransition);
            }
            pvmTransitionList.clear();

            // 建立新出口
            List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();
            for (PvmTransition nextTransition : nextTransitionList) {
                PvmActivity nextActivity = nextTransition.getSource();
                ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition)
                        .findActivity(nextActivity.getId());
                TransitionImpl newTransition = currActivity
                        .createOutgoingTransition();
                newTransition.setDestination(nextActivityImpl);
                newTransitions.add(newTransition);
            }
            // 完成任务
            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(instance.getId())
                    .taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
            for (Task task : tasks) {
                taskService.complete(task.getId(), variables);
                historyService.deleteHistoricTaskInstance(task.getId());
                historyService.deleteHistoricProcessInstance(task.getId());
            }
            // 恢复方向
            for (TransitionImpl transitionImpl : newTransitions) {
                currActivity.getOutgoingTransitions().remove(transitionImpl);
            }
            for (PvmTransition pvmTransition : oriPvmTransitionList) {
                pvmTransitionList.add(pvmTransition);
            }
            return;
        } catch (Exception e) {

            return;
        }
    }

    /**
     * 根据选择节点退回
     */
    @RequestMapping(value = "/taskRollBackByHis.json")
    @ResponseBody
    public void taskRollBackByHis(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        //boolean success = false;
        String taskId = request.getParameter("taskId");
        String activitiBackId = request.getParameter("activitiBackId");
        String comment = "";
        //String comment = request.getParameter("comment");
        //String comment = new String(request.getParameter("comment").getBytes("iso8859-1"), "gbk");

       /* try {
            Map<String, Object> variables;
            // 取得当前任务
            HistoricTaskInstance currTask = historyService
                    .createHistoricTaskInstanceQuery().taskId(taskId)
                    .singleResult();
            // 取得流程实例
            ProcessInstance instance = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(currTask.getProcessInstanceId())
                    .singleResult();
            if (instance == null) {

                //流程结束
            }
            variables = instance.getProcessVariables();
            // 取得流程定义
            ProcessDefinitionEntity definition = (ProcessDefinitionEntity) (repositoryService.getProcessDefinition(currTask
                    .getProcessDefinitionId()));

            if (definition == null) {

                //log.error("流程定义未找到");
                return;
            }
            ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
                    .findActivity(currTask.getTaskDefinitionKey());//当前活动
            // 清除当前活动的出口
            List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
            List<PvmTransition> pvmTransitionList = currActivity//当前活动出口
                    .getOutgoingTransitions();
            for (PvmTransition pvmTransition : pvmTransitionList) {
                oriPvmTransitionList.add(pvmTransition);
            }
            pvmTransitionList.clear();
            //新出口
            List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();
            ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition)
                    .findActivity(activitiBackId);
            TransitionImpl newTransition = currActivity
                    .createOutgoingTransition();
            newTransition.setDestination(nextActivityImpl);
            newTransitions.add(newTransition);
            // 完成任务

            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(instance.getId())
                    .taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
            for (Task task : tasks) {
                //设置批注
                if(comment!=null && !("".equals(comment))){
                    taskService.addComment(task.getId(),instance.getId(),comment);
                }
                taskService.complete(task.getId(), variables);
                //historyService.deleteHistoricTaskInstance(task.getId());
                //historyService.deleteHistoricProcessInstance(task.getId());
            }

            // 恢复方向
            for (TransitionImpl transitionImpl : newTransitions) {
                currActivity.getOutgoingTransitions().remove(transitionImpl);
            }
            for (PvmTransition pvmTransition : oriPvmTransitionList) {
                pvmTransitionList.add(pvmTransition);
            }
            success = true;
        }catch (Exception e) {
            return ;
        }*/
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        List<HistoricActivityInstance> list = historyService // 历史相关Service
                .createHistoricActivityInstanceQuery() // 创建历史活动实例查询
                .activityId(activitiBackId)
                .processInstanceId(task.getProcessInstanceId())
                .activityType("userTask")
                .finished()
                .list();
        BpmnModel model = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        if (model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            String startEventID = "";
            boolean isFirst = false;
            for (FlowElement flowElement : flowElements) {
                if (flowElement instanceof StartEvent) {
                    StartEvent startEvent = (StartEvent) flowElement;
                    startEventID = startEvent.getId();
                }
            }
            for (FlowElement flowElement : flowElements) {
                if (flowElement instanceof UserTask) {
                    UserTask userTask = (UserTask) flowElement;
                    List<SequenceFlow> incomingFlows = userTask.getIncomingFlows();
                    for (SequenceFlow sequenceFlow : incomingFlows) {
                        if (sequenceFlow.getSourceRef().equals(startEventID) && userTask.getId().equals(activitiBackId)) {//是否第一环节
                            isFirst = true;
                        }
                    }
                }
            }
            boolean success = activitiService.taskRollBackByHis(taskId, activitiBackId, comment, list.get(0).getAssignee(), isFirst);
            out.println(success);
            out.flush();
            out.close();
        }
    }


    /**
     * 查询当前节点可驳回的任务节点
     */
    @RequestMapping(value = "/historyActInstanceList.htm")
    public String historyActInstanceList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String taskId = request.getParameter("taskId");
        String comment = new String(request.getParameter("comment").getBytes("iso8859-1"), "utf-8");
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        // 取得流程定义
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) (repositoryService.getProcessDefinition(task
                .getProcessDefinitionId()));
        ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
                .findActivity(task.getTaskDefinitionKey());//当前活动

        List<HistoricActivityInstance> list = historyService // 历史相关Service
                .createHistoricActivityInstanceQuery() // 创建历史活动实例查询
                .processInstanceId(task.getProcessInstanceId()) // 执行流程实例id
                .finished()
                .list();
        List<FlowElement> retList = new ArrayList<>();
        BpmnModel model = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        if (model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for (FlowElement flowElement : flowElements) {
                if (flowElement instanceof UserTask && !(flowElement.getId().equals(currActivity.getId()))) {
                    int i = 0;
                    for (HistoricActivityInstance hai : list) {
                        if (hai.getActivityId().equals(flowElement.getId())) {
                            i++;
                        }
                    }
                    if (i > 0) {
                        retList.add(flowElement);
                    }
                }
            }
        }
        request.setAttribute("taskId", taskId);
        request.setAttribute("comment", comment);
        request.setAttribute("list", retList);
        return "system/activitiListen/processHistoryPart";
    }
}
