package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String labelId) {
        return Result.fail(StatusCodeEnum.STATUS_ERROR.getCode(), "熔断器触发了！");
    }
}
