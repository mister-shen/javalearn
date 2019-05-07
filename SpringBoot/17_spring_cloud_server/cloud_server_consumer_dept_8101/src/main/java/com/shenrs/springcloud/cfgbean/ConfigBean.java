package com.shenrs.springcloud.cfgbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author shenrs
 * @Description
 * @create 2019-05-07 15:46
 **/
@Configuration
public class ConfigBean {

    /**
     * 将RestTemplate注入容器中
     * @return
     */
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    
}
