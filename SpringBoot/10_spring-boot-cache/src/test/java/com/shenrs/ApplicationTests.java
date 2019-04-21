package com.shenrs;

import com.shenrs.bean.Employee;
import com.shenrs.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private StringRedisTemplate stringRedisTemplate; // 操作k-v字符串

	@Autowired
	private RedisTemplate redisTemplate; // 操作k-v对象

	@Autowired
	private RedisTemplate<Object, Employee> emRedisTemplate; // 操作k-v对象

	@Test
	public void contextLoads() {
		System.out.println(employeeMapper.getEmpById(1));
	}

	/**
	 * Redis常见的5大数据类型
	 * String(字符串)、List(列表)、Set(无序集合)、Hash(散列)、ZSet(有序集合)
	 * 	stringRedisTemplate.opsForValue();	[String(字符串)]
	 * 	stringRedisTemplate.opsForList();	[List(列表)]
	 * 	stringRedisTemplate.opsForSet();	[Set(无序集合)]
	 * 	stringRedisTemplate.opsForHash();	[Hash(散列)]
	 * 	stringRedisTemplate.opsForZSet();	[ZSet(有序集合)]
	 */
	@Test
	public void test01(){
		/*ValueOperations<String, String> strOper = stringRedisTemplate.opsForValue();
		strOper.append("msg", "hello");
		String msg = strOper.get("msg");
		System.out.println(msg);*/
		ListOperations<String, String> listOper = stringRedisTemplate.opsForList();
		listOper.leftPush("mylist", "1");
		listOper.leftPush("mylist", "2");
		listOper.leftPush("mylist", "3");
		listOper.leftPush("mylist", "4111");
		String mylist = listOper.leftPop("mylist");
		System.out.println(mylist);
	}


	@Test
	public void test02() throws Exception{
		Employee empById = employeeMapper.getEmpById(2);
		//默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
		emRedisTemplate.opsForValue().set("emp02", empById);
//		redisTemplate.opsForValue().set("msg", "aa");// 返回的是"aa" 格式不正确
		stringRedisTemplate.opsForValue().set("msg", "aa"); // 使用string类型的正确
		// 1.将数据以json的格式保存
		//	1)将对象转换成json
		//  2)redisTemplate默认的序列化规则
	}
}
