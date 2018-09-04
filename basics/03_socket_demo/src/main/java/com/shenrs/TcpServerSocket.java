package com.shenrs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * tcp服务器端
 */
public class TcpServerSocket {
    public static void main(String[] args) throws IOException {
        System.out.println("tcp协议服务器端启动....");
        // 创建服务器连接
        ServerSocket socket = new ServerSocket(80);

        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            // 接收客户端请求，阻塞功能
            while (true) {
                Socket accept = socket.accept();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            InputStream inputStream = accept.getInputStream();
                            // 将字节流转换为字节数组
                            byte[] bytes = new byte[1024];
                            int len = inputStream.read(bytes);
                            String result = new String(bytes, 0, len);
                            System.out.println("服务器端接收客户端内容：" + result);

                            OutputStream outputStream = accept.getOutputStream();
                            outputStream.write("test response....".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            socket.close();
        }
    }
}

/**
 * tcp客户端
 */
class TcpClientSocket {
    public static void main(String[] args) throws IOException {
        System.out.println("tcp协议客户端启动....");
        Socket socket = new Socket("127.0.0.1", 80);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("tcp客户端数据....".getBytes());
        socket.close();
    }
}

