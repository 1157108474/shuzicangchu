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
     * 获取列表数据
     */
    @RequestMapping("/listDepart.json")
    @ResponseBody
    public String listDepart(Depart obj) {
        //HttpResponse<String> ret = new HttpResponse(HttpResponse.Status.FAILURE, "获取组织数据失败！", obj.toString());
        String ret = "";
        try {
            List<Map<String, Object>> departs = this.service.listDepart(obj);
            //转换成json串
            ret = new ObjectMapper().writeValueAsString(departs);
            //String json = this.service.listDepart(obj);
            //ret = new HttpResponse(HttpResponse.Status.FAILURE, "获取组织数据成功！", json);
        } catch (Exception e) {
            log.error("获取组织数据列表出错！参数：" + obj.toString());
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 保存记录
     */
    @RequestMapping(value = "/saveDepart.json")
    @ResponseBody
    public HttpResponse<Depart> saveDepart(@ModelAttribute("de") Depart obj, Integer parentId, DepartCondition condition, HttpServletRequest request) {
        HttpResponse<Depart> ret = new HttpResponse(HttpResponse.Status.FAILURE, "新增失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        if (0 == obj.getType()) {
            //公司
            BaseLocal local = CompanyDaoGuard.getThreadLocal();
            local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
            CompanyDaoGuard.setThreadLocal(local);
            try {
                Company company = this.companyTransformation(obj, parentId);
                List<Company> companyList = this.companyService.listCompanyCode(company);
                if (companyList.size() == 0) {
                    this.companyService.saveCompany(company);
                } else {
                    ret.setMessage("组织机构编号重复!");
                    return ret;
                }
            } catch (Exception e) {
                log.error("新增组织机构:" + obj.getName() + "失败！");
                log.errorPrintStacktrace(e);
            }
        } else {
            //组织
            BaseLocal local = OrganizationDaoGuard.getThreadLocal();
            local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
            OrganizationDaoGuard.setThreadLocal(local);
            try {
                Organization organization = this.organizationTransformation(obj, parentId);
                List<Organization> organizations = this.organizationService.listOrganizationCode(organization);
                if (organizations.size() == 0) {
                    this.organizationService.saveOrganization(organization);
                } else {
                    ret.setMessage("组织机构编号重复!");
                    return ret;
                }
            } catch (Exception e) {
                log.error("新增组织机构:" + obj.getName() + "失败！");
                log.errorPrintStacktrace(e);
            }
        }
        //设置返回参数
        ret = new HttpResponse(HttpResponse.Status.SUCCESS, "新增成功！", obj);
        //日志
        this.logService.addSystemLog(0, SystemLogEnums.LogObject.ORGANIZATION.getCode(),
                SystemLogEnums.LogAction.ADD.getCode(), "新增组织机构:" + obj.getName(), userIp, (int) userId);
        return ret;
    }

    /**
     * 更新方法
     */
    @RequestMapping(value = "/updateDepart.json")
    @ResponseBody
    public HttpResponse<Depart> updateDepart(@ModelAttribute("de") Depart obj, Integer parentId, DepartCondition condition, HttpServletRequest request) {
        HttpResponse<Depart> ret = new HttpResponse(HttpResponse.Status.FAILURE, "修改失败！", obj);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        if (0 == obj.getType()) {
            //公司
            BaseLocal local = CompanyDaoGuard.getThreadLocal();
            local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
            CompanyDaoGuard.setThreadLocal(local);
            try {
                Company company = this.companyTransformation(obj, parentId);
                List<Company> companyList = this.companyService.listCompanyCode(company);
                if (companyList.size() == 0) {
                    this.companyService.updateCompany(company);
                } else {
                    ret.setMessage("组织机构编号重复!");
                    return ret;
                }
            } catch (Exception e) {
                log.error("修改组织机构:" + obj.getName() + "失败！");
                log.errorPrintStacktrace(e);
            }
        } else {
            //组织
            BaseLocal local = OrganizationDaoGuard.getThreadLocal();
            local.setCurrentUserId(userId); // 把当前操作人ID存入当前线程对象中
            OrganizationDaoGuard.setThreadLocal(local);
            try {
                Organization organization = this.organizationTransformation(obj, parentId);
                List<Organization> organizations = this.organizationService.listOrganizationCode(organization);
                if (organizations.size() == 0) {
                    this.organizationService.updateOrganization(organization);
                } else {
                    ret.setMessage("组织机构编号重复!");
                    return ret;
                }
            } catch (Exception e) {
                log.error("修改组织机构:" + obj.getName() + "失败！");
                log.errorPrintStacktrace(e);
            }
        }
        ret = new HttpResponse(HttpResponse.Status.SUCCESS, "修改成功！", obj);
        //日志
        this.logService.addSystemLog(0, SystemLogEnums.LogObject.ORGANIZATION.getCode(),
                SystemLogEnums.LogAction.EDIT.getCode(), "修改组织机构:" + obj.getName(), userIp, (int) userId);
        return ret;
    }

    /**
     * 拼装公司实体类
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
     * 拼装部门实体类
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
     * 批量删除方法
     */
    @RequestMapping(value = "/delDepart.json")
    @ResponseBody
    public HttpResponse<DepartCondition> delDepart(@ModelAttribute("de") Depart obj, DepartCondition condition, HttpServletRequest request) {
        HttpResponse<DepartCondition> ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除失败！", condition);
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());

        try {
            if (0 == obj.getType()) {
                //公司
                this.companyService.delCompany(obj.getId());
            } else {
                //组织
                this.organizationService.delOrganization(obj.getId());
            }
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "删除成功！", condition);
            //日志
            logService.addSystemLog(0, SystemLogEnums.LogObject.ORGANIZATION.getCode(),
                    SystemLogEnums.LogAction.DELETE.getCode(), "删除部门:ids=" + condition.getIds().toString(),
                    userIp, (int) userId);
        } catch (Exception e) {
            log.error("删除失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
    * 批量删除方法
    */
    @RequestMapping(value = "/depe.json")
    @ResponseBody
    public HttpResponse<Depart> depe( HttpServletRequest request) {
        HttpResponse<Depart> ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除失败！", null);
        try {

            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "删除成功！", null);
        } catch (Exception e) {
            log.error("删除失败！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }



    /**
     * 打开公用组织部门页面
     */
    @RequestMapping("/openPublicDepart.htm")
    public String openPublicDepart() {
        return "system/dept/publicDepart";
    }

    /**
     * 公用列表数据
     */
    @RequestMapping("/publicDepart.json")
    @ResponseBody
    public String publicDepart(Depart obj) {
        String ret = "";
        //HttpResponse<String> ret = new HttpResponse(HttpResponse.Status.FAILURE, "获取组织数据失败！", obj.toString());
        try {
            List<Map<String, Object>> departs = this.service.listDepart(obj);
            //转换成json串
            ret = new ObjectMapper().writeValueAsString(departs);
//            String json = this.service.listDepart(obj);
//            ret = new HttpResponse(HttpResponse.Status.FAILURE, "获取组织数据成功！", json);
        } catch (Exception e) {
            log.error("获取组织数据列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 公用组织列表数据
     */
    @RequestMapping("/publicOrganization.json")
    @ResponseBody
    public String publicOrganization(Depart obj) {
        // HttpResponse<String> ret = new HttpResponse(HttpResponse.Status.FAILURE, "获取组织数据失败！", obj.toString());
        String ret = "";
        try {
            List<Map<String, Object>> departs = this.service.listDepart(obj);
            //转换成json串
            ret = new ObjectMapper().writeValueAsString(departs);
//            String json = this.service.listDepart(obj);
//            ret = new HttpResponse(HttpResponse.Status.FAILURE, "获取组织数据成功！", json);
        } catch (Exception e) {
            log.error("获取组织数据列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}
