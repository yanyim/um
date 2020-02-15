package com.tongtech.uesop.dto;

import lombok.Data;

@Data
public class Permission {

    private int id;

    private String type;

    private String description;

    private String permission_name;

    private int parent_permission;

    private String code;

    private String status;

}
