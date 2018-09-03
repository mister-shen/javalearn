package com.shenrs;

/**
 * 保证jvm中只有一个实例
 */
class Singleton2 {
    // 饿汉式，加载class的时候创建
    private static Singleton2 singleton = new Singleton2();

    // 私有构造方法
    private Singleton2() {}

    public static Singleton2 getSingleton2(){
        return singleton;
    }
}

/**
 *  单例模式：懒汉模式
 */
public class Test003 {
    public static void main(String[] args) {
        Singleton2 singleton = Singleton2.getSingleton2();
        Singleton2 singleton2 = Singleton2.getSingleton2();
        System.out.println(singleton == singleton2);
    }
}
