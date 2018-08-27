package com.shenrs;


/**
 * 继承Thread类创建线程
 */
class CreatThreadDemo01 extends Thread{

    /**
     * run 方法就是线程需要执行的任务或者执行的代码
     */
    @Override
    public void run() {
        for (int i = 0; i < 30; i++){
            System.out.println("run,i" + i);
        }
    }
}

/**
 *
 * @author shenrs
 * @Description 如何创建多线程
 * @create 2018-08-27 9:51
 **/
public class ThreadDemo01 {


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
        // 1.怎么调用线程
        CreatThreadDemo01 t1 = new CreatThreadDemo01();
        // 2.启动线程，不是调用run方法，而是调用start方法
        // 3.使用开启多线程后，程序的执行程序并不一定是从上往下
        t1.start();
        for (int i = 0; i < 30; i++){
            System.out.println("main,i" + i);
        }
    }
}
