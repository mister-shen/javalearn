package com.shenrs.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.activation.FileTypeMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Test
	public void contextLoads() {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("中奖通知");
		message.setText("恭喜您喜中100万大奖。");
		message.setTo("xxxxxxx@qq.com");
		message.setFrom("xxxxxxxx@qq.com");// 必须设置否则会报错
		mailSender.send(message);
	}


	@Test
	public void test02() throws MessagingException {
		// 创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // 上传文件设置为true

		// 邮件设置
		helper.setSubject("中奖通知");
		helper.setText("<span style='font-size:20px; color:red;'>中奖消息</span>，请看附件", true);
		helper.setTo("xxxxxx@qq.com");// 收邮件方
		helper.setFrom("xxxxxxxx@qq.com");// 必须设置否则会报错

		// 上传附件
		helper.addAttachment("1.png", new File("C:\\Users\\Administrator\\Desktop\\1.png"));
		helper.addAttachment("2.png", new File("C:\\Users\\Administrator\\Desktop\\2.png"));
		mailSender.send(mimeMessage);
	}

}
