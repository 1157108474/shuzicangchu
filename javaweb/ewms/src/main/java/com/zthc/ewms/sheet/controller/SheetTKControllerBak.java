package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;
import com.zthc.ewms.sheet.entity.tk.TK;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import drk.system.Log;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

//@Controller
@RequestMapping("/sheet/tk")
public class SheetTKControllerBak extends SheetControllerGuard {

	private final static Log log= Log.getLog("com.hckj.ewms.orderinfo.controller.SheetCKControllerGuard");
	@Resource(name = "dictionaryService")
	public DictionaryService dictionaryService;
	/**
	 * ���������˿ⵥҳ��
	 */
	@RequestMapping(value = "/sheetTK.htm")
	public String sheetTK(HttpServletRequest request, Model model) {
		//��ȡ��¼�ˡ���¼��Ip
		HttpSession session = request.getSession();
		Object officesNameStr = session.getAttribute("officesName");
		String officesName = (null == officesNameStr ? null : officesNameStr.toString());
		Object parentNameStr = session.getAttribute("parentName");
		String parentName = (null == parentNameStr ? null : parentNameStr.toString());
		//��ȡ�ֵ�
		List<Dictionary> tkTypes = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.TKTYPE.getCode());
		//��ȡ�ֵ�
		List<Dictionary> fundSources = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());

		model.addAttribute("tkTypes", tkTypes);
		model.addAttribute("fundSources", fundSources);
		model.addAttribute("officesName", officesName);
		model.addAttribute("parentName", parentName);
		return "sheet/tk/sheetTK";
	}


	/**
	 * ҳ��-��ϸ�б�
	 */
	@RequestMapping(value="/listSheetTK.json")
	@ResponseBody
	public LayuiPage<TK> listSheetTK(Sheet obj, SheetCondition condition, HttpServletRequest request)  {
		log.debug("�����ȡ�б���");
		LayuiPage<TK> ret = null;
		try {
//			ret = this.service.sheetList(obj, condition,"TK");
		} catch (Exception e) {
			log.error("��ȡ�����б����");
			log.errorPrintStacktrace(e);
		}
		return ret;
	}
	/**
	 * �����˿ⵥ����ҳ��
	 */
	@RequestMapping(value = "/manageSheetTK.htm")
	public String manageSheetTK(HttpServletRequest request, Model model) {
		List<Dictionary> dictionaries = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());
		model.addAttribute("dictionaries",dictionaries);

		return "sheet/tk/manageSheetTK";
	}
	/**
	 * ����ҳ�浥���б�
	 */
	@RequestMapping(value="/listManageTK.json")
	@ResponseBody
	public LayuiPage<TK> listManageTK(Sheet obj, SheetCondition condition, HttpServletRequest request)  {
		log.debug("�����ȡ�б���");
		LayuiPage<TK> ret = null;
		try {
			String  data = request.getParameter("appFlag");
			String begin = request.getParameter("begin");
			String end = request.getParameter("end");
			boolean appFlag = false;
			Integer userId = 0;
			if(data!=null && "1".equals(data)){
				appFlag=true;
				userId = Integer.parseInt(request.getParameter("userId"));
			}else{
				if(request.getSession().getAttribute("userId")!=null){
					userId = (Integer)request.getSession().getAttribute("userId");
				}
			}
			ret = this.service.sheetList(obj, condition,"ManageTK",userId,begin,end,appFlag);
		} catch (Exception e) {
			log.error("��ȡ�����б����");
			log.errorPrintStacktrace(e);
		}
		return ret;
	}
	/**
	 * ������ϸҳ��
	 */
	@RequestMapping(value = "/openDetailWindow.htm")
	public String openDetailWindow() {
		return "sheet/tk/detailed";
	}

}	
	
	
