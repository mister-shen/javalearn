package com.shenrs.exception;

/**
 * @author shenrs
 * @Description
 * @create 2018-11-08 9:54
 **/
public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("用户不存在");
    }
}
