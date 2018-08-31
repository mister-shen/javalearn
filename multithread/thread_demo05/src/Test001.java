import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author shenrs
 * @Description 线程池的四种创建方式
 * @create 2018-08-31 15:14
 **/
public class Test001 {

    public static void main(String[] args) {
        // 可缓存的线程池，重复利用， 无限大小线程池，jvm自动回收
        /**
         * 总结：线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
         */
        /*ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            int temp = i;
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ", i:" + temp);
                }
            });
        }*/

        /**
         * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
         *
         * 总结:因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
         * 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
         */
        /*ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 20; i++) {
            int temp = i;
            newFixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ", i:" + temp);
                }
            });
        }*/

        /**
         * 创建一个定长线程池，支持定时及周期性任务执行。
         * 3秒后执行
         */
       /* ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 20; i++) {
            int temp = i;
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ", i:" + temp);
                }
            }, 3, TimeUnit.SECONDS);
        }*/

        /**
         *
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 20; i++) {
            int temp = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ", i:" + temp);
                }
            });
        }

        singleThreadExecutor.shutdown();
    }
}
