package com.shenrs.msg;

import com.shenrs.MsgType;
import com.shenrs.entity.ReplyBody;

/**
 * @author shenrs
 * @Description 响应消息类型
 * @create 2018-09-25 15:47
 **/
public class ReplyMsg extends BaseMsg {
    /**
     * 初始化客户端id
     *
     */
    public ReplyMsg() {
        super();
        setType(MsgType.REPLY);
    }

    private ReplyBody body;

    public ReplyBody getBody() {
        return body;
    }

    public void setBody(ReplyBody body) {
        this.body = body;
    }
}
