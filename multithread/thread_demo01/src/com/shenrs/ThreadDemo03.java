package com.shenrs;

/**
 *
 * @author shenrs
 * @Description 如何创建多线程
 * @create 2018-08-27 9:51
 **/
public class ThreadDemo03 {


    /**
     * 什么是进程，进程就是正在运行的应用程序，进程是线程的集合
     * 什么是线程，线程就是一条执行路径，一个独立的执行单元
     * 什么是多线程，为了提高程序效率
     * 创建有哪些方式？
     * 1.使用继承Thread类方式
     * 2.使用实现runlabe接口方式
     * 3.使用匿名内部类方式
     * 4.callable
     * 5.使用线程池创建线程
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 使用匿名内部类创建线程
         */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++){
                    System.out.println("子线程 run,i" + i);
                }
            }
        });
        thread.start();
        for (int i = 0; i < 30; i++){
            System.out.println("主线程run ,i" + i);
        }
    }
}
