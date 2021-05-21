package com.jia.zuul.config;

import com.jia.zuul.filter.LogFilter;
import com.jia.zuul.filter.UserFilter;
import com.jia.zuul.handler.MyAccessDeniedHandler;
import com.jia.zuul.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.URL;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean(value = "rabbitjiaxiwang")
//    public RabbitTemplate a(){
//        return new RabbitTemplate();
//    }
    @Bean
    public String getUrl(){
        return new String();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").permitAll();
        URL failure = new URL("https://www.baidu.com");
        http.formLogin()
                .loginPage("http://3580656qa1.qicp.vip/KGcloud/login.html")
                .loginProcessingUrl("/login")
                .successHandler(new MyAuthenticationSuccessHandler("http://3580656qa1.qicp.vip/KGcloud/entity.html"));
        http.authorizeRequests()
                .antMatchers("/user/**").hasAuthority("user")
                .antMatchers("/modeling/**").hasAuthority("professor")
                .antMatchers("/neo4j/**").hasAuthority("user")
                .anyRequest().authenticated();

        http.addFilterAfter(new UserFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new LogFilter(),UserFilter.class);
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
        http.csrf().disable();

    }
}
