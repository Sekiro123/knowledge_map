package com.jia.zuul.handler;

import com.google.gson.Gson;
import com.jia.zuul.components.ApplicationContextGetBeanHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.jia.common.entity.log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String url;

    public MyAuthenticationSuccessHandler(String url){
        this.url=url;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        System.out.println("rabbitTemplate.toString() = " + rabbitTemplate.toString());
        RabbitTemplate rabbitTemplate = (RabbitTemplate) ApplicationContextGetBeanHelper.getBean("RabbitTemplate");
        System.out.println("httpServletRequest.getParameterNames() = " + httpServletRequest.getParameterNames());
        System.out.println("httpServletRequest.getParameter(\"username\") = " + httpServletRequest.getParameter("username"));
//       下面这一句其实拿到的是account，不是username。
        String remoteAddr = httpServletRequest.getRemoteAddr();
        log log = new log(httpServletRequest.getParameter("username"), new Date(), "login", remoteAddr, "success");
        String s = new Gson().toJson(log);
        rabbitTemplate.convertAndSend("amq.direct","direct-logs",s);

        System.out.println("jia");
        System.out.println("user.getAuthorities() = " + user.getAuthorities());
//        httpServletResponse.sendRedirect(url);
    }
}
