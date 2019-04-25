package com.shenrs.task.controller;

import com.shenrs.task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenrs
 * @Description
 * @create 2019-04-25 16:25
 **/
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;


    @GetMapping("/hello")
    public String hello(){
        asyncService.hello();
        return "success";
    }
}
