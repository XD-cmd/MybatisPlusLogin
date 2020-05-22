package com.swjd.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //写放行的条件
        String url = request.getRequestURI();//获取当前用户请求访问的地址
        //1.不需要登录就能访问
        if (url.indexOf("login")>=0||url.indexOf("Login")>=0){
            return true;//放行
        }
        if (url.indexOf("main")>=0||url.indexOf("Main")>=0){
            return true;//放行
        }
        //2.需要登录才能访问
        HttpSession session = request.getSession();
        if (session.getAttribute("activeName")!=null){
            return true;
        }

        //无条件到登录
        request.getRequestDispatcher("/toLogin").forward(request,response);
        return false;
    }
}
