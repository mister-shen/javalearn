package com.shenrs.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author shenrs
 * @Description
 * @create 2019-05-07 16:40
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)/*由于启动出错
Failed to configure a DataSource: 'url' attribute is not specified and .....
错误解决方案：https://www.jianshu.com/p/836d455663da
排除注入数据源*/
public class DeptConsumer8101_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer8101_App.class, args);
    }
}
