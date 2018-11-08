package com.shenrs.controller;

import com.shenrs.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

//    @RequestMapping({"/", "/login.html"})
//    public String index() {
//        return "index";
//    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam("user") String user){
        if(user.equals("aaa")){
            throw new UserNotExistException();
        }
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
