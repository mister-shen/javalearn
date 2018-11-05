package com.shenrs.web.demo.service.impl;

import com.shenrs.web.base.dao.BaseDao;
import com.shenrs.web.base.service.impl.BaseServiceImpl;
import com.shenrs.web.demo.dao.UserDao;
import com.shenrs.web.demo.pojo.User;
import com.shenrs.web.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* Title: UserServiceImpl
* Description: 
* 用户操作实现类 
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
	private UserDao userDao;
	
	@Override
	protected BaseDao<User> getMapper() {
		return this.userDao;
	}
	
}