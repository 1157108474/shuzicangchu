package com.zthc.ewms.system.formTemplateManage.service;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.formTemplateManage.dao.FormTemplateDao;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FormTemplateService {

    @Resource(name = "formTemplateDao")
    private FormTemplateDao formTemplateDao;
    @Transactional
    public List<FormTemplate> getFromTemBydicID(String dicID) {
        return formTemplateDao.getFromTemBydicID(dicID);
    }
    public List<FormTemplate> getFromTemBydicIDCk(String dicID) {
        return formTemplateDao.getFromTemBydicID(dicID);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public LayuiPage<FormTemplate> getFromTemplateList(String dicID) {
        return formTemplateDao.getFromTemplateList(dicID);
    }

    @Transactional
    public void save(FormTemplate obj) {
        formTemplateDao.save(obj);
    }
    @Transactional
    public FormTemplate getFromTemByID(int id) {
        return formTemplateDao.getFromTemByID(id);
    }

    @Transactional
    public void edit(FormTemplate obj) {
        formTemplateDao.edit(obj);
    }
    @Transactional
    public int deleteById(Integer[] id) {
        return formTemplateDao.deleteById(id);
    }
    @Transactional
    public List<FormTemplate> getFromTemByMenuStatus(String menuCode, int i) {
        return formTemplateDao.getFromTemByMenuStatus(menuCode,i);
    }
    @Transactional
    public List<FormTemplate> getFromTemByMenuStatusNothis(int id,String menuCode, int i) {
        return formTemplateDao.getFromTemByMenuStatusNothis(id,menuCode,i);
    }
    @Transactional
    public List<FormTemplate> getFromTemByMenuCode(String menuCode) {
        return formTemplateDao.getFromTemByMenuCode(menuCode);
    }
    @Transactional
    public List<FormTemplate> getFromTemByCardStatusNothis(int id, String card, int i) {
        return formTemplateDao.getFromTemByCardStatusNothis(id,card,i);
    }

    public List<FormTemplate> getFromTemByCardStatus(String formTemCard, int i) {
        return formTemplateDao.getFromTemByCardStatus(formTemCard,i);
    }
}
