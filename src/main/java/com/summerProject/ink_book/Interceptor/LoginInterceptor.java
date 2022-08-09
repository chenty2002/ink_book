package com.summerProject.ink_book.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    //登录前拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        System.out.println(request.getRequestURI());
        System.out.println("loginUser = " + loginUser);
        if (loginUser == null) {
            request.setAttribute("msg", "请先登录");
            request.getRequestDispatcher("").forward(request, response);
            return false;
        }
        return true;
    }
}

