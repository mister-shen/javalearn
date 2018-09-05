package com.shenrs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shenrs
 * @Description 普通io流网络传输实现异步效果，普通io流是阻塞的。
 * @create 2018-09-05 16:32
 **/

/**
 * tcp 服务端
 */
public class TcpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("socket tcp服务器端启动....");
        ServerSocket serverSocket = new ServerSocket(80);
        // 等待客户端响应
        try{
            while (true) {
                Socket accept = serverSocket.accept();
                ExecutorService executorService = Executors.newFixedThreadPool(6);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = accept.getInputStream();
                            // 转换成String类型
                            byte[] bytes = new byte[1024];
                            int len = inputStream.read(bytes);
                            String str = new String(bytes, 0, len);
                            System.out.println("服务器端接收客户端的信息：" + str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                serverSocket.close();
            }
        }
    }
}

/**
 * tcp服务端
 */
class TcpClient {
    public static void main(String[] args) throws IOException {
        System.out.println("socket tcp 客户端启动....");
        Socket socket = new Socket("127.0.0.1", 80);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("网络传输，普通io流是阻塞的！".getBytes());
        socket.close();
    }
}
