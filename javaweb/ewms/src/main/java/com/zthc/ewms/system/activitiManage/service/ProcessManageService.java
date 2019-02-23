package com.zthc.ewms.system.activitiManage.service;


import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.activitiManage.entity.ActDic;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.service.FormTemplateService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcessManageService {


    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private ManagementService managementService;
    @Resource
    private ActivitiService activitiService;
    @Resource(name="formTemplateService")
    public FormTemplateService formTemplateService;
    @Resource
    public ActdicService actdicService;



    /**
     * ������ǰ���̣���δ���͵���һ���ڡ�
     *��������ʵ��id:processInstanceId
     *��ǰ����id��taskId
     *@param menuCode �˵�code
     *@param userCode ��ǰ��code
     */
    @Transactional
    public Map<String, Object> startProcessInstance(String menuCode, String userCode) {//������ǰ���̣���δ���͵���һ����,��������ʵ��ID
        List<FormTemplate> formTemplates = formTemplateService.getFromTemByMenuStatus(menuCode,2);
        Map<String,Object> map = new HashMap<>();
        if(formTemplates.size()>0){
            //List<ActDic> actDics = actdicService.getActDicByModelID(formTemplates.get(0).getProcessDefinitionKey());
            ActDic actDics = actdicService.getActDicBydicID(formTemplates.get(0).getProcessDefinitionKey());
            if(actDics!=null&&actDics.getDepId()!=null){
                List<ProcessDefinition> list = repositoryService
                        .createProcessDefinitionQuery()
                        .deploymentId(actDics.getDepId())
                        .orderByProcessDefinitionVersion().desc()
                        .list();
                String processDefinitionKey = list.get(0).getKey();
                ProcessInstance pi = runtimeService
                        .startProcessInstanceByKey(processDefinitionKey);
                Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).active().singleResult();
                taskService.setAssignee(task.getId(),userCode);//��sessin��ȥ��ǰ��¼��Ա�����õ�ǰ������
                map.put("success","�����ɹ�");
                map.put("processInstanceId",pi.getId());
                map.put("taskId",task.getId());
            }else{
                map.put("success","��������δ����");
                map.put("processInstanceId",0);
                map.put("taskId",0);
            }
        }else{
            map.put("success","�ò˵���δ��������");
            map.put("processInstanceId",0);
            map.put("taskId",0);
        }
        return map;
    }


    /**
     * ���沢�ύ��ֻ�ʺ��»���һ������
     * @param processDefinitionKey  ����key
    * */
    public Integer  startProcessInstanceGoNext(String processDefinitionKey) {//������ǰ���̣�����ɵ�ǰ�����͵���һ���ڡ�
        Integer msg = 0;
        try {
            ProcessInstance pi = runtimeService
                    .startProcessInstanceByKey(processDefinitionKey);
            List<Task> list=taskService
                    .createTaskQuery()
                    .processInstanceId(pi.getId())
                    .orderByTaskCreateTime().asc()
                    .list();
            String taskId = list.get(0).getId();
            taskService.setAssignee(taskId,"4");//���õ�ǰ��
            taskService.complete(taskId);//��ɸ�������
            msg = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}
