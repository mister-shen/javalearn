# 多线程
###一、多线程入门
####1、创建线程的五种方式
（1）使用继承Thread类方式[(thread_demo01)ThreadDemo01.java]<br/>
（2）使用实现Runnable接口方式[(thread_demo01)ThreadDemo02.java]<br/>
（3）使用匿名内部类方式[(thread_demo01)ThreadDemo03.java]<br/>
（4）callable<br/>
（5）使用线程池创建线程
####2、多线程基础知识
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
![输入图片说明](http://git.oschina.net/uploads/images/2017/0105/082137_85109d07_437188.jpeg "在这里输入图片标题")
<br/>(7)join()方法作用：thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。