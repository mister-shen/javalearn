# java部分基础知识
### 一、数据交换格式与SpringIOC底层实现
#### 1、常用的数据交换格式
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/json_demo/src/image/%E6%95%B0%E6%8D%AE%E4%BA%A4%E6%8D%A2%E6%A0%BC%E5%BC%8F.png "在这里输入图片标题")
#### 2、常用的JSON解析框架
    fastjson(阿里)、gson(谷歌)、jackson(SpringMVC自带)，其中fastjson效率最高
#### 3、常用的XML解析框架
    Dom4j(常用)、Sax、Pull，Dom4j不适合大文件的解析，因为是一下子把文件加载到内存中，
    Sax可以解析大文件。
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/json_demo/src/image/xml.png "在这里输入图片标题")

#### 4、java反射机制
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/json_demo/src/image/java%E7%9A%84%E5%8F%8D%E5%B0%84%E6%9C%BA%E5%88%B6.png "在这里输入图片标题")
#### 5、SpringIOC底层实现
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/json_demo/src/image/SpringIOC%E5%BA%95%E5%B1%82%E5%AE%9E%E7%8E%B0.png "在这里输入图片标题")
### 二、自定义注解与设计模式
#### 1、注解
    注解分类：内置注解(也成为元注解 jdk 自带注解)、自定义注解（Spring框架）
---

    内置注解： 比如
         （1） @SuppressWarnings   再程序前面加上可以在javac编译中去除警告--阶段是SOURCE
         （2） @Deprecated   带有标记的包，方法，字段说明其过时----阶段是SOURCE
         （3）@Overricle   打上这个标记说明该方法是将父类的方法重写--阶段是SOURCE
---

    自定义注解：
        （1）@Target：说明Annotation所修饰的对象范围
            1）.CONSTRUCTOR:用于描述构造器
            2）.FIELD:用于描述域
            3）.LOCAL_VARIABLE:用于描述局部变量
            4）.METHOD:用于描述方法
            5）.PACKAGE:用于描述包
            6）.PARAMETER:用于描述参数
            7）.TYPE:用于描述类、接口(包括注解类型) 或enum声明
        （2）@Retention：被描述的注解在什么范围内有效（RetentionPolicy.RUNTIME 运行时）
        （3）@Documented
        （4）@Inherited
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/02_annotation_demo/image/%E4%BB%80%E4%B9%88%E6%98%AF%E6%B3%A8%E8%A7%A3.png "什么是注解")
#### 2、实现ORM框架映射
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/02_annotation_demo/image/orm%E6%98%A0%E5%B0%84.png "orm映射")

#### 3、常用设计模式
    创建型模式，共五种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
    结构型模式，共七种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
    行为型模式，共十一种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、
        备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。
    其实还有两类：并发型模式和线程池模式。
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/02_annotation_demo/image/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F.png "设计模式")
    （1）单例模式
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/02_annotation_demo/image/%E4%BB%80%E4%B9%88%E6%98%AF%E5%8D%95%E4%BE%8B.png "什么是单例")
    （2）工厂模式
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/02_annotation_demo/image/%E5%B7%A5%E5%8E%82%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F.png "工厂设计模式")
    （3）代理模式
        静态代理(静态定义代理类)
        动态代理(动态生成代理类)
        Jdk自带动态代理
        Cglib 、javaassist（字节码操作库）
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/02_annotation_demo/image/%E4%B8%AD%E4%BB%8B.png "中介")
### 三、Socket网络通讯基础
#### 1、网络通讯
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/03_socket_demo/image/网络通讯.png "网络通讯")
#### 2、网络模型结构
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/03_socket_demo/image/网络模型结构.png "网络模型结构")
#### 3、tcp协议与udp协议
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/03_socket_demo/image/tcp协议与udp协议.png "tcp协议与udp协议")
#### 4、tcp协议三次握手
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/03_socket_demo/image/三次握手.png "三次握手")
### 四、NIO编程基础
#### 1、什么是NIO
    jdk1.4 及以上版本里提供的新api(New IO) ，为所有的原始类型(boolean类型除外)提供缓存支持的数据容器，使用它可以提供非阻塞式的高伸缩性网络。
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/04_nio_demo/image/NIO.png "NIO")
#### 2、IO与NIO区别
| IO | NIO | 
| - | - | 
| 面向流 | 面向缓冲|
| 阻塞IO | 非阻塞IO |
| 无 | 选择器 |
#### 3、Buffer的数据存取
    常用的Buffer子类：ByteBuffer（常用）、CharBuffer、ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer
---
    （1）buffer的概述
        1）容量（capacity）：表示Buffer最大数据容量，缓冲区容量不能为负，并且建立后不能修改。
        2）限制（limit）：第一个不应该读取或者写入的数据的索引，即位于limit后的数据不可以读写。缓冲区的限制不能为负，
           并且不能大于其容量（capacity）。
        3）位置（position）：下一个要读取或写入的数据的索引。缓冲区的位置不能为负，并且不能大于其限制（limit）。
        4）标记（mark）与重置（reset）：标记是一个索引，通过Buffer中的mark()方法指定Buffer中一个特定的position，
           之后可以通过调用reset()方法恢复到这个position。
---
    （2）直接缓冲区与非直接缓冲区别
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/04_nio_demo/image/非直接缓冲区.png "非直接缓冲区")
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/04_nio_demo/image/直接缓冲区.png "直接缓冲区")
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/04_nio_demo/image/缓冲区.png "缓冲区")
---
    （3）分散读取与聚集写入
        分散读取(scattering Reads)：将通道中的数据分散到多个缓冲区中
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/04_nio_demo/image/分散读取.png "分散读取")
---
        聚集写入(gathering Writes)：将多个缓冲区的数据聚集到通道中
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/04_nio_demo/image/聚集写入.png "聚集写入")
---
    （4）字符集 Charset
        编码：字符串->字节数组
        解码：字节数组 -> 字符串
### 五、NIO高级编程与Netty入门
#### 1、BIO与NIO
    IO(BIO)和NIO区别:其本质就是阻塞和非阻塞的区别 
    阻塞概念:应用程序在获取网络数据的时候,如果网络传输数据很慢，就会一直等待,直到传输完毕为止。
    非阻塞概念:应用程序直接可以获取已经准备就绪好的数据,无需等待。
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/05_entty_demo/image/NIO.png "NIO")
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/05_entty_demo/image/非阻塞与阻塞IO.png "非阻塞与阻塞IO")

#### 2、什么是阻塞
    阻塞概念:应用程序在获取网络数据的时候,如果网络传输很慢,那么程序就一直等着,直接到传输完毕。
#### 3、什么是非阻塞
    应用程序直接可以获取已经准备好的数据,无需等待.
    IO为同步阻塞形式,NIO为同步非阻塞形式。NIO没有实现异步,在JDK1.7之后，升级了NIO库包
    ,支持异步费阻塞通讯模型NIO2.0(AIO)
#### 4、NIO非阻塞代码
#### 5、选择KEY 
    1、SelectionKey.OP_CONNECT
    2、SelectionKey.OP_ACCEPT
    3、SelectionKey.OP_READ
    4、SelectionKey.OP_WRITE
---
#### 6、什么是Netty
     Netty 是一个基于 JAVA NIO 类库的异步通信框架，
     它的架构特点是：异步非阻塞、基于事件驱动、高性能、高可靠性和高可定制性。
![输入图片说明](https://github.com/mister-shen/javalearn/blob/master/basics/05_entty_demo/image/netty.png "netty")

#### 6、Netty应用场景
    1.分布式开源框架中dubbo、Zookeeper，RocketMQ底层rpc通讯使用就是netty。
    2.游戏开发中，底层使用netty通讯。
#### 7、为什么选择netty
    1)NIO的类库和API繁杂，使用麻烦，你需要熟练掌握Selector、ServerSocketChannel、SocketChannel、ByteBuffer等；
    2)需要具备其它的额外技能做铺垫，例如熟悉Java多线程编程，因为NIO编程涉及到Reactor模式，你必须对多线程和网路
      编程非常熟悉，才能编写出高质量的NIO程序；
    3)可靠性能力补齐，工作量和难度都非常大。例如客户端面临断连重连、网络闪断、半包读写、失败缓存、网络拥塞和异
      常码流的处理等等，NIO编程的特点是功能开发相对容易，但是可靠性能力补齐工作量和难度都非常大；
    4)JDK NIO的BUG，例如臭名昭著的epoll bug，它会导致Selector空轮询，最终导致CPU 100%。官方声称在JDK1.6版本的
      update18修复了该问题，但是直到JDK1.7版本该问题仍旧存在，只不过该bug发生概率降低了一些而已，它并没有被根本解决。
#### 8、netty服务端代码与客户端代码
