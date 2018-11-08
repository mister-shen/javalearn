package com.shenrs.controller;

import com.shenrs.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenrs
 * @Description
 * @create 2018-11-08 10:02
 **/
@ControllerAdvice
public class MyExceptionHandler {

/*    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String, Object> handlerException(Exception e){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        return map;
    }*/

    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());

        // 传入我们自己的错误状态码 4xx 5xx，否则就不会进入定制错误页面的解析流程
        /*
         * BasicErrorController中获取status代码
         * Integer statusCode = (Integer) request
         * .getAttribute("javax.servlet.error.status_code");
         * */

        request.setAttribute("ext", map);
        // 设置传入状态码
        request.setAttribute("javax.servlet.error.status_code", 500);
        return "forward:/error";
    }

}
