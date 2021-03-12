package com.blz.springsecuritydemo.controller;

import com.blz.springsecuritydemo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: TestController
 * @Package: com.blz.springsecuritydemo.controller
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 下午6:54
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    ResultGenerator resultGenerator;

    @GetMapping("/p1")
    public String p1(){

        return resultGenerator.getSuccessResult("您正在访问/p/p1").toString();
    }

    @GetMapping("/p2")
    public String p2(){
        return resultGenerator.getSuccessResult("您正在访问/p/p2").toString();
    }

    @GetMapping("/p3")
    public String p3(){
        return resultGenerator.getSuccessResult("您正在访问/p/p3").toString();
    }

    @GetMapping("/p4")
    public String p4(){
        return resultGenerator.getSuccessResult("您正在访问/p/p4").toString();
    }
}
