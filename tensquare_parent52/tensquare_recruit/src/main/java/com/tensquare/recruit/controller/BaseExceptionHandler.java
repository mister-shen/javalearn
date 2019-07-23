package com.tensquare.recruit.controller;

import entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author shenrs
 * @Description 统一异常处理
 * @create 2019-07-23 15:35
 **/
@RestControllerAdvice
public class BaseExceptionHandler {

    /**
     * 统一处理Exception异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }
}
