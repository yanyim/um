package com.tongtech.uesop.dto;

import lombok.Data;
import sun.awt.PeerEvent;

@Data
public class DepartmentRequest {

    Department department;

    Permission permission;

    Role role;
}
