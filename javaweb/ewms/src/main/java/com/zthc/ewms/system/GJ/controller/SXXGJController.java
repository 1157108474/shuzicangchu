package com.zthc.ewms.system.GJ.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.ExcelExport;
import com.zthc.ewms.system.GJ.entity.VSxxEntity;
import com.zthc.ewms.system.GJ.entity.VSxxEntityCondition;
import com.zthc.ewms.system.GJ.service.VSxxEntityService;
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
@RequestMapping("/system/sxxgj")
public class SXXGJController {

    @Resource(name = "vSxxEntityService")
    public VSxxEntityService vSxxEntityService;

    @RequestMapping(value = "/showSXXGJ.htm")
    public String showSXXGJ() {
        return "system/gj/showSXXGJ";
    }

    private final static Log log = Log.getLog("com.system.GJ.controller.BZQGJController");

    @RequestMapping(value = "/sxxGjDetails.htm")
    public String sxxGjDetails(@ModelAttribute("VSxxEntity")VSxxEntity obj, VSxxEntityCondition condition,Model model) {
        VSxxEntity sxx = vSxxEntityService.getOne(obj);
        model.addAttribute("sxx",sxx);
        return "system/gj/sxxGjDetails";
    }

    /**
     * ��ȡ�����޸澯��ѯ�б�
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/SXXGJ.json")
    @ResponseBody
    public LayuiPage<VSxxEntity> SXXGJ(@ModelAttribute("VSxxEntity")VSxxEntity obj, VSxxEntityCondition condition,
                                       String gjtype, HttpServletRequest request, HttpServletResponse response, Model model){
        log.debug("�����ȡ�����ڸ澯��ѯ�б���,��ǰ������:BzqList");
        //TODO:���ȡ��ǰ��¼�˲���ID��ѯ
        LayuiPage<VSxxEntity> ret = null;
        try{
            ret = vSxxEntityService.SxxList(obj,condition,gjtype);
        }catch (Exception e){
            log.error("��ȡ�����ڸ澯��ѯ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ���������޸澯Excel
     */
    @RequestMapping(value = "/exportSxxGjExcel.htm")
    public void exportSxxGjExcel(@ModelAttribute("VSxxEntity")VSxxEntity obj, VSxxEntityCondition condition,String gjtype,
                                 HttpServletRequest request, HttpServletResponse response,Model model) throws ParseException, IOException {
        List<VSxxEntity> list = vSxxEntityService.SxxExcelList(obj,condition,gjtype);
        String[] gridTitles = {"���ϱ���","��������","��������","�����ͺ�","�������","��������","��������"};
        String[] coloumsKey = {"code","name","description","models","con","stockdown","stockup"};
        String userName = "�����޸澯��";
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
