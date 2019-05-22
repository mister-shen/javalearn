package com.shenrs.springcloud.controller;

import com.shenrs.springcloud.eneites.Dept;
import com.shenrs.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenrs
 * @Description
 * @create 2019-05-07 15:49
 **/
@RestController
public class ConsumerDeptController {

//    private static final String REST_URL_PERFIX = "http://localhost:8001";
    private static final String REST_URL_PERFIX = "http://cloudserver-dept";

    @Autowired
    private DeptClientService service;

    @GetMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return service.add(dept);
    }

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return service.get(id);
    }

    @GetMapping("/consumer/dept/list")
    public List<Dept> list(){
        return service.list();
    }

    @GetMapping("/consumer/dept/discovery")
    public Object discovery()
    {
        return service.discovery();
    }


}
