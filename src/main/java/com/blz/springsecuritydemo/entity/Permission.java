package com.blz.springsecuritydemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Title: Permission
 * @Package: com.blz.springsecuritydemo.entity
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 上午8:52
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    private Integer id;
    private String code;
    private String description;
    private String url;
}
