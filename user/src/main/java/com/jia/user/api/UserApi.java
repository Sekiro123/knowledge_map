package com.jia.user.api;

import com.jia.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="user",path="/user")
public interface UserApi {
    @RequestMapping("/findOne")
    public User findOne(@RequestBody String account);
}
