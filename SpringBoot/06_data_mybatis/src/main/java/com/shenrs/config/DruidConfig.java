package com.shenrs.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenrs
 * @Description druid数据源配置
 * @create 2019-03-29 13:48
 **/
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    // 配置druid的监控
    // 1.配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<String, String>();

        // 设置登录用户名与密码
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "1");
        initParams.put("allow", ""); // 默认是允许所有访问
        initParams.put("deny", "192.168.2.130"); // 拒绝某一个或多个ip访问

        bean.setInitParameters(initParams);
        return bean;
    }

    // 2.配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("exclusions", "*.js,*.css,/druid/*"); // 排除拦截请求

        bean.setInitParameters(initParams);

        // 拦截的请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
