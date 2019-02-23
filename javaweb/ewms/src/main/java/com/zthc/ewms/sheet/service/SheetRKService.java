package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.rk.RkDetail;
import com.zthc.ewms.sheet.entity.rk.RkDetails;
import com.zthc.ewms.sheet.entity.rk.RkSubDetail;
import com.zthc.ewms.sheet.entity.rk.RkdList;
import com.zthc.ewms.sheet.entity.zr.SheetZRD;
import com.zthc.ewms.sheet.entity.zr.ZrDetails;
import com.zthc.ewms.sheet.service.guard.SheetRKServiceGuard;
import net.sf.json.JSONObject;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

@Service
public class SheetRKService extends SheetRKServiceGuard {

    /**
     * 获取物资接收单列表
     *
     * @param sheet
     * @param condition
     * @return
     */
    public LayuiPage<SheetJSD> generalJSDList(Sheet sheet, SheetCondition condition) {
        return this.dao.generalJSDList(sheet, condition);
    }

    /**
     * 获取物资入库单新增明细列表
     *
     * @param rkDetail
     * @param condition
     * @return
     */
    public LayuiPage<RkDetail> detailsList(RkDetail rkDetail, SheetRKCondition condition) {
        return this.dao.detailsList(rkDetail, condition);
    }

    /**
     * 物资入库获取入库单列表
     *
     * @param obj
     * @param condition
     * @return
     */
    public LayuiPage<RkdList> generalRKDList(RkdList obj, SheetRKCondition condition) {
        return this.dao.generalRKDList(obj, condition);
    }

    /**
     * 物资入库获取已分配明细列表
     *
     * @param subDetail
     * @param condition
     * @return
     */
    public LayuiPage<RkSubDetail> getSonDetail(RkSubDetail subDetail, SheetRKCondition condition) {
        return this.dao.getSonDetail(subDetail, condition);
    }

    /**
     * 获取物资入库单页面明细列表
     *
     * @param obj
     * @param condition
     * @return
     */
    public LayuiPage<RkDetails> rkDetailList(RkDetails obj, SheetRKCondition condition) {
        return this.dao.rkDetailList(obj, condition);
    }

    /**
     * 获取物资杂入单页面明细列表
     *
     * @param obj
     * @param condition
     * @return
     */
    public LayuiPage<ZrDetails> zrDetailList(ZrDetails obj, SheetRKCondition condition) {
        return this.dao.zrDetailList(obj, condition);
    }

    //增改
    @Transactional
    public void saveSheet(SheetRK obj, String menuCode, String type, String userCode, SheetRKCondition condition) {
        String code = this.getCode(type, 0F);
        if (code == null) {
            throw new RuntimeException("单据保存失败：生成单据编码失败");
        } else {
            obj.setCode(code);
            Map<String, Object> map = processManageService.startProcessInstance(menuCode, userCode);
            if ("启动成功".equals(map.get("success"))) {
                obj.setRouteId(Integer.parseInt(map.get("processInstanceId").toString()));
                obj.setTaskId(map.get("taskId").toString());
                this.dao.saveSheet(obj, condition);
                obj.setUrl(SheetRK.URL.getByType(type).getValue() + obj.getId());
            } else {
                throw new RuntimeException("单据保存失败：启动工作流失败--" + map.get("success") + "");
            }
        }
    }

    //增改
    @Transactional
    public void saveSheet(SheetRK obj) {
        this.dao.saveSheet(obj, null);
    }

    /**
     * 修改物资入库单
     *
     * @param sheet
     */
    @Transactional
    public void editSheet(SheetRK sheet) {
        this.dao.editSheet(sheet);
    }

    /**
     * 生成单据编码
     */
    @Transactional
    public String getCode(String prefix, float flag) {
        try {
            return this.sheetDao.getCode(prefix, flag);
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * 获取明细库房库位
     *
     * @param code
     * @param sid
     * @return
     */
    public SheetStock getOriginalLocation(String code, Integer sid) {
        return this.dao.getOriginalLocation(code, sid);
    }

    public JSONObject isEqualsCount(Integer sheetId) {

        JSONObject ret = new JSONObject();
        Integer status = this.dao.isEqualsCount(sheetId);
        if (status == 0) {
            ret.put("code", false);
            ret.put("message", "有明细没有分配库位，不能提交！");
        } else {
            ret.put("code", true);
        }
        return ret;
    }

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 删除单据
     *
     * @param id
     */
    @Transactional
    public void deleteSheet(Integer id) {

        this.dao.deleteSheet(id);
        SheetRKD sheet = this.sheetDao.getRkSheetOne(id);
        runtimeService.deleteProcessInstance(sheet.getRouteid() + "", "");//删除流程
        this.sheetRKDETAILDao.deleteDetailBySheetId(id);
        this.sheetRKDETAILDao.deleteSonDetailByDetailId(id);

    }

    /**
     * 删除单据
     *
     * @param id
     */
    @Transactional
    public void deleteZrSheet(Integer id) {

        this.dao.deleteSheet(id);
        SheetZRD sheet = this.sheetDao.getZrSheetOne(id);
        runtimeService.deleteProcessInstance(sheet.getRouteid() + "", "");//删除流程
        this.sheetRKDETAILDao.deleteDetailBySheetId(id);
        this.sheetRKDETAILDao.deleteSonDetailByDetailId(id);

    }

    public LayuiPage<SheetRKD> manageRkList(SheetRKD obj, SheetCondition condition, Integer userId, String begin,
                                            String end)
            throws ParseException {
        return this.dao.manageRkList(obj, condition, userId, begin, end);
    }

    public LayuiPage<SheetZRD> manageZrList(SheetZRD obj, SheetCondition condition, Integer userId, String begin,
                                            String end)
            throws ParseException {
        return this.dao.manageZrList(obj, condition, userId, begin, end);
    }


}
