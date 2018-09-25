package com.shenrs;

/**
 * @author shenrs
 * @Description 常量设置
 * @create 2018-09-25 15:30
 **/
public class Constants {
    private static String clientId;

    public static String getClientId(){
        return clientId;
    }

    public static void setClientId(String clientId) {
        Constants.clientId = clientId;
    }
}
