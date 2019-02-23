package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.entity.query.VKcdetailEntity;
import com.zthc.ewms.sheet.entity.query.VKcdetailEntityCondition;
import com.zthc.ewms.sheet.service.guard.VKcdetailEntityServiceGuard;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("vKcdetailEntityService")
public class VKcdetailEntityService extends VKcdetailEntityServiceGuard {

    /**
     * ��ȡ���ʽ��յ������б�
     * @param condition
     * @return
     * throws ParseException
     */
    public LayuiPage<VKcdetailEntity> KCList(Integer userId,VKcdetailEntity obj, VKcdetailEntityCondition condition)throws ParseException {
        return this.dao.KCList(userId,obj,condition);
    }
    /**
     * ��ȡ��������
     *
     * @return
     */
    public VKcdetailEntity getOne(VKcdetailEntity obj) {
        return this.dao.getOne(obj);
    }

    /**
     * �������Excel
     */
    public List<VKcdetailEntity> KCExcelList(Integer userId,VKcdetailEntity obj, VKcdetailEntityCondition condition) {
        return this.dao.KCExcelList(userId,obj,condition);
    }
}
