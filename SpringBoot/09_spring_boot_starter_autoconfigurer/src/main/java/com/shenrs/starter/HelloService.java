package com.shenrs.starter;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-30 14:53
 **/
public class HelloService {

    private HelloProperties properties;

    public String sayHello(String name){
        return properties.getPrefix() + "-" + name + properties.getSuffix();
    }

    public HelloProperties getProperties() {
        return properties;
    }

    public void setProperties(HelloProperties properties) {
        this.properties = properties;
    }
}
