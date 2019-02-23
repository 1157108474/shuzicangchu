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
     * ��ȡ��ǰ���ڵĺ�ѡ�ˣ���������
     */
    public List<Expression> getThisAssigent(String taskId) {
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)
                .singleResult();
        String processDefinitionId = task.getProcessDefinitionId();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                .processInstanceId(processInstanceId)//ʹ������ʵ��ID��ѯ
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
     * ��ȡ���߶���
     *
     * @param taskId  ����ID
     * @param isAdmin �Ƿ�չʾ��ᰴť
     */
    public List<PvmTransition> getOutcome(String taskId, Boolean isAdmin) {
        //1:ʹ������ID����ѯ�������
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//ʹ������ID��ѯ
                .singleResult();
        //2����ȡ���̶���ID
        String processDefinitionId = task.getProcessDefinitionId();
        //3����ѯProcessDefinitionEntiy����
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        //ʹ���������Task��ȡ����ʵ��ID
        String processInstanceId = task.getProcessInstanceId();
        //ʹ������ʵ��ID����ѯ����ִ�е�ִ�ж������������ʵ������
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                .processInstanceId(processInstanceId)//ʹ������ʵ��ID��ѯ
                .singleResult();
        //��ȡ��ǰ���id
        String activityId = pi.getActivityId();
        //4����ȡ��ǰ�Ļ
        ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
        //5����ȡ��ǰ����֮�����ߵ�����
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
     * @param taskId  ����ID
     * @param outcome ����name
     */
    public ActivityImpl getNextActivityImpl(String taskId, String outcome) {
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//ʹ������ID��ѯ
                .singleResult();
        //2����ȡ���̶���ID
        String processDefinitionId = task.getProcessDefinitionId();
        //3����ѯProcessDefinitionEntiy����
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        //��ȡ��ǰ����֮�����ߵ�����
        List<PvmTransition> pvmList = getOutcome(taskId, false);
        ActivityImpl activityImpl = null;
        for (PvmTransition pvm : pvmList) {
            //String name = (String) pvm.getProperty("name");
            String name = pvm.getId();
            if (outcome.equals(name)) {
                PvmActivity ac = pvm.getDestination(); //��ȡ��·���յ�ڵ�
                if (!"endEvent".equals((String) ac.getProperty("type"))) {
                    activityImpl = processDefinitionEntity.findActivity(ac.getId());
                }
            }
        }
        return activityImpl;
    }

    /**
     * @param taskId  ����ID
     * @param outcome ����name
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
     * ��ȡ��һ���ڰ�ť
     *
     * @param menuCode �˵�code
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
                            //TODO ��ʼ�ж������ֻȡ���һ��
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
     * ��������֮���ȡ���ڰ�ť
     *
     * @param taskId ����id
     */
    public List<Dictionary> getPartButtonOnStar(String taskId) {
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//ʹ������ID��ѯ
                .singleResult();
        List<Dictionary> dictionaries = new ArrayList<>();
        if (null != task) {
            //2����ȡ���̶���ID
            String processDefinitionId = task.getProcessDefinitionId();
            //3����ѯProcessDefinitionEntiy����
            ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
            //ʹ���������Task��ȡ����ʵ��ID
            String processInstanceId = task.getProcessInstanceId();
            //ʹ������ʵ��ID����ѯ����ִ�е�ִ�ж������������ʵ������
            ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                    .processInstanceId(processInstanceId)//ʹ������ʵ��ID��ѯ
                    .singleResult();
            //��ȡ��ǰ���id
            String activityId = pi.getActivityId();
            //4����ȡ��ǰ�Ļ
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
            //�԰�ť��������
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
     * ����������ɸ�������
     *
     * @param taskId  ����id
     * @param outcome ����name
     * @param userId  �»�����Աcode
     */
    @Transactional
    public void completeMyPersonalTask(String taskId, String outcome, String userId) {
        //�����»�����Ա
        ActivityImpl activityImpl = this.getNextActivityImpl(taskId, outcome);
        this.setAssignee(activityImpl, userId);
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("message", outcome);
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//ʹ������ID��ѯ
                .singleResult();
        int i = 0;
        if (task != null) {
            Map<String, Object> sheet = sheetDao.getSheetOneByPi(task.getProcessInstanceId());
            if (sheet != null) {
                //����id
                Integer routeId = Integer.parseInt(sheet.get("routeId").toString());
                switch (Integer.parseInt(sheet.get("kindId").toString())) {
                    case 35://���
                        i = sheetDao.updateSheetRKStauts(routeId, 40);
                        break;
                    case 36://����
                        i = sheetDao.updateSheetCKStauts(routeId, 40);
                        break;
                    default://����
                        i = sheetDao.updateSheetStauts(routeId, 40);
                }
            }
        }
        if (i > 0) {
            taskService.complete(taskId, variables);
        }

        //System.out.println("�������");
    }

    /**
     * ��󻷽�ʹ�ã��������
     *
     * @param taskId ����ID
     */
    @Transactional
    public Map completeTask(String taskId, String outcome, int userId) throws Exception {
        log.error("������󻷽ڣ�����taskId��" + taskId + ",outcome:" + outcome + ",userId:" + userId);
        //dictionaryService.getDicFoemTem();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("message", outcome);
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//ʹ������ID��ѯ
                .singleResult();
        boolean flag = true;
        String message = "";
        if (task != null) {
            log.error("����task.getProcessInstanceId()��" + task.getProcessInstanceId());
            Map<String, Object> sheet = sheetDao.getSheetOneByPi(task.getProcessInstanceId());
            log.error("���뵥���ж�");
            if (sheet != null) {
                Integer sheetId = Integer.parseInt(sheet.get("id").toString());
                //����id
                Integer routeId = Integer.parseInt(sheet.get("routeId").toString());
                try {
                    switch (Integer.parseInt(sheet.get("kindId").toString())) {
                        case 246://����̵㵥
                            sheetDao.updateSheetStauts(routeId, 41);//��������ID�޸ĵ���״̬
                            break;
                        case 485://�����˻���
                            String extendString7 = "";
                            if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postTHXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXPORET", userId, 142, "ͬ���˻������,�ӿ�����:invokews");
                                if ("1".equals(result[0])) {
                                    String xml = new String(new BASE64Decoder().decodeBuffer(result[1]));
                                    Document document = DocumentHelper.parseText(xml);
                                    Element root = document.getRootElement();
                                    //List<Element> nodes = root.elements("WSINTERFACE");
                                    //extendString7 = nodes.get(0).element("RECEIPT_NUM").getText();
                                    Element info = root.element("RESULT_INFO").element("RECEIPT_NUM");
                                    extendString7 = info.getText();
                                } else {
                                    throw new Exception("ͬ��ERPʧ��," + result[1]);
                                }
                            }
                            sheetDao.updateTHSheetStauts(sheetId, 41, extendString7);//��������ID�޸ĵ���״̬
                            sheetDao.auditSheet(sheetId, "SHEETTH_COMMIT", userId);
                            break;
                        case 161://�ƿ���λ��
                            Ykyw ykyw = sheetService.getYkywSheetOne(sheetId);
                            if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postYWXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXINVTRX", userId, 145, "ͬ�������ӿ�,�ӿ�����:invokews");
                                if ("1".equals(result[0])) {
                                    AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
                                    executor.execute(new OutThreadYKYW(ykyw, userId), new Date().getTime());
                                } else {
                                    throw new Exception("ͬ��ERPʧ��," + result[1]);
                                }

                            }
                            sheetDao.updateSheetStauts(routeId, 41);//��������ID�޸ĵ���״̬
                            sheetDao.auditSheet(sheetId, "SHEETYW_COMMIT", userId);
                            break;
                        case 445://�������뵥
                            break;
                        case 588://���ʽ��յ�
                            sheetDao.updateSheetStauts(routeId, 41);
                            sheetDao.auditSheet(sheetId, "SHEETJS_COMMIT", userId);
                            break;
                        case 568://�ڳ�����
                            break;
                        case 465://���ʼĴ���ⵥ
                            sheetDao.updateSheetStauts(routeId, 41);//��������ID�޸ĵ���״̬
                            sheetDao.auditSheet(sheetId, "SHEETJCRK_COMMIT", userId);
                            break;
                        case 466://���ʼĴ���ⵥ
                            sheetDao.updateSheetStauts(routeId, 41);//��������ID�޸ĵ���״̬
                            sheetDao.auditSheet(sheetId, "SHEETJCCK_COMMIT", userId);
                            break;
                        case 106://��Ʒ�����
                            break;
                        case 650://�������ϵ�
                            /*if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postLLOccupyCountXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXINVRES", userId, 182, "ͬ�����ϸ���ռ�������ӿ�,�ӿ�����:invokews");
                                if (!"1".equals(result[0])) {
                                    throw new Exception("ͬ��ERPʧ��,"+result[1]);
                                }
                            }*/
                            sheetDao.updateSheetStauts(routeId, 41);//��������ID�޸ĵ���״̬
                            break;
                        case 35://������ⵥ
                            String extendString6 = "";
                            if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postRKXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXPOREC", userId, 141, "ͬ�����ӿ�,�ӿ�����:invokews");
                                log.error("�ӿڷ��ز���result=" + result[0] + "," + result[1]);
                                if ("1".equals(result[0])) {
                                    String xml = new String(new BASE64Decoder().decodeBuffer(result[1]), "utf-8");
                                    Document document = DocumentHelper.parseText(xml);
                                    Element root = document.getRootElement();
                                    Element info = root.element("RESULT_INFO").element("RECEIPT_NUM");
                                    //List<Element> nodes = root.elements("RESULT_INFO");
                                    //extendString6 = nodes.get(0).element("RECEIPT_NUM").getText();
                                    extendString6 = info.getText();
                                } else {
                                    throw new Exception("ͬ��ERPʧ��," + result[1]);
                                    /*throw new RuntimeException("ͬ��ERPʧ��,"+result[1]);*/
                                }
                            }
                            log.error("ִ���޸ķ�����processInstanceId=" + task.getProcessInstanceId() + ",extendString6=" + extendString6);
                            rkDao.updateSheetStauts(Integer.parseInt(task.getProcessInstanceId()), 41, extendString6);
                            sheetDao.auditSheet(sheetId, "SHEETRK_COMMIT", userId);
                            break;
                        case 36://���ʳ��ⵥ
                            SheetCK sheetCk = sheetCKService.getSheetCKOne(sheetId);
                            if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data;
                                String PIfaceCode;
                                String name;
                                int infTaskId;
                                if (sheetCk.getTypeid() == 772) { //��������
                                    infTaskId = 145;
                                    PIfaceCode = "CUXYXINVTRX";
                                    name = "ͬ����������ӿ�,�ӿ�����:invokews";
                                    data = sheetService.postDBDXML(sheetId, sheetCk);
                                } else {
                                    infTaskId = 143;
                                    PIfaceCode = "CUXYXINVISS";
                                    name = "ͬ�����ϳ���,�ӿ�����:invokews";
                                    data = sheetCKService.pushSLCKXML(sheetCk);
                                }
                                String[] result = syncToErp(data, PIfaceCode, userId, infTaskId, name);
                                if ("1".equals(result[0])) {
                                    AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
                                    executor.execute(new OutThreadCK(sheetCk, userId), new Date().getTime());
                                } else {
                                    throw new Exception("ͬ��ERPʧ��," + result[1]);
                                }
                            }
                            // TODO �����첽���³ɱ�
                            /*AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
                            executor.execute(new OutThread(sheetCk, userId), new Date().getTime());*/

                            ckDao.updateSheetStauts(Integer.parseInt(task.getProcessInstanceId()), 41);//��������ID�޸ĵ���״̬
                            sheetDao.auditSheet(sheetId, "SHEETCK_COMMIT", userId);
                            break;
                        case 315://�����˿ⵥ
                            TK sheetTK = sheetService.getTKSheetOne(sheetId);

                            if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postTKXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXINVREC", userId, 144, "ͬ���˻��ӿ�,�ӿ�����:invokews");
                                if ("1".equals(result[0])) {
                                    AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
                                    executor.execute(new OutThreadTK(sheetTK, userId), new Date().getTime());
                                } else {
                                    throw new Exception("ͬ��ERPʧ��," + result[1]);
                                }
                            }
                            /*AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
                            executor.execute(new OutThreadTK(sheetTK, userId), new Date().getTime());*/

                            sheetDao.updateSheetStauts(routeId, 41);//��������ID�޸ĵ���״̬
                            sheetDao.auditSheet(sheetId, "SHEETTK_COMMIT", userId);
                            break;
                        case 325://����Ʒ��ⵥ
                            break;
                        case 506://����
                            /*if ("1".equalsIgnoreCase(AppConfig.getProperty("syncToERP"))) {
                                String data = sheetService.postDBOccupyCountXML(sheetId);
                                String[] result = syncToErp(data, "CUXYXINVRES", userId, 181, "ͬ����������ռ�������ӿ�,�ӿ�����:invokews");
                                if (!"1".equals(result[0])) {
                                    throw new Exception("ͬ��ERPʧ��,"+result[1]);
                                }
                                //�����erp�������ӿ��߼������ּ��ۺͷǼ��ۣ��ӿ�interfacecode��һ�£��������Ͳ�һ��
                            }*/
                            sheetDao.updateSheetStauts(routeId, 773);//��������ID�޸ĵ���״̬ ������
                            break;
                        case 848://����̵㵥
                            sheetDao.updateSheetStauts(routeId, 41);//��������ID�޸ĵ���״̬
                            break;
                        case 835://�ӳ�
                            sheetDao.updateSheetStauts(routeId, 41);//��������ID�޸ĵ���״̬
                            sheetDao.auditSheet(sheetId, "SHEETZC_COMMIT", userId);
                            break;
                        case 832://����
                            rkDao.updateSheetStauts(routeId, 41, "");
                            sheetDao.auditSheet(sheetId, "SHEETRK_COMMIT", userId);
                            break;
                    }
                } catch (SQLException e) {
                    xmllog.message("sql����:" + e);
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
                    xmllog.message("Remote����:" + e);
                    log.error(e.getMessage());
                    log.errorPrintStacktrace(e);
                    flag = false;
                    throw new RuntimeException("����ERP�ӿ�ʧ�ܣ�" + e.getMessage());
                } catch (DocumentException e) {
                    xmllog.message("Document����:" + e);
                    log.error(e.getMessage());
                    log.errorPrintStacktrace(e);
                    flag = false;
                    message = "��������xmlʧ�ܣ�" + e.getMessage();
                    throw new RuntimeException("��������xmlʧ�ܣ�" + e.getMessage());
                } catch (Exception e) {
                    xmllog.message("Exception����:" + e);
                    log.error(e.getMessage());
                    log.errorPrintStacktrace(e);
                    flag = false;
                    message = "���ʧ�ܣ�" + e;
                    e.printStackTrace();
                }
            }
            if (flag) {
                log.error("�������̣�����taskId=" + taskId);
                try {
                    taskService.complete(taskId, variables);
                } catch (Exception e) {
                    e.fillInStackTrace();
                    log.error("�������̳���");
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
     * �첽���� ����
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
            System.out.println("�첽������,���ⵥ��sheetId:" + this.sheetCK.getId());
            log.error("�첽������,���ⵥ��sheetId:" + this.sheetCK.getId());
            try {
                // ���������Ҫ���õĺ�����
                Thread.sleep(renewalCost * 1000);
                sheetCKService.asynRenewalCost(this.sheetCK, this.userId);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
                log.errorPrintStacktrace(e);
            } catch (Exception e) {
                log.error("ͬ�����³ɱ�����");
                log.errorPrintStacktrace(e);
            }
        }
    }

    /**
     * �첽���� �˿�
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
            System.out.println("�첽������,�˿ⵥ��sheetId:" + this.sheetTK.getId());
            log.error("�첽������,�˿ⵥ��sheetId:" + this.sheetTK.getId());
            try {
                // ���������Ҫ���õĺ�����
                Thread.sleep(renewalCost * 1000);
                sheetService.asynRenewalCost(this.sheetTK, this.userId);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
                log.errorPrintStacktrace(e);
            } catch (Exception e) {
                log.error("ͬ�����³ɱ�����");
                log.errorPrintStacktrace(e);
            }
        }
    }
    /**
     * �첽���� �ƿ���λ
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
            System.out.println("�첽������,�ƿ���λ����sheetId:" + this.ykyw.getId());
            log.error("�첽������,�ƿ���λ����sheetId:" + this.ykyw.getId());
            try {
                // ���������Ҫ���õĺ�����
                Thread.sleep(renewalCost * 1000);
                sheetService.asynRenewalCostYkyw(this.ykyw, this.userId);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
                log.errorPrintStacktrace(e);
            } catch (Exception e) {
                log.error("ͬ�����³ɱ�����");
                log.errorPrintStacktrace(e);
            }
        }
    }
    /**
     * @param taskId ����id
     * @param page   ��ǰҳ��
     * @param size   ÿҳ��ʾ����
     *               ��ѯ��ת��ʷ
     */
    public LayuiPage<Map<String, Object>> historyActInstanceList(String taskId, int page, int size) {
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//ʹ������ID��ѯ
                .singleResult();
        LayuiPage<Map<String, Object>> ret = new LayuiPage<>();
        String processInstanceId = taskId;
        if (null != task) {
            processInstanceId = task.getProcessInstanceId();
        }
        List<HistoricActivityInstance> list = historyService // ��ʷ���Service
                .createHistoricActivityInstanceQuery() // ������ʷ�ʵ����ѯ
                .processInstanceId(processInstanceId) // ִ������ʵ��id
                .activityType("userTask")//.finished()
                .listPage(page, size);
        long count = historyService // ��ʷ���Service
                .createHistoricActivityInstanceQuery() // ������ʷ�ʵ����ѯ
                .processInstanceId(processInstanceId) // ִ������ʵ��id
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
            //�����ǰ��������ע��Ϣ����ӵ�������
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
     * �����ע
     *
     * @param taskId  ����id
     * @param comment ��ע����
     */
    public void addComment(String taskId, String comment) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstancesId = task.getProcessInstanceId();
        taskService.addComment(taskId, processInstancesId, comment);
    }

    /**
     * ��������ʵ��IDɾ������
     *
     * @param processInstanceId ����ʵ��id
     * @param reason            ɾ��ԭ��
     */
    public void deleteProcessInstance(String processInstanceId, String reason) {
        runtimeService.deleteProcessInstance(processInstanceId, reason);//ɾ������
    }

    /**
     * ��������ʵ��ID��ȡTaskID
     *
     * @param processInsTanceId ����ʵ��ID
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
     * ����
     */
    @Transactional
    public boolean taskRollBackByHis(String taskId, String activitiBackId, String comment, String assignee, boolean isFirst) {
        try {
            Map<String, Object> variables;
            // ȡ�õ�ǰ����
            HistoricTaskInstance currTask = historyService
                    .createHistoricTaskInstanceQuery().taskId(taskId)
                    .singleResult();
            // ȡ������ʵ��
            ProcessInstance instance = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(currTask.getProcessInstanceId())
                    .singleResult();
            if (instance == null) {

                //���̽���
            }
            variables = instance.getProcessVariables();
            // ȡ�����̶���
            ProcessDefinitionEntity definition = (ProcessDefinitionEntity) (repositoryService.getProcessDefinition(currTask
                    .getProcessDefinitionId()));

            if (definition == null) {
                //log.error("���̶���δ�ҵ�");
                return false;
            }
            ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
                    .findActivity(currTask.getTaskDefinitionKey());//��ǰ�
            // �����ǰ��ĳ���
            List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
            List<PvmTransition> pvmTransitionList = currActivity//��ǰ�����
                    .getOutgoingTransitions();
            for (PvmTransition pvmTransition : pvmTransitionList) {
                oriPvmTransitionList.add(pvmTransition);
            }
            pvmTransitionList.clear();
            //�³���
            List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();
            ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition)
                    .findActivity(activitiBackId);
            this.setAssignee(nextActivityImpl, assignee);
            TransitionImpl newTransition = currActivity
                    .createOutgoingTransition();
            newTransition.setDestination(nextActivityImpl);
            newTransitions.add(newTransition);
            // ������� ��һ�����޸�Ϊ�Ƶ���
            if (isFirst) {
                sheetDao.updateSheetStauts(Integer.parseInt(currTask.getProcessInstanceId()), 39);
            }
            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(instance.getId())
                    .taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
            for (Task task : tasks) {
                //������ע
                if (comment != null && !("".equals(comment))) {
                    taskService.addComment(task.getId(), instance.getId(), comment);
                }
                taskService.complete(task.getId(), variables);
                //historyService.deleteHistoricTaskInstance(task.getId());
                //historyService.deleteHistoricProcessInstance(task.getId());
            }

            // �ָ�����
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
     * �����ύǰ����֤
     *
     * @param taskId ��������
     */
    public JSONObject isSheetTrue(String taskId) {
        JSONObject ret = new JSONObject();
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            ret.put("code", false);
            ret.put("message", "�����Ѵ����������²�����");
        } else {
            Map<String, Object> sheet = sheetDao.getSheetOneByPi(task.getProcessInstanceId());//sheetService.getSheetOneAll(taskId);
            // id��code��kindId��routeId
            if (sheet != null) {
                switch (Integer.parseInt(sheet.get("kindId").toString())) {
                    case 246://����̵㵥
                        //��ѯ�Ƿ����̵�
                        ret = sheetPDService.isPDTrue(sheet);
                        break;
                    case 485://�����˻���
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 161://�ƿ���λ��
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 445://�������뵥
                        break;
                    case 588://���ʽ��յ�
                        ret = sheetCGService.isOrderTrue(Integer.parseInt(sheet.get("id").toString()));
                        break;
                    case 568://�ڳ�����
                        break;
                    case 465://���ʼĴ���ⵥ
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 466://���ʼĴ���ⵥ
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 106://��Ʒ�����
                        break;
                    case 650://�������ϵ�
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 35://������ⵥ
                        ret = sheetRKService.isEqualsCount(Integer.parseInt(sheet.get("id").toString()));
                        break;
                    case 36://���ʳ��ⵥ
                        ret = sheetCKService.isTrue(sheet.get("id"));
                        break;
                    case 315://�����˿ⵥ
                        ret = sheetService.isTrue(sheet.get("id"));
                        break;
                    case 325://����Ʒ��ⵥ
                        break;
                    case 506://����
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
            msg = "��http Header��ע��ESB��Ϣʧ��";
        }
        log.error("���ӿ�ͬ���� xml���ݣ�" + data);
        this.taskLogService.addTaskLog(taskId, taskName, syncResult, "", msg, userId);
        if (syncResult == 0) {
            return new String[]{syncResult + "", msg};
        }
        return new String[]{syncResult + "", syncData};
    }

    @Transactional
    public RenewalCostDTO syncToRenewalCost(Integer detailId, Integer userId) throws RuntimeException, Exception {
        String name = "ͬ�����³ɱ�,�ӿ�����:CUXYXTRANCOST";
        String data = XmlUtil.renewalCostXML(detailId);
        xmllog.error("�����³ɱ��� dataXML:" + data);
        //TODO ��ʽ
        String[] result = this.syncToErp(data, "CUXYXTRANCOST", userId, 0, name);
        xmllog.error("�����³ɱ��� ���ز���result[0]:" + result[0] + ";result[1]:" + result[1]);
        if ("1".equals(result[0])) {
            RenewalCostDTO renewalCostDTO = XmlUtil.getRenewalCostDTO(result[1], detailId);
            return renewalCostDTO;
        } else {
            throw new RuntimeException("���½ӿ�ͬ��ʧ��");
        }
        //TODO ����
        /*return new RenewalCostDTO(detailId,10.33,20.66);*/
    }

}
