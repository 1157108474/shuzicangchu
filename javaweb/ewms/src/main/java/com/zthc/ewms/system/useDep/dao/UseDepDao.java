package com.zthc.ewms.system.useDep.dao;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.useDep.entity.OfficesScope;
import com.zthc.ewms.system.useDep.entity.UseDep;
import com.zthc.ewms.system.useDep.entity.UseDepCondition;
import drk.util.TextUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UseDepDao {

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
     * ��ȡʹ�õ�λ�б�
     * @param useDep
     * @param condition
     * @return
     */
    @Transactional
    public LayuiPage<UseDep> listUseDep(UseDep useDep, UseDepCondition condition){
        LayuiPage<UseDep> ret = new LayuiPage<>();
        String hql_left = " select new UseDep(u.id,u.code,u.name,o.name,u.organizationType,u.ztId,o.name,o.code,u.status,u.memo)";
        String hql = "from UseDep u,Organization o ";
        hql += " where 1 = 1 and (u.deleted is null or u.deleted !=2) ";
        Map<String,Object> param = new HashMap<>();
        if(!StringUtils.isEmpty(useDep.getName())){
            hql += " and u.name like :name ";
            param.put("name","%%" + useDep.getName().trim() + "%%");
        }
        //hql += "and (u.ztId = o.id or u.organizationId = o.id) order by u.id DESC";
        hql += "and u.organizationId = o.id order by u.id DESC";
        String totalsql = " select count(u.id) "+hql;
        List<UseDep> useDepList = baseDao.findByHql(hql_left+hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);
        ret.setData(useDepList);
        ret.setCount(total);
        return ret;
    }

    /**
     * ��ȡͨ��ʹ�õ�λ�б���
     * @param useDep
     * @param condition
     * @return
     */
    public LayuiPage<UseDep> listGeneral(UseDep useDep,UseDepCondition condition){
        LayuiPage<UseDep> ret = new LayuiPage<>();

        String hql = " from UseDep ";
        hql += " where 1 = 1 and deleted != 2 ";

        Map<String,Object> param = new HashMap<>();

        if(!StringUtils.isEmpty(useDep.getId())){
            hql += " and id != :id ";
            param.put("id",useDep.getId());
        }
        if (!StringUtils.isEmpty(useDep.getZtId()) && useDep.getZtId() != 0) {
            //hql += " and ztId = :ztId ";
            hql += " and organizationId = :ztId ";
            param.put("ztId", useDep.getZtId());
        }
        if(!StringUtils.isEmpty(useDep.getName())){
            hql += " and name like :name ";
            param.put("name","%%" +useDep.getName().trim() + "%%");
        }

        hql += " and status != 2 order by id DESC ";

        String totalsql = " select count(*) " + hql;

        List<UseDep> list = baseDao.findByHql(hql,param,condition.getPageNum(),condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql,param);

        ret.setData(list);
        ret.setCount(total);

        return ret;

    }

    /**
     * ��ȡʹ�õ�λ����ͨ���б�
     *
     * @param useDep
     * @param condition
     * @return
     */
    public LayuiPage<UseDep> listDepartGeneral(UseDep useDep, UseDepCondition condition) {

        LayuiPage<UseDep> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql = " from UseDep where deleted !=2 and status !=2 and organizationType = 2 ";

        if (!StringUtils.isEmpty(useDep.getZtId()) && useDep.getZtId() != 0) {
            //hql += " and ztId = :ztid";
            hql += " and organizationId = :ztid";
            param.put("ztid", useDep.getZtId());
        }
        if (!StringUtils.isEmpty(useDep.getName())) {
            hql += " and name like :name ";
            param.put("name", "%%" + useDep.getName().trim() + "%%");
        }


        String totalsql = " select count(1) " + hql;
        hql += " order by id DESC ";

        List<UseDep> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * ��ȡ�Ѱ����Ϸ����б�
     * @param useId
     * @return
     */
    public List<OfficesScope> listOfficeScope(Integer useId){
        String hql_left = " select new OfficesScope(o.id,o.scopeId,s.name,s.code) ";
        String hql  = " from OfficesScope o,Sparepartscate s where 1 = 1 and o.officesId = :officesid " +
                " and o.scopeId = s.id order by o.scopeId";
        Query query = baseDao.createQuery(hql_left+hql);
        query.setParameter("officesid",useId);
        List list = query.list();
        return list;
    }

    /**
     * ��ȡʹ�õ�λ����ͨ�÷���
     * @param ztId
     * @param ids
     * @return
     */
    public List<UseDep> listDepartDep(Integer ztId,Integer[] ids){
        //String hql = " from UseDep where 1 = 1  and deleted != 2 and status != 2 and organizationType = 2 and ztId = :ztid ";
        //�޸�Ϊ����ʹ�õ�λ����֯id����֯��id�Ƚ�
        String hql = " from UseDep where 1 = 1  and deleted != 2 and status != 2 and organizationType = 2 and organizationId = :ztid ";
        if(null != ids && ids.length !=0){
            hql += " and id not in :ids ";
        }
        Query query = baseDao.createQuery(hql);
        query.setParameter("ztid",ztId);
        if(null != ids && ids.length !=0){
            query.setParameterList("ids",ids);
        }
        return query.list();
    }
    /**
     * ��ȡ�Ѱ����Ϸ�������
     * @param useDep
     * @return
     */
    public int countOfficeScope(UseDep useDep){
        String hql = " select count(*) from OfficesScope where 1 = 1 and officesId = :officesid order by create_date DESC";
        Query query = baseDao.createQuery(hql);
        query.setParameter("officesid",useDep.getId());
        List list = query.list();
        int listSize = Integer.parseInt(list.get(0).toString());
        return listSize;
    }



    /**
     * ����
     * @param useDep
     */
    public void saveUseDep(UseDep useDep){
        baseDao.save(useDep);
    }

    /**
     * �������Ϸ�Χ
     * @param officesScope
     */
    public void saveMaterielRange(OfficesScope officesScope){
        baseDao.save(officesScope);
    }

    /**
     * �޸�
     * @param useDep
     */
    public void editUseDep(UseDep useDep){
        String hql = "update UseDep set code = :code,name = :name,organizationId = :organizationid,organizationType = :organizationtype," +
                " ztId = :ztid, status = :status, memo = :memo ,updater = :updater, updateDate = :updatedate where 1 = 1 " +
                "and id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code",useDep.getCode());
        query.setParameter("name",useDep.getName());
        query.setParameter("organizationid",useDep.getOrganizationId());
        query.setParameter("organizationtype",useDep.getOrganizationType());
        query.setParameter("ztid",useDep.getZtId());
        query.setParameter("status",useDep.getStatus());
        query.setParameter("memo",useDep.getMemo());
        query.setParameter("updater",useDep.getUpdater());
        query.setParameter("updatedate",useDep.getUpdateDate());
        query.setParameter("id",useDep.getId());
        query.executeUpdate();
    }

    /**
     * ����
     * deleted ��Ϊ2
     * @param ids
     */
    public void logicDelete(Integer[] ids){
        String hql = "update UseDep set deleted = 2 where 1 = 1 and id in :ids";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids",ids);
        query.executeUpdate();
    }

    /**
     * ʹ�õ�λ�Ƴ����Ϸ�Χ����
     * @param id
     */
    public void deleteMaterielRange(Integer id){
        String hql = " delete OfficesScope where officesId = :officesid";
        Query query = baseDao.createQuery(hql);
        query.setParameter("officesid",id);
        query.executeUpdate();
    }


    /**
     * ��ȡ��������
     * @param id
     * @return
     */
    public UseDep getOne(Integer id){
        UseDep ret = new UseDep();
        if(!TextUtil.isNotNull(id)){
            ret = new UseDep();
        }else{
            ret = baseDao.getById(UseDep.class,id);
        }
        return ret;
    }

    /**
     * �ж��Ƿ��ظ�
     * @param code
     * @param name
     * @param id
     * @return
     */
    public int checkUseDep(String code,String name,Integer id){

        String hql = " select count(1) from UseDep where (name = :name or code = :code) and deleted = 1 ";
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        param.put("name",name);
        if(!StringUtils.isEmpty(id) && 0!= id ){
            hql += " and id != :id ";
            param.put("id",id);
        }
        Query query = baseDao.createQuery(hql);
        for(Map.Entry<String,Object> entry : param.entrySet()){
            query.setParameter(entry.getKey(),entry.getValue());
        }
        List list = query.list();
        int listSize = Integer.parseInt(list.get(0).toString());
        return listSize;
    }
    /**
     * ����code��ȡ����
     */
     public UseDep getUseDepCode (String code) {
            UseDep res = null;
            String hql = " from UseDep where 1 = 1 and code = :code";
            Query query = baseDao.createQuery(hql);
            query.setParameter("code", code);
            if (null != query.list() && query.list().size() > 0) {
                res = (UseDep) query.list().get(0);
            }
            return res;
        }
     /**
      * ����ztId�������뵥λ�б�
      *
      * @param ztId
      * @return
      */
    public List<OfficesScope> listOfficesScopes (Integer scopeId,Integer ztId) {
        String hql = " from OfficesScope a inner join UseDep b on a.officesId = b.id where a.scopeType = 3 and  a.scopeId = :scopeId " +
                "and  b.ztId = :ztId and b.organizationType = 2";
        Query query = baseDao.createQuery(hql);
        query.setParameter("scopeId", scopeId);
        query.setParameter("ztId", ztId);
        return query.list();
    }
    public List<Integer> listOfficeScopeByOfficesId(Integer officesId){
        String hql = " select officesId from OfficesScope where  officesId =:officesId";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("officesId",officesId);
        return query.list();
    }
}
