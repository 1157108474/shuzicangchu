package com.zthc.ewms.system.log.service;

import com.alibaba.fastjson.JSONException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.log.dao.LogDao;
import com.zthc.ewms.system.log.entity.SystemLog;
import com.zthc.ewms.system.log.entity.SystemLogCondition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@Service
public class LogService {

    @Resource(name = "logDao")
    public LogDao dao;

    /**
     * 获取系统日志列表
     * @param systemLog
     * @param condition
     * @param startTime
     * @param endTime
     * @return
     */
    public LayuiPage<SystemLog> listLog(SystemLog systemLog, SystemLogCondition condition,String startTime,String endTime)
            throws ParseException{
        return this.dao.listLog(systemLog,condition,startTime,endTime);
    }

    /**
     * 删除系统日志
     * @param ids
     */
    @Transactional
    public void deleteLog(Integer[] ids){
        this.dao.deleteLog(ids);
    }


    /**
     * 各模块添加系统日志方法
     * @param logType 0:系统日志 1:业务日志
     * @param logObject 模块名称 详查枚举类
     * @param logAction 操作类型 详查枚举类
     * @param logDesc 描述( 参数值 + 具体操作)
     * @param logIp IP地址( 从session中获取 )
     * @param creator 操作人ID
     */
    @Transactional
    public void addSystemLog(Integer logType,Integer logObject,Integer logAction,String logDesc, String logIp,
                             Integer creator){
        SystemLog systemLog = new SystemLog();
        Date now = new Date();
        systemLog.setLogType(logType);
        systemLog.setLogObject(logObject);
        systemLog.setLogAction(logAction);
        systemLog.setLogDesc(logDesc);
        systemLog.setLogIp(logIp);
        systemLog.setCreator(creator);
        systemLog.setCreateDate(now);
        this.dao.saveLog(systemLog);
    }

    /**
     * 转换JSON字符串
     * @param obj
     * @param <T>
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public static<T> String objectToJson(T obj) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
