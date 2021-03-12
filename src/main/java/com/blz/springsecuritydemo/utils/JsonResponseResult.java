package com.blz.springsecuritydemo.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Title: JsonResponseResult
 * @Package: com.blz.springsecuritydemo.utils
 * @Description: 响应信息数据结构
 * @author: ITblz
 * @date: 2021/3/12 下午6:34
 */
public class JsonResponseResult {
    public void setCode(int code) {
        this.code = code;
    }

    private int code;//状态码

    private String message;//消息

    private Object data;//数据

    public JsonResponseResult setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public JsonResponseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResponseResult setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
