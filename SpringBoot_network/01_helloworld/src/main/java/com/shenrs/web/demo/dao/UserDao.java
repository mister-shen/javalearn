package com.shenrs.web.demo.dao;

import com.shenrs.web.base.dao.BaseDao;
import com.shenrs.web.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据接口
 */
@Mapper
public interface UserDao extends BaseDao<User> {
    
}