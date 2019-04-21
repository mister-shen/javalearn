package com.shenrs.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenrs
 * @Description
 * @create 2019-04-21 19:25
 **/
@Configuration
public class MyAMQPConfig {

    /**
     * 定义消息转换器默认转换为json
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        // 如果转换对象时，对象必须含有无参构造方法
        return new Jackson2JsonMessageConverter();
    }
}
