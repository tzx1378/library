package com.tzx.interceptor;

import com.tzx.util.Const;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 去除要拦截的路径
        Set<String> uri = new HashSet();

        uri.add("/toLogin");
        uri.add("/checkReader");
        uri.add("/toRegister");
        uri.add("/submitAddReader");
        uri.add("/doLogin");

        //请求路径
        String servletPath = request.getServletPath();
        if (uri.contains(servletPath)) {
            return true;
        }

        //2.判断用户是否登录 会员是否登录
        HttpSession session = request.getSession();
        Object admin = session.getAttribute(Const.ADMIN);
        Object reader = session.getAttribute(Const.READER);
        if (admin != null || reader != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/toLogin");
            return false;
        }

    }

}
