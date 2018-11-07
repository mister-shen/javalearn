package com.shenrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author shenrs
 * @Description
 * @create 2018-11-07 15:22
 **/
@Controller
public class LoginController {

//    @DeleteMapping
//    @PutMapping
//    @GetMapping
//    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if (!StringUtils.isEmpty(username)
                && "123456".equals(password)){
            // 登录成功,为了防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser", username);
            return "redirect:/index.html";
        }

        // 登录失败
        map.put("msg", "用户名或密码错误");
        return "login";
    }
}
