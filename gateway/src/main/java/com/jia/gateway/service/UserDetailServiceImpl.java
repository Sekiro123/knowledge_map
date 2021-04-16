package com.jia.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailServiceImpl implements ReactiveUserDetailsService {
    @Autowired
    private UserApi userApi;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Mono<UserDetails> findByUsername(String s) {
        System.out.println("find by user name function");
        System.out.println("username= "+s);
        String password = passwordEncoder.encode("123");
        UserDetails userDetails = User.withUsername("admin")
                .password(password)
                .roles("user")
                .build();
        return Mono.just(userDetails);
    }
}
