package com.shenrs.entity;

import java.io.Serializable;

/**
 * @author shenrs
 * @Description 请求类型参数，必须实现序列化接口
 * @create 2018-09-25 15:42
 **/
public class AskParams implements Serializable {
    private static final long serialVersionUID = 1L;
    private String auth;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
