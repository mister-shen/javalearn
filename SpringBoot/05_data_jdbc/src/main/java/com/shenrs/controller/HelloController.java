package com.shenrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-27 10:43
 **/
@Controller
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/query")
    @ResponseBody
    public Map<String, Object> map(){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from department ");
        return maps.get(0);
    }
}
