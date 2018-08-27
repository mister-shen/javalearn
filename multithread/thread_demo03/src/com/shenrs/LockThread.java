package com.shenrs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock锁实现线程同步
 */
class Res1 {
    public String userName;
    public String sex;
    // true 生产者生产，消费者等待；false 生产者等待，消费者消费
    public boolean flag = false;
    public Lock lock = new ReentrantLock();
    public Condition condition;

    public Res1() {
    }

    @Override
    public String toString() {
        return "Res{" +
                "userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

/**
 * 生产者
 */
class Put1 extends Thread{
    Res1 res;
    Condition condition;

    public Put1(Res1 res, Condition condition) {
        this.res = res;
        this.condition = condition;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            try{
                // 上锁
                res.lock.lock();
                if(res.flag){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(count == 0){
                    res.userName = "张三";
                    res.sex = "男";
                }else{
                    res.userName = "麻子";
                    res.sex = "女";
                }
                count = (count + 1) % 2;
                res.flag = true;
                // 唤醒锁
                condition.signal();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                //释放锁
                res.lock.unlock();
            }
        }
    }
}

/**
 * 消费者
 */
class Out1 extends Thread{
    Res1 res;
    Condition condition;

    public Out1(Res1 res, Condition condition) {
        this.res = res;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true){
            try {
                // 上锁
                res.lock.lock();
                if(!res.flag){
                    try {
                        // 添加同步
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("name" + res.userName + "sex: " + res.sex);
//                System.out.println(res.toString());
                res.flag = false;
                //释放锁
                condition.signal();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                res.lock.unlock();
            }
        }
    }
}

/**
 * @author shenrs
 * @Description
 * @create 2018-08-27 16:58
 **/
public class LockThread {

    public static void main(String[] args) {
        Res1 res = new Res1();
        Condition condition = res.lock.newCondition();
        Put1 put = new Put1(res, condition);
        Out1 out = new Out1(res, condition);
        put.start();
        out.start();
    }
}
