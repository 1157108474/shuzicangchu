package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.dao.VCgjhEntityDao;
import com.zthc.ewms.sheet.entity.query.VCgjhEntity;
import com.zthc.ewms.sheet.entity.query.VCgjhEntityCondition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Service("vCgjhEntityService")
public class VCgjhEntityService {

    @Resource(name = "vCgjhEntityDao")
    public VCgjhEntityDao dao;

    public LayuiPage<VCgjhEntity> CGJHList(VCgjhEntity obj, VCgjhEntityCondition condition, Integer userId, String
            startTime1,String startTime2, String endTime1, String endTime2) throws ParseException {
        return this.dao.CGJHList(obj, condition, userId, startTime1, startTime2, endTime1, endTime2);
    }
    public List<VCgjhEntity> excelCGJHList(VCgjhEntity obj, VCgjhEntityCondition condition, String
            startTime1, String startTime2, String endTime1, String endTime2) throws ParseException {
        return this.dao.excelCGJHList(obj, condition,startTime1, startTime2, endTime1, endTime2);
    }

    public VCgjhEntity getOne(Integer id){
        return this.dao.getOne(id);
    }
}
