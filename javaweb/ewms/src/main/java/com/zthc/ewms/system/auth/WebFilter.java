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
 * ��ҳȨ�޹����ܿ���
 */
public class WebFilter implements Filter {
    //��¼��ַ
    private static final String GOTOURL = "/login.html";
    //��ҳ��ַ
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
        if (null == session.getAttribute("userId")) { //�ж��û�Id�Ƿ�Ϊ��
            //app�ӿ�,����ͨ��
            String appFlag = request.getParameter("appFlag");
            if (null != appFlag && "1".equals(appFlag)) {
                log.debug("app�ӿ�url:" + url + "���û�δ��¼������ͨ��");
                filterChain.doFilter(request, response);
                return;
            }
            //�ж�Url�Ƿ����ڵ�¼ǰ����Ҫ���ص�URL
            for (String loginFrontUrl : InterceptURL.getLoginFrontUrl()) {
                if (url.equals(loginFrontUrl)) {
                    log.debug("url:"+url+"���û�δ��¼������ͨ��");
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            //�ж�session���Ƿ��в���,�����session����ת����ҳ
            log.debug("url:" + url + "���û�δ��¼�����ص���¼����");
            res.getWriter().print("<script>parent.window.location.href='" + GOTOURL + "'</script>");
            return;
        }else if(url.equals(GOTOURL)||url.equals("")){
            //�ж�session���Ƿ��в���,�����session����ת����ҳ
            log.debug("url:" + url + "���û��ѵ�¼�����ص��׽���");
            res.getWriter().print("<script>parent.window.location.href='" + GOINDEX + "'</script>");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
