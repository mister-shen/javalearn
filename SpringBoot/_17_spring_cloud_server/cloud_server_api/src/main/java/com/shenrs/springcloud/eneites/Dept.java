package com.shenrs.springcloud.eneites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author shenrs
 * @Description
 * @create 2019-05-06 16:41
 **/
@AllArgsConstructor /*有参构造*/
@NoArgsConstructor  /*无参构造*/
@Data   /*生成get set toString hashCode equals  */
@Accessors(chain = true)    /*给对象赋值时可以连续.属性设置值*/
public class Dept {
    private Long deptno;    // 主键
    private String dname;   // 部门名称
    private String db_source;   // 来自那个数据库，因为可以一个服务对应一个数据库，同一个信息被存储到不同数据库。

    /*@Accessors(chain = true) 注解的作用*/
   /* public static void main(String[] args) {
        new Dept().setDb_source("aa").setDeptno(1L).setDname("haha");
    }*/
}
