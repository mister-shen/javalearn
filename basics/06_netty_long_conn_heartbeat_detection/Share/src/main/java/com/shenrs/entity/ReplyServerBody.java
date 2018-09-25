package com.shenrs.entity;

/**
 * @author shenrs
 * @Description 服务端响应对象
 * @create 2018-09-25 15:56
 **/
public class ReplyServerBody extends ReplyBody{
    private String serverInfo;

    public ReplyServerBody(String serverInfo) {
        this.serverInfo = serverInfo;
    }

    public String getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }
}
