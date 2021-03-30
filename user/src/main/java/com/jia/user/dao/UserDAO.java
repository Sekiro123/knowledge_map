package com.jia.user.dao;

import com.jia.modeling.dao.BaseDAO;
import com.jia.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO extends BaseDAO<User,String> {
    @Override
    User findOne(String s);

//    void incUser(String username);
}
