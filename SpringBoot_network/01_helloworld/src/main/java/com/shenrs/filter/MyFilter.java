package com.shenrs.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * filters用于录调用日志、排除有XSS威胁的字符、执行权限验证等等。
 * Spring Boot自动添加了OrderedCharacterEncodingFilter和HiddenHttpMethodFilter，
 * 并且我们可以自定义Filter。
 * @author shenrs
 * @Description 自定义的filter
 * @create 2018-11-02 10:37
 **/
public class MyFilter implements Filter{
    /**
     * 系统启动初始化的时候进入该方法
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("进入filter："+filterConfig.getInitParameter("paramName"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("this is MyFilter,url :" + request.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 销毁的时候执行
     */
    @Override
    public void destroy() {

    }
}
