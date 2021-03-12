package com.blz.springsecuritydemo.mapper;

import com.blz.springsecuritydemo.entity.Permission;

import java.util.List;

/**
 * @Title: PermissionMapper
 * @Package: com.blz.springsecuritydemo.mapper
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 上午10:56
 */
public interface PermissionMapper {


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
     * 根据用户id查询权限
     * @param userId
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

    int insert(Permission permission);

    int update(Permission permission);

    int delete(Integer id);
}
