package com.blz.springsecuritydemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Title: User
 * @Package: com.blz.springsecuritydemo.entity
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 上午8:46
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String mobile;
    private Date create_time;

    private List<Role> roleList;
    private List<Permission> permissionList;
}
