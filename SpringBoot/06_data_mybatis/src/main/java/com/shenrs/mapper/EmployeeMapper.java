package com.shenrs.mapper;

import com.shenrs.bean.Employee;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-29 16:03
 **/
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
