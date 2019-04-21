package com.shenrs.listenter;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-30 14:02
 **/
@Component
public class HelloApplicationRunner implements ApplicationRunner{
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner....run....");
    }
}
