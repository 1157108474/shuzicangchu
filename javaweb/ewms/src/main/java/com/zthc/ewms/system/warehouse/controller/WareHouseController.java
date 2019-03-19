package com.zthc.ewms.system.warehouse.controller;

import com.hckj.base.mvc.HttpResponse;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.log.service.LogService;
import com.zthc.ewms.system.user.service.UserService;
import com.zthc.ewms.system.warehouse.controller.guard.WareHouseControllerGuard;
import com.zthc.ewms.system.warehouse.entity.guard.Location;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouseCondition;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/ware")
public class WareHouseController extends WareHouseControllerGuard {

    private final static Log log;


    @Resource(name = "logService")
    public LogService logService;

    @Resource(name = "userService")
    public UserService userService;

    static {
        log = Log.getLog("com.zthc.ewms.system.warehouse.controller.WareHouseController");
    }

    /**
     * ��ȡ�б�����
     */
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> treeWares(HttpServletRequest request) {
        log.debug("�����ȡtree����");

//		HttpResponse ret ;
        try {
//			String name = request.getParameter("name");
//			obj.setName(name);
            String idStr = request.getParameter("id");
            String levelStr = request.getParameter("level");
            return this.service.getWareTree(idStr, levelStr);

//            ret = new HttpResponse(maps);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
//             ret = new HttpResponse(HttpResponse.Status.FAILURE,e.getMessage(),null);
            return null;
        }

//		return ret;
    }

    /**
     * ��ȡ�б�����
     */
//	@RequestMapping("/listWareHouse.json")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LayuiPage<WareHouse> listWareHouse(@ModelAttribute("ware") WareHouse obj, WareHouseCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<WareHouse> ret = null;
        try {
            Integer orgId = null;// 200241;
//			Object data = request.getSession().getAttribute("orgId");
//			if (data != null) {
//				if(!Boolean.valueOf(request.getSession().getAttribute("isAdmin").toString())){
//					orgId = Integer.parseInt(data.toString());
//				}
//			}
//			String spareId = request.getParameter("spareId");
//			obj.setCode(request.getParameter("code"));
//			obj.setDescription( request.getParameter("description"));
            ret = this.service.listWareHouse(obj, condition, orgId);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();

        }
        return ret;
    }

    /**
     * �����¼
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse saveWareHouse(WareHouse obj, WareHouseCondition condition,
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
                obj.setCreator(userId);
                obj.setCreateDate(new Date());
                obj.setAddType(DictionaryEnums.AddType.EWMS.getCode());
//			obj.setStatus(DictionaryEnums.Status.ENABLE.getCode());
//			BaseLocal local = WareHouseDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //�ѵ�ǰ������ID���뵱ǰ�̶߳�����
//			WareHouseDaoGuard.setThreadLocal(local);
                this.service.saveWareHouse(obj, condition);
                logService.addSystemLog(0, SystemLogEnums.LogObject.STOREHOUSE_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
                        "�����ⷿ����:" + obj.getName(), userIp, userId);

                ret = new HttpResponse(obj);
            } else {
                log.debug("���ƻ�����ظ��ظ�");
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "�ⷿ�������ƻ�����ظ������޸ĺ������ύ", null);
            }
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);

            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), obj);
        }
        return ret;

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public HttpResponse updateWareHouse(WareHouse obj, HttpServletRequest request) {
        log.debug("�޸��ύ" + obj.getName());
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
                obj.setUpdater(userId);
                obj.setUpdateDate(new Date());
//			BaseLocal local = WareHouseDaoGuard.getThreadLocal();
//			local.setCurrentUserId(userId); //�ѵ�ǰ������ID���뵱ǰ�̶߳�����
//			WareHouseDaoGuard.setThreadLocal(local);
                this.service.updateWareHouse(obj);
                logService.addSystemLog(0, SystemLogEnums.LogObject.STOREHOUSE_MANAGEMENT.getCode(), SystemLogEnums.LogAction.EDIT.getCode(),
                        "�޸Ŀⷿ����:" + obj.getName(), userIp, userId);

                ret = new HttpResponse(obj);
            } else {
                log.debug("���ƻ�����ظ��ظ�");
                ret = new HttpResponse(HttpResponse.Status.FAILURE, "�ⷿ�������ƻ�����ظ������޸ĺ������ύ", null);
            }
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), obj);
        }
        return ret;
    }


    /**
     * ɾ��
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "{ids}")
    public HttpResponse deleteWareHouse(@PathVariable("ids") String ids, HttpServletRequest request) {
        log.debug("ɾ���ύ��id in �� " + ids);
        HttpResponse ret;
        Integer userId = 0;
        String userIp = "";
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                userId = Integer.parseInt(session.getAttribute("userId").toString());
                userIp = session.getAttribute("userIp").toString();
            }
            logService.addSystemLog(0, SystemLogEnums.LogObject.STOREHOUSE_MANAGEMENT.getCode(), SystemLogEnums.LogAction.DELETE.getCode(),
                    "ɾ���ⷿ��������:id in:" + ids, userIp, userId);

            this.service.deleteWareHouse(ids, userId);
            ret = new HttpResponse(ids);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), ids);
        } catch (Exception e) {
            log.error("�����¼����");
            log.errorPrintStacktrace(e);
            ret = new HttpResponse(HttpResponse.Status.FAILURE, e.getMessage(), ids);
        }
        return ret;
    }


    @RequestMapping(value = "printLocation", method = RequestMethod.GET)
    public String allocation() {

        return "system/ware/printLocation";
    }

    @RequestMapping(value = "listPrintAllocation", method = RequestMethod.GET)
    @ResponseBody
    public LayuiPage<Location> listPrintAllocation(WareHouseCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<Location> ret = null;
        try {
            String name = null;
            String begin = null;
            String end = null;
            String data = request.getParameter("name");
            if (!StringUtils.isEmpty(data)) {
                name = new String(data.getBytes("ISO-8859-1"), "utf-8").trim();
                log.debug("utf-8:" + name);
                log.debug("gbk:" + new String(data.getBytes("ISO-8859-1"), "GBK"));

            }
            data = request.getParameter("begin");
            if (!StringUtils.isEmpty(data)) {
                begin = data;
            }
            data = request.getParameter("end");
            if (!StringUtils.isEmpty(data)) {
                end = data;
            }

            ret = this.service.listPrintLocation(name, begin, end, condition);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();

        }
        return ret;
    }


    @RequestMapping(method = RequestMethod.GET, value = "location")
    public String location(@ModelAttribute("ware") WareHouse obj, WareHouseCondition condition,
                           HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("pre", request.getParameter("pre"));
        model.addAttribute("parentId", request.getParameter("parentId"));
        model.addAttribute("parentId", request.getParameter("parentId"));
        model.addAttribute("parentCode", request.getParameter("parentCode"));

        model.addAttribute("ztid", request.getParameter("ztId"));
        return "system/ware/location";
    }

    /**
     * @param obj
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "locationWare")
    public String locationWare(WareHouse obj, HttpServletRequest request, Model model) {
        model.addAttribute("pre", "new");
        return "system/ware/locationWare";
    }

    @RequestMapping(value = "listLocation", method = RequestMethod.POST)
    @ResponseBody
    public LayuiPage<WareHouse> listLocation(WareHouseCondition condition, HttpServletRequest request) {
        log.debug("�����ȡ�б���");
        LayuiPage<WareHouse> ret = null;
        try {
            Integer parentId = null;
            Integer ztId = null;
            String parentCode = "";
            String data = request.getParameter("parentId");
            if (!StringUtils.isEmpty(data)) {
                parentId = Integer.parseInt(data);
            }
            data = request.getParameter("parentCode");
            if (!StringUtils.isEmpty(data)) {
                parentCode = data;
            }
            data = request.getParameter("appFlag");
            if (data != null && data.equals("1")) {
                ztId = this.userService.getUserOne(Integer.parseInt(request.getParameter("userId"))).getZtId();
            } else {
                data = request.getParameter("ztId");
                if (!StringUtils.isEmpty(data)) {
                    ztId = Integer.parseInt(data);
                }
            }
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            ret = this.service.listLocation(parentId, ztId, name,code, parentCode, condition);
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
//			e.printStackTrace();
        }
        return ret;
    }

    /**
     * ���ݿ�λ�����ȡ��Ϣ
     *
     * @param code
     * @param request
     * @return
     */
    @RequestMapping(value = "getWareHouseByCode.json", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<WareHouse> getWareHouseByCode(String code, HttpServletRequest request) {
        try {
            WareHouse wareHouse = this.service.getWareHouseByCode(code);
            if (wareHouse != null) {
                return new HttpResponse<WareHouse>(HttpResponse.Status.SUCCESS, "��ȡ�ɹ���", wareHouse);
            } else {
                return new HttpResponse<WareHouse>(HttpResponse.Status.FAILURE, "��ȡʧ�ܣ�", null);
            }
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            return new HttpResponse<WareHouse>(HttpResponse.Status.FAILURE, "��ȡʧ�ܣ�", null);
        }
    }

    /**
     * ���ݿ�λ�����ȡ��Ϣ
     *
     * @param code
     * @param request
     * @return
     */
    @RequestMapping(value = "ifWareHouseByCode.json", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<Boolean> ifWareHouseByCode(String code, HttpServletRequest request) {
        try {
            Long wareHouseList = this.service.ifFindByParentCode(code);
            if (wareHouseList>0) {
                return new HttpResponse<Boolean>(HttpResponse.Status.SUCCESS, "�п�λ��", true);
            } else {
                return new HttpResponse<Boolean>(HttpResponse.Status.SUCCESS, "�޿�λ��", false);
            }
        } catch (Exception e) {
            log.error("��ȡ�����б����");
            log.errorPrintStacktrace(e);
            e.printStackTrace();
            return new HttpResponse<Boolean>(HttpResponse.Status.FAILURE, "��ȡʧ�ܣ�", false);
        }
    }
}

	
	
