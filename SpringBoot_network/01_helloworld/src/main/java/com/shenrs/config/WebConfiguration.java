package com.shenrs.config;

import com.shenrs.filter.MyFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenrs
 * @Description 自定义filter Config
 * @create 2018-11-02 10:36
 **/
@Configuration
public class WebConfiguration {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");  // 添加拦截路径
        registration.addInitParameter("paramName", "paramValue");   // filter初始化参数
        registration.setName("MyFilter");
        registration.setOrder(1);   // 设置注册bean的顺序。
        return registration;
    }
}
