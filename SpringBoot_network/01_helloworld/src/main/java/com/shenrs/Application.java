package com.shenrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenrs
 * @Description @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 * @create 2018-10-17 13:31
 **/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // Spring应用启动起来
        SpringApplication.run(Application.class, args);
    }
}
