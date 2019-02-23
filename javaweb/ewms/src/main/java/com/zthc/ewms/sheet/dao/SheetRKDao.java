package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.SheetRKDaoGuard;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.rk.RkDetail;
import com.zthc.ewms.sheet.entity.rk.RkDetails;
import com.zthc.ewms.sheet.entity.rk.RkSubDetail;
import com.zthc.ewms.sheet.entity.rk.RkdList;
import com.zthc.ewms.sheet.entity.zr.SheetZRD;
import com.zthc.ewms.sheet.entity.zr.ZrDetails;
import com.zthc.ewms.system.dictionary.dao.DictionaryDao;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.user.dao.UserScopeDao;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class SheetRKDao extends SheetRKDaoGuard {

    @Resource(name = "userScopeDao")
    public UserScopeDao userScopeDao;

    @Resource(name = "dictionaryDao")
    public DictionaryDao dictionaryDao;

    /**
     * 获取物资接收单列表
     *
     * @param sheet
     * @param condition
     * @return
     */
    public LayuiPage<SheetJSD> generalJSDList(Sheet sheet, SheetCondition condition) {

        LayuiPage<SheetJSD> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql = " from SheetJSD where 1 = 1  and jscount > rkcoumt";

        if (!StringUtils.isEmpty(sheet.getCode())) {
            hql += " and code like :code ";
            param.put("code", "%%" + sheet.getCode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(sheet.getOrderNum())) {
            hql += " and ordernum like :ordernum ";
            param.put("ordernum", "%%" + sheet.getOrderNum().trim() + "%%");
        }

        hql += " order by id DESC ";
        String totalsql = " select count(*) " + hql;

        List<SheetJSD> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 获取物资入库单新增明细列表
     *
     * @param rkDetail
     * @param condition
     * @return
     */
    public LayuiPage<RkDetail> detailsList(RkDetail rkDetail, SheetRKCondition condition) {

        LayuiPage<RkDetail> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql = " from RkDetail where 1 = 1 and (jsCount - isCount )>0 and jsCode = :jscode";
        param.put("jscode", rkDetail.getJsCode());

        if (!StringUtils.isEmpty(rkDetail.getMaterialCode())) {
            hql += " and materialCode like :materialCode ";
            param.put("materialCode", "%" + rkDetail.getMaterialCode().trim() + "%");
        }
        if (!StringUtils.isEmpty(rkDetail.getDescription())) {
            hql += " and description like :description ";
            param.put("description", "%%" + rkDetail.getDescription().trim() + "%%");
        }

        String totalsql = " select count(1) " + hql;

        List<RkDetail> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);
        Dictionary dictionary;
        for (int i = 0; i < list.size(); i++) {
            dictionary = this.dictionaryDao.getDictionaryByCode(list.get(i).getJsTypeCode());
            if (StringUtils.isEmpty(dictionary)) {
                continue;
            }
            list.get(i).setStoreId(dictionary.getId());
            list.get(i).setStoreName(dictionary.getName());
        }


        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 物资入库获取已分配明细列表
     *
     * @param obj
     * @param condition
     * @return
     */
    public LayuiPage<RkSubDetail> getSonDetail(RkSubDetail obj, SheetRKCondition condition) {

        LayuiPage<RkSubDetail> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql = " from RkSubDetail where 1 = 1  and detailId = :detailId ";
        param.put("detailId", obj.getDetailId());

        String totalsql = " select count(1) " + hql;

        List<RkSubDetail> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 获取物资入库单页面明细列表
     *
     * @param rkDetails
     * @param condition
     * @return
     */
    public LayuiPage<RkDetails> rkDetailList(RkDetails rkDetails, SheetRKCondition condition) {

        LayuiPage<RkDetails> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql = " from RkDetails where 1 = 1 and sheetId = :sheetId ";
        param.put("sheetId", rkDetails.getSheetId());

        String totalsql = " select count(1) " + hql;

        List<RkDetails> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 获取物资杂入单页面明细列表
     *
     * @param zrDetails
     * @param condition
     * @return
     */
    public LayuiPage<ZrDetails> zrDetailList(ZrDetails zrDetails, SheetRKCondition condition) {

        LayuiPage<ZrDetails> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql = " from ZrDetails where 1 = 1 and sheetId = :sheetId ";
        param.put("sheetId", zrDetails.getSheetId());

        String totalsql = " select count(1) " + hql;

        List<ZrDetails> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 物资入库管理页面列表
     *
     * @param obj
     * @param condition
     * @param userId
     * @param begin
     * @param end
     * @return
     * @throws ParseException
     */
    public LayuiPage<SheetRKD> manageRkList(SheetRKD obj, SheetCondition condition, Integer userId, String begin,
                                            String end)
            throws ParseException {

        LayuiPage<SheetRKD> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();
        List<Integer> userScopeZTID = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String hql = " from SheetRKD where 1 = 1 ";

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
        if (!StringUtils.isEmpty(obj.getZtid())) {
            hql += " and ztid = :ztid";
            param.put("ztid", obj.getZtid());
        }
        if (!StringUtils.isEmpty(obj.getCreator())) {
            hql += " and creator = :creator";
            param.put("creator", obj.getCreator());
        }
        if (!StringUtils.isEmpty(obj.getOrdernum())) {
            hql += " and ordernum like :ordernum ";
            param.put("ordernum", "%%" + obj.getOrdernum().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and code like :code ";
            param.put("code", "%%" + obj.getCode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getStatus())) {
            hql += " and status = :status ";
            param.put("status", obj.getStatus());
        }
        if (!StringUtils.isEmpty(obj.getExtendstring6())) {
            hql += " and extendstring6 like :extendstring6 ";
            param.put("extendstring6", "%%" + obj.getExtendstring6().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getTypeid())) {
            hql += " and typeid = :typeid ";
            param.put("typeid", obj.getTypeid());
        }
        if (!StringUtils.isEmpty(obj.getReceivenum())) {
            hql += " and receivenum like :receivenum ";
            param.put("receivenum", obj.getReceivenum());
        }
        if (!StringUtils.isEmpty(begin) && StringUtils.isEmpty(end)) {
            Date startTime = sdf.parse(begin + " 00:00:00");
            hql += " and createdate >= :start ";
            param.put("start", startTime);
        } else if (StringUtils.isEmpty(begin) && !StringUtils.isEmpty(end)) {
            Date endTime = sdf.parse(end + " 23:59:59");
            hql += " and createdate <= :end ";
            param.put("end", endTime);
        } else if (!StringUtils.isEmpty(begin) && !StringUtils.isEmpty(end)) {
            Date startTime = sdf.parse(begin + " 00:00:00");
            Date endTime = sdf.parse(end + " 23:59:59");
            hql += " and createdate BETWEEN :start and :end";
            param.put("start", startTime);
            param.put("end", endTime);
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by createdate DESC ";

        List<SheetRKD> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 物资杂入管理页面列表
     *
     * @param obj
     * @param condition
     * @param userId
     * @param begin
     * @param end
     * @return
     * @throws ParseException
     */
    public LayuiPage<SheetZRD> manageZrList(SheetZRD obj, SheetCondition condition, Integer userId, String begin,
                                            String end)
            throws ParseException {

        LayuiPage<SheetZRD> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();
        List<Integer> userScopeZTID = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String hql = " from SheetZRD where 1 = 1 ";

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
        if (!StringUtils.isEmpty(obj.getZtid())) {
            hql += " and ztid = :ztid";
            param.put("ztid", obj.getZtid());
        }
        if (!StringUtils.isEmpty(obj.getCreator())) {
            hql += " and creator = :creator";
            param.put("creator", obj.getCreator());
        }
        if (!StringUtils.isEmpty(obj.getOrdernum())) {
            hql += " and ordernum like :ordernum ";
            param.put("ordernum", "%%" + obj.getOrdernum().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and code like :code ";
            param.put("code", "%%" + obj.getCode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getStatus())) {
            hql += " and status = :status ";
            param.put("status", obj.getStatus());
        }
        if (!StringUtils.isEmpty(obj.getExtendstring6())) {
            hql += " and extendstring6 like :extendstring6 ";
            param.put("extendstring6", "%%" + obj.getExtendstring6().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getTypeid())) {
            hql += " and typeid = :typeid ";
            param.put("typeid", obj.getTypeid());
        }
        if (!StringUtils.isEmpty(obj.getReceivenum())) {
            hql += " and receivenum like :receivenum ";
            param.put("receivenum", obj.getReceivenum());
        }
        if (!StringUtils.isEmpty(begin) && StringUtils.isEmpty(end)) {
            Date startTime = sdf.parse(begin + " 00:00:00");
            hql += " and createdate >= :start ";
            param.put("start", startTime);
        } else if (StringUtils.isEmpty(begin) && !StringUtils.isEmpty(end)) {
            Date endTime = sdf.parse(end + " 23:59:59");
            hql += " and createdate <= :end ";
            param.put("end", endTime);
        } else if (!StringUtils.isEmpty(begin) && !StringUtils.isEmpty(end)) {
            Date startTime = sdf.parse(begin + " 00:00:00");
            Date endTime = sdf.parse(end + " 23:59:59");
            hql += " and createdate BETWEEN :start and :end";
            param.put("start", startTime);
            param.put("end", endTime);
        }

        String totalsql = " select count(*) " + hql;
        // 排序
        hql += " order by id DESC ";

        List<SheetZRD> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 物资入库获取入库单列表
     *
     * @param obj
     * @param condition
     * @return
     */
    public LayuiPage<RkdList> generalRKDList(RkdList obj, SheetRKCondition condition) {

        LayuiPage<RkdList> ret = new LayuiPage<>();
        Map<String, Object> param = new HashMap<>();

        String hql = " from RkdList where 1 = 1 ";
        if (obj.getCreator() != null) {
            hql += " and creator = :creator ";
            param.put("creator", obj.getCreator());
        }

        if (!StringUtils.isEmpty(obj.getCode())) {
            hql += " and code like :code";
            param.put("code", "%%" + obj.getCode().trim() + "%%");
        }
        if (!StringUtils.isEmpty(obj.getOrderNum())) {
            hql += " and orderNum like :orderNum ";
            param.put("orderNum", "%%" + obj.getOrderNum().trim() + "%%");
        }

        String totalsql = " select count(1) " + hql;
        hql += " order by id DESC";

        List<RkdList> list = baseDao.findByHql(hql, param, condition.getPageNum(), condition.getPageTotal());
        Long total = baseDao.countByHql(totalsql, param);

        ret.setData(list);
        ret.setCount(total);

        return ret;
    }

    /**
     * 保存单据
     *
     * @param obj
     * @param condition
     */
    public void saveSheet(SheetRK obj, SheetRKCondition condition) {
        baseDao.save(obj);
    }

    /**
     * 物资接收单修改
     *
     * @param sheet
     * @return
     */
    public void editSheet(SheetRK sheet) {
        String hql = " update Sheet set typeId = :typeId, receiveNum = :receiveNum, extendInt1 = :extendInt1," +
                " orderNum = :orderNum, extendInt2 = :extendInt2, extendString3 = :extendString3, " +
                " extendString4 = :extendString4, extendString2 = :extendString2, ztId = :ztId, extendString1 = " +
                ":extendString1, " +
                "providerDepId =:providerDepId, extendString5 = :extendString5, updator = :updator, " +
                " updateDate = :updateDate where id = :id ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("typeId", sheet.getTypeId());
        query.setParameter("receiveNum", sheet.getReceiveNum());
        query.setParameter("extendInt1", sheet.getExtendInt1());
        query.setParameter("orderNum", sheet.getOrderNum());
        query.setParameter("extendInt2", sheet.getExtendInt2());
        query.setParameter("extendString3", sheet.getExtendString3());
        query.setParameter("extendString4", sheet.getExtendString4());
        query.setParameter("extendString2", sheet.getExtendString2());
        query.setParameter("ztId", sheet.getZtId());
        query.setParameter("extendString1", sheet.getExtendString1());
        query.setParameter("providerDepId", sheet.getProviderDepId());
        query.setParameter("extendString5", sheet.getExtendString5());
        query.setParameter("updator", sheet.getUpdator());
        query.setParameter("updateDate", new Date());
        query.setParameter("id", sheet.getId());
        query.executeUpdate();
    }

    /**
     * 获取明细库房库位
     *
     * @param code
     * @param sid
     * @return
     */
    public SheetStock getOriginalLocation(String code, Integer sid) {

        SheetStock stock = null;

        String hql = " from SheetStock where materialCode = :materialCode and storeId = :storeId ";
        Query query = baseDao.createQuery(hql);
        query.setParameter("materialCode", code);
        query.setParameter("storeId", sid);
        List<SheetStock> list = query.list();
        if (null != list && list.size() != 0) {
            return list.get(0);
        } else {
            return stock;
        }
    }

    /**
     * 物资入库提交前验证
     *
     * @param sheetId
     * @return
     */
    public int isEqualsCount(Integer sheetId) {
        int param = 0;
        Map<String, Object> map = new HashMap<>();

        //检查是否已分配库位
        String sql = " select (select sum(b.detailcount) from WZ_SHEETRKDETAIL b where b.sheetid = a.id)as mxcount, " +

                " (select sum(c.subDetailCount) from wz_sheetrksubdetail c , WZ_SHEETRKDETAIL d where c.detailId " +
                "= d" +
                ".id and d.sheetid = a.id ) " +
                " as subcount from WZ_SHEET_RK a where a.id = " + sheetId;
        SQLQuery query = baseDao.getCurrentSession().createSQLQuery(sql);
        query.addScalar("mxcount", StandardBasicTypes.FLOAT);
        query.addScalar("subcount", StandardBasicTypes.FLOAT);
        List list = query.list();
        if (list.size() > 0) {
            Iterator result = list.iterator();
            while (result.hasNext()) {
                Object[] rows = (Object[]) result.next();
                Float mxCount = (Float) rows[0];
                Float subCount = (Float) rows[1];
                if (!StringUtils.isEmpty(mxCount) && !StringUtils.isEmpty(subCount)) {
                    if (Math.abs(mxCount - subCount) <= 0) {
                        param = 1;
                    } else {
                        param = 0;
                    }
                }
            }
        }
        return param;
    }

    public void deleteSheet(Integer id) {
        baseDao.removeById(SheetRK.class, id);
    }

    // 工作流

    public int updateSheetStauts(int processInstanceId, int status, String extendString6) {//修改单据状态为审核中
        String hql = "update SheetRK set  status= :status, extendString6=:extendString6 where routeId=:routeId";
        Query query = this.baseDao.createQuery(hql);
        query.setParameter("status", status);
        query.setParameter("extendString6", extendString6);
        query.setParameter("routeId", processInstanceId);
        return query.executeUpdate();
    }


} 