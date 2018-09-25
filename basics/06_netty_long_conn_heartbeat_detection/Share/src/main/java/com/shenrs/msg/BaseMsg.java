package com.shenrs.msg;

import com.shenrs.Constants;
import com.shenrs.MsgType;

import java.io.Serializable;

/**
 * @author shenrs
 * @Description Message 基类
 * @create 2018-09-25 15:25
 **/
// 必须实现序列化 serialVersionUID必须有，否则netty消息序列化与反序列化会有问题，接收不到消息
public class BaseMsg implements Serializable{
    private static final long serialVersionUID = 1L;

    private MsgType type;

    /**
     * 必须唯一，否则会出现channel调用混乱
     */
    private String clientId;

    /**
     * 初始化客户端id
     */
    public BaseMsg() {
        this.clientId = Constants.getClientId();
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
