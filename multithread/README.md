# 多线程
### 一、多线程入门
#### 1、创建线程的五种方式
（1）使用继承Thread类方式[(thread_demo01)ThreadDemo01.java]<br/>
（2）使用实现Runnable接口方式[(thread_demo01)ThreadDemo02.java]<br/>
（3）使用匿名内部类方式[(thread_demo01)ThreadDemo03.java]<br/>
（4）callable<br/>
（5）使用线程池创建线程
#### 2、多线程基础知识
(1)线程与进程区别
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo01/image/%E7%BA%BF%E7%A8%8B%E4%B8%8E%E8%BF%9B%E7%A8%8B%E5%8C%BA%E5%88%AB.png "在这里输入图片标题")
<br/>(2)启动线程，不是调用run方法，而是调用start方法
<br/>(3)多线程几种状态
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo01/image/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%87%A0%E7%A7%8D%E7%8A%B6%E6%80%81.png "在这里输入图片标题")
<br/>(4)异步与同步区别
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo01/image/%E5%BC%82%E6%AD%A5%E4%B8%8E%E5%90%8C%E6%AD%A5%E5%8C%BA%E5%88%AB.png "在这里输入图片标题")
<br/>(5)守护线程与非守护线程
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo01/image/%E5%AE%88%E6%8A%A4%E7%BA%BF%E7%A8%8B%E4%B8%8E%E9%9D%9E%E5%AE%88%E6%8A%A4%E7%BA%BF%E7%A8%8B.png "在这里输入图片标题")
<br/>(6)多线程分批处理信息
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo01/image/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%88%86%E6%89%B9%E5%A4%84%E7%90%86%E4%BF%A1%E6%81%AF.png "在这里输入图片标题")
<br/>(7)join()方法作用：thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。

### 二、多线程之间实现同步
#### 1、线程安全问题
（1）线程安全问题
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo02/image/%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8%E9%97%AE%E9%A2%98.png "在这里输入图片标题")
<br/>（2）多线程安全问题产生
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo02/image/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8%E9%97%AE%E9%A2%98%E4%BA%A7%E7%94%9F.png "在这里输入图片标题")
<br/>（3）多线程冲突产生
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo02/image/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%86%B2%E7%AA%81%E4%BA%A7%E7%94%9F.png "在这里输入图片标题")
<br/>（4）线程安全问题解决办法
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo02/image/%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8%E9%97%AE%E9%A2%98%E8%A7%A3%E5%86%B3%E5%8A%9E%E6%B3%95.png "在这里输入图片标题")
#### 2、线程三大特性
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo02/image/%E7%BA%BF%E7%A8%8B%E4%B8%89%E5%A4%A7%E7%89%B9%E6%80%A7.png "在这里输入图片标题")
#### 3、线程同步
（1）多线程如何实现同步
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo02/image/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%A6%82%E4%BD%95%E5%AE%9E%E7%8E%B0%E5%90%8C%E6%AD%A5.png "在这里输入图片标题")
<br/>（2）线程之间同步
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo02/image/%E7%BA%BF%E7%A8%8B%E4%B9%8B%E9%97%B4%E5%90%8C%E6%AD%A5.png "在这里输入图片标题")
<br/>（3）同步函数与静态函数区别
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo02/image/%E5%90%8C%E6%AD%A5%E5%87%BD%E6%95%B0%E4%B8%8E%E9%9D%99%E6%80%81%E5%87%BD%E6%95%B0%E5%8C%BA%E5%88%AB.png "在这里输入图片标题")
#### 4、Java内存模型
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo02/image/java%E5%86%85%E5%AD%98%E6%A8%A1%E5%9E%8B.png "在这里输入图片标题")

### 三、多线程间通讯
（1）生产者与消费者
<br/>![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo03/image/%E7%94%9F%E4%BA%A7%E8%80%85%E4%B8%8E%E6%B6%88%E8%B4%B9%E8%80%85.png "在这里输入图片标题")
<br/>（2）生成者与消费者通讯
<br/>![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo03/image/%E7%94%9F%E6%88%90%E8%80%85%E4%B8%8E%E6%B6%88%E8%B4%B9%E8%80%85%E9%80%9A%E8%AE%AF.png "在这里输入图片标题")
<br/>（3）lock与syn区别
<br/>![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo03/image/lock%E4%B8%8Esyn%E5%8C%BA%E5%88%AB.png "在这里输入图片标题")
<br/>（4）wait与sleep区别
<br/>![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo03/image/wait%E4%B8%8Esleep%E5%8C%BA%E5%88%AB.png "在这里输入图片标题")
<br/>（5）ThreadLock原理剖析
<br/>![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo03/image/ThreadLock%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90.png "在这里输入图片标题")
<br/>（6）怎么停止线程
<br/>![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo03/image/%E6%80%8E%E4%B9%88%E5%81%9C%E6%AD%A2%E7%BA%BF%E7%A8%8B.png "在这里输入图片标题")
<br/>（7）总结
<br/>![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/multithread/thread_demo03/image/%E6%80%BB%E7%BB%93.png "在这里输入图片标题")

### 四、java并发包&并发队列
#### 1、同步容器类
1）Vector(低效集合)<br />
2）HasTable(低效集合)<br />
3）Collections.synchronized*(m) 将线程不安全额集合变为线程安全集合(低效集合)<br />
4）ConcurrentHashMap(相对高效集合)<br />
    使用分段锁，把一个整体分成16个段（最多16个段），每一个段都是一个HashTable。因此最多支持16个线程并发<br />
5）CountDownLatch利用它可以实现类似计数器的功能。<br />
    比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，<br />
6）CyclicBarrier <br />
    CyclicBarrier初始化时规定一个数目，然后计算调用了CyclicBarrier.await()进入等待的线程数。当线程数达到了这个数目时，所有进入等待状态的线程被唤醒并继续。 <br />
7）Semaphore是一种基于计数的信号量。 <br />
    它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做自己的申请后归还，超过阈值后，线程申请许可信号将ap会被阻塞。<br />
#### 2、并发容器
1）ConcurrentLinkedDeque（高性能队列）<br />
    适用于高并发场景，无界线程安全队列，头是最先加入的，尾是最近加入的，该队列不允许null元素。<br />
1）BlockingQueue（阻塞队列，性能低于ConcurrentLinkedDeque）<br />
    ①ArrayBlockingQueue：先进先出的存储方式，内部实现是数组，有边界，初始化时必须指定容量大小。<br />
    ②LinkedBlockingQueue：先进先出的存储方式，内部实现是链表，阻塞队列的大小是可选的，如果初始化时指定了容量大小，就是有界；如果不指定，就是无界。<br />
    ③PriorityBlockingQueue：无边界，该队列允许null元素。所有插入PriorityBlockingQueue的对象必须实现 java.lang.Comparable接口，队列优先级的排序规则<br />
    就是按照我们对这个接口的实现来定义的。另外，我们可以从PriorityBlockingQueue获得一个迭代器Iterator，但这个迭代器并不保证按照优先级顺序进行迭代。<br />
    ④SynchronousQueue：队列内部仅允许容纳一个元素。当一个线程插入一个元素后会被阻塞，除非这个元素被另一个线程消费。<br />
    
### 四、线程池原理剖析&锁的深度化