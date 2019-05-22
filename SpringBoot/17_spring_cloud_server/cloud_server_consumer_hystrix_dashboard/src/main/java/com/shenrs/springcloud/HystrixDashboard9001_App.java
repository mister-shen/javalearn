package com.shenrs.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author shenrs
 * @Description
 * @create 2019-05-07 16:40
 **/
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboard9001_App {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboard9001_App.class, args);
    }
}
