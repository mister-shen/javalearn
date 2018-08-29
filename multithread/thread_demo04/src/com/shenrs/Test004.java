package com.shenrs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author shenrs
 * @Description
 * @create 2018-08-28 17:36
 **/
public class Test004 {
    public static void main(String[] args) throws InterruptedException {
        // 无界
        ConcurrentLinkedDeque q = new ConcurrentLinkedDeque();
        q.offer("余胜军");
        q.offer("码云");
        q.offer("蚂蚁课堂");
        q.offer("张杰");
        q.offer("艾姐");
        //从头获取元素,删除该元素
        System.out.println(q.poll());
        //从头获取元素,不刪除该元素
        System.out.println(q.peek());
        //获取总长度
        System.out.println(q.size());

        // 阻塞队列
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        arrayBlockingQueue.add("张三");
        arrayBlockingQueue.add("李四");
        arrayBlockingQueue.add("王武");
        //可以阻塞的队列
        boolean offer = arrayBlockingQueue.offer("王麻衣子", 2, TimeUnit.SECONDS);
        System.out.println(arrayBlockingQueue.size()+"---"+offer);
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
    }
}
