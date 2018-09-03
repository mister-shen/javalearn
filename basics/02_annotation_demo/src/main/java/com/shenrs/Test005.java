package com.shenrs;

interface Hose {
     void maifang();
}

class Shenrs implements Hose {

    @Override
    public void maifang() {
        System.out.println("我是shenrs，我可以买房啦！！");
    }
}

class Proxy implements Hose {
    // 代理对象
    private Shenrs shenrs;

    public Proxy(Shenrs shenrs) {
        this.shenrs = shenrs;
    }

    @Override
    public void maifang() {
        System.out.println("我是中介，你可以开始买房了。。。");
        shenrs.maifang();
        System.out.println("我是中介，你买房结束了。。。");
    }
}


/**
 * 静态代理模式
 */
public class Test005 {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new Shenrs());
        proxy.maifang();
    }
}
