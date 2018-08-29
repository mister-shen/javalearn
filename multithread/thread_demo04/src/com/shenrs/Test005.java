package com.shenrs;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者线程
 */
class ProducerThread extends Thread {
    private BlockingQueue queue;//阻塞队列
    private volatile boolean flag = true;
    private static AtomicInteger count = new AtomicInteger();// 原子对象

    public ProducerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("生产者线程启动.....");
        try {
            while (flag) {
                System.out.println("正在生产队列.....");
                String data = count.incrementAndGet() + "";

                //添加队列
                boolean offer = queue.offer(data);
                if(offer){ //如果添加成功
                    System.out.println("生产者添加队列"+data+"成功");
                }else{
                    System.out.println("生产者添加队列"+data+"失败");
                }

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者线程停止.....");
        }
    }

    /**
     * 停止线程方法
     */
    public void stopThread() {
        this.flag = false;
    }
}

/**
 * 消费者线程
 */
class ConsumerThread extends Thread {
    private BlockingQueue queue;
    private volatile boolean flag = true;

    public ConsumerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("消费者线程启动....");

        try {
            while (flag) {
                String data  = (String) queue.poll(2, TimeUnit.SECONDS);
                if(data != null){
                    System.out.println("消费者获取data："+data+"成功");
                }else{
                    System.out.println("消费者获取data："+data+"失败");
                    this.flag = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("消费者线程停止....");
        }
    }
}

/**
 * 使用阻塞队列BlockingQueue实现生产者消费者
 */
public class Test005 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>(10);
        ProducerThread producer = new ProducerThread(queue);
        ConsumerThread consumer = new ConsumerThread(queue);
        producer.start();
        consumer.start();

        // 执行 10s 停止线程
        Thread.sleep(20000);
        producer.stopThread();

    }
}
