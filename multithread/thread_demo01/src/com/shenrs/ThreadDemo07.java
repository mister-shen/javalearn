package com.shenrs;

/**
 * 线程优先级：通过一个priority来控制优先级，范围为1-10，其中10最高，默认值为5。
 */
public class ThreadDemo07 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++){
                    System.out.println("子线程1， i: " + i);
                }
            }
        });

        t1.setPriority(10);//设置t1线程优先级最高
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++){
                    System.out.println("子线程2， i: " + i);
                }
            }
        });
        t2.start();

        Thread.currentThread().setPriority(8);//设置主线程优先级
        for (int i = 0; i < 5; i++){
            System.out.println("主线程， i: " + i);
        }
        System.out.println("主线程执行完毕。。。。。");
    }
}
