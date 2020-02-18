package com.tongtech.uesop.dto;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private Integer id;

    private String roleName;

    private Integer parentRole;

    private String roleCode;

    private String viewPermissions;

    private List<Permission> permissions;
}