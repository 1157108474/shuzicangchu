package com.zthc.ewms.system.print.controller;

import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.entity.apply.ApplyPrint;
import com.zthc.ewms.sheet.entity.apply.ManageApply;
import com.zthc.ewms.sheet.entity.guard.SheetCKD;
import com.zthc.ewms.sheet.entity.guard.SheetCKDETAIL;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetRKD;
import com.zthc.ewms.sheet.entity.order.OrderPrint;
import com.zthc.ewms.sheet.entity.rk.DbrkDetailPrint;
import com.zthc.ewms.sheet.entity.rk.DbrkPrint;
import com.zthc.ewms.sheet.entity.rk.RkDetailPrint;
import com.zthc.ewms.sheet.entity.th.ThDetail;
import com.zthc.ewms.sheet.entity.th.ThPrint;
import com.zthc.ewms.sheet.entity.tk.TKDetail;
import com.zthc.ewms.sheet.entity.tk.TKPrint;
import com.zthc.ewms.sheet.service.*;
import com.zthc.ewms.system.print.entity.guard.PrintEnums;
import com.zthc.ewms.system.print.service.BarCodeService;
import drk.system.Log;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/system/print")
public class PrintController {
    @Resource(name = "barCodeService")
    public BarCodeService barCodeService;


    @Resource(name = "sheetService")
    public SheetService sheetService;
    @Resource(name = "sheetDetailService")
    public SheetDetailService detailService;

    @Autowired
    private SheetCGService sheetCGService;
    @Autowired
    public SheetApplyService sheetApplyService;
    @Autowired
    private SheetCKService sheetCKService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.print.controller.PrintController");
    }

    @RequestMapping(value = "allocation", method = RequestMethod.GET)
    public String allocation(HttpServletRequest request, HttpServletResponse response, Model model) {
        // model.addAttribute("num",request.getParameter("num"));
        return "system/print/allocation";
    }

    @RequestMapping(value = "tag", method = RequestMethod.GET)
    public String tag(String length, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("num", length);
        return "sheet/print/tag";
    }

    @RequestMapping(value = "print1D/{code}", method = RequestMethod.GET)
    public void allocationPrint(@PathVariable("code") String code, HttpServletRequest req,
                                HttpServletResponse resp) throws IOException {
        if (code != null && !"".equals(code)) {
            ServletOutputStream stream = resp.getOutputStream();
            this.barCodeService.print1D(code, stream);

        }
    }

    @RequestMapping(value = "print2D/{code}", method = RequestMethod.GET)
    public void print2D(@PathVariable("code") String code, HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {
        if (code != null && !"".equals(code)) {
            ServletOutputStream stream = response.getOutputStream();
            this.barCodeService.print2D(code, stream);

        }
    }

    @RequestMapping(value = "sheet/{type}-{id}", method = RequestMethod.GET)
    public String printSheet(@PathVariable("type") String type, @PathVariable("id") Integer id, HttpServletRequest
            request, HttpServletResponse response, Model model) throws IOException, ServletException {

        //��ȡ��ӡ����
        String typeObject = request.getParameter("printType");
        Integer printType = 0;
        if (typeObject != null) {
            printType = Integer.valueOf(typeObject);
        }
        if (type.equals("WZTH")) {
            //��ӡ�����˻�
            printWZTH(model, id);
        } else if (type.equals("WZTK")) {
            //��ӡ�����˿�
            printWZTK(model, id);
        } else if (type.equals("WZJS")) {
            //��ӡ���ʽ���
            type = printWZJS(printType, model, id);
        } else if (type.equals("JSRK")) {
            //��ӡ�������
            printJSRK(model, id);
        } else if (type.equals("DBRK")) {
            //��ӡ�������
            printDBRK(model, id);
        } else if (type.equals("BQDY")) {
            String numObject = request.getParameter("num");
            Integer num = numObject.equals("") ? 0 : Integer.valueOf(numObject);
            model.addAttribute("num", num);
        } else if (type.equals("WZLLD")) {
            //��ӡ�������ϵ�
            printWZLLD(model, id);
        } else if (type.equals("WZCK")) {
            //��ӡ���ʳ���
            printWZCK(model, id);
        }
        return "system/print/" + type + "Sheet";
    }

    /**
     * ��ӡ�������
     *
     * @param model
     * @param id
     */
    private void printDBRK(Model model, Integer id) {
        DbrkPrint sheet = this.sheetService.getDbrkPrintOne(id);
        if (!StringUtils.isEmpty(sheet)) {
            sheet.setCreateDateStr(new DateTime(sheet.getRkDate()).toString("yyyy��MM��dd�� HH:mm:ss"));
        }
        List<DbrkDetailPrint> detail = this.detailService.printDetails(id, null, "DbrkDetailPrint");
        if (detail.size() > 0) {
            Double sum1 = 0.0;
            for (int i = 0; i < detail.size(); i++) {
                sum1 = detail.get(i).getNotaxSum();
            }
            model.addAttribute("sum1", sum1);
        }
        model.addAttribute("sheet", sheet);
        model.addAttribute("detail", detail);
    }

    /**
     * ��ӡ�������ϵ�
     *
     * @param model
     * @param id
     */
    private void printWZLLD(Model model, Integer id) {
        ApplyPrint sheet = this.sheetService.getApplyPrintOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy-MM-dd"));
        model.addAttribute("sheet", sheet);
        List<SheetDetail> details = this.detailService.printDetails(id, null, "SheetDetail");
        model.addAttribute("details", details);
    }

    /**
     * ��ӡ���ʳ���
     *
     * @param model
     * @param id
     */
    private void printWZCK(Model model, Integer id) {
        SheetCKD sheet = this.sheetService.getSheetCKDOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreatedate()).toString("yyyy-MM-dd"));
        model.addAttribute("sheet", sheet);

        ManageApply apply = this.sheetApplyService.getApplyOne(sheet.getExtendint1());
        model.addAttribute("apply", apply);

        List<SheetCKDETAIL> details = this.sheetCKService.printCKDetails(id);
        model.addAttribute("details", details);
    }

    /**
     * ��ӡ�����˿�
     *
     * @param model
     * @param id
     * @return
     */
    private void printWZTK(Model model, Integer id) {
        TKPrint sheet = this.sheetService.getTKPrintSheetOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy-MM-dd"));
        model.addAttribute("sheet", sheet);
        List<TKDetail> details = this.detailService.printDetails(id, null, "TKDetail");
        model.addAttribute("details", details);
    }

    /**
     * ��ӡ�����˻�
     *
     * @param id
     * @return
     */
    private void printWZTH(Model model, Integer id) {

        ThPrint sheet = this.sheetService.getThPrintSheetOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy-MM-dd HH:mm:ss"));
        model.addAttribute("sheet", sheet);

        List<ThDetail> details = this.detailService.printDetails(id, null, "ThDetail");
        model.addAttribute("details", details);
    }

    /**
     * ��ӡ���ʽ���
     *
     * @param printType
     * @param model
     * @param id
     * @return
     */
    private String printWZJS(Integer printType, Model model, Integer id) {

        OrderPrint sheet = this.sheetService.getOrderPrintSheetOne(id);
        if (sheet != null) {
            sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy��MM��dd�� HH:mm:ss"));
        }
        model.addAttribute("sheet", sheet);
        String userName = sheetCGService.getSheetCGNameOne(sheet.getExtendInt1());
        model.addAttribute("userName", userName);
        List<SheetDetail> details = this.detailService.printDetails(id, null, "SheetDetail");
        model.addAttribute("details", details);

        if (printType.equals(PrintEnums.WZJSEnum.EQUIPMENT_SPARE.getType())) {
            return "WZJSEquipmentSpare";
        } else if (printType.equals(PrintEnums.WZJSEnum.ORIGINAL_AUXILIARY.getType())) {
            return "WZJSoriginalAuxiliary";
        } else if (printType.equals(PrintEnums.WZJSEnum.SAFETY_MATERIALS.getType())) {
            return "WZJSafetyMaterials";
        } else {
            return "WZJOtherMaterials";
        }
    }

    /**
     * ��ӡ�������
     *
     * @param model
     * @param id
     * @return
     */
    private void printJSRK(Model model, Integer id) {
        SheetRKD sheet = this.sheetService.getRkSheetOne(id);
        if (!StringUtils.isEmpty(sheet)) {
            sheet.setCreateDateStr(new DateTime(sheet.getCreatedate()).toString("yyyy��MM��dd�� HH:mm:ss"));
        }
        model.addAttribute("sheet", sheet);
        List<RkDetailPrint> rkDetailPrints = this.detailService.printDetails(id, null, "RkDetailPrint");

        List<RkDetailPrint> details = new ArrayList<>();
        String str = "";
        for (RkDetailPrint rkDetailPrint : rkDetailPrints) {
            if(!rkDetailPrint.getExtendString1().equals(str)){
                str= rkDetailPrint.getExtendString1();
                details.add(rkDetailPrint);
            }
        }


        model.addAttribute("details", details);
    }

}
