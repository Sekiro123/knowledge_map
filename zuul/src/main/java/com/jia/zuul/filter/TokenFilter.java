package com.jia.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序
     * 当一个请求在同一个阶段存在多个过滤器的时候 过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 编写过滤器拦截业务逻辑代码
     */
    @Override
    public Object run() {
        return null;
    }
}
