package cn.shenrs.patterns.creational.factorymethod.news;

//日志记录器工厂接口：抽象工厂
public abstract class LoggerFactory {
    abstract Logger createLogger();
    abstract Logger createLogger(String args);
    abstract Logger createLogger(Object obj);

    // 在工厂类中直接调用日志记录器类的业务方法writeLog()
    public void writeLog(){
        Logger logger = this.createLogger();
        logger.writeLog();
    }
}  