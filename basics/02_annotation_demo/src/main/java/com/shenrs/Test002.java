package com.shenrs;

/**
 * 保证jvm中只有一个实例
 */
class Singleton {
    // 懒汉式，当需要的时候才会去创建对象
    private static Singleton singleton;

    // 私有构造方法
    private Singleton() {}

    public static Singleton getSingleton(){
        if(singleton == null){
            synchronized (Singleton.class){ //静态方法锁字节码文件
                if(singleton == null){ // 第二层上锁，双重 检验锁
                    singleton = new Singleton();
                }
            }
        }

        //  当对象不为空就不用考虑线程安全性问题了
        return singleton;
    }

}

/**
 *  单例模式：懒汉模式
 */
public class Test002 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        Singleton singleton2 = Singleton.getSingleton();
        System.out.println(singleton == singleton2);
    }
}
