package com.jia.zuul.service;

import com.jia.zuul.feign.UserFeign;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private UserFeign userFeign;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("In userDetailService name="+s);
//        System.out.println("userFeign.test() = " + userFeign.test());
        com.jia.common.entity.User user = userFeign.findOne(s);
//        System.out.println("userFeign.test() = " + userFeign.test());
        System.out.println(user.toString());
        return new User(user.getAccount(),user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities()));
    }
}
