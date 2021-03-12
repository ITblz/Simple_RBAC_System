package com.blz.springsecuritydemo.config.handler;

import com.blz.springsecuritydemo.exception.MyException;
import com.blz.springsecuritydemo.utils.ResultCode;
import com.blz.springsecuritydemo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: GlobalExceptionHandler
 * @Package: com.blz.springsecuritydemo.config.handler
 * @Description: 全局异常处理
 * @author: ITblz
 * @date: 2021/3/12 下午11:02
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    ResultGenerator resultGenerator;

    /**
     * 空指针异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {NullPointerException.class})
    public String handlerNullPoint(Exception e) {
        return resultGenerator.getFreeResult(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage(), e).toString();
    }

    /**
     * 参数解析失败异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        return resultGenerator.getFreeResult(ResultCode.FAIL, (request.getRequestURI() + ":参数解析失败"), e).toString();
    }

    /**
     * 不支持当前请求方法异常处理
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        return resultGenerator.getFreeResult(ResultCode.METHOD_NOT_ALLOWED, "不支持当前请求方法", e).toString();
    }


    /**
     * 项目运行异常处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request) {
        return resultGenerator.getFreeResult(ResultCode.INTERNAL_SERVER_ERROR, (request.getRequestURI() + "服务运行异常"), e).toString();
    }


    /**
     * 自定义异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MyException.class)
    public String handleException(MyException e, HttpServletRequest request) {
        return resultGenerator.getFreeResult(ResultCode.METHOD_NOT_ALLOWED, "自定义异常", e).toString();

    }

}
