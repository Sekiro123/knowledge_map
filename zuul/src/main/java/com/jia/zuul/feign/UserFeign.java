package com.jia.zuul.feign;

import com.jia.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "user", path = "")
public interface UserFeign {
    @RequestMapping("/findOne")
    public User findOne(@RequestBody String account);

}
