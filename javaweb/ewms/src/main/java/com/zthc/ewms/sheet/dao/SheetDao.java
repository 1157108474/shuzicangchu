package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.SheetDaoGuard;
import com.zthc.ewms.sheet.entity.apply.ApplyPrint;
import com.zthc.ewms.sheet.entity.apply.ManageApply;
import com.zthc.ewms.sheet.entity.ck.ManageCK;
import com.zthc.ewms.sheet.entity.db.Db;
import com.zthc.ewms.sheet.entity.db.Dbd;
import com.zthc.ewms.sheet.entity.enums.RenewalCostEnum;
import com.zthc.ewms.sheet.entity.file.AttachFile;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.order.Order;
import com.zthc.ewms.sheet.entity.order.OrderDetails;
import com.zthc.ewms.sheet.entity.order.OrderPrint;
import com.zthc.ewms.sheet.entity.pd.WzpcManage;
import com.zthc.ewms.sheet.entity.rk.*;
import com.zthc.ewms.sheet.entity.th.Th;
import com.zthc.ewms.sheet.entity.th.ThPrint;
import com.zthc.ewms.sheet.entity.tk.TK;
import com.zthc.ewms.sheet.entity.tk.TKPrint;
import com.zthc.ewms.sheet.entity.ykyw.Ykyw;
import com.zthc.ewms.sheet.entity.ykyw.YkywDetail;
import com.zthc.ewms.sheet.entity.zc.ZC;
import com.zthc.ewms.sheet.entity.zr.SheetZRD;
import com.zthc.ewms.sheet.entity.zr.ZrDetails;
import com.zthc.ewms.system.user.dao.UserScopeDao;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import drk.util.TextUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SheetDao extends SheetDaoGuard {
    @Resource(name = "userScopeDao")
    public UserScopeDao userScopeDao;


    public <T> LayuiPage<T> sheetList(Sheet obj, SheetCondition condition, String className, Integer currentUserId, Date begin, Date end, boolean appFlag) {
        LayuiPage<T> ret = new LayuiPage<>();
        String hql_count = "select count(id) ";
        String append = " from  " + className + " where 1=1 ";//kindId = :kindId ";
        Map<String, Object> param = new HashMap<String, Object>();
        List<Integer> userScopeZTID = null;

        if (appFlag) {
            obj.setStatus(39);
            if("ManageCK".equals(className)){
                append += " and slCode IS NOT NULL";
            }
        } else {
            userScopeZTID = this.userScopeDao.listUserScopeZTID(currentUserId, UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        }
        if (userScopeZTID == null || userScopeZTID.size() == 0) {

            append += " and creator =  :creator";
            param.put("creator", currentUserId);
        } else {
            String ztids = "";
            int i = 0;
            for (Integer ztid : userScopeZTID) {
                if (i == 0) {
                    append += " and  ( ztId =  :ztid" + i;

                } else {
                    append += " or ztId =  :ztid" + i;
                }
                param.put("ztid" + i++, ztid);
            }
            append += " ) ";

        }

        if (obj.getCreator() != null) {
            append += " and creator =  :creator";
            param.put("creator", obj.getCreator());
        }
        if (obj.getStatus() != null) {
            append += " and status =  :status";
            param.put("status", obj.getStatus());
        }
        if (obj.getZtId() != null) {
            append += " and ztId =  :ztId";
            param.put("ztId", obj.getZtId());
        }
        if (!StringUtils.isEmpty(obj.getCode())) {
            append += " and upper(code) like  :code";
            param.put("code", "%" + obj.getCode().toUpperCase() + "%");
        }
        if (begin != null) {
            append += " and createDate >= :begin";
            param.put("begin", begin);
        }
        if (end != null) {
            append += " and createDate <= :end";
            param.put("end", end);
        }
        if (obj.getExtendInt1() != null) {
            append += " and extendInt1 =  :extendInt1";
            param.put("extendInt1", obj.getExtendInt1());
        }

        if (obj.getExtendInt2() != null) {
            append += " and extendInt2 =  :extendInt2";
            param.put("extendInt2", obj.getExtendInt2());
        }
        if(!StringUtils.isEmpty(obj.getExtendString1())){
            append += " and extendString1 =  :extendString1";
            param.put("extendString1", obj.getExtendString1());
        }
        if(!StringUtils.isEmpty(obj.getExtendString5())){
            append += " and extendString5 =  :extendString5";
            param.put("extendString5", obj.getExtendString5());
        }
        if(!StringUtils.isEmpty(obj.getExtendString5())){
            append += " and extendString7 =  :extendString7";
            param.put("extendString7", obj.getExtendString7());
        }

        if (!StringUtils.isEmpty(obj.getProviderDepId())) {
            append += " and providerDepId =  :providerDepId";
            param.put("providerDepId", obj.getProviderDepId());
        }

        if (!StringUtils.isEmpty(obj.getExtendString5())) {
            append += " and extendString5 like  :extendString5";
            param.put("extendString5", "%" + obj.getExtendString5() + "%");
        }

        if (!StringUtils.isEmpty(obj.getExtendString7())) {
            append += " and extendString7 like  :extendString7";
            param.put("extendString7", "%" + obj.getExtendString7()+ "%");
        }

//        // 排序
        String order = " order by  id desc";
//
        List<T> list = baseDao.findByHql(append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(list);
        return ret;

    }

//    public Sheet getSheetOne(Integer id) {
//        String hql = "select new Sheet(s.code,s.departId,s.memo,s.creator,s.createDate,s.ztId," +
//                "s.extendInt1,s.extendInt2,s.extendInt3,s.extendInt4,s.extendInt5," +
//                "s.extendInt6,s.extendInt7,s.extendInt8,s.extendFloat1,s.extendFloat2," +
//                "s.extendFloat3,s.extendFloat4,s.extendFloat5,s.extendFloat6,s.extendFloat7," +
//                "s.extendFloat8,s.extendString1,s.extendString2,s.extendString3," +
//                "s.extendString4,s.extendString5,s.extendString6,s.extendString7," +
//                "s.extendString8,s.extendString9,s.extendString10,s.orderNum," +
//                "s.receiveNum,s.usedDepartId,s.storeManId,s.usedManId," +
//                "s.providerDepId,s.ownerDep,s.officesId,s.fundsSource," +
//                "s.applyDepartId,o.name ,p.name ) " +
//                " from Sheet s , User p ,Organization o  where s.id = :id and s.creator = p.id  and o.id = s.departId";
//        Query query = this.baseDao.createQuery(hql);
//        query.setParameter("id", id);
//        List<Sheet> list = query.list();
//        if (list == null || list.size() == 0) {
//            return null;
//        } else {
//            return list.get(0);
//        }
//    }
    /**
     * 获取单据附件列表
     * @param sheetId
     * @param condition
     * @return
     */
    public LayuiPage<AttachFile> sheetFileList(Integer sheetId, SheetCondition condition) {

        LayuiPage<AttachFile> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql_left = " select new AttachFile(a.id,a.fileName,a.fileAliasName,a.fileExt,a.fileType,a.filePath,a" +
                ".memo,a.status," +
                " u.name,a.createDate,a.attachRelateId) ";
        String hql = " from AttachFile a, User u where a.attachRelateId = :attachRelateId and a.creator = u.id and a" +
                ".status = 1 order " +
                "by a.createDate DESC ";
        param.put("attachRelateId", sheetId);

        String totalsql = " select count(*) " + hql;

        List<AttachFile> list = baseDao.findByHql(hql_left + hql, param, condition.getPageNum(), condition
                .getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    public int updateSheet(Integer id, String memo, String extendString1, Integer userId) {
        String hql = "update Sheet set  memo= :memo, updater = :updater, updateDate= :updateDate ";
        if (!StringUtils.isEmpty(extendString1 )) {
            hql += " ,extendString1 = :extendString1  ";
        }

        hql += " where id=:id ";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("memo", memo);
        query.setParameter("updater", userId);
        query.setParameter("updateDate", new Date());
        if (!StringUtils.isEmpty(extendString1 )) {
            query.setParameter("extendString1", extendString1);
        }
        return query.executeUpdate();
    }

    public int updateSheetStauts(int processInstanceId, int status) {//修改单据状态为审核中
        String hql = "update Sheet set  status= :status where routeId=:routeId";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", status);
        query.setParameter("routeId", processInstanceId);

        return query.executeUpdate();// + query_rk.executeUpdate() + query_ck.executeUpdate();
    }
    public int updateSheetCKStauts(int processInstanceId, int status) {//修改单据状态为审核中
        String hql = "update SheetCK set  status= :status where routeId=:routeId";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", status);
        query.setParameter("routeId", processInstanceId);
        return query.executeUpdate();
    }
    public int updateSheetRKStauts(int processInstanceId, int status) {//修改单据状态为审核中
       String hql = "update SheetRK set  status= :status where routeId=:routeId";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", status);
        query.setParameter("routeId", processInstanceId);
        return query.executeUpdate();// + query_rk.executeUpdate() + query_ck.executeUpdate();
    }
    public int updateTHSheetStauts(int sheetId, int status,String extendString7) {//修改单据状态为审核中
        String hql = "update Sheet set  status= :status ,extendString7 = :extendString7 where id=:sheetId";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", status);
        query.setParameter("extendString7", extendString7);
        query.setParameter("sheetId", sheetId);
        return query.executeUpdate() ;
    }

    public String getCode(String prefix, Float flag) throws SQLException {

//        //1.获得session对象
//
//        Session session= this.baseDao.getCurrentSession();
//
//
//        //2.设置查询过程字符串
//        String procName="{Call proc_GetMaxNum(?,?)}";
//        //3.创建本地查询对象传入过程查询字符串
//        SQLQuery sqlquery=session.createSQLQuery(procName);
//
//        sqlquery.setFloat(0, 0F);
//        sqlquery.setString(1, prefix);
////        sqlquery.setString(2, "");
//        sqlquery.addEntity("maxCode");
//
////        sqlquery.setResultSetMapping("maxCode");
////        sqlquery.
//        //5.执行过程返回结果集合回结果集合
//
//        List list =sqlquery.list();
        // return (String)list.get(0);

        SessionFactory sessionFactory = this.baseDao.sessionFactory;
        Connection connection = null;

        try {
            connection = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
            CallableStatement statement = connection.prepareCall(
                    "{call proc_GetMaxNum(?,?,?)}");
            statement.setFloat(1, flag);
            statement.setString(2, prefix);

            statement.registerOutParameter(3, Types.NVARCHAR);
            statement.execute();
            statement.getResultSet();
            return statement.getString(3);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.close();
            }

        }

    }


    public List<Sheet> sheetListByProId(int proId) {
        String hql = "from Sheet s  where s.routeId = " + proId;
        Query query = baseDao.createQuery(hql);
        return query.list();
    }

    public Ykyw getYkywSheetOne(Integer id) {
        Ykyw ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new Ykyw();
        } else {
            ret = baseDao.getById(Ykyw.class, id);
        }
        return ret;
    }

    public ManageApply getApplySheetOne(Integer id) {
        ManageApply ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new ManageApply();
        } else {
            ret = baseDao.getById(ManageApply.class, id);
        }
        return ret;
    }
    public WzpcManage getWzpcSheetOne(Integer id) {
        WzpcManage ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new WzpcManage();
        } else {
            ret = baseDao.getById(WzpcManage.class, id);
        }
        return ret;
    }

    public SheetCKDETAIL getCkDetailOne(Integer id) {
        SheetCKDETAIL ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetCKDETAIL();
        } else {
            ret = baseDao.getById(SheetCKDETAIL.class, id);
        }
        return ret;
    }

    public SheetStock getStockOne(Integer id) {
        SheetStock ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetStock();
        } else {
            ret = baseDao.getById(SheetStock.class, id);
        }
        return ret;
    }

    public SheetRKD getRkSheetOne(Integer id) {
        SheetRKD ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetRKD();
        } else {
            ret = baseDao.getById(SheetRKD.class, id);
        }
        return ret;
    }

    public SheetZRD getZrSheetOne(Integer id) {
        SheetZRD ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetZRD();
        } else {
            ret = baseDao.getById(SheetZRD.class, id);
        }
        return ret;
    }

    public DbrkPrint getDbrkPrintOne(Integer id) {
        DbrkPrint ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new DbrkPrint();
        } else {
            ret = baseDao.getById(DbrkPrint.class, id);
        }
        return ret;
    }

    public ManageCK getCkSheetOne(Integer id) {
        ManageCK ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new ManageCK();
        } else {
            ret = baseDao.getById(ManageCK.class, id);
        }
        return ret;
    }
    public SheetCK getSheetCkOne(Integer id) {
        SheetCK ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetCK();
        } else {
            ret = baseDao.getById(SheetCK.class, id);
        }
        return ret;
    }
    public SheetCKD getSheetCKDOne(Integer id) {
        if (!TextUtil.isNotNull(id)) {
            return new SheetCKD();
        } else {
            return baseDao.getById(SheetCKD.class, id);
        }
    }

    public Db getDbSheetOne(Integer id) {
        Db ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new Db();
        } else {
            ret = baseDao.getById(Db.class, id);
        }
        return ret;
    }

    public Th getThSheetOne(Integer id) {
        Th ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new Th();
        } else {
            ret = baseDao.getById(Th.class, id);
        }
        return ret;
    }

    public ZC getZCSheetOne(Integer id) {
        ZC ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new ZC();
        } else {
            ret = baseDao.getById(ZC.class, id);
        }
        return ret;
    }

    public ThPrint getThPrintSheetOne(Integer id) {
        ThPrint ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new ThPrint();
        } else {
            ret = baseDao.getById(ThPrint.class, id);
        }
        return ret;
    }

    public TK getTKSheetOne(Integer id) {
        TK ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new TK();
        } else {
            ret = baseDao.getById(TK.class, id);
        }
        return ret;
    }

    public TKPrint getTKPrintSheetOne(Integer id) {
        TKPrint ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new TKPrint();
        } else {
            ret = baseDao.getById(TKPrint.class, id);
        }
        return ret;
    }
    public ApplyPrint getApplyPrintOne(Integer id) {
        if (!TextUtil.isNotNull(id)) {
            return new ApplyPrint();
        } else {
            return baseDao.getById(ApplyPrint.class, id);
        }
    }

    public OrderPrint getOrderPrintSheetOne(Integer id) {
        OrderPrint ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new OrderPrint();
        } else {
            ret = baseDao.getById(OrderPrint.class, id);
        }
        return ret;
    }

    public Order getOrderOne(String id) {
        Order ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new Order();
        } else {
            //ret = baseDao.getById(Order.class, id);
            String sql = "select * from wz_orderinfo where id="+id;
            SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
            List<Object[]> list = null;
            list = query
                    .addScalar("id",StandardBasicTypes.INTEGER)
                    .addScalar("erpid",StandardBasicTypes.STRING)
                    .addScalar("ordernum",StandardBasicTypes.STRING)
                    .addScalar("providerdepname",StandardBasicTypes.STRING)
                    .addScalar("stockorgid",StandardBasicTypes.INTEGER)
                    .addScalar("ordertype",StandardBasicTypes.STRING)
                    .addScalar("issuecode",StandardBasicTypes.INTEGER)
                    .addScalar("providerdepid",StandardBasicTypes.INTEGER)
                    .addScalar("fyid",StandardBasicTypes.INTEGER)
                    .addScalar("orderId",StandardBasicTypes.INTEGER)
                    .addScalar("issueid",StandardBasicTypes.INTEGER)
                    .addScalar("erprownum",StandardBasicTypes.STRING)
                    .addScalar("orderrowid",StandardBasicTypes.STRING)
                    .list();
            if(list.size()==1) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] map = list.get(i);
                    ret = new Order();
                    //if(map[0]!=null){ret.setOrderInfoId((Integer) map[0]);}
                    if(map[1]!=null){ ret.setId((String) map[1]);}
                    if(map[2]!=null){ ret.setOrdernum((String) map[2]);}
                    if(map[3]!=null){ ret.setProviderdepname((String) map[3]);}
                    if(map[4]!=null){ ret.setStockorgid((Integer) map[4]);}
                    if(map[5]!=null){ ret.setOrdertype((String) map[5]);}
                    if(map[6]!=null){ ret.setIssuecode((Integer) map[6]);}
                    if(map[7]!=null){ ret.setProviderdepid((Integer) map[7]);}
                    if(map[8]!=null){ ret.setFyid((Integer)map[8]);}
                    if(map[9]!=null){ ret.setOrderId((Integer)map[9]);}
                    if(map[10]!=null){ ret.setIssueid((Integer)map[10]);}
                    if(map[11]!=null){ ret.setErpRowNum((String) map[11]);}
                    if(map[12]!=null){ret.setOrderRowId((String) map[12]);}
                }
            }
        }
        return ret;
    }

    public OrderDetails getOrderInfoOne(Integer id) {
        OrderDetails orderDetails = null;
        String hql = " from OrderDetails where id = :orderid ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("orderid", id);
        List<OrderDetails> list = query.list();
        if (list.size() > 0) {
            orderDetails = list.get(0);
        }
        return orderDetails;
    }

    public List<SheetRKDETAIL> getRkDetailBySheetId(Integer id) {
        String hql = " from SheetRKDETAIL where sheetid = :sheetid ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("sheetid", id);
        List<SheetRKDETAIL> list = query.list();
        return list;
    }

    public List<SheetRkSonDetail> getSonDetailBySheetId(Integer id) {
        String hql = " from SheetRkSonDetail where  detailId in (select  id from  SheetRKDETAIL where sheetid = :id" +
                " ) ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("id", id);
        List<SheetRkSonDetail> list = query.list();
        return list;
    }

    public SheetRK getRKOne(Integer id) {
        SheetRK ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetRK();
        } else {
            ret = baseDao.getById(SheetRK.class, id);
        }
        return ret;
    }


    public SheetCG getOrderOneById(Integer id) {
        SheetCG ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetCG();
        } else {
            ret = baseDao.getById(SheetCG.class, id);
        }
        return ret;
    }


    public RkDetails getRkDetails(Integer id) {
        RkDetails ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new RkDetails();
        } else {
            ret = baseDao.getById(RkDetails.class, id);
        }
        return ret;
    }

    public RkSubDetail getRkSonDetail(Integer id) {
        RkSubDetail ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new RkSubDetail();
        } else {
            ret = baseDao.getById(RkSubDetail.class, id);
        }
        return ret;
    }

    public SheetRKDETAIL getRkDetailById(Integer id) {
        SheetRKDETAIL ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetRKDETAIL();
        } else {
            ret = baseDao.getById(SheetRKDETAIL.class, id);
        }
        return ret;
    }

    public RkDetail getRkDetail(Integer id) {
        RkDetail ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new RkDetail();
        } else {
            ret = baseDao.getById(RkDetail.class, id);
        }
        return ret;
    }

    public JsrkDetails getJsrkDetails(Integer id) {
        JsrkDetails ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new JsrkDetails();
        } else {
            ret = baseDao.getById(JsrkDetails.class, id);
        }
        return ret;
    }
    public RkSubDetail getRkSubDetail(Integer id) {
        RkSubDetail ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new RkSubDetail();
        } else {
            ret = baseDao.getById(RkSubDetail.class, id);
        }
        return ret;
    }

    public ZrDetails getZrDetails(Integer id) {
        ZrDetails ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new ZrDetails();
        } else {
            ret = baseDao.getById(ZrDetails.class, id);
        }
        return ret;
    }

    public List<SheetDetail> getDetailOne(Integer id) {
        String hql = "from SheetDetail where sheetId = :id and detailCount > 0";
        Query query = baseDao.createQuery(hql);
        query.setParameter("id", id);
        List<SheetDetail> list = query.list();
        return list;
    }

    /**
     * APP
     *
     * @param id
     * @param masterId
     * @return
     */
    public List<SheetDetail> getDetailList(Integer id, Integer masterId) {
        String hql = "from SheetDetail where sheetId = :id and detailCount > 0 and id = :materialId ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("materialId", masterId);
        List<SheetDetail> list = query.list();
        return list;
    }

    public SheetDetail getDetailOneById(Integer id) {
        SheetDetail ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetDetail();
        } else {
            ret = baseDao.getById(SheetDetail.class, id);
        }
        return ret;
    }

    public SheetRKDETAIL getSheetRKDETAILOneById(Integer id) {
        SheetRKDETAIL ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetRKDETAIL();
        } else {
            ret = baseDao.getById(SheetRKDETAIL.class, id);
        }
        return ret;
    }

    public SheetJCRK getJCRKSheetOne(Integer id) {
        SheetJCRK ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetJCRK();
        } else {
            ret = baseDao.getById(SheetJCRK.class, id);
        }
        return ret;
    }


    public SheetJCCK getJCCKSheetOne(Integer id) {
        SheetJCCK ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new SheetJCCK();
        } else {
            ret = baseDao.getById(SheetJCCK.class, id);
        }
        return ret;
    }

    /**
     * 各模块保存附件方法
     *
     * @param file
     */
    public void saveFile(AttachFile file) {
        this.baseDao.save(file);
    }

    /**
     * 附件名称重复校验
     *
     * @param name
     * @return
     */
    public int checkFile(String name) {
        String hql = " select count(1) from AttachFile where fileName = :name and status = 1 ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("name", name);
        List list = query.list();
        int listSize = Integer.parseInt(list.get(0).toString());
        return listSize;
    }

    /**
     * 附件删除方法
     *
     * @param id
     */
    public void deleteFile(Integer id) {
        String hql = "update AttachFile set status = 2 where id=:id";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public String auditSheet(Integer sheetId, String procedure, Integer userId) throws SQLException {
        SessionFactory sessionFactory = this.baseDao.sessionFactory;
        Connection connection = null;

        try {
            connection = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
            CallableStatement statement = connection.prepareCall(
                    "{call " + procedure + "(?,?,?,?)}");
            statement.setInt(1, 0);
            statement.setInt(2, sheetId);
            statement.setInt(3, userId);
            statement.registerOutParameter(4, Types.NVARCHAR);
            statement.execute();
            return statement.getString(4);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.close();
            }

        }
    }

    public LayuiPage<Dbd> listDbdSheets(Integer ztId, String type, String code, SheetCondition condition) {
        LayuiPage<Dbd> ret = new LayuiPage<>();
        String append = "from Dbd where 1=1 ";
        String hql_count = "select count(id) ";


        Map<String, Object> param = new HashMap<String, Object>();

        if ("rk".equalsIgnoreCase(type)) {
            append += " and ckCount >0 and drZtid = :ztId ";
            param.put("ztId", ztId);
        } else if ("ck".equalsIgnoreCase(type)) {
            append += " and jhCount > ckCount and dcZtid = :ztId ";
            param.put("ztId", ztId);
        }
        if (!StringUtils.isEmpty(code)) {
            append += " and code like :code ";
            param.put("code", "%" + code.trim() + "%");
        }
//        append += " and status in (:status1,:status2) ";
//        param.put("status1", 773);
//        param.put("status2", 774);


        String order = " order by code desc ";
        List<Dbd> list = baseDao.findByHql(append + order, param, condition.getPageNum(),
                condition.getPageTotal());
        Long total = baseDao.countByHql(hql_count + append, param);
        ret.setCount(total);
        ret.setData(list);
        return ret;
    }


    public Integer editPDSheet(Integer id, Integer extendInt1, String extendString1, Integer extendInt2, String memo, Integer userId) {
        String hql = "update Sheet set  memo= :memo, updater = :updater, updateDate= :updateDate,extendInt1= :extendInt1,extendString1= :extendString1,extendInt2= :extendInt2  where id=:id";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("memo", memo);
        query.setParameter("updater", userId);
        query.setParameter("updateDate", new Date());
        query.setParameter("extendInt1", extendInt1);
        query.setParameter("extendString1", extendString1);
        query.setParameter("extendInt2", extendInt2);
        return query.executeUpdate();
    }

    public  Map<String,Object> getSheetOneByPi(String processInstanceId) {//根据流程ID查询单据
        String sql ="select ID,CODE,KINDID,ROUTEID from (select * from WZ_SHEET  union select * from WZ_SHEET_RK union select * from WZ_SHEET_CK)  where ROUTEID = "+processInstanceId;
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = null;
        try {
            list = query
                    .addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("code", StandardBasicTypes.STRING)
                    .addScalar("kindId", StandardBasicTypes.INTEGER)
                    .addScalar("routeId", StandardBasicTypes.INTEGER)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<>();
        if(list.size()>0){
            Object[] obj = list.get(0);
            if(obj[0]!=null){ map.put("id",obj[0]);}
            if(obj[1]!=null){ map.put("code",obj[1]);}
            if(obj[2]!=null){ map.put("kindId",obj[2]);}
            if(obj[3]!=null){ map.put("routeId",obj[3]);}
        }
        return map;
        /*String hql = "  from Sheet where routeId = :routeId ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("routeId", Integer.parseInt(processInstanceId));
        List<Sheet> list = query.list();
        if(list.size()==1){
            return list.get(0);
        }else{
            return null;
        }*/
    }

    /* public Map<String,Object> getSheetOneAll(String taskId) {
         String sql ="select ID,CODE,KINDID,ROUTEID from (select * from WZ_SHEET  union select * from WZ_SHEET_RK
         union select * from WZ_SHEET_CK)  where ROUTEID = "+taskId;
         SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
         List<Object[]> list = null;
         try {
             list = query
                     .addScalar("id", StandardBasicTypes.INTEGER)
                     .addScalar("code", StandardBasicTypes.STRING)
                     .addScalar("kindId", StandardBasicTypes.INTEGER)
                     .addScalar("routeId", StandardBasicTypes.INTEGER)
                     .list();
         } catch (Exception e) {
             e.printStackTrace();
         }
         Map<String,Object> map = new HashMap<>();
         if(list.size()>0){
             Object[] obj = list.get(0);
             if(obj[0]!=null){ map.put("id",obj[0]);}
             if(obj[1]!=null){ map.put("code",obj[1]);}
             if(obj[2]!=null){ map.put("kindId",obj[2]);}
             if(obj[3]!=null){ map.put("routeId",obj[3]);}
         }
         return map;
     }*/
    public long getDetailCount(Object id) {

        Map<String, Object> params = new HashMap<>();
        params.put("sheetId", id);
        Long count = baseDao.countByHql("select count(1) from SheetDetail where sheetId = :sheetId", params);


        return count;
    }

    /**
     * 删除方法
     *
     * @param extendInt1
     */
    public void deleteSheet(Integer extendInt1) {
        String hql = "delete Sheet where extendInt1 = :extendInt1";
        Query query = baseDao.createQuery(hql);
        query.setParameter("extendInt1", extendInt1);
        query.executeUpdate();
    }

    public boolean checkNotExit(String code) {
        String hql = "from Sheet where code =:code ";
        Query query = this.baseDao.createQuery(hql);

        query.setParameter("code",code);

        List<Sheet> list = query.list();
        return list == null || list.size() == 0;
    }
    public boolean existsFish(String code,Integer extendInt1,Integer ztId) {
        String hql = "from Sheet where code =:code and extendInt1 = :extendInt1 and ztId = :ztId";
        Query query = this.baseDao.createQuery(hql);

        query.setParameter("code",code);
        query.setParameter("extendInt1",extendInt1);
        query.setParameter("ztId",ztId);
        List<Sheet> list = query.list();
        return list == null || list.size() == 0;
    }

    public List<SheetDetail> listSheetTKDetail(Integer sheetId) {
        String hql = " from SheetDetail where sheetId = :sheetId";
        Query query = baseDao.createQuery(hql);
        query.setParameter("sheetId", sheetId);
        return query.list();
    }
    public List<YkywDetail> listYkywDetail(Integer sheetId) {
        String hql = " from YkywDetail where sheetId = :sheetId";
        Query query = baseDao.createQuery(hql);
        query.setParameter("sheetId", sheetId);
        return query.list();
    }

    /**
     * 更新成本状态
     *
     * @param sheetId
     * @param renewalCost
     */
    public void renewalCost(Integer sheetId, Integer renewalCost) {
        String sql = "update WZ_SHEET set extendint3 = "+renewalCost+" where id = "+sheetId;
        Query query = this.baseDao.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    /**
     * 更新成本明细
     * @param id
     */
    public void renewalCostDetail(Integer id,Double noTaxPrice,Double notAxSum) {
        String sql = "update WZ_SHEETDETAIL set NOTAXPRICE = "+noTaxPrice+",NOTAXSUM = "+notAxSum+" where id = "+id;
        Query query = this.baseDao.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }
    /**
     * 获取更新成本不为更新成功的单据
     */
    public List<?> findRenewalCost(String tableName) {
        String hql = " from "+tableName+" where extendInt3 <> :renewalCost and status =:status";
        Query query = baseDao.createQuery(hql);
        query.setParameter("renewalCost", RenewalCostEnum.SUCCESS.getRenewalCost());
        query.setParameter("status", 41);
        return query.list();
    }

}