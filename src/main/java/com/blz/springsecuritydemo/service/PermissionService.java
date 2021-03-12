package com.blz.springsecuritydemo.service;

import com.blz.springsecuritydemo.entity.Permission;

import java.util.List;

/**
 * @Title: PermissionService
 * @Package: com.blz.springsecuritydemo.service
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 上午11:16
 */
public interface PermissionService {

    /**
     * 查询所有权限以及对应的资源
     * @return
     */
    List<Permission> queryAllPermissionUrl();

    /**
     * 根据角色id查询权限
     * @return
     */
    List<Permission> queryPermissionsByRoleId(Integer roleId);

    /**
     * 根据用户id查询对应权限
     * @return
     */
    List<Permission> queryPermissionsByUserId(Integer userId);

    /**
     * 根据权限标识符查询权限
     * @param permissionCode
     * @return
     */
    List<Permission> queryByPermissionCode(String permissionCode);

    /**
     * 根据url获取对应的所需权限列表
     * @param url
     * @return
     */
    List<Permission> queryPermissionsByUrl(String url);
}
