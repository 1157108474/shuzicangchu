package com.zthc.ewms.task;


import com.zthc.ewms.sheet.service.SheetCKService;
import com.zthc.ewms.sheet.service.SheetService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {
    @Autowired
    public SheetCKService sheetCKService;
    @Autowired
    public SheetService sheetService;
    /**
     * ����ʱִ��һ�Σ�֮��ÿ��ʮ����ִ��һ��
     */
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void job() {
        System.out.println(new DateTime() + "==��ʱ���������ɹ�==");
        //����������³ɱ�
        sheetCKService.renewalCostTask();
        //�����˿���³ɱ�
        sheetService.renewalCostTask();
    }
}
