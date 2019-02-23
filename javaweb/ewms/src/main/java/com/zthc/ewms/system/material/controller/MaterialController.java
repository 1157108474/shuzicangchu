package com.zthc.ewms.system.material.controller;

import com.hckj.base.mvc.HttpResponse;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.material.controller.guard.MaterialControllerGuard;
import com.zthc.ewms.system.material.entity.guard.Material;
import com.zthc.ewms.system.material.entity.guard.MaterialCondition;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/system/material")
public class MaterialController extends MaterialControllerGuard {
	@Resource(name = "logService")
	public LogService logService;
	private final static Log log;
	
	static{
		log = Log.getLog("com.zthc.ewms.system.material.controller.MaterialController");
	}
	/**
	 * 获取列表数据
	 */
//	@RequestMapping("/listMaterial.json")
	@RequestMapping(value="/materials",method = RequestMethod.GET)
	@ResponseBody
	public LayuiPage<Material> listMaterial(Material obj, MaterialCondition condition, HttpServletRequest request)  {
		log.debug("进入获取列表方法");
		LayuiPage<Material> ret = null;
		try {
			Integer orgId = null;
//			Object data = request.getSession().getAttribute("orgId");
//			if (data != null) {
//				if(!Boolean.valueOf(request.getSession().getAttribute("isAdmin").toString())){
//					orgId = Integer.parseInt(data.toString());
//				}
//			}
			String spareCode = request.getParameter("spareCode");
			String description = obj.getDescription();
			if (description != null){
				obj.setDescription(new String(description.getBytes("ISO-8859-1"), "utf-8"));
				//log.debug("utf-8:"+obj.getDescription());
				//log.debug("gbk:"+new String(description.getBytes("ISO-8859-1"), "GBK"));
			}
			String name= obj.getName();
			if(name != null){
				obj.setName(new String(name.getBytes("ISO-8859-1"), "utf-8"));
				log.debug("utf-8:"+obj.getDescription());
				/*log.debug("gbk:"+new String(description.getBytes("ISO-8859-1"), "GBK"));*/

			}

			ret = this.service.listMaterial(obj, condition,orgId,spareCode);
		} catch (Exception e) {
			log.error("获取任务管理数据列表出错！");
			log.errorPrintStacktrace(e);
//			e.printStackTrace();

		}
		return ret;
	}

	/**
	 * 保存记录
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public HttpResponse saveMaterial(Material obj, MaterialCondition condition,
									HttpServletRequest request) {
		log.debug("新增提交" + obj.getName());
		HttpResponse ret;
		try {
			Integer userId = 0;
			String userIp = "";
			//检查 名字是否重复
			if (this.service.checkNotExit(obj)) {

				HttpSession session = request.getSession();
				if (session.getAttribute("userId") != null) {
					userId = Integer.parseInt(session.getAttribute("userId").toString());
					userIp = session.getAttribute("userIp").toString();
				}
			obj.setCreator(userId);
			obj.setCreateDate(new Date());
			obj.setAddType(DictionaryEnums.AddType.EWMS.getCode());
			obj.setEnableSN(DictionaryEnums.YesOrNo.NO.getCode());
//			obj.setStatus(DictionaryEnums.Status.ENABLE.getCode());
//			BaseLocal local = MaterialDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //把当前操作人ID存入当前线程对象中
//			MaterialDaoGuard.setThreadLocal(local);
			this.service.saveMaterial(obj, condition);
				logService.addSystemLog(0, SystemLogEnums.LogObject.MATERIA_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
						"新增物料:" + obj.getName(), userIp, userId);

				ret = new HttpResponse(obj);
			} else {
				log.debug("名称或编码重复重复");
				ret = new HttpResponse(HttpResponse.Status.FAILURE, "物料名称或编码重复，请修改后重新提交", null);
			}
		} catch (Exception e) {
			log.error("保存记录出错！");
			log.errorPrintStacktrace(e);

			ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), obj);
		}
		return ret;

	}
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public HttpResponse updateMaterial(Material obj,HttpServletRequest request) {
		log.debug("修改提交" + obj.getName());
		HttpResponse ret;
		try {
			Integer userId = 0;
			String userIp = "";
			//检查 名字是否重复
//			if (this.service.checkNotExit(obj)) {

				HttpSession session = request.getSession();
				if (session.getAttribute("userId") != null) {
					userId = Integer.parseInt(session.getAttribute("userId").toString());
					userIp = session.getAttribute("userIp").toString();
//				}
                    obj.setUpdater(userId);
                    obj.setUpdateDate(new Date());
//			BaseLocal local = MaterialDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //把当前操作人ID存入当前线程对象中
//			MaterialDaoGuard.setThreadLocal(local);
			this.service.updateMaterial(obj);
				logService.addSystemLog(0, SystemLogEnums.LogObject.MATERIA_MANAGEMENT.getCode(), SystemLogEnums.LogAction.EDIT.getCode(),
                        "修改物料:" + obj.getName() + ";id:" + obj.getId(), userIp, userId);

				ret = new HttpResponse(obj);
			} else {
				log.debug("名称或编码重复重复");
				ret = new HttpResponse(HttpResponse.Status.FAILURE, "物料名称或编码重复，请修改后重新提交", null);
			}
		} catch (Exception e) {
			log.error("保存记录出错！");
			log.errorPrintStacktrace(e);
			ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), obj);
		}
		return ret;
	}



	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "{ids}")
	public HttpResponse deleteMaterial(@PathVariable("ids") String ids, HttpServletRequest request) {
		log.debug("删除提交，id in ： " + ids);
		HttpResponse ret;
		Integer userId = 0;
		String userIp = "";
		try {

			HttpSession session = request.getSession();
			if (session.getAttribute("userId") != null) {
				userId = Integer.parseInt(session.getAttribute("userId").toString());
				userIp = session.getAttribute("userIp").toString();
			}
			logService.addSystemLog(0, SystemLogEnums.LogObject.MATERIA_MANAGEMENT.getCode(), SystemLogEnums.LogAction.DELETE.getCode(),
					"删除物料分类:id in:" +ids , userIp, userId);

			this.service.deleteMaterial(ids,userId);
			ret = new HttpResponse(ids);
		} catch (Exception e) {
			log.error("保存记录出错！");
			log.errorPrintStacktrace(e);
			ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), ids);
		}
		return ret;
	}

	@RequestMapping(method= RequestMethod.GET,value="listMaterial")
    public String listMaterial(@ModelAttribute("material") Material obj, MaterialCondition condition,
                               HttpServletRequest request, HttpServletResponse response, Model model) {

		return "system/material/listMaterial";
	}

	/**
	 * 根据物料编码获取信息
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMaterialByCode.json", method = RequestMethod.POST)
	@ResponseBody
	public HttpResponse<Material> getMaterialByCode(String code, HttpServletRequest request) {
		try {
			Material material = this.service.getMaterialByCode(code);
			if(material!=null){
				return new HttpResponse<Material>(HttpResponse.Status.SUCCESS,"获取成功！", material);
			}else {
				return new HttpResponse<Material>(HttpResponse.Status.FAILURE,"获取失败！",null);
			}
		} catch (Exception e) {
			log.error("获取数据列表出错！");
			log.errorPrintStacktrace(e);
			e.printStackTrace();
			return new HttpResponse<Material>(HttpResponse.Status.FAILURE,"获取失败！",null);
		}
	}



}	
	
	
