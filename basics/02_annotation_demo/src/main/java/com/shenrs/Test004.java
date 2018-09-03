package com.shenrs;

interface Cat {
    void run();
}

class Aodi implements Cat{
    @Override
    public void run() {
        System.out.println("我是正在跑的奥迪。。。");
    }
}

class BenChi implements Cat{
    @Override
    public void run() {
        System.out.println("我是正在跑的奔驰。。。");
    }
}

class CatFactory {
    public static Cat createCat(String name) {
        Cat cat = null;
        switch (name) {
            case "Aodi":
                cat = new Aodi();
                break;
            case "BenChi":
                cat  = new BenChi();
                break;
            default:
                break;
        }
        return cat;
    }
}


/**
 * 工厂模式
 */
public class Test004 {
    public static void main(String[] args) {
        Cat benChi = CatFactory.createCat("BenChi");
        benChi.run();
    }
}
