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

    /*-------------------------------------------------跳转方法-------------------------------------------------*/

    /**
     * 进入供应商管理首页
     * @return
     */
    @RequestMapping(value="/manageProvider.htm")
    public String manageProvider(){
        return "system/provider/manageProvider";
    }

    /**
     * 进入新增修改页面
     * @param provider
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateProvider.htm")
    public String updateProvider(@ModelAttribute("Provider")Provider provider, HttpServletRequest request,
                                 HttpServletResponse response, Model model){
        log.debug("进入供应商新增更新页面,当前方法为updateProvider");
        if(StringUtils.isEmpty(provider.getId())){
            log.debug("进入供应商新增页面");
            return "system/provider/updateProvider";
        }else{
            log.debug("进入供应商编辑页面,当前供应商ID:"+provider.getId());
            Provider ret = this.service.getOne(provider.getId());
            log.debug("获取单条供应商信息成功,跳转到页面");
            model.addAttribute("ProviderValue",ret);
            return "system/provider/updateProvider";
        }

    }

    /**
     * 供应商通用调用页面
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
        log.error("调用供应商通用页面,当前方法:generalProvider");
        Integer pid = 0;
        if(!StringUtils.isEmpty(provider.getId())){
            pid = provider.getId();
        }
        model.addAttribute("providerId",pid);
        return "system/provider/generalProvider";
    }

    /*-------------------------------------------------基础方法-------------------------------------------------*/

    /**
     * 获取供应商列表
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
        log.debug("进入获取供应商列表方法,当前方法名:providerList");
        LayuiPage<Provider> ret = null;
        try{
            ret = service.listProviders(provider,Condition);
        }catch (Exception e){
            log.error("获取供应商列表出错");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 获取通用供应商列表
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
        log.debug("进入获取通用供应商列表方法,当前方法名:generalList");
        LayuiPage<Provider> ret = null;
        try{
            ret = this.service.listGeneral(provider,condition);
        }catch (Exception e){
            log.error("获取通用供应商列表失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }


    /**
     * 新增修改方法
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
        log.debug("进入供应商更新方法,方法名为providerEdit");
        String str = "";

        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        //进入方法
        try {
            if(!StringUtils.isEmpty(provider.getId()) && 0 != provider.getId()){
                log.debug("进入供应商修改方法,当前供应商ID为:"+provider.getId());
                int check = this.service.checkProvider(provider.getCode(),provider.getName(),provider.getId());
                if(check >= 1){

                    ret = new HttpResponse(HttpResponse.Status.SUCCESS,"保存失败,拥有重复的编码或名称",null);
                    return ret;

                }else {
                    provider.setUpdater(userId);
                    this.service.editProvider(provider);

                    log.debug("编辑供应商成功");
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS,"编辑供应商成功",null);

                    str = logService.objectToJson(provider);
                    this.logService.addSystemLog(0, 1900, 200, "编辑供应商信息" +
                            str, userIp, userId);

                }
            }else{
                log.debug("进入供应商新增方法");
                int check = this.service.checkProvider(provider.getCode(),provider.getName(),0);
                if( check >= 1){

                    ret = new HttpResponse(HttpResponse.Status.FAILURE,"保存失败,拥有重复的编码或名称",null);
                    return ret;

                }else {

                    provider.setCreator(userId);
                    provider.setDeleted(1);
                    provider.setAddType(1);
                    provider.setUpdater(userId);
                    this.service.addProvider(provider);

                    log.debug("新增供应商成功");
                    ret = new HttpResponse(HttpResponse.Status.SUCCESS,"新增供应商成功",null);

                    str = logService.objectToJson(provider);
                    this.logService.addSystemLog(0, 1900, 100, "新增供应商信息" +
                            str, userIp, userId);
                }
            }
        }catch (Exception e){

                log.error("更新记录出错");
                log.errorPrintStacktrace(e);
                ret = new HttpResponse(HttpResponse.Status.FAILURE,e.getMessage(),null);
        }
        return ret;
    }

    /**
     * 删除供应商方法
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

        //获取当前登录人信息
        HttpSession session = request.getSession();
        Object requestUserIp = session.getAttribute("userIp");
        Object requestUserId = session.getAttribute("userId");
        Integer userId = (null == requestUserId ? 0 : Integer.valueOf(requestUserId.toString()));
        String userIp = (null == requestUserIp ? null : requestUserIp.toString());

        try{
            log.debug("进入删除供应商方法,当前方法名:providerDel,当前供应商ID:"+logService.objectToJson(condition.getIds()));
            this.service.delProvider(condition.getIds());

            log.debug("删除供应商成功");
            ret = new HttpResponse(HttpResponse.Status.SUCCESS,"删除供应商成功",null);

            str = logService.objectToJson(str);
            this.logService.addSystemLog(0,1900,300,"删除供应商信息"+
                    str,userIp,userId);
        }catch (Exception e){
            log.error("删除供应商出错");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE,"删除供应商失败,原因:"+e.getMessage(),null);
        }
        return ret;
    }


}
