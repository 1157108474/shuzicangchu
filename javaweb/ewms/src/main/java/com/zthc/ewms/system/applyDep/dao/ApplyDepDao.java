package com.zthc.ewms.system.applyDep.dao;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.applyDep.entity.ApplyDep;
import com.zthc.ewms.system.applyDep.entity.ApplyDepCondition;
import drk.util.TextUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ApplyDepDao {

    @Resource
    public BaseDao baseDao;

    //线程局部变量，用于存放当前用户信息等数据
    private static final ThreadLocal<BaseLocal> threadLocal = new ThreadLocal<BaseLocal>();

    /**
     * 得到当前线程（本地）数据对象
     *
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
     * 获取申请单位列表
     *
     * @param applyDep
     * @param condition
     * @return
     */
    public LayuiPage<ApplyDep> listApplyDep(ApplyDep applyDep, ApplyDepCondition condition) {
        LayuiPage<ApplyDep> ret = new LayuiPage<>();
        String hql_left = " select new ApplyDep(a.id,a.code,a.name,a.subjectsGroup,a.subjectsGroupDescription,a.ztId,o.code,o.name,a.status,a.demo) ";
        String hql = "from ApplyDep a,Organization o ";
        hql += " where 1 = 1 and a.deleted != 2 ";
        Map<String, Object> param = new HashMap<>();

        if (!StringUtils.isEmpty(applyDep.getName())) {
            hql += " and a.name like :name ";
            param.put("name", "%%" + applyDep.getName().trim() + "%%");
        }

        hql += "and a.ztId = o.ztId or a.ztId = o.id order by a.id DESC ";

        String totalsql = " select count(a.id) " + hql;

        List<ApplyDep> applyDepList = baseDao.findByHql(hql_left + hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(applyDepList);
        ret.setCount(total);

        return ret;
    }

    /**
     * 获取通用申请单位列表方法
     *
     * @param applyDep
     * @param condition
     * @return
     */
    public LayuiPage<ApplyDep> listGeneral(ApplyDep applyDep, ApplyDepCondition condition) {
        LayuiPage<ApplyDep> ret = new LayuiPage<>();

        String hql = " from ApplyDep ";
        hql += " where 1 = 1 and deleted != 2 ";

        Map<String, Object> param = new HashMap<>();

        if (!StringUtils.isEmpty(applyDep.getId())) {
            hql += " and id != :id ";
            param.put("id", applyDep.getId());
        }
        if (!StringUtils.isEmpty(applyDep.getName())) {
            hql += " and name like :name ";
            param.put("name", "%%" + applyDep.getName().trim() + "%%");
        }

        hql += " and status != 2 order by id DESC ";

        String totalsql = " select count(*) " + hql;

        List<ApplyDep> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;

    }

    /**
     * 根据ztId返回申请单位列表
     *
     * @param ztId
     * @return
     */
    public List<ApplyDep> listApplyDepZt(Integer ztId) {
//        String hql = " from ApplyDep where 1 = 1 and ztId = :ztid and status = 1 and deleted != 2 order by id DESC";
        String hql = " from ApplyDep where 1 = 1  and status = 1 and deleted != 2 order by id DESC";
        Query query = baseDao.createQuery(hql);
//        query.setParameter("ztid", ztId);
        return query.list();
    }

    /**
     * 根据code获取信息
     *
     * @param code
     * @return
     */
    public ApplyDep getApplyDepCode(String code) {
        ApplyDep res = null;
        String hql = " from ApplyDep where 1 = 1 and code = :code";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", code);
        if (null != query.list() && query.list().size() > 0) {
            res = (ApplyDep) query.list().get(0);
        }
        return res;
    }

    /**
     * 新增
     *
     * @param applyDep
     */
    public void saveApplyDep(ApplyDep applyDep) {
        baseDao.save(applyDep);
    }

    /**
     * 修改
     *
     * @param applyDep
     */
    public void editApplyDep(ApplyDep applyDep) {
        String hql = "update ApplyDep set code = :code,name = :name,demo = :demo,ztId = :ztid,subjectsGroup = :subjectsGroup, " +
                " subjectsGroupDescription = :subjectsgroupdescription,status = :status where 1 = 1 and id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", applyDep.getCode());
        query.setParameter("name", applyDep.getName());
        query.setParameter("demo", applyDep.getDemo());
        query.setParameter("ztid", applyDep.getZtId());
        query.setParameter("subjectsGroup", applyDep.getSubjectsGroup());
        query.setParameter("subjectsgroupdescription", applyDep.getSubjectsGroupDescription());
        query.setParameter("status", applyDep.getStatus());
        query.setParameter("id", applyDep.getId());
        query.executeUpdate();
    }

    /**
     * 删除申请单位
     * 逻辑删除 deleted 改为2
     *
     * @param ids
     */
    public void logicDelete(Integer[] ids) {
        String hql = "update ApplyDep set deleted = 2 where 1 = 1 and id in :ids ";
        Query query = baseDao.createQuery(hql);
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public ApplyDep getOne(Integer id) {
        if (!TextUtil.isNotNull(id)) {
            return new ApplyDep();
        } else {
            return baseDao.getById(ApplyDep.class, id);
        }
    }

    /**
     * 判断是否重复
     *
     * @param code
     * @param name
     * @param id
     * @return
     */
    public int checkApplyDep(String code, String name, Integer id) {

        String hql = " select count(1) from ApplyDep where (name = :name or code = :code) and deleted = 1 ";
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        param.put("name", name);
        if (!StringUtils.isEmpty(id) && 0 != id) {
            hql += " and id != :id ";
            param.put("id", id);
        }
        Query query = baseDao.createQuery(hql);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List list = query.list();
        int listSize = Integer.parseInt(list.get(0).toString());
        return listSize;
    }
}
