package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.hibernate.ComDao;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.SheetCGDaoGuard;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.order.Order;
import com.zthc.ewms.sheet.entity.order.OrderDetails;
import com.zthc.ewms.sheet.entity.order.OrderHistory;
import com.zthc.ewms.sheet.entity.query.VCgddEntity;
import com.zthc.ewms.sheet.entity.query.VCgddEntityCondition;
import com.zthc.ewms.system.material.entity.guard.Material;
import com.zthc.ewms.system.user.dao.UserScopeDao;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import drk.util.TextUtil;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SheetCGDao extends SheetCGDaoGuard {

    @Resource(name = "userScopeDao")
    public UserScopeDao userScopeDao;
    @Autowired
    ComDao comDao;
    /**
     * 获取采购订单通用调用列表
     *
     * @param condition
     * @return
     */
    public LayuiPage<Order> generalList(SheetCGCondition condition) {

        LayuiPage<Order> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String lefthql = "select new com.zthc.ewms.sheet.entity.order.Order(MIN(id),erpid, ordernum, providerdepname, " +
                "stockorgid, ordertype, issuecode, stockorgname, providerdepid) ";
        String hql = " from Order where  canusedcount>0   ";
      /*  String hql = " from Order where canusedcount>0  ";*/

        if (!StringUtils.isEmpty(condition.getOrderId())) {
            hql += " and  id != :orderid ";
            param.put("orderid", Integer.parseInt(condition.getOrderId()));
        }
        if (!StringUtils.isEmpty(condition.getOrderNum())) {
            hql += " and  ordernum like :ordernum ";
            param.put("ordernum", "%" + condition.getOrderNum().trim() + "%");
        }
        if (!StringUtils.isEmpty(condition.getProviderName())) {
            hql += " and  providerdepname like :providerdepname ";
            param.put("providerdepname", "%" + condition.getProviderName().trim() + "%");
        }

        String totalsql = " select count(distinct erpid) " + hql;
        // 排序
        hql += " GROUP BY erpid,ordernum,providerdepname,stockorgid,ordertype,issuecode,stockorgname,providerdepid  order by erpid DESC";

        List<Order> list = baseDao.findByHql(lefthql + hql, param, condition.getPageNum(), condition.getPageTotal());

        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

//    /**
//     * 获取采购订单通用调用列表
//     *
//     * @param condition
//     * @return
//     */
//    public LayuiPage<Order> generalList(SheetCGCondition condition) {
//
//        LayuiPage<Order> ret = new LayuiPage<>();
//        Map<String, Object> param = new HashMap<>();
//        String hql_left = " select new Order(a.id,a.ordernum,a.providerdepname,a,stockorgid,a.ordertype,a
// .issuecode,a.stockorgname,a.providerdepid, " +
//                " a.allcount,a.canusedcount ) ";
//        String hql = " from Order a,OrderDetails b ";
//        hql += " where a.id = b.erpid and b.isCount > 0 ";
//
//        if (!StringUtils.isEmpty(condition.getOrderId())) {
//            hql += " and a.id != :orderid ";
//            param.put("orderid", Integer.parseInt(condition.getOrderId()));
//        }
//        if (!StringUtils.isEmpty(condition.getOrderNum())) {
//            hql += " and a.ordernum like :ordernum ";
//            param.put("ordernum", "%%" + condition.getOrderNum().trim() + "%%");
//        }
//        if (!StringUtils.isEmpty(condition.getProviderName())) {
//            hql += " and a.providerdepname like :providerdepname ";
//            param.put("providerdepname", "%%" + condition.getProviderName().trim() + "%%");
//        }
//
//
//        String totalsql = " select count(a.id) " + hql;
//        // 排序
//        hql += " order by a.id DESC";
//
//
//        List<Order> list = baseDao.findByHql(hql_left + hql, param, condition.getPageNum(), condition.getPageTotal());
//
//        Long total = baseDao.countByHql(totalsql, param);
//
//        ret.setData(list);
//        ret.setCount(total);
//
//        return ret;
//    }

    /**
     * 获取物资接收单管理列表
     *
     * @param obj
     * @param condition
     * @return
     * @throws ParseException
     */
    public LayuiPage<ManageOrder> manageOrderList(ManageOrder obj, SheetCGCondition condition, Integer userId, String
            begin,String end) throws ParseException {

        LayuiPage<ManageOrder> ret = new LayuiPage<>();
        List<Integer> userScopeZTID = null;
        Map<String, Object> param = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String hql = " from ManageOrder where 1 = 1 and kindid = 588";

        if (userId.equals(obj.getCreator())) {
            obj.setStatus(39);
        } else {
            userScopeZTID = this.userScopeDao.listUserScopeZTID(userId, UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        }

        if (userScopeZTID == null || userScopeZTID.size() == 0) {

            hql += " and creator =  :creator";
            param.put("creator", userId);
        } else {
            String ztids = "";
            int i = 0;
            for (Integer ztid : userScopeZTID) {
                if (i == 0) {
                    hql += " and  ( ztid =  :ztid" + i;

                } else {
                    hql += " or ztid =  :ztid" + i;
                }
                param.put("ztid" + i++, ztid);
            }
            hql += " ) ";

        }
        if (!StringUtils.isEmpty(obj.getDepartid())) {
            hql += " and departid = :departid ";
            param.put("departid", obj.getDepartid());
        }
        if (!StringUtils.isEmpty(obj.getProviderdepid())) {
            hql += " and providerdepid = :providerdepid ";
            param.put("providerdepid", obj.getProviderdepid());
        }
        if (!StringUtils.isEmpty(obj.getZtid())) {
            hql += " and ztid = :ztid ";
            param.put("ztid", obj.getZtid());
        }
        if (!StringUtils.isEmpty(obj.getCreator())) {
            hql += " and creator = :creator ";
            param.put("creator", obj.getCreator());
        }
        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and code like :code ";
            param.put("code", "%" + obj.getCode().trim() + "%");
        }
        if (!StringUtils.isEmpty(obj.getOrdernum())) {
            hql += " and ordernum like :ordernum ";
            param.put("ordernum", "%" + obj.getOrdernum().trim() + "%");
        }
        if (!StringUtils.isEmpty(obj.getStatus())) {
            hql += " and status = :status ";
            param.put("status", obj.getStatus());
        }
        if (!StringUtils.isEmpty(begin) && StringUtils.isEmpty(end)) {
            Date startTime = sdf.parse(begin + " 00:00:00");
            hql += " and createdate >= :starttime ";
            param.put("starttime", startTime);
        } else if (StringUtils.isEmpty(begin) && !StringUtils.isEmpty(end)) {
            Date endTime = sdf.parse(end + " 23:59:59");
            hql += " and createdate <= :endtime";
            param.put("endtime", endTime);
        } else if (!StringUtils.isEmpty(begin) && !StringUtils.isEmpty(end)) {
            Date startTime = sdf.parse(begin + " 00:00:00");
            Date endTime = sdf.parse(end + " 23:59:59");
            hql += " and createdate BETWEEN :starttime and :endtime ";
            param.put("starttime", startTime);
            param.put("endtime", endTime);
        }


        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id DESC ";

        List<ManageOrder> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 获取物资接收单新增明细列表
     *
     * @param condition
     * @param details
     * @return
     */
    public LayuiPage<OrderDetails> detailsList(OrderDetails details, SheetCGCondition condition) {

        LayuiPage<OrderDetails> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();
        String hql = " from OrderDetails where ordernum = :ordernum and isCount > 0";
        param.put("ordernum", details.getOrdernum());

        if (!StringUtils.isEmpty(details.getMaterialCode())) {
            hql += " and materialCode like :materialcode ";
            param.put("materialcode", "%%" + details.getMaterialCode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(details.getDescription())) {
            hql += " and description like :description ";
            param.put("description", "%%" + details.getDescription().trim() + "%%");
        }


        String totalsql = " select count(*) " + hql;

        List<OrderDetails> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);


        // 进行是否为设备的判断
        if (null != list || list.size() != 0) {
            SheetStock stock = null;
            Material material = null;
            for (int i = 0; i < list.size(); i++) {
                stock = this.getWzStock(list.get(i).getMaterialCode(), list.get(i).getStockorgid());
                if (!StringUtils.isEmpty(stock)) {
                    list.get(i).setIsEnableSN("true");
                    list.get(i).setIsEquipment(stock.getIsEquipment() == null ? 0 : stock.getIsEquipment());
                    list.get(i).setEnableSn(stock.getEnableSn() == null ? 0 : stock.getEnableSn());
                } else {
                    material = this.getMaterial(list.get(i).getMaterialCode(), list.get(i).getStockorgid());
                    if (material != null) {
                        list.get(i).setIsEnableSN("false");
                        list.get(i).setEnableSn(material.getEnableSN() == null ? 0 : material.getEnableSN());
                        list.get(i).setIsEquipment(list.get(i).getEnableSn() == 1 ? 1 : 0);
                    } else {
                        list.get(i).setIsEnableSN("false");
                        list.get(i).setEnableSn(0);
                        list.get(i).setIsEquipment(0);
                    }
                }
            }
        }


        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 获取物资接收单明细列表
     *
     * @param condition
     * @return
     */
    public LayuiPage<SheetJSDDetails> detailsJSDList(SheetCGCondition condition) {

        LayuiPage<SheetJSDDetails> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql = " from SheetJSDDetails where sheetId = :sheetid ";
        param.put("sheetid", condition.getSheetId());

        if (!StringUtils.isEmpty(condition.getMaterialCode())) {
            hql += " and materialCode like :materialCode ";
            param.put("materialCode", "%" + condition.getMaterialCode().trim() + "%");
        }

        String totalsql = " select count(*) " + hql;

        List<SheetJSDDetails> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 获取物资接收单明细日志列表
     *
     * @param relationGuid
     * @param condition
     * @return
     */
    public LayuiPage<OrderHistory> orderHistoryList(String relationGuid, SheetCGCondition condition) {

        LayuiPage<OrderHistory> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql = " from OrderHistory where relationGuid = :relationGuid ";
        param.put("relationGuid", relationGuid);

        String totalsql = " select count(1) " + hql;

        List<OrderHistory> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 采购订单查询
     *
     * @param obj
     * @param condition
     * @return
     */
    public LayuiPage<VCgddEntity> CGDDList(VCgddEntity obj, VCgddEntityCondition condition, Integer userId, String startTime,
                                           String endTime) throws ParseException {

        LayuiPage<VCgddEntity> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();
        List<Integer> userScopeZTID = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userScopeZTID = this.userScopeDao.listUserScopeZTID(userId, UserEnums.ScopeTypeEnum.ORGANIZATION.getType());


        String hql = " from VCgddEntity where 1 = 1";

        if (userScopeZTID == null || userScopeZTID.size() == 0) {

            hql += " and creator =  :creator";
            param.put("creator", userId);
        } else {
            String ztids = "";
            int i = 0;
            for (Integer ztid : userScopeZTID) {
                if (i == 0) {
                    hql += " and  ( stockorgid =  :ztid" + i;

                } else {
                    hql += " or stockorgid =  :ztid" + i;
                }
                param.put("ztid" + i++, ztid);
            }
            hql += " ) ";

        }

        if (!StringUtils.isEmpty(obj.getProviderdepid())) {
            hql += " and providerdepid = :providerdepid ";
            param.put("providerdepid", obj.getProviderdepid());
        }
        if (!StringUtils.isEmpty(obj.getStockorgid())) {
            hql += " and stockorgid = :stockorgid ";
            param.put("stockorgid", obj.getStockorgid());
        }
        if (!StringUtils.isEmpty(obj.getOrdernum())) {
            hql += " and ordernum like :ordernum ";
            param.put("ordernum", "%%" + obj.getOrdernum().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getExtendint1())) {
            hql += " and extendint1 = :extendint1 ";
            param.put("extendint1", obj.getExtendint1());
        }
        if (!StringUtils.isEmpty(obj.getMaterialcode())) {
            hql += " and materialcode like :materialcode ";
            param.put("materialcode", "%%" + obj.getMaterialcode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getDescription())) {
            hql += " and description like :description ";
            param.put("description", "%%" + obj.getDescription().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getConsignment())) {
            hql += " and consignment = :consignment";
            param.put("consignment", obj.getConsignment());
        }
        // 需求日期
        if (!StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
            Date start = sdf.parse(startTime + " 00:00:00");
            hql += " and createdate >= :startTime ";
            param.put("startTime", start);
        } else if (StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            Date end = sdf.parse(endTime + " 23:59:59");
            hql += "and createdate <= :endTime ";
            param.put("endTime", end);
        } else if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            Date start = sdf.parse(startTime + " 00:00:00");
            Date end = sdf.parse(endTime + " 23:59:59");
            hql += " and createdate BETWEEN :starttime and :endtime ";
            param.put("starttime", start);
            param.put("endtime", end);
        }

        String totalsql = " select count(1) " + hql;
        hql += " order by ordernum DESC ";

        List<VCgddEntity> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 采购订单查询
     *
     * @param obj
     * @param condition
     * @return
     */
    public List<VCgddEntity> listCGDD(VCgddEntity obj, VCgddEntityCondition condition, Integer userId, String startTime,
                                      String endTime) {
        Map<String, Object> param = new HashMap<>();
        List<Integer> userScopeZTID = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userScopeZTID = this.userScopeDao.listUserScopeZTID(userId, UserEnums.ScopeTypeEnum.ORGANIZATION.getType());

        String hql = " from VCgddEntity where 1 = 1";
        if (userScopeZTID == null || userScopeZTID.size() == 0) {
            hql += " and creator =  :creator";
            param.put("creator", userId);
        } else {
            String ztids = "";
            int i = 0;
            for (Integer ztid : userScopeZTID) {
                if (i == 0) {
                    hql += " and  ( stockorgid =  :ztid" + i;

                } else {
                    hql += " or stockorgid =  :ztid" + i;
                }
                param.put("ztid" + i++, ztid);
            }
            hql += " ) ";

        }
        if (!StringUtils.isEmpty(obj.getProviderdepid())) {
            hql += " and providerdepid = :providerdepid ";
            param.put("providerdepid", obj.getProviderdepid());
        }
        if (!StringUtils.isEmpty(obj.getStockorgid())) {
            hql += " and stockorgid = :stockorgid ";
            param.put("stockorgid", obj.getStockorgid());
        }
        if (!StringUtils.isEmpty(obj.getOrdernum())) {
            hql += " and ordernum like :ordernum ";
            param.put("ordernum", "%%" + obj.getOrdernum().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getExtendint1())) {
            hql += " and extendint1 = :extendint1 ";
            param.put("extendint1", obj.getExtendint1());
        }
        if (!StringUtils.isEmpty(obj.getMaterialcode())) {
            hql += " and materialcode like :materialcode ";
            param.put("materialcode", "%%" + obj.getMaterialcode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getDescription())) {
            hql += " and description like :description ";
            param.put("description", "%%" + obj.getDescription().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getConsignment())) {
            hql += " and consignment = :consignment";
            param.put("consignment", obj.getConsignment());
        }
        // 需求日期
        try {
            if (!StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
                Date start = null;
                start = sdf.parse(startTime + " 00:00:00");
                hql += " and createdate >= :startTime ";
                param.put("startTime", start);
            } else if (StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
                Date end = sdf.parse(endTime + " 23:59:59");
                hql += "and createdate <= :endTime ";
                param.put("endTime", end);
            } else if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
                Date start = sdf.parse(startTime + " 00:00:00");
                Date end = sdf.parse(endTime + " 23:59:59");
                hql += " and createdate BETWEEN :starttime and :endtime ";
                param.put("starttime", start);
                param.put("endtime", end);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hql += " order by ordernum DESC ";
        return comDao.list(hql, param);
    }


    /**
     * 获取单条数据
     *
     * @param sheetId
     * @return
     */
    public ManageOrder getOrderOne(Integer sheetId) {
        ManageOrder ret = null;
        if (!TextUtil.isNotNull(sheetId)) {
            ret = new ManageOrder();
        } else {
            ret = baseDao.getById(ManageOrder.class, sheetId);
        }
        return ret;
    }

    public Order getOrderOneById(Integer id) {
        Order ret = null;
        if (!TextUtil.isNotNull(id)) {
            ret = new Order();
        } else {
            ret = baseDao.getById(Order.class, id);
        }
        return ret;
    }

    /**
     * 获取物料
     *
     * @param code
     * @param ztId
     * @return
     */
    public Material getMaterial(String code, Integer ztId) {
        Material material = null;
        String hql = "from Material where code = :code and ztid = :ztid ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", code);
        query.setParameter("ztid", ztId);
        List<Material> list = query.list();
        if (null != list && list.size() != 0) {
            return list.get(0);
        } else {
            return material;
        }
    }

    /**
     * 获取库房
     *
     * @param code
     * @param ztID
     * @return
     */
    public SheetStock getWzStock(String code, Integer ztID) {
        SheetStock stock = null;
        String hql = " from SheetStock where materialcode = :code and ztid = :ztid ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("code", code);
        query.setParameter("ztid", ztID);
        List<SheetStock> list = query.list();
        if (null != list && list.size() != 0) {
            return list.get(0);
        } else {
            return stock;
        }
    }

    /**
     * 物资接收单修改
     *
     * @param sheet
     * @return
     */
    public void editSheet(Sheet sheet) {
        String hql = " update Sheet set orderNum = :ordernum, extendInt1 = :extendInt, extendString1 = :providername," +
                " " +
                " extendString2 = :ztname, extendString3 = :ordertype, extendString4 = :extendString, extendString5 =" +
                " :extendString5," +
                " providerDepId = :providerid, updater = :updater, updateDate = :updatedate where id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("ordernum", sheet.getOrderNum());
        query.setParameter("extendInt", sheet.getExtendInt1());
        query.setParameter("providername", sheet.getExtendString1());
        query.setParameter("ztname", sheet.getExtendString2());
        query.setParameter("ordertype", sheet.getExtendString3());
        query.setParameter("extendString", sheet.getExtendString4());
        query.setParameter("extendString5", sheet.getExtendString5());
        query.setParameter("providerid", sheet.getProviderDepId());
        query.setParameter("updater", sheet.getUpdater());
        query.setParameter("updatedate", new Date());
        query.setParameter("id", sheet.getId());
        query.executeUpdate();
    }

    public void savePlanOther(SheetDetail detail) {
        String hql = " update SheetDetail set expirationTime = :expirationTime, extendDate2 = :extendDate2, " +
                "planDepartId = :planDepartId" +
                " where id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("expirationTime", detail.getExpirationTime());
        query.setParameter("extendDate2", detail.getExtendDate21());
        query.setParameter("planDepartId", detail.getPlanDepartId());
        query.setParameter("id", detail.getId());
        query.executeUpdate();
    }

    public int isOrderTrue(Integer id) {
        int ret = 0;
        Map<String, Object> param = new HashMap<>();
        String hql = " select count(1) from SheetDetail where sheetId = :sheetId ";
        param.put("sheetId", id);
        Long total = baseDao.countByHql(hql, param);
        if (total <= 0) {
            ret = 0;
        } else {
            ret = 1;
        }
        return ret;
    }
    public String getSheetCGNameOne(Integer id) {
        String hql = " select user.name from User user, SheetCG cg where user.id =cg.creator and  cg.id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("id",id);
        List<?> objects = query.list();
        if (!objects.isEmpty()&& objects.size()>0){
            return (String) objects.get(0);
        }
        return "";

    };
}