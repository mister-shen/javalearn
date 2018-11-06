package com.shenrs.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中的配置的每一个属性的值，映射到这个组件中。
 * @author shenrs
 * @Description
 * @create 2018-11-06 14:57
 * @ConfigurationProperties:告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *  prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 *
 *  只有这个组件是容器中的组件，才能容器提供@ConfigurationProperties功能
 *  @ConfigurationProperties(prefix = "person")：默认从全局配置文件获取值
 *  @PropertySource(value = "classpath:person.properties"):加载指定配置文件
 **/
@Data
@Component
@PropertySource(value = "classpath:person.properties")
@ConfigurationProperties(prefix = "person")
@Validated
public class Person
{
    //@Value("${person.last-name}")
    //@Value("${person.lastName}") @Value 严格区分
    //@Email
    private String lastName;
    //@Value("#{11*2}")
    private Integer age;
    //@Value("true")
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

}
