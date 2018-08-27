package com.shenrs;

/**
 * 共享资源实体类
 */
class Res {
    public String userName;
    public String sex;

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
                if(count == 0){
                    res.userName = "张三";
                    res.sex = "男";
                }else{
                    res.userName = "麻子";
                    res.sex = "女";
                }
                count = (count + 1) % 2;
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
                System.out.println(res.toString());
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
