package com.zthc.ewms.sheet.service;

import com.zthc.ewms.sheet.entity.guard.SheetCK;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//����junit spring�����ļ�
@ContextConfiguration("classpath:applicationContext.xml")
public class SheetCKServiceTest {
    @Autowired
    private SheetCKService sheetCKService;

    @Test
    public void asynRenewalCostTest() throws Exception {

        SheetCK sheetCK = sheetCKService.getSheetCKOne(1150);
        sheetCKService.asynRenewalCost(sheetCK,0);
    }

}