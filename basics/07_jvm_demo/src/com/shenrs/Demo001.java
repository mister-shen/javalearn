package com.shenrs;

/**
 * 加了static关键修饰的存放在方法区（永久）
 */
public class Demo001 {

    private static Integer count = 0; // 在方法区中
//    private static Demo001 demo001 = new Demo001();// 在方法区中
//    private Demo001 demo001 = new Demo001();// 在堆内存中

    public static void main(String[] args) {
        Demo001 demo001 = new Demo001();
        Demo001 demo002 = new Demo001();
        ++demo001.count;
        ++demo002.count;
        System.out.println(demo002.count);
    }
}
