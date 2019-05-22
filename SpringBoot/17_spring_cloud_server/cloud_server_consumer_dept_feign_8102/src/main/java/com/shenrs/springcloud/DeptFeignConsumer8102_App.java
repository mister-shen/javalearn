package com.shenrs.springcloud;

import com.shenrs.rules.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shenrs
 * @Description
 * @create 2019-05-07 16:40
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)/*由于启动出错
Failed to configure a DataSource: 'url' attribute is not specified and .....
错误解决方案：https://www.jianshu.com/p/836d455663da
排除注入数据源*/
@EnableEurekaClient     //本服务启动后会自动注册进eureka服务中
@EnableFeignClients

@RibbonClients({
        @RibbonClient(name="cloudserver-dept", configuration = MySelfRule.class)    // 使用ribbon实现负载均衡
})
/*@EnableFeignClients(basePackages = {"com.shenrs.springcloud"}) // 扫描使用Feign接口服务的包
@ComponentScan(basePackages = {"com.shenrs.springcloud"}) // 扫描接口提供包*/
public class DeptFeignConsumer8102_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptFeignConsumer8102_App.class, args);
    }
}
