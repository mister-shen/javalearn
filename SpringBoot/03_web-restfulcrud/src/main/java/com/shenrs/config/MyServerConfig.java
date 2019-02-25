package com.shenrs.config;

import com.shenrs.filter.MyFilter;
import com.shenrs.listener.MyListener;
import com.shenrs.servlet.MyServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 注册三大组件 Servlet Filter Listener
 * @author shenrs
 * @Description
 * @create 2018-11-08 13:45
 **/
@Configuration
public class MyServerConfig {

    /**
     * 获取上下文相对路径
     */
    /*@Value("${server.servlet.context-path}")
    private String contextPath;*/

    /**
     * 注册Servlet  与注解法使用
     * @return
     */
    @Bean
    public ServletRegistrationBean<MyServlet> myServlet(){
        return new ServletRegistrationBean<MyServlet>(new MyServlet(), "/myServlet");
    }

    /**
     * 注册Filter  与注解法使用
     * @return
     */
    /*@Bean
    public FilterRegistrationBean<MyFilter> myFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));   // 添加拦截的url
        // 设置不拦截的地址
        filterRegistrationBean.addInitParameter("no_filter_url", "/user/login");
        filterRegistrationBean.addInitParameter("contextPath", contextPath);
        return filterRegistrationBean;
    }*/

    /**
     * 注册Listener 与注解法使用
     * @return
     */
    /*@Bean
    public ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean(){
        ServletListenerRegistrationBean listenerRegistrationBean =
                new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return listenerRegistrationBean;
    }*/

}
