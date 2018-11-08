package com.shenrs.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 给容器中加入我们自己定义的ErrorAttributes
 * @author shenrs
 * @Description 自定义错误返回json
 * @create 2018-11-08 10:46
 **/
public class MyErrorAttributes extends DefaultErrorAttributes{
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        // 自定义额外的参数
        errorAttributes.put("company", "shenrs");
        // 获取自定义异常的参数 scope:0 从request拿，1 从session中拿
        Map<String, Object> extMap = (Map<String, Object>)
                webRequest.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        errorAttributes.put("ext", extMap);
        return errorAttributes;
    }
}
