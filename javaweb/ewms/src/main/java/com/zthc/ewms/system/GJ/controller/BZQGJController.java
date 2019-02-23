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
     * 获取保质期告警查询列表
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
        log.debug("进入获取保质期告警查询列表方法,当前方法名:BzqList");
        //TODO:需获取当前登录人部门ID查询
        LayuiPage<VBzqEntity> ret = null;
        try{
            ret = vBzqEntityService.BzqList(obj,condition,maturity);
        }catch (Exception e){
            log.error("获取保质期告警查询列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * 导出保质期告警Excel
     */
    @RequestMapping(value = "/exportBzqGjExcel.htm")
    public void exportBzqGjExcel(@ModelAttribute("VBzqEntity")VBzqEntity obj, VBzqEntityCondition condition,String maturity,
                              HttpServletRequest request, HttpServletResponse response,Model model) throws ParseException, IOException {
        List<VBzqEntity> list = vBzqEntityService.BzqExcelList(obj,condition,maturity);
        String[] gridTitles = {"物料编码","物料名称","物料描述","物料型号","库存数量","入库时间","保质期时间","剩余天数"};
        String[] coloumsKey = {"materialcode","materialname","description","materialmodel","storecount","createdate","expirationtime","syday"};

        String userName = "保质期告警表";
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
