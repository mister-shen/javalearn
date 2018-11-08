package com.shenrs.config;

import com.shenrs.component.MyErrorAttributes;
import com.shenrs.component.MylocalResolver;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            /**
             * 定制嵌入式的servlet容器相关的定制
             * @param factory
             */
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8081);
            }
        };
    }


    // 所有的WebMvcConfigurer组件都会一起起作用
    @Bean // 将组件注册在容器
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer(){

            /**
             * 添加视图映射
             * @param registry
             */
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

    }

    /**
     * 将国际化自定义的LocaleResolver添加到容器中
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MylocalResolver();
    }

    /**
     * 自定义异常返回参数
     * 给容器中加入我们自己定义的ErrorAttributes
     * @return
     */
    @Bean
    public DefaultErrorAttributes defaultErrorAttributes(){
        return new MyErrorAttributes();
    }
}

