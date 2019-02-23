package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.sheet.entity.zc.ZC;
import com.zthc.ewms.sheet.entity.zc.ZCDetail;
import com.zthc.ewms.sheet.service.SheetZCService;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.print.entity.guard.Print;
import com.zthc.ewms.system.print.service.PrintService;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
@RequestMapping("/sheet/zc")
public class SheetZCController extends SheetControllerGuard {

    @Resource(name = "printService")
    public PrintService printService;

    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;

    @Resource(name = "userScopeService")
    private UserScopeService userScopeService;

    @Resource(name = "sheetZCService")
    private SheetZCService sheetZCService;

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.sheet.controller.SheetZCController");
    }


    /**
     * ҳ��_����
     */
    @RequestMapping("add")
    public String add(@ModelAttribute("sheet") ZC obj, SheetCondition condition,
                      HttpServletRequest request, HttpServletResponse response, Model model) {

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
        //   obj.setCreateDate(new Date());
        obj.setCreateDateStr(new DateTime().toString("yyyy��MM��dd�� HH:mm:ss"));
        model.addAttribute("sheet", obj);
        Integer[] ids = {184, 185, 182, 183};
        List<Print> prints = this.printService.getPrints(ids);
        model.addAttribute("printTypes", prints);
        String menuCode = request.getParameter("menuCode");
        model.addAttribute("menuCode", menuCode);

        List<Dictionary> dictionaries = activitiService.getPartButton(menuCode,userCode);//���һ���ڰ�ť
        model.addAttribute("buttons", dictionaries);

        return "sheet/zc/zc";
    }


    /**
     * ҳ��_�༭
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {
        ZC sheet = this.service.getZCSheetOne(id);

        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy��MM��dd�� HH:mm:ss"));
        model.addAttribute("sheet", sheet);
        Integer[] ids = {184, 185, 182, 183};
        List<Print> prints = this.printService.getPrints(ids);
        model.addAttribute("printTypes", prints);

        return this.getUrl(request, sheet.getRouteId().intValue() + "", model, "sheet/zc/zc", sheet.getStatus());

    }

    /**
     * ҳ��_��ϸ
     */
    @RequestMapping(value = "details", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<ZCDetail> getDetails(SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�������ϸ�б���");
        LayuiPage<ZCDetail> ret = null;
        String sheetId = null;
        try {
            sheetId = request.getParameter("sheetId");
            ret = this.detailService.sheetDetails(sheetId, condition, "ZCDetail");
        } catch (Exception e) {
            log.error("��ȡ�������ϸ�����б����sheetId��" + sheetId);
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }

    /**
     * ���������ϸҳ��
     *
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
        return "sheet/zc/detailed";
    }

    /**
     * ҳ��_����
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manageMaterial(@ModelAttribute("material") Sheet obj, SheetCondition condition,
                                 HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Integer userId = 0;
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        }

        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        model.addAttribute("departList", departList);

        List<Dictionary> statusList = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.ReceiptStatus.getCode());
        model.addAttribute("statusList", statusList);
        return "sheet/zc/manageZc";
    }

    /**
     * ����ҳ�浥���б�
     */
    @RequestMapping(value = "sheets", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<ZC> getDetails(Sheet obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<ZC> ret = null;
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
            ret = this.service.sheetList(obj, condition, "ZC", userId, begin, end, appFlag);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }

    /**
     * �༭������ϸҳ��
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editDetail.htm")
    public String editDetailCount(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer ztId = null;
        if (session.getAttribute("userId") != null) {
            ztId = Integer.parseInt(session.getAttribute("ztId").toString());
        }
        String id = request.getParameter("id");
        SheetDetail sheetDetail = this.detailService.getSheetDetailOne(Integer.parseInt(id));
        List<WareHouse> list = wareHouseService.getStores(ztId);
        request.setAttribute("stores", list);
        request.setAttribute("sheetDetail", sheetDetail);
        return "sheet/zc/detailed";
    }

    @ResponseBody
    @RequestMapping(value = "/editDetail", method = RequestMethod.POST)
    public HttpResponse editDetail(HttpServletRequest request, HttpServletResponse response) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "�޸�ʧ��", null);

        JSONArray detailJson = JSONArray.fromObject(request.getParameter("details"));
        Collection collection = JSONArray.toCollection(detailJson);
        if (collection != null && !collection.isEmpty()) {
            Iterator it = collection.iterator();
            JSONObject jsonObj = JSONObject.fromObject(it.next());
            SheetDetail sheetDetail1 = (SheetDetail) JSONObject.toBean(jsonObj, SheetDetail.class);
            int data = sheetZCService.editSheetDatailed(sheetDetail1);
            if (data > 0) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�޸ĳɹ�", null);
            }
        }
        return ret;
    }
}	

	
