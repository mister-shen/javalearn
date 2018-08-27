package com.shenrs;


class CreateTread04 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(getId()+":子线程, i" + i + " name： "+ getName());
            System.out.println(Thread.currentThread().getId()+":子线程, i" + i + " name： "+ Thread.currentThread().getName());
            if (i == 5){
                // stop();
                Thread.currentThread().stop(); //不安全，不建议大家使用。
            }
        }
    }
}

/**
 *
 * @author shenrs
 * @Description 使用常用的线程api demo
 * @create 2018-08-27 9:51
 **/
public class ThreadDemo04 {

    public static void main(String[] args) {
        // 获取主线程id
        // 任何一个程序肯定有一个主线程
        System.out.println("主线程：id " + Thread.currentThread().getId() + " name " + Thread.currentThread().getName());
        for (int i = 0; i < 2; i++){
            CreateTread04 t1 = new CreateTread04();
            Thread thread = new Thread(t1, "子线程" + i);
            thread.start();
        }

    }
}
