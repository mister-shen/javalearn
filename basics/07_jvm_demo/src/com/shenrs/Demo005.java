package com.shenrs;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出演示
 */
public class Demo005 {

	public static void main(String[] args) {
        List<Object> listObject = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			System.out.println("i:"+i);
			listObject.add(new byte[1*1024*1024]);
		}
		System.out.println("创建完毕!");
	}

}