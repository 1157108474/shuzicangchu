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
 * 登录控制器
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
        HttpResponse<User> ret = new HttpResponse(HttpResponse.Status.FAILURE, "账号或密码错误！", obj);
        HttpSession session = request.getSession();
        log.debug("进入登录方法，code：" + obj.getCode() + ";password:" + obj.getPassWord());


        //根据登录名和密码查询是否存在该用户
        User user = this.userService.getUserLogin(obj);
        if (null != user) {
            String ip = "";
            try {
                ip = GetIp.getIpAddr(request);
            } catch (Exception e) {
                log.message("获取用户：" + user.getName() + "的Ip错误！");
            }
            String macAddress = "";
            try {
                macAddress = GetIp.getMACAddress(ip);
            } catch (Exception e) {
                log.message("获取用户：" + user.getName() + "的Ip错误！");
            }
            String address = "";
            //获取登录地址的先注释掉，只存ip
            /*try {
                address =GetIp.getAddresses("ip="+ip, "utf-8");
            } catch (Exception e) {
                log.message("获取用户："+user.getName()+"的地址错误！");
            }
            Map map = (Map) JSON.parse(address);
            Map<String,String> maps =(Map<String, String>) map.get("data");*/
            user.setLoginIp(ip);
            //user.setLoginCity(maps.get("region")+"/"+maps.get("city"));
            this.userService.updateUserLogin(user);
            //设置session中的参数
            addSession(user, request);
            //设置返回参数
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, "登录成功！", user);
            log.debug("登录成功,用户Id" + user.getId() + ",用户名称：" + user.getName());
        }
        return ret;
    }

    /**
     * 注销
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
        //设置返回参数
        HttpResponse<Void> ret = new HttpResponse(HttpResponse.Status.SUCCESS, "注销成功！", null);
        return ret;
    }

    /**
     * app登录方法
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
        HttpResponse<User> ret = new HttpResponse(HttpResponse.Status.FAILURE, "账号或密码错误！", obj);
        HttpSession session = request.getSession();
        log.debug("进入登录方法，code：" + obj.getCode() + ";password:" + obj.getPassWord());
        //根据登录名和密码查询是否存在该用户
        User user = this.userService.getUserLogin(obj);
        if (null != user) {
            String codes = this.menuService.appMenus(user.getId());
            //设置返回参数
            ret = new HttpResponse(HttpResponse.Status.SUCCESS, codes, user);
            log.debug("登录成功,用户Id" + user.getId() + ",用户名称：" + user.getName() + ",拥有的menuCode：" + codes);
        }
        return ret;

    }

    /**
     * 左菜单栏
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
        //菜单
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
     * 单点登录
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
            //判断key值是否为空
            bool =false;
            page ="/goError.jsp?errorMsg=1";
        }else if (null == request.getParameter("userCode")) {
            //判断用户编码是否为空
            bool =false;
            page ="/goError.jsp?errorMsg=2";
        }
        if(bool){
            String keyStr = request.getParameter("key");
            String userCodeStr = request.getParameter("userCode");
            log.debug("进入单点登录方法，key：" + keyStr+";userCode："+userCodeStr);
            //Base64解密
            String key = Base64.getFromBase64(keyStr);
            String userCode = Base64.getFromBase64(userCodeStr);
            log.debug("Base64解密后，key：" + key+";userCode："+userCode);
            //判断key值是否匹配
            if(key.equals(AppConfig.getProperty("sso.key"))){
                //根据编码查询是否存在该用户
                User user = this.userService.getUserByCode(userCode);
                if (null != user) {
                    String ip = "";
                    try {
                        ip = GetIp.getIpAddr(request);
                    } catch (Exception e) {
                        log.message("获取用户：" + user.getName() + "的Ip错误！");
                    }
                    user.setLoginIp(ip);
                    this.userService.updateUserLogin(user);
                    //设置session中的参数
                    addSession(user, request);
                    page = "/ewms.jsp";
                }
            }else {
                page ="/goError.jsp?errorMsg=3";
            }
        }
        //跳转到页面
        response.sendRedirect(request.getContextPath() + page);
    }

    /**
     * 保存session
     *
     * @param user
     */
    public void addSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //设置session中的参数
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
