package com.jia.modeling.controller;

import com.jia.common.entity.relation;
import com.jia.modeling.service.relationService;
import com.jia.modeling.service.relationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/relation")
@CrossOrigin
@Api("relation service")
public class RelationController {
    @Autowired
    private relationServiceImpl relationService;
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation("insert a relation")
    public String insert(@RequestBody @ApiParam("要插入的关系") relation relation) throws JSONException {

        try {
            relationService.save(relation);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }



    @ResponseBody
    @RequestMapping("deleteOne")
    public String deleteOne(@RequestBody relation relation){
        return relationService.delete(relation);
    }
    @ResponseBody
    @RequestMapping("/findOne")
    public String findOne(@RequestParam("field") String field, @RequestParam("author") String author){
        return relationService.findOne(field,author);
    }
    @ResponseBody
    @RequestMapping("/findFields")
    public String findFields(@RequestBody String author){
        return relationService.findFields(author);
    }

}
