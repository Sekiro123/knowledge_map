package com.jia.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginController {
    @RequestMapping("login")
    public String login(){
        System.out.println("执行登录方法");
        return "main.html";
    }
    @RequestMapping("/test")
    public String test(){
        System.out.println("test   = " + true);
        return "main";
    }
}
