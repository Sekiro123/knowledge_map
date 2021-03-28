package com.jia.modeling.controller;

import com.jia.modeling.entity.entity;
import com.jia.modeling.service.entityServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/entity")
@CrossOrigin
@Api("entity")
public class EntityController {
    @RequestMapping("test")
    public String test(){
        return "test";
    }
    @Autowired
    private entityServiceImpl entityService;
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation("entity insert")
    public String insert(@RequestBody String s){
        System.out.println("s = " + s);
        String[] split = s.split("\\.");
        System.out.println("split = " + split[0]);
        entity entity = new entity(split[0], split[1], "nothing");
        entityService.save(entity);
        return "success";
    }
    @RequestMapping("/findOne")
    @ResponseBody
    @ApiOperation("find an entity")
    public String findOne(@RequestBody @ApiParam("用户的相关信息") String s){
        Pattern compile = Pattern.compile("(rect-\\d+)\\ ?");
        Matcher m = compile.matcher(s);
        m.find();
        Pattern compile1 = Pattern.compile(m.group(1) + "\\\"\\,\\\"BlockContent\\\"\\:\\\"(.*?)\\\"");
        System.out.println(m.group(1) + "\\\",\\\"BlockContent\\\"\\:\\\"(.*?)\\\"");
        Matcher matcher = compile1.matcher(s);
        matcher.find();
        System.out.println("m.group(1) = " + matcher.group(1));
        entity one = this.entityService.findOne(matcher.group(1));
        System.out.println("this.entityService.findOne(matcher.group(1)) = " + this.entityService.findOne(matcher.group(1)));
//        System.out.println("this.entityService.findTotals() = " + this.entityService.findTotals());
        if(this.entityService.findOne(matcher.group(1))==null){
            return null;
        }
        System.out.println("one.getProperties() = " + one.getProperties());
        return one.getProperties();
    }
    @ApiOperation("testQueue")
    @RequestMapping("/testQueue")
    @ResponseBody
    public void testQueue(){
        entityService.incUser("ming");
    }
}
