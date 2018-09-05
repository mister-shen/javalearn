package com.shenrs;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 缓冲区是NIO提高给传输文件和通道一起配合使用，存储数据。
 * Buffer <br/>
 * ByteBuffer <br/>
 * LongBuffer <br/>
 * FloatBuffer <br/>
 * DobboBuffer <br/>
 * @author shenrs
 * @Description
 * @create 2018-09-05 9:53
 **/
public class BufferTest {

    /**
     * position 缓冲区正在操作的位置，默认从0开始。
     * limit 界面（缓冲区可用的大小）
     * capacity 缓冲区最大容量，一旦声明不能改变。
     *
     * 核心方法：
     *  put() 往buffer存取数据
     *  get() 获取数据
     */
    @Test
    public void test001() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("往byteBuffer存放数据....");
        byteBuffer.put("哈哈哈哈".getBytes());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("--------读取值-------");

        // 开启读取模式
        byteBuffer.flip();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));

        System.out.println("--------重复读取值-------");
        // 重复读取模式
        byteBuffer.rewind();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        byte[] bytes2 = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes2);
        System.out.println(new String(bytes2, 0, bytes2.length));

        // 清空缓冲区数据被遗忘了.... 清空了下标值
        System.out.println("--------清空缓冲区-------");
        byteBuffer.clear();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byte[] bytes3 = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes3);
        System.out.println(new String(bytes3, 0, bytes3.length));

    }
}
