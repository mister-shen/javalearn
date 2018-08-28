package com.shenrs;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Parent implements Runnable {
    Semaphore wc;
    String name;

    public Parent(Semaphore wc, String name) {
        this.wc = wc;
        this.name = name;
    }

    @Override
    public void run() {
        // 获取到资源，减去1
        int availablePermits = wc.availablePermits();
        if(availablePermits > 0) {//代表还有资源
            System.out.println(name + "资源获取成功1......");
        }else{
            System.out.println(name + "资源获取失败......");
        }
        try {
            wc.acquire();
            System.out.println(name + "开始使用资源2......");
            //模拟获取资源时间
            Thread.sleep(1000);
            System.out.println(name + "资源使用完毕3....");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            wc.release();// 释放资源
        }
    }
}


/**
 * @author shenrs
 * @Description
 * @create 2018-08-28 17:05
 **/
public class Test003 {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//创建最多支持三个资源访问
        for (int i = 0; i < 10; i++){
            new Thread(new Parent(semaphore, "第"+i+"个,")).start();
        }
    }
}
