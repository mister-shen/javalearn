package com.shenrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

//    @RequestMapping({"/", "/login.html"})
//    public String index() {
//        return "index";
//    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello world!";
    }

    @RequestMapping("success")
    public String success(Map<String, Object> map){
        map.put("hello", "<h5>hello thymeleaf</h5>");
        // classpath:/templates/success.html
        map.put("users", Arrays.asList("zhangsan", "lisi", "wanger", "mazi"));
        return "success";
    }
}
