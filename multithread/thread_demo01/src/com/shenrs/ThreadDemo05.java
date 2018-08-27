package com.shenrs;

/**
 * 守护线程与非守护线程
 */
public class ThreadDemo05 {

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
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.setDaemon(true); // 该线程为守护线程，和主线程一起销毁
        t1.start();

        for (int i = 0; i < 5; i++){
            System.out.println("主线程， i: " + i);
        }
        System.out.println("主线程执行完毕。。。。。");
    }
}
