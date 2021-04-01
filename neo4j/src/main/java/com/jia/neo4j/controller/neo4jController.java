package com.jia.neo4j.controller;

import com.jia.neo4j.service.neo4jServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("neo4j")
public class neo4jController {
    @Autowired
    private neo4jServiceImpl neo4jService;
    @RequestMapping("findOne")
    @ResponseBody
    public String findOne(@RequestParam("name") String name){
        System.out.println("controller start");
        neo4jService.findOne(name);
        return null;
    }
}
