package com.shenrs.web.demo.service.impl;

import com.shenrs.web.base.dao.BaseDao;
import com.shenrs.web.base.service.impl.BaseServiceImpl;
import com.shenrs.web.demo.dao.StudentDao;
import com.shenrs.web.demo.pojo.Student;
import com.shenrs.web.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 
* Title: StudentServiceImpl
* Description: 
* 用户操作实现类 
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
    @Autowired
	private StudentDao studentDao;
	
	@Override
	protected BaseDao<Student> getMapper() {
		return this.studentDao;
	}
	
}