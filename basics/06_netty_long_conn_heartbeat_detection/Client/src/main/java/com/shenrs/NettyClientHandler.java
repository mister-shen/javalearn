package com.shenrs;

import com.shenrs.entity.ReplyClientBody;
import com.shenrs.entity.ReplyServerBody;
import com.shenrs.msg.BaseMsg;
import com.shenrs.msg.LoginMsg;
import com.shenrs.msg.PingMsg;
import com.shenrs.msg.ReplyMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

/**
 * @author shenrs
 * @Description
 * @create 2018-09-25 17:36
 **/
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMsg> {


    /**
     * 利用些空闲发送心跳检测消息
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    PingMsg pingMsg = new PingMsg();
                    ctx.writeAndFlush(pingMsg);
                    System.out.println("send ping to server.....");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BaseMsg baseMsg) throws Exception {
        MsgType msgType= baseMsg.getType();
        switch (msgType) {
            case LOGIN: {
                // 向服务器发起登陆
                LoginMsg loginMsg = new LoginMsg();
                loginMsg.setUsername("shenrs");
                loginMsg.setPassword("shenrs");
                ctx.writeAndFlush(loginMsg);
            }break;
            case PING: {
                System.out.println("send ping to server.....");
            }break;
            case ASK: {
                ReplyClientBody replyClientBody = new ReplyClientBody("client info **** !!!");
                ReplyMsg replyMsg = new ReplyMsg();
                replyMsg.setBody(replyClientBody);
                ctx.writeAndFlush(replyMsg);
            }break;
            case REPLY: {
                ReplyMsg replyMsg = (ReplyMsg) baseMsg;
                ReplyServerBody replyServerBody = (ReplyServerBody) replyMsg.getBody();
                System.out.println("receive client msg: " + replyServerBody.getServerInfo());
            }break;
            default:break;
        }

        ReferenceCountUtil.release(baseMsg);
    }
}
