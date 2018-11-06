package com.shenrs;

import com.shenrs.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot单元测试
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private Person person;

	@Autowired
	ApplicationContext ioc;

	@Test
	public void testHelloService() {
		/**
		 * 判断容器中有没有helloService
		 */
		System.out.println(ioc.containsBean("helloService"));
	}

	@Test
	public void contextLoads() {
		System.out.println(person);
	}

}
