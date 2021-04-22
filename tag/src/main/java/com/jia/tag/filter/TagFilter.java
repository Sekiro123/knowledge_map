//package com.jia.tag.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.google.gson.Gson;
//import com.jia.tag.entity.tag;
//import lombok.SneakyThrows;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Enumeration;
//
////@WebFilter(urlPatterns = "/tag/insert",filterName = "insert_filter")
//public class TagFilter implements Filter {
//    @SneakyThrows
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
//        String result = httpServletRequest.getParameter("result");
//        JSONObject jsonObject = new JSONObject(result);
//        StringBuffer ans = new StringBuffer();
//        for(int i=0;i<jsonObject.length();i++){
//            JSONArray jsonArray = jsonObject.getJSONArray(String.valueOf(i+1));
//            tag tag = new tag();
//            tag.setAccount(jsonArray.getString(0));
//            tag.setSubject(jsonArray.getString(1));
//            tag.setSubject_properties(jsonArray.getString(2));
//            tag.setObject(jsonArray.getString(3));
//            tag.setObject_properties(jsonArray.getString(4));
//            tag.setRelation(jsonArray.getString(5));
//            tag.setText(jsonArray.getString(6));
//            tag.setTime(new Date().toString());
//            String s = new Gson().toJson(tag);
//            System.out.println("s = " + s);
//            ans.append(s);
//            ans.append("#");
////            System.out.println("s = " + s);
//        }
//        httpServletRequest.setAttribute("result",ans);
//        System.out.println("httpServletRequest.getParameter(\"result\") = " + httpServletRequest.getParameter("result"));
//        System.out.println("ans.toString() = " + ans.toString());
//        chain.doFilter(httpServletRequest,response);
//        System.out.println("over!");
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("insert_filter start!");
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
