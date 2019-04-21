package com.shenrs.controller;

import com.shenrs.bean.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenrs
 * @Description
 * @create 2018-10-17 13:29
 **/
@RestController
// @EnableConfigurationProperties({ConfigBean.class})
// 注入ConfigBean的方式：
//      1.在注入类上加 @EnableConfigurationProperties注解
//      1.在ConfigBean类上添加@Component注解
public class HelloController {

    @Autowired
    private ConfigBean configBean;

    @Value("${my.name}")
    private String name;

    @Value("${my.age}")
    private String age;

    @RequestMapping("/hello")
    public String hello(){
        return "hello world !";
    }

    /**
     * controller 获取.properties文件配置
     * @return
     */
    @RequestMapping("/miya")
    public String miya(){
        return "my name is " + name + ", " + age;
    }

    @RequestMapping("/lucy")
    public ConfigBean lucy(){
        return configBean;
    }


}
