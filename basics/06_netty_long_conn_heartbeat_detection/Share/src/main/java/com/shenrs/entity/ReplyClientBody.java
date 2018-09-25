package com.shenrs.entity;

/**
 * @author shenrs
 * @Description 客户端响应对象
 * @create 2018-09-25 15:54
 **/
public class ReplyClientBody extends ReplyBody{
    private String clientInfo;

    public ReplyClientBody(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }
}
