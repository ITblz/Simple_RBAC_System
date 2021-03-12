package com.blz.springsecuritydemo.mapper;

import com.blz.springsecuritydemo.entity.Role;

import java.util.List;

/**
 * @Title: RoleMapper
 * @Package: com.blz.springsecuritydemo.mapper
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 上午10:53
 */
public interface RoleMapper {

    List<Role> queryByRoleCode(String roleCode);

    Role queryByRoleId(Integer roleId);

    List<Role> queryRoleByUserId(Integer userId);

    int insert(Role role);

    int update(Role role);

    int delete(Integer id);
}
