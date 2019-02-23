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
	 * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
	 */
	@InitBinder("detail")
    public void initSheetDetailBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("detail.");
    }

	/**
	 * ����ҳ��
	 */
	@RequestMapping(value="/manageSheetDetail.htm")
	public String manageSheetDetail(@ModelAttribute("detail") SheetDetail obj, SheetDetailCondition condition,
                                    HttpServletRequest request, HttpServletResponse response, Model model) {
		return "ewms/sheet/view/manageSheetDetail";
	}
	/**
     *����
	 */
	@RequestMapping(value="/addSheetDetail.htm")
	public String addSheetDetail(@ModelAttribute("detail") SheetDetail obj, SheetDetailCondition condition,
                                 HttpServletRequest request, HttpServletResponse response, Model model) {
		return editSheetDetail(obj,condition,request,response,model);
	}

	/**
	 * �༭ҳ��
	 */
	@RequestMapping(value="/editSheetDetail.htm")
	public String editSheetDetail(@ModelAttribute("detail") SheetDetail obj, SheetDetailCondition condition,
                                  HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("detail", obj);
		return "ewms/sheet/view/editSheetDetail";
	}

	/**
	 * �����¼
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
			local.setCurrentUserId(userId); //�ѵ�ǰ������ID���뵱ǰ�̶߳�����
			SheetDetailDaoGuard.setThreadLocal(local);

			this.service.saveSheetDetail(obj, condition);
		}
		catch(Exception e){
			log.error("�����¼����");
			log.errorPrintStacktrace(e);
		}
		BaseController.print(response, ret);
	}


	/****************************��ӷ���****************************************/
	//select�����б�

	//update
	//delete
	/**
	 * ɾ����ϸ
	 */
	@RequestMapping(value = "/delSheetDetails.json")
	@ResponseBody
	public HttpResponse<SheetDetailCondition> delSheetDetails(SheetDetailCondition condition, HttpServletRequest request) {
		HttpResponse<SheetDetailCondition> ret = new HttpResponse(HttpResponse.Status.FAILURE, "ɾ����ϸʧ�ܣ�", condition);
		try {
			this.service.delDetails(condition);
			//���÷��ز���
			ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ɾ����ϸ�ɹ���", condition);
		} catch (Exception e) {
			log.error("ɾ����ϸʧ�ܣ�");
			log.errorPrintStacktrace(e);
		}
		return ret;
	}
	//jump
}
