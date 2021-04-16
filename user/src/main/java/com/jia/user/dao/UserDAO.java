package com.jia.user.dao;

import com.jia.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    User findOne(String s);
    void save(User user);

//    void incUser(String username);
}
