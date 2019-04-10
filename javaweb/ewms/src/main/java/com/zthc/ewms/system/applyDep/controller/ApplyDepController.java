package com.zthc.ewms.system.applyDep.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.applyDep.entity.ApplyDep;
import com.zthc.ewms.system.applyDep.entity.ApplyDepCondition;
import com.zthc.ewms.system.applyDep.service.ApplyDepService;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.service.DepartService;
import com.zthc.ewms.system.log.service.LogService;
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

@Controller
@RequestMapping("/system/applyDep")
public class ApplyDepController {

    @Resource(name = "applyDepService")
    public ApplyDepService service;

    @Resource(name = "logService")
    public LogService logService;

    @Resource(name = "departService")
    public DepartService departService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.applyDep.controller.ApplyDepController");
    }

     /*-------------------------------------------------��ת����-------------------------------------------------*/

    /**
     * �������뵥λҳ��
     *
     * @return
     */
    @RequestMapping(value = "/manageApplyDep.htm")
    public String manageApplyDep() {
        return "system/applyDep/manageApplyDep";
    }

    /**
     * ���������޸�ҳ��
     *
     * @param applyDep
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateApplyDep.htm")
    public String updateApplyDep(@ModelAttribute("ApplyDep") ApplyDep applyDep, HttpServletRequest request,
                                 HttpServletResponse response, Model model) {
        log.debug("�������뵥λ��������ҳ��,��ǰ����ΪupdateApplyDep,��ǰ���뵥λID:" + applyDep.getId());
        if (StringUtils.isEmpty(applyDep.getId())) {
            log.debug("�������뵥λ����ҳ��");
            return "system/applyDep/updateApplyDep";
        } else {
            log.debug("�������뵥λ�༭ҳ��,��ǰ���뵥λID:" + applyDep.getId());

            ApplyDep ret = this.service.getOne(applyDep.getId());
            Depart depart = this.departService.getDepartOne(ret.getZtId());

            model.addAttribute("depName", depart.getName());
            model.addAttribute("applyDepValue", ret);

            log.debug("��ȡ�������뵥λ��Ϣ�ɹ�,��ת��ҳ��");
            return "system/applyDep/updateApplyDep";
        }
    }

    /**
     * ���뵥λͨ�õ���ҳ��
     *
     * @param applyDep
     * @param condition
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/generalApplyDep.htm")
    public String generalApplyDep(@ModelAttribute("ApplyDep") ApplyDep applyDep, ApplyDepCondition condition,
                                  HttpServletRequest request, HttpServletResponse response, Model model) {
        log.error("����ʹ�õ�λͨ��ҳ��,��ǰ����:generalApplyDep");
        Integer aid = 0;
        if (!StringUtils.isEmpty(applyDep.getId()) && 0 != applyDep.getId()) {
            aid = applyDep.getId();
        }
        model.addAttribute("applydepId", aid);
        return "system/applyDep/generalApplyDep";
    }


     /*-------------------------------------------------��������-------------------------------------------------*/

    /**
     * ��ȡ���뵥λ�б�
     *
     * @param applyDep
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listApplyDep.json")
    @ResponseBody
    public LayuiPage<ApplyDep> applyDepList(@ModelAttribute("ApplyDep") ApplyDep applyDep, ApplyDepCondition condition,
                                            HttpServletRequest request, HttpServletResponse response) {
        log.debug("�����ȡ���뵥λ����,��ǰ������:applyDepList");
        LayuiPage<ApplyDep> ret = null;
        try {
            ret = service.listApplyDep(applyDep, condition);
        } catch (Exception e) {
            log.error("��ȡ���뵥λ�б�ʧ��");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * ��ȡͨ��ʹ�õ�λ�б�
     *
     * @param applyDep
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listApplyDepGeneral.json")
    @ResponseBody
    public LayuiPage<ApplyDep> generalList(@ModelAttribute("ApplyDep") ApplyDep applyDep, ApplyDepCondition condition,
                                           HttpServletRequest request, HttpServletResponse response) {
        log.debug("�����ȡͨ�����뵥λ�б���,��ǰ������:generalList");
        LayuiPage<ApplyDep> ret = null;
        try {
            ret = this.service.listGeneral(applyDep, condition);
        } catch (Exception e) {
            log.error("��ȡͨ�����뵥λ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �����޸ķ���
     *
     * @param applyDep
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editApplyDep.json")
    @ResponseBody
    public HttpResponse applyDepEdit(@ModelAttribute("ApplyDep") ApplyDep applyDep, HttpServletRequest request,
                                     HttpServletResponse response) {
        log.debug("�������뵥λ���·���,applyDepEdit");
        String str = "";
        HttpResponse ret;

        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Object requestUserName = session.getAttribute("userName");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());
        String userName = (null == requestUserName ? null : requestUserName.toString());

        //���뷽��
        try {
            if (!StringUtils.isEmpty(applyDep.getId()) && 0 != applyDep.getId()) {
                log.debug("�������뵥λ�޸ķ���,��ǰ���뵥λID:" + applyDep.getId());
                int check = this.service.checkApplyDep(applyDep.getCode(), applyDep.getName(), applyDep.getId());

                if (check >= 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE,"����ʧ�ܣ�ӵ���ظ��ı��������",null);

                    return ret;
                } else {
                    this.service.editApplyDep(applyDep);
                    log.debug("�༭���뵥λ�ɹ�");

                    ret = new HttpResponse(HttpResponse.Status.SUCCESS,"�༭���뵥λ�ɹ�",null);

                    str = logService.objectToJson(applyDep);
                    this.logService.addSystemLog(0, 2100, 200, "�༭���뵥λ:" + str,
                            userIp, userId);
                }
            } else {
                log.debug("�������뵥λ��������");

                int check = this.service.checkApplyDep(applyDep.getCode(), applyDep.getName(), 0);
                if (check >= 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE,"����ʧ��,ӵ���ظ��ı��������",null);

                    return ret;
                } else {

                    applyDep.setCreator(userId);
                    applyDep.setCreateName(userName);
                    applyDep.setDeleted(1);
                    applyDep.setAddType(1);
                    if(StringUtils.isEmpty(applyDep.getErpId())){
                        applyDep.setErpId("");
                    }
                    this.service.addApplyDep(applyDep);

                    log.debug("�������뵥λ�ɹ�");
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS,"�������뵥λ�ɹ�",applyDep);

                    str = logService.objectToJson(applyDep);
                    this.logService.addSystemLog(0, 2100, 100, "�������뵥λ:" + str,
                            userIp, userId);
                }
            }
        } catch (Exception e) {
            log.error("���¼�¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE,e.getMessage(),null);
        }
        return ret;
    }

    /**
     * ɾ�����뵥λ����
     *
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delApplyDep.json")
    @ResponseBody
    public HttpResponse ApplyDepDel(ApplyDepCondition condition, HttpServletRequest request,
                                           HttpServletResponse response) {
        HttpResponse ret;
        String str = "";

        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());


        try {
            str = logService.objectToJson(condition.getIds());
            log.debug("����ɾ�����뵥λ����,��ǰid:"+str);

            this.service.delApplyDep(condition.getIds());
            log.debug("ɾ�����뵥λ�ɹ�");

            ret = new HttpResponse(HttpResponse.Status.SUCCESS,"ɾ�����뵥λ�ɹ�",null);

            this.logService.addSystemLog(0, 2100, 300, "ɾ�����뵥λ:" + str,
                    userIp, userId);
        } catch (Exception e) {
            log.error("ɾ�����뵥λ����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE,e.getMessage(),null);
        }
        return ret;
    }

}
