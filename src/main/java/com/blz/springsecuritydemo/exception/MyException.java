package com.blz.springsecuritydemo.exception;

import lombok.Data;

/**
 * @Title: MyException
 * @Package: com.blz.springsecuritydemo.exception
 * @Description: 自定义异常
 * @author: ITblz
 * @date: 2021/3/12 下午11:40
 */

@Data
public class MyException extends RuntimeException {

    //错误吗
    private Integer code;

    //错误信息
    private String msg;

    public MyException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}