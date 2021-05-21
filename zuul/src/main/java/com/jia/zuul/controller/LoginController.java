package com.jia.zuul.controller;


import com.jia.zuul.feign.UserFeign;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("zuul")
public class LoginController {
    @Autowired
    private UserFeign userFeign;
    @RequestMapping("findUserInfoByCookie")
    public String findUserInfoByCookie(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("cookie.toString() = " + cookie.toString());
        }
//        userFeign.findUserInfo()
        return "success";
    }
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
