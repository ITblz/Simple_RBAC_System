package com.blz.springsecuritydemo.utils;

/**
 * @Title: ResultCode
 * @Package: com.blz.springsecuritydemo.utils
 * @Description: 响应码
 * @author: ITblz
 * @date: 2021/3/12 下午6:35
 */
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public int getCode() {
        return code;
    }
}
