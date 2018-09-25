package com.shenrs.msg;

import com.shenrs.MsgType;

/**
 * @author shenrs
 * @Description 心跳检测Ping类型消息
 * @create 2018-09-25 15:37
 **/
public class PingMsg extends BaseMsg {
    /**
     * 初始化客户端id
     */
    public PingMsg() {
        super();
        setType(MsgType.PING);
    }
}
