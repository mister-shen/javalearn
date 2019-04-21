package com.shenrs.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-30 14:58
 **/
@Configuration
@ConditionalOnWebApplication // web应用才生效
@EnableConfigurationProperties(HelloProperties.class) // 加入helloproperties,使其可以注入
public class HelloServiceAutoConfiguration {

    @Autowired
    private HelloProperties helloProperties;

    @Bean
    public HelloService helloService(){
        HelloService helloService = new HelloService();
        helloService.setProperties(helloProperties);
        return helloService;
    }

}
