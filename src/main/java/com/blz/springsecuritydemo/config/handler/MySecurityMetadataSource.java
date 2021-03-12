package com.blz.springsecuritydemo.config.handler;

import com.blz.springsecuritydemo.entity.Permission;
import com.blz.springsecuritydemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Title: MySecurityMetadataSource
 * @Package: com.blz.springsecuritydemo.config.handler
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 下午6:25
 */
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        HttpServletRequest request = ((FilterInvocation)o).getRequest();
        String curUrl = request.getRequestURI();

        List<Permission> permissionList = permissionService.queryPermissionsByUrl(curUrl);

        if(!(permissionList == null || permissionList.size() == 0)){

            List<String> permissions = new ArrayList<>(permissionList.size());
            String[] attributes = new String[permissionList.size()];
            for(int i = 0;i<permissionList.size();i++){
                attributes[i] = permissionList.get(i).getCode();
            }
            return SecurityConfig.createList(attributes);
        }

        return null;

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
