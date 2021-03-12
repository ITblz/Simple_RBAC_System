package com.blz.springsecuritydemo.service.impl;

import com.blz.springsecuritydemo.entity.Permission;
import com.blz.springsecuritydemo.mapper.PermissionMapper;
import com.blz.springsecuritydemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: PermissionServiceImpl
 * @Package: com.blz.springsecuritydemo.service.impl
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 下午12:56
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    /**
     * 查询所有权限以及权限对应的url
     * @return
     */
    @Override
    public List<Permission> queryAllPermissionUrl() {
        List<Permission>  permissionList = permissionMapper.queryAllPermissionUrl();
        if (permissionList == null || permissionList.size() == 0)
            return null;
        return permissionList;
    }

    /**
     * 根据角色ID查询权限列表
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> queryPermissionsByRoleId(Integer roleId) {
        List<Permission>  permissionList = permissionMapper.queryPermissionsByRoleId(roleId);
        if (permissionList == null || permissionList.size() == 0)
            return null;
        return permissionList;
    }

    @Override
    public List<Permission> queryPermissionsByUserId(Integer userId) {
        List<Permission>  permissionList = permissionMapper.queryPermissionsByUserId(userId);
        if (permissionList == null || permissionList.size() == 0)
            return null;
        return permissionList;
    }

    /**
     * 根据权限标识符获得url
     * @param permissionCode
     * @return
     */
    @Override
    public List<Permission> queryByPermissionCode(String permissionCode) {
        List<Permission>  permissionList = permissionMapper.queryByPermissionCode(permissionCode);
        if (permissionList == null || permissionList.size() == 0)
            return null;
        return permissionList;
    }

    @Override
    public List<Permission> queryPermissionsByUrl(String url) {
        List<Permission>  permissionList = permissionMapper.queryPermissionsByUrl(url);
        if (permissionList == null || permissionList.size() == 0)
            return null;
        return permissionList;
    }
}
