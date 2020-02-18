package com.tongtech.uesop.dto;

import lombok.Data;

@Data
public class UserRequest {
    User user;

    Integer[] roles;
}
