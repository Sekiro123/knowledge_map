package com.jia.user.service;

import com.jia.user.dao.UserDAO;
import com.jia.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Override
    public String regist(User user){
        User one = userDAO.findOne(user.getAccount().toString());
        if(one!=null){
            return "用户已经存在！";
        }
        else{
            userDAO.save(user);
            return "注册成功";
        }
    }
    @Override
    public String login(String account,String password){
        User one = userDAO.findOne(account);
        System.out.println("one = " + one);
        if(one==null){
            return "用户未注册！";
        }
        else{
            if(password.equals(one.getPassword())){
                System.out.println("success");
                return "success";
            }
            else{
                return "密码错误！";
            }
        }
    }
    @Override
    public User findOne(String account){
        return userDAO.findOne(account);
    }
}
