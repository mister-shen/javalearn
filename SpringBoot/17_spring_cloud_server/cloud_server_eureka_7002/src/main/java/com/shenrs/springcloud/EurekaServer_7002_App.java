package com.shenrs.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author shenrs
 * @Description
 * @create 2019-05-07 17:40
 **/
@SpringBootApplication
@EnableEurekaServer /*Eureka 服务端启动类，接受其他微服务注册进来*/
public class EurekaServer_7002_App {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_7002_App.class, args);
    }
}
