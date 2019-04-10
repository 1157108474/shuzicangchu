package com.zthc.ewms.system.useDep.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.service.DepartService;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.spare.entity.guard.Sparepartscate;
import com.zthc.ewms.system.spare.service.SparepartscateService;
import com.zthc.ewms.system.useDep.entity.OfficesScopeCondition;
import com.zthc.ewms.system.useDep.entity.UseDep;
import com.zthc.ewms.system.useDep.entity.UseDepCondition;
import com.zthc.ewms.system.useDep.service.UseDepService;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/useDep")
public class UseDepController {

    @Resource(name = "useDepService")
    public UseDepService service;

    @Resource(name = "logService")
    public LogService logService;

    @Resource(name = "sparepartscateService")
    public SparepartscateService sparepartscateService;

    @Resource(name = "departService")
    public DepartService departService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.useDep.controller.UseDepController");
    }

     /*-------------------------------------------------��ת����-------------------------------------------------*/

    /**
     * ����ʹ�õ�λҳ��
     *
     * @return
     */
    @RequestMapping(value = "/manageUseDep.htm")
    public String manageUseDep() {
        return "system/useDep/manageUseDep";
    }

    /**
     * ���������޸�ҳ��
     *
     * @param useDep
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateUseDep.htm")
    public String updateUseDep(@ModelAttribute("UseDep") UseDep useDep, HttpServletRequest request,
                               HttpServletResponse response, Model model) {
        log.debug("����ʹ�õ�λ��������ҳ��,��ǰ����ΪupdateUseDep");
        if (StringUtils.isEmpty(useDep.getId())) {
            log.debug("����ʹ�õ�λ����ҳ��");
            return "system/useDep/updateUseDep";
        } else {
            log.debug("����ʹ�õ�λ�༭ҳ��,��ǰ���뵥λID:" + useDep.getId());
            UseDep ret = this.service.getOne(useDep.getId());
            Depart depart = this.departService.getDepartByZtId(ret.getZtId());

            model.addAttribute("depName", depart.getName());
            model.addAttribute("useDepValue", ret);
            log.debug("��ȡ����ʹ�õ�λ��Ϣ�ɹ�,��ת��ҳ��");

            return "system/useDep/updateUseDep";
        }
    }

    /**
     * ���Ϸ�Χ����ҳ��
     *
     * @param useDep
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/showMaterielRange.htm")
    public String showMaterielRange(@ModelAttribute("UseDep") UseDep useDep, OfficesScopeCondition condition,
                                    HttpServletRequest request,
                                    HttpServletResponse response, Model model) {
        log.debug("����ʹ�õ�λ-���Ϸ�Χ����ҳ��,��ǰ����ΪshowMaterielRange");
        model.addAttribute("useId", useDep.getId());
        model.addAttribute("ztId", useDep.getZtId());
        return "system/useDep/manageMaterielRange";
    }

    /**
     * ʹ�õ�λͨ�õ���ҳ��
     *
     * @param useDep
     * @param condition
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/generalUseDep.htm")
    public String generalUseDep(@ModelAttribute("UseDep") UseDep useDep, UseDepCondition condition,
                                HttpServletRequest request, HttpServletResponse response, Model model) {
        log.error("����ʹ�õ�λͨ��ҳ��,��ǰ����:generalUseDep");
        Integer uid = 0;
        Integer ztId = 0;
        if (!StringUtils.isEmpty(useDep.getId()) && 0 != useDep.getId()) {
            uid = useDep.getId();
        }
        if (!StringUtils.isEmpty(useDep.getZtId()) && 0 != useDep.getZtId()) {
            ztId = useDep.getZtId();
        }

        model.addAttribute("usedepId", uid);
        model.addAttribute("ztId", ztId);

        return "system/useDep/generalUseDep";
    }

    @RequestMapping(value = "/generalUseDepDepart.htm")
    public String generalUseDepDepart(@ModelAttribute("UseDep") UseDep useDep, HttpServletRequest request, Model
            model) {
        log.debug("����ʹ�õ�λ����ͨ��ҳ��,��ǰ����:generalUseDepDepart");
        Integer ztId = null == useDep.getZtId() ? 0 : useDep.getZtId();

        model.addAttribute("ztId", ztId);

        return "system/useDep/generalUseDepDepart";
    }

     /*-------------------------------------------------��������-------------------------------------------------*/

    /**
     * ��ȡʹ�õ�λ�б�
     *
     * @param useDep
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listUseDep.json")
    @ResponseBody
    public LayuiPage<UseDep> useDepList(@ModelAttribute("UseDep") UseDep useDep, UseDepCondition condition,
                                        HttpServletRequest request, HttpServletResponse response) {
        log.debug("�����ȡʹ�õ�λ����,��ǰ������:useDepList");
        LayuiPage<UseDep> ret = null;
        try {
            ret = service.listUseDep(useDep, condition);
        } catch (Exception e) {
            log.error("��ȡʹ�õ�λ�б�ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡͨ��ʹ�õ�λ�б�
     *
     * @param useDep
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listUseDepGeneral.json")
    @ResponseBody
    public LayuiPage<UseDep> generalList(@ModelAttribute("UseDep") UseDep useDep, UseDepCondition condition,
                                         HttpServletRequest request, HttpServletResponse response) {
        log.debug("�����ȡͨ��ʹ�õ�λ�б���,��ǰ������:generalList");
        LayuiPage<UseDep> ret = null;
        try {
            ret = this.service.listGeneral(useDep, condition);
        } catch (Exception e) {
            log.error("��ȡͨ��ʹ�õ�λ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡͨ��ʹ�õ�λ�����б�
     *
     * @param useDep
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listUseDepDepartGeneral.json")
    @ResponseBody
    public LayuiPage<UseDep> generalDepartList(@ModelAttribute("UseDep") UseDep useDep, UseDepCondition condition,
                                               HttpServletRequest request, HttpServletResponse response) {
        log.debug("�����ȡͨ��ʹ�õ�λ�����б���,��ǰ������:generalDepartList");
        LayuiPage<UseDep> ret = null;
        try {
            ret = this.service.listDepartGeneral(useDep, condition);
        } catch (Exception e) {
            log.error("��ȡͨ��ʹ�õ�λ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡʹ�õ�λ���Ϸ�Χ
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/officesScopeList.json")
    @ResponseBody
    public Map<String, Object> officesScopeList(UseDepCondition condition, HttpServletRequest request,
                                                HttpServletResponse response) {
        log.debug("�����ȡʹ�õ�λ���Ϸ�Χ����,��ǰ������:officesScopeList,��ǰʹ�õ�λID:" + condition.getId());
        Map<String, Object> args = new HashMap<>();
        try {

            //��ȡʹ�õ�λ��ѡ������Ϸ����б�
            List<Sparepartscate> officesScopeList = this.sparepartscateService.listOfficeScope(condition.getId());

            //��ȡ��ѡ������Ϸ����б�ID
            Integer[] ids = new Integer[officesScopeList.size()];

            if (officesScopeList.size() != 0) {
                for (int i = 0; i < officesScopeList.size(); i++) {
                    ids[i] = officesScopeList.get(i).getId();
                }
            }
            //��ȡ��ǰδѡ������Ϸ����б�
            List<Sparepartscate> sparepartscateList = this.sparepartscateService.getSpareList(ids);
            args.put("officesList", officesScopeList);
            args.put("scopeList", sparepartscateList);

        } catch (Exception e) {
            log.error("��ȡʹ�õ�λ���Ϸ�Χ����ʧ��");
            log.errorPrintStacktrace(e);
        }
        return args;
    }


    /**
     * �����޸ķ���
     *
     * @param useDep
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editUseDep.json")
    @ResponseBody
    public HttpResponse useDepEdit(@ModelAttribute("UseDep") UseDep useDep, HttpServletRequest request,
                                   HttpServletResponse response) {
        HttpResponse ret;
        log.debug("����ʹ�õ�λ���·���,useDepEdit");
        String str;
        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        //���뷽��
        try {
            if (!StringUtils.isEmpty(useDep.getId()) && 0 != useDep.getId()) {
                log.debug("����ʹ�õ�λ�޸ķ���,��ǰʹ�õ�λID:" + useDep.getId());
                int check = this.service.checkUseDep(useDep.getCode(), useDep.getName(), useDep.getId());
                if (check >= 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE, "����ʧ��,ӵ���ظ��ı��������", null);
                    return ret;
                } else {
                    useDep.setUpdater(userId);
                    useDep.setZtId(useDep.getOrganizationType());
                    this.service.editUseDep(useDep);
                    log.debug("�༭ʹ�õ�λ�ɹ�");
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�༭ʹ�õ�λ�ɹ�", null);
                    str = logService.objectToJson(useDep);
                    this.logService.addSystemLog(0, 2000, 200, "�༭ʹ�õ�λ:" + str,
                            userIp, userId);

                }
            } else {
                log.debug("����ʹ�õ�λ��������");
                int check = this.service.checkUseDep(useDep.getCode(), useDep.getName(), 0);
                if (check >= 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE, "����ʧ��,ӵ���ظ��ı��������", null);
                    return ret;
                } else {
                    useDep.setCreator(userId);
                    useDep.setDeleted(1);
                    useDep.setAddType(1);
                    useDep.setErpId(0);
                    useDep.setZtId(useDep.getOrganizationType());
                    this.service.addUseDep(useDep);
                    log.debug("����ʹ�õ�λ�ɹ�");
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS, "����ʹ�õ�λ�ɹ�", null);
                    str = logService.objectToJson(useDep);
                    this.logService.addSystemLog(0, 2000, 100, "����ʹ�õ�λ:" + str,
                            userIp, userId);
                }
            }
        } catch (Exception e) {
            log.error("���¼�¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "���³���,ԭ��:" + e.getMessage(), null);
        }
        return ret;
    }

    /**
     * ����ʹ�õ�λ����
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delUseDep.json")
    @ResponseBody
    public HttpResponse useDepDel(UseDepCondition condition, HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpResponse ret;
        log.debug("�������ʹ�õ�λ����");
        String str = "";

        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        try {

            this.service.delUseDep(condition.getIds());

            log.debug("����ʹ�õ�λ�ɹ�");
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "����ʹ�õ�λ�ɹ�", null);

            str = logService.objectToJson(condition.getIds());
            this.logService.addSystemLog(0, 2000, 300, "ɾ�����뵥λ:" + str,
                    userIp, userId);
        } catch (Exception e) {
            log.error("����ʹ�õ�λ����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "����ʹ�õ�λ����,ԭ��:" + e.getMessage(), null);
        }
        return ret;
    }

    /**
     * ʹ�õ�λ������Ϸ�Χ����
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveMaterielRange.json")
    @ResponseBody
    public HttpResponse saveMaterielRange(UseDepCondition condition, HttpServletRequest request,
                                          HttpServletResponse response) {
        log.debug("����ʹ�õ�λ������Ϸ�Χ����,��ǰ������:saveMaterielRange");
        HttpResponse ret;

        Integer[] arr = condition.getIds();

        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        try {
            this.service.saveMaterielRange(arr, condition, userId);
            log.debug("�������Ϸ�Χ�ɹ�");
            ret = new HttpResponse(null);
            this.logService.addSystemLog(0, 2000, 100, "�������Ϸ�Χ,���Ϸ�ΧID:"
                    + logService.objectToJson(arr), userIp, userId);

        } catch (Exception e) {
            log.error("������Ϸ�Χ�쳣");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "�������Ϸ�Χʧ��,ԭ��:" + e.getMessage(), null);
        }

        return ret;
    }


}
