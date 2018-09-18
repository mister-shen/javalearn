package com.shenrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author shenrs
 * @Description 使用取摸方式分表
 * @create 2018-09-18 14:03
 **/
@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 注册用户
     */
    public String register(String name, String pwd) {
        // 1.先获取到自增长id
        String idInsertSql = "INSERT INTO UUID VALUES(NULL)";
        jdbcTemplate.update(idInsertSql);
        // 2.获取最后插入的id
        Long insertId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID() ", Long.class);
        // 3.判断存储表名称
        String tableName = "USER" + insertId % 3;
        // 4.注册数据
        String insertSql = "INSERT INTO " + tableName + " VALUES("+insertId+", '"+name+"', '"+pwd+"')";
        System.out.println("insertSql：" + insertSql);
        jdbcTemplate.update(insertSql);

        return "success";
    }

    /**
     * 获取用户名称
     * @param id
     * @return
     */
    public String get(Long id) {
        String tableName = "USER" + id % 3;
        String querySql = "SELECT NAME FROM "+ tableName + " WHERE ID = " + id;
        System.out.println("query SQL：" + querySql);
        return jdbcTemplate.queryForObject(querySql, String.class);
    }
}
