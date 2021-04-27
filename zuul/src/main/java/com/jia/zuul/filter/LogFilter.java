package com.jia.zuul.filter;

import com.google.gson.Gson;
import com.jia.common.entity.log;
import com.jia.zuul.components.ApplicationContextGetBeanHelper;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

//@WebFilter
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("logFilter doFilter function");
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
//        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500/");
//        //允许跨域请求中携带cookie
//        resp.setHeader("Access-Control-Allow-Credentials", "true");
//        // 如果存在自定义的header参数，需要在此处添加，逗号分隔
//        resp.addHeader("Access-Control-Allow-Headers", "authorization,Origin, No-Cache, X-Requested-With, "
//                + "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, " + "Content-Type, X-E4M-With");
//        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        System.out.println("req.getCookies() = " + req.getCookies());
//        System.out.println("req.getParameterNames() = " + req.getParameterNames());
//        Enumeration<String> parameterNames = req.getParameterNames();
//        while(parameterNames.hasMoreElements()){
//            System.out.println("parameterNames.nextElement() = " + parameterNames.nextElement());
//        }
        if(SecurityContextHolder.getContext().getAuthentication()!=null){
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            if(name!=null){
                System.out.println("获取到 " + name+" 的身份，开始存储日志！");
                System.out.println("req.getRequestURI() = " + req.getRequestURI());
                String operation = req.getRequestURI();
                String ip=req.getRemoteAddr();
                StringBuffer extra_info=new StringBuffer();
                Enumeration<String> parameterNames = req.getParameterNames();
                while(parameterNames.hasMoreElements()){
                    extra_info.append(req.getParameter(parameterNames.nextElement()));
                }
                log log = new log(name, new Date(), operation, ip, extra_info.toString());
                String s = new Gson().toJson(log);
                RabbitTemplate rabbitTemplate = (RabbitTemplate) ApplicationContextGetBeanHelper.getBean("RabbitTemplate");
                rabbitTemplate.convertAndSend("amq.direct","direct-logs",s);
            }
        }
        System.out.println("日志存储完成，进入下一步");
        chain.doFilter(request,response);

//        if(resp.getStatus()!=200){
//            System.out.println("用户身份错误，清空cookie");
//            Cookie jsessionid = new Cookie("JSESSIONID", "");
//            jsessionid.setMaxAge(0);
//            resp.addCookie(jsessionid);
//        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Logfilter init() function!");
    }

    @Override
    public void destroy() {

    }
}
