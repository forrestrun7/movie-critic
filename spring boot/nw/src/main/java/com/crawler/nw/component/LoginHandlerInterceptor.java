package com.crawler.nw.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    //拦截器，当用户未登录时访问一些页面，导向登录界面
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("userid");
        if (user == null){
            // 未登录
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/log").forward(request,response);
            return false;
        }
            // 已登录
            else{
                return true;
        }

    }
}
