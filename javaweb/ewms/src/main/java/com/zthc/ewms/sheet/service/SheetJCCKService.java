package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCKCondition;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetJCCKList;
import com.zthc.ewms.sheet.service.guard.SheetJCCKServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class SheetJCCKService extends SheetJCCKServiceGuard {

    /**
     * ��ȡ�Ĵ����ʳ��ⵥ������ϸ�б�
     *
     * @param sheetJCCKList
     * @param condition
     * @return
     */
    public LayuiPage<SheetJCCKList> detailsList(SheetJCCKList sheetJCCKList, SheetCKCondition condition) {
        return this.dao.detailsList(sheetJCCKList, condition);
    }

    /**
     * �޸ļĴ����ʳ��ⵥ
     *
     * @param sheetDetail
     */
    @Transactional
    public int editSheetDatailed(SheetDetail sheetDetail) {
        return this.dao.editSheetDatailed(sheetDetail);
    }

    /**
     * �޸ļĴ����ʳ��ⵥ
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
