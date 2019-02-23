package com.zthc.ewms.sheet.service;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.dao.MesDao;
import com.zthc.ewms.sheet.entity.mes.MesCk;
import com.zthc.ewms.sheet.entity.mes.MesCkCondition;
import com.zthc.ewms.sheet.entity.mes.MesRk;
import com.zthc.ewms.sheet.entity.mes.MesRkCondition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MesService{

    @Resource(name = "mesDao")
    private MesDao dao;

    public LayuiPage<MesRk> queryMesRkList(MesRk obj, MesRkCondition condition){
        return this.dao.queryMesRkList(obj,condition);
    }

    public LayuiPage<MesCk> queryMesCkList(MesCk obj, MesCkCondition condition){
        return this.dao.queryMesCkList(obj,condition);
    }

}
