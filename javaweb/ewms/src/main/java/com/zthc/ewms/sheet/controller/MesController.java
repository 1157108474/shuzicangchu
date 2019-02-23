package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.entity.mes.MesCk;
import com.zthc.ewms.sheet.entity.mes.MesCkCondition;
import com.zthc.ewms.sheet.entity.mes.MesRk;
import com.zthc.ewms.sheet.entity.mes.MesRkCondition;
import com.zthc.ewms.sheet.service.MesService;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/mes")
public class MesController {

    @Resource(name = "mesService")
    private MesService service;

    private final static Log log = Log.getLog("com.zthc.ewms.sheet.controller.MesController");

    /*-------------------------------------------------��ת����-------------------------------------------------*/

    @RequestMapping(value = "/manageMesRk.htm")
    public String manageMesRk(HttpServletRequest request, Model model){
        return "/sheet/mes/queryMesRk";
    }

    @RequestMapping(value = "/manageMesCk.htm")
    public String manageMesCk(HttpServletRequest request, Model model){
        return "/sheet/mes/queryMesCk";
    }

    /*-------------------------------------------------��������-------------------------------------------------*/

    @RequestMapping(value = "/listMesRk.json")
    @ResponseBody
    public LayuiPage<MesRk> queryMesRkList(@ModelAttribute("MesRk")MesRk mesRk, MesRkCondition condition,
                                       HttpServletRequest request, HttpServletResponse response){
        log.debug("�����ȡ��Ʒ������б���,��ǰ������:queryMesRkList");
        LayuiPage<MesRk> ret = null;
        try{
            ret = this.service.queryMesRkList(mesRk,condition);
        }catch (Exception e){
            log.error("��ȡ��Ʒ������б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    @RequestMapping(value = "/listMesCk.json")
    @ResponseBody
    public LayuiPage<MesCk> queryMesCkList(@ModelAttribute("MesCk")MesCk mesCk, MesCkCondition condition,
                                           HttpServletRequest request, HttpServletResponse response){
        log.debug("�����ȡ��Ʒ������б���,��ǰ������:queryMesCkList");
        LayuiPage<MesCk> ret = null;
        try{
            ret = this.service.queryMesCkList(mesCk,condition);
        }catch (Exception e){
            log.error("��ȡ��Ʒ������б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
}
