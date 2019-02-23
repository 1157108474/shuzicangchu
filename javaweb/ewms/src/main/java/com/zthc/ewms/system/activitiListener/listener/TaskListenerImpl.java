package com.zthc.ewms.system.activitiListener.listener;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


public class TaskListenerImpl implements Serializable,TaskListener, ExecutionListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		/*if(null == runtimeService){
			runtimeService = (RuntimeService) ApplicationContextHandler.getBean("runtimeService");
		}
		System.out.println("xml：" + runtimeService + " ActivitiListenner");*/
	}

	/**用来指定任务的办理人*/
	@Override
	public void notify(DelegateTask delegateTask) {
		//指定个人任务的办理人，也可以指定组任务的办理人
		//个人任务：通过类去查询数据库，将下一个任务的办理人查询获取，然后通过setAssignee()的方法指定任务的办理人
		String processInstanceId = delegateTask.getProcessInstanceId();
		//ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String eventName = delegateTask.getEventName();
		if ("create".endsWith(eventName)) {

			/*if(1==1){//下一环节人员设置一个人
				delegateTask.setAssignee("3");//从下环节办理人中查询
			}else if(1==1){//下一环节人员设置多个人
				List<String> emp = new ArrayList<String>();
				emp.add("5");
				emp.add("6");
				delegateTask.addCandidateUsers(emp);//Arrays.asList(empLoyees)
			}else{//下一环节人员设置没有人，即保存步骤
			}*/
			//System.out.println("create========="+delegateTask.getName());
		}else if ("assignment".endsWith(eventName)) {
			//System.out.println("assignment========"+delegateTask.getName());
		}else if ("complete".endsWith(eventName)) {
			//System.out.println("complete==========="+delegateTask.getName());
		}else if ("delete".endsWith(eventName)) {
			//System.out.println("delete============="+delegateTask.getName());
		}
	}
}
