package com.shenrs;

public class Demo004 {

	public static void main(String[] args) {
		byte[] bytes = null;
		for (int i = 0; i < 10; i++) {
			System.out.println("i:" + i);
			bytes = new byte[1 * 1024 * 1024];
		}
	}

}