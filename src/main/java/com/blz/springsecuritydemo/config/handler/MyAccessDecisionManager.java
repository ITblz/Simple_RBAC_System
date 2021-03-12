package com.blz.springsecuritydemo.config.handler;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Title: MyAccessDecisionManager
 * @Package: com.blz.springsecuritydemo.config.handler
 * @Description:
 * @author: ITblz
 * @date: 2021/3/12 上午9:15
 */
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //当前用户所具有的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            //当前请求需要的权限
            String needPermission = ca.getAttribute();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needPermission)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("无权访问!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
