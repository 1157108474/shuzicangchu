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
     * 启动时执行一次，之后每隔十分钟执行一次
     */
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void job() {
        System.out.println(new DateTime() + "==定时任务启动成功==");
        //批量出库更新成本
        sheetCKService.renewalCostTask();
        //批量退库更新成本
        sheetService.renewalCostTask();
    }
}
