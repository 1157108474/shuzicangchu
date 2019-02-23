package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.db.Db;
import com.zthc.ewms.sheet.entity.db.DbDetail;
import com.zthc.ewms.sheet.entity.db.Dbd;
import com.zthc.ewms.sheet.entity.db.DbdList;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import com.zthc.ewms.system.dept.service.OrganizationService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
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
@RequestMapping("/sheet/db")
public class SheetDBController extends SheetControllerGuard {

    @Resource(name = "organizationService")
    public OrganizationService orgService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.sheet.controller.SheetDBController");
    }


    /**
     * ҳ��_����
     */
    @RequestMapping("add")
    public String add(@ModelAttribute("sheet") Db obj, SheetCondition condition,
                      HttpServletRequest request, HttpServletResponse response, Model model) {
        String menuCode = request.getParameter("menuCode");
        model.addAttribute("menuCode", menuCode);



        HttpSession session = request.getSession();
        String userName = "";
        String departName = "";
        Integer departId = null;
        Integer userId = 0;
        String userCode = "";
        if (session.getAttribute("userId") != null) {
            userCode = session.getAttribute("userCode").toString();

            userId = Integer.valueOf(session.getAttribute("userId").toString());
            userName = session.getAttribute("userName").toString();
            departName = session.getAttribute("departName").toString();
            departId = (Integer) session.getAttribute("departId");


        }

        obj.setDepName(departName);
        obj.setPersonName(userName);
//        obj.setCreateDate(new Date());
        obj.setCreateDateStr(new DateTime().toString("yyyy��MM��dd�� HH:mm:ss"));
        model.addAttribute("sheet", obj);
        //List<Organization> orgs = this.orgService.getOrganizationByCode(new String[]{"ZT1", "ZT2", "ZT3"});
        List<Organization> orgs = this.orgService.getOrganizationList(new Organization());
        model.addAttribute("orgs", orgs);
        model.addAttribute("userZtId", departId);
        List<Dictionary> dictionaries = activitiService.getPartButton(menuCode,userCode);//���һ���ڰ�ť
        model.addAttribute("buttons", dictionaries);
        return "sheet/db/db";
    }


    /**
     * ҳ��_�༭
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {
        Db sheet = this.service.getDbSheetOne(id);
        model.addAttribute("sheet", sheet);
       /* List<Organization> orgs = this.orgService.getOrganizationByCode(new String[]{"ZT1", "ZT2", "ZT3"});*/
        List<Organization> orgs = this.orgService.listOrganization();
        model.addAttribute("orgs", orgs);

        return this.getUrl(request, sheet.getRouteId().intValue() + "", model, "sheet/db/db", sheet.getStatus());


    }

    /**
     * ҳ��_�������ϸ�б�
     */
    @RequestMapping(value = "details", method = RequestMethod.GET)
    @ResponseBody
    public LayuiPage<DbDetail> getDetails(SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�������ϸ�б���");
        LayuiPage<DbDetail> ret = null;
        String sheetId = null;
        try {
            sheetId = request.getParameter("sheetId");
            ret = this.detailService.sheetDetails(sheetId, condition, "DbDetail");
        } catch (Exception e) {
            log.error("��ȡ�������ϸ�����б����sheetId��" + sheetId);
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }


    /**
     * �����ϸ��ť�¼����������ϸҳ��
     */
    @RequestMapping("openAddDetail")
    public String openAddDetail(@ModelAttribute("sheet") Sheet obj, SheetCondition condition,
                                HttpServletRequest request, HttpServletResponse response, Model model) {

        return "sheet/db/addDetail";
    }

    @RequestMapping(value = "addDetails", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<DbdList> listDetails(SheetDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<DbdList> ret = null;
        try {
            Integer ztId = null;
            String data = request.getParameter("ztId");
            if (!StringUtils.isEmpty(data)) {
                ztId = Integer.parseInt(data);
            }
            ret = this.detailService.addDetailList(obj, condition, ztId, "DbdList");
        } catch (Exception e) {
            log.error("��ȡ������������б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }


    /**
     * ҳ��_����
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manageDb(@ModelAttribute("material") Sheet obj, SheetCondition condition,
                           HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Integer userId = 0;
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        }
       List<Organization> orgs = this.orgService.getOrganizationByCode(new String[]{"ZT1", "ZT2", "ZT3"});
        /*List<Organization> orgs = this.orgService.listOrganization();*/
        model.addAttribute("orgs", orgs);

        List<Dictionary> statusList = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.ReceiptStatus.getCode());
        model.addAttribute("statusList", statusList);
        return "sheet/db/manageDb";
    }

    /**
     * ����ҳ�浥���б�
     */
    @RequestMapping(value = "sheets", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<Db> getSheets(Sheet obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<Db> ret = null;
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
            ret = this.service.sheetList(obj, condition, "Db", userId, begin, end, appFlag);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }

    @RequestMapping(value = "dbd", method = RequestMethod.GET)
    public String listDbd(@ModelAttribute("material") Sheet obj, SheetCondition condition,
                          HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("ztId", request.getParameter("ztId"));
        model.addAttribute("type", request.getParameter("type"));
        return "sheet/db/dbd";
    }

    /**
     * ������ȡ������
     */
    @RequestMapping(value = "dbdSheets", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<Dbd> listDbdSheets(String ztId, String type, String code, SheetCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<Dbd> ret = null;
        try {
            ret = this.service.listDbdSheets(Integer.parseInt(ztId), type, code, condition);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }
}	

	
