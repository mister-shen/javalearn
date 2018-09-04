package com.shenrs;

import java.io.IOException;
import java.net.*;

/**
 * upd:面向无连接，不需要建立连接。每个数据报不能超过64k,不安全，但速度快。
 * upd服务器端
 */
public class UpdServerSocket {
    public static void main(String[] args) throws IOException {
        System.out.println("udp服务器端启动连接....");
        DatagramSocket ds = new DatagramSocket(80);
        byte[] bytes = new byte[1024];
        // 创建数据包
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        // 阻塞，等待接收客户端请求
        ds.receive(dp);

        System.out.println("来源地址：" + dp.getAddress() + ", 端口：" + dp.getPort());
        // 获取客户端请求内容
        String clientData = new String(dp.getData(), 0, dp.getLength());
        System.out.println("来自客户端的数据：" + clientData);
        ds.close();
    }
}

/**
 * upd客户端
 */
class UpdClientSocket {
    public static void main(String[] args) throws IOException {
        System.out.println("udp客户端启动连接....");

        DatagramSocket ds = new DatagramSocket();
        String clientData = "hello world 服务器！";
        byte[] bytes = clientData.getBytes();

        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 80);
        ds.send(dp);
        ds.close();
    }
}
