package com.tongtech.uesop.dto;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String userName;

    private String department;

    private String status;

    private String avatarUrl;

    private String userId;
}