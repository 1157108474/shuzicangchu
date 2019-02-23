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
     * ��ȡ���ʽ��յ��б�
     *
     * @param sheet
     * @param condition
     * @return
     */
    public LayuiPage<SheetJSD> generalJSDList(Sheet sheet, SheetCondition condition) {
        return this.dao.generalJSDList(sheet, condition);
    }

    /**
     * ��ȡ������ⵥ������ϸ�б�
     *
     * @param rkDetail
     * @param condition
     * @return
     */
    public LayuiPage<RkDetail> detailsList(RkDetail rkDetail, SheetRKCondition condition) {
        return this.dao.detailsList(rkDetail, condition);
    }

    /**
     * ��������ȡ��ⵥ�б�
     *
     * @param obj
     * @param condition
     * @return
     */
    public LayuiPage<RkdList> generalRKDList(RkdList obj, SheetRKCondition condition) {
        return this.dao.generalRKDList(obj, condition);
    }

    /**
     * ��������ȡ�ѷ�����ϸ�б�
     *
     * @param subDetail
     * @param condition
     * @return
     */
    public LayuiPage<RkSubDetail> getSonDetail(RkSubDetail subDetail, SheetRKCondition condition) {
        return this.dao.getSonDetail(subDetail, condition);
    }

    /**
     * ��ȡ������ⵥҳ����ϸ�б�
     *
     * @param obj
     * @param condition
     * @return
     */
    public LayuiPage<RkDetails> rkDetailList(RkDetails obj, SheetRKCondition condition) {
        return this.dao.rkDetailList(obj, condition);
    }

    /**
     * ��ȡ�������뵥ҳ����ϸ�б�
     *
     * @param obj
     * @param condition
     * @return
     */
    public LayuiPage<ZrDetails> zrDetailList(ZrDetails obj, SheetRKCondition condition) {
        return this.dao.zrDetailList(obj, condition);
    }

    //����
    @Transactional
    public void saveSheet(SheetRK obj, String menuCode, String type, String userCode, SheetRKCondition condition) {
        String code = this.getCode(type, 0F);
        if (code == null) {
            throw new RuntimeException("���ݱ���ʧ�ܣ����ɵ��ݱ���ʧ��");
        } else {
            obj.setCode(code);
            Map<String, Object> map = processManageService.startProcessInstance(menuCode, userCode);
            if ("�����ɹ�".equals(map.get("success"))) {
                obj.setRouteId(Integer.parseInt(map.get("processInstanceId").toString()));
                obj.setTaskId(map.get("taskId").toString());
                this.dao.saveSheet(obj, condition);
                obj.setUrl(SheetRK.URL.getByType(type).getValue() + obj.getId());
            } else {
                throw new RuntimeException("���ݱ���ʧ�ܣ�����������ʧ��--" + map.get("success") + "");
            }
        }
    }

    //����
    @Transactional
    public void saveSheet(SheetRK obj) {
        this.dao.saveSheet(obj, null);
    }

    /**
     * �޸�������ⵥ
     *
     * @param sheet
     */
    @Transactional
    public void editSheet(SheetRK sheet) {
        this.dao.editSheet(sheet);
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

    /**
     * ��ȡ��ϸ�ⷿ��λ
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
            ret.put("message", "����ϸû�з����λ�������ύ��");
        } else {
            ret.put("code", true);
        }
        return ret;
    }

    @Autowired
    private RuntimeService runtimeService;

    /**
     * ɾ������
     *
     * @param id
     */
    @Transactional
    public void deleteSheet(Integer id) {

        this.dao.deleteSheet(id);
        SheetRKD sheet = this.sheetDao.getRkSheetOne(id);
        runtimeService.deleteProcessInstance(sheet.getRouteid() + "", "");//ɾ������
        this.sheetRKDETAILDao.deleteDetailBySheetId(id);
        this.sheetRKDETAILDao.deleteSonDetailByDetailId(id);

    }

    /**
     * ɾ������
     *
     * @param id
     */
    @Transactional
    public void deleteZrSheet(Integer id) {

        this.dao.deleteSheet(id);
        SheetZRD sheet = this.sheetDao.getZrSheetOne(id);
        runtimeService.deleteProcessInstance(sheet.getRouteid() + "", "");//ɾ������
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
