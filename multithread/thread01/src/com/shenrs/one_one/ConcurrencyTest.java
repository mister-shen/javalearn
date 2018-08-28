package com.shenrs.one_one;

/**
 * 上下文切换
 * @author shenrs
 * @Description 演示串行和并发执行并累计操作的时间
 * @create 2018-08-28 18:48
 **/
public class ConcurrencyTest {
    /**
     * 结论：测试结果表明，并发执行不一定比串行执行快，数据量越大并发的效率越能明显体现
     * 为什么并发执行的速度会比串行慢呢？这是因为线程有创建和上下文切换的开销
     *    Lmbench3工具可以测量上下文切换的时长
     *    vmstat工具可以测量上下文切换的次数
     * 减少上下文切换的方法有：无锁并发编程、CAS算法、使用最少线程和使用协程。
     *     无锁并发编程。多线程竞争锁时，会引起上下文切换，所以多线程处理数据时，可以用一些办法来避免使用锁，
     *  如将数据的ID按照Hash算法取模分段，不同的线程处理不同段的数据。
     *     CAS算法。Java的Atomic包使用CAS算法来更新数据，而不需要加锁。
     *     使用最少线程。避免创建不需要的线程，比如任务很少，但是创建了很多线程来处理，这样会造成大量线程都处于等待状态。
     *     协程：在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换。
     *
     */
//    private static final long count = 10000;//  concurrency：1ms, b=-10000  serial: 0ms, b=-10000
//    private static final long count = 100000;//  concurrency：6ms, b=-100000  serial: 5ms, b=-100000
//    private static final long count = 1000000;//  concurrency：7ms, b=-1000000  serial: 8ms, b=-1000000
//    private static final long count = 10000000;//  concurrency：11ms, b=-10000000  serial: 13ms, b=-10000000
    private static final long count = 100000000;//  concurrency：66ms, b=-100000000  serial: 104ms, b=-100000000

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    // 串行
    private static void serial() {
        long start = System.currentTimeMillis();

        int a = 0;
        for (long i = 0; i < count; i++){
            a += 5;
        }

        int b = 0;
        for (long i = 0; i < count; i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial: " + time + "ms, b=" + b);
    }

    //并发
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++){
                    a += 5;
                }
            }
        });
        thread.start();

        int b = 0;
        for (long i = 0; i < count; i++){
            b--;
        }
        thread.join(); // 之后的代码需要等待thread子线程执行完毕后，再执行
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency：" + time + "ms, b=" + b);
    }

}
