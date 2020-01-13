package cn.shenrs.patterns.creational.simplefactory.old;

/**
 * @author shenrs
 * @Description
 * @create 2020-01-13 16:08
 * 代码分析：
 *  不难看出，Chart类是一个“巨大的”类，在该类的设计中存在如下几个问题：
 *  (1) 在Chart类中包含很多“if…else…”代码块，整个类的代码相当冗长，代码越长，阅读难度、维护难度和测试难度也越大；
 *      而且大量条件语句的存在还将影响系统的性能，程序在执行过程中需要做大量的条件判断。
 *  (2) Chart类的职责过重，它负责初始化和显示所有的图表对象，将各种图表对象的初始化代码和显示代码集中在一个类中实现，
 *      违反了“单一职责原则”，不利于类的重用和维护；而且将大量的对象初始化代码都写在构造函数中将导致构造函数非常庞大，
 *      对象在创建时需要进行条件判断，降低了对象创建的效率。
 *  (3) 当需要增加新类型的图表时，必须修改Chart类的源代码，违反了“开闭原则”。
 *  (4) 客户端只能通过new关键字来直接创建Chart对象，Chart类与客户端类耦合度较高，对象的创建和使用无法分离。
 *  (5) 客户端在创建Chart对象之前可能还需要进行大量初始化设置，例如设置柱状图的颜色、高度等，如果在Chart类的构造函数
 *      中没有提供一个默认设置，那就只能由客户端来完成初始设置，这些代码在每次创建Chart对象时都会出现，导致代码的重复。
 **/
public class Chart {
    private String type; //图表类型
    public Chart(Object[][] data, String type) {
        this.type = type;
        if (type.equalsIgnoreCase("histogram")) {
            //初始化柱状图
        }
        else if (type.equalsIgnoreCase("pie")) {
            //初始化饼状图
        }
        else if (type.equalsIgnoreCase("line")) {
            //初始化折线图
        }
    }
    public void display() {
        if (this.type.equalsIgnoreCase("histogram")) {
            //显示柱状图
        }
        else if (this.type.equalsIgnoreCase("pie")) {
            //显示饼状图
        }
        else if (this.type.equalsIgnoreCase("line")) {
            //显示折线图
        }
    }
}