package com.shenrs;

class ResNumber {
    public int count = 0;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        protected Integer initialValue() {
            return 0;
        }
    };

    public String getNumber() {
        count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count + "";
    }

}

class ThreadLocalDemo01 extends Thread{
    private ResNumber resNumber;

    public ThreadLocalDemo01(ResNumber resNumber) {
        this.resNumber = resNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++){
            System.out.println(getName() + ",number：" + resNumber.getNumber());
        }
    }
}


/**
 * @author shenrs
 * @Description
 * @create 2018-08-28 15:33
 **/
public class ThreadLocalDemo {

    public static void main(String[] args) {
        ResNumber resNumber = new ResNumber();
        ThreadLocalDemo01 t1 = new ThreadLocalDemo01(resNumber);
        ThreadLocalDemo01 t2 = new ThreadLocalDemo01(resNumber);
        ThreadLocalDemo01 t3 = new ThreadLocalDemo01(resNumber);
        t1.start();
        t2.start();
        t3.start();
    }
}

