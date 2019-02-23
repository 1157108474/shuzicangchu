package com.zthc.ewms.sheet.service;

import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.service.guard.SheetZCServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sheetZCService")
public class SheetZCService extends SheetZCServiceGuard {


    /**
     * 修改物资杂出单
     *
     * @param sheetDetail
     */
    @Transactional
    public int editSheetDatailed(SheetDetail sheetDetail) {
        return this.dao.editSheetDatailed(sheetDetail);
    }

}
