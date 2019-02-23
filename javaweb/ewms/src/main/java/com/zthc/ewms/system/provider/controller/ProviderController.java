package com.zthc.ewms.system.provider.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.provider.entity.Provider;
import com.zthc.ewms.system.provider.entity.ProviderCondition;
import com.zthc.ewms.system.provider.service.ProviderService;
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
@RequestMapping("/system/provider")
public class ProviderController {

    @Resource(name = "providerService")
    public ProviderService service;

    @Resource(name = "logService")
    public LogService logService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.provider.controller.ProviderController");
    }

    /*-------------------------------------------------��ת����-------------------------------------------------*/

    /**
     * ���빩Ӧ�̹�����ҳ
     * @return
     */
    @RequestMapping(value="/manageProvider.htm")
    public String manageProvider(){
        return "system/provider/manageProvider";
    }

    /**
     * ���������޸�ҳ��
     * @param provider
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateProvider.htm")
    public String updateProvider(@ModelAttribute("Provider")Provider provider, HttpServletRequest request,
                                 HttpServletResponse response, Model model){
        log.debug("���빩Ӧ����������ҳ��,��ǰ����ΪupdateProvider");
        if(StringUtils.isEmpty(provider.getId())){
            log.debug("���빩Ӧ������ҳ��");
            return "system/provider/updateProvider";
        }else{
            log.debug("���빩Ӧ�̱༭ҳ��,��ǰ��Ӧ��ID:"+provider.getId());
            Provider ret = this.service.getOne(provider.getId());
            log.debug("��ȡ������Ӧ����Ϣ�ɹ�,��ת��ҳ��");
            model.addAttribute("ProviderValue",ret);
            return "system/provider/updateProvider";
        }

    }

    /**
     * ��Ӧ��ͨ�õ���ҳ��
     * @param provider
     * @param condition
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/generalProvider.htm")
    public String generalProvider(@ModelAttribute("Provider")Provider provider,ProviderCondition condition,
                                  HttpServletRequest request, HttpServletResponse response,Model model){
        log.error("���ù�Ӧ��ͨ��ҳ��,��ǰ����:generalProvider");
        Integer pid = 0;
        if(!StringUtils.isEmpty(provider.getId())){
            pid = provider.getId();
        }
        model.addAttribute("providerId",pid);
        return "system/provider/generalProvider";
    }

    /*-------------------------------------------------��������-------------------------------------------------*/

    /**
     * ��ȡ��Ӧ���б�
     * @param provider
     * @param Condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listProvider.json")
    @ResponseBody
    public LayuiPage<Provider> providerList(@ModelAttribute("Provider")Provider provider, ProviderCondition Condition,
                                            HttpServletRequest request, HttpServletResponse response){
        log.debug("�����ȡ��Ӧ���б���,��ǰ������:providerList");
        LayuiPage<Provider> ret = null;
        try{
            ret = service.listProviders(provider,Condition);
        }catch (Exception e){
            log.error("��ȡ��Ӧ���б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡͨ�ù�Ӧ���б�
     * @param provider
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listProviderGeneral.json")
    @ResponseBody
    public LayuiPage<Provider> generalList(@ModelAttribute("Provider")Provider provider,ProviderCondition condition,
                                           HttpServletRequest request, HttpServletResponse response){
        log.debug("�����ȡͨ�ù�Ӧ���б���,��ǰ������:generalList");
        LayuiPage<Provider> ret = null;
        try{
            ret = this.service.listGeneral(provider,condition);
        }catch (Exception e){
            log.error("��ȡͨ�ù�Ӧ���б�ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * �����޸ķ���
     * @param provider
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editProvider.json")
    @ResponseBody
    public HttpResponse providerEdit(@ModelAttribute("Provider") Provider provider, HttpServletRequest request,
                                     HttpServletResponse response){
        HttpResponse ret;
        log.debug("���빩Ӧ�̸��·���,������ΪproviderEdit");
        String str = "";

        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        //���뷽��
        try {
            if(!StringUtils.isEmpty(provider.getId()) && 0 != provider.getId()){
                log.debug("���빩Ӧ���޸ķ���,��ǰ��Ӧ��IDΪ:"+provider.getId());
                int check = this.service.checkProvider(provider.getCode(),provider.getName(),provider.getId());
                if(check >= 1){

                    ret = new HttpResponse(HttpResponse.Status.SUCCESS,"����ʧ��,ӵ���ظ��ı��������",null);
                    return ret;

                }else {
                    provider.setUpdater(userId);
                    this.service.editProvider(provider);

                    log.debug("�༭��Ӧ�̳ɹ�");
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS,"�༭��Ӧ�̳ɹ�",null);

                    str = logService.objectToJson(provider);
                    this.logService.addSystemLog(0, 1900, 200, "�༭��Ӧ����Ϣ" +
                            str, userIp, userId);

                }
            }else{
                log.debug("���빩Ӧ����������");
                int check = this.service.checkProvider(provider.getCode(),provider.getName(),0);
                if( check >= 1){

                    ret = new HttpResponse(HttpResponse.Status.FAILURE,"����ʧ��,ӵ���ظ��ı��������",null);
                    return ret;

                }else {

                    provider.setCreator(userId);
                    provider.setDeleted(1);
                    provider.setAddType(1);
                    provider.setUpdater(userId);
                    this.service.addProvider(provider);

                    log.debug("������Ӧ�̳ɹ�");
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS,"������Ӧ�̳ɹ�",null);

                    str = logService.objectToJson(provider);
                    this.logService.addSystemLog(0, 1900, 100, "������Ӧ����Ϣ" +
                            str, userIp, userId);
                }
            }
        }catch (Exception e){

                log.error("���¼�¼����");
                log.errorPrintStacktrace(e);
                ret = new HttpResponse(HttpResponse.Status.FAILURE,e.getMessage(),null);
        }
        return ret;
    }

    /**
     * ɾ����Ӧ�̷���
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delProvider.json")
    @ResponseBody
    public HttpResponse providerDel(ProviderCondition condition,HttpServletRequest request,
                                          HttpServletResponse response){
        HttpResponse ret;
        String str = "";

        //��ȡ��ǰ��¼����Ϣ
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        try{
            log.debug("����ɾ����Ӧ�̷���,��ǰ������:providerDel,��ǰ��Ӧ��ID:"+logService.objectToJson(condition.getIds()));
            this.service.delProvider(condition.getIds());

            log.debug("ɾ����Ӧ�̳ɹ�");
            ret = new HttpResponse(HttpResponse.Status.SUCCESS,"ɾ����Ӧ�̳ɹ�",null);

            str = logService.objectToJson(str);
            this.logService.addSystemLog(0,1900,300,"ɾ����Ӧ����Ϣ"+
                    str,userIp,userId);
        }catch (Exception e){
            log.error("ɾ����Ӧ�̳���");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE,"ɾ����Ӧ��ʧ��,ԭ��:"+e.getMessage(),null);
        }
        return ret;
    }


}
