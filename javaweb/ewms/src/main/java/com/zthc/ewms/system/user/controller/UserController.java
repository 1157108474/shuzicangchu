package com.zthc.ewms.system.user.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hckj.base.mvc.BaseLocal;
import com.hckj.base.util.Tool;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.entity.guard.DepartCondition;
import com.zthc.ewms.system.dept.service.DepartService;
import com.zthc.ewms.system.log.entity.SystemLogEnums;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.role.service.RoleService;
import com.zthc.ewms.system.spare.entity.guard.Sparepartscate;
import com.zthc.ewms.system.spare.service.SparepartscateService;
import com.zthc.ewms.system.useDep.entity.UseDep;
import com.zthc.ewms.system.useDep.service.UseDepService;
import com.zthc.ewms.system.user.controller.guard.UserControllerGuard;
import com.zthc.ewms.system.user.dao.guard.UserDaoGuard;
import com.zthc.ewms.system.user.dao.guard.UserScopeDaoGuard;
import com.zthc.ewms.system.user.entity.guard.*;
import com.zthc.ewms.system.user.service.UserRoleService;
import com.zthc.ewms.system.user.service.UserScopeService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/user")
public class UserController extends UserControllerGuard {
    @Resource(name = "roleService")
    public RoleService roleservice;
    @Resource(name = "userRoleService")
    public UserRoleService userRoleService;
    @Resource(name = "userScopeService")
    public UserScopeService userScopeService;
    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;
    @Resource(name = "sparepartscateService")
    public SparepartscateService sparepartscateService;
    @Resource(name = "useDepService")
    public UseDepService useDepService;
    @Resource(name = "departService")
    public DepartService departService;

    private final static Log log = Log.getLog("com.zght.crms.system.user.controller.UserController");

    /**
     * ��ȡ�б�����
     */
    @RequestMapping("/listUser.json")
    @ResponseBody
    public LayuiPage<User> listUser(User obj, UserCondition condition, String startTime, String endTime,
                                    HttpServletResponse response) {
        LayuiPage<User> ret = null;
        try {
            ret = this.service.listUser(obj, condition, startTime, endTime);
        } catch (Exception e) {
            log.error("��ȡ�û����ݳ���");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * ��ȡ�б�����
     */
    @RequestMapping("/listNoRoleUser.json")
    @ResponseBody
    public LayuiPage<User> listNoRoleUser(User obj, String roleCode, UserCondition condition, HttpServletResponse
            response) {
        LayuiPage<User> ret = null;
        try {
            ret = this.userRoleService.listNoRoleUser(obj, roleCode, condition);
        } catch (Exception e) {
            log.error("��ȡ�û����ݳ���");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }
    /**
     * ����������ҳ��
     *
     * @param obj
     * @param condition
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/openResetPWD.htm")
    public String openResetPWD(@ModelAttribute("us") User obj, UserCondition condition,
                               HttpServletRequest request, HttpServletResponse response, Model model) {

        User us = this.service.getUserOne(obj.getId());

        model.addAttribute("us", us);
        model.addAttribute("source", condition.getSource());

        return "system/user/resetPWD";
    }

    /**
     * ��������
     *
     * @param obj
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateResetPWD.json")
    @ResponseBody
    public HttpResponse<User> updateResetPWD(@ModelAttribute("us") User obj, HttpServletRequest request,
                                             HttpServletResponse response) {
        HttpResponse<User> ret = new HttpResponse(HttpResponse.Status.FAILURE, "��������ʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        BaseLocal local = UserDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
        UserDaoGuard.setThreadLocal(local);
        try {
            int i = this.service.updateResetPWD(obj);
            //���÷��ز���
            if (1 == i) {
                ret = new HttpResponse(HttpResponse.Status.SUCCESS, "��������ɹ���", obj);
            }
        } catch (Exception e) {
            log.error("��������ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �����û���ȡ��ɫ�б�����,��ҳ
     */
    @RequestMapping("/listUserRole.json")
    @ResponseBody
    public LayuiPage<Role> listUserRole(User obj, RoleCondition condition, HttpServletResponse response) {
        LayuiPage<Role> ret = null;
        try {
            ret = this.userRoleService.listUserRole(obj, condition);
        } catch (Exception e) {
            log.error("��ȡ�û����ݳ���");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * �����û���ȡ��ɫ����
     */
    @RequestMapping("/listUserRoles.json")
    @ResponseBody
    public List<Role> listUserRoles(User obj, HttpServletResponse response) {
        List<Role> ret = new ArrayList<>();
        try {
            ret = this.userRoleService.listUserRoles(obj.getId());
        } catch (Exception e) {
            log.error("��ȡ��ɫ���ݳ���");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }


    /**
     * ����ͨ�ò���ҳ��
     */
    @RequestMapping(value = "/publicDepart.htm")
    public String publicDepart() {

        return "system/dept/publicDepart";
    }


    /**
     * �����û���ɫ����
     */
    @RequestMapping("/saveUserRoles.json")
    @ResponseBody
    public HttpResponse saveUserRoles(User obj, UserRoleCondition condition) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "�����û���ɫʧ�ܣ�", condition);
        try {
            this.userRoleService.saveUserRoles(obj, condition);
            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "�����û���ɫ�ɹ���", condition);
        } catch (Exception e) {
            log.error("�����û���ɫʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * �������ҳ��
     */
    @RequestMapping(value = "/manageOperation.htm")
    public String manageOperation(User obj, Model model) {
        User user = this.service.getUserOne(obj.getId());
        model.addAttribute("user", user);
        return "system/user/manageOperation";
    }

    /**
     * �����б�����
     */
    @RequestMapping("/publicDepart.json")
    @ResponseBody
    public String publicDepart(Depart obj) {
        String ret = "";
        try {
            List<Map<String, Object>> departs = this.departService.listPublicDepart(obj);
            //ת����json��
            ret = new ObjectMapper().writeValueAsString(departs);
        } catch (Exception e) {
            log.error("��ȡ��֯�����б����");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ��֯�����б�����
     */
    @RequestMapping("/listDeparts.json")
    @ResponseBody
    public List<Depart> listDeparts(User obj) {
        List<Depart> ret = new ArrayList();
        try {
            ret = this.userScopeService.listUserScopes(obj.getId(), "Depart", UserEnums.ScopeTypeEnum.ORGANIZATION.getType());
        } catch (Exception e) {
            log.error("��ȡ��֯�������ݳ���");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * ��ȡδ����ⷿ�����б�����
     */
    @RequestMapping("/listWareHouses.json")
    @ResponseBody
    public List<WareHouse> listWareHouses(User obj) {
        List<WareHouse> ret = new ArrayList();
        try {
            List<WareHouse> wareHouses = this.userScopeService.listUserScopes(obj.getId(), "WareHouse", UserEnums.ScopeTypeEnum.WAREHOUSE.getType());
            Integer ids[] = new Integer[wareHouses.size()];
            for (int i = 0; i < wareHouses.size(); i++) {
                ids[i] = wareHouses.get(i).getId();
            }
            ret = this.wareHouseService.listWareHouse(obj.getZtId(), ids);
        } catch (Exception e) {
            log.error("��ȡ�ⷿ�������ݳ���");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ�ѷ���ⷿ�����б�����
     */
    @RequestMapping("/listNewWareHouses.json")
    @ResponseBody
    public List<WareHouse> listNewWareHouses(User obj) {
        List<WareHouse> ret = new ArrayList();
        try {
            ret = this.userScopeService.listUserScopes(obj.getId(), "WareHouse", UserEnums.ScopeTypeEnum.WAREHOUSE.getType());
        } catch (Exception e) {
            log.error("��ȡ�ⷿ�������ݳ���");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * ��ȡδ�������Ϸ�Χ�б�����
     */
    @RequestMapping("/listSpareparts.json")
    @ResponseBody
    public List<Sparepartscate> listSpareparts(User obj) {
        List<Sparepartscate> ret = new ArrayList();
        try {
            List<Sparepartscate> sparepartscates = this.userScopeService.listUserScopes(obj.getId(), "Sparepartscate", UserEnums.ScopeTypeEnum.SPAREPARTSCATE.getType());
            Integer ids[] = new Integer[sparepartscates.size()];
            if (sparepartscates.size() > 0) {
                for (int i = 0; i < sparepartscates.size(); i++) {
                    ids[i] = sparepartscates.get(i).getId();
                }
            }
            ret = this.sparepartscateService.listSpareparts(ids);
        } catch (Exception e) {
            log.error("��ȡ���Ϸ�Χ���ݳ���");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ��ȡ�ѷ������Ϸ�Χ�б�����
     */
    @RequestMapping("/listNewSpareparts.json")
    @ResponseBody
    public List<Sparepartscate> listNewSpareparts(User obj) {
        List<Sparepartscate> ret = new ArrayList();
        try {
            ret = this.userScopeService.listUserScopes(obj.getId(), "Sparepartscate", UserEnums.ScopeTypeEnum.SPAREPARTSCATE.getType());
        } catch (Exception e) {
            log.error("��ȡ���Ϸ�Χ���ݳ���");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * ��ȡδ������Ͻ�����б�����
     */
    @RequestMapping("/listUseDeps.json")
    @ResponseBody
    public List<UseDep> listUseDeps(User obj) {
        List<UseDep> ret = new ArrayList();
        try {
            List<UseDep> useDeps = this.userScopeService.listUserScopes(obj.getId(), "UseDep", UserEnums.ScopeTypeEnum.SPAREPARTSCATE.getType());
            Integer ids[] = new Integer[useDeps.size()];
            for (int i = 0; i < useDeps.size(); i++) {
                ids[i] = useDeps.get(i).getId();
            }
            ret = this.useDepService.listDepartDep(obj.getZtId(), ids);
        } catch (Exception e) {
            log.error("��ȡ���Ϸ�Χ���ݳ���");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * ��ȡ�ѷ�����Ͻ�����б�����
     */
    @RequestMapping("/listNewUseDeps.json")
    @ResponseBody
    public List<UseDep> listNewUseDeps(User obj) {
        List<UseDep> ret = new ArrayList();
        try {
            ret = this.userScopeService.listUserScopes(obj.getId(), "UseDep", UserEnums.ScopeTypeEnum.WAREHOUSE.getType());
        } catch (Exception e) {
            log.error("��ȡ�ⷿ�������ݳ���");
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

    /**
     * ���������Χ
     */
    @RequestMapping(value = "/saveUserScopes.json")
    @ResponseBody
    public HttpResponse saveUserScopes(@ModelAttribute("us") User obj, UserScopeCondition condition, HttpServletRequest request) {
        HttpResponse ret = new HttpResponse(HttpResponse.Status.FAILURE, "���������Χʧ�ܣ�", obj);
        //��ȡ��¼�ˡ���¼��Ip
        HttpSession session = request.getSession();
        Object userIdStr = session.getAttribute("userId");
        long userId = (null == userIdStr ? 0L : Long.parseLong(userIdStr.toString()));
        Object userIpStr = session.getAttribute("userIp");
        String userIp = (null == userIpStr ? null : userIpStr.toString());
        BaseLocal local = UserScopeDaoGuard.getThreadLocal();
        local.setCurrentUserId(userId); // �ѵ�ǰ������ID���뵱ǰ�̶߳�����
        UserScopeDaoGuard.setThreadLocal(local);
        try {
            this.userScopeService.saveUserScopes(obj, condition);
            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "���������Χ�ɹ���", obj);
            String logDesc = "��ԱId:" + obj.getId() + ";��֯����Id:" + condition.getDepts().toString() +
                    ";�ⷿ����ID" + condition.getWareHouses().toString() + ";���Ϸ�ΧId:" + condition.getSpareparts().toString() +
                    ";��Ͻ����Id:" + condition.getUseDeps().toString();
            //��־
//                this.logService.addSystemLog(0, SystemLogEnums.LogObject.PERSON_MANAGEMENT.getCode(), SystemLogEnums.LogAction.ADD.getCode(),
//                        logDesc, userIp, (int)userId);
        } catch (Exception e) {
            log.error("����ʧ�ܣ�");
            log.errorPrintStacktrace(e);
        }
        return ret;
    }

    /**
     * ����ͨ�ò����û�ҳ��
     */
    @RequestMapping(value = "/publicDepartUser.htm")
    public String publicDepartUser(String type, Model model) {
        model.addAttribute("type", type);
        return "system/user/publicDepartUser";
    }


    /** �����޸�
     *
     * @param jiuPassword   ԭ����
     * @param newPassword   ������
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/usersPass.json")
    @ResponseBody
    public HttpResponse usersPass(String jiuPassword, String newPassword,
                                  HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse ret = new HttpResponse();
        try {
            Integer userId = 0;
            if (request.getSession().getAttribute("userId") != null) {
                userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
            }
            User user=this.service.getUserOne(userId);
            if(user.getPassWord().equals(jiuPassword)){
//                user.setPassWord(Tool.md5(newPassword));
                user.setPassWord(newPassword);
                this.service.updateUserPassword(user);
                ret.setStatus(HttpResponse.Status.SUCCESS);
                ret.setMessage("�޸ĳɹ���");
            }else {
                ret.setStatus(HttpResponse.Status.FAILURE);
                ret.setMessage("ԭ�������");
            }
        } catch (Exception e) {
            log.error("�޸��������");
            log.errorPrintStacktrace(e);
            ret.setStatus(HttpResponse.Status.FAILURE);
            ret.setMessage("�޸��������");
        }
        return ret;
    }
}
