package com.shenrs;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author shenrs
 * @Description 分散读取与聚集写入
 * @create 2018-09-05 13:27
 **/
public class Test004 {
    public static void main(String[] args) throws IOException {
        //随机访问
        RandomAccessFile raf = new RandomAccessFile("E:\\projects\\javalearn\\basics\\04_nio_demo\\image\\test.txt", "rw");

        //获取通道
        FileChannel channel = raf.getChannel();
        //分配指定大小指定缓冲区
        ByteBuffer buf1=ByteBuffer.allocate(1024);
        ByteBuffer buf2=ByteBuffer.allocate(1024);
        ByteBuffer buf3=ByteBuffer.allocate(1024);
        ByteBuffer buf4=ByteBuffer.allocate(1024);
        // 分散读取
        ByteBuffer[] bufs={buf1,buf2, buf3, buf4};
        channel.read(bufs);
        for (ByteBuffer byteBuffer : bufs) {
            // 切换成读模式
            byteBuffer.flip();
        }


        System.out.println("------聚集写入---------");
        RandomAccessFile raf2 = new RandomAccessFile("E:\\projects\\javalearn\\basics\\04_nio_demo\\image\\test2.txt", "rw");
        //获取通道
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);
        raf2.close();
        raf.close();
    }

}
