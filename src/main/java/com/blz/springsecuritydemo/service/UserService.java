package com.blz.springsecuritydemo.service;

import com.blz.springsecuritydemo.entity.User;

/**
 * @Title: UserService
 * @Package: com.blz.springsecuritydemo.service
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 上午9:12
 */
public interface UserService {

    User queryByUserName(String username);
}
