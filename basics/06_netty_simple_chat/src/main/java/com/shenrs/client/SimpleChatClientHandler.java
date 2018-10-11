package com.shenrs.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author shenrs
 * @Description
 * @create 2018-09-27 11:05
 **/
public class SimpleChatClientHandler extends SimpleChannelInboundHandler<String>{


    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
    }
}
