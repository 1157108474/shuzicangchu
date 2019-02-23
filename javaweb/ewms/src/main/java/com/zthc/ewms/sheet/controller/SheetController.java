package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;
import com.zthc.ewms.sheet.service.SheetRKService;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.service.FormTemplateService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.service.UserService;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sheet")
public class SheetController extends SheetControllerGuard {

    @Resource(name = "logService")
    public LogService logService;

    @Resource(name = "dictionaryService")
    public DictionaryService dicService;

    @Resource(name = "formTemplateService")
    public FormTemplateService formTemplateService;

    @Resource(name = "userService")
    public UserService userService;

    @Resource(name = "sheetRKService")
    public SheetRKService sheetRKService;


    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.sheet.controller.SheetController");
    }

    /**
     * 新增保存
     */
    @RequestMapping(method = RequestMethod.POST, value = "{type}")
    @ResponseBody
    public HttpResponse saveSheet(@PathVariable("type") String type, Sheet obj, SheetCondition
            condition, HttpServletRequest request) {
        log.debug("新增保存" + type);
        HttpResponse ret;
        try {
            String menuCode = request.getParameter("menuCode");
            Dictionary dic = this.dicService.getDictionaryByType(type);
             if (dic == null) {
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "数据字典中查找不到相应数据", null);

            } else {
                List<FormTemplate> formTemplates = this.formTemplateService.getFromTemBydicID(dic.getId().toString());
                if (formTemplates == null || formTemplates.size() != 1) {
                    ret = new HttpResponse(HttpResponse.Status.FAILURE, "未配置单据", null);
                } else {
                    FormTemplate formTemplate = formTemplates.get(0);
                    Integer userId = 0;
                    Integer departId = 0;
                    Integer ztId = 0;
                    String userIp = "";
                    String userCode = null;
                    HttpSession session = request.getSession();
                    String data = request.getParameter("appFlag");
                    boolean appFlag = false;
                    if (data != null && "1".equals(data)) {
                        appFlag = true;
                        userId = Integer.parseInt(request.getParameter("userId"));
                        userIp = "app";
                        User user = this.userService.getUserOne(userId);
                        if (null != user) {
                            ztId = user.getZtId();
                            userCode = user.getCode();
                            Depart depart = user.getParent();
                            if (null != depart) {
                                departId = depart.getId();
                            }
                        }else{
                            return new HttpResponse(HttpResponse.Status.FAILURE, "用户不存在"+userId, null);
                        }

                    } else {
                        if (session.getAttribute("userId") != null) {
                            userId = Integer.parseInt(session.getAttribute("userId").toString());
                            departId = Integer.parseInt(session.getAttribute("departId").toString());
                            ztId = Integer.parseInt(session.getAttribute("ztId").toString());
                            userIp = session.getAttribute("userIp").toString();
                            userCode = session.getAttribute("userCode").toString();
                        }
                    }

                    obj.setDepartId(departId);
                    obj.setGuid(java.util.UUID.randomUUID().toString());
                    obj.setName(formTemplate.getFormTemName());
                    obj.setKindId(dic.getId());
                    obj.setStatus(DictionaryEnums.Status.SHEETING.getCode());
                    if(!type.equals(SystemLogEnums.LogObject.MATERIAL_RETURN.getType())) {
                        obj.setZtId(ztId);
                    }
                    obj.setCreator(userId);
                    obj.setCreateDate(new Date());
//                    obj.setId(1111);
//                    obj.setCode("11111");
                    this.service.saveSheet(obj, menuCode, type, userCode, condition);
                    logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums
                                    .LogAction.ADD.getCode(),
                            "新增单据:" + obj.getName(), userIp, userId);
                    ret = new HttpResponse(obj);

                }
            }

        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;

    }

    /**
     * 单据修改 （移库移位/调拨/退货/退库）
     */
    @RequestMapping(method = RequestMethod.PUT, value = "{type}/{id}")
    @ResponseBody
    public HttpResponse saveOrUpdate(@PathVariable("type") String type, @PathVariable("id") Integer id, HttpServletRequest request, Model model) {

        HttpResponse ret;
        log.debug("修改提交" + type + id);
        try {
            Integer userId = 0;
            String userIp = "";
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                userId = Integer.parseInt(session.getAttribute("userId").toString());
                userIp = session.getAttribute("userIp").toString();
            }
            String memo = request.getParameter("memo");
            String extendString1 = request.getParameter("extendString1");
            this.service.updateSheet(id, memo,extendString1, userId);
            logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums.LogAction.EDIT.getCode(),
                    "修改:" + id.intValue(), userIp, userId);
            ret = new HttpResponse(null);
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), null);
        }
        return ret;
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "{type}-{id}")
    public HttpResponse deleteSheet(@PathVariable("type") String type, @PathVariable("id") Integer id, HttpServletRequest request) {
        log.debug("删除" + type + "提交，id = " + id);
        HttpResponse ret;
        Integer userId = 0;
        String userIp = "";
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                userId = Integer.parseInt(session.getAttribute("userId").toString());
                userIp = session.getAttribute("userIp").toString();
            }
            logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums.LogAction.DELETE.getCode(),
                    "删除单据:id =" + id, userIp, userId);

            this.service.deleteSheet(id, type);
            ret = new HttpResponse(id);
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), id);
        }
        return ret;
    }


    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "delete-{id}")
    public HttpResponse deleteRkSheet(@PathVariable("id") Integer id, HttpServletRequest request) {
        log.debug("删除RK:" + "提交，id = " + id);
        HttpResponse ret;
        Integer userId = 0;
        String userIp = "";
        String type = "RK";
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                userId = Integer.parseInt(session.getAttribute("userId").toString());
                userIp = session.getAttribute("userIp").toString();
            }
            logService.addSystemLog(1, SystemLogEnums.LogObject.getByType(type).getCode(), SystemLogEnums.LogAction
                            .DELETE.getCode(),
                    "删除单据:id =" + id, userIp, userId);

            this.sheetRKService.deleteSheet(id);
            ret = new HttpResponse(id);
        } catch (Exception e) {
            log.error("保存记录出错！");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), id);
        }
        return ret;
    }


}

	
