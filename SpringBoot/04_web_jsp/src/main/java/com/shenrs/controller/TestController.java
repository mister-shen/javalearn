package com.shenrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shenrs
 * @Description
 * @create 2019-02-25 14:30
 **/
@Controller
public class TestController {
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("msg", "SpringBoot-JSP");
        return "index";
    }

}
