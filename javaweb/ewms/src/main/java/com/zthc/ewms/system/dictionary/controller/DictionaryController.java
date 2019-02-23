package com.zthc.ewms.system.dictionary.controller;

import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dictionary.controller.guard.DictionaryControllerGuard;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryCondition;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
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

@Controller
@RequestMapping("/system/dic")
public class DictionaryController extends DictionaryControllerGuard {

    @Resource(name = "logService")
    public LogService logService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.dictionary.controller.DictionaryController");
    }

//    @Override
//    //方法可以省略的
//    public String editDictionary(@ModelAttribute("dic") Dictionary obj, DictionaryCondition condition,
//                                 HttpServletRequest request, HttpServletResponse response, Model model) {
//
//        return super.editDictionary(obj, condition, request, response, model);
//    }

    /**********************************************************/


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse saveDictionary(Dictionary obj, DictionaryCondition condition,
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
                obj.setAddType(DictionaryEnums.AddType.EWMS.getCode());
                obj.setCreator(userId);
                obj.setCreateDate(new Date());
//			BaseLocal local = DictionaryDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //把当前操作人ID存入当前线程对象中
//			DictionaryDaoGuard.setThreadLocal(local);
                obj.setGuid(java.util.UUID.randomUUID().toString());
                this.service.saveDictionary(obj, condition);
                logService.addSystemLog(0, SystemLogEnums.LogObject.DATA_DICTIONARY.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                        "新增数据字典:" + obj.getName(), userIp, userId);
                ret = new HttpResponse(obj);
            } else {
                log.debug("名称或编码重复");
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "名称或编码重复，请修改后再提交", null);
            }
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public HttpResponse updateDictionary(Dictionary obj, HttpServletRequest request) {
        HttpResponse ret;
        log.debug("修改提交" + obj.getName());
        try {
            if (this.service.checkNotExit(obj)) {

                Integer userId = 0;
                String userIp = "";
                HttpSession session = request.getSession();
                if (session.getAttribute("userId") != null) {
                    userId = Integer.parseInt(session.getAttribute("userId").toString());
                    userIp = session.getAttribute("userIp").toString();
                }
                obj.setUpdater(userId);
                obj.setUpdateDate(new Date());
//			BaseLocal local = DictionaryDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //把当前操作人ID存入当前线程对象中
//			DictionaryDaoGuard.setThreadLocal(local);
                this.service.updateDic(obj);
                logService.addSystemLog(0, SystemLogEnums.LogObject.DATA_DICTIONARY.getCode(), SystemLogEnums.LogAction.EDIT.getCode(),
                        "修改数据字典:" + obj.getName(), userIp, userId);
                ret = new HttpResponse(obj);
            } else {
                log.debug("名称或编码重复");
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "名称或编码重复，请修改后再提交", null);
            }
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);

            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;

    }

    /**
     * 查看
     *
     * @param id
     * @return HttpResponse
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    @ResponseBody
    public HttpResponse getDictionary(@PathVariable("id") Integer id) {
        Dictionary dic = this.service.getDic(id);
        HttpResponse ret;
        if (dic != null && !StringUtils.isEmpty(dic.getId())) {
            ret = new HttpResponse(dic);
        } else {
            ret = new HttpResponse(HttpResponse.Status.FAILURE, "查找不id=" + id + "的记录", null);
        }
        return ret;
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public HttpResponse deleteDictionary(@PathVariable("id") Integer id, HttpServletRequest request) {

        HttpResponse ret;
        log.debug("删除提交" + id);
        try {
            Integer userId = 0;
            String userIp = "";
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                userId = Integer.parseInt(session.getAttribute("userId").toString());
                userIp = session.getAttribute("userIp").toString();
            }
            logService.addSystemLog(0, SystemLogEnums.LogObject.DATA_DICTIONARY.getCode(), SystemLogEnums.LogAction.DELETE.getCode(),
                    "删除数据字典:id=" + id, userIp, userId);
            this.service.deleteDictionary(id, userId);
            ret = new HttpResponse(id);
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }


//    /**
//     * 查找
//     *
//     * @param parentId
//     * @return HttpResponse
//     */
//    @RequestMapping(method = RequestMethod.GET, value = "{parentId}")
//    @ResponseBody
//    public HttpResponse getDictionary(@PathVariable("parentId") String parentId) {
//        log.debug("查找"+parentId+"的子节点");
//        Dictionary dic = this.service.getDic(Integer.parseInt(parentId));
//        HttpResponse ret;
//        if (dic != null && !StringUtils.isEmpty(dic.getId())) {
//            ret = new HttpResponse(dic);
//        } else {
//            ret = new HttpResponse(HttpResponse.Status.FAILURE, "查找不到记录", null);
//        }
//        return ret;
//    }

}	

	
