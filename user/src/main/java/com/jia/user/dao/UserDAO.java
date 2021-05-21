package com.jia.user.dao;

import com.jia.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    User findOne(String s);
    void save(User user);
    void incNum_tag(String account);
//    void incUser(String username);
    void incNum_articles(String account);
    void incNum_sentences(String account);
}
