package com.tongtech.uesop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    @Value("${user.code}")
    private String admin_name;

    @Value("${user.pwd}")
    private String admin_pwd;

}
