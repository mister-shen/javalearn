package cn.shenrs.patterns.creational.simplefactory.news;

/**
 * 柱状图类：具体产品类
 */
class HistogramChart implements Chart {
    public HistogramChart() {  
        System.out.println("创建柱状图！");  
    }
    @Override
    public void display() {  
        System.out.println("显示柱状图！");  
    }  
}

/**
 * 饼状图类：具体产品类
 */
class PieChart implements Chart {  
    public PieChart() {  
        System.out.println("创建饼状图！");  
    }
    @Override
    public void display() {  
        System.out.println("显示饼状图！");  
    }  
}
/**
 * 折线图类：具体产品类
 */
class LineChart implements Chart {
    public LineChart() {  
        System.out.println("创建折线图！");  
    }
    @Override
    public void display() {  
        System.out.println("显示折线图！");  
    }  
}

/**
 * 图表工厂类：工厂类
 *
 * 在简单工厂模式结构图中包含如下几个角色：
 *    ● Factory（工厂角色）：工厂角色即工厂类，它是简单工厂模式的核心，负责实现创建所有产品实例的内部逻辑；
 *       工厂类可以被外界直接调用，创建所需的产品对象；在工厂类中提供了静态的工厂方法factoryMethod()，
 *       它的返回类型为抽象产品类型Product。
 *
 *    ● Product（抽象产品角色）：它是工厂类所创建的所有对象的父类，封装了各种产品对象的公有方法，它的引入将
 *       提高系统的灵活性，使得在工厂类中只需定义一个通用的工厂方法，因为所有创建的具体产品对象都是其子类对象。
 *
 *    ● ConcreteProduct（具体产品角色）：它是简单工厂模式的创建目标，所有被创建的对象都充当这个角色的某个具
 *       体类的实例。每一个具体产品角色都继承了抽象产品角色，需要实现在抽象产品中声明的抽象方法。
 *
 */
public class ChartFactory {
    //静态工厂方法  
    public static Chart getChart(String type) {  
        Chart chart = null;  
        if (type.equalsIgnoreCase("histogram")) {  
            chart = new HistogramChart();  
            System.out.println("初始化设置柱状图！");  
        }  
        else if (type.equalsIgnoreCase("pie")) {  
            chart = new PieChart();
            System.out.println("初始化设置饼状图！");  
        }  
        else if (type.equalsIgnoreCase("line")) {  
            chart = new LineChart();
            System.out.println("初始化设置折线图！");              
        }  
        return chart;  
    }  
}