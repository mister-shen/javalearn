package com.shenrs;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ClientHandler extends SimpleChannelHandler {
    /** 3
     * 通道被关闭的时候会触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
        System.out.println("channelClosed");
    }

    /** 2
     * 必须要建立连接，关闭通道的时候才能触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        System.out.println("channelDisconnected");
    }

    /**
     * 接收出现的异常
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        System.out.println("exceptionCaught");
    }

    /** one
     * 接收客户端数据
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);
        System.out.println("messageReceived");
        System.out.println("来自服务器端回复的消息：" + e.getMessage());

    }
}

public class NettyClient {
    public static void main(String[] args) {
        System.out.println("netty客户端启动....");
        // 1.创建客户端类
        ClientBootstrap clientBootstrap = new ClientBootstrap();
        // 2.创建两个线程池 第一个监听端口号 第二个监听nio
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService wook = Executors.newCachedThreadPool();
        // 3.将线程池放入工程
        clientBootstrap.setFactory(new NioClientSocketChannelFactory(boos, wook));
        // 4.设置管道工程
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            // 设置管道
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                // 数据传输的时候直接为String类型
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                // 设置事件监听类
                pipeline.addLast("clientHandler", new ClientHandler());
                return pipeline;
            }
        });

        // 连接服务端
        ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 80));
        Channel channel = connect.getChannel();
        System.out.println("client start");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输输入内容...");
            channel.write(scanner.next());
        }
    }
}


