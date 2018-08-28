package com.shenrs;

/**
 * wait方法测试
 *  总结：1、wait、notify、notifyAll只能与synchronized使用
 *       2、wait(20):表示该线程休眠20毫秒后才能被notify或notifyAll唤醒
\ */
public class ThreadDemo08 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                for (int i = 0; i < 20; i++){
                    if(i%2 == 1) {
                        try {
                            this.wait(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("子线程1， i: " + i);
                    this.notifyAll();
                }
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                for (int i = 0; i < 20; i++){
                    if(i%2 == 0) {
                        try {
                            this.wait(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("子线程2， i: " + i);
                    this.notify();
                }
            }
        });
        t2.start();

        for (int i = 0; i < 5; i++){
            System.out.println("主线程， i: " + i);
        }
        System.out.println("主线程执行完毕。。。。。");
    }
}
