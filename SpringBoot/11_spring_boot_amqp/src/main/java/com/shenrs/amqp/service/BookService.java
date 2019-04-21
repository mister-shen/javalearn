package com.shenrs.amqp.service;

import com.shenrs.amqp.model.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author shenrs
 * @Description
 * @create 2019-04-21 19:49
 **/
@Service
public class BookService {

    /**
     * 监听某一个消息队列，接收消息
     * @param book
     */
    @RabbitListener(queues = {"shenrs.news"})
    public void receive(Book book){
        System.out.println("收到消息：" + book);
    }


    @RabbitListener(queues = "shenrs")
    public void receive02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
