package com.tongtech.uesop.dto;

import lombok.Data;

@Data
public class Department {
    private Integer id;

    private String departmentName;

    private String parentDepartmentName;

    private Integer parentDepartment;

    private String description;

    private Integer roleId;

    private String type;
}