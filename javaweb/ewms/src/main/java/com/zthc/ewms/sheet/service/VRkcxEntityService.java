package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.entity.query.VRkcxEntity;
import com.zthc.ewms.sheet.entity.query.VRkcxEntityCondition;
import com.zthc.ewms.sheet.service.guard.VRkcxEntityServiceGuard;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("vRkcxEntityService")
public class VRkcxEntityService extends VRkcxEntityServiceGuard {

    /**
     * 获取物资接收单管理列表
     * @param condition
     * @return
     * throws ParseException
     */
    public LayuiPage<VRkcxEntity> RKCXList(Integer userId,VRkcxEntity obj, VRkcxEntityCondition condition)throws ParseException {
        return this.dao.RKCXList(userId,obj,condition);
    }


    public List<VRkcxEntity> RKExcelList(Integer userId,VRkcxEntity obj, VRkcxEntityCondition condition)throws ParseException {
        return this.dao.RKExcelList(userId,obj,condition);
    }
}
