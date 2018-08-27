package com.shenrs;


import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * 产品
 */
class Product {
    private int id;

    public Product(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }
}

/**
 * 仓库
 */
class Storage {
    BlockingDeque<Product> queues = new LinkedBlockingDeque<Product>(10);

    /**
     * 生产
     * @param p
     */
    public void push(Product  p){
        queues.push(p);
    }

    /**
     * 消费
     * @return
     * @throws InterruptedException
     */
    public Product pop() throws InterruptedException {
        return queues.take();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {
    private String name = null;
    private Storage s = null;

    public Producer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }

    @Override
    public void run() {
        while (true){
            Product product = new Product((int) (Math.random()* 10000));
            System.out.println(name + "准备生产(" + product.toString() + ").");
            s.push(product);
            System.out.println(name + "已生产(" + product.toString() + ").");
            System.out.println("===============");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {
    private String name;
    private Storage s = null;


    public Consumer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }

    public void run() {
        try {
            while (true) {
                System.out.println(name + "准备消费产品.");
                Product product = s.pop();
                System.out.println(name + "已消费(" + product.toString() + ").");
                System.out.println("===============");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


/**
 * @author shenrs
 * @Description 生产者消费者
 * @create 2018-08-27 17:50
 **/
public class ProducerConsumer {
    
    public static void main(String[] args) {
        Storage s = new Storage();

        ExecutorService service = Executors.newCachedThreadPool();
        Producer p = new Producer("张三", s);
        Producer p2 = new Producer("李四", s);
        Consumer c = new Consumer("王五", s);
        Consumer c2 = new Consumer("老刘", s);
        Consumer c3 = new Consumer("老林", s);
        service.submit(p);
        //service.submit(p2);
        service.submit(c);
        service.submit(c2);
        service.submit(c3);

    }
}
