package com.zthc.ewms.system.activitiListener.controller;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.sheet.service.SheetService;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplateCondition;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.service.UserService;

import drk.system.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/system/activitiListener")
public class ActivitiListenController {

    private final static Log log = Log.getLog("com.zthc.ewms.system.activitiListener.controller");

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
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    ProcessEngineFactoryBean processEngine;
    @Resource(name = "dictionaryService")
    public DictionaryService dictionaryService;
    @Resource(name = "activitiService")
    public ActivitiService activitiService;
    @Resource(name = "userService")
    public UserService userService;
    @Resource
    public SheetService sheetService;
    @Resource(name = "logService")
    public LogService logService;

    @RequestMapping(value = "/processList.htm")
    public String processList(HttpServletRequest request, HttpServletResponse response, Model model) {
        //查询字典，获取单据状态
        List<Dictionary> dictionaries = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.ReceiptStatus.getCode());
        model.addAttribute("dictionaries", dictionaries);
        return "system/activitiListen/activitiListenSystem";
    }


    //根据连线完成个人任务

    @ResponseBody
    @RequestMapping(value = "/completeMyPersonalTask.json")
    public HttpResponse completeMyPersonalTask(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HttpResponse ret;
        String taskId = request.getParameter("taskId");
        String outcome = request.getParameter("outcome");
        String userId = request.getParameter("userId");
        String comment = "";
        try {
//        String comment = request.getParameter("comment");
        	comment = new String(request.getParameter("comment").getBytes("iso8859-1"), "utf-8");
        //comment = new String(request.getParameter("comment").getBytes("iso8859-1"), "utf-8");
        //在这里处理业务，修改单据状态
        //设置批注
        if (comment != null && !("".equals(comment))) {
            activitiService.addComment(taskId, comment);
        }
        //处理完业务，执行下行方法，根据连线和人员，完成任务
        
            activitiService.completeMyPersonalTask(taskId, outcome, userId);
            String userIp = (null == session.getAttribute("userIp") ?
                    null : session.getAttribute("userIp").toString());
            int userIdLog = (null == session.getAttribute("userId") ?
                    0 : Integer.parseInt(session.getAttribute("userId").toString()));
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.ACTIVITI_OPERATION.getCode(), SystemLogEnums.LogAction.APPROVAL_NEXTPART.getCode(),
                    "根据连线完成个人任务:" + taskId, userIp, userIdLog);
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "完成任务！", null);
        } catch (Exception e) {
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "完成任务出错！", null);
            log.error("根据连线完成个人任务出错！");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
        }
        return ret;
    }

    //最后环节使用，完成任务
    @RequestMapping(value = "/completeTask.json")
    @ResponseBody
    public HttpResponse completeTask(HttpServletRequest request, HttpServletResponse response) {
        //request.setCharacterEncoding("utf-8");
        //response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        //PrintWriter out = response.getWriter();
        String taskId = request.getParameter("taskId");
        String outcome = request.getParameter("outcome");
        String comment = "";
        HttpResponse ret;
        int userId = 0;
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        }
        //处理完业务，执行下行方法，完成任务
        try {
        	comment = new String(request.getParameter("comment").getBytes("iso8859-1"), "utf-8");
            //comment = new String(request.getParameter("comment").getBytes("iso8859-1"), "utf-8");
            //在这里处理业务，修改单据状态
            //设置批注
            if (comment != null && !("".equals(comment))) {
                activitiService.addComment(taskId, comment);
            }
        	
            Map map = activitiService.completeTask(taskId, outcome, userId);
            String userIp = (null == session.getAttribute("userIp") ?
                    null : session.getAttribute("userIp").toString());
            if ((boolean) map.get("flag")) {
                this.logService.addSystemLog(1, SystemLogEnums.LogObject.ACTIVITI_OPERATION.getCode(), SystemLogEnums.LogAction.APPROVAL_FINSH.getCode(),
                        "最后环节使用完成任务:" + taskId, userIp, userId);
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "办结完成", null);
            } else {
                this.logService.addSystemLog(1, SystemLogEnums.LogObject.ACTIVITI_OPERATION.getCode(), SystemLogEnums.LogAction.APPROVAL_FINSH.getCode(),
                        "最后环节使用完成任务失败:" + taskId, userIp, userId);
                ret = new HttpResponse(HttpResponse.Status.FAILURE, (String) map.get("message"), null);
            }
        }catch (Exception e) {
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "办结出错:" + e.getMessage(), null);
            e.printStackTrace();
            log.error("最后环节使用完成任务出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
       /* out.println(success);
        out.flush();
        out.close();*/
    }

    //待办
    @RequestMapping(value = "/findProcessing.htm")
    public String findProcessing(HttpServletRequest request, HttpServletResponse response) {
        return "system/activitiListen/showProcessing";
    }

    //待办Table内容查询
    @RequestMapping(value = "/findProcessingByperson.json")
    @ResponseBody
    public LayuiPage<Map<String, Object>> findProcessingByperson(FormTemplateCondition condition, HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入待办Table内容查询findProcessingByperson方法");
        HttpSession session = request.getSession();
        String assignee = null;
        if (session.getAttribute("userCode") != null) {
            assignee = session.getAttribute("userCode").toString();
        }
        String isAdmin = request.getParameter("isAdmin");
        LayuiPage<Map<String, Object>> ret = new LayuiPage<Map<String, Object>>();
        String temCode = request.getParameter("temCode");
        String temName = request.getParameter("temName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String status = request.getParameter("status");
        Map<String, Object> params = new HashMap<>();
        if (temCode != null && !"".equals(temCode)) {
            params.put("temCode", temCode);
        }
        if (temName != null && !"".equals(temName)) {
            params.put("temName", temName);
        }
        if (startTime != null && !"".equals(startTime)) {
            startTime += " 00:00:00";
            params.put("startTime", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            endTime += " 23:59:59";
            params.put("endTime", endTime);
        }
        if ("admin".equals(isAdmin)) {
        } else if ("person".equals(isAdmin)) {
            params.put("assignee", assignee);
        }
        //状态
        if (status != null && !status.equals("0")) {
            params.put("status", status);
        }
        ret = activitiService.queryProcessing(condition, params);
        return ret;
    }

    //已办
    @RequestMapping(value = "/findProcessed.htm")
    public String findProcessed(HttpServletRequest request, HttpServletResponse response) {
        return "system/activitiListen/showProcessed";
    }

    //已办Table内容查询
    @RequestMapping(value = "/findProcessedByperson.json")
    @ResponseBody
    public LayuiPage<Map<String, Object>> findProcessedByPerson(FormTemplateCondition condition, HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入已办Table内容查询findProcessedByperson方法");
        HttpSession session = request.getSession();
        String assignee = null;
        if (session.getAttribute("userCode") != null) {
            assignee = session.getAttribute("userCode").toString();
        }
        LayuiPage<Map<String, Object>> ret = new LayuiPage<Map<String, Object>>();
        String temCode = request.getParameter("temCode");
        String temName = request.getParameter("temName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        Map<String, Object> params = new HashMap<>();
        if (temCode != null && !"".equals(temCode)) {
            params.put("temCode", temCode);
        }
        if (temName != null && !"".equals(temName)) {
            params.put("temName", temName);
        }
        if (startTime != null && !"".equals(startTime)) {
            startTime += " 00:00:00";
            params.put("startTime", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            endTime += " 23:59:59";
            params.put("endTime", endTime);
        }
        if (assignee != null && !"".equals(assignee)) {
            params.put("assignee", assignee);
        }
        ret = activitiService.queryProcessed(condition, params);
        return ret;
       /* HttpSession session = request.getSession();
        String userId = session.getAttribute("userCode").toString();
        int page = condition.getPageNum();
        int size =condition.getPageTotal();
        int startNo = (page- 1) * size;
        LayuiPage<Map<String,Object>> ret = new LayuiPage<Map<String,Object>>();
        List<HistoricTaskInstance> datas = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userId)
                .orderByHistoricTaskInstanceEndTime()
                .desc()
                .finished()
                .listPage(startNo,size);
        long count = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userId)
                .finished()
                .count();
        List<Map<String,Object>> tasks = new ArrayList<Map<String,Object>>();
        for(HistoricTaskInstance his:datas){
            Map<String,Object> resultOne = new HashMap<>();
            resultOne.put("id",his.getId());
            resultOne.put("assignee",his.getAssignee());
            resultOne.put("name",his.getName());
            resultOne.put("processInstanceId",his.getProcessInstanceId());
            resultOne.put("createTime",his.getCreateTime());
            List<Sheet> sheets = sheetService.sheetListByProId(Integer.parseInt(his.getProcessInstanceId()));
            Sheet sheet;
            if(sheets.size()>0){
                sheet=sheets.get(0);
            }else{
                sheet = new Sheet();
            }
            resultOne.put("code",sheet.getCode());
            resultOne.put("sheetName",sheet.getName());
            resultOne.put("submitMan",sheet.getSubmitManId());
            resultOne.put("status",sheet.getStatus());
            resultOne.put("url",sheet.getUrl());//点击单据编号跳转的url
            tasks.add(resultOne);
        }
        ret.setData(tasks);
        ret.setCount(count);
        return ret;*/
    }

    //可以分配个人任务从一个人到另一个人（另派）
    @RequestMapping(value = "/setAssigneeTask.htm")
    public void setAssigneeTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("进入另派setAssigneeTask方法");
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        boolean ret = false;
        try {
            //任务ID
            String taskId = request.getParameter("taskId");
            //指定的办理人
            String userId = request.getParameter("userId");
            taskService.setAssignee(taskId, userId);
            String userIp = (null == session.getAttribute("userIp") ?
                    null : session.getAttribute("userIp").toString());
            int userIdLog = (null == session.getAttribute("userId") ?
                    0 : Integer.parseInt(session.getAttribute("userId").toString()));
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.ACTIVITI_OPERATION.getCode(), SystemLogEnums.LogAction.ANOTHERPIE.getCode(),
                    "最后环节使用完成任务:" + taskId, userIp, userIdLog);
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("任务另派出错！");
            log.errorPrintStacktrace(e);
        }
        out.println(ret);
        out.flush();
        out.close();
    }

    //获取当前节点的连线
    @RequestMapping(value = "/findNextPart.htm")
    public String findNextPart(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        log.debug("进入获取当前节点的连线findNextPart方法");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String taskId = request.getParameter("taskId");
        String isAdmin = request.getParameter("isAdmin");
        String comment = new String(request.getParameter("comment").getBytes("iso8859-1"), "utf-8");
        List<PvmTransition> pvmList;
        String url = "system/activitiListen/processNextPart";
        if ("true".equals(isAdmin)) {
            pvmList = activitiService.getOutcome(taskId, true);
            url = "system/activitiListen/processNextPartSys";
        } else {
            pvmList = activitiService.getOutcome(taskId, false);
        }
        //String outcome = (String)pvmList.get(0).getProperty("name");
        String outcome = pvmList.get(0).getId();
        List<Expression> expressions = null;
        if (outcome != null) {
            expressions = activitiService.getNextAssignee(taskId, outcome);
        }/*else if(outcome == null && ("endEvent").equals(pvmList.get(0).getDestination().getProperty("type"))){
            outcome = "办结";
        }*/
        int count = ((expressions == null) ? 0 : expressions.size() - 1);
        request.setAttribute("count", count);//返回人员总数
        request.setAttribute("taskId", taskId);
        request.setAttribute("comment", comment);
        request.setAttribute("outcome", outcome);
        return url;
    }

    //当前环节人员另派
    @RequestMapping(value = "/thisPartAssent.htm")
    public String thisPartAssent(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        log.debug("进入当前环节人员另派thisPartAssent方法");
        String taskId = request.getParameter("taskId");
        List<Expression> expressions = activitiService.getThisAssigent(taskId);
        request.setAttribute("count", expressions.size() - 1);//返回人员总数
        request.setAttribute("taskId", taskId);
        //request.setAttribute("users",expressions);
        return "system/activitiListen/processThisPart";
    }

    @RequestMapping(value = "/findOutComeListByTaskId.json")
    @ResponseBody
    public LayuiPage<Map<String, Object>> findOutComeListByTaskId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入findOutComeListByTaskId方法");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        LayuiPage<Map<String, Object>> ret = new LayuiPage<Map<String, Object>>();
        String taskId = request.getParameter("taskId");
        String isAdmin = request.getParameter("isAdmin");
        List<PvmTransition> pvmList;
        if ("true".equals(isAdmin)) {
            pvmList = activitiService.getOutcome(taskId, true);
        } else {
            pvmList = activitiService.getOutcome(taskId, false);
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (pvmList != null && pvmList.size() > 0) {
            for (PvmTransition pvm : pvmList) {
                String name = (String) pvm.getProperty("name");
                if (StringUtils.isEmpty(name)) {
                    Map<String, Object> m = new HashMap<String, Object>();
                    m.put("taskId", taskId);
                    m.put("outId", pvm.getId());
                    m.put("outcome", "默认提交");
                    list.add(m);
                } else {
                    Map<String, Object> m = new HashMap<String, Object>();
                    m.put("taskId", taskId);
                    m.put("outId", pvm.getId());
                    m.put("outcome", name);
                    list.add(m);
                }
               /* String name = (String) pvm.getProperty("name");
                String type =  (String)pvm.getDestination().getProperty("type");
                if(StringUtils.isEmpty(name)&&!("endEvent").equals(type)){//(StringUtils.isNotBlank(name))
                    Map<String,Object> m = new HashMap<String,Object>();
                    m.put("taskId",taskId);
                    m.put("outcome","默认提交");
                    list.add(m);
                }else if(("endEvent").equals(type)){
                    Map<String,Object> m = new HashMap<String,Object>();
                    m.put("taskId",taskId);
                    m.put("outcome","办结");
                    list.add(m);
                }else{
                    Map<String,Object> m = new HashMap<String,Object>();
                    m.put("taskId",taskId);
                    m.put("outcome",name);
                    list.add(m);
                }*/
            }
        }
        ret.setData(list);
        return ret;
    }

    //获取当前节点候选人
    @RequestMapping(value = "/findThisAssignee.json")
    @ResponseBody
    public void findThisAssignee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入获取当前节点候选人findThisAssignee方法");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String taskId = request.getParameter("taskId");
        int page = Integer.parseInt(request.getParameter("cur"));
        List<Expression> expressions = activitiService.getThisAssigent(taskId);
        List<User> users = new ArrayList<>();
        for (int i = 1; i < expressions.size(); i++) {
            String code = expressions.get(i).getExpressionText();
            List<User> user = userService.listUserCodesName(code);
            users.add(user.get(0));
        }
        Collections.sort(users);
        int size = 7;
        int startNo = (page - 1) * size;
        List<User> result = null;
        if (startNo + size < users.size()) {
            result = users.subList(startNo, startNo + size);
        } else {
            result = users.subList(startNo, users.size());
        }
        Object obj = JSONArray.fromObject(result.toArray());
        out.println(obj);
        out.flush();
        out.close();
    }

    //获取下节点的候选人
    @RequestMapping(value = "/findNextAssignee.json")
    @ResponseBody
    public void findNextAssignee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入获取下节点的候选人findNextAssignee方法");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String taskId = request.getParameter("taskId");
        String outcome = request.getParameter("outcome");
        int page = Integer.parseInt(request.getParameter("cur"));
        if (outcome == null) {
            List<PvmTransition> pvmList = activitiService.getOutcome(taskId, false);
            //outcome = (String)pvmList.get(0).getProperty("name");
            outcome = pvmList.get(0).getId();
        }
        List<Expression> expressions = activitiService.getNextAssignee(taskId, outcome);
        List<User> users = new ArrayList<>();
        for (int i = 1; i < expressions.size(); i++) {
            String code = expressions.get(i).getExpressionText();
            List<User> user = userService.listUserCodesName(code);
            if (user.size() > 0) {
                users.add(user.get(0));
            }
        }
        Collections.sort(users);
        int size = 7;
        int startNo = (page - 1) * size;
        List<User> result = null;
        if (startNo + size < users.size()) {
            result = users.subList(startNo, startNo + size);
        } else {
            result = users.subList(startNo, users.size());
        }
        Object obj = JSONArray.fromObject(result.toArray());
        out.println(obj);
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/findNextAssigneeCount.json")
    @ResponseBody
    public void findNextAssigneeCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入findNextAssigneeCount方法");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String taskId = request.getParameter("taskId");
        String outcome = request.getParameter("outcome");
        if (outcome == null) {
            List<PvmTransition> pvmList = activitiService.getOutcome(taskId, false);
            //outcome = (String)pvmList.get(0).getProperty("name");
            outcome = pvmList.get(0).getId();
        }
        List<Expression> expressions = activitiService.getNextAssignee(taskId, outcome);
        out.println(expressions.size() - 1);
        out.flush();
        out.close();
    }

    /**
     * 根据连线判断是否为最后环节
     */
    @RequestMapping(value = "/isEndedByOut.json")
    @ResponseBody
    public void isEndedByOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入判断是否为最后环节isEndedByOut方法");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String taskId = request.getParameter("taskId");
        String outcome = request.getParameter("outcome");
        ActivityImpl activity = activitiService.getNextActivityImpl(taskId, outcome);
        int isEnd = 0;
        if (activity == null) {
            isEnd = 1;
        }
        out.println(isEnd);
        out.flush();
        out.close();
    }

    /**
     * 判断是否为最后环节 -  适用于单线流程
     */
    @RequestMapping(value = "/isEnded.json")
    @ResponseBody
    public void isEnded(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入判断是否为最后环节isEnded方法");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String taskId = request.getParameter("taskId");
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        boolean isEnd = false;
        if (processInstance != null) {
            isEnd = processInstance.isEnded();
        }
        out.println(isEnd);
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/isSheetTrue.json")
    @ResponseBody
    public void isSheetTrue(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入判断任务isSheetTrue方法");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String taskId = request.getParameter("taskId");
        JSONObject ret = activitiService.isSheetTrue(taskId);

        out.println(ret);
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/isTaskTrue.json")
    @ResponseBody
    public void isTaskTrue(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入判断任务isTaskTrue方法");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String taskId = request.getParameter("taskId");
        List<PvmTransition> pvmList = activitiService.getOutcome(taskId, false);
        boolean isEnd = true;
        if (pvmList.size() > 0) {
            isEnd = false;
        }
        out.println(isEnd);
        out.flush();
        out.close();
    }

    /*
    * 流转历史图
    * */
    @RequestMapping(value = "/getHistoryImage.htm")
    public void getHistoryImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        //获取历史流程实例
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

        List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        //高亮环节id集合
        List<String> highLightedActivitis = new ArrayList<String>();
        //高亮线路id集合
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity, highLightedActivitList);

        for (HistoricActivityInstance tempActivity : highLightedActivitList) {
            String activityId = tempActivity.getActivityId();
            highLightedActivitis.add(activityId);
        }
        //中文显示的是口口口，设置字体就好了
        InputStream imageStream = diagramGenerator
                .generateDiagram(bpmnModel, "png", activeActivityIds,
                        highLightedFlows, processEngineConfiguration
                                .getActivityFontName(),
                        processEngineConfiguration.getLabelFontName(),
                        "宋体",
                        processEngineConfiguration.getClassLoader(),
                        1.0);
        //.generateDiagram(bpmnModel, "png",activeActivityIds);
        //(bpmnModel, "png", highLightedActivitis,highLightedFlows,"宋体","宋体",null,1.0);
        //单独返回流程图，不高亮显示
//        InputStream imageStream = diagramGenerator.generatePngDiagram(bpmnModel);
        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

    /**
     * 获取需要高亮的线
     *
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }

    @RequestMapping(value = "/historyActInstanceList.json")
    @ResponseBody
    public LayuiPage<Map<String, Object>> historyActInstanceList(FormTemplateCondition condition,Model model, HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入流转历史historyActInstanceList方法");
        String taskId = request.getParameter("taskId");
        int size = condition.getPageTotal();
        int page = condition.getPageNum();
        int startNo = (page - 1) * size;
        LayuiPage<Map<String, Object>> historicActivityInstances = activitiService.historyActInstanceList(taskId, startNo, size);
        List<Map<String,Object>> data = historicActivityInstances.getData();
        for (Map<String, Object> map : data) {
			if(map.get("endTime")==null){	
				model.addAttribute("activityName", map.get("activityName"));
			}
		}
        return historicActivityInstances;
    }

    @RequestMapping(value = "/isProcessComplete.json")
    @ResponseBody
    public void isProcessComplete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("进入isProcessComplete方法");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String taskId = request.getParameter("taskId");
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)
                .singleResult();
        boolean obj = false;
        if (task != null) {
            obj = false;
        } else {
            obj = true;
        }
        out.println(obj);
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/processComplete.json")
    @ResponseBody
    public LayuiPage<HistoricTaskInstance> processComplete(HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入processComplete方法");
        String taskId = request.getParameter("taskId");
        HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery()
                .taskId(taskId)
                .finished()
                .singleResult();
        List<HistoricTaskInstance> historicTaskInstances = new ArrayList<>();
        historicTaskInstances.add(historicTaskInstance);
        LayuiPage<HistoricTaskInstance> ret = new LayuiPage<HistoricTaskInstance>();
        ret.setData(historicTaskInstances);
        return ret;
    }

    @RequestMapping(value = "/processCompleteEdit.json")
    @ResponseBody
    public LayuiPage<HistoricTaskInstance> processCompleteEdit(HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入processComplete方法");
        String taskId = request.getParameter("taskId");
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(taskId)
                .finished()
                .list();
        LayuiPage<HistoricTaskInstance> ret = new LayuiPage<HistoricTaskInstance>();
        ret.setData(historicTaskInstances);
        return ret;
    }

    @RequestMapping(value = "/deletePi.htm")
    public void deletePi(HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入删除流程deletePi方法");
        String piid = request.getParameter("piid");
        try {
            runtimeService.deleteProcessInstance(piid, "删除原因");//删除流程
            System.out.println("删除成功");
        } catch (Exception e) {
            System.out.println("删除失败");
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/expActivitiListen.json")
    public void expActivitiListen(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("进入导出流程监控expActivitiListen方法");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        boolean obj = false;
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("流程监控表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        sheet.setColumnWidth(0, 10000);
        sheet.setColumnWidth(1, 7000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 7000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 4500);
        sheet.setColumnWidth(6, 4500);

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("单据编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("单据名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("申请人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("审核环节");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("处理人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("提交时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("单据状态");
        cell.setCellStyle(style);

        String temCode = request.getParameter("temCode");
        String temName = request.getParameter("temName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        Map<String, Object> params = new HashMap<>();
        if (temCode != null && !"".equals(temCode)) {
            params.put("temCode", temCode);
        }
        if (temName != null && !"".equals(temName)) {
            params.put("temName", temName);
        }
        if (startTime != null && !"".equals(startTime)) {
            startTime += " 00:00:00";
            params.put("startTime", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            endTime += " 23:59:59";
            params.put("endTime", endTime);
        }

        List<Map<String, Object>> list = activitiService.queryProcessing(params);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            Map map = list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue((String) map.get("code"));
            row.createCell((short) 1).setCellValue((String) map.get("sheetName"));
            row.createCell((short) 2).setCellValue((String) map.get("assignee"));
            row.createCell((short) 3).setCellValue((String) map.get("name"));
            row.createCell((short) 4).setCellValue((String) map.get("assignee"));
            //row.createCell((short) 5).setCellValue((String)map.get("createTime"));
            cell = row.createCell((short) 5);
            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(map.get("createTime")));
            row.createCell((short) 6).setCellValue((String) map.get("status"));

        }
        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("D:/流程监控.xls");
            wb.write(fout);
            fout.close();
            obj = true;
            String userIp = (null == session.getAttribute("userIp") ?
                    null : session.getAttribute("userIp").toString());
            int userIdLog = (null == session.getAttribute("userId") ?
                    0 : Integer.parseInt(session.getAttribute("userId").toString()));
            this.logService.addSystemLog(1, SystemLogEnums.LogObject.ACTIVITI_OPERATION.getCode(), SystemLogEnums.LogAction.EXPORT.getCode(),
                    "导出流程监控:", userIp, userIdLog);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("导出流程监控任务出错！");
            log.errorPrintStacktrace(e);
        }
        out.println(obj);
        out.flush();
        out.close();
    }
}
