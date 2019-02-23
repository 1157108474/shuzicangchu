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
     * 获取接口日志列表
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
     * 删除（物理删除）
     * @param ids
     */
    public void taskLogDelete(Integer[] ids){
        this.dao.taskLogDelete(ids);
    }

    /**
     * 接口添加方法
     * @param taskId 任务管理ID
     * @param infTaskdetailn 对应的接口名称
     * @param syncResult 同步结果 0:未同步 1:同步成功 2:同步失败 3:同步异常
     * @param infContent 接口详情（参数值）
     * @param infErrorContent 报错信息
     * @param creator 同步人ID
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
