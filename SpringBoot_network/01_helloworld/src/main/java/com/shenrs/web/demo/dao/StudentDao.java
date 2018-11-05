package com.shenrs.web.demo.dao;

import com.shenrs.web.base.dao.BaseDao;
import com.shenrs.web.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生数据接口
 */
@Mapper
public interface StudentDao extends BaseDao<Student> {

}