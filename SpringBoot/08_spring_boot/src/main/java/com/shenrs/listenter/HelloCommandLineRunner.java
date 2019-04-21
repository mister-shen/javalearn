package com.shenrs.listenter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-30 14:03
 **/
@Component
public class HelloCommandLineRunner implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner....run...." + Arrays.asList(args));
    }
}
