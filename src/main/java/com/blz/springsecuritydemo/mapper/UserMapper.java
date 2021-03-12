package com.blz.springsecuritydemo.mapper;

import com.blz.springsecuritydemo.entity.User;

/**
 * @Title: UserMapper
 * @Package: com.blz.springsecuritydemo.mapper
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 上午9:02
 */
public interface UserMapper {

    User queryByUserName(String username);

    User queryByUserId(Integer id);

    int insert(User user);

    int update(User user);

    int delete(Integer id);
}
