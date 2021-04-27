package com.jia.user.controller;

import com.jia.common.entity.User;
import com.jia.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Api("User Controller")
@Controller
//@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;
    @ApiOperation("User regist")
    @RequestMapping("/regist")
    @ResponseBody
    public String regist(@RequestBody User user) throws JSONException, UnsupportedEncodingException {
//        User user1 = new User(user.getString("name"),user.getString("account"), user.getString("password"), user.getString("phoneNumber"), user.getString("payAccount"));
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        System.out.println(user.toString());
        String ret = userService.regist(user);
        return ret;
    }
    @RequestMapping("/test2")
    public void test2(@RequestParam User user){
        System.out.println(user.toString());
    }
    @ApiOperation("user login")
    @RequestMapping("login")
    @ResponseBody
    public String login(@RequestBody User user) throws UnsupportedEncodingException, JSONException {
//        String decode = URLDecoder.decode(msg, "UTF-8");
//        JSONObject content = new JSONObject(decode);
//        System.out.println("content = " + content);
//        String ret = userService.login(content.getString("account"), content.getString("password"));
//        return ret;
        String login = userService.login(user.getAccount(), user.getPassword());
        return login;
    }
    @ApiOperation("get user_info from database")
    @RequestMapping("/findOne")
    @ResponseBody
    public User findOne(@RequestBody String account){
        System.out.println("looking information for "+account);
//        User one = userService.findOne(account);
//        System.out.println("one.toString() = " + one.toString());
        return userService.findOne(account);
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test() throws InterruptedException {

        System.out.println("test function!");
        userService.test();
        return "testOK";
    }
}
