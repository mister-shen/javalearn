package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * springboot 主启动类
 * @author shenrs
 * @Description
 * @create 2019-07-23 14:02
 **/
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    /**
     * 将需要生成分布式主键的工具类加入容器中
     * @return
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
