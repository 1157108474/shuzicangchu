package com.zthc.ewms.sheet.service;

import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.service.guard.SheetJCRKServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class SheetJCRKService extends SheetJCRKServiceGuard {


    /**
     * �޸ļĴ�������ⵥ
     *
     * @param sheetDetail
     */
    @Transactional
    public int editSheetDatailed(SheetDetail sheetDetail) {
        return this.dao.editSheetDatailed(sheetDetail);
    }
    /**
     * �޸ļĴ�������ⵥ
     *
     * @param sheet
     */
    @Transactional
    public int editSheet(Sheet sheet) {
        return this.dao.editSheet(sheet);
    }
    /**
     * ���ɵ��ݱ���
     */
    @Transactional
    public String getCode(String prefix, float flag) {
        try {
            return this.sheetDao.getCode(prefix, flag);
        } catch (SQLException e) {
            return null;
        }
    }




}
