package com.shenrs.controller;

import com.shenrs.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-30 15:10
 **/
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello(){
        return helloService.sayHello("自定义starter");
    }

}
