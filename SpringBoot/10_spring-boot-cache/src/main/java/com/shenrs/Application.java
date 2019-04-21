package com.shenrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一.搭建环境
 * 	1.初始化数据库表
 * 	2.创建javabean
 * 	3.整合mybatis数据库
 * 		①配置数据源信息
 * 		②使用注解版的mybatis
 * 二.快速体验缓存
 * 	1.开启基于注解的缓存
 * 	2.标注注解缓存
 * 		@Cacheable
 * 		@CachePut
 * 		@CacheEvict
 * 	默认使用的是ConcurrentMapCacheMananger == ConcurrentMapCache:将数据保存在ConcurrentMap<Object, Object>中
 * 	开发中使用缓存中间件：redis memcached ehcache
 *  三、整合redis作为缓存
 *  	Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。
 *  	1.安装redis：使用docker
 *  	2.引入redis的star
 *  	3.配置redis
 *  	4.测试缓存
 *  		原理：CacheManager==Cache缓存组件来实际给缓存中存数据
 *  		1)引入redis的starter,容器帮我们保存的是RedisCacheManager
 *  		2)RedisCacheManager帮我们创建RedisCache来作为缓存组件；RedisCache通过操作缓存数据。
 *  		3)默认保存数据k-v都是object;利用序列化保存。如何存为JSON?
 *  			1.引入redis的starter,chacheManager变为RedisCacheManager;
 *  			2.默认创建的 RedisCacheManager 操作redis的时候使用的是 RedisTemplate<Object,Object>
 *  			3.RedisTemplate<Object,Object>是默认使用jdk的序列化机制
 *
 */
@SpringBootApplication
@EnableCaching // 开启注解缓存
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
