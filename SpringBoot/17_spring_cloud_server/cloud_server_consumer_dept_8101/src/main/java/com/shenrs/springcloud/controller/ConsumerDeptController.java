package com.shenrs.springcloud.controller;

import com.shenrs.springcloud.eneites.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    /**
     * 使用restTemplate访问resetfull接口非常简单粗暴
     * (url, requestMap, ResponseBean.class)这三个参数分别代表REST请求地址、请求参数、Http响应转换被转换成的对象类型
     */
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PERFIX + "/dept/add", dept, Boolean.class );
    }

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") String id){
        return restTemplate.getForObject(REST_URL_PERFIX + "/dept/get/"+id, Dept.class );
    }

    @GetMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PERFIX + "/dept/list", new ArrayList<Dept>().getClass());
    }

    @GetMapping("/consumer/dept/discovery")
    public Object discovery()
    {
        return restTemplate.getForObject(REST_URL_PERFIX + "/dept/discovery", Object.class);
    }


}
