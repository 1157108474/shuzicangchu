package com.zthc.ewms.sheet.service;

import com.zthc.ewms.sheet.entity.query.KcszCondition;
import com.zthc.ewms.sheet.service.guard.KcszServiceGuard;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service("kcszService")
public class KcszService extends KcszServiceGuard {

    /**
     * 获取物资接收单管理列表
     * @param
     * @return
     * throws ParseException
     */
    public List<Map<String,Object>> KCSZList(String materialcode, String materialname, String storeid, KcszCondition condition) throws ParseException, SQLException {
        return this.dao.KCSZList(materialcode,materialname,storeid,condition);
    }
}
