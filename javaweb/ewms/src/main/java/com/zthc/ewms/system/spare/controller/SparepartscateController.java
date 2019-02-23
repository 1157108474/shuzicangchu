package com.zthc.ewms.system.spare.controller;

import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.spare.controller.guard.SparepartscateControllerGuard;
import com.zthc.ewms.system.spare.entity.guard.Sparepartscate;
import com.zthc.ewms.system.spare.entity.guard.SparepartscateCondition;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/spare")
public class SparepartscateController extends SparepartscateControllerGuard {

    @Resource(name = "logService")
    public LogService logService;

    private final static Log log;


    static {
        log = Log.getLog("com.zthc.ewms.system.sparepartscate.controller.SparepartscateController");
    }

//    @Override
//    //��������ʡ�Ե�
//    public String editSparepartscate(@ModelAttribute("Spare") Sparepartscate obj, SparepartscateCondition condition,
//                                 HttpServletRequest request, HttpServletResponse response, Model model) {
//
//        return super.editSparepartscate(obj, condition, request, response, model);
//    }

    /**********************************************************/


    /**
     * ��ȡ�б�����
     */
    @RequestMapping(value = "/spares", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> listSpare(HttpServletRequest request) {
        log.debug("�����ȡ�б���");

//		HttpResponse ret ;
        try {
//			String name = request.getParameter("name");
//			obj.setName(name);
            String idStr = request.getParameter("id");
            String levelStr = request.getParameter("level");
            return this.service.getSpareTree(idStr, levelStr);

//            ret = new HttpResponse(maps);
        } catch (Exception e) {
            log.error("��ȡ������������б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
//             ret = new HttpResponse(HttpResponse.Status.FAILURE,e.getMessage(),null);
            return null;
        }

//		return ret;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse saveSparepartscate(Sparepartscate obj, SparepartscateCondition condition,
                                           HttpServletRequest request) {
        log.debug("�����ύ" + obj.getName());
        HttpResponse ret;
        try {
            Integer userId = 0;
            String userIp = "";
            //��� �����Ƿ��ظ�
            if (this.service.checkNotExit(obj)) {

                HttpSession session = request.getSession();
                if (session.getAttribute("userId") != null) {
                    userId = Integer.parseInt(session.getAttribute("userId").toString());
                    userIp = session.getAttribute("userIp").toString();
                }
                obj.setAddType(DictionaryEnums.AddType.EWMS.getCode());
                obj.setCreator(userId);
                obj.setCreateDate(new Date());
//			BaseLocal local = SparepartscateDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //�ѵ�ǰ������ID���뵱ǰ�̶߳�����
//			SparepartscateDaoGuard.setThreadLocal(local);

                this.service.saveSparepartscate(obj, condition);
                logService.addSystemLog(0, SystemLogEnums.LogObject.MATERIAL_CLASS.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                        "�������Ϸ���:" + obj.getName(), userIp, userId);

                ret = new HttpResponse(obj);
            } else {
                log.debug("���ƻ�����ظ��ظ�");
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "���Ϸ������ƻ�����ظ������޸ĺ������ύ", null);
            }

        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);

            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public HttpResponse updateSparepartscate(Sparepartscate obj, HttpServletRequest request) {

        Integer userId = 0;
        String userIp = "";
        HttpResponse ret;
        log.debug("�޸��ύ" + obj.getName());
        try {
            //��� �����Ƿ��ظ�
            if (this.service.checkNotExit(obj)) {

                HttpSession session = request.getSession();
                if (session.getAttribute("userId") != null) {
                    userId = Integer.parseInt(session.getAttribute("userId").toString());
                    userIp = session.getAttribute("userIp").toString();
                }
                obj.setUpdater(userId);
                obj.setUpdateDate(new Date());
//			BaseLocal local = SparepartscateDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //�ѵ�ǰ������ID���뵱ǰ�̶߳�����
//			SparepartscateDaoGuard.setThreadLocal(local);
                this.service.updateSpare(obj);
                logService.addSystemLog(0, SystemLogEnums.LogObject.MATERIAL_CLASS.getCode(), SystemLogEnums.LogAction.EDIT.getCode(),
                        "�޸����Ϸ���:" + obj.getName(), userIp, userId);

                ret = new HttpResponse(obj);
            } else {
                log.debug("���ƻ�����ظ��ظ�");
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "���Ϸ������ƻ�����ظ������޸ĺ������ύ", null);
            }
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);

            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;

    }

    /**
     * �鿴
     *
     * @param id
     * @return HttpResponse
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}-{level}")
    @ResponseBody
    public HttpResponse getSparepartscate(@PathVariable("id") Integer id, @PathVariable("level") Integer level) {

        Sparepartscate Spare = this.service.getSpare(id, level);
        HttpResponse ret;
        if (Spare != null && !StringUtils.isEmpty(Spare.getId())) {
            ret = new HttpResponse(Spare);
        } else {
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "���Ҳ�id=" + id + "�ļ�¼", null);
        }
        return ret;
    }

    /**
     * ɾ��
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public HttpResponse deleteSparepartscate(@PathVariable("id") Integer id, HttpServletRequest request) {
        log.debug("ɾ���ύ��id = " + id);
        HttpResponse ret;
        Integer userId = 0;
        String userIp = "";
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                userId = Integer.parseInt(session.getAttribute("userId").toString());
                userIp = session.getAttribute("userIp").toString();
            }
            this.service.deleteSparepartscate(id, userId);
            logService.addSystemLog(0, SystemLogEnums.LogObject.MATERIAL_CLASS.getCode(), SystemLogEnums.LogAction.DELETE.getCode(),
                    "ɾ�����Ϸ���:id=" +id , userIp, userId);

            ret = new HttpResponse(id);
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }


//	/**
//	 * ����
//	 *
//	 * @param parentId
//	 * @return HttpResponse
//	 */
//	@RequestMapping(method = RequestMethod.GET, value = "{parentId}")
//	@ResponseBody
//	public HttpResponse getSparepartscate(@PathVariable("parentId") String parentId) {
//		log.debug("����"+parentId+"���ӽڵ�");
//		Sparepartscate Spare = this.service.getSpare(Integer.parseInt(parentId));
//		HttpResponse ret;
//		if (Spare != null && !StringUtils.isEmpty(Spare.getId())) {
//			ret = new HttpResponse(Spare);
//		} else {
//			ret = new HttpResponse(HttpResponse.Status.FAILURE, "���Ҳ�����¼", null);
//		}
//		return ret;
//	}

}	

	

	
	
