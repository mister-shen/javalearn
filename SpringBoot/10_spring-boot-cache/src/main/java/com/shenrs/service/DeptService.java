package com.shenrs.service;

import com.shenrs.bean.Department;
import com.shenrs.mapper.DepartmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author shenrs
 * @Description
 * @create 2019-04-21 17:42
 **/
@Service
public class DeptService {

    private Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private DepartmentMapper departmentMapper;


    @Cacheable(value = "dept")
    public Department getDeptById(Integer id){
        log.info("查询" + id + "号部门");
        return departmentMapper.getDeptById(id);
    }

}
