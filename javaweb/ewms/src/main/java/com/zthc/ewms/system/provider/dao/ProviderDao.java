package com.zthc.ewms.system.provider.dao;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.provider.entity.Provider;
import com.zthc.ewms.system.provider.entity.ProviderCondition;
import drk.util.TextUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProviderDao {

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
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public Provider getProvider(Integer id) {
        if (TextUtil.isNotNull(id)) {
            return baseDao.getById(Provider.class, id);
        }
        return null;
    }

    /**
     * 获取供应商列表
     *
     * @param provider
     * @param condition
     * @return
     * @throws Exception
     */
    @Transactional
    public LayuiPage<Provider> listProviders(Provider provider, ProviderCondition condition) throws Exception {
        LayuiPage<Provider> ret = new LayuiPage<>();
//        String hqlLeft = "select Provider ";
        String hql = " from Provider ";
        hql += "where 1=1 and deleted != 2";

        Map<String, Object> param = new HashMap<>();

        //查询条件
        if (!StringUtils.isEmpty(provider.getName())) {
            hql += "and name like :name ";
            param.put("name", "%%" + provider.getName().trim() + "%%");
        }

        String totalsql = "select count(*) " + hql;
        //排序
        hql += " order by id DESC ";

        List<Provider> providerList = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);
        ret.setData(providerList);
        ret.setCount(total);
        return ret;
    }

    /**
     * 获取通用供应商列表方法
     *
     * @param provider
     * @param condition
     * @return
     */
    public LayuiPage<Provider> listGeneral(Provider provider, ProviderCondition condition) {
        LayuiPage<Provider> ret = new LayuiPage<>();

        String hql = " from Provider ";
        hql += " where 1 = 1 and deleted != 2 ";

        Map<String, Object> param = new HashMap<>();

        if (!StringUtils.isEmpty(provider.getId())) {
            hql += " and id != :id ";
            param.put("id", provider.getId());
        }
        if (!StringUtils.isEmpty(provider.getName())) {
            hql += " and name like :name ";
            param.put("name", "%%" + provider.getName().trim() + "%%");
        }

        hql += " and status != 2 order by id DESC ";

        String totalsql = " select count(*) " + hql;


        List<Provider> providerList = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(providerList);
        ret.setCount(total);

        return ret;
    }

    /**
     * 新增
     *
     * @param provider
     */
    public void saveProvider(Provider provider) {
        baseDao.save(provider);
    }

    /**
     * 编辑
     *
     * @param provider
     */
    public void editProvider(Provider provider) {
        String hql = "update Provider set code = :code, name= :name,address = :address,zipCode = :zipcode,contactPerson = :contactperson, " +
                "contractPhone = :contractphone,fax = :fax,email = :email,sort = :sort,memo = :memo,updater = :updater, " +
                " updateDate = :updatedate,status = :status where 1 = 1 and id = :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", provider.getCode());
        query.setParameter("name", provider.getName());
        query.setParameter("address", provider.getAddress());
        query.setParameter("zipcode", provider.getZipCode());
        query.setParameter("contactperson", provider.getContactPerson());
        query.setParameter("contractphone", provider.getContractPhone());
        query.setParameter("fax", provider.getFax());
        query.setParameter("email", provider.getEmail());
        query.setParameter("sort", provider.getSort());
        query.setParameter("memo", provider.getMemo());
        query.setParameter("updater", provider.getUpdater());
        query.setParameter("updatedate", provider.getUpdateDate());
        query.setParameter("status", provider.getStatus());
        query.setParameter("id", provider.getId());
        query.executeUpdate();
    }

    /**
     * 删除供应商
     * 逻辑删除 deleted 改为2
     *
     * @param ids
     */
    public void logicDelete(Integer[] ids) {
        String hql = "update Provider set deleted = 2 where 1 = 1 and id in :ids";
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
    public Provider getOne(Integer id) {
        Provider ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new Provider();
        } else {
            ret = baseDao.getById(Provider.class, id);
        }
        return ret;
    }

    /**
     * 判断是否重复
     *
     * @param code
     * @param name
     * @param id
     * @return
     */
    public int checkProvider(String code, String name, Integer id) {

        String hql = " select count(1) from Provider where (name = :name or code = :code) and deleted = 1 ";
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

    public Provider getProviderOneById(String name) {
        String hql = " from Provider where name = :name ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("name", name);
        List<Provider> list = query.list();
        return list.get(0);
    }
}
