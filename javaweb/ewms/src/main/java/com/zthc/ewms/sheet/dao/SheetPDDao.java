package com.zthc.ewms.sheet.dao;

import com.hckj.base.database.hibernate.BaseDao;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.Condition;
import com.zthc.ewms.sheet.entity.pd.KcpdjsDetail;
import com.zthc.ewms.sheet.entity.pd.KcpdxhDetail;
import com.zthc.ewms.sheet.entity.pd.WzpcManage;
import com.zthc.ewms.sheet.entity.pd.WzpdDetail;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class SheetPDDao{

    @Resource
    public BaseDao baseDao;

    public LayuiPage<KcpdjsDetail> getjspdStockDetailList(Condition condition,Map params) {
        LayuiPage<KcpdjsDetail> ret = new LayuiPage<>();
        int page = condition.getPageNum();
        int size = condition.getPageTotal();
        int startNo = (page- 1) * size;
        int endNo = startNo + size;
        String sql = "select t.* from V_KCPDJSLIST t where 1=1 ";
        String sql2 = "select count(1) from V_KCPDJSLIST  where 1=1 ";

        if(params.get("materialCode")!=null){
            sql += " and materialCode="+params.get("materialCode");
            sql2 += " and materialCode="+params.get("materialCode");
        }
        if(params.get("description")!=null){
            sql += " and description like '%"+params.get("description")+"%'";
            sql2 += " and description like '%"+params.get("description")+"%'";
        }
        if(params.get("storeID")!=null){
            sql += " and storeID="+params.get("storeID");
            sql2 += " and storeID="+params.get("storeID");
        }
        if(params.get("providerdepid")!=null){
            sql += " and providerdepid="+params.get("providerdepid");
            sql2 += " and providerdepid="+params.get("providerdepid");
        }
        SQLQuery query2 = baseDao.getCurrentSession().createSQLQuery(sql2);
        Object count = query2.uniqueResult();
        sql = "SELECT *  FROM  " +
                "(select a.*,RowNum rn from ("+sql+") a  where Rownum <= "+endNo+")  where rn > "+startNo;
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        //query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Object[]> list = null;
        try {
            list = query
                    .addScalar("materialcode", StandardBasicTypes.STRING)
                    .addScalar("description", StandardBasicTypes.STRING)
                    .addScalar("detailunitname", StandardBasicTypes.STRING)
                    .addScalar("storelocationname", StandardBasicTypes.STRING)
                    .addScalar("storelocationcode", StandardBasicTypes.STRING)
                    .addScalar("housename", StandardBasicTypes.STRING)
                    .addScalar("providername", StandardBasicTypes.STRING)
                    .addScalar("storecount", StandardBasicTypes.DOUBLE)
                    .addScalar("storeid", StandardBasicTypes.INTEGER)
                    .addScalar("storelocationid", StandardBasicTypes.INTEGER)
                    .addScalar("ztid", StandardBasicTypes.INTEGER)
                    .addScalar("enablesn", StandardBasicTypes.INTEGER)
                    .addScalar("ypcount", StandardBasicTypes.INTEGER)
                    .addScalar("providerdepid", StandardBasicTypes.INTEGER)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<KcpdjsDetail> kcpdjsDetails = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Object[] map = list.get(i);
            KcpdjsDetail kcpdjsDetail = new KcpdjsDetail();
            if(map[0]!=null){ kcpdjsDetail.setMaterialcode((String) map[0]);}
            if(map[1]!=null){ kcpdjsDetail.setDescription((String) map[1]);}
            if(map[2]!=null){ kcpdjsDetail.setDetailunitname((String) map[2]);}
            if(map[3]!=null){ kcpdjsDetail.setStorelocationname((String) map[3]);}
            if(map[4]!=null){ kcpdjsDetail.setStorelocationcode((String) map[4]);}
            if(map[5]!=null){ kcpdjsDetail.setHousename((String) map[5]);}
            if(map[6]!=null){ kcpdjsDetail.setProvidername((String) map[6]);}
            if(map[7]!=null){ kcpdjsDetail.setStorecount((Double) map[7]);}
            if(map[8]!=null){ kcpdjsDetail.setStoreid((Integer)map[8]);}
            if(map[9]!=null){ kcpdjsDetail.setStorelocationid((Integer)map[9]);}
            if(map[10]!=null){ kcpdjsDetail.setZtid((Integer)map[10]);}
            if(map[11]!=null){ kcpdjsDetail.setEnablesn((Integer)map[11]);}
            if(map[12]!=null){kcpdjsDetail.setYpcount((Integer)map[12]);}
            if(map[13]!=null){kcpdjsDetail.setProviderdepid((Integer)map[13]);}
            kcpdjsDetails.add(kcpdjsDetail);
        }
       ret.setData(kcpdjsDetails);
       ret.setCount( Long.valueOf(String.valueOf(count)).longValue());
       return ret;
    }

    public LayuiPage<KcpdxhDetail> getxhpdStockDetailList(Condition condition, Map<String, Object> params) {
        LayuiPage<KcpdxhDetail> ret = new LayuiPage<>();
        int page = condition.getPageNum();
        int size = condition.getPageTotal();
        int startNo = (page- 1) * size;
        int endNo = startNo + size;
        String sql = "select t.* from V_KCXHPDLIST t where 1=1 ";
        String sql2 = "select count(1) from V_KCXHPDLIST where 1=1 ";

        if(params.get("materialCode")!=null){
            sql += " and materialCode="+params.get("materialCode");
            sql2 += " and materialCode="+params.get("materialCode");
        }
        if(params.get("description")!=null){
            sql += " and description like %"+params.get("description")+"%";
            sql2 += " and description like %"+params.get("description")+"%";
        }
        if(params.get("storeID")!=null){
            sql += " and storeID="+params.get("storeID");
            sql2 += " and storeID="+params.get("storeID");
        }
        if(params.get("providerdepid")!=null){
            sql += " and providerdepid="+params.get("providerdepid");
            sql2 += " and providerdepid="+params.get("providerdepid");
        }
        if (params.get("code1")!=null){
            sql += " and code>="+params.get("code1");
            sql2 += " and code>="+params.get("code1");
        }
        if (params.get("code2")!=null){
            sql += " and code<="+params.get("code2");
            sql2 += " and code<="+params.get("code2");
        }
        if (params.get("storeLocationName1")!=null){
            sql += " and storelocationname>= '" + params.get("storeLocationName1") + "'";
            sql2 += " and storelocationname>= '" + params.get("storeLocationName1") + "'";
        }
        if (params.get("storeLocationName2")!=null){
            sql += " and storelocationname<= '" + params.get("storeLocationName2") + "'";
            sql2 += " and storelocationname<= '" + params.get("storeLocationName2") + "'";
        }
        SQLQuery query2 = baseDao.getCurrentSession().createSQLQuery(sql2);
        Object count = query2.uniqueResult();
        sql = "SELECT * FROM  " +
                "(select a.*,RowNum rn from ("+sql+") a  where Rownum <= "+endNo+") where rn > "+startNo;
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = null;
        try {
            list = query
                    .addScalar("materialcode", StandardBasicTypes.STRING)
                    .addScalar("description", StandardBasicTypes.STRING)
                    .addScalar("detailunitname", StandardBasicTypes.STRING)
                    .addScalar("storelocationname", StandardBasicTypes.STRING)
                    .addScalar("storelocationcode", StandardBasicTypes.STRING)
                    .addScalar("housename", StandardBasicTypes.STRING)
                    .addScalar("providername", StandardBasicTypes.STRING)
                    .addScalar("storecount", StandardBasicTypes.DOUBLE)
                    .addScalar("storeid", StandardBasicTypes.INTEGER)
                    .addScalar("storelocationid", StandardBasicTypes.INTEGER)
                    .addScalar("ztid", StandardBasicTypes.INTEGER)
                    .addScalar("enablesn", StandardBasicTypes.INTEGER)
                    .addScalar("ypcount", StandardBasicTypes.INTEGER)
                    .addScalar("providerdepid", StandardBasicTypes.INTEGER)
                    .addScalar("code", StandardBasicTypes.STRING)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<KcpdxhDetail> kcpdxhDetails = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Object[] map = list.get(i);
            KcpdxhDetail kcpdxhDetail = new KcpdxhDetail();
            if(map[0]!=null){ kcpdxhDetail.setMaterialcode((String) map[0]);}
            if(map[1]!=null){ kcpdxhDetail.setDescription((String) map[1]);}
            if(map[2]!=null){ kcpdxhDetail.setDetailunitname((String) map[2]);}
            if(map[3]!=null){ kcpdxhDetail.setStorelocationname((String) map[3]);}
            if(map[4]!=null){ kcpdxhDetail.setStorelocationcode((String) map[4]);}
            if(map[5]!=null){ kcpdxhDetail.setHousename((String) map[5]);}
            if(map[6]!=null){ kcpdxhDetail.setProvidername((String) map[6]);}
            if(map[7]!=null){ kcpdxhDetail.setStorecount((Double) map[7]);}
            if(map[8]!=null){ kcpdxhDetail.setStoreid((Integer)map[8]);}
            if(map[9]!=null){ kcpdxhDetail.setStorelocationid((Integer)map[9]);}
            if(map[10]!=null){ kcpdxhDetail.setZtid((Integer)map[10]);}
            if(map[11]!=null){ kcpdxhDetail.setEnablesn((Integer)map[11]);}
            if(map[12]!=null){kcpdxhDetail.setYpcount((Integer)map[12]);}
            if(map[13]!=null){kcpdxhDetail.setProviderdepid((Integer)map[13]);}
            if(map[14]!=null){kcpdxhDetail.setCode((String) map[14]);}
            kcpdxhDetails.add(kcpdxhDetail);
        }
        ret.setData(kcpdxhDetails);
        ret.setCount( Long.valueOf(String.valueOf(count)).longValue());
        return ret;
    }

    public void saveSheetDetail(WzpdDetail detail) {
        //进行新增保存
        baseDao.save(detail);
    }

    public void deletePDDetail(Integer id) {
        baseDao.removeById(WzpdDetail.class,id);
    }

    public  LayuiPage<WzpcManage>  manageKcpd(Condition condition, Map<String, Object> params) {
        String hql = "from WzpcManage where 1=1 ";
        if(params.get("extendInt1")!=null){
            hql += " and extendInt1= :extendInt1";
        }
        if(params.get("creator")!=null){
            hql += " and creator= :creator";
        }
        if(params.get("code")!=null){
            hql += " and code= :code";
        }
        if(params.get("beginDate")!=null){
            hql += " and createDate>= to_date(:beginDate,'yyyy-MM-dd') ";
        }
        if(params.get("endDate")!=null){
            hql += " and createDate<=to_date(:endDate,'yyyy-MM-dd') ";
        }
        if(params.get("ztId")!=null){
            hql += " and ztId= :ztId";
        }
        if(params.get("status")!=null){
            hql += " and status= :status";
        }
        String order = " order by id desc";
        List<WzpcManage> list = baseDao.findByHql(hql+order,params,condition.getPageNum(),condition.getPageTotal());
        long count = baseDao.countByHql("select count(1)  "+hql, params);
        LayuiPage<WzpcManage> ret = new LayuiPage<>();
        ret.setCount(count);
        ret.setData(list);
        return  ret;
    }
    public List<WzpcManage> manageKcpdExport(Map<String, Object> params) {
        String hql = "from WzpcManage where 1=1 ";
        if(params.get("extendInt1")!=null){
            hql += " and extendInt1= "+params.get("extendInt1");
        }
        if(params.get("creator")!=null){
            hql += " and creator= "+params.get("creator");
        }
        if(params.get("code")!=null){
            hql += " and code= "+params.get("code");
        }
        if(params.get("beginDate")!=null){
            hql += " and createDate>= to_date("+params.get("beginDate")+",'yyyy-MM-dd') ";
        }
        if(params.get("endDate")!=null){
            hql += " and createDate<=to_date("+params.get("endDate")+",'yyyy-MM-dd') ";
        }
        if(params.get("ztId")!=null){
            hql += " and ztId= "+params.get("ztId");
        }
        String order = " order by id desc";
        Query query = baseDao.createQuery(hql);
        return query.list();
    }
    public int editPdDetail(int id, double detailCount,int userId,String userName,int stockResult) {
        String hql = "update WzpdDetail set  detailCount= :detailCount, stockMan = :stockMan, extendString1 = :extendString1, stockDate = :stockDate, stockStatus = 1, stockResult = :stockResult where id=:id";//, stockStatus = 1
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("detailCount", detailCount);
        query.setParameter("stockMan", userId);
        query.setParameter("extendString1", userName);
        query.setParameter("stockDate", new Date());
        query.setParameter("stockResult", stockResult);
        return query.executeUpdate();
    }

    public WzpdDetail getDetail(int i) {
       return (WzpdDetail) baseDao.find(WzpdDetail.class,i);
    }

    public long getDetailCount(Map<String, Object> params) {
        //long count = baseDao.countByHql("select count(1) from WzpdDetail where 1=1 sheetId= sheetId:",params);
        String hql = "select count(1) from WzpdDetail where 1=1";
        if(params.get("sheetId")!=null){
            hql += " and sheetId= :sheetId";
        }
        if(params.get("stockStatus")!=null){
            hql += " and stockStatus= :stockStatus";
        }
        long count = baseDao.countByHql(hql, params);
        return count;
    }

    public Map<String,Object> getPyCount(int id) {
        String sql = "select sum(syscount) sysCountAll,sum(detailcount) detailCountAll from WZ_PDDETAIL where sheetId= "+id;
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = null;
        try {
            list = query
                    .addScalar("sysCountAll", StandardBasicTypes.INTEGER)
                    .addScalar("detailCountAll", StandardBasicTypes.INTEGER)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<>();
        if(list.size()>0){
            Object[] obj = list.get(0);
            if(obj[0]!=null){ map.put("sysCountAll",obj[0]);}
            if(obj[1]!=null){ map.put("detailCountAll",obj[1]);}
        }
        return map;
    }

    public int delDetailsBySheetId(Integer id) {
        String hql = "delete WzpdDetail where sheetId = :id";
        Query query = baseDao.createQuery(hql);
        query.setParameter("id", id);
        return query.executeUpdate();
    }


    public List<WzpdDetail> sheetDetailsNoPage(String sheetId) {
        String hql = "from WzpdDetail w  where sheetId =  "+sheetId;
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    public WzpcManage getWzpcManageOne(String id) {
        return (WzpcManage) baseDao.find(WzpcManage.class,Integer.parseInt(id));
    }

    public WzpdDetail getWzpdDetail(WzpdDetail wzpdDetail) {
        String hql = "from WzpdDetail where materialCode =:materialCode and storeLocationName =:storeLocationName " +
                "and sysCount =:sysCount";
        Query query = baseDao.createQuery(hql);
        query.setParameter("materialCode",wzpdDetail.getMaterialCode());
        query.setParameter("storeLocationName",wzpdDetail.getStoreLocationName());
        query.setParameter("sysCount",wzpdDetail.getSysCount());
        List<WzpdDetail> list = query.list();
        if (!list.isEmpty()&&list.size()>0){
            return list.get(0);
        }
        return null;
    }


}