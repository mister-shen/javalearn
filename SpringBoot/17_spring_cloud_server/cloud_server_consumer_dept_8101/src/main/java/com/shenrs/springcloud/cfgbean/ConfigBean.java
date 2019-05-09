package com.shenrs.springcloud.cfgbean;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    @LoadBalanced   //Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端 负载均衡工具
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    /**
     * 自定义负载均衡算法
     * @return
     */
    @Bean
    public IRule myRule(){
//        return new RandomRule(); // 随机访问算法
//        return new RoundRobinRule(); // 轮循默认
        // 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，
        // 还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮循策略进行访问
//        return new AvailabilityFilteringRule();
        // 根据平均响应时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高，
        // 刚启动时如果统计信息不足，则使用RoundRobinRule策略，等统计信息足够，
        // 会切换到WeightedResponseTimeRule
//        return new WeightedResponseTimeRule();
        // 先按照则使用RoundRobinRule策略获取服务，如果获取服务失败则在指定时间内进行重试，获取可用的服务。
//        return new RetryRule();
        // 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务。
//        return new BestAvailableRule();
        // 默认规则，复合判断server所在区域的性能和server的可用性选择服务器
        return new ZoneAvoidanceRule();
    }
    
}
