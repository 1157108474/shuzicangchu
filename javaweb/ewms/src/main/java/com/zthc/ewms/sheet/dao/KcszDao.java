package com.zthc.ewms.sheet.dao;

import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.guard.KcszDaoGuard;
import com.zthc.ewms.sheet.entity.query.KcszCondition;

import oracle.jdbc.OracleTypes;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

@Repository("kcszDao")
public class KcszDao extends KcszDaoGuard {

    /**
     * 获取库存收支查询列表
     * @param
     * @param
     * @return
     * @throws ParseException
     */
    public List<Map<String,Object>> KCSZList(String materialcode, String materialname, String storeid, KcszCondition condition) throws ParseException, SQLException {
        SessionFactory sessionFactory = this.baseDao.sessionFactory;
        Connection connection = null;

        try {
            connection = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
            CallableStatement statement = connection.prepareCall("{call stock_sz(?,?,?,?,?,?,?)}");
            String sqlInto = " 1=1 ";
            String sqlwldate = " 1=1 ";
            String sqlOther = " 1=1 ";
            String startTime = condition.getStartTime();
            String endTime = condition.getEndTime();
            List<Map<String,Object>> list = new ArrayList();
            if (!StringUtils.isEmpty(startTime)){
                if (startTime.indexOf("0:00:00") > 0){
                    startTime = startTime.split(" ")[0];
                }
                sqlInto = sqlInto + " and detail.createdate >= to_date('" + startTime.toString().trim() + "','yyyy-MM-dd HH24:MI:SS')";
                sqlwldate = sqlwldate + " and detail.createdate < to_date('" + startTime.toString().trim() + "','yyyy-MM-dd HH24:MI:SS')";
            }else if (!StringUtils.isEmpty(endTime)){
                if (endTime.indexOf("0:00:00") > 0){
                    endTime = endTime.split(" ")[0];
                }
                sqlInto = sqlInto + " and detail.createdate <= to_date('" + endTime.toString().trim() + " 23:59:59','yyyy-MM-dd HH24:MI:SS')";
            }else{
                return list;
            }

            if (!StringUtils.isEmpty(materialcode)){
                sqlOther = sqlOther + " and materialcode like'%" + materialcode + "%'";
            }
            if (!StringUtils.isEmpty(materialname)){
                sqlOther = sqlOther + " and materialname like'%" + materialname + "%'";
            }
            if (!StringUtils.isEmpty(storeid)){
                sqlOther = sqlOther + " and a.storeid =" + storeid;
            }
            int pageIndex = condition.getPageNum();
            int pageSize = condition.getPageTotal();
            statement.registerOutParameter(1, OracleTypes.CURSOR);
             statement.registerOutParameter(2, Types.INTEGER);
            statement.setString(3,sqlInto);
            statement.setString(4, sqlOther);
            statement.setString(5, sqlwldate);
            statement.setInt(6, pageIndex);
            statement.setInt(7, pageSize);
            statement.execute();
            ResultSet rs = (ResultSet) statement.getObject(1);
            Map<String,Object> map ;
            while (rs.next()){
                map  =  new HashMap();
                map.put("materialcode",rs.getString(1));
                map.put("materialname",rs.getString(2));
                map.put("materialmodel",rs.getString(3));
                map.put("materialspecification",rs.getString(4));
                String startcount = rs.getString(5);
                String startmoney = rs.getString(6);
                String bqrcount = rs.getString(7);
                String bqrkmoney = rs.getString(8);
                String bqckcount = rs.getString(9);
                String bqckmoney = rs.getString(10);
                map.put("startcount",startcount);
                map.put("startmoney",startmoney);
                map.put("bqrcount",bqrcount);
                map.put("bqrkmoney",bqrkmoney);
                map.put("bqckcount",bqckcount);
                map.put("bqckmoney",bqckmoney);
                map.put("qmcount",Integer.parseInt(startcount)+Integer.parseInt(bqrcount)-Integer.parseInt(bqckcount));
                map.put("qmmoney",Integer.parseInt(startmoney)+Double.parseDouble(bqrkmoney)-Integer.parseInt(bqckmoney));
                list.add(map);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.close();
            }

        }
    }

} 