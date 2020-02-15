package com.tongtech.uesop.dto;

import lombok.Data;

@Data
public class ResponseResult<T> {
    T body;
    String msg;
    long state = 1;
}
