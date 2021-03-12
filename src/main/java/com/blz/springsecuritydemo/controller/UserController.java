package com.blz.springsecuritydemo.controller;

import com.blz.springsecuritydemo.entity.User;
import com.blz.springsecuritydemo.service.UserService;
import com.blz.springsecuritydemo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: UserController
 * @Package: com.blz.springsecuritydemo.controller
 * @Description:
 * @author: ITblz
 * @date: 2021/3/10 下午9:41
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ResultGenerator resultGenerator;

    @RequestMapping("/query")
    public String getAllUser(){
        User user = userService.queryByUserName("zhangsan");
        return resultGenerator.getSuccessResult("查询到的信息：",user).toString();
    }

}
