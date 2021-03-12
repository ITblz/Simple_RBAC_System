package com.blz.springsecuritydemo.controller;

import com.blz.springsecuritydemo.utils.ResultCode;
import com.blz.springsecuritydemo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: MyErrorController
 * @Package: com.blz.springsecuritydemo.controller
 * @Description: 处理404异常
 * @author: ITblz
 * @date: 2021/3/12 下午11:10
 */
@RestController
public class MyErrorController implements ErrorController {

    @Autowired
    ResultGenerator resultGenerator;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String error() {
        return resultGenerator.getFreeResult(ResultCode.NOT_FOUND, "Not Found!", null).toString();
    }
}
