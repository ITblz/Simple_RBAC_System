package com.blz.springsecuritydemo.service.impl;

import com.blz.springsecuritydemo.entity.User;
import com.blz.springsecuritydemo.mapper.UserMapper;
import com.blz.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: UserServiceImpl
 * @Package: com.blz.springsecuritydemo.service.impl
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 下午12:42
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryByUserName(String username) {
        //根据用户名查询用户
        User user = userMapper.queryByUserName(username);
        return user;
    }

}
