package cn.shenrs.patterns.creational.factorymethod.old;

/**
 * @author shenrs
 * @Description
 * @create 2020-01-14 10:02
 **/
public class FileLogger implements Logger{
    @Override
    public void writeLog() {
        System.out.println("打印文件日志");
    }
}
