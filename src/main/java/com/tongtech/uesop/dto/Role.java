package com.tongtech.uesop.dto;

import lombok.Data;

@Data
public class Role {

    private int id;

    private String role_name;

    private int parent_role;

}
