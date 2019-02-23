package com.zthc.ewms.system.GJ.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.ExcelExport;
import com.zthc.ewms.system.GJ.entity.VBzqEntity;
import com.zthc.ewms.system.GJ.entity.VBzqEntityCondition;
import com.zthc.ewms.system.GJ.service.VBzqEntityService;
import drk.system.Log;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/system/bzqgj")
public class BZQGJController {

    @Resource(name = "vBzqEntityService")
    public VBzqEntityService vBzqEntityService;

    @RequestMapping(value = "/showBZQGJ.htm")
    public String showBZQGJ() {
        return "system/gj/showBZQGJ";
    }

    private final static Log log = Log.getLog("com.system.GJ.controller.BZQGJController");

    @RequestMapping(value = "/bzqGjDetails.htm")
    public String bzqGjDetails(@ModelAttribute("VBzqEntity")VBzqEntity obj, VBzqEntityCondition condition,Model model) {
        VBzqEntity bzq = vBzqEntityService.getOne(obj);
        model.addAttribute("bzq",bzq);
        return "system/gj/bzqGjDetails";
    }

    /**
     * ��ȡ�����ڸ澯��ѯ�б�
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/BZQGJ.json")
    @ResponseBody
    public LayuiPage<VBzqEntity> BZQGJ(@ModelAttribute("VBzqEntity")VBzqEntity obj, VBzqEntityCondition condition,
                                     String maturity, HttpServletRequest request, HttpServletResponse response, Model model){
        log.debug("�����ȡ�����ڸ澯��ѯ�б���,��ǰ������:BzqList");
        //TODO:���ȡ��ǰ��¼�˲���ID��ѯ
        LayuiPage<VBzqEntity> ret = null;
        try{
            ret = vBzqEntityService.BzqList(obj,condition,maturity);
        }catch (Exception e){
            log.error("��ȡ�����ڸ澯��ѯ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ���������ڸ澯Excel
     */
    @RequestMapping(value = "/exportBzqGjExcel.htm")
    public void exportBzqGjExcel(@ModelAttribute("VBzqEntity")VBzqEntity obj, VBzqEntityCondition condition,String maturity,
                              HttpServletRequest request, HttpServletResponse response,Model model) throws ParseException, IOException {
        List<VBzqEntity> list = vBzqEntityService.BzqExcelList(obj,condition,maturity);
        String[] gridTitles = {"���ϱ���","��������","��������","�����ͺ�","�������","���ʱ��","������ʱ��","ʣ������"};
        String[] coloumsKey = {"materialcode","materialname","description","materialmodel","storecount","createdate","expirationtime","syday"};

        String userName = "�����ڸ澯��";
        HSSFWorkbook workBook = new ExcelExport().getExcelContent(userName,list,gridTitles,coloumsKey);
        //���Excel�ļ�
        OutputStream output=response.getOutputStream();
        response.reset();

        String userAgent = request.getHeader("User-Agent");
        // ���IE������IEΪ�ں˵��������
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userName = java.net.URLEncoder.encode(userName, "UTF-8");
        } else {
            // ��IE������Ĵ���
            userName = new String(userName.getBytes("UTF-8"), "ISO-8859-1");
        }
        // ����response��Header
        response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"", userName+".xls"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        workBook.write(output);
        output.close();
    }
}
