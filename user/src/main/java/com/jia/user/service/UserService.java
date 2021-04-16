package com.jia.user.service;

import com.jia.common.entity.User;

public interface UserService {
    public String regist(User user);
    public String login(String account,String password);
    public User findOne(String account);
}
