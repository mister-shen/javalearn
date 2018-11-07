package com.shenrs.config;

import com.shenrs.component.LoginHandlerInterceptor;
import com.shenrs.component.MylocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @EnableWebMvc:全面接管SpringMVC,所有的自动配置都失效了，需要自己配置。
 * @author shenrs
 * @Description 扩展mvc
 * @create 2018-11-07 11:42
 **/
//@EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送/shenrs 请求来到success.html页面
        registry.addViewController("/shenrs").setViewName("success");

    }

    // 所有的WebMvcConfigurer组件都会一起起作用
    @Bean // 将组件注册在容器
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/index.html").setViewName("dashboard");
            }

            /**
             * 添加拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                /*registry.addInterceptor(new LoginHandlerInterceptor())// 添加拦截器
                        .addPathPatterns("/**") // 添加链接地址
                        .excludePathPatterns(
                                "/webjars/**",
                                "/asserts/**",
                                "/login.html",
                                "/user/*",
                                "/");// 排除不拦截的地址
*/
            }
        };
        return webMvcConfigurer;
    }

    /**
     * 将国际化自定义的LocaleResolver添加到容器中
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MylocalResolver();
    }

}
