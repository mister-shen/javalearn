package cn.shenrs.patterns.creational.factorymethod.news;

//文件日志记录器工厂类：具体工厂
public class FileLoggerFactory extends LoggerFactory {
    public Logger createLogger() {  
        //创建文件日志记录器对象
        Logger logger = new FileLogger();
        //创建文件，代码省略
        return logger;
    }

    @Override
    public Logger createLogger(String args) {
        return null;
    }

    @Override
    public Logger createLogger(Object obj) {
        return null;
    }
}