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
### 六、Netty高级
#### 1、Netty5的用法
#### 2、TCP粘包、拆包问题解决方案
      一个完整的业务可能会被TCP拆分成多个包进行发送，也有可能把多个小的包封装成一个大的数据包发送，这个就是TCP的拆包和封包问题。
    解决办法
        消息定长，报文大小固定长度，不够空格补全，发送和接收方遵循相同的约定，这样即使粘包了通过接收方编程实现
    获取定长报文也能区分。
---
        sc.pipeline().addLast(new FixedLengthFrameDecoder(10));
        包尾添加特殊分隔符，例如每条报文结束都添加回车换行符（例如FTP协议）或者指定特殊字符作为报文分隔符，接收
    方通过特殊分隔符切分报文区分。
---
        ByteBuf buf = Unpooled.copiedBuffer("_mayi".getBytes());
        sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
---
        将消息分为消息头和消息体，消息头中包含表示信息的总长度（或者消息体长度）的字段
#### 3、序列化协议与自定义序列化协议
        序列化（serialization）就是将对象序列化为二进制形式（字节数组），一般也将序列化称为编码（Encode），
    主要用于网络传输、数据持久化等；
--- 
        反序列化（deserialization）则是将从网络、磁盘等读取的字节数组还原成原始对象，以便后续业务的进行，一
    般也将反序列化称为解码（Decode），主要用于网络传输对象的解码，以便完成远程调用。
#### 4、几种流行的序列化协议比较
    XML
---
        （1）定义：
---
            XML（Extensible Markup Language）是一种常用的序列化和反序列化协议， 它历史悠久，从1998年的1.0版本被广泛使用至今。
---
        （2）优点
---
            人机可读性好
            可指定元素或特性的名称
---
        （3）缺点
---
            序列化数据只包含数据本身以及类的结构，不包括类型标识和程序集信息。
            类必须有一个将由 XmlSerializer 序列化的默认构造函数。
            只能序列化公共属性和字段
            不能序列化方法
            文件庞大，文件格式复杂，传输占带宽
---
       （4）使用场景
---
            当做配置文件存储数据
            实时数据转换
---
    JSON
---
        （1）定义：
---
            JSON(JavaScript Object Notation, JS 对象标记) 是一种轻量级的数据交换格式。
            它基于 ECMAScript (w3c制定的js规范)的一个子集， JSON采用与编程语言无关的文本格式，
            但是也使用了类C语言（包括C， C++， C#， Java， JavaScript， Perl， Python等）的习惯，
            简洁和清晰的层次结构使得 JSON 成为理想的数据交换语言。
---
        （2）优点
---
            前后兼容性高
            数据格式比较简单，易于读写
            序列化后数据较小，可扩展性好，兼容性好
            与XML相比，其协议比较简单，解析速度比较快
---
        （3）缺点
---
            数据的描述性比XML差
            不适合性能要求为ms级别的情况
            额外空间开销比较大
---
        （4）适用场景（可替代ＸＭＬ）
---
            跨防火墙访问
            可调式性要求高的情况
            基于Web browser的Ajax请求
            传输数据量相对小，实时性要求相对低（例如秒级别）的服务
---
    Fastjson
---
        （1）定义
---
            Fastjson是一个Java语言编写的高性能功能完善的JSON库。它采用一种“假定有序快速匹配”的算法，
            把JSON Parse的性能提升到极致。
---
        （2）优点
---
            接口简单易用
            目前java语言中最快的json库
---
        （3）缺点
---
            过于注重快，而偏离了“标准”及功能性
            代码质量不高，文档不全
---
        （4）适用场景
---
            协议交互
            Web输出
            Android客户端
---
    Thrift
---
        （1）定义：
---
            Thrift并不仅仅是序列化协议，而是一个RPC框架。它可以让你选择客户端与服务端之间传输通信协议的类别，
            即文本(text)和二进制(binary)传输协议, 为节约带宽，提供传输效率，一般情况下使用二进制类型的传输协议。
---
        （2）优点
---
            序列化后的体积小, 速度快
            支持多种语言和丰富的数据类型
            对于数据字段的增删具有较强的兼容性
            支持二进制压缩编码
---
        （3）缺点
---
            使用者较少
            跨防火墙访问时，不安全
            不具有可读性，调试代码时相对困难
            不能与其他传输层协议共同使用（例如HTTP）
            无法支持向持久层直接读写数据，即不适合做数据持久化序列化协议
---
        （4）适用场景
---
            分布式系统的RPC解决方案
---
    Avro
---
        （1）定义：
---
            Avro属于Apache Hadoop的一个子项目。 Avro提供两种序列化格式：JSON格式或者Binary格式。
            Binary格式在空间开销和解析性能方面可以和Protobuf媲美，Avro的产生解决了JSON的冗长和没有IDL的问题
---
        （2）优点
---
            支持丰富的数据类型
            简单的动态语言结合功能
            具有自我描述属性
            提高了数据解析速度
            快速可压缩的二进制数据形式
            可以实现远程过程调用RPC
            支持跨编程语言实现
---
        （3）缺点
---
            对于习惯于静态类型语言的用户不直观
---
        （4）适用场景
---
            在Hadoop中做Hive、Pig和MapReduce的持久化数据格式
---
    Protobuf
---
        （1）定义
---
            protocol buffers 由谷歌开源而来，在谷歌内部久经考验。它将数据结构以.proto文件进行描述，
            通过代码生成工具可以生成对应数据结构的POJO对象和Protobuf相关的方法和属性。
---
        （2）优点
---
            序列化后码流小，性能高
            结构化数据存储格式（XML JSON等）
            通过标识字段的顺序，可以实现协议的前向兼容
            结构化的文档更容易管理和维护
---
        （3）缺点
---
            需要依赖于工具生成代码
            支持的语言相对较少，官方只支持Java 、C++ 、Python
---
        （4）适用场景
---
            对性能要求高的RPC调用
            具有良好的跨防火墙的访问属性
            适合应用层对象的持久化
---
    其它
---
        protostuff 基于protobuf协议，但不需要配置proto文件，直接导包即
        Jboss marshaling 可以直接序列化java类， 无须实java.io.Serializable接口
        Message pack 一个高效的二进制序列化格式
        Hessian 采用二进制协议的轻量级remoting onhttp工具
        kryo 基于protobuf协议，只支持java语言,需要注册（Registration），然后序列化（Output），反序列化（Input）     
---
    XML序列化（Xstream）无论在性能和简洁性上比较差。
    Thrift与Protobuf相比在时空开销方面都有一定的劣势。
    Protobuf和Avro在两方面表现都非常优越。 
---
    选型建议
    不同的场景适用的序列化协议：
    对于公司间的系统调用，如果性能要求在100ms以上的服务，基于XML的SOAP协议是一个值得考虑的方案。
    基于Web browser的Ajax，以及Mobile app与服务端之间的通讯，JSON协议是首选。对于性能要求不太高，
    或者以动态类型语言为主，或者传输数据载荷很小的的运用场景，JSON也是非常不错的选择。
    对于调试环境比较恶劣的场景，采用JSON或XML能够极大的提高调试效率，降低系统开发成本。
    当对性能和简洁性有极高要求的场景，Protobuf，Thrift，Avro之间具有一定的竞争关系。
    对于T级别的数据的持久化应用场景，Protobuf和Avro是首要选择。如果持久化后的数据存储在Hadoop子项目里，Avro会是更好的选择。
    由于Avro的设计理念偏向于动态类型语言，对于动态语言为主的应用场景，Avro是更好的选择。
    对于持久层非Hadoop项目，以静态类型语言为主的应用场景，Protobuf会更符合静态类型语言工程师的开发习惯。
    如果需要提供一个完整的RPC解决方案，Thrift是一个好的选择。
    如果序列化之后需要支持不同的传输层协议，或者需要跨防火墙访问的高性能场景，Protobuf可以优先考虑。

### 七、JVM参数调优配置
#### 1、java内存结构
![jvm内存结构](https://github.com/mister-shen/javalearn/blob/master/basics/07_jvm_demo/image/jvm内存结构.png "jvm内存结构")

---    
    (1)方法区（永久）：static关键字修饰，常量信息当class文件被加载的时候，就会被初始化，所有线程会被共享。
       注意：线程安全问题。
       调优问题：web并发不能定义常量太多。
---
    (2)堆：创建对象，new 创建 数组存放在堆内存---调优策略堆。堆所有线程会被共享。
![新生代与老年代](https://github.com/mister-shen/javalearn/blob/master/basics/07_jvm_demo/image/新生代与老年代.png "新生代与老年代")

---
    (3)栈：定义基本局部变量，栈代码运行完毕，自动释放内存。每个线程私有，互不共享，栈不会产生线程安全问题。
---
    (4)本地方法栈：主要调用C语言，JNI.
---
    (5)PC寄存器：每个线程启动的时候，都会创建一个PC（Program Counter，程序计数器）寄存器。
       PC寄存器里保存有当前正在执行的JVM指令的地址。 每一个线程都有它自己的PC寄存器，也是
       该线程启动时创建的。保存下一条将要执行的指令地址的寄存器是 ：PC寄存器。PC寄存器的内
       容总是指向下一条将被执行指令的地址，这里的地址可以是一个本地指针，也可以是在方法区
       中相对应于该方法起始指令的偏移量。
---
    (6)执行引擎：执行字节码文件。
[Java栈，PC寄存器，本地方法栈，堆，方法区和运行常量池](https://my.oschina.net/wangsifangyuan/blog/711329 )
#### 2、虚拟机参数配置
    堆的参数配置
        -XX:+PrintGC      每次触发GC的时候打印相关日志
        -XX:+UseSerialGC      串行回收
        -XX:+PrintGCDetails  更详细的GC日志
        -Xms               堆初始值
        -Xmx               堆最大可用值
        -Xmn               新生代堆最大可用值
        -XX:SurvivorRatio     用来设置新生代中eden空间和from/to空间的比例.
        含以-XX:SurvivorRatio=eden/from=den/to
        总结:在实际工作中，我们可以直接将初始的堆大小与最大堆大小相等，
        这样的好处是可以减少程序运行时垃圾回收次数，从而提高效率。
        -XX:SurvivorRatio     用来设置新生代中eden空间和from/to空间的比例.
--- 
    设置新生代与老年代优化参数
        -Xmn    新生代大小，一般设为整个堆的1/3到1/4左右
        -XX:SurvivorRatio    设置新生代中eden区和from/to空间的比例关系n/1，eden容量大小与s0、s1容量大小为2:1
        -XX:NewRatio=2    设置新生与老年代代参数，参数为2代表 新生代 ：老年代 = 1 ：2
---
    内存溢出解决办法
        设置堆内存大小
            错误原因: java.lang.OutOfMemoryError: Java heap space 堆内存溢出
            重现方法：设置堆内存大小 -Xms1m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
            解决办法:
        设置栈内存大小
            错误原因: java.lang.StackOverflowError  栈内存溢出
            栈溢出 产生于递归调用，循环遍历是不会的，但是循环方法里面产生递归调用， 也会发生栈溢出。 
            解决办法:设置线程最大调用深度，如：-Xss5m 设置最大调用深度
#### 3、JVM参数调优总结
    在JVM启动参数中，可以设置跟内存、垃圾回收相关的一些参数设置，默认情况不做任何设置JVM会工作的很好，
    但对一些配置很好的Server和具体的应用必须仔细调优才能获得最佳性能。通过设置我们希望达到一些目标：
        •	GC的时间足够的小
        •	GC的次数足够的少
        •	发生Full GC(新生代和老年代)的周期足够的长
---
    前两个目前是相悖的，要想GC时间小必须要一个更小的堆，要保证GC次数足够少，必须保证一个更大的堆，我们只能取其平衡。
        （1）针对JVM堆的设置，一般可以通过-Xms -Xmx限定其最小、最大值，为了防止垃圾收集器在最小、最大之间收缩堆而产
             生额外的时间，我们通常把最大、最小设置为相同的值
---
        （2）年轻代和年老代将根据默认的比例（1：2）分配堆内存，可以通过调整二者之间的比率NewRadio来调整二者之间的大
            小，也可以针对回收代，比如年轻代，通过 -XX:newSize -XX:MaxNewSize来设置其绝对大小。同样，为了防止年轻代
            的堆收缩，我们通常会把-XX:newSize -XX:MaxNewSize设置为同样大小
---
        （3）年轻代和年老代设置多大才算合理？这个我问题毫无疑问是没有答案的，否则也就不会有调优。我们观察一下二者大
            小变化有哪些影响
            •	更大的年轻代必然导致更小的年老代，大的年轻代会延长普通GC的周期，但会增加每次GC的时间；小的年老代会导致更频繁的Full GC
            •	更小的年轻代必然导致更大年老代，小的年轻代会导致普通GC很频繁，但每次的GC时间会更短；大的年老代会减少Full GC的频率
            •	如何选择应该依赖应用程序对象生命周期的分布情况：如果应用存在大量的临时对象，应该选择更大的年轻代；如果存在相对较多的持久对象，年老代应该适当增大。但很多应用都没有这样明显的特性，在抉择时应该根据以下两点：（A）本着Full GC尽量少的原则，让年老代尽量缓存常用对象，JVM的默认比例1：2也是这个道理 （B）通过观察应用一段时间，看其他在峰值时年老代会占多少内存，在不影响Full GC的前提下，根据实际情况加大年轻代，比如可以把比例控制在1：1。但应该给年老代至少预留1/3的增长空间

### 八、垃圾回收机制算法分析
![什么是垃圾回收](https://github.com/mister-shen/javalearn/blob/master/basics/08_垃圾回收机制算法分析/图片/什么是垃圾回收.png "什么是垃圾回收")
#### 1、finalize作用
	finalize()方法是在垃圾收集器删除对象之前对这个对象调用的。它是在Object类中定义的，因此所有的类都继承了它。
#### 2、内存泄露
	定义：对象已经没有被应用程序使用，但是垃圾回收器没办法移除它们，因为还在被引用着。
---
    如何防止内存泄露：
    	（1）HashMap、ArrayList等集合对象，使用static修饰。
        （2）当一个监听器在使用的时候被注册，但不再使用之后却未被反注册。
        （3）成员变量引用其他对象，初始化的时候需要置空。
#### 3、垃圾回收机制算法
![垃圾收集算法](https://github.com/mister-shen/javalearn/blob/master/basics/08_垃圾回收机制算法分析/图片/垃圾收集算法.png "垃圾收集算法")
	(1)引用计数法

    	概述：给对象中添加一个引用计数器，每当有一个地方引用它时，计数器值就加1；
        	当引用失效时，计数器值就减1；任何时刻计数器都为0的对象就是不再被使用
            的，垃圾收集器将回收该对象使用的内存。
---
		优点:引用计数收集器可以很快的执行，交织在程序运行中。对程序需要不被长时间打断的实时环境比较有利。
---
		缺点：无法检测出循环引用。如父对象有一个对子对象的引用，子对象反过来引用父对象。这样，
        	他们的引用计数永远不可能为0.而且每次加减非常浪费内存。
---
		用处：新生代的eden区垃圾回收
---
	(2)复制算法
        概述：S0和s1将可用内存按容量分成大小相等的两块，每次只使用其中一块，当这块内存使用
        	完了，就将还存活的对象复制到另一块内存上去，然后把使用过的内存空间一次清理掉。
---
		优点:这样使得每次都是对其中一块内存进行回收，内存分配时不用考虑内存碎片等复杂情况，
        	只需要移动堆顶指针，按顺序分配内存即可，实现简单，运行高效。
---
		缺点：可使用的内存降为原来一半。
---
		用处：新生代的 s0 s1 垃圾回收
![复制算法](https://github.com/mister-shen/javalearn/blob/master/basics/08_垃圾回收机制算法分析/图片/复制算法.png "复制算法")

---
	(3)标记清除算法
        概述：根据特定的算法（如：引用计数算法，可达性分析算法等）标出内存中哪些对象可以回收，哪些对象还要继续用。
---
		缺点：
        	1.标记与清除没有连续性效率低;
        	2.清除之后内存会产生大量碎片；
---
	(4)标记-压缩算法
        概述：标记压缩法在标记清除基础之上做了优化，把存活的对象压缩到内存一端,而后进行垃圾清理。
![标记压缩与标记整理](https://github.com/mister-shen/javalearn/blob/master/basics/08_垃圾回收机制算法分析/图片/标记压缩与标记整理.png "标记压缩与标记整理")
---
		用处：老年代垃圾回收
---
	(5)分代收集算法
        概述：对于新生代和老年代来说,新生代回收频率很高,但是每次回收耗时很短,而老年代回收频率
        	较低,但是耗时会相对较长,所以应该尽量减少老年代的GC.
---
#### 4、垃圾回收时的停顿现象
	    停顿的目的是为了终止所有的应用线程，只有这样的系统才不会有新垃圾的产生。同时停顿保证了系统
    状态在某一个瞬间的一致性，也有利于更好的标记垃圾对象。因此在垃圾回收时，都会产生应用程序的停顿。
#### 5、垃圾收集器
	（1）什么是Java垃圾回收器
		    Java垃圾回收器是Java虚拟机(JVM)的三个重要模块(另外两个是解释器和多线程机制)之一，
        为应用程序提供内存的自动分配(Memory Allocation)、自动回收(Garbage Collect)
        功能，这两个操作都发生在Java堆上(一段内存快)。
---
	（2）串行回收器(Serial Collector)
    	单线程执行回收操作，回收期间暂停所有应用线程的执行，client模式下的默认回收器，通过
        -XX:+UseSerialGC命令行可选项强制指定。参数可以设置使用新生代串行和老年代串行回收器
---
	（3）并行回收器
        ①并行回收器(ParNew回收器)
            多个线程同时进行垃圾回收，ParNew回收器是一个工作在新生代的垃圾收集器，
            XX:+UseParNewGC 新生代ParNew回收器
---
        ②并行回收集器(ParallelGC)
            老年代ParallelOldGC回收器也是一种多线程的回收器，和新生代的
            ParallelGC回收器一样，也是一种关往吞吐量的回收器，他使用了标
            记压缩算法进行实现。
            -XX:+UseParallelOldGC 进行设置
            -XX:+ParallelCThread也可以设置垃圾收集时的线程教量。
---
	（4）并CMS(并发GC)收集器
    	   CMS(Concurrent Mark Sweep)收集器是一种以获取最短回收停顿时间为目标的收集器。
        CMS收集器是基于“标记-清除”算法实现的，整个收集过程大致分为4个步骤：
            ①.初始标记(CMS initial mark)
            ②.并发标记(CMS concurrenr mark)
            ③.重新标记(CMS remark)
            ④.并发清除(CMS concurrent sweep)
		优点：并发收集、低停顿
        缺点：
        	1.在并发阶段，虽然不会导致用户线程停顿，但是会占用CPU资源而导致引用程序变慢，总吞吐量下降。
        	  CMS默认启动的回收线程数是：(CPU数量+3) / 4。
        	2.CMS收集器无法处理浮动垃圾，可能出现“Concurrent Mode Failure“，失败后而导致另一次Full  GC的产生。
        	3.CMS是基于“标记-清除”算法实现的收集器，使用“标记-清除”算法收集后，会产生大量碎片。
        	  为了解决这个问题，CMS收集器提供了一个-XX:UseCMSCompactAtFullCollection开关参数，
              用于在Full  GC之后增加一个碎片整理过程，还可通过-XX:CMSFullGCBeforeCompaction参数
              设置执行多少次不压缩的Full  GC之后，跟着来一次碎片整理过程。
---
	（5）G1回收器
    	G1回收器(Garbage-First)实在]dk1.7中提出的垃圾回收器，从长期目标来看是为了取
		代CMS回收器，G1回收器拥有独特的垃圾回收策略，G1属于分代垃圾回收器，区分
		新生代和老年代，依然有eden和from/to区,它并不要求整个eden区或者新生代、老
		年代的空间都连续，它使用了分区算法。
        并行性: G1回收期间可多线程同时工作。
        井发性G1拥有与应用程序交替执行能力，部分工作可与应用程序同时执行，在整个
        GC期间不会完全阻塞应用程序。
        分代GC:G1依然是一个分代的收集器，但是它是非两新生代和老年代一杯政的杂尊。
        空间基理，G1在国收过程中，不会微CMS那样在若千tacAy 要进行碎片整理。
        G1
        来用了有效复制对象的方式，减少空间碎片。
        利得程，用于分区的原因，G可以贝造取都分区城进行回收，帽小了国收的格想，
        提升了性能。
        使用.XXX:+UseG1GC 应用G1收集器，
        Mills指定最大停顿时间
        使用-XX:MaxGCPausel
        设置并行回收的线程数量
        使用-XX:ParallelGCThreads
#### 6、Tomcat配置调优测试
	Jmeter压力测试工具
![压力测试工具](https://github.com/mister-shen/javalearn/blob/master/basics/08_垃圾回收机制算法分析/图片/压力测试工具.png "压力测试工具")


### 九、Maven项目管理工具
![mvn常用命令](https://github.com/mister-shen/javalearn/blob/master/basics/09_Maven项目管理工具/图片/maven常用命令.png "mvn常用命令")

### 十、DNS解析过程&&外网映射工具&Cookie与Session实现原理Servlet源码分析
#### 1、DNS解析过程
![dns解析过程](https://github.com/mister-shen/javalearn/blob/master/basics/10_servletweb_demo/image/dns解析过程.png "dns解析过程")
---
#### 2、内网与外网区别
![内网与外网区别](https://github.com/mister-shen/javalearn/blob/master/basics/10_servletweb_demo/image/内网与外网区别.png "内网与外网区别")
---
#### 3、Servlet核心内容
![servlet声明周期](https://github.com/mister-shen/javalearn/blob/master/basics/10_servletweb_demo/image/servlet声明周期.png "servlet声明周期")
---
![servlet执行流程](https://github.com/mister-shen/javalearn/blob/master/basics/10_servletweb_demo/image/servlet执行流程.png "servlet执行流程")
---
![servlet线程是否安全](https://github.com/mister-shen/javalearn/blob/master/basics/10_servletweb_demo/image/servlet线程是否安全.png "servlet线程是否安全")
---
#### 4、cookie实现原理
    1）Cookie只能存字符串类型。不能保存对象
    2）只能存非中文。
    3）1个Cookie的容量不超过4KB。
    如果存中文，需要转码解码；浏览器一般只允许存放300个Cookie，每个站点最多存放20个Cookie，每个Cookie的大小限制为4KB。
![cookie实现原理](https://github.com/mister-shen/javalearn/blob/master/basics/10_servletweb_demo/image/cookie实现原理.png "cookie实现原理")
---
#### 5、session实现原理
    特点：会话数据保存在服务器端。（内存中）
![session实现原理](https://github.com/mister-shen/javalearn/blob/master/basics/10_servletweb_demo/image/session实现原理.png "session实现原理")
---

### 十一、深入理解Http协议 
![客户端与服务器](https://github.com/mister-shen/javalearn/blob/master/basics/11_servletweb_demo/image/客户端与服务器.png "客户端与服务器")
![http协议组成部分](https://github.com/mister-shen/javalearn/blob/master/basics/11_servletweb_demo/image/http协议组成部分.png "http协议组成部分")

#### 1、Http请求
![http请求](https://github.com/mister-shen/javalearn/blob/master/basics/11_servletweb_demo/image/http请求.png "http请求")
![http请求分析](https://github.com/mister-shen/javalearn/blob/master/basics/11_servletweb_demo/image/http请求分析.png "http请求分析")
#### 2、重定向实现原理
![重定向实现原理](https://github.com/mister-shen/javalearn/blob/master/basics/11_servletweb_demo/image/重定向实现原理.png "重定向实现原理")
#### 3、防盗链
![防盗链](https://github.com/mister-shen/javalearn/blob/master/basics/11_servletweb_demo/image/防盗链.png "防盗链")

### 十二、表单重复提交&防止模拟请求&跨域解决方案
#### 1、http与https的区别
![http协议抓包](https://github.com/mister-shen/javalearn/blob/master/basics/12_servletweb_demo/image/http协议抓包.png "http协议抓包")
#### 2、java使用HttpClient发送http请求（get与post）
#### 3、长连接与短连接
![长连接与短连接](https://github.com/mister-shen/javalearn/blob/master/basics/12_servletweb_demo/image/长连接与短连接.png "长连接与短连接")
---
#### 4、跨域解决方案
![使用httpclient转发](https://github.com/mister-shen/javalearn/blob/master/basics/12_servletweb_demo/image/使用httpclient转发.png "使用httpclient转发")
1.  使用后台response添加header
    ```response.setHeader("Access-Control-Allow-Origin", "*");``` 支持所有网站
2. 使用JSONP
    前端代码：
    ```
    $.ajax({
            type : "POST",
            async : false,
            url : "http://a.a.com/a/FromUserServlet?userName=张三",
            //数据类型为jsonp  
            dataType : "jsonp",
            //服务端用于接收callback调用的function名的参数
            jsonp : "jsonpCallback",  
            success : function(data) {
                alert(data.result);
            },
            error : function() {
                alert('fail');
            }
    });
    ```
    后端代码：
    ```
        // 客户端请求参数
        String jsonpCallback = req.getParameter("jsonpCallback");
        // 返回jsonp格式数据
        out.println(jsonpCallback + "(" + resultJSON.toJSONString() + ")");
    ```
    jsop原理：
        JSONP模式来请求数据的时候服务端返回的是一段可执行的 JavaScript代码。
        原理是动态加载 script的src，只能把参数通过 url的方式传递,所以jsonp的 type类型只能是 get。
        客户端发送一个请求，规定一个可执行的函数名，服务器端接受了这个 backfunc函数名，然后把数据通过实参的形式发送出去


3. 后台Http请求转发
      使用HttpClinet转发进行转发
      ``` 
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
            // 创建默认连接
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 创建get请求
            HttpGet httpGet = new HttpGet("http://a.a.com/a/FromServlet?userName=" + req.getParameter("userName"));
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            // 获取状态
            System.out.println("http请求结果:" + code);
            if (code == 200) {
                String result = EntityUtils.toString(response.getEntity());
                System.out.println(result);
                resp.getWriter().print(result);
                response.close();
                httpClient.close();
                // 将string转换html框架
    
            }
        
      }
      ```
4. 使用接口网关
    * 使用nginx转发
    * 使用SpringCloud网关

#### 5、表单重复提交解决方案
    转发至jsp代码
```
@WebServlet("/ForwardServlet")
public class ForwardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("sesionToken", TokenUtils.getToken());
        req.getRequestDispatcher("form.jsp").forward(req, resp);
    }
}
```
    转发jsp页面
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Form表单</title>

</head>

<body>
    <form action="${pageContext.request.contextPath}/DoFormServlet"
        method="post" onsubmit="return dosubmit()">
        <input type="hidden" name="token" value="${sesionToken}"> 用户名：<input type="text"
            name="userName"> <input type="submit" value="提交" id="submit">
    </form>
</body>
</html>
```
    保存数据后台代码
```
@WebServlet("/DoFormServlet")
public class DoFormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        boolean flag = isFlag(req);
        if (!flag) {
            resp.getWriter().write("已经提交...");
            System.out.println("数据已经提交了..");
            return;
        }
        String userName = req.getParameter("userName");
        try {
            Thread.sleep(300);
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("往数据库插入数据...." + userName);
        resp.getWriter().write("success");
    }

    public boolean isFlag(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sesionToken = (String) session.getAttribute("sesionToken");
        String token = request.getParameter("token");
        if (!(token.equals(sesionToken))) {
            return false;
        }
        session.removeAttribute("sesionToken");
        return true;
    }
}
```
    更好的实现方式：https://blog.csdn.net/zhang289202241/article/details/70801640

#### 5、如何防止接口被模拟 使用验证码

#### 6、使用Fileter防止XSS攻击
    在web.xml加一个filter

```
<filter>  
   <filter-name>XssEscape</filter-name>  
   <filter-class>cn.pconline.morden.filter.XssFilter</filter-class>  
</filter>  
<filter-mapping>  
   <filter-name>XssEscape</filter-name>  
   <url-pattern>/*</url-pattern>  
   <dispatcher>REQUEST</dispatcher>  
</filter-mapping> 
```
    XssFilter 的实现方式是实现servlet的Filter接口

```
package cn.pconline.morden.filter;  
  
import java.io.IOException;  
  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
  
public class XssFilter implements Filter {  
      
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);  
    }  
  
    @Override  
    public void destroy() {  
    }  
}  
```
     关键是XssHttpServletRequestWrapper的实现方式，
     继承servlet的HttpServletRequestWrapper，并重写相应的几个有可能带xss攻击的方法，如：

```
package cn.pconline.morden.filter;  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletRequestWrapper;  
  
import org.apache.commons.lang3.StringEscapeUtils;  
  
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {  
  
    public XssHttpServletRequestWrapper(HttpServletRequest request) {  
        super(request);  
    }  
  
    @Override  
    public String getHeader(String name) {  
        return StringEscapeUtils.escapeHtml4(super.getHeader(name));  
    }  
  
    @Override  
    public String getQueryString() {  
        return StringEscapeUtils.escapeHtml4(super.getQueryString());  
    }  
  
    @Override  
    public String getParameter(String name) {  
        return StringEscapeUtils.escapeHtml4(super.getParameter(name));  
    }  
  
    @Override  
    public String[] getParameterValues(String name) {  
        String[] values = super.getParameterValues(name);  
        if(values != null) {  
            int length = values.length;  
            String[] escapseValues = new String[length];  
            for(int i = 0; i < length; i++){  
                escapseValues[i] = StringEscapeUtils.escapeHtml4(values[i]);  
            }  
            return escapseValues;  
        }  
        return super.getParameterValues(name);  
    }  
      
}  
```


    