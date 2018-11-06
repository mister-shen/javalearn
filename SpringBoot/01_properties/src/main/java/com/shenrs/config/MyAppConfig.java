package com.shenrs.config;

import com.shenrs.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration：指明当前类是一个配置类，就是来代替之前的Spring配置文件。
 * 在配置文件中用<bean></bean>标签添加组件
 * @author shenrs
 * @Description
 * @create 2018-11-06 16:01
 **/
@Configuration
public class MyAppConfig {

    /**
     * 将方法的返回值添加到容器中，容器中这个组件默认的id就是方法名
     * @return
     */
    @Bean
    public HelloService helloService(){
        System.out.println("配置@Bean给容器添加组件了。");
        return new HelloService();
    }
}
