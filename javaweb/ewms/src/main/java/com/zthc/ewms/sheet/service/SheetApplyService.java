package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.Condition;
import com.zthc.ewms.sheet.entity.apply.ManageApply;
import com.zthc.ewms.sheet.service.guard.SheetApplyServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class SheetApplyService extends SheetApplyServiceGuard {


    /**
     * 公用列表查询方法
     * @param className
     * @param key
     * @param param
     * @param condition
     * @param <T>
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public <T> LayuiPage<T> publicDetails(String className, String key, Map<String, Object> param, Condition condition) {
        return this.dao.publicDetails( className, key,param, condition);
    }

    /**
     * 公用列表查询方法
     * @param className
     * @param param
     * @param condition
     * @param <T>
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public <T> List<T> listDetails(String className, Map<String, Object> param, Condition condition) {
        return this.dao.listDetails( className,param, condition);
    }

}
