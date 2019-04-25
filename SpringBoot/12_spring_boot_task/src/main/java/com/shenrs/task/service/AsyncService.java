package com.shenrs.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author shenrs
 * @Description 测试异步调用功能
 * @create 2019-04-25 16:24
 **/
@Service
public class AsyncService {

    /*这个方法将异步处理*/
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中。。。");
    }
}
