package com.shenrs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	public void contextLoads() throws SQLException {
		System.err.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		System.err.println(connection);
		connection.close();
	}

}
