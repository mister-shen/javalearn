package com.shenrs;

/**
 * 设置新生代比例参数
 */
public class Demo003 {
    public static void main(String[] args) {
        // -Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
        // 堆初始容量 堆最大容量 新生代大小 eden容量大小与s0、s1容量大小为2:1 更详细的GC日志 串行回收
        byte [] b = null;
        for (int i = 0; i < 10; i++) {
            b =new byte[1*1024*1024];
        }
    }
}
