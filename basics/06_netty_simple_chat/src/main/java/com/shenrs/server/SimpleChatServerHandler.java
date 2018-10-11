package com.shenrs.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author shenrs
 * @Description handler(处理器) 是由 Netty 生成用来处理 I/O 事件的。
 * @create 2018-09-26 9:46
 **/

/**
 *    SimpleChatServerHandler 继承自 SimpleChannelInboundHandler，
 * 这个类实现了 ChannelInboundHandler 接口，ChannelInboundHandler
 * 提供了许多事件处理的接口方法，然后你可以覆盖这些方法。现在仅仅只需要继承
 * SimpleChannelInboundHandler 类而不是你自己去实现接口方法。
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * 每当从服务端收到新的客户端连接时，
     * 客户端的 Channel 存入 ChannelGroup 列表中，
     * 并通知列表中的其他客户端 Channel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入 \n");
        channels.add(incoming);
    }

    /**
     * 每当从服务端收到客户端断开时，
     * 客户端的 Channel 自动从 ChannelGroup 列表中移除了，
     * 并通知列表中的其他客户端 Channel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        //关闭通道自动从ChannelGroup移除，
        //所以没有必要做“channel .remove(ctx.channel();”

    }

    /**
     * 每当从服务端读到客户端写入信息时，
     * 将信息转发给其他客户端的 Channel。
     * 其中如果你使用的是 Netty 5.x 版本时，需要把 channelRead0() 重命名为messageReceived()
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            if(channel != incoming) {
                channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + msg + " \n ");
            }else{
                channel.writeAndFlush("[you]" + msg + "\n");
            }
        }
    }

    /**
     * 服务端监听到客户端活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
    }

    /**
     * 服务端监听到客户端不活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
    }

    /**
     * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，
     * 即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。
     * 在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。
     * 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，
     * 比如你可能想在关闭连接之前发送一个错误码的响应消息。
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
