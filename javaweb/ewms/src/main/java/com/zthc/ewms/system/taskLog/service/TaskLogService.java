package com.zthc.ewms.system.taskLog.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.task.entity.guard.InfTask;
import com.zthc.ewms.system.taskLog.dao.TaskLogDao;
import com.zthc.ewms.system.taskLog.entity.TaskLog;
import com.zthc.ewms.system.taskLog.entity.TaskLogCondition;
import com.zthc.ewms.system.taskLog.entity.TaskLogView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

@Service
public class TaskLogService {

    @Resource(name = "taskLogDao")
    public TaskLogDao dao;

    /**
     * ��ȡ�ӿ���־�б�
     * @param taskLog
     * @param condition
     * @param startTime
     * @param endTime
     * @return
     */
    public LayuiPage<TaskLogView> listTaskLog(TaskLogView taskLog, TaskLogCondition condition, String startTime, String endTime)
            throws ParseException{
        return this.dao.listTaskLog(taskLog,condition,startTime,endTime);
    }

    /**
     * ɾ��������ɾ����
     * @param ids
     */
    public void taskLogDelete(Integer[] ids){
        this.dao.taskLogDelete(ids);
    }

    /**
     * �ӿ���ӷ���
     * @param taskId �������ID
     * @param infTaskdetailn ��Ӧ�Ľӿ�����
     * @param syncResult ͬ����� 0:δͬ�� 1:ͬ���ɹ� 2:ͬ��ʧ�� 3:ͬ���쳣
     * @param infContent �ӿ����飨����ֵ��
     * @param infErrorContent ������Ϣ
     * @param creator ͬ����ID
     */
    @Transactional
    public void addTaskLog(Integer taskId,String infTaskdetailn,Integer syncResult,String infContent,String infErrorContent,
                           Integer creator){
        TaskLog taskLog = new TaskLog();
        Date now = new Date();
        taskLog.setTaskId(taskId);
        taskLog.setInfTaskdetailn(infTaskdetailn);
        taskLog.setSyncResult(syncResult);
        taskLog.setInfContent(infContent);
        taskLog.setInfErrorContent(infErrorContent);
        taskLog.setCreator(creator);
        taskLog.setCreateDate(now);
        this.dao.saveTaskLog(taskLog);
    }
}
