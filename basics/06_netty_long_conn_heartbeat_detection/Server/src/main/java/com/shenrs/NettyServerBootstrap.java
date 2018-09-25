package com.shenrs;

import com.shenrs.msg.AskMsg;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.TimeUnit;

/**
 * @author shenrs
 * @Description Netty服务器实现
 * @create 2018-09-25 17:14
 **/
public class NettyServerBootstrap {
    private int port;
    private SocketChannel socketChannel;

    public NettyServerBootstrap(int port) throws InterruptedException {
        this.port = port;
        bind();
    }

    private void bind() throws InterruptedException {
        // 配置服务端的NIO线程组
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        // 通过NoDelay禁用Nagel, 使消息立即发出去，不用等待到一定的数量才发出去
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        // 保持长连接状态
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline p = socketChannel.pipeline();
                p.addLast(new ObjectEncoder());
                p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                p.addLast(new NettyServerHandler());
            }
        });

        // 绑定端口，同步等待成功
        ChannelFuture f = bootstrap.bind(port).sync();
        if(f.isSuccess()){
            System.out.println("server start.......");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        NettyServerBootstrap nettyServerBootstrap = new NettyServerBootstrap(8080);
        while (true) {
            SocketChannel channel = (SocketChannel) NettyChannelMap.get("001");
            if (channel != null){
                AskMsg askMsg = new AskMsg();
                channel.writeAndFlush(askMsg);
            }

            TimeUnit.SECONDS.sleep(10);
        }
    }

}
