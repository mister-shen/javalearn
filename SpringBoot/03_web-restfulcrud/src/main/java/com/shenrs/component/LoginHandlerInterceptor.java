package com.shenrs.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查
 * @author shenrs
 * @Description
 * @create 2018-11-07 15:48
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor{

    /**
     * 目标方法执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");

        if(user == null){
            // 未登录，返回登录页面
            request.setAttribute("msg", "您没有访问的权限，请先登录。");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        }
        // 已登录，直接放行
        return true;
    }
}
