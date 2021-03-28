package com.jia.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api("test class")
@Controller
public class TestController {
    @ApiOperation("test function")
    @RequestMapping("test")
    public String test(){
        return "success test!";
    }
}
