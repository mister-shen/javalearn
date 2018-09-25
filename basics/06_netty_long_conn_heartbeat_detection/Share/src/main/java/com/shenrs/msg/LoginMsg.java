package com.shenrs.msg;

import com.shenrs.MsgType;

/**
 * @author shenrs
 * @Description 登陆类型消息
 * @create 2018-09-25 15:33
 **/
public class LoginMsg extends BaseMsg{
    private String username;
    private String password;

    /**
     * 初始化客户端id
     *
     */
    public LoginMsg() {
        super();
        setType(MsgType.LOGIN);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
