package com.shenrs.web.demo.pojo;

import lombok.Data;

@Data
public class Student {

    /** 学生编号*/
	private Long id;
	/** 学生姓名*/
	private String name;
	/** 学生年龄*/
	private Integer age;
	
}