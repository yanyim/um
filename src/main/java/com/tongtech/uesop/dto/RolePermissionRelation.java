package com.tongtech.uesop.dto;

import lombok.Data;

@Data
public class RolePermissionRelation {
    private Integer rpId;

    private Integer roleId;

    private Integer prmissionId;
}