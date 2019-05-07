package com.shenrs.springcloud.service;

import com.shenrs.springcloud.eneites.Dept;

import java.util.List;


public interface DeptService
{
	public boolean add(Dept dept);

	public Dept get(Long id);

	public List<Dept> list();
}
