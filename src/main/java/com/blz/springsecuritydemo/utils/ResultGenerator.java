package com.blz.springsecuritydemo.utils;

import org.springframework.stereotype.Component;

/**
 * @Title: ResultGenerator
 * @Package: com.blz.springsecuritydemo.utils
 * @Description:
 * @author: ITblz
 * @date: 2021/3/12 下午6:37
 */
@Component
public class ResultGenerator extends  JsonResponseResult {

    private static final String SUCCESS = "success";
    JsonResponseResult JsonResponseResult = new JsonResponseResult();

    //成功
    public JsonResponseResult getSuccessResult() {
        return new JsonResponseResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS);
    }

    //成功，附带额外数据
    public JsonResponseResult getSuccessResult(Object data) {
        return new JsonResponseResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS)
                .setData(data);
    }

    //成功，自定义消息及数据
    public JsonResponseResult getSuccessResult(String message, Object data) {
        return new JsonResponseResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }

    //失败，附带消息
    public JsonResponseResult getFailResult(String message) {
        return new JsonResponseResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    //失败，自定义消息及数据
    public JsonResponseResult getFailResult(String message, Object data) {
        return new JsonResponseResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message)
                .setData(data);
    }

    //自定义创建
    public JsonResponseResult getFreeResult(ResultCode code, String message, Object data) {
        return new JsonResponseResult()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }
}
