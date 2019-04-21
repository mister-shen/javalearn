package com.shenrs.amqp;

import com.shenrs.amqp.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private AmqpAdmin amqpAdmin; // 使用代码创建 queue/exchange/binding


	/**
	 * 创建交换器
	 */
	@Test
	public void createExChange(){
		// 创建交换器
		amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange.direct"));
		System.out.println("交换器创建完成");

		// 创建队列
		amqpAdmin.declareQueue(new Queue("amqpAdmin.queue.direct"));
		System.out.println("交换器创建完成");

		// 创建绑定
		amqpAdmin.declareBinding(new Binding("amqpAdmin.queue.direct", Binding.DestinationType.QUEUE, "amqpAdmin.exchange.direct", "amqp.queue", null));
	}

	/**
	 * 1.单播（点对点）
	 */
	@Test
	public void sendDirect() {
		// Message需要自己构造一个；定义消息体内容和消息头
		// rabbitTemplate.send(exchange, routeKey, messsage);

		// object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
		// rabbitTemplate.convertAndSend(exchange, routeKey, object);

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("msg", "这是第一个消息");
		dataMap.put("data", Arrays.asList(new Object[]{"helloworld", 222, false}));
		// 对象被默认序列化后发送出去
		rabbitTemplate.convertAndSend("exchange.direct", "shenrs.news", dataMap);
	}

	/**
	 * 2.广播
	 */
	@Test
	public void sendFanout(){
		rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("西游记", "吴承恩"));
	}

	/**
	 * 接收数据后，如何自动将数据转为json发送出去
	 */
	@Test
	public void receive(){
		Object msg = rabbitTemplate.receiveAndConvert("erqiu.news");
		System.out.println(msg.getClass());
		System.out.println(msg);
	}

}
