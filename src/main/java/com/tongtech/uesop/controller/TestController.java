package com.tongtech.uesop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping(path = "/yans", method = RequestMethod.GET)
    public String listYanyan(HttpServletResponse response) throws IOException {
        return "fffff";
    }



}
