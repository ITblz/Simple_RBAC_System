package com.blz.springsecuritydemo.service.impl;

import com.blz.springsecuritydemo.entity.Role;
import com.blz.springsecuritydemo.mapper.RoleMapper;
import com.blz.springsecuritydemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: RoleServiceImpl
 * @Package: com.blz.springsecuritydemo.service.impl
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 下午12:56
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> queryByRoleCode(String roleCode) {
        List<Role> roleList = roleMapper.queryByRoleCode(roleCode);
        if (roleList == null && roleList.size() == 0)
            return null;
        return roleList;
    }

    @Override
    public Role queryByRoleId(Integer roleId) {
        Role role = roleMapper.queryByRoleId(roleId);
        return role;
    }

    @Override
    public List<Role>queryRoleByUserId(Integer userId) {
        List<Role> roleList = roleMapper.queryRoleByUserId(userId);
        if (roleList == null && roleList.size() == 0)
            return null;
        return roleList;
    }
}
