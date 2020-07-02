package cn.shenrs.patterns.creational.simplefactory;

import cn.shenrs.patterns.creational.simplefactory.news.Chart;
import cn.shenrs.patterns.creational.simplefactory.news.ChartFactory;
import cn.shenrs.patterns.utils.XMLUtil;

class Client {
    public static void main(String args[]) {
        // 获取chart类型
        String chartType = XMLUtil.getChartType("creational/simplefactory/config.xml");
        Chart chart = ChartFactory.getChart(chartType); //通过静态工厂方法创建产品
//        Chart chart = new Chart(new Object[][]{}, "histogram"); // 普通方法创建产品
        chart.display();  
    }  
}