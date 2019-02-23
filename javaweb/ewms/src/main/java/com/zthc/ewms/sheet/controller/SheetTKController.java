package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.tk.TK;
import com.zthc.ewms.sheet.entity.tk.TKDetail;
import com.zthc.ewms.sheet.entity.tk.ZJTKDetail;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sheet/tk")
public class SheetTKController extends SheetControllerGuard {

    private final static Log log = Log.getLog("com.zthc.ewms.sheet.controller.SheetCKControllerGuard");
    @Resource(name = "dictionaryService")
    public DictionaryService dictionaryService;

    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;


    @Resource(name = "userScopeService")
    private UserScopeService userScopeService;

    /**
     * 页面_新增
     */
    @RequestMapping("add")
    public String add(@ModelAttribute("sheet") TK obj, SheetCondition condition,
                      HttpServletRequest request, HttpServletResponse response, Model model) {



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
        obj.setZtidName(departName);
        obj.setPersonName(userName);
        //   obj.setCreateDate(new Date());
        obj.setCreateDateStr(new DateTime().toString("yyyy年MM月dd日 HH:mm:ss"));

        List<Dictionary> tkTypes = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.TKTYPE.getCode());
        model.addAttribute("tkTypes", tkTypes);

        model.addAttribute("sheet", obj);
        //查询字典，获取资金来源
        List<Dictionary> fundsSources = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());
        model.addAttribute("fundsSources", fundsSources);
        String menuCode = request.getParameter("menuCode");
        model.addAttribute("menuCode", menuCode);

        List<Dictionary> dictionaries = activitiService.getPartButton(menuCode,userCode);//查第一环节按钮
        model.addAttribute("buttons", dictionaries);
        return "sheet/tk/sheetTK";
    }


    /**
     * 页面_编辑
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {
        TK sheet = this.service.getTKSheetOne(id);
        model.addAttribute("sheet", sheet);
        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy年MM月dd日 HH:mm:ss"));
        model.addAttribute("sheet", sheet);
        List<Dictionary> fundsSources = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());
        model.addAttribute("fundsSources", fundsSources);
        return this.getUrl(request, sheet.getRouteId().intValue() + "", model, "sheet/tk/sheetTK", sheet.getStatus());

    }

    /**
     * 页面_已添加明细列表
     */
    @RequestMapping(value = "details", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<TKDetail> getDetails(SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取已添加明细列表方法");
        LayuiPage<TKDetail> ret = null;
        String sheetId = null;
        try {
            sheetId = request.getParameter("sheetId");
            ret = this.detailService.sheetDetails(sheetId, condition, "TKDetail");
        } catch (Exception e) {
            log.error("获取已添加明细数据列表出错！sheetId：" + sheetId);
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }


    /**
     * 添加明细按钮事件，打开添加明细页面
     */
    @RequestMapping("openAddDetail")
    public String openAddDetail(@ModelAttribute("sheet") Sheet obj, SheetCondition condition,
                                HttpServletRequest request, HttpServletResponse response, Model model) {

        return "sheet/tk/detailed";
    }

    @RequestMapping(value = "getAddDetails", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<ZJTKDetail> listDetails(SheetDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<ZJTKDetail> ret = null;
        try {
            Integer ztid = null;
//			HttpSession session = request.getSession();
//			if (session.getAttribute("ztId") != null) {
//				ztid = Integer.parseInt(session.getAttribute("ztId").toString());
//			}

            ret = this.detailService.addDetailList(obj, condition, ztid, "ZJTKDetail");
        } catch (Exception e) {
            log.error("获取任务管理数据列表出错！");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }


    /**
     * 页面_管理
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manageTK(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Integer userId = 0;
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        }
        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        model.addAttribute("departList", departList);

        List<Dictionary> statusList = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.ReceiptStatus.getCode());
        model.addAttribute("statusList", statusList);
        return "sheet/tk/manageSheetTK";
    }

    /**
     * 管理页面单据列表
     */
    @RequestMapping(value = "sheets", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<TK> getSheets(Sheet obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("进入获取列表方法");
        LayuiPage<TK> ret = null;
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
            ret = this.service.sheetList(obj, condition, "TK", userId, begin, end, appFlag);
        } catch (Exception e) {
            log.error("获取单据列表出错！");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }
    /**
     * 更新成本
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/renewalCost.json")
    @ResponseBody
    public HttpResponse renewalCost(Integer id, HttpServletRequest request) {
        //获取登录人、登录人Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        TK sheetTK = service.getTKSheetOne(id);
        int userId = (null == userIdStr ? 0 : Integer.parseInt(userIdStr.toString()));
        try {
            service.asynRenewalCost(sheetTK,userId);
            return new HttpResponse(HttpResponse.Status.SUCCESS, "更新退库成功！",null);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            return new HttpResponse(HttpResponse.Status.FAILURE, "更新退库失败！",null);
        }catch (Exception e) {
            log.error("更新失败！");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            return new HttpResponse(HttpResponse.Status.FAILURE, "更新退库成功！",null);
        }
    }
}

	
