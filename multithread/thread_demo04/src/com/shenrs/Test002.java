package com.shenrs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Writer extends Thread {
    CyclicBarrier cyclicBarrier;

    public Writer(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始写入数据...");
        try {
            // 模拟耗时时间
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "写入数据成功...");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "线程全部执行完毕...");
    }
}

/**
 * @author shenrs
 * @Description CyclicBarrier
 * @create 2018-08-28 16:58
 **/
public class Test002 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        for (int i = 0; i < 5; i++){
            new Writer(cyclicBarrier).start();
        }
    }
}
