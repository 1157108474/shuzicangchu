package com.zthc.ewms.system.activitiListener.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.XmlUtil;
import com.zthc.ewms.sheet.dao.SheetCKDao;
import com.zthc.ewms.sheet.dao.SheetDao;
import com.zthc.ewms.sheet.dao.SheetRKDao;
import com.zthc.ewms.sheet.entity.ck.dto.RenewalCostDTO;
import com.zthc.ewms.sheet.entity.guard.SheetCK;
import com.zthc.ewms.sheet.entity.tk.TK;
import com.zthc.ewms.sheet.entity.ykyw.Ykyw;
import com.zthc.ewms.sheet.service.*;
import com.zthc.ewms.system.activitiListener.dao.ActivitiDao;
import com.zthc.ewms.system.activitiManage.entity.ActDic;
import com.zthc.ewms.system.activitiManage.service.ActdicService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplateCondition;
import com.zthc.ewms.system.formTemplateManage.service.FormTemplateService;
import com.zthc.ewms.system.taskLog.service.TaskLogService;
import com.zthc.ewms.system.user.service.UserService;
import com.zthc.ewms.webservice.axis2.CuxwmsStub;
import com.zthc.ewms.webservice.axis2.ESBPortProxy;
import drk.system.AppConfig;
import drk.system.Log;
import net.sf.json.JSONObject;
import org.activiti.bpmn.model.*;
import org.activiti.engine.*;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.el.FixedValue;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.beans.Transient;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;


@Service
public class ActivitiService {

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

    @Resource(name = "formTemplateService")
    public FormTemplateService formTemplateService;
    @Resource
    public ActdicService actdicService;
    @Resource
    public UserService userService;
    @Resource
    public ActivitiDao activitiDao;
    @Resource
    public SheetDao sheetDao;

    @Resource
    public SheetRKDao rkDao;
    @Resource
    public SheetCKDao ckDao;
    @Resource
    public SheetCKService sheetCKService;
    @Resource
    public SheetService sheetService;
    @Resource
    public TaskLogService taskLogService;
    @Resource
    public SheetPDService sheetPDService;
    @Resource
    public SheetCGService sheetCGService;
    @Resource
    public SheetRKService sheetRKService;

    private final static Log log = Log.getLog("com.zthc.ewms.system.activitiListener.service");
    private final static Log xmllog = Log.getLog(" com.zthc.ewms.system.activitiListener.service.xmllog");

    /**
     * 获取当前环节的候选人，用以另派
     */
    public List<Expression> getThisAssigent(String taskId) {
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)
                .singleResult();
        String processDefinitionId = task.getProcessDefinitionId();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .singleResult();
        String activityId = pi.getActivityId();
        ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
        List<Expression> expressions = new ArrayList<>();
        TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl.getActivityBehavior()).getTaskDefinition();
        Expression assignee = taskDefinition.getAssigneeExpression();
        Set<Expression> candidateUser = taskDefinition.getCandidateUserIdExpressions();
        expressions.add(assignee);
        expressions.addAll(candidateUser);
        return expressions;
    }

    /**
     * 获取连线对象
     *
     * @param taskId  任务ID
     * @param isAdmin 是否展示办结按钮
     */
    public List<PvmTransition> getOutcome(String taskId, Boolean isAdmin) {
        //1:使用任务ID，查询任务对象
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
        //5：获取当前活动完成之后连线的名称
        List<PvmTransition> pvmList = activityImpl.getOutgoingTransitions();
        return pvmList;
       /* List<PvmTransition> pvmListRet = new ArrayList<>();
        for(PvmTransition pvmTransition : pvmList){
                if(!("endEvent").equals(pvmTransition.getDestination().getProperty("type"))){
                    pvmListRet.add(pvmTransition);
                }
        }
        if(isAdmin){
            return pvmList;
        }else{
            return pvmListRet;
        }*/
    }

    /**
     * @param taskId  任务ID
     * @param outcome 连线name
     */
    public ActivityImpl getNextActivityImpl(String taskId, String outcome) {
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        //2：获取流程定义ID
        String processDefinitionId = task.getProcessDefinitionId();
        //3：查询ProcessDefinitionEntiy对象
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        //获取当前活动完成之后连线的名称
        List<PvmTransition> pvmList = getOutcome(taskId, false);
        ActivityImpl activityImpl = null;
        for (PvmTransition pvm : pvmList) {
            //String name = (String) pvm.getProperty("name");
            String name = pvm.getId();
            if (outcome.equals(name)) {
                PvmActivity ac = pvm.getDestination(); //获取线路的终点节点
                if (!"endEvent".equals((String) ac.getProperty("type"))) {
                    activityImpl = processDefinitionEntity.findActivity(ac.getId());
                }
            }
        }
        return activityImpl;
    }

    /**
     * @param taskId  任务ID
     * @param outcome 连线name
     */
    public List<Expression> getNextAssignee(String taskId, String outcome) {
        ActivityImpl activityImpl = getNextActivityImpl(taskId, outcome);
        List<Expression> expressions = new ArrayList<>();
        if (activityImpl != null) {
            TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl.getActivityBehavior()).getTaskDefinition();
            Expression assignee = taskDefinition.getAssigneeExpression();
            Set<Expression> candidateUser = taskDefinition.getCandidateUserIdExpressions();
            expressions.add(assignee);
            expressions.addAll(candidateUser);
        }
        return expressions;
    }

    public void setAssignee(ActivityImpl prototypeActivity, String assignee) {
        TaskDefinition taskDefinition = ((UserTaskActivityBehavior) prototypeActivity.getActivityBehavior()).getTaskDefinition();
        if (assignee != null) {
            taskDefinition.setAssigneeExpression(new FixedValue(assignee));
        }
    }

    /**
     * 获取第一环节按钮
     *
     * @param menuCode 菜单code
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Dictionary> getPartButton(String menuCode, String userCode) {
        List<Dictionary> dictionaries = new ArrayList<>();
        List<FormTemplate> formTemplates = formTemplateService.getFromTemByMenuStatus(menuCode, 2);
        if (formTemplates.size() > 0) {
            //List<ActDic> actDics = actdicService.getActDicByModelID(formTemplates.get(0).getProcessDefinitionKey());
            ActDic actDics = actdicService.getActDicBydicID(formTemplates.get(0).getProcessDefinitionKey());
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                    .deploymentId(actDics.getDepId())
                    .orderByProcessDefinitionVersion().desc()
                    .list();
            String processDefinitionKey = list.get(0).getId();
            BpmnModel model = repositoryService.getBpmnModel(processDefinitionKey);
            if (model != null) {
                Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
                String startEventID = "";
                UserTask userTaskNeed = null;
                for (FlowElement flowElement : flowElements) {
                    //System.out.println("flowelement id:" + e.getId() + "  name:" + e.getName() + "   class:" + e.getClass().toString());
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
                            if (sequenceFlow.getSourceRef().equals(startEventID)) {
                                userTaskNeed = userTask;
                            }
                        }
                    }
                }
                if (userTaskNeed != null) {
                    for (String candidateUsers : userTaskNeed.getCandidateUsers()) {
                        if (candidateUsers.equals(userCode)) {
                            //TODO 开始有多个环节只取最后一个
                            List<String> buttonID = userTaskNeed.getCandidateGroups();
                            for (String s : buttonID) {
                                Dictionary dictionary = dictionaryService.getDic(Integer.parseInt(s));
                                dictionaries.add(dictionary);
                            }
                        }
                    }
                }

            }
        }
        return dictionaries;
    }

    /**
     * 启动流程之后获取环节按钮
     *
     * @param taskId 任务id
     */
    public List<Dictionary> getPartButtonOnStar(String taskId) {
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        List<Dictionary> dictionaries = new ArrayList<>();
        if (null != task) {
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
            int[] dicButtons = new int[permissions.size()];
            int i = 0;
            for (Expression per : permissions) {
                dicButtons[i] = Integer.parseInt(per.getExpressionText());
                i++;
           /* Dictionary dictionary = dictionaryService.getDicButton(Integer.parseInt(per.getExpressionText()));
            dictionaries.add(dictionary);*/
            }
            //对按钮进行排序
            Arrays.sort(dicButtons);
            for (int j = 0; j < dicButtons.length; j++) {
                Dictionary dictionary = dictionaryService.getDicButton(dicButtons[j]);
                dictionaries.add(dictionary);
            }
        } else {

        }
        return dictionaries;
    }

    /**
     * 根据连线完成个人任务
     *
     * @param taskId  任务id
     * @param outcome 连线name
     * @param userId  下环节人员code
     */
    @Transactional
    public void completeMyPersonalTask(String taskId, String outcome, String userId) {
        //设置下环节人员
        ActivityImpl activityImpl = this.getNextActivityImpl(taskId, outcome);
        this.setAssignee(activityImpl, userId);
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("message", outcome);
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        int i = 0;
        if (task != null) {
            Map<String, Object> sheet = sheetDao.getSheetOneByPi(task.getProcessInstanceId());
            if (sheet != null) {
                //流程id
                Integer routeId = Integer.parseInt(sheet.get("routeId").toString());
                switch (Integer.parseInt(sheet.get("kindId").toString())) {
                    case 35://入库
                        i = sheetDao.updateSheetRKStauts(routeId, 40);
                        break;
                    case 36://出库
                        i = sheetDao.updateSheetCKStauts(routeId, 40);
                        break;
                    default://其他
                        i = sheetDao.updateSheetStauts(routeId, 40);
                }
            }
        }
        if (i > 0) {
            taskService.complete(taskId, variables);
        }

        //System.out.println("完成任务");
    }

    /**
     * 最后环节使用，完成任务
     *
     * @param taskId 任务ID
     */
    @Transactional
    public Map completeTask(String taskId, String outcome, int userId) throws Exception {
        log.error("进入最后环节，参数taskId：" + taskId + ",outcome:" + outcome + ",userId:" + userId);
        //dictionaryService.getDicFoemTem();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("message", outcome);
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        boolean flag = true;
        String message = "";
        if (task != null) {
            log.error("参数task.getProcessInstanceId()：" + task.getProcessInstanceId());
            Map<String, Object> sheet = sheetDao.getSheetOneByPi(task.getProcessInstanceId());
            log.error("进入单据判断");
            if (sheet != null) {
                Integer sheetId = Integer.parseInt(sheet.get("id").toString());
                //流程id
                Integer routeId = Integer.parseInt(sheet.get("routeId").toString());
                try {
                    switch (Integer.parseInt(sheet.get("kindId").toString())) {
                        case 246://库存盘点单
                            sheetDao.updateSheetStauts(routeId, 41);//根据流程ID修改单据状态
                            break;
                        case 485://物资退货单
                            String extendString7 = "";
                            if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postTHXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXPORET", userId, 142, "同步退货出库口,接口名称:invokews");
                                if ("1".equals(result[0])) {
                                    String xml = new String(new BASE64Decoder().decodeBuffer(result[1]));
                                    Document document = DocumentHelper.parseText(xml);
                                    Element root = document.getRootElement();
                                    //List<Element> nodes = root.elements("WSINTERFACE");
                                    //extendString7 = nodes.get(0).element("RECEIPT_NUM").getText();
                                    Element info = root.element("RESULT_INFO").element("RECEIPT_NUM");
                                    extendString7 = info.getText();
                                } else {
                                    throw new Exception("同步ERP失败," + result[1]);
                                }
                            }
                            sheetDao.updateTHSheetStauts(sheetId, 41, extendString7);//根据流程ID修改单据状态
                            sheetDao.auditSheet(sheetId, "SHEETTH_COMMIT", userId);
                            break;
                        case 161://移库移位单
                            Ykyw ykyw = sheetService.getYkywSheetOne(sheetId);
                            if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postYWXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXINVTRX", userId, 145, "同步调拨接口,接口名称:invokews");
                                if ("1".equals(result[0])) {
                                    AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
                                    executor.execute(new OutThreadYKYW(ykyw, userId), new Date().getTime());
                                } else {
                                    throw new Exception("同步ERP失败," + result[1]);
                                }

                            }
                            sheetDao.updateSheetStauts(routeId, 41);//根据流程ID修改单据状态
                            sheetDao.auditSheet(sheetId, "SHEETYW_COMMIT", userId);
                            break;
                        case 445://物资申请单
                            break;
                        case 588://物资接收单
                            sheetDao.updateSheetStauts(routeId, 41);
                            sheetDao.auditSheet(sheetId, "SHEETJS_COMMIT", userId);
                            break;
                        case 568://期初建账
                            break;
                        case 465://物资寄存入库单
                            sheetDao.updateSheetStauts(routeId, 41);//根据流程ID修改单据状态
                            sheetDao.auditSheet(sheetId, "SHEETJCRK_COMMIT", userId);
                            break;
                        case 466://物资寄存出库单
                            sheetDao.updateSheetStauts(routeId, 41);//根据流程ID修改单据状态
                            sheetDao.auditSheet(sheetId, "SHEETJCCK_COMMIT", userId);
                            break;
                        case 106://产品变更单
                            break;
                        case 650://物资领料单
                            /*if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postLLOccupyCountXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXINVRES", userId, 182, "同步领料更新占用数量接口,接口名称:invokews");
                                if (!"1".equals(result[0])) {
                                    throw new Exception("同步ERP失败,"+result[1]);
                                }
                            }*/
                            sheetDao.updateSheetStauts(routeId, 41);//根据流程ID修改单据状态
                            break;
                        case 35://物资入库单
                            String extendString6 = "";
                            if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postRKXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXPOREC", userId, 141, "同步入库接口,接口名称:invokews");
                                log.error("接口返回参数result=" + result[0] + "," + result[1]);
                                if ("1".equals(result[0])) {
                                    String xml = new String(new BASE64Decoder().decodeBuffer(result[1]), "utf-8");
                                    Document document = DocumentHelper.parseText(xml);
                                    Element root = document.getRootElement();
                                    Element info = root.element("RESULT_INFO").element("RECEIPT_NUM");
                                    //List<Element> nodes = root.elements("RESULT_INFO");
                                    //extendString6 = nodes.get(0).element("RECEIPT_NUM").getText();
                                    extendString6 = info.getText();
                                } else {
                                    throw new Exception("同步ERP失败," + result[1]);
                                    /*throw new RuntimeException("同步ERP失败,"+result[1]);*/
                                }
                            }
                            log.error("执行修改方法，processInstanceId=" + task.getProcessInstanceId() + ",extendString6=" + extendString6);
                            rkDao.updateSheetStauts(Integer.parseInt(task.getProcessInstanceId()), 41, extendString6);
                            sheetDao.auditSheet(sheetId, "SHEETRK_COMMIT", userId);
                            break;
                        case 36://物资出库单
                            SheetCK sheetCk = sheetCKService.getSheetCKOne(sheetId);
                            if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data;
                                String PIfaceCode;
                                String name;
                                int infTaskId;
                                if (sheetCk.getTypeid() == 772) { //调拨出库
                                    infTaskId = 145;
                                    PIfaceCode = "CUXYXINVTRX";
                                    name = "同步调拨出库接口,接口名称:invokews";
                                    data = sheetService.postDBDXML(sheetId, sheetCk);
                                } else {
                                    infTaskId = 143;
                                    PIfaceCode = "CUXYXINVISS";
                                    name = "同步领料出库,接口名称:invokews";
                                    data = sheetCKService.pushSLCKXML(sheetCk);
                                }
                                String[] result = syncToErp(data, PIfaceCode, userId, infTaskId, name);
                                if ("1".equals(result[0])) {
                                    AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
                                    executor.execute(new OutThreadCK(sheetCk, userId), new Date().getTime());
                                } else {
                                    throw new Exception("同步ERP失败," + result[1]);
                                }
                            }
                            // TODO 测试异步更新成本
                            /*AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
                            executor.execute(new OutThread(sheetCk, userId), new Date().getTime());*/

                            ckDao.updateSheetStauts(Integer.parseInt(task.getProcessInstanceId()), 41);//根据流程ID修改单据状态
                            sheetDao.auditSheet(sheetId, "SHEETCK_COMMIT", userId);
                            break;
                        case 315://物资退库单
                            TK sheetTK = sheetService.getTKSheetOne(sheetId);

                            if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postTKXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXINVREC", userId, 144, "同步退货接口,接口名称:invokews");
                                if ("1".equals(result[0])) {
                                    AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
                                    executor.execute(new OutThreadTK(sheetTK, userId), new Date().getTime());
                                } else {
                                    throw new Exception("同步ERP失败," + result[1]);
                                }
                            }
                            /*AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
                            executor.execute(new OutThreadTK(sheetTK, userId), new Date().getTime());*/

                            sheetDao.updateSheetStauts(routeId, 41);//根据流程ID修改单据状态
                            sheetDao.auditSheet(sheetId, "SHEETTK_COMMIT", userId);
                            break;
                        case 325://赠送品入库单
                            break;
                        case 506://调拨
                            /*if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postDBOccupyCountXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXINVRES", userId, 181, "同步调拨更新占用数量接口,接口名称:invokews");
                                if (!"1".equals(result[0])) {
                                    throw new Exception("同步ERP失败,"+result[1]);
                                }
                                //需添加erp调拨单接口逻辑，区分寄售和非寄售，接口interfacecode不一致，事务类型不一致
                            }*/
                            sheetDao.updateSheetStauts(routeId, 773);//根据流程ID修改单据状态 待出库
                            break;
                        case 848://库存盘点单
                            sheetDao.updateSheetStauts(routeId, 41);//根据流程ID修改单据状态
                            break;
                        case 835://杂出
                            sheetDao.updateSheetStauts(routeId, 41);//根据流程ID修改单据状态
                            sheetDao.auditSheet(sheetId, "SHEETZC_COMMIT", userId);
                            break;
                        case 832://杂入
                            rkDao.updateSheetStauts(routeId, 41, "");
                            sheetDao.auditSheet(sheetId, "SHEETRK_COMMIT", userId);
                            break;
                    }
                } catch (SQLException e) {
                    xmllog.message("sql出错:" + e);
                    log.error(e.getMessage());
                    log.errorPrintStacktrace(e);
                    flag = false;
                    message = e.getMessage();
                    throw new RuntimeException(e.getMessage());
                } catch (RuntimeException e) {
                    xmllog.message(e.getMessage());
                    log.error(e.getMessage());
                    message = e.getMessage();
                    flag = false;
                    throw new RuntimeException(e.getMessage());
                } catch (RemoteException e) {
                    xmllog.message("Remote出错:" + e);
                    log.error(e.getMessage());
                    log.errorPrintStacktrace(e);
                    flag = false;
                    throw new RuntimeException("调用ERP接口失败：" + e.getMessage());
                } catch (DocumentException e) {
                    xmllog.message("Document出错:" + e);
                    log.error(e.getMessage());
                    log.errorPrintStacktrace(e);
                    flag = false;
                    message = "解析返回xml失败：" + e.getMessage();
                    throw new RuntimeException("解析返回xml失败：" + e.getMessage());
                } catch (Exception e) {
                    xmllog.message("Exception出错:" + e);
                    log.error(e.getMessage());
                    log.errorPrintStacktrace(e);
                    flag = false;
                    message = "办结失败：" + e;
                    e.printStackTrace();
                }
            }
            if (flag) {
                log.error("结束流程：参数taskId=" + taskId);
                try {
                    taskService.complete(taskId, variables);
                } catch (Exception e) {
                    e.fillInStackTrace();
                    log.error("结束流程出错！");
                    log.errorPrintStacktrace(e);
                }
            }
        }
        Map ret = new HashMap();
        ret.put("flag", flag);
        ret.put("message", message);
        return ret;
    }

    /**
     * 异步任务 出库
     */
    public class OutThreadCK implements Runnable {

        private SheetCK sheetCK;
        private Integer userId;

        public OutThreadCK(SheetCK sheetCK, Integer userId) {
            this.sheetCK = sheetCK;
            this.userId = userId;
        }

        public void run() {
            Long renewalCost = Long.valueOf(AppConfig.getProperty("erp.renewalCost"));
            System.out.println("异步任务开启,出库单据sheetId:" + this.sheetCK.getId());
            log.error("异步任务开启,出库单据sheetId:" + this.sheetCK.getId());
            try {
                // 这个方法需要调用的毫秒数
                Thread.sleep(renewalCost * 1000);
                sheetCKService.asynRenewalCost(this.sheetCK, this.userId);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
                log.errorPrintStacktrace(e);
            } catch (Exception e) {
                log.error("同步更新成本出错！");
                log.errorPrintStacktrace(e);
            }
        }
    }

    /**
     * 异步任务 退库
     */
    public class OutThreadTK implements Runnable {

        private TK sheetTK;
        private Integer userId;

        public OutThreadTK(TK sheetTK, Integer userId) {
            this.sheetTK = sheetTK;
            this.userId = userId;
        }

        public void run() {
            Long renewalCost = Long.valueOf(AppConfig.getProperty("erp.renewalCost"));
            System.out.println("异步任务开启,退库单据sheetId:" + this.sheetTK.getId());
            log.error("异步任务开启,退库单据sheetId:" + this.sheetTK.getId());
            try {
                // 这个方法需要调用的毫秒数
                Thread.sleep(renewalCost * 1000);
                sheetService.asynRenewalCost(this.sheetTK, this.userId);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
                log.errorPrintStacktrace(e);
            } catch (Exception e) {
                log.error("同步更新成本出错！");
                log.errorPrintStacktrace(e);
            }
        }
    }
    /**
     * 异步任务 移库移位
     */
    public class OutThreadYKYW implements Runnable {

        private Ykyw ykyw;
        private Integer userId;

        public OutThreadYKYW(Ykyw ykyw, Integer userId) {
            this.ykyw = ykyw;
            this.userId = userId;
        }

        public void run() {
            Long renewalCost = Long.valueOf(AppConfig.getProperty("erp.renewalCost"));
            System.out.println("异步任务开启,移库移位单据sheetId:" + this.ykyw.getId());
            log.error("异步任务开启,移库移位单据sheetId:" + this.ykyw.getId());
            try {
                // 这个方法需要调用的毫秒数
                Thread.sleep(renewalCost * 1000);
                sheetService.asynRenewalCostYkyw(this.ykyw, this.userId);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
                log.errorPrintStacktrace(e);
            } catch (Exception e) {
                log.error("同步更新成本出错！");
                log.errorPrintStacktrace(e);
            }
        }
    }
    /**
     * @param taskId 任务id
     * @param page   当前页面
     * @param size   每页显示数量
     *               查询流转历史
     */
    public LayuiPage<Map<String, Object>> historyActInstanceList(String taskId, int page, int size) {
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        LayuiPage<Map<String, Object>> ret = new LayuiPage<>();
        String processInstanceId = taskId;
        if (null != task) {
            processInstanceId = task.getProcessInstanceId();
        }
        List<HistoricActivityInstance> list = historyService // 历史相关Service
                .createHistoricActivityInstanceQuery() // 创建历史活动实例查询
                .processInstanceId(processInstanceId) // 执行流程实例id
                .activityType("userTask")//.finished()
                .listPage(page, size);
        long count = historyService // 历史相关Service
                .createHistoricActivityInstanceQuery() // 创建历史活动实例查询
                .processInstanceId(processInstanceId) // 执行流程实例id
                .activityType("userTask")
                .count();//.finished()
        List<Map<String, Object>> list2 = new ArrayList<>();
        for (HistoricActivityInstance hai : list) {
            String name = "";
            if (hai.getAssignee() != null) {
                name = userService.listUserCodes(hai.getAssignee()).get(0).getName();
            }
            Map<String, Object> map = new HashMap<>();
            map.put("id", hai.getId());
            map.put("activityName", hai.getActivityName());
            map.put("assignee", name);
            map.put("startTime", hai.getStartTime());
            map.put("endTime", hai.getEndTime());
            List<Comment> comments = taskService.getTaskComments(hai.getTaskId());
            //如果当前任务有批注信息，添加到集合中
            if (comments != null && comments.size() > 0) {
                map.put("comment", comments.get(0).getFullMessage());
            }
            list2.add(map);
        }
        ret.setCount(count);
        ret.setData(list2);
        return ret;
    }

    /**
     * 添加批注
     *
     * @param taskId  任务id
     * @param comment 批注内容
     */
    public void addComment(String taskId, String comment) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstancesId = task.getProcessInstanceId();
        taskService.addComment(taskId, processInstancesId, comment);
    }

    /**
     * 根据流程实例ID删除流程
     *
     * @param processInstanceId 流程实例id
     * @param reason            删除原因
     */
    public void deleteProcessInstance(String processInstanceId, String reason) {
        runtimeService.deleteProcessInstance(processInstanceId, reason);//删除流程
    }

    /**
     * 根据流程实例ID获取TaskID
     *
     * @param processInsTanceId 流程实例ID
     */
    public String getTaskByPi(String processInsTanceId) {
        Task task = taskService.createTaskQuery()//
                .processInstanceId(processInsTanceId)
                .singleResult();
        if (task == null) {
            return processInsTanceId;
        } else {
            return task.getId();
        }
    }

    public LayuiPage<Map<String, Object>> queryProcessing(FormTemplateCondition condition, Map<String, Object> params) {
        return activitiDao.queryProcessing(condition, params);
    }

    public List<Map<String, Object>> queryProcessing(Map<String, Object> params) {
        return activitiDao.queryProcessing(params);
    }

    public LayuiPage<Map<String, Object>> queryProcessed(FormTemplateCondition condition, Map<String, Object> params) {
        return activitiDao.queryProcessed(condition, params);
    }

    /**
     * 驳回
     */
    @Transactional
    public boolean taskRollBackByHis(String taskId, String activitiBackId, String comment, String assignee, boolean isFirst) {
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
                return false;
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
            this.setAssignee(nextActivityImpl, assignee);
            TransitionImpl newTransition = currActivity
                    .createOutgoingTransition();
            newTransition.setDestination(nextActivityImpl);
            newTransitions.add(newTransition);
            // 完成任务 第一环节修改为制单中
            if (isFirst) {
                sheetDao.updateSheetStauts(Integer.parseInt(currTask.getProcessInstanceId()), 39);
            }
            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(instance.getId())
                    .taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
            for (Task task : tasks) {
                //设置批注
                if (comment != null && !("".equals(comment))) {
                    taskService.addComment(task.getId(), instance.getId(), comment);
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
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 用于提交前的验证
     *
     * @param taskId 单据主键
     */
    public JSONObject isSheetTrue(String taskId) {
        JSONObject ret = new JSONObject();
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            ret.put("code", false);
            ret.put("message", "环节已处理，请勿重新操作！");
        } else {
            Map<String, Object> sheet = sheetDao.getSheetOneByPi(task.getProcessInstanceId());//sheetService.getSheetOneAll(taskId);
            // id、code、kindId、routeId
            if (sheet != null) {
                switch (Integer.parseInt(sheet.get("kindId").toString())) {
                    case 246://库存盘点单
                        //查询是否都已盘点
                        ret = sheetPDService.isPDTrue(sheet);
                        break;
                    case 485://物资退货单
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 161://移库移位单
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 445://物资申请单
                        break;
                    case 588://物资接收单
                        ret = sheetCGService.isOrderTrue(Integer.parseInt(sheet.get("id").toString()));
                        break;
                    case 568://期初建账
                        break;
                    case 465://物资寄存入库单
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 466://物资寄存出库单
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 106://产品变更单
                        break;
                    case 650://物资领料单
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 35://物资入库单
                        ret = sheetRKService.isEqualsCount(Integer.parseInt(sheet.get("id").toString()));
                        break;
                    case 36://物资出库单
                        ret = sheetCKService.isTrue(sheet.get("id"));
                        break;
                    case 315://物资退库单
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 325://赠送品入库单
                        break;
                    case 506://调拨
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 832:
                        ret = sheetRKService.isEqualsCount(Integer.parseInt(sheet.get("id").toString()));
                        break;
                    case 835:
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    default:
                        ret.put("code", true);
                        ret.put("message", "");
                        break;
                }
            }
        }
        return ret;
    }

    @Transient
    public String[] syncToErp(String data, String pIfaceCode, Integer userId, Integer taskId, String taskName) throws Exception {
        int syncResult = 0;
        String msg;
        String syncData = "";
        CuxwmsStub stub = ESBPortProxy.getPayEntity();
        //if (stub != null && !"".equals(stub)) {
        if (stub != null) {
            CuxwmsStub.Invokews invokews = new CuxwmsStub.Invokews();
            invokews.setPIfaceCode(pIfaceCode);//"CUXYXPORET"
            invokews.setPBatchNumber("10000");
            //invokews.setPRequestData(new BASE64Encoder().encodeBuffer(data.getBytes()));
            invokews.setPRequestData(new BASE64Encoder().encode(data.getBytes("UTF-8")));

            invokews.setXReturnCode_out("");
            invokews.setXReturnMesg_out("");
            invokews.setXResponseData_out("");
            try {
                CuxwmsStub.InvokewsResponse response = stub.invokews(invokews);
                String returnCode = response.getXReturnCode_out();
                syncResult = "S000A000".equals(returnCode) ? 1 : 0;
                msg = response.getXReturnMesg_out();
                syncData = response.getXResponseData_out();
            } catch (RemoteException e) {
                e.printStackTrace();
                log.error(e.getMessage());
                log.errorPrintStacktrace(e);
                msg = "RemoteException";
            }
        } else {
            msg = "向http Header中注入ESB信息失败";
        }
        log.error("【接口同步】 xml数据：" + data);
        this.taskLogService.addTaskLog(taskId, taskName, syncResult, "", msg, userId);
        if (syncResult == 0) {
            return new String[]{syncResult + "", msg};
        }
        return new String[]{syncResult + "", syncData};
    }

    @Transactional
    public RenewalCostDTO syncToRenewalCost(Integer detailId, Integer userId) throws RuntimeException, Exception {
        String name = "同步更新成本,接口名称:CUXYXTRANCOST";
        String data = XmlUtil.renewalCostXML(detailId);
        xmllog.error("【更新成本】 dataXML:" + data);
        //TODO 正式
        String[] result = this.syncToErp(data, "CUXYXTRANCOST", userId, 0, name);
        xmllog.error("【更新成本】 返回参数result[0]:" + result[0] + ";result[1]:" + result[1]);
        if ("1".equals(result[0])) {
            RenewalCostDTO renewalCostDTO = XmlUtil.getRenewalCostDTO(result[1], detailId);
            return renewalCostDTO;
        } else {
            throw new RuntimeException("更新接口同步失败");
        }
        //TODO 测试
        /*return new RenewalCostDTO(detailId,10.33,20.66);*/
    }

}
