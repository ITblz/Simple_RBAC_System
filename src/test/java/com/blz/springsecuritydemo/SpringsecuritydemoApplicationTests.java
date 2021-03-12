package com.blz.springsecuritydemo;

import com.blz.springsecuritydemo.entity.User;
import com.blz.springsecuritydemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringsecuritydemoApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        String s = "zhangsan";
        User user = userMapper.queryByUserName(s);
        System.out.println(user.getMobile());
    }

}
