package com.shenrs.one_two;

/**
 * @author shenrs
 * @Description 死锁例子
 * 分析造成原因：t1先获取了A锁之后，休眠2秒；在此期间t2获取了B锁之后，想获取A锁，但t1又未释放A锁。
 *      t1休眠结束后，想获取B锁，而t2又未释放B锁。因此就造成了死锁。
 * 查看死锁的方法：cmd命令窗口输入Jconsole
 *      详细操作链接：https://www.cnblogs.com/flyingeagle/articles/6853167.html
 *
 * 避免死锁的几个方法：
 *      1）避免一个线程同时获取多个锁。
 *      2）避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源。
 *      3）尝试使用定时锁，使用lock.tryLock(timeout)来代替内部锁机制。
 *      4）对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况。
 * @create 2018-08-28 19:29
 **/
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println("1");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
