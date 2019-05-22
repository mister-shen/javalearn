package com.shenrs.springcloud;

import com.shenrs.rules.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
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

// 自定义的Ribbon规则类不能放在@ComponentScan（@SpringBootApplication注解下包含该注解）所扫描的包及子包下，
// 否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，也就是说我们达不到特殊化定制的目的了。
// 在启动微服务的时候就能去加载我们的自定义Ribbon配置，从而是配置生效。
@RibbonClient(name="cloudserver-dept", configuration = MySelfRule.class)
public class DeptConsumer8101_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer8101_App.class, args);
    }
}
