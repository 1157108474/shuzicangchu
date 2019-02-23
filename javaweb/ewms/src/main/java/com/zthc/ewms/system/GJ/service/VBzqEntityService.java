package com.zthc.ewms.system.GJ.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.GJ.entity.VBzqEntity;
import com.zthc.ewms.system.GJ.entity.VBzqEntityCondition;
import com.zthc.ewms.system.GJ.service.guard.VBzqEntityServiceGuard;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("vBzqEntityService")
public class VBzqEntityService extends VBzqEntityServiceGuard {
    /**
     * 获取保质期告警查询列表
     * @param condition
     * @return
     * throws ParseException
     */
    public LayuiPage<VBzqEntity> BzqList(VBzqEntity obj, VBzqEntityCondition condition,String maturity)throws ParseException {
        return this.dao.BzqList(obj,condition,maturity);
    }

    public List<VBzqEntity> BzqExcelList(VBzqEntity obj, VBzqEntityCondition condition, String maturity)throws ParseException {
        return this.dao.BzqExcelList(obj,condition,maturity);
    }

    /**
     * 获取单条数据
     *
     * @return
     */
    public VBzqEntity getOne(VBzqEntity obj) {
        return this.dao.getOne(obj);
    }
}
