package com.shenrs.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * @author shenrs
 * @Description
 * @create 2019-03-29 16:43
 **/
// 使用JPA注解配置映射关系
@Data
@Entity // 告诉jpa这是一个实体类（和数据表映射的类）
@Table(name="tab_user") // @table指定和那个数据表对应，如果省略默认表名就是user
public class User {

    @Id // 标识是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    private Integer id;

    @Column(name="last_name", length = 50)
    private String lastName;

    @Column
    private String email;
}
