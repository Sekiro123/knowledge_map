package com.jia.neo4j.controller;

import com.jia.neo4j.service.neo4jServiceImpl;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestController
@RequestMapping("neo4j")
public class neo4jController {
    @Autowired
    private neo4jServiceImpl neo4jService;
    @RequestMapping(value="findOne",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String findOne(@RequestParam("name") String name, HttpServletResponse response) throws SQLException, JSONException {
        System.out.println("controller start");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return neo4jService.findOne(name);
    }
}
