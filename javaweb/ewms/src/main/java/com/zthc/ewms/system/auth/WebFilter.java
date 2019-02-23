package com.zthc.ewms.system.auth;


import drk.system.Log;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网页权限过滤总控器
 */
public class WebFilter implements Filter {
    //登录地址
    private static final String GOTOURL = "/login.html";
    //首页地址
    private static final String GOINDEX = "/ewms.jsp";
    private final static Log log = Log.getLog("com.zthc.ewms.system.auth.WebFilter");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @SuppressWarnings("rawtypes")
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        String url = req.getRequestURI();


        if(url.endsWith(".css")||url.endsWith(".js")||url.endsWith(".ico")||url.endsWith(".jpg")||url.endsWith(".jpeg")||url.endsWith(".png")||url.endsWith(".gif")){
            filterChain.doFilter(request, response);
            return;
        }
        if (null == session.getAttribute("userId")) { //判定用户Id是否为空
            //app接口,允许通过
            String appFlag = request.getParameter("appFlag");
            if (null != appFlag && "1".equals(appFlag)) {
                log.debug("app接口url:" + url + "，用户未登录，允许通过");
                filterChain.doFilter(request, response);
                return;
            }
            //判断Url是否属于登录前不需要拦截的URL
            for (String loginFrontUrl : InterceptURL.getLoginFrontUrl()) {
                if (url.equals(loginFrontUrl)) {
                    log.debug("url:"+url+"，用户未登录，允许通过");
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            //判断session中是否有参数,有情况session并跳转到首页
            log.debug("url:" + url + "，用户未登录，拦截到登录界面");
            res.getWriter().print("<script>parent.window.location.href='" + GOTOURL + "'</script>");
            return;
        }else if(url.equals(GOTOURL)||url.equals("")){
            //判断session中是否有参数,有情况session并跳转到首页
            log.debug("url:" + url + "，用户已登录，拦截到首界面");
            res.getWriter().print("<script>parent.window.location.href='" + GOINDEX + "'</script>");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
