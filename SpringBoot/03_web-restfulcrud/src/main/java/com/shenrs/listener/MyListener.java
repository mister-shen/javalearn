package com.shenrs.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 自定义Listener
 * @author shenrs
 * @Description
 * @create 2018-11-08 16:22
 **/
@WebListener("myListener")
public class MyListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized...web项目启动了.....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed...web项目销毁了.....");
    }
}
