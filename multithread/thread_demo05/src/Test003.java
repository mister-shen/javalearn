/**
 * 重入锁 synchronized
 */
public class Test003 implements Runnable{
    public synchronized void get() {
        System.out.println("name：" + Thread.currentThread().getName() + "get()");
        set();
    }

    public synchronized void set() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name：" + Thread.currentThread().getName() + "set()");
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Test003 t = new Test003();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }
}
