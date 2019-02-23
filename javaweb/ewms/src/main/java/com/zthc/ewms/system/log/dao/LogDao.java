package com.zthc.ewms.system.log.dao;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.log.entity.SystemLog;
import com.zthc.ewms.system.log.entity.SystemLogCondition;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LogDao {

    @Resource
    public BaseDao baseDao;

    //线程局部变量，用于存放当前用户信息等数据
    private static final ThreadLocal<BaseLocal> threadLocal = new ThreadLocal<BaseLocal>();

    /**
     * 得到当前线程（本地）数据对象
     * @return
     */
    public static BaseLocal getThreadLocal() {
        if (threadLocal.get() == null) {
            return new BaseLocal();
        } else {
            return threadLocal.get();
        }
    }

    /**
     * 把数据存入当前线程（本地）对象
     *
     * @param local 设置好共享值的对象
     */
    public static void setThreadLocal(BaseLocal local) {
        threadLocal.set(local);
    }

    /*-------------------------------------------------开始方法-------------------------------------------------*/


    /**
     * 新增单条
     * @param log
     */
    public void saveLog(SystemLog log){
        baseDao.save(log);
    }

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
        LayuiPage<SystemLog> ret = new LayuiPage<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hql_left = " select new SystemLog(s.id,s.logType,s.logObject,s.logAction,s.logDesc,s.logIp,u.name,s.createDate) ";
        String hql = " from SystemLog s , User u ";
        hql += " where 1 = 1 ";
        Map<String,Object> param = new HashMap<>();

        //查询条件
        if(!StringUtils.isEmpty(systemLog.getLogType())){
            hql += "and s.logType = :logtype ";
            param.put("logtype",systemLog.getLogType());
        }
        if(!StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)){
            Date start = sdf.parse(startTime);
            hql += " and s.createDate >= :starttime ";
            param.put("starttime",start);
        } else if (StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            Date end = sdf.parse(endTime);
            hql += " and s.createDate <= :endtime ";
            param.put("endtime", end);
        }else if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)){
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            hql += "and s.createDate BETWEEN :starttime and :endtime ";
            param.put("starttime",start);
            param.put("endtime",end);
        }

            hql += "and s.creator = u.id order by s.createDate DESC ";

        String totalsql = "select count(s.id) " + hql;

        List<SystemLog> logList = baseDao.findByHql(hql_left+hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);
        ret.setData(logList);
        ret.setCount(total);
        return ret;
    }

    /**
     * 删除系统日志
     * @param ids
     */
    public void deleteLog(Integer[] ids){
        String hql = "delete SystemLog where 1 = 1 and id in :ids";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids",ids);
        query.executeUpdate();

    }
}
