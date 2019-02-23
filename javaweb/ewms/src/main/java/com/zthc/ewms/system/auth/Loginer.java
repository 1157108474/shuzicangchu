package com.zthc.ewms.system.auth;


import com.zthc.ewms.base.resp.HttpResponse;
import com.zthc.ewms.base.util.Base64;
import com.zthc.ewms.base.util.GetIp;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.service.MenuService;
import com.zthc.ewms.system.useDep.entity.UseDep;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.service.UserService;
import drk.system.AppConfig;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��¼������
 *
 * @author 02-23
 */
@Controller
@RequestMapping("/system/auth")
public class Loginer {
    private final static Log log = Log.getLog("login");
    @Resource(name = "userService")
    public UserService userService;
    @Resource(name = "menuService")
    public MenuService menuService;

    @RequestMapping(value = "/login.json")
    @ResponseBody
    public HttpResponse<User> login(User obj, HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse<User> ret = new HttpResponse(HttpResponse.Status.FAILURE, "�˺Ż��������", obj);
        HttpSession session = request.getSession();
        log.debug("�����¼������code��" + obj.getCode() + ";password:" + obj.getPassWord());


        //���ݵ�¼���������ѯ�Ƿ���ڸ��û�
        User user = this.userService.getUserLogin(obj);
        if (null != user) {
            String ip = "";
            try {
                ip = GetIp.getIpAddr(request);
            } catch (Exception e) {
                log.message("��ȡ�û���" + user.getName() + "��Ip����");
            }
            String macAddress = "";
            try {
                macAddress = GetIp.getMACAddress(ip);
            } catch (Exception e) {
                log.message("��ȡ�û���" + user.getName() + "��Ip����");
            }
            String address = "";
            //��ȡ��¼��ַ����ע�͵���ֻ��ip
            /*try {
                address =GetIp.getAddresses("ip="+ip, "utf-8");
            } catch (Exception e) {
                log.message("��ȡ�û���"+user.getName()+"�ĵ�ַ����");
            }
            Map map = (Map) JSON.parse(address);
            Map<String,String> maps =(Map<String, String>) map.get("data");*/
            user.setLoginIp(ip);
            //user.setLoginCity(maps.get("region")+"/"+maps.get("city"));
            this.userService.updateUserLogin(user);
            //����session�еĲ���
            addSession(user, request);
            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "��¼�ɹ���", user);
            log.debug("��¼�ɹ�,�û�Id" + user.getId() + ",�û����ƣ�" + user.getName());
        }
        return ret;
    }

    /**
     * ע��
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout.json")
    @ResponseBody
    public HttpResponse<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        session.removeAttribute("userName");
        session.removeAttribute("userCode");
        session.removeAttribute("ztid");
        session.removeAttribute("departId");
        session.removeAttribute("departName");
        session.removeAttribute("departCode");
        session.removeAttribute("useDepId");
        session.removeAttribute("useDepName");
        session.removeAttribute("useDepCode");
        //���÷��ز���
        HttpResponse<Void> ret = new HttpResponse(HttpResponse.Status.SUCCESS, "ע���ɹ���", null);
        return ret;
    }

    /**
     * app��¼����
     *
     * @param obj
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/appLogin.json")
    @ResponseBody
    public HttpResponse<User> appLogin(User obj, HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpResponse<User> ret = new HttpResponse(HttpResponse.Status.FAILURE, "�˺Ż��������", obj);
        HttpSession session = request.getSession();
        log.debug("�����¼������code��" + obj.getCode() + ";password:" + obj.getPassWord());
        //���ݵ�¼���������ѯ�Ƿ���ڸ��û�
        User user = this.userService.getUserLogin(obj);
        if (null != user) {
            String codes = this.menuService.appMenus(user.getId());
            //���÷��ز���
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, codes, user);
            log.debug("��¼�ɹ�,�û�Id" + user.getId() + ",�û����ƣ�" + user.getName() + ",ӵ�е�menuCode��" + codes);
        }
        return ret;

    }

    /**
     * ��˵���
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/menu.json")
    @ResponseBody
    public List<Map<String, Object>> menu(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Integer userId = 0;
        if (session.getAttribute("userId") != null) {
            userId = Integer.parseInt(session.getAttribute("userId").toString());
        }
        //�˵�
        List<Menu> menus = this.menuService.leftMenus(userId);

        List<Map<String, Object>> lists = new ArrayList<>();
        for (Menu m : menus) {
            Map<String, Object> maps = new HashMap<>();
            maps.put("title", m.getName());
            maps.put("icon", m.getIcon());
            maps.put("spread", false);
            List<Map<String, Object>> list = new ArrayList<>();
            for (Menu menu : m.getChildren()) {
                Map<String, Object> map = new HashMap<>();
                map.put("title", menu.getName());
                map.put("icon", menu.getIcon());
                map.put("href", menu.getUrl() + "?menuCode=" + menu.getCode());
                list.add(map);
            }
            maps.put("children", list);
            lists.add(maps);
        }
        return lists;
    }


    /**
     * �����¼
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ssoLogin")
    public void ssoLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = "";
        Boolean bool =true;
        if (null == request.getParameter("key")) {
            //�ж�keyֵ�Ƿ�Ϊ��
            bool =false;
            page ="/goError.jsp?errorMsg=1";
        }else if (null == request.getParameter("userCode")) {
            //�ж��û������Ƿ�Ϊ��
            bool =false;
            page ="/goError.jsp?errorMsg=2";
        }
        if(bool){
            String keyStr = request.getParameter("key");
            String userCodeStr = request.getParameter("userCode");
            log.debug("���뵥���¼������key��" + keyStr+";userCode��"+userCodeStr);
            //Base64����
            String key = Base64.getFromBase64(keyStr);
            String userCode = Base64.getFromBase64(userCodeStr);
            log.debug("Base64���ܺ�key��" + key+";userCode��"+userCode);
            //�ж�keyֵ�Ƿ�ƥ��
            if(key.equals(AppConfig.getProperty("sso.key"))){
                //���ݱ����ѯ�Ƿ���ڸ��û�
                User user = this.userService.getUserByCode(userCode);
                if (null != user) {
                    String ip = "";
                    try {
                        ip = GetIp.getIpAddr(request);
                    } catch (Exception e) {
                        log.message("��ȡ�û���" + user.getName() + "��Ip����");
                    }
                    user.setLoginIp(ip);
                    this.userService.updateUserLogin(user);
                    //����session�еĲ���
                    addSession(user, request);
                    page = "/ewms.jsp";
                }
            }else {
                page ="/goError.jsp?errorMsg=3";
            }
        }
        //��ת��ҳ��
        response.sendRedirect(request.getContextPath() + page);
    }

    /**
     * ����session
     *
     * @param user
     */
    public void addSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //����session�еĲ���
        session.setAttribute("userId", user.getId());
        session.setAttribute("userCode", user.getCode());
        session.setAttribute("userName", user.getName());
        session.setAttribute("ztId", user.getZtId());
        session.setAttribute("userIp", "");
        Depart depart = user.getParent();
        if (null == depart) {
            session.setAttribute("departId", 0);
            session.setAttribute("departName", "");
            session.setAttribute("departCode", "");
        } else {
            session.setAttribute("departId", depart.getId());
            session.setAttribute("departName", depart.getName());
            session.setAttribute("departCode", depart.getCode());
        }
        UseDep useDep = user.getOffices();
        if (null == useDep) {
            session.setAttribute("useDepId", 0);
            session.setAttribute("useDepName", "");
            session.setAttribute("useDepCode", "");
        } else {
            session.setAttribute("useDepId", useDep.getId());
            session.setAttribute("useDepName", useDep.getName());
            session.setAttribute("useDepCode", useDep.getCode());
        }
    }
}
