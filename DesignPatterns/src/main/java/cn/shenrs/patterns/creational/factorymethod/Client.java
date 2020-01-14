package cn.shenrs.patterns.creational.factorymethod;

import cn.shenrs.patterns.creational.factorymethod.news.FileLoggerFactory;
import cn.shenrs.patterns.creational.factorymethod.news.Logger;
import cn.shenrs.patterns.creational.factorymethod.news.LoggerFactory;
import cn.shenrs.patterns.utils.XMLUtil;

/*import cn.shenrs.patterns.creational.factorymethod.old.Logger;
import cn.shenrs.patterns.creational.factorymethod.old.LoggerFactory;*/

class Client {
    public static void main(String args[]) {

        // 工厂方法模式
        // 工厂接口方法模式
        /*LoggerFactory factory;
        Logger logger;
        factory = new FileLoggerFactory(); //可引入配置文件实现
        logger = factory.createLogger();  
        logger.writeLog();*/
        // 改为工厂抽象方法
        LoggerFactory factory = (LoggerFactory) XMLUtil.getBean("creational/factorymethod/config.xml");
        factory.writeLog();

        // 简单工厂模式
        /*Logger factory = LoggerFactory.createLogger("db");
        factory.writeLog();*/
    }  
}