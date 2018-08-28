package com.shenrs;

import java.util.concurrent.CountDownLatch;

/**
 * @author shenrs
 * @Description CountDownLatch 计数器：让其他线程执行完毕，再执行该线程
 * @create 2018-08-28 16:10
 **/
public class Test001 {
    public static void main(String[] args){
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线1程开始计数.....");
                try {
                    Thread.sleep(200);// 线程执行时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();//每次减一
                System.out.println("子线1程结束计数.....");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线2程开始计数.....");
                try {
                    Thread.sleep(100);// 线程执行时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("子线2程结束计数.....");
            }
        }).start();

        try {
            countDownLatch.await();//r如果不为0一直等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程开始执行任务......");
        for (int i = 0; i < 10; i++){
            System.out.println("i：" + i);
        }

    }
}
