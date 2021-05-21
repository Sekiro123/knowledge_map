package com.jia.zuul.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class RequestParameterWrapper extends HttpServletRequestWrapper {
    private Map<String,String[]> params=new HashMap<>();
    public RequestParameterWrapper(HttpServletRequest request, Map<String, String> extraParams) {
        this(request);
        addParameters(extraParams);
    }
    private RequestParameterWrapper(HttpServletRequest request){
        super(request);
        this.params.putAll(request.getParameterMap());
    }
    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<>(params.keySet());
        return vector.elements();
    }
    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }
    private void addParameters(Map<String, String> extraParams) {
        for (Map.Entry<String, String> entry : extraParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }
    private void addParameter(String name, String value) {
        if (value != null) {

            params.put(name, new String[]{value});

        }
    }
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }
    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }
}
