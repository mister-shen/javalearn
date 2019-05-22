package com.shenrs.rules;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Ribbon负载均衡规则-- 针对单个微服务
 * @author shenrs
 * @Description
 * @create 2019-05-10 9:47
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
//        return new RandomRule(); // Ribbon默认轮询，我自定义为随机
        return new RoundRobinFiveRule(); // 自定义为每五次轮询
    }
}
