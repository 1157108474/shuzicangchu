package com.zthc.ewms.system.taskLog.dao;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.taskLog.entity.TaskLog;
import com.zthc.ewms.system.taskLog.entity.TaskLogCondition;
import com.zthc.ewms.system.taskLog.entity.TaskLogView;
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
public class TaskLogDao {

    @Resource
    public BaseDao baseDao;

    //�ֲ߳̾����������ڴ�ŵ�ǰ�û���Ϣ������
    private static final ThreadLocal<BaseLocal> threadLocal = new ThreadLocal<BaseLocal>();

    /**
     * �õ���ǰ�̣߳����أ����ݶ���
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
     * �����ݴ��뵱ǰ�̣߳����أ�����
     *
     * @param local ���úù���ֵ�Ķ���
     */
    public static void setThreadLocal(BaseLocal local) {
        threadLocal.set(local);
    }

    /*-------------------------------------------------��ʼ����-------------------------------------------------*/

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
        LayuiPage<TaskLogView> ret = new LayuiPage<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String hql = " from TaskLogView ";
        hql += " where 1 = 1 ";
        Map<String,Object> param = new HashMap<>();

        if(!StringUtils.isEmpty(taskLog.getSyncResult())){
            hql += " and syncResult = :syncresult";
            param.put("syncresult",taskLog.getSyncResult());
        }
        if(!StringUtils.isEmpty(taskLog.getInfTaskdetailn())){
            hql += " and infTaskdetailn like :inftaskdetailn ";
            param.put("inftaskdetailn","%%" + taskLog.getInfTaskdetailn().trim() + "%%");
        }
        if(!StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)){
            hql += " and createDate >= :starttime";
            Date start = sdf.parse(startTime);
            param.put("starttime",start);
        }else if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)){
            hql += " and createDate BETWEEN :starttime and :endtime ";
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            param.put("starttime",start);
            param.put("endtime",end);
        }

            hql += " order by createDate DESC ";

        String totalsql = " select count(*) " + hql;

        List<TaskLogView> taskLogList = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);
        ret.setData(taskLogList);
        ret.setCount(total);
        return ret;
    }

    /**
     * ɾ��������ɾ����
     * @param ids
     */
    public void taskLogDelete(Integer[] ids){
        String hql = "delete TaskLog where 1 = 1 and id in :ids";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids",ids);
        query.executeUpdate();
    }

    /**
     * ��������
     * @param taskLog
     */
    public void saveTaskLog(TaskLog taskLog){baseDao.save(taskLog);}
}
