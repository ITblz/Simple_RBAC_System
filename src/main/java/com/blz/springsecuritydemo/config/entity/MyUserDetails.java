package com.blz.springsecuritydemo.config.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Title: MyUserDetails
 * @Package: com.blz.springsecuritydemo.config.entity
 * @Description: 用户数据结构
 * @author: ITblz
 * @date: 2021/3/11 上午9:15
 */
public class MyUserDetails extends User {

    private Integer id;

    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,Integer id) {
        super(username, password, authorities);
        this.setId(id);
    }

    public MyUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
