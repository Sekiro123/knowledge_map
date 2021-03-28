package com.jia.zuul.service;

import com.jia.user.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserApi userApi;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.jia.user.entity.User user = userApi.findOne(s);
        System.out.println(user.toString());
        return new User(user.getAccount(),user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities()));
    }
}
