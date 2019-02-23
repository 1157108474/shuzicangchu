package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.order.Order;
import com.zthc.ewms.sheet.entity.order.OrderDetails;
import com.zthc.ewms.sheet.entity.order.OrderHistory;
import com.zthc.ewms.sheet.entity.query.VCgddEntity;
import com.zthc.ewms.sheet.entity.query.VCgddEntityCondition;
import com.zthc.ewms.sheet.service.guard.SheetCGServiceGuard;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service
public class SheetCGService extends SheetCGServiceGuard {

    /**
     * 获取采购订单通用调用列表
     *
     * @param condition
     * @return
     */
    public LayuiPage<Order> generalList(SheetCGCondition condition) {
        return this.dao.generalList(condition);
    }

    /**
     * 获取物资接收单管理列表
     *
     * @param condition
     * @return throws ParseException
     */
    public LayuiPage<ManageOrder> manageOrderList(ManageOrder obj, SheetCGCondition condition, Integer userId,
                                                  String begin, String end) throws ParseException {
        return this.dao.manageOrderList(obj, condition, userId, begin, end);
    }

    public JSONObject isOrderTrue(Integer id) {
        JSONObject ret = new JSONObject();
        Integer status = this.dao.isOrderTrue(id);
        if (status == 1) {
            ret.put("code", true);
        } else {
            ret.put("code", false);
            ret.put("message", "单据未添加明细，请重新填写再提交！");
        }
        return ret;
    }


    /**
     * 获取物资接收单新增明细列表
     *
     * @param condition
     * @return
     */
    public LayuiPage<OrderDetails> detailsList(OrderDetails details, SheetCGCondition condition) {
        return this.dao.detailsList(details, condition);
    }

    /**
     * 获取物资接收单明细列表
     *
     * @param condition
     * @return
     */
    public LayuiPage<SheetJSDDetails> detailsJSDList(SheetCGCondition condition) {
        return this.dao.detailsJSDList(condition);
    }

    /**
     * 获取物资接收单明细日志列表
     *
     * @param relationGuid
     * @param condition
     * @return
     */
    public LayuiPage<OrderHistory> orderHistoryList(String relationGuid, SheetCGCondition condition) {
        return this.dao.orderHistoryList(relationGuid, condition);
    }

    /**
     * 获取采购订单查询列表方法
     *
     * @param obj
     * @param condition
     * @return
     */
    public LayuiPage<VCgddEntity> CGDDList(VCgddEntity obj, VCgddEntityCondition condition, Integer userId,String startTime,
                                           String endTime)throws ParseException {
        return this.dao.CGDDList(obj, condition, userId,startTime,endTime);
    }
    /**
     * 获取采购订单查询列表方法
     *
     * @param obj
     * @param condition
     * @return
     */
    public List<VCgddEntity> listCGDD(VCgddEntity obj, VCgddEntityCondition condition, Integer userId, String startTime,
                                      String endTime){
        return this.dao.listCGDD(obj, condition, userId,startTime,endTime);
    }


    /**
     * 修改物资接收单
     *
     * @param sheet
     */
    @Transactional
    public void editSheet(Sheet sheet) {
        this.dao.editSheet(sheet);
    }

    @Transactional
    public void savePlanOther(SheetDetail detail) {
        this.dao.savePlanOther(detail);
    }


}
