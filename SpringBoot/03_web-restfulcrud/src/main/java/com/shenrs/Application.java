package com.shenrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 扫描servlet 、 filter 、 listener
 */
@ServletComponentScan(basePackages = {"com.shenrs.servlet", "com.shenrs.filter", "com.shenrs.listener"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
