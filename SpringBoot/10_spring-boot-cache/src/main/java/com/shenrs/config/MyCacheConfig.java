package com.shenrs.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author shenrs
 * @Description
 * @create 2019-04-20 14:43
 **/
@Configuration
public class MyCacheConfig {

    /**
     * 自定义缓存key生成策略
     * @return
     */
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return target.hashCode() + method.getName() + Arrays.asList(params);
            }
        };
    }
}
