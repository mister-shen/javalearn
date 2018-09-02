import jdk.nashorn.internal.ir.CatchNode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁实现缓存
 * 只能同时进入一把锁
 */
public class Cache {
    static private volatile Map<String, Object> map = new HashMap<String , Object>();
    // 读写锁
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    // 读锁
    static Lock r =  rwl.readLock();
    // 写锁
    static Lock w = rwl.writeLock();


    static public Object put(String key, Object value) {
        try {
            // 添加读锁
            w.lock();
            System.out.println("正在写入key：" + key + ", value：" + value + "开始....");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object oj = map.put(key, value);
            System.out.println("正在写入key：" + key + ", value：" + value + "结束....");
            System.out.println();
            return oj;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 释放读锁
            w.unlock();
        }
        return null;
    }

    static public Object get(String key) {
        try {
            r.lock();
            System.out.println("正在读取 key：" + key + "开始....");
            try {
                Thread.sleep(100);
                Object value = map.get(key);
                System.out.println("正在读取 key：" + key + ", value：" + value + "结束....");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("正在读取 key：" + key + "结束....");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            r.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    Cache.put(i + "", i + "");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    Cache.put(i + "", i + "");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    Cache.get(i + "");
                }
            }
        }).start();
    }
}
