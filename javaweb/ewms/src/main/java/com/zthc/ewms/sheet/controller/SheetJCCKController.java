package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.service.*;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.activiti.engine.task.Task;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/sheet/jcwzck")
public class SheetJCCKController extends SheetControllerGuard {
    @Resource(name= "sheetCKService")
    public SheetCKService service;

    @Resource(name = "activitiService")
    public ActivitiService activitiService;

    @Resource(name = "sheetService")
    public SheetService sheetService;

    @Resource(name = "sheetJCCKService")
    public SheetJCCKService sheetJCCKService;

    @Resource(name = "sheetDetailService")
    public SheetDetailService detailService;

    @Resource(name = "dictionaryService")
    public DictionaryService dictionaryService;

    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.sheet.controller.SheetJCCKController");
    }

	 /*-------------------------------------------------跳转方法-------------------------------------------------*/

    @RequestMapping(value = "/jcwzck.htm")
    public String jcwzck(@ModelAttribute("sheet") SheetJCCK obj, SheetCKCondition condition, HttpServletRequest request, Model model) {
        log.debug("进入新增寄存物资出库单页面,当前方法名:jcwzck");





        HttpSession session = request.getSession();
        String userName = "";
        String departName = "";
        Integer userId = 0;

        String userCode = "";
        if (session.getAttribute("userId") != null) {
            userCode = session.getAttribute("userCode").toString();

            userId = Integer.valueOf(session.getAttribute("userId").toString());
            userName = session.getAttribute("userName").toString();
            departName = session.getAttribute("departName").toString();
        }
        obj.setDepName(departName);
        obj.setPersonName(userName);
        obj.setCreateDateStr(new DateTime().toString("yyyy年MM月dd日 HH:mm:ss"));
        model.addAttribute("sheet", obj);
        //获取菜单Code 用于工作流
        String menuCode = request.getParameter("menuCode");
        List<Dictionary> dictionaries = this.activitiService.getPartButton(menuCode,userCode);
        model.addAttribute("buttons", dictionaries);
        model.addAttribute("menuCode", menuCode);

        return "sheet/jcwzck/jcwzck";
    }


    @RequestMapping(value = "/manageJcWzck.htm")
    public String manageJcWzck(HttpServletRequest request, Model model) {
        //获取单据状态字典表
        List<Dictionary> dictionaries = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType
                .ReceiptStatus.getCode());
        model.addAttribute("orderStatus", dictionaries);
        return "sheet/jcwzck/manageJcWzck";
    }
    /**
     * 页面_已添加明细列表
     */
    @RequestMapping(value = "detailsJCWZCKList", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<SheetJCCKDetails> getDetails(SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入物资寄存出库获取已添加明细列表方法,当前方法名：detailsJCWZCKList");
        LayuiPage<SheetJCCKDetails> ret = null;
        String sheetId = null;
        try {
            sheetId = request.getParameter("sheetId");
            ret = this.detailService.sheetDetails(sheetId, condition, "SheetJCCKDetails");
        } catch (Exception e) {
            log.error("获取已添加明细数据列表出错！sheetId：" + sheetId);
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    /**
     * 获取寄存出库新增明细列表
     *
     * @param details
     * @param condition
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listDetails.json", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<SheetJCCKList> detailsList(@ModelAttribute("jcck") SheetJCCKList details, SheetCKCondition
            condition, HttpServletRequest request, HttpServletResponse response) {
        log.debug("进入获取寄存物资出库新增明细列表方法,当前方法名:listDetails");
        LayuiPage<SheetJCCKList> ret = null;
        try {
            ret = this.sheetJCCKService.detailsList(details, condition);
        } catch (Exception e) {
            log.error("获取物资寄存出库新增明细列表方法失败");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    /**
     * 添加物料明细页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/addDetails.htm")
    public String addDetails(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer ztId = null;
        if (session.getAttribute("userId") != null) {
            ztId = Integer.parseInt(session.getAttribute("ztId").toString());
        }
        List<WareHouse> list = wareHouseService.getStores(ztId);
        model.addAttribute("stores", list);
        return "sheet/jcwzck/detailed";
    }

    /**
     * 编辑物料明细页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editDetail.htm" )
    public String editDetailCount(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer ztId = null;
        if (session.getAttribute("userId") != null) {
            ztId = Integer.parseInt(session.getAttribute("ztId").toString());
        }
        String id = request.getParameter("id");
        SheetDetail sheetDetail=this.detailService.getSheetDetailOne(Integer.parseInt(id));
        List<WareHouse> list = wareHouseService.getStores(ztId);
        request.setAttribute("stores", list);
        request.setAttribute("sheetDetail",sheetDetail);
        return "sheet/jcwzck/detailed";
    }

	 /*-------------------------------------------------基础方法-------------------------------------------------*/
    /**
     * 管理页面单据列表
     */
    @RequestMapping(value = "/listManageJcwzck.json", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<SheetJCCK> getSheets(Sheet obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("进入寄存物资出库获取列表方法，当前方法名:listManageJcwzck");
        LayuiPage<SheetJCCK> ret = null;
        try {
            String data = request.getParameter("appFlag");
            String begin = null;
            String end = null;
            boolean appFlag = false;
            Integer userId = 0;
            if (data != null && "1".equals(data)) {
                appFlag = true;
                userId = Integer.parseInt(request.getParameter("userId"));
            } else {
                if (request.getSession().getAttribute("userId") != null) {
                    userId = (Integer) request.getSession().getAttribute("userId");
                }
                begin = request.getParameter("begin");
                end = request.getParameter("end");
            }
            ret = this.sheetService.sheetList(obj, condition, "SheetJCCK", userId, begin, end, appFlag);
        } catch (Exception e) {
            log.error("获取单据列表出错！");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "/editDetail",method = RequestMethod.POST)
    public HttpResponse editDetail(HttpServletRequest request, HttpServletResponse response){
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "修改失败", null);

        JSONArray detailJson = JSONArray.fromObject(request.getParameter("details"));
        Collection collection = JSONArray.toCollection(detailJson);
        if (collection != null && !collection.isEmpty()) {
            Iterator it = collection.iterator();
            JSONObject jsonObj = JSONObject.fromObject(it.next());
            SheetDetail sheetDetail1 = (SheetDetail) JSONObject.toBean(jsonObj, SheetDetail.class);
            int data = sheetJCCKService.editSheetDatailed(sheetDetail1);
            if(data>0){
                ret = new HttpResponse(HttpResponse.Status.SUCCESS,"修改成功",null);
            }
        }
        return ret;
    }
    @ResponseBody
    @RequestMapping(value = "/editSheet",method = RequestMethod.POST)
    public HttpResponse editSheet( Sheet obj,HttpServletRequest request, HttpServletResponse response){
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "修改失败", null);

        int data = sheetJCCKService.editSheet(obj);
        if(data>0){
            ret = new HttpResponse(HttpResponse.Status.SUCCESS,"修改成功",null);
        }
        return ret;
    }
    /**
     * 页面_编辑
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {
        SheetJCCK sheetJCCK = this.sheetService.getJCCKSheetOne(id);
        sheetJCCK.setCreateDateStr(new DateTime(sheetJCCK.getCreateDate()).toString("yyyy年MM月dd日 HH:mm:ss"));
        model.addAttribute("sheet", sheetJCCK);
        String oper = request.getParameter("oper");
        String taskId = null;
        Task task = null;
        if (StringUtils.isEmpty(oper)) {
            taskId = request.getParameter("taskId");
            task = taskService.createTaskQuery().taskId(taskId).singleResult();
            if (task == null) {
                request.setAttribute("taskId", taskId);
                return "/system/activitiListen/showProcessComplete";
            }
            List<Dictionary> dictionaries = activitiService.getPartButtonOnStar(taskId);
            model.addAttribute("buttons", dictionaries);
        }else if ("edit".equals(oper)) {
            taskId = activitiService.getTaskByPi(sheetJCCK.getRouteId().intValue()+"");
            List<Dictionary> dictionaries = activitiService.getPartButtonOnStar(taskId);
            model.addAttribute("buttons", dictionaries);
        }else if ("show".equals(oper)) {
            taskId = activitiService.getTaskByPi(sheetJCCK.getRouteId().intValue()+"");
        }
        model.addAttribute("taskId", taskId);
        /*String taskId = activitiService.getTaskByPi(sheetJCCK.getRouteId().intValue()+"");
        List<Dictionary> dictionaries = activitiService.getPartButtonOnStar(taskId);
        model.addAttribute("buttons", dictionaries);*/
        return this.getUrl(request, sheetJCCK.getRouteId().intValue() + "", model, "sheet/jcwzck/jcwzck", sheetJCCK
                .getStatus());

    }
}	

	
