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

	 /*-------------------------------------------------��ת����-------------------------------------------------*/

    @RequestMapping(value = "/jcwzck.htm")
    public String jcwzck(@ModelAttribute("sheet") SheetJCCK obj, SheetCKCondition condition, HttpServletRequest request, Model model) {
        log.debug("���������Ĵ����ʳ��ⵥҳ��,��ǰ������:jcwzck");





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
        obj.setCreateDateStr(new DateTime().toString("yyyy��MM��dd�� HH:mm:ss"));
        model.addAttribute("sheet", obj);
        //��ȡ�˵�Code ���ڹ�����
        String menuCode = request.getParameter("menuCode");
        List<Dictionary> dictionaries = this.activitiService.getPartButton(menuCode,userCode);
        model.addAttribute("buttons", dictionaries);
        model.addAttribute("menuCode", menuCode);

        return "sheet/jcwzck/jcwzck";
    }


    @RequestMapping(value = "/manageJcWzck.htm")
    public String manageJcWzck(HttpServletRequest request, Model model) {
        //��ȡ����״̬�ֵ��
        List<Dictionary> dictionaries = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType
                .ReceiptStatus.getCode());
        model.addAttribute("orderStatus", dictionaries);
        return "sheet/jcwzck/manageJcWzck";
    }
    /**
     * ҳ��_�������ϸ�б�
     */
    @RequestMapping(value = "detailsJCWZCKList", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<SheetJCCKDetails> getDetails(SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("�������ʼĴ�����ȡ�������ϸ�б���,��ǰ��������detailsJCWZCKList");
        LayuiPage<SheetJCCKDetails> ret = null;
        String sheetId = null;
        try {
            sheetId = request.getParameter("sheetId");
            ret = this.detailService.sheetDetails(sheetId, condition, "SheetJCCKDetails");
        } catch (Exception e) {
            log.error("��ȡ�������ϸ�����б����sheetId��" + sheetId);
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    /**
     * ��ȡ�Ĵ����������ϸ�б�
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
        log.debug("�����ȡ�Ĵ����ʳ���������ϸ�б���,��ǰ������:listDetails");
        LayuiPage<SheetJCCKList> ret = null;
        try {
            ret = this.sheetJCCKService.detailsList(details, condition);
        } catch (Exception e) {
            log.error("��ȡ���ʼĴ����������ϸ�б���ʧ��");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }
    /**
     * ���������ϸҳ��
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
     * �༭������ϸҳ��
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

	 /*-------------------------------------------------��������-------------------------------------------------*/
    /**
     * ����ҳ�浥���б�
     */
    @RequestMapping(value = "/listManageJcwzck.json", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<SheetJCCK> getSheets(Sheet obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("����Ĵ����ʳ����ȡ�б�������ǰ������:listManageJcwzck");
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
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "/editDetail",method = RequestMethod.POST)
    public HttpResponse editDetail(HttpServletRequest request, HttpServletResponse response){
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "�޸�ʧ��", null);

        JSONArray detailJson = JSONArray.fromObject(request.getParameter("details"));
        Collection collection = JSONArray.toCollection(detailJson);
        if (collection != null && !collection.isEmpty()) {
            Iterator it = collection.iterator();
            JSONObject jsonObj = JSONObject.fromObject(it.next());
            SheetDetail sheetDetail1 = (SheetDetail) JSONObject.toBean(jsonObj, SheetDetail.class);
            int data = sheetJCCKService.editSheetDatailed(sheetDetail1);
            if(data>0){
                ret = new HttpResponse(HttpResponse.Status.SUCCESS,"�޸ĳɹ�",null);
            }
        }
        return ret;
    }
    @ResponseBody
    @RequestMapping(value = "/editSheet",method = RequestMethod.POST)
    public HttpResponse editSheet( Sheet obj,HttpServletRequest request, HttpServletResponse response){
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "�޸�ʧ��", null);

        int data = sheetJCCKService.editSheet(obj);
        if(data>0){
            ret = new HttpResponse(HttpResponse.Status.SUCCESS,"�޸ĳɹ�",null);
        }
        return ret;
    }
    /**
     * ҳ��_�༭
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {
        SheetJCCK sheetJCCK = this.sheetService.getJCCKSheetOne(id);
        sheetJCCK.setCreateDateStr(new DateTime(sheetJCCK.getCreateDate()).toString("yyyy��MM��dd�� HH:mm:ss"));
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

	
