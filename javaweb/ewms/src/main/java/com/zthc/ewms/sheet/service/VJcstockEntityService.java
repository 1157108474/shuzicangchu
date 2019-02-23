package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.entity.query.VJcstockEntity;
import com.zthc.ewms.sheet.entity.query.VJcstockEntityCondition;
import com.zthc.ewms.sheet.service.guard.VJcstockEntityServiceGuard;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("vJcstockEntityService")
public class VJcstockEntityService extends VJcstockEntityServiceGuard {

    /**
     * 获取物资接收单管理列表
     * @param condition
     * @return
     * throws ParseException
     */
    public LayuiPage<VJcstockEntity> JCCXList(VJcstockEntity obj, VJcstockEntityCondition condition)throws ParseException {
        return this.dao.JCCXList(obj,condition);
    }
}
