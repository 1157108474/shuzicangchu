package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.entity.query.VCkcxEntity;
import com.zthc.ewms.sheet.entity.query.VCkcxEntityCondition;
import com.zthc.ewms.sheet.service.guard.VCkcxEntityServiceGuard;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("vCkcxEntityService")
public class VCkcxEntityService extends VCkcxEntityServiceGuard {

    /**
     * 获取物资接收单管理列表
     * @param condition
     * @return
     * throws ParseException
     */
    public LayuiPage<VCkcxEntity> CKCXList(Integer userId,VCkcxEntity obj, VCkcxEntityCondition condition)throws ParseException {
        return this.dao.CKCXList(userId,obj,condition);
    }

    public List<VCkcxEntity> CKExcelList(Integer userId,VCkcxEntity obj, VCkcxEntityCondition condition)throws ParseException {
        return this.dao.CKExcelList(userId,obj,condition);
    }
}
