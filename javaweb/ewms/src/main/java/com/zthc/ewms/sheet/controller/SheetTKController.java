package com.zthc.ewms.sheet.controller;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.sheet.controller.guard.SheetControllerGuard;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.tk.TK;
import com.zthc.ewms.sheet.entity.tk.TKDetail;
import com.zthc.ewms.sheet.entity.tk.ZJTKDetail;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.user.entity.guard.UserEnums;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
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
@RequestMapping("/sheet/tk")
public class SheetTKController extends SheetControllerGuard {

    private final static Log log = Log.getLog("com.zthc.ewms.sheet.controller.SheetCKControllerGuard");
    @Resource(name = "dictionaryService")
    public DictionaryService dictionaryService;

    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;


    @Resource(name = "userScopeService")
    private UserScopeService userScopeService;

    /**
     * ҳ��_����
     */
    @RequestMapping("add")
    public String add(@ModelAttribute("sheet") TK obj, SheetCondition condition,
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
        obj.setZtidName(departName);
        obj.setPersonName(userName);
        //   obj.setCreateDate(new Date());
        obj.setCreateDateStr(new DateTime().toString("yyyy��MM��dd�� HH:mm:ss"));

        List<Dictionary> tkTypes = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.TKTYPE.getCode());
        model.addAttribute("tkTypes", tkTypes);

        model.addAttribute("sheet", obj);
        //��ѯ�ֵ䣬��ȡ�ʽ���Դ
        List<Dictionary> fundsSources = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());
        model.addAttribute("fundsSources", fundsSources);
        String menuCode = request.getParameter("menuCode");
        model.addAttribute("menuCode", menuCode);

        List<Dictionary> dictionaries = activitiService.getPartButton(menuCode,userCode);//���һ���ڰ�ť
        model.addAttribute("buttons", dictionaries);
        return "sheet/tk/sheetTK";
    }


    /**
     * ҳ��_�༭
     */
    @RequestMapping("{id}")
    public String edit(@PathVariable("id") Integer id, SheetCondition condition,
                       HttpServletRequest request, HttpServletResponse response, Model model) {
        TK sheet = this.service.getTKSheetOne(id);
        model.addAttribute("sheet", sheet);
        sheet.setCreateDateStr(new DateTime(sheet.getCreateDate()).toString("yyyy��MM��dd�� HH:mm:ss"));
        model.addAttribute("sheet", sheet);
        List<Dictionary> fundsSources = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.FundSource.getCode());
        model.addAttribute("fundsSources", fundsSources);
        return this.getUrl(request, sheet.getRouteId().intValue() + "", model, "sheet/tk/sheetTK", sheet.getStatus());

    }

    /**
     * ҳ��_�������ϸ�б�
     */
    @RequestMapping(value = "details", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<TKDetail> getDetails(SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�������ϸ�б���");
        LayuiPage<TKDetail> ret = null;
        String sheetId = null;
        try {
            sheetId = request.getParameter("sheetId");
            ret = this.detailService.sheetDetails(sheetId, condition, "TKDetail");
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

        return "sheet/tk/detailed";
    }

    @RequestMapping(value = "getAddDetails", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<ZJTKDetail> listDetails(SheetDetail obj, SheetDetailCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<ZJTKDetail> ret = null;
        try {
            Integer ztid = null;
//			HttpSession session = request.getSession();
//			if (session.getAttribute("ztId") != null) {
//				ztid = Integer.parseInt(session.getAttribute("ztId").toString());
//			}

            ret = this.detailService.addDetailList(obj, condition, ztid, "ZJTKDetail");
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
    public String manageTK(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Integer userId = 0;
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        }
        List<Depart> departList = this.userScopeService.listUserScopes(userId, "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        model.addAttribute("departList", departList);

        List<Dictionary> statusList = this.dictionaryService.getDicListByParentId(DictionaryEnums.DicType.ReceiptStatus.getCode());
        model.addAttribute("statusList", statusList);
        return "sheet/tk/manageSheetTK";
    }

    /**
     * ����ҳ�浥���б�
     */
    @RequestMapping(value = "sheets", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<TK> getSheets(Sheet obj, SheetCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<TK> ret = null;
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
            ret = this.service.sheetList(obj, condition, "TK", userId, begin, end, appFlag);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }
    /**
     * ���³ɱ�
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/renewalCost.json")
    @ResponseBody
    public HttpResponse renewalCost(Integer id, HttpServletRequest request) {
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        TK sheetTK = service.getTKSheetOne(id);
        int userId = (null == userIdStr ? 0 : Integer.parseInt(userIdStr.toString()));
        try {
            service.asynRenewalCost(sheetTK,userId);
            return new HttpResponse(HttpResponse.Status.SUCCESS, "�����˿�ɹ���",null);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            return new HttpResponse(HttpResponse.Status.FAILURE, "�����˿�ʧ�ܣ�",null);
        }catch (Exception e) {
            log.error("����ʧ�ܣ�");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            return new HttpResponse(HttpResponse.Status.FAILURE, "�����˿�ɹ���",null);
        }
    }
}

	
