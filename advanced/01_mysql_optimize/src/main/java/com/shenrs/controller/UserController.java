package com.shenrs.controller;

import com.shenrs.service.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shenrs
 * @Description 用户管理Controller
 * @create 2018-09-18 14:25
 **/
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/register")
    public String register(String name, String pwd) {
        return userService.register(name, pwd);
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public String get(@PathVariable Long id) {
        return userService.get(id);
    }
}
