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
        （1）定义：
            XML（Extensible Markup Language）是一种常用的序列化和反序列化协议， 它历史悠久，从1998年的1.0版本被广泛使用至今。
        （2）优点
            人机可读性好
            可指定元素或特性的名称
        （3）缺点
            序列化数据只包含数据本身以及类的结构，不包括类型标识和程序集信息。
            类必须有一个将由 XmlSerializer 序列化的默认构造函数。
            只能序列化公共属性和字段
            不能序列化方法
            文件庞大，文件格式复杂，传输占带宽
        （4）使用场景
            当做配置文件存储数据
            实时数据转换
    JSON
        （1）定义：
            JSON(JavaScript Object Notation, JS 对象标记) 是一种轻量级的数据交换格式。
            它基于 ECMAScript (w3c制定的js规范)的一个子集， JSON采用与编程语言无关的文本格式，
            但是也使用了类C语言（包括C， C++， C#， Java， JavaScript， Perl， Python等）的习惯，
            简洁和清晰的层次结构使得 JSON 成为理想的数据交换语言。
        （2）优点
            前后兼容性高
            数据格式比较简单，易于读写
            序列化后数据较小，可扩展性好，兼容性好
            与XML相比，其协议比较简单，解析速度比较快
        （3）缺点
            数据的描述性比XML差
            不适合性能要求为ms级别的情况
            额外空间开销比较大
        （4）适用场景（可替代ＸＭＬ）
            跨防火墙访问
            可调式性要求高的情况
            基于Web browser的Ajax请求
            传输数据量相对小，实时性要求相对低（例如秒级别）的服务
    Fastjson
        （1）定义
            Fastjson是一个Java语言编写的高性能功能完善的JSON库。它采用一种“假定有序快速匹配”的算法，
            把JSON Parse的性能提升到极致。
        （2）优点
            接口简单易用
            目前java语言中最快的json库
        （3）缺点
            过于注重快，而偏离了“标准”及功能性
            代码质量不高，文档不全
        （4）适用场景
            协议交互
            Web输出
            Android客户端
    Thrift
        （1）定义：
            Thrift并不仅仅是序列化协议，而是一个RPC框架。它可以让你选择客户端与服务端之间传输通信协议的类别，
            即文本(text)和二进制(binary)传输协议, 为节约带宽，提供传输效率，一般情况下使用二进制类型的传输协议。
        （2）优点
            序列化后的体积小, 速度快
            支持多种语言和丰富的数据类型
            对于数据字段的增删具有较强的兼容性
            支持二进制压缩编码
        （3）缺点
            使用者较少
            跨防火墙访问时，不安全
            不具有可读性，调试代码时相对困难
            不能与其他传输层协议共同使用（例如HTTP）
            无法支持向持久层直接读写数据，即不适合做数据持久化序列化协议
        （4）适用场景
            分布式系统的RPC解决方案
    Avro
        （1）定义：
            Avro属于Apache Hadoop的一个子项目。 Avro提供两种序列化格式：JSON格式或者Binary格式。
            Binary格式在空间开销和解析性能方面可以和Protobuf媲美，Avro的产生解决了JSON的冗长和没有IDL的问题
        （2）优点
            支持丰富的数据类型
            简单的动态语言结合功能
            具有自我描述属性
            提高了数据解析速度
            快速可压缩的二进制数据形式
            可以实现远程过程调用RPC
            支持跨编程语言实现
        （3）缺点
            对于习惯于静态类型语言的用户不直观
        （4）适用场景
            在Hadoop中做Hive、Pig和MapReduce的持久化数据格式
    Protobuf
        （1）定义
            protocol buffers 由谷歌开源而来，在谷歌内部久经考验。它将数据结构以.proto文件进行描述，
            通过代码生成工具可以生成对应数据结构的POJO对象和Protobuf相关的方法和属性。
        （2）优点
            序列化后码流小，性能高
            结构化数据存储格式（XML JSON等）
            通过标识字段的顺序，可以实现协议的前向兼容
            结构化的文档更容易管理和维护
        （3）缺点
            需要依赖于工具生成代码
            支持的语言相对较少，官方只支持Java 、C++ 、Python
        （4）适用场景
            对性能要求高的RPC调用
            具有良好的跨防火墙的访问属性
            适合应用层对象的持久化
    其它
        protostuff 基于protobuf协议，但不需要配置proto文件，直接导包即
        Jboss marshaling 可以直接序列化java类， 无须实java.io.Serializable接口
        Message pack 一个高效的二进制序列化格式
        Hessian 采用二进制协议的轻量级remoting onhttp工具
        kryo 基于protobuf协议，只支持java语言,需要注册（Registration），然后序列化（Output），反序列化（Input）
        
    XML序列化（Xstream）无论在性能和简洁性上比较差。
    Thrift与Protobuf相比在时空开销方面都有一定的劣势。
    Protobuf和Avro在两方面表现都非常优越。
    
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

