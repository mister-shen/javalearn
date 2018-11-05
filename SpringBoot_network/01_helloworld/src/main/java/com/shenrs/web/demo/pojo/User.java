package com.shenrs.web.demo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户pojo类
 */
@Data
public class User implements Serializable{
    /** 编号*/
	private Long id;
	/** 姓名*/
	private String name;
	/** 年龄*/
	private Integer age;

}