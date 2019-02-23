package com.zthc.ewms.system.formTemplateManage.dao;

import com.hckj.base.database.hibernate.BaseDao;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplateCondition;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FormTemplateDao {

    @Resource
    public BaseDao baseDao;

    public List<FormTemplate> getFromTemBydicID(String dicID) {
        String hql = "from FormTemplate f  where f.formTemDic = '" + dicID + "' order by ext1";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }
    public List<FormTemplate> getFromTemByMenuStatus(String menucode,int status) {
        String hql = "from FormTemplate f  where f.formTemMenu = '" + menucode + "' and f.formTemSta = "+ status +"  order by ext1";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }
    public List<FormTemplate> getFromTemByMenuStatusNothis(int id,String menucode,int status) {
        String hql = "from FormTemplate f  where f.formTemMenu = '" + menucode + "' and f.formTemSta = "+ status+" and f.id != "+id+" order by ext1";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    public LayuiPage<FormTemplate> getFromTemplateList(String dicID) {
        LayuiPage<FormTemplate> ret = new LayuiPage<>();
        String hql = "from FormTemplate where 1=1 ";
        Map<String, Object> param = new HashMap<>();
        if(dicID == null || dicID.isEmpty()){
            hql += " order by id";
        }else{
            hql += " and formTemDic = " + dicID;
            hql += " order by ext1";
        }
        FormTemplateCondition condition = new FormTemplateCondition();
        List<FormTemplate> formTemplates = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        String totalsql = "select count(*) " + hql;
        Long total = baseDao.countByHql(totalsql, param);
        ret.setCount(total);
        ret.setData(formTemplates);
        return ret;
    }

    public void save(FormTemplate obj) {
       baseDao.save(obj);
    }

    public FormTemplate getFromTemByID(int id) {
        return baseDao.getById(FormTemplate.class,id);
    }

    public void edit(FormTemplate obj) {
        baseDao.update(obj);
    }

    public int deleteById(Integer[] ids) {
        String hql = "delete FormTemplate where id in :ids";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids", ids);
        return query.executeUpdate();
    }

    public List<FormTemplate> getFromTemByMenuCode(String menuCode) {
        String hql = "from FormTemplate f  where f.formTemMenu = '" + menuCode + "' order by ext1";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    public List<FormTemplate> getFromTemByCardStatusNothis(int id, String card, int status) {
        String hql = "from FormTemplate f  where f.formTemCard = '" + card + "' and f.formTemSta = "+ status+" and f.id != "+id+" order by ext1";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    public List<FormTemplate> getFromTemByCardStatus(String formTemCard, int i) {
        String hql = "from FormTemplate f  where f.formTemCard = '" + formTemCard + "' order by ext1";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }
}
