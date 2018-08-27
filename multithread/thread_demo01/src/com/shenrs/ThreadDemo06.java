package com.shenrs;

/**
 * 线程优先级join
 */
public class ThreadDemo06 {

    /**
     * 用户线程：是主线程创建的线程，不随主线程一起销毁，是非守护线程
     * 守护线程：和主线程一起销毁
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++){
                    System.out.println("子线程， i: " + i);
                }
            }
        });
        t1.start();
        try {
            t1.join(); // 主线程让子线程优先执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++){
            System.out.println("主线程， i: " + i);
        }
        System.out.println("主线程执行完毕。。。。。");
    }
}

/**
 * t1、t2、t3三个线程按顺序执行
 */
class CreateThread06 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                new CreateThread06().forIn();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new CreateThread06().forIn();
            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new CreateThread06().forIn();
            }
        }, "t3");
        t3.start();

    }

    public void forIn(){
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ "， i: " + i);
        }
    }
}