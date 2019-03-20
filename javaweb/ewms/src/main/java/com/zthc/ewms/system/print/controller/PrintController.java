package com.zthc.ewms.system.print.controller;

import com.zthc.ewms.base.page.LayuiPage;
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
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
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
import java.util.Map;

@Controller
@RequestMapping("/system/print")
public class PrintController {
    @Resource(name = "barCodeService")
    public BarCodeService barCodeService;


    @Resource(name = "sheetService")
    public SheetService sheetService;
    @Resource(name = "sheetDetailService")
    public SheetDetailService detailService;
    @Resource(name = "activitiService")
    public ActivitiService activitiService;
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

        //获取打印类型
        String typeObject = request.getParameter("printType");
        String taskId = request.getParameter("taskId");
        Integer printType = 0;
        if (typeObject != null) {
            printType = Integer.valueOf(typeObject);
        }
        if (type.equals("WZTH")) {
            //打印物资退货
            printWZTH(model, id);
        } else if (type.equals("WZTK")) {
            //打印物资退库
            printWZTK(model, id,request,response,taskId);
        } else if (type.equals("WZJS")) {
            //打印物资接收
            type = printWZJS(printType, model, id,request,response,taskId);
        } else if (type.equals("JSRK")) {
            //打印接收入库
            printJSRK(model, id,taskId,request,response);
        } else if (type.equals("DBRK")) {
            //打印调拨入库
            printDBRK(model, id);
        } else if (type.equals("BQDY")) {
            String numObject = request.getParameter("num");
            Integer num = numObject.equals("") ? 0 : Integer.valueOf(numObject);
            model.addAttribute("num", num);
        } else if (type.equals("WZLLD")) {
            //打印物资领料单
            printWZLLD(model, id,request,response,taskId);
        } else if (type.equals("WZCK")) {
            //打印物资出库
            printWZCK(model, id,request,response,taskId);
        }
        return "system/print/" + type + "Sheet";
    }

    /**
     * 打印调拨入库
     *
     * @param model
     * @param id
     */
    private void printDBRK(Model model, Integer id) {
        DbrkPrint sheet = this.sheetService.getDbrkPrintOne(id);
        if (!StringUtils.isEmpty(sheet)) {
            sheet.setCreateDateStr(new DateTime(sheet.getRkDate()).toString("yyyy年MM月dd日 HH:mm:ss"));
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
     * 打印物资领料单
     *
     * @param model
     * @param id
     * @param taskId 
     * @param response 
     * @param request 
     */
    private void printWZLLD(Model model, Integer id, HttpServletRequest request, HttpServletResponse response, String taskId) {
        ApplyPrint sheet = this.sheetService.getApplyPrintOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy-MM-dd"));
        model.addAttribute("sheet", sheet);
        List<SheetDetail> details = this.detailService.printDetails(id, null, "SheetDetail");
        model.addAttribute("details", details);
        LayuiPage<Map<String, Object>> historicActivityInstances = activitiService.historyActInstanceList(taskId,0,100);
        List<Map<String,Object>> data = historicActivityInstances.getData();
        if(!"安全部负责人".equals(data.get(data.size()-1).get("activityName").toString()) && !"技术部负责人".equals(data.get(data.size()-1).get("activityName").toString()) && !"机动部负责人".equals(data.get(data.size()-1).get("activityName").toString())){
        	model.addAttribute("shenpi", data.get(data.size()-1).get("assignee"));
        }else{
        	model.addAttribute("shenpi", data.get(data.size()-3).get("assignee"));
        	model.addAttribute("shenpi2", data.get(data.size()-2).get("assignee"));
        	model.addAttribute("shenpi3", data.get(data.size()-1).get("assignee"));
        }
    }

    /**
     * 打印物资出库
     *
     * @param model
     * @param id
     * @param taskId 
     * @param response 
     * @param request 
     */
    private void printWZCK(Model model, Integer id, HttpServletRequest request, HttpServletResponse response, String taskId) {
        SheetCKD sheet = this.sheetService.getSheetCKDOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreatedate()).toString("yyyy-MM-dd"));
        model.addAttribute("sheet", sheet);

        ManageApply apply = this.sheetApplyService.getApplyOne(sheet.getExtendint1());
        model.addAttribute("apply", apply);

        List<SheetCKDETAIL> details = this.sheetCKService.printCKDetails(id);
        model.addAttribute("details", details);
        LayuiPage<Map<String, Object>> historicActivityInstances = activitiService.historyActInstanceList(taskId,0,100);
        List<Map<String,Object>> data = historicActivityInstances.getData();
				model.addAttribute("zhidanren", data.get(0).get("assignee"));
    }

    /**
     * 打印物资退库
     *
     * @param model
     * @param id
     * @param taskId 
     * @param response 
     * @param request 
     * @return
     */
    private void printWZTK(Model model, Integer id, HttpServletRequest request, HttpServletResponse response, String taskId) {
        TKPrint sheet = this.sheetService.getTKPrintSheetOne(id);
        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy-MM-dd"));
        model.addAttribute("sheet", sheet);
        List<TKDetail> details = this.detailService.printDetails(id, null, "TKDetail");
        model.addAttribute("details", details);
        LayuiPage<Map<String, Object>> historicActivityInstances = activitiService.historyActInstanceList(taskId,0,100);
        List<Map<String,Object>> data = historicActivityInstances.getData();
				model.addAttribute("zhidanren", data.get(0).get("assignee"));
				model.addAttribute("shenpi", data.get(data.size()-1).get("assignee"));
    }

    /**
     * 打印物资退货
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
     * 打印物资接收
     *
     * @param printType
     * @param model
     * @param id
     * @param response 
     * @param request 
     * @param taskId2 
     * @return
     */
    private String printWZJS(Integer printType, Model model, Integer id, HttpServletRequest request, HttpServletResponse response, String taskId) {
    	LayuiPage<Map<String, Object>> historicActivityInstances = activitiService.historyActInstanceList(taskId,0,100);
        List<Map<String,Object>> data = historicActivityInstances.getData();
        OrderPrint sheet = this.sheetService.getOrderPrintSheetOne(id);
        if (sheet != null) {
            sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy年MM月dd日 HH:mm:ss"));
        }
        model.addAttribute("sheet", sheet);
        String userName = sheetCGService.getSheetCGNameOne(sheet.getExtendInt1());
        model.addAttribute("userName", userName);
        List<SheetDetail> details = this.detailService.printDetails(id, null, "SheetDetail");
        model.addAttribute("details", details);
		if(data.size()>0){
			for (Map<String, Object> map : data) {
				if("机械动力部技术验收".equals(map.get("activityName")) || "技术质量部技术验收".equals(map.get("activityName"))){
					model.addAttribute("shenpi2", map.get("assignee"));
					model.addAttribute("comment2", map.get("comment"));
					model.addAttribute("activityName", map.get("activityName"));
				}
				if("制单人".equals(map.get("activityName"))){
					model.addAttribute("fqr", map.get("assignee"));
				}
				if("仪电中心".equals(map.get("activityName")) || "生产运营指挥中心".equals(map.get("activityName")) || "热动中心".equals(map.get("activityName")) || "机械动力部".equals(map.get("activityName")) || "蒙大能源环保".equals(map.get("activityName")) || "公共工程".equals(map.get("activityName")) || "供销中心".equals(map.get("activityName")) || "财务部".equals(map.get("activityName")) || "计划发展部".equals(map.get("activityName")) || "甲醇制造中心".equals(map.get("activityName")) || "综合办公室".equals(map.get("activityName")) || "技术质量部".equals(map.get("activityName")) || "安全环保部".equals(map.get("activityName")) || "党群工作部".equals(map.get("activityName"))){
					model.addAttribute("shenpi1", map.get("assignee"));
					model.addAttribute("comment1", map.get("comment"));
				}
				if("采购员".equals(map.get("activityName"))){
					model.addAttribute("zuihou", map.get("assignee"));
					model.addAttribute("commentz", map.get("comment"));
				}
			}
		}
        
        if (printType.equals(PrintEnums.WZJSEnum.EQUIPMENT_SPARE.getType())) {
        	
//        	if(data.size()>0){
//				if(data.size()>3){//
//					
//					if(data.get(0).get("assignee").toString().equals(data.get(data.size()-4).get("assignee").toString())){
//						model.addAttribute("shenpi1", data.get(data.size()-3).get("assignee"));
//						model.addAttribute("comment1", data.get(data.size()-3).get("comment"));
//						
//					}else{
//						model.addAttribute("shenpi1", data.get(data.size()-2).get("assignee"));
//						model.addAttribute("comment1", data.get(data.size()-2).get("comment"));
//					}
//				}else{
//					model.addAttribute("shenpi1", data.get(data.size()-2).get("assignee"));
//					model.addAttribute("comment1", data.get(data.size()-2).get("comment"));
//				}
//				model.addAttribute("zuihou", data.get(data.size()-1).get("assignee"));
//				model.addAttribute("commentz", data.get(data.size()-1).get("comment"));
//			}
            return "WZJSEquipmentSpare";
        } else if (printType.equals(PrintEnums.WZJSEnum.ORIGINAL_AUXILIARY.getType())) {
//			if(data.size()>0){
//				model.addAttribute("fqr", data.get(0).get("assignee"));
//				model.addAttribute("shenpi1", data.get(data.size()-2).get("assignee"));
//				model.addAttribute("comment1", data.get(data.size()-2).get("comment"));
//				model.addAttribute("zuihou", data.get(data.size()-1).get("assignee"));
//				model.addAttribute("commentz", data.get(data.size()-1).get("comment"));
//			}
            
				
            return "WZJSoriginalAuxiliary";
        } else if (printType.equals(PrintEnums.WZJSEnum.SAFETY_MATERIALS.getType())) {
//        	if(data.size()>0){
//				model.addAttribute("fqr", data.get(0).get("assignee"));
//				model.addAttribute("shenpi1", data.get(data.size()-2).get("assignee"));
//				model.addAttribute("comment1", data.get(data.size()-2).get("comment"));
//				model.addAttribute("zuihou", data.get(data.size()-1).get("assignee"));
//				model.addAttribute("commentz", data.get(data.size()-1).get("comment"));
//			}
            return "WZJSafetyMaterials";
        } else {
//        	if(data.size()>0){
//				model.addAttribute("fqr", data.get(0).get("assignee"));
//				model.addAttribute("shenpi1", data.get(data.size()-2).get("assignee"));
//				model.addAttribute("comment1", data.get(data.size()-2).get("comment"));
//				model.addAttribute("zuihou", data.get(data.size()-1).get("assignee"));
//				model.addAttribute("commentz", data.get(data.size()-1).get("comment"));
//			}
            return "WZJOtherMaterials";
        }
    }

    /**
     * 打印接收入库
     *
     * @param model
     * @param id
     * @param response 
     * @param request 
     * @param taskId 
     * @return
     */
    private void printJSRK(Model model, Integer id, String taskId, HttpServletRequest request, HttpServletResponse response) {
    	LayuiPage<Map<String, Object>> historicActivityInstances = activitiService.historyActInstanceList(taskId,0,100);
        List<Map<String,Object>> data = historicActivityInstances.getData();
    	SheetRKD sheet = this.sheetService.getRkSheetOne(id);
        if (!StringUtils.isEmpty(sheet)) {
            sheet.setCreateDateStr(new DateTime(sheet.getCreatedate()).toString("yyyy年MM月dd日 HH:mm:ss"));
        }
        model.addAttribute("sheet", sheet);
        List<RkDetailPrint> rkDetailPrints = this.detailService.printDetails(id, null, "RkDetailPrint");

        List<RkDetailPrint> details = new ArrayList<>();
//        String str = "";
//        for (RkDetailPrint rkDetailPrint : rkDetailPrints) {
//            if(!rkDetailPrint.getExtendString1().equals(str)){
//                str= rkDetailPrint.getExtendString1();
//                details.add(rkDetailPrint);
//            }
//        }
//        model.addAttribute("fqrName", data.get(0).get("assignee"));//发起人
//        model.addAttribute("depName", data.get(data.size()-1).get("assignee"));//最后审批节点（部门负责人）
        for (Map<String, Object> map : data) {
        	if("库管员".equals(map.get("activityName"))){
        		model.addAttribute("fqrName", map.get("assignee"));//发起人
			}
			if("采购员".equals(map.get("activityName"))){
				model.addAttribute("cgyName", map.get("assignee"));
			}
			if("库房负责人".equals(map.get("activityName"))||"库房负责人".equals(map.get("activityName"))){
				model.addAttribute("cczgName", map.get("assignee"));
			}
			if("采购主管".equals(map.get("activityName"))){
				model.addAttribute("cgzgName", map.get("assignee"));
			}
			if("部门负责人审核".equals(map.get("activityName"))){
				model.addAttribute("depName", map.get("assignee"));
			}
		}
        

        model.addAttribute("details", rkDetailPrints);
    }

}
