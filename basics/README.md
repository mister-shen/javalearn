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
