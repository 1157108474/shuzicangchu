package com.zthc.ewms.sheet.controller.guard;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.sheet.service.SheetService;
import com.zthc.ewms.system.log.service.LogService;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hckj.base.database.jdbc.tools.PageHelper;
import com.zthc.ewms.sheet.dao.guard.SheetDetailDaoGuard;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.hckj.base.mvc.BaseController;
import com.hckj.base.mvc.BaseLocal;

import drk.system.Log;
import org.springframework.web.bind.annotation.ResponseBody;

public class SheetDetailControllerGuard {

	@Resource(name="sheetDetailService")
	public SheetDetailService service;

	@Resource(name="logService")
	public LogService logService;

	@Resource(name = "sheetService")
    public SheetService sheetService;

	private final static Log log;

	static{
		log = Log.getLog("com.zthc.ewms.sheetdetail.controller.guard.SheetDetailControllerGuard");
	}

	/**
	 * 表单提交时，字段所用前缀，只提交一个对象时，可以不写此前缀
	 */
	@InitBinder("detail")
    public void initSheetDetailBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("detail.");
    }

	/**
	 * 管理页面
	 */
	@RequestMapping(value="/manageSheetDetail.htm")
	public String manageSheetDetail(@ModelAttribute("detail") SheetDetail obj, SheetDetailCondition condition,
                                    HttpServletRequest request, HttpServletResponse response, Model model) {
		return "ewms/sheet/view/manageSheetDetail";
	}
	/**
     *新增
	 */
	@RequestMapping(value="/addSheetDetail.htm")
	public String addSheetDetail(@ModelAttribute("detail") SheetDetail obj, SheetDetailCondition condition,
                                 HttpServletRequest request, HttpServletResponse response, Model model) {
		return editSheetDetail(obj,condition,request,response,model);
	}

	/**
	 * 编辑页面
	 */
	@RequestMapping(value="/editSheetDetail.htm")
	public String editSheetDetail(@ModelAttribute("detail") SheetDetail obj, SheetDetailCondition condition,
                                  HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("detail", obj);
		return "ewms/sheet/view/editSheetDetail";
	}

	/**
	 * 保存记录
	 */
	@RequestMapping(value="/saveSheetDetail.htm")
	public void saveSheetDetail(@ModelAttribute("detail") SheetDetail obj, SheetDetailCondition condition,
                                HttpServletRequest request, HttpServletResponse response, Model model) {

		String ret = "f";
		try{
			long userId = 0L;
			if(request.getSession().getAttribute("userId") != null){
				userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
			}
			BaseLocal local = SheetDetailDaoGuard.getThreadLocal();
			local.setCurrentUserId(userId); //把当前操作人ID存入当前线程对象中
			SheetDetailDaoGuard.setThreadLocal(local);

			this.service.saveSheetDetail(obj, condition);
		}
		catch(Exception e){
			log.error("保存记录出错！");
			log.errorPrintStacktrace(e);
		}
		BaseController.print(response, ret);
	}


	/****************************添加方法****************************************/
	//select条件列表

	//update
	//delete
	/**
	 * 删除明细
	 */
	@RequestMapping(value = "/delSheetDetails.json")
	@ResponseBody
	public HttpResponse<SheetDetailCondition> delSheetDetails(SheetDetailCondition condition, HttpServletRequest request) {
		HttpResponse<SheetDetailCondition> ret = new HttpResponse(HttpResponse.Status.FAILURE, "删除明细失败！", condition);
		try {
			this.service.delDetails(condition);
			//设置返回参数
			ret = new HttpResponse(HttpResponse.Status.SUCCESS, "删除明细成功！", condition);
		} catch (Exception e) {
			log.error("删除明细失败！");
			log.errorPrintStacktrace(e);
		}
		return ret;
	}
	//jump
}
