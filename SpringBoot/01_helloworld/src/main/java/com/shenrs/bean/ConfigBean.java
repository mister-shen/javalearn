package com.shenrs.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 配置类
 * @author shenrs
 * @Description
 * @create 2019-03-29 10:36
 **/
@Data
@ConfigurationProperties(prefix = "my")
@Component
public class ConfigBean {
    private String name;
    private int age;
    private int number;
    private String uuid;
    private int max;
    private String value;
    private String greeting;
    private Date birth;
    private Map<String, Object> map;
    private List<Object> list;
    private Dog dog;
}
