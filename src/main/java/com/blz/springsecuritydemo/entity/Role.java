package com.blz.springsecuritydemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Title: Role
 * @Package: com.blz.springsecuritydemo.entity
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 上午8:48
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Integer id;
    private String code;
    private String description;
    private Date create_time;
    private Date update_time;

    private List<Permission> permissionList;
}
