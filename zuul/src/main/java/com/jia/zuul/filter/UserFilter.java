package com.jia.zuul.filter;


import com.jia.zuul.utils.RequestParameterWrapper;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Security;
import java.util.HashMap;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(SecurityContextHolder.getContext()==null||SecurityContextHolder.getContext().getAuthentication()==null){

        }
        else{
            System.out.println("SecurityContextHolder.getContext().getAuthentication().getName() = " + SecurityContextHolder.getContext().getAuthentication().getName());
            request.setAttribute("account", SecurityContextHolder.getContext().getAuthentication().getName());
            System.out.println("request.getAttribute(\"account\") = " + request.getAttribute("account"));
        }
        chain.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
