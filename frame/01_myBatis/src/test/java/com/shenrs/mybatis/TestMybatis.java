package com.shenrs.mybatis;

import java.io.IOException;
import java.io.Reader;

import com.shenrs.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class TestMybatis {
	@Test
    public void getUser() throws IOException {
		String resource = "mybatis.xml";
		// 读取配置文件
		Reader reader = Resources.getResourceAsReader(resource);
		// 获取会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession openSession = sqlSessionFactory.openSession();
		// 查询
		String sql = "com.shenrs.mybatis.mapper.UserMapper.getUser";
		// 调用api查询
		User user = openSession.selectOne(sql, 1);
		System.out.println(user.toString());
	}

	@Test
	public void add() throws IOException{
		String resource = "mybatis.xml";
		// 读取配置文件
		Reader reader = Resources.getResourceAsReader(resource);
		// 获取会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession openSession = sqlSessionFactory.openSession();
		// 查询
		String sql = "com.shenrs.mybatis.mapper.UserMapper.addUser";
		// 调用api查询
		User userPa = new User();
		userPa.setAge(19);
		userPa.setName("张三");
		int reuslt = openSession.insert(sql, userPa);
		System.out.println(reuslt);
	}
}