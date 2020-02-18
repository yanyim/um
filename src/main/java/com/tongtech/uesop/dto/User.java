package com.tongtech.uesop.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String userName;

    private String department;

    private String departmentName;

    private String status;

    private String avatarUrl;

    private String userId;

    private String email;

    private String phone;

    private String password;

    private Date createTime;

    private Date lastPasswordResetTime;

    private String group;

    private List<Role> roles;
}