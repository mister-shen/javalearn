package com.shenrs;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.invoke.MethodHandleInfo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Hose3 {
     void maifang();
}

class Shenrs3 implements Hose3 {

    @Override
    public void maifang() {
        System.out.println("我是shenrs，我可以买房啦！！");
    }
}

/**
 * Cglib动态代理
 */
class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object oj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是中介，你可以开始买房了。。。");
        Object invokeSuper = methodProxy.invokeSuper(oj, objects);
        System.out.println("我是中介，你买房结束了。。。");
        return invokeSuper;
    }
}


/**
 * 动态代理模式
 */
public class Test007 {
    public static void main(String[] args) {
        Shenrs3 shenrs3 = new Shenrs3();
        CglibProxy cglibProxy = new CglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(shenrs3.getClass());
        enhancer.setCallback(cglibProxy);
        Hose3 hose3 = (Hose3) enhancer.create();
        hose3.maifang();
    }
}
