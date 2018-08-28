package com.shenrs;

/**
 * 共享资源实体类
 */
class Res {
    public String userName;
    public String sex;
    // true 生产者生产，消费者等待；false 生产者等待，消费者消费
    public boolean flag = false;

    public Res() {
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
class Put extends Thread{
    Res res;

    public Put(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            synchronized (res){
                if(res.flag){
                    try {
                        res.wait();
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
                res.flag = false;
                res.notify();
            }
        }
    }
}

/**
 * 消费者
 */
class Out extends Thread{
    Res res;

    public Out(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true){
            synchronized (res) {
                if(!res.flag){
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("name" + res.userName + "sex: " + res.sex);
//                System.out.println(res.toString());
                res.flag = false;
                res.notify();
            }
        }
    }
}

/**
 * @author shenrs
 * @Description
 * @create 2018-08-27 16:58
 **/
public class OutInputThread {

    public static void main(String[] args) {
        Res res = new Res();
        Put put = new Put(res);
        Out out = new Out(res);
        put.start();
        out.start();
    }
}