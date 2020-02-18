package com.tongtech.uesop.dto;

import lombok.Data;

@Data
public class Permission {
    private Integer id;

    private String type;

    private String description;

    private String permissionName;

    private String code;

    private String status;

    private String rule;
}