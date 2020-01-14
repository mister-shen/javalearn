package cn.shenrs.patterns.creational.factorymethod.news;

//文件日志记录器：具体产品
class FileLogger implements Logger {  
    public void writeLog() {  
        System.out.println("文件日志记录。");  
    }  
}  