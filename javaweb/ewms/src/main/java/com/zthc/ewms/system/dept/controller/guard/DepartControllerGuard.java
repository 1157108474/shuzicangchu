package com.zthc.ewms.system.dept.controller.guard;


import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.resp.HttpResponse;

import com.zthc.ewms.system.dept.dao.guard.CompanyDaoGuard;
import com.zthc.ewms.system.dept.dao.guard.OrganizationDaoGuard;
import com.zthc.ewms.system.dept.entity.guard.Company;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.entity.guard.DepartCondition;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import com.zthc.ewms.system.dept.service.CompanyService;
import com.zthc.ewms.system.dept.service.DepartService;
import com.zthc.ewms.system.dept.service.OrganizationService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import drk.system.Log;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DepartControllerGuard {
    @Resource(name = "departService")
    public DepartService service;
    @Resource(name = "companyService")
    public CompanyService companyService;
    @Resource(name = "organizationService")
    public OrganizationService organizationService;
    @Resource(name = "logService")
    public LogService logService;
    private final static Log log = Log.getLog("com.system.dept.controller.guard.DepartControllerGuard");

    /**
     * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
     */
    @InitBinder("de")
    public void initDepartmentBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("de.");
    }

    /**
     * ����ҳ��
     */
    @RequestMapping(value = "/manageDepart.htm")
    public String manageDepart(@ModelAttribute("de") Depart obj, HttpServletRequest request,
                               HttpServletResponse response, Model model) {
        return "system/dept/manageDepart";
    }

    /**
     * ����
     */
    @RequestMapping(value = "/addDepart.json")
    @ResponseBody
    public HttpResponse addDepart(@ModelAttribute("de") Depart obj) {
        return editDepart(obj);
    }

    /**
     * �༭
     */
    @RequestMapping(value = "/editDepart.json")
    @ResponseBody
    public HttpResponse editDepart(@ModelAttribute("de") Depart obj) {
        Depart de = this.service.getDepartOne(obj.getId());
        HttpResponse ret = new HttpResponse(HttpResponse.Status.SUCCESS, "����ҳ�棡", de);
        return ret;
    }

    /**
     * �鿴
     */
    @RequestMapping(value = "/showDepart.json")
    @ResponseBody
    public HttpResponse showDepart(@ModelAttribute("de") Depart obj) {
        HttpResponse ret = null;
        Depart de = this.service.getDepartOne(obj.getId());
        ret = new HttpResponse(HttpResponse.Status.SUCCESS, "����ҳ�棡", de);
        return ret;
    }

    /**************************** ��ӷ��� ****************************************/
    // select�����б�


}

