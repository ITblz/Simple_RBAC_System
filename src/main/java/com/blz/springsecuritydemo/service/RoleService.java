package com.blz.springsecuritydemo.service;

import com.blz.springsecuritydemo.entity.Role;

import java.util.List;

/**
 * @Title: RoleService
 * @Package: com.blz.springsecuritydemo.service
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 下午12:40
 */
public interface RoleService {

    List<Role> queryByRoleCode(String roleCode);

    Role queryByRoleId(Integer roleId);

    List<Role> queryRoleByUserId(Integer userId);
}
