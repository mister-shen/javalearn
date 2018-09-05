package com.shenrs;
import java.nio.ByteBuffer;

/**
 * @author shenrs
 * @Description mark与reset的用法
 * @create 2018-09-05 10:51
 **/
public class Test002 {

    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        String str = "abcd";
        byteBuffer.put(str.getBytes());

        //开启读的模式
        byteBuffer.flip();

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.mark();// 打印标记
        byteBuffer.get(bytes, 0, 2);
        System.out.println(new String(bytes, 0, 2));
        System.out.println("当前buffer的下标（position）：" + byteBuffer.position());
        byteBuffer.reset(); // position还原到mark之前

        byte[] bytes2 = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes2, 2, 2);
        System.out.println(new String(bytes2, 2, 2));
        System.out.println("当前buffer的下标（position）：" + byteBuffer.position());


    }
}
