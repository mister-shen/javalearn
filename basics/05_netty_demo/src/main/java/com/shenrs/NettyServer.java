package com.shenrs;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServerHanlder extends SimpleChannelHandler {

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
        System.out.println("服务器获取客户端发来的参数：" + e.getMessage());
        ctx.getChannel().write("爹爹在这！");
    }
}

/**
 * netty 服务器端
 */
public class NettyServer {
    public static void main(String[] args) {
        // 1.创建服务对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 2.创建两个线程池 第一个监听端口号 第二个监听nio
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService wook = Executors.newCachedThreadPool();
        // 3.将线程池放入工程
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boos, wook));
        // 4.设置管道工程
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            // 设置管道
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                // 数据传输的时候直接为String类型
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                // 设置事件监听类
                pipeline.addLast("serverHanlder", new ServerHanlder());
                return pipeline;
            }
        });

        // 绑定端口号
        serverBootstrap.bind(new InetSocketAddress(80));
        System.out.println("netty服务器端已经启动....");
    }
}
