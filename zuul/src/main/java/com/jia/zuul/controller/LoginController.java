package com.jia.zuul.controller;


import org.apache.ibatis.plugin.Intercepts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("toMain")
    public String toMain(){
        return "redirect:main.html";
    }
    @RequestMapping("toError")
    public String toError(){
        return "redirect:error.html";
    }
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
