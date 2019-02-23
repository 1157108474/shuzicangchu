package com.zthc.ewms.system.formTemplateManage.controller;

import com.hckj.base.mvc.HttpResponse;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.activitiManage.entity.ActDic;
import com.zthc.ewms.system.activitiManage.service.ActdicService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.service.FormTemplateService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.service.MenuService;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/formTemplate/formTemplateManage")
public class FormTemplateController {

	private final static Log log = Log.getLog("com.zthc.ewms.system.formTemplateManage.controller");


	@Resource(name = "dictionaryService")
	public DictionaryService dictionaryService;

	@Resource(name = "formTemplateService")
	public FormTemplateService formTemplateService;

	@Resource(name = "menuService")
	private MenuService menuService;

	@Resource(name = "actdicService")
	public ActdicService actdicService;

	@Resource(name = "logService")
	public LogService logService;

	@RequestMapping("/formTemplateList.json")
	@ResponseBody
	public LayuiPage<FormTemplate> formTemplateList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.debug("进入获取列表formTemplateList方法");
		String dicID = request.getParameter("id");
		LayuiPage<FormTemplate> formTemplates = formTemplateService.getFromTemplateList(dicID);
		return formTemplates;
	}

	/**
	 * 查询单据模板和数据字典
	 */
	@RequestMapping(value = "formTemplate.htm")
	public String formTemplate(HttpServletRequest request,HttpServletResponse response) {
		log.debug("进入查询单据模板和数据字典formTemplate方法");
		int formTemDicPid = DictionaryEnums.DicType.ReceiptType.getCode();
		List<Dictionary> actDics = dictionaryService.getDicListByParentId(formTemDicPid);
		request.setAttribute("tree", actDics);
		return "system/formTemplateManage/manageFormTemplate";
	}

	@RequestMapping(value = "addFormTemplate.htm")
	public String addFormTemplate(HttpServletRequest request,HttpServletResponse response) {
		log.debug("进入addFormTemplate方法");
		String id = request.getParameter("tem_dic");
		Dictionary dictionary = dictionaryService.getDic( Integer.parseInt(id));
		//List<Dictionary> actDics = dictionaryService.getDicListByParentId(30);
		//request.setAttribute("actDics",actDics);
		request.setAttribute("dictionary",dictionary);
		return "system/formTemplateManage/addFormTemplate";
	}

	/**
	 * 获取流程名称
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "generalTemplateName.htm")
	public String generalTemplateName(HttpServletRequest request,HttpServletResponse response){
		log.debug("进入获取流程名称generalTemplateName方法");
		int proDicPid = DictionaryEnums.DicType.ReceiptProcess.getCode();
		List<Dictionary> actDics = dictionaryService.getDicListByParentId(proDicPid);
		List<Map<String,Object>>  ret = new ArrayList<>();
		for(Dictionary dic : actDics){
			Map<String,Object> map = new HashMap<>();
			ActDic actDic = actdicService.getActDicBydicID(dic.getId()+"");
			if(actDic != null){
				map.put("id",actDic.getDicID());
				map.put("name",dic.getName());
				ret.add(map);
			}
		}
		request.setAttribute("actDics",ret);
		return "system/formTemplateManage/generalTemplateName";
	}

	@RequestMapping(value = "addFormTem.json")
	@ResponseBody
	public HttpResponse addFormTem(FormTemplate obj, HttpServletRequest request, HttpServletResponse response) {
		HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "保存失败", obj);
		log.debug("进入添加单据addFormTem方法");
		HttpSession session = request.getSession();
		try {
			List<FormTemplate> formTemplates = formTemplateService.getFromTemByMenuStatus(obj.getFormTemMenu(),2);
			if(formTemplates.size()<=0){
				List<FormTemplate> formTemplates2 = formTemplateService.getFromTemByCardStatus(obj.getFormTemCard(),2);
				if(formTemplates2.size()<=0){
					formTemplateService.save(obj);
					long userId = (null == session.getAttribute("userId") ?
							0L : Long.parseLong(session.getAttribute("userId").toString()));
					String userIp = (null == session.getAttribute("userIp") ?
							null : session.getAttribute("userIp").toString());
					ret = new HttpResponse(HttpResponse.Status.SUCCESS, "保存成功", obj);
					this.logService.addSystemLog(0, SystemLogEnums.LogObject.DOCUMENT_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
							"新增单据:" + obj.getFormTemName(), userIp, (int)userId);
				}else{
					ret = new HttpResponse(HttpResponse.Status.FAILURE, "该编码已存在", obj);
				}
			}else{
				ret = new HttpResponse(HttpResponse.Status.FAILURE, "该菜单已关联单据", obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("新增单据出错！");
			log.errorPrintStacktrace(e);
		}
		return ret;
	}

	@RequestMapping(value = "editFormTemplate.htm")
	public String editFormTemplate(HttpServletRequest request,HttpServletResponse response) {
		log.debug("进入editFormTemplate方法");
		String id = request.getParameter("id");
		FormTemplate formTemplate = formTemplateService.getFromTemByID(Integer.parseInt(id));//查询当前对象
		Dictionary dictionary = dictionaryService.getDic(formTemplate.getFormTemDic());//单据种类
		Menu menu = menuService.getMenuOne(formTemplate.getFormTemMenu());//菜单名称
		//Dictionary processDefinition =  dictionaryService.getDic(Integer.parseInt(actdicService.getActDicByModelID(formTemplate.getProcessDefinitionKey()).get(0).getDicID()));//liucheng
		Dictionary processDefinition =  dictionaryService.getDic(Integer.parseInt(formTemplate.getProcessDefinitionKey()));
		//List<Dictionary> actDics = dictionaryService.getDicListByParentId(30);
		request.setAttribute("processDefinition",processDefinition);
		request.setAttribute("menu",menu);
		request.setAttribute("formTemplate",formTemplate);
		request.setAttribute("dictionary",dictionary);
		return "system/formTemplateManage/editFormTemplate";
	}
	@RequestMapping(value = "editFormTem.json")
	@ResponseBody
	public HttpResponse editFormTem(FormTemplate obj, HttpServletRequest request, HttpServletResponse response) {
		HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "修改失败", obj);
		log.debug("进入修改单据editFormTemplate方法");
		HttpSession session = request.getSession();
		try {
			List<FormTemplate> formTemplates = formTemplateService.getFromTemByMenuStatusNothis(obj.getId(),obj.getFormTemMenu(),2);
			if(formTemplates.size()<=0){
				List<FormTemplate> formTemplates2 = formTemplateService.getFromTemByCardStatusNothis(obj.getId(),obj.getFormTemCard(),2);
				if(formTemplates2.size()<=0){
					long userId = (null == session.getAttribute("userId") ?
							0L : Long.parseLong(session.getAttribute("userId").toString()));
					String userIp = (null == session.getAttribute("userIp") ?
							null : session.getAttribute("userIp").toString());
					formTemplateService.edit(obj);
					ret = new HttpResponse(HttpResponse.Status.SUCCESS, "修改成功", obj);
					this.logService.addSystemLog(0, SystemLogEnums.LogObject.DOCUMENT_MANAGEMENT.getCode(), SystemLogEnums.LogAction.EDIT.getCode(),
							"编辑单据:" + obj.getFormTemName(), userIp, (int)userId);
				}else{
					ret = new HttpResponse(HttpResponse.Status.FAILURE, "该编码已存在", obj);
				}
			}else{
				ret = new HttpResponse(HttpResponse.Status.FAILURE, "该菜单已关联单据", obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改单据出错！");
			log.errorPrintStacktrace(e);
		}
		return ret;
	}
	@RequestMapping(value = "delFromTem.json")
	public void delFromTem(FormTemplate obj, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("进入删除单据delFromTem方法");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String msg = "删除失败";
		String idStr = request.getParameter("ids");
		String[] ids = idStr.split(",");
		Integer[] intTemp = new Integer[ids.length];
		for (int i = 0; i <intTemp.length; i++){
			intTemp[i] = Integer.parseInt(ids[i]);
		}
		try {
			HttpSession session = request.getSession();
			long userId = (null == session.getAttribute("userId") ?
					0L : Long.parseLong(session.getAttribute("userId").toString()));
			String userIp = (null == session.getAttribute("userIp") ?
					null : session.getAttribute("userIp").toString());
			int i = formTemplateService.deleteById(intTemp);
			if(i>0){
				msg = "删除成功";
				logService.addSystemLog(0, SystemLogEnums.LogObject.DOCUMENT_MANAGEMENT.getCode(),
						SystemLogEnums.LogAction.DELETE.getCode(), "删除单据:rid=" + idStr,
						userIp, (int)userId);
			}
			/*for (String id : ids){
				 formTemplateService.deleteById(Integer.parseInt(id));
            }*/

		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除单据失败！");
			log.errorPrintStacktrace(e);
		}
		//Object obj= JSONArray.fromObject(roles.toArray());
		//System.out.println(obj);
		out.println(msg);
		out.flush();
		out.close();
	}
}
