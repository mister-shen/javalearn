package com.shenrs;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Hose2 {
     void maifang();
}

class Shenrs2 implements Hose2 {

    @Override
    public void maifang() {
        System.out.println("我是shenrs，我可以买房啦！！");
    }
}

/**
 * jdk动态代理
 */
class JDKProxy implements InvocationHandler {
    // 代理对象
    private Object tarjet;

    public JDKProxy(Object tarjet) {
        this.tarjet = tarjet;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是中介，你可以开始买房了。。。");
        Object invoke = method.invoke(tarjet, args);
        System.out.println("我是中介，你买房结束了。。。");
        return invoke;
    }
}


/**
 * 动态代理模式
 */
public class Test006 {
    public static void main(String[] args) {
        Shenrs2 shenrs2 = new Shenrs2();
        JDKProxy jdkProxy = new JDKProxy(shenrs2);
        Hose2 hose = (Hose2) Proxy.newProxyInstance(shenrs2.getClass().getClassLoader(), shenrs2.getClass().getInterfaces(), jdkProxy);
        hose.maifang();
    }
}
