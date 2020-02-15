package com.tongtech.uesop.dto;

import lombok.Data;

@Data
public class ListResult<T> {
    T body;
    int total;
    int currentPage;
    int pageSize;
    String msg;
    long state = 1;
}
