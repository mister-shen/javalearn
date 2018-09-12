package com.shenrs;

import java.text.DecimalFormat;

/**
 * 设置最大堆内存
 */
public class Demo002 {

    public static void main(String[] args) throws InterruptedException {
        byte[] bytes = new byte[1 * 1024 * 1024];
        System.out.println("分配了 1M 内存");
        jvmInfo();
        Thread.sleep(3000);
        byte[] bytes2 = new byte[4 * 1024 * 1024];
        System.out.println("分配了 4M 内存");
        jvmInfo();
    }

    public static String toM(long maxMemory) {
        float num = (float) maxMemory / (1024 * 1024);
        DecimalFormat format = new DecimalFormat("0.00");
        return  format.format(num);
    }

    public static void jvmInfo() throws InterruptedException {
        // 最大内存配置信息
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("maxMemory: " + maxMemory + "B , " + toM(maxMemory) + "M ");
        // 当前空闲内存
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("freeMemory: " + freeMemory + "B , " + toM(freeMemory) + "M ");
        // 已使用内存
        Thread.sleep(1000);
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory: " + totalMemory + "B , " + toM(totalMemory) + "M ");
    }
}
