package com.shenrs.msg;

import com.shenrs.MsgType;
import com.shenrs.entity.AskParams;

/**
 * @author shenrs
 * @Description 请求类型消息
 * @create 2018-09-25 15:40
 **/
public class AskMsg extends BaseMsg{
    /**
     * 初始化客户端id
     *
     */
    public AskMsg() {
        super();
        setType(MsgType.ASK);
    }

    private AskParams params;

    public AskParams getParams() {
        return params;
    }

    public void setParams(AskParams params) {
        this.params = params;
    }
}
