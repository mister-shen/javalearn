package com.shenrs.springcloud.service;

import com.shenrs.springcloud.eneites.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author shenrs
 * @Description
 * @create 2019-05-10 15:38
 **/
@FeignClient(value = "cloudserver-dept", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    public List<Dept> list();

    @PostMapping("/dept/add")
    public boolean add(Dept dept);

    @PostMapping("/dept/discovery")
    public Object discovery();
}
