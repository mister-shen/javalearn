package com.shenrs.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot默认支持两种技术来和Es交互
 * 1.Jest(默认不生效，需要导入Jset工具包io.searchbox.client.JestClient)
 * 2.SpringData ElasticSearch[ES版本可能不合适]
 * 		版本适配说明https://github.com/spring-projects/spring-data-elasticsearch
 * 			如果版本不适配，
 * 				1)升级SpringBoot版本
 * 				2)安装对应的ES版本
 * 		1)TransportClient节点信息 clusterName（默认：elasticsearch），clusterNodes
 * 		2)ElasticsearchTemplate 操作es
 * 		3)编写一个实现了ElasticsearchRepository的子接口来操作ES
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
