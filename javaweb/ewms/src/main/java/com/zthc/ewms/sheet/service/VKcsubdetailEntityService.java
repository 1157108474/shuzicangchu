package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.entity.query.VKcdetailEntity;
import com.zthc.ewms.sheet.entity.query.VKcdetailEntityCondition;
import com.zthc.ewms.sheet.entity.query.VKcsubdetailEntity;
import com.zthc.ewms.sheet.entity.query.VKcsubdetailEntityCondition;
import com.zthc.ewms.sheet.service.guard.VKcdetailEntityServiceGuard;
import com.zthc.ewms.sheet.service.guard.VKcsubdetailEntityServiceGuard;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("vKcsubdetailEntityService")
public class VKcsubdetailEntityService extends VKcsubdetailEntityServiceGuard {

    /**
     * 获取物资接收单管理列表
     * @param condition
     * @return
     * throws ParseException
     */
    public LayuiPage<VKcsubdetailEntity> KCDetailList(VKcsubdetailEntity obj, VKcsubdetailEntityCondition condition)throws ParseException {
        return this.dao.KCDetailList(obj,condition);
    }

    public LayuiPage<VKcsubdetailEntity> KCDetailsList(String userId,String storelocationcode,String materialcode, VKcsubdetailEntityCondition condition)throws ParseException {
        return this.dao.KCDetailsList(userId,storelocationcode,materialcode,condition);
    }

    public String getDetailunitname(VKcsubdetailEntity obj, VKcsubdetailEntityCondition condition)throws ParseException {
        return this.dao.getDetailunitname(obj,condition);
    }
}
