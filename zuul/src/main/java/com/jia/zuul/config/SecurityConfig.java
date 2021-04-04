package com.jia.zuul.config;

import com.jia.user.api.UserApi;
import com.jia.user.entity.User;
import com.jia.zuul.handler.MyAccessDeniedHandler;
import com.jia.zuul.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(new MyAuthenticationSuccessHandler("http://3580656qa1.qicp.vip/KGcloud/entity.html"));
        http.authorizeRequests()
                .antMatchers("login.html").permitAll()
                .antMatchers("/user/**").hasAuthority("user")
                .antMatchers("/modeling/**").hasAuthority("user")
                .antMatchers("/neo4j/**").hasAuthority("user")
                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
        http.csrf().disable();

    }
}
