package com.zthc.ewms.system.GJ.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.GJ.entity.VBzqEntity;
import com.zthc.ewms.system.GJ.entity.VBzqEntityCondition;
import com.zthc.ewms.system.GJ.entity.VSxxEntity;
import com.zthc.ewms.system.GJ.entity.VSxxEntityCondition;
import com.zthc.ewms.system.GJ.service.guard.VBzqEntityServiceGuard;
import com.zthc.ewms.system.GJ.service.guard.VSxxEntityServiceGuard;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("vSxxEntityService")
public class VSxxEntityService extends VSxxEntityServiceGuard {
    /**
     * ��ȡ�����ڸ澯��ѯ�б�
     * @param condition
     * @return
     * throws ParseException
     */
    public LayuiPage<VSxxEntity> SxxList(VSxxEntity obj, VSxxEntityCondition condition, String gjtype)throws ParseException {
        return this.dao.SxxList(obj,condition,gjtype);
    }

    public List<VSxxEntity> SxxExcelList(VSxxEntity obj, VSxxEntityCondition condition, String gjtype)throws ParseException {
        return this.dao.SxxExcelList(obj,condition,gjtype);
    }
    /**
     * ��ȡ��������
     *
     * @return
     */
    public VSxxEntity getOne(VSxxEntity obj) {
        return this.dao.getOne(obj);
    }
}
