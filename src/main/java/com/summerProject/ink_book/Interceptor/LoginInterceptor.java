package com.summerProject.ink_book.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    //登录前拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");

        if(loginUser == null){
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("").forward(request,response);
            return false;
        }
        return true;
    }
}
