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
     * 获取上下限告警查询列表
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
        log.debug("进入获取保质期告警查询列表方法,当前方法名:BzqList");
        //TODO:需获取当前登录人部门ID查询
        LayuiPage<VSxxEntity> ret = null;
        try{
            ret = vSxxEntityService.SxxList(obj,condition,gjtype);
        }catch (Exception e){
            log.error("获取保质期告警查询列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 导出上下限告警Excel
     */
    @RequestMapping(value = "/exportSxxGjExcel.htm")
    public void exportSxxGjExcel(@ModelAttribute("VSxxEntity")VSxxEntity obj, VSxxEntityCondition condition,String gjtype,
                                 HttpServletRequest request, HttpServletResponse response,Model model) throws ParseException, IOException {
        List<VSxxEntity> list = vSxxEntityService.SxxExcelList(obj,condition,gjtype);
        String[] gridTitles = {"物料编码","物料名称","物料描述","物料型号","库存数量","下线数量","上线数量"};
        String[] coloumsKey = {"code","name","description","models","con","stockdown","stockup"};
        String userName = "上下限告警表";
        HSSFWorkbook workBook = new ExcelExport().getExcelContent(userName,list,gridTitles,coloumsKey);

        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();

        String userAgent = request.getHeader("User-Agent");
        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userName = java.net.URLEncoder.encode(userName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            userName = new String(userName.getBytes("UTF-8"), "ISO-8859-1");
        }
        // 设置response的Header
        response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"", userName+".xls"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        workBook.write(output);
        output.close();
    }
}
