package com.zthc.ewms.system.dept.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.dept.controller.guard.DepartControllerGuard;

import com.zthc.ewms.system.dept.dao.guard.CompanyDaoGuard;
import com.zthc.ewms.system.dept.dao.guard.OrganizationDaoGuard;
import com.zthc.ewms.system.dept.entity.guard.Company;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.entity.guard.DepartCondition;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import com.zthc.ewms.system.dept.service.OrganizationService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import drk.system.Log;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/dept")
public class DepartController extends DepartControllerGuard {
    private final static Log log = Log.getLog("com.system.dept.controller.DepartController");
    @Resource(name = "organizationService")
    public OrganizationService organizationService;

    /**
     * ��ȡ�б�����
     */
    @RequestMapping("/listDepart.json")
    @ResponseBody
    public String listDepart(Depart obj) {
        //HttpResponse<String> ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ȡ��֯����ʧ�ܣ�", obj.toString());
        String ret = "";
        try {
            List<Map<String, Object>> departs = this.service.listDepart(obj);
            //ת����json��
            ret = new ObjectMapper().writeValueAsString(departs);
            //String json = this.service.listDepart(obj);
            //ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ȡ��֯���ݳɹ���", json);
        } catch (Exception e) {
            log.error("��ȡ��֯�����б����������" + obj.toString());
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �����¼
     */
    @RequestMapping(value = "/saveDepart.json")
    @ResponseBody
    public HttpResponse<Depart> saveDepart(@ModelAttribute("de") Depart obj, Integer parentId, DepartCondition condition, HttpServletRequest request) {
        HttpResponse<Depart> ret = new HttpResponse(HttpResponse.Status.FAILURE, "����ʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        if (0 == obj.getType()) {
            //��˾
            BaseLocal local = CompanyDaoGuard.getThreadLocal();
            local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
            CompanyDaoGuard.setThreadLocal(local);
            try {
                Company company = this.companyTransformation(obj, parentId);
                List<Company> companyList = this.companyService.listCompanyCode(company);
                if (companyList.size() == 0) {
                    this.companyService.saveCompany(company);
                } else {
                    ret.setMessage("��֯��������ظ�!");
                    return ret;
                }
            } catch (Exception e) {
                log.error("������֯����:" + obj.getName() + "ʧ�ܣ�");
                log.errorPrintStacktrace(e);
            }
        } else {
            //��֯
            BaseLocal local = OrganizationDaoGuard.getThreadLocal();
            local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
            OrganizationDaoGuard.setThreadLocal(local);
            try {
                Organization organization = this.organizationTransformation(obj, parentId);
                List<Organization> organizations = this.organizationService.listOrganizationCode(organization);
                if (organizations.size() == 0) {
                    this.organizationService.saveOrganization(organization);
                } else {
                    ret.setMessage("��֯��������ظ�!");
                    return ret;
                }
            } catch (Exception e) {
                log.error("������֯����:" + obj.getName() + "ʧ�ܣ�");
                log.errorPrintStacktrace(e);
            }
        }
        //���÷��ز���
        ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�����ɹ���", obj);
        //��־
        this.logService.addSystemLog(0, SystemLogEnums.LogObject.ORGANIZATION.getCode(),
                SystemLogEnums.LogAction.ADD.getCode(), "������֯����:" + obj.getName(), userIp, (int) userId);
        return ret;
    }

    /**
     * ���·���
     */
    @RequestMapping(value = "/updateDepart.json")
    @ResponseBody
    public HttpResponse<Depart> updateDepart(@ModelAttribute("de") Depart obj, Integer parentId, DepartCondition condition, HttpServletRequest request) {
        HttpResponse<Depart> ret = new HttpResponse(HttpResponse.Status.FAILURE, "�޸�ʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        if (0 == obj.getType()) {
            //��˾
            BaseLocal local = CompanyDaoGuard.getThreadLocal();
            local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
            CompanyDaoGuard.setThreadLocal(local);
            try {
                Company company = this.companyTransformation(obj, parentId);
                List<Company> companyList = this.companyService.listCompanyCode(company);
                if (companyList.size() == 0) {
                    this.companyService.updateCompany(company);
                } else {
                    ret.setMessage("��֯��������ظ�!");
                    return ret;
                }
            } catch (Exception e) {
                log.error("�޸���֯����:" + obj.getName() + "ʧ�ܣ�");
                log.errorPrintStacktrace(e);
            }
        } else {
            //��֯
            BaseLocal local = OrganizationDaoGuard.getThreadLocal();
            local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
            OrganizationDaoGuard.setThreadLocal(local);
            try {
                Organization organization = this.organizationTransformation(obj, parentId);
                List<Organization> organizations = this.organizationService.listOrganizationCode(organization);
                if (organizations.size() == 0) {
                    this.organizationService.updateOrganization(organization);
                } else {
                    ret.setMessage("��֯��������ظ�!");
                    return ret;
                }
            } catch (Exception e) {
                log.error("�޸���֯����:" + obj.getName() + "ʧ�ܣ�");
                log.errorPrintStacktrace(e);
            }
        }
        ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�޸ĳɹ���", obj);
        //��־
        this.logService.addSystemLog(0, SystemLogEnums.LogObject.ORGANIZATION.getCode(),
                SystemLogEnums.LogAction.EDIT.getCode(), "�޸���֯����:" + obj.getName(), userIp, (int) userId);
        return ret;
    }

    /**
     * ƴװ��˾ʵ����
     *
     * @param depart
     * @param parentId
     * @return
     */
    private Company companyTransformation(Depart depart, Integer parentId) {

        Company company = new Company();
        company.setId(depart.getId());
        company.setCode(depart.getCode());
        company.setName(depart.getName());
        company.setShortName(depart.getName());
        company.setType(depart.getType());
        company.setSort(depart.getSort());
        company.setStatus(depart.getStatus());
        company.setMemo(depart.getMemo());
        company.setParentId(parentId);
        company.setLevelCount(depart.getLevelCount());
        company.setLevelCode(depart.getLevelCode());
        company.setZtId(depart.getZtId());
        return company;
    }

    /**
     * ƴװ����ʵ����
     *
     * @param depart
     * @param parentId
     * @return
     */
    private Organization organizationTransformation(Depart depart, Integer parentId) {

        Organization organization = new Organization();
        organization.setId(depart.getId());
        organization.setCode(depart.getCode());
        organization.setName(depart.getName());
        organization.setType(depart.getType());
        organization.setSort(depart.getSort());
        organization.setStatus(depart.getStatus());
        organization.setMemo(depart.getMemo());
        organization.setParentId(parentId);
        organization.setLevelCount(depart.getLevelCount());
        organization.setLevelCode(depart.getLevelCode());
        organization.setZtId(depart.getZtId());
        organization.setCompanyId(depart.getCompanyId());
        return organization;
    }

    /**
     * ����ɾ������
     */
    @RequestMapping(value = "/delDepart.json")
    @ResponseBody
    public HttpResponse<DepartCondition> delDepart(@ModelAttribute("de") Depart obj, DepartCondition condition, HttpServletRequest request) {
        HttpResponse<DepartCondition> ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ��ʧ�ܣ�", condition);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        try {
            if (0 == obj.getType()) {
                //��˾
                this.companyService.delCompany(obj.getId());
            } else {
                //��֯
                this.organizationService.delOrganization(obj.getId());
            }
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ���ɹ���", condition);
            //��־
            logService.addSystemLog(0, SystemLogEnums.LogObject.ORGANIZATION.getCode(),
                    SystemLogEnums.LogAction.DELETE.getCode(), "ɾ������:ids=" + condition.getIds().toString(),
                    userIp, (int) userId);
        } catch (Exception e) {
            log.error("ɾ��ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
    * ����ɾ������
    */
    @RequestMapping(value = "/depe.json")
    @ResponseBody
    public HttpResponse<Depart> depe( HttpServletRequest request) {
        HttpResponse<Depart> ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ��ʧ�ܣ�", null);
        try {

            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ���ɹ���", null);
        } catch (Exception e) {
            log.error("ɾ��ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }



    /**
     * �򿪹�����֯����ҳ��
     */
    @RequestMapping("/openPublicDepart.htm")
    public String openPublicDepart() {
        return "system/dept/publicDepart";
    }

    /**
     * �����б�����
     */
    @RequestMapping("/publicDepart.json")
    @ResponseBody
    public String publicDepart(Depart obj) {
        String ret = "";
        //HttpResponse<String> ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ȡ��֯����ʧ�ܣ�", obj.toString());
        try {
            List<Map<String, Object>> departs = this.service.listDepart(obj);
            //ת����json��
            ret = new ObjectMapper().writeValueAsString(departs);
//            String json = this.service.listDepart(obj);
//            ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ȡ��֯���ݳɹ���", json);
        } catch (Exception e) {
            log.error("��ȡ��֯�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ������֯�б�����
     */
    @RequestMapping("/publicOrganization.json")
    @ResponseBody
    public String publicOrganization(Depart obj) {
        // HttpResponse<String> ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ȡ��֯����ʧ�ܣ�", obj.toString());
        String ret = "";
        try {
            List<Map<String, Object>> departs = this.service.listDepart(obj);
            //ת����json��
            ret = new ObjectMapper().writeValueAsString(departs);
//            String json = this.service.listDepart(obj);
//            ret = new HttpResponse(HttpResponse.Status.FAILURE, "��ȡ��֯���ݳɹ���", json);
        } catch (Exception e) {
            log.error("��ȡ��֯�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}
