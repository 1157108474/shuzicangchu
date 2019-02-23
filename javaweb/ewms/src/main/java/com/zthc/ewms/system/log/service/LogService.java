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
     * ��ȡϵͳ��־�б�
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
     * ɾ��ϵͳ��־
     * @param ids
     */
    @Transactional
    public void deleteLog(Integer[] ids){
        this.dao.deleteLog(ids);
    }


    /**
     * ��ģ�����ϵͳ��־����
     * @param logType 0:ϵͳ��־ 1:ҵ����־
     * @param logObject ģ������ ���ö����
     * @param logAction �������� ���ö����
     * @param logDesc ����( ����ֵ + �������)
     * @param logIp IP��ַ( ��session�л�ȡ )
     * @param creator ������ID
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
     * ת��JSON�ַ���
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
