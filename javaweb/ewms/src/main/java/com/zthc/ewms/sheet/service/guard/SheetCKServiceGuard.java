package com.zthc.ewms.sheet.service.guard;

import com.zthc.ewms.sheet.dao.SheetCKDao;
import com.zthc.ewms.sheet.entity.ck.CK;
import com.zthc.ewms.sheet.entity.ck.VCKList;
import com.zthc.ewms.sheet.entity.guard.SheetCK;
import com.zthc.ewms.sheet.entity.guard.SheetCKDETAIL;
import com.zthc.ewms.sheet.entity.guard.SheetCKDETAILCondition;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class SheetCKServiceGuard {
    @Resource(name = "sheetCKDao")
    public SheetCKDao dao;

    /**************************  基础方法   ***************************/
    @Transactional
    public VCKList getVCKListOne(Integer id) {
        return this.dao.getVCKListOne(id);
    }
    @Transactional
    public SheetCK getSheetCKOne(Integer id) {
        return this.dao.getSheetCKOne(id);
    }



    //删
    @Transactional
    public void delSheetCK(Integer id){
        this.dao.delSheetCK(id);
        this.dao.delSheetDetails(id);
    }
    /**************************  基础结束   ***************************/
    //list条件列表

    //update

    //delete

    /**
     * 删除明细方法
     **/
    @Transactional
    public void delDetails(SheetCKDETAILCondition condition) {
        int i = this.dao.delDetails(condition);
        if (condition.getIds().length != i) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public List<SheetCKDETAIL> listSheetCKdetail(Integer sheetId) {
        return this.dao.listSheetCKdetail(sheetId);
    }


    @Transactional
    public void updateSheetCKdetail(SheetCKDETAIL obj) {
        this.dao.updateSheetCKdetail(obj);
    }

}