package com.shenrs.filter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义filter
 * @author shenrs
 * @Description
 * @create 2018-11-08 14:02
 **/
@WebFilter(filterName = "myFilter", urlPatterns = {"/*"})
@Order(value = 1) //order的值越小 优先级约高
public class MyFilter implements Filter {
    private String noFilterUrl;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /*noFilterUrl = filterConfig.getInitParameter("no_filter_url");
        contextPath = filterConfig.getInitParameter("contextPath");*/
        noFilterUrl = "/user/login";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        StringBuffer url = req.getRequestURL();
        System.out.println(url.toString());
        if((contextPath+noFilterUrl).equals(uri)){// 不拦截的uri 放行
            chain.doFilter(request, response);
            return;
        }

        System.out.println("MyFilter process...");
        chain.doFilter(request, response); // 放行
    }

    @Override
    public void destroy() {

    }
}
