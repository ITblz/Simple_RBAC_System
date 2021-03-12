package com.blz.springsecuritydemo.config.service;

import com.blz.springsecuritydemo.config.entity.MyUserDetails;
import com.blz.springsecuritydemo.entity.Permission;
import com.blz.springsecuritydemo.entity.User;
import com.blz.springsecuritydemo.service.PermissionService;
import com.blz.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: UserDetailsServiceImpl
 * @Package: com.blz.springsecuritydemo.config.service
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 上午9:13
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //获得用户信息
        User user = userService.queryByUserName(s);
        if(user == null || !user.getUsername().equals(s))
            throw new UsernameNotFoundException("认证异常：用户名不存在！");

        //声明用户权限
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //获得用户权限
        List<Permission> permissionList = permissionService.queryPermissionsByUserId(user.getId());
        permissionList.forEach(permission -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getCode());
            grantedAuthorities.add(grantedAuthority);
        });
        //返回UserDetails对象
        return new MyUserDetails(user.getUsername(),user.getPassword(),grantedAuthorities,user.getId());
    }
}
