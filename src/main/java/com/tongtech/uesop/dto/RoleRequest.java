package com.tongtech.uesop.dto;

import lombok.Data;

@Data
public class RoleRequest {

    private Role role;

    private  Integer[] permissions;
}
