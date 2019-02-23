package com.zthc.ewms.system.activitiListener.dao;

import com.hckj.base.database.hibernate.BaseDao;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplateCondition;
import org.activiti.engine.ManagementService;
import org.activiti.engine.task.Task;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ActivitiDao {

    @Resource
    public BaseDao baseDao;
    @Autowired
    private ManagementService managementService;


    public LayuiPage<Map<String,Object>> queryProcessing(FormTemplateCondition condition, Map<String, Object> params) {
        LayuiPage<Map<String,Object>> ret = new LayuiPage<>();
        int page = condition.getPageNum();
        int size = condition.getPageTotal();
        int startNo = (page- 1) * size;
        int endNo = startNo + size;
        //String sql = "SELECT T.* FROM " + managementService.getTableName(Task.class) + " T,v_jsd w WHERE T.proc_inst_id_ = w.routeId ";
        String WZ_SHEET = "(select * from WZ_SHEET union select * from WZ_SHEET_RK union select * from WZ_SHEET_CK)";
        String sql = "SELECT T.ID_ id,T.NAME_ name,T.Proc_Inst_Id_ processInstanceId,T.CREATE_TIME_ createTime,p.name assignee, " +
                "                         w.CODE code,w.NAME sheetName,p2.name submitMan,d.Name status,w.Url url " +
                "                          FROM ACT_RU_TASK T,"+WZ_SHEET+" w,BASE_PERSON p,BASE_PERSON p2,BASE_DICTIONARY d  " +
                "                          WHERE T.proc_inst_id_ = w.routeId  " +
                "                                and T.ASSIGNEE_ = p.code " +
                "                                and w.Creator = p2.id  " +
                "                                and w.STATUS = d.id ";
        String sql2 = "SELECT count(1)  FROM ACT_RU_TASK T,"+WZ_SHEET+" w,BASE_PERSON p,BASE_PERSON p2,BASE_DICTIONARY d  " +
                "                          WHERE T.proc_inst_id_ = w.routeId  " +
                "                                and T.ASSIGNEE_ = p.code " +
                "                                and w.Creator = p2.id  " +
                "                                and w.STATUS = d.id ";
        if(params.get("assignee")!=null){
            sql += " and T.assignee_ = '"+params.get("assignee")+"'";
            sql2 += " and T.assignee_ = '"+params.get("assignee")+"'";
        }
        if(params.get("status")!=null){
            sql += " and w.STATUS = '"+params.get("status")+"'";
            sql2 += " and w.STATUS = '"+params.get("status")+"'";
        }
        if(params.get("temCode")!=null){
            sql += " and upper(w.code) like upper('%" + params.get("temCode") + "%')";
            sql2 += " and  upper(w.code) like upper('%" + params.get("temCode") + "%')";
        }
        if(params.get("temName")!=null){
            sql += " and w.name like '%"+params.get("temName")+"%'";
            sql2 += " and w.name like '%"+params.get("temName")+"%'";
        }
        if(params.get("startTime")!=null){
            sql += " and T.create_time_ >= to_date('"+params.get("startTime")+"','YYYY/MM/DD hh24:mi:ss')";
            sql2 +=" and T.create_time_ >= to_date('"+params.get("startTime")+"','YYYY/MM/DD hh24:mi:ss')";
        }
        if(params.get("endTime")!=null){
            sql += " and T.create_time_ <= to_date('"+params.get("endTime")+"','YYYY/MM/DD hh24:mi:ss')";
            sql2 +=" and T.create_time_ <= to_date('"+params.get("endTime")+"','YYYY/MM/DD hh24:mi:ss')";
        }
        sql += " order by T.create_time_ desc ";
        SQLQuery query2 = baseDao.getCurrentSession().createSQLQuery(sql2);
        Object count = query2.uniqueResult();

        sql = "SELECT *  FROM  " +
                "(select a.*,RowNum rn from ("+sql+") a  where Rownum <= "+endNo+")  where rn > "+startNo;
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = null;
        try {
            list = query
                    .addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("assignee", StandardBasicTypes.STRING)
                    .addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("processInstanceId", StandardBasicTypes.STRING)
                    .addScalar("createTime", StandardBasicTypes.DATE)
                    .addScalar("code", StandardBasicTypes.STRING)
                    .addScalar("sheetName", StandardBasicTypes.STRING)
                    .addScalar("submitMan", StandardBasicTypes.STRING)
                    .addScalar("status", StandardBasicTypes.STRING)
                    .addScalar("url", StandardBasicTypes.STRING)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> lists = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Object[] obj = list.get(i);
            Map<String,Object> map1 = new HashMap<>();
            if(obj[0]!=null){ map1.put("id",obj[0]);}
            if(obj[1]!=null){ map1.put("assignee",obj[1]);}
            if(obj[2]!=null){ map1.put("name",obj[2]);}
            if(obj[3]!=null){ map1.put("processInstanceId",obj[3]);}
            if(obj[4]!=null){ map1.put("createTime",obj[4]);}
            if(obj[5]!=null){ map1.put("code",obj[5]);}
            if(obj[6]!=null){ map1.put("sheetName",obj[6]);}
            if(obj[7]!=null){ map1.put("submitMan",obj[7]);}
            if(obj[8]!=null){ map1.put("status",obj[8]);}
            if(obj[9]!=null){ map1.put("url",obj[9]);}
            lists.add(map1);
        }
        ret.setData(lists);
        ret.setCount( Long.valueOf(String.valueOf(count)).longValue());
        return ret;
    }

    public List<Map<String, Object>> queryProcessing(Map<String, Object> params) {//µ¼³ö
        String WZ_SHEET = "(select * from WZ_SHEET union select * from WZ_SHEET_RK union select * from WZ_SHEET_CK)";
        String sql = "SELECT T.ID_ id,T.NAME_ name,T.Proc_Inst_Id_ processInstanceId,T.CREATE_TIME_ createTime,p.name assignee, " +
                "                         w.CODE code,w.NAME sheetName,p2.name submitMan,d.Name status,w.Url url " +
                "                          FROM ACT_RU_TASK T,"+WZ_SHEET+" w,BASE_PERSON p,BASE_PERSON p2,BASE_DICTIONARY d  " +
                "                          WHERE T.proc_inst_id_ = w.routeId  " +
                "                                and T.ASSIGNEE_ = p.code " +
                "                                and w.Creator = p2.id  " +
                "                                and w.STATUS = d.id ";
        if(params.get("assignee")!=null){
            sql += " and T.assignee_ = '"+params.get("assignee")+"'";
        }
        if(params.get("temCode")!=null){
            sql += " and upper(w.code) like upper('%" + params.get("temCode") + "%')";
        }
        if(params.get("temName")!=null){
            sql += " and w.name like '%"+params.get("temName")+"%'";
        }
        if(params.get("startTime")!=null){
            sql += " and T.create_time_ >= to_date('"+params.get("startTime")+"','YYYY/MM/DD hh24:mi:ss')";
        }
        if(params.get("endTime")!=null){
            sql += " and T.create_time_ <= to_date('"+params.get("endTime")+"','YYYY/MM/DD hh24:mi:ss')";
        }
        sql += " order by T.create_time_ desc ";


        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = null;
        try {
            list = query
                    .addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("assignee", StandardBasicTypes.STRING)
                    .addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("processInstanceId", StandardBasicTypes.STRING)
                    .addScalar("createTime", StandardBasicTypes.DATE)
                    .addScalar("code", StandardBasicTypes.STRING)
                    .addScalar("sheetName", StandardBasicTypes.STRING)
                    .addScalar("submitMan", StandardBasicTypes.STRING)
                    .addScalar("status", StandardBasicTypes.STRING)
                    .addScalar("url", StandardBasicTypes.STRING)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> lists = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Object[] obj = list.get(i);
            Map<String,Object> map1 = new HashMap<>();
            if(obj[0]!=null){ map1.put("id",obj[0]);}
            if(obj[1]!=null){ map1.put("assignee",obj[1]);}
            if(obj[2]!=null){ map1.put("name",obj[2]);}
            if(obj[3]!=null){ map1.put("processInstanceId",obj[3]);}
            if(obj[4]!=null){ map1.put("createTime",obj[4]);}
            if(obj[5]!=null){ map1.put("code",obj[5]);}
            if(obj[6]!=null){ map1.put("sheetName",obj[6]);}
            if(obj[7]!=null){ map1.put("submitMan",obj[7]);}
            if(obj[8]!=null){ map1.put("status",obj[8]);}
            if(obj[9]!=null){ map1.put("url",obj[9]);}
            lists.add(map1);
        }
        return lists;
    }


    public LayuiPage<Map<String,Object>> queryProcessed(FormTemplateCondition condition, Map<String, Object> params) {
        LayuiPage<Map<String,Object>> ret = new LayuiPage<>();
        int page = condition.getPageNum();
        int size = condition.getPageTotal();
        int startNo = (page- 1) * size;
        int endNo = startNo + size;
        String WZ_SHEET = "(select * from WZ_SHEET union select * from WZ_SHEET_RK union select * from WZ_SHEET_CK)";
        String sql = "SELECT T.ID_ id,T.ACT_NAME_ name,T.PROC_INST_ID_ processInstanceId,T.END_TIME_ endTime," +
                "p.name assignee, w.CODE code,w.NAME sheetName,p2.name submitMan,d.Name status,w.Url url " +
                "                 FROM act_hi_actinst T,"+WZ_SHEET+" w,BASE_PERSON p,BASE_PERSON p2,BASE_DICTIONARY d   " +
                "                                          WHERE T.proc_inst_id_ = w.routeId   " +
                "                                                and T.ASSIGNEE_ = p.code  " +
                "                                                and w.Creator = p2.id   " +
                "                                             and w.STATUS = d.id " +
                "                                             and T.END_TIME_ is not null";
        String sql2 = "SELECT count(1) FROM  act_hi_actinst T,v_jsd w WHERE T.proc_inst_id_ = w.routeId and T.END_TIME_ is not null";
        if(params.get("assignee")!=null){
            sql += " and T.assignee_ = '"+params.get("assignee")+"'";
            sql2 += " and T.assignee_ = '"+params.get("assignee")+"'";
        }
        if(params.get("temCode")!=null){
            sql += " and upper(w.code) like upper('%" + params.get("temCode") + "%')";
            sql2 += " and upper(w.code) like upper('%" + params.get("temCode") + "%')";
        }
        if(params.get("temName")!=null){
            sql += " and w.name like '%"+params.get("temName")+"%'";
            sql2 += " and w.name like '%"+params.get("temName")+"%'";
        }
        if(params.get("startTime")!=null){
            sql += " and T.end_time_ >= to_date('"+params.get("startTime")+"','YYYY/MM/DD hh24:mi:ss')";
            sql2 +=" and T.end_time_ >= to_date('"+params.get("startTime")+"','YYYY/MM/DD hh24:mi:ss')";
        }
        if(params.get("endTime")!=null){
            sql += " and T.end_time_ <= to_date('"+params.get("endTime")+"','YYYY/MM/DD hh24:mi:ss')";
            sql2 +=" and T.end_time_ <= to_date('"+params.get("endTime")+"','YYYY/MM/DD hh24:mi:ss')";
        }
        sql += " order by T.end_time_ desc ";
        SQLQuery query2 = baseDao.getCurrentSession().createSQLQuery(sql2);
        Object count = query2.uniqueResult();

        sql = "SELECT *  FROM  " +
                "(select a.*,RowNum rn from ("+sql+") a  where Rownum <= "+endNo+")  where rn > "+startNo;
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = null;
        try {
            list = query
                    .addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("assignee", StandardBasicTypes.STRING)
                    .addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("processInstanceId", StandardBasicTypes.STRING)
                    .addScalar("endTime", StandardBasicTypes.DATE)
                    .addScalar("code", StandardBasicTypes.STRING)
                    .addScalar("sheetName", StandardBasicTypes.STRING)
                    .addScalar("submitMan", StandardBasicTypes.STRING)
                    .addScalar("status", StandardBasicTypes.STRING)
                    .addScalar("url", StandardBasicTypes.STRING)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> lists = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Object[] obj = list.get(i);
            Map<String,Object> map1 = new HashMap<>();
            if(obj[0]!=null){ map1.put("id",obj[0]);}
            if(obj[1]!=null){ map1.put("assignee",obj[1]);}
            if(obj[2]!=null){ map1.put("name",obj[2]);}
            if(obj[3]!=null){ map1.put("processInstanceId",obj[3]);}
            if(obj[4]!=null){ map1.put("endTime",obj[4]);}
            if(obj[5]!=null){ map1.put("code",obj[5]);}
            if(obj[6]!=null){ map1.put("sheetName",obj[6]);}
            if(obj[7]!=null){ map1.put("submitMan",obj[7]);}
            if(obj[8]!=null){ map1.put("status",obj[8]);}
            if(obj[9]!=null){ map1.put("url",obj[9]);}
            lists.add(map1);
        }
        ret.setData(lists);
        ret.setCount( Long.valueOf(String.valueOf(count)).longValue());
        return ret;
    }
}
