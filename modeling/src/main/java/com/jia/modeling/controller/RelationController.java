package com.jia.modeling.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
//    插入一条关系
    public String insert(@RequestBody @ApiParam("要插入的关系") relation relation) throws JSONException {

        try {
            relationService.save(relation);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
    @RequestMapping("update")
    @ResponseBody
    public String update(@RequestParam("result") String message) throws JSONException, UnsupportedEncodingException {
        String message2= URLDecoder.decode(message,"UTF-8");
        System.out.println("message.toString() = " + message2.toString());
        JSONObject result = new JSONObject(message2);
//        JSONArray result = jsonObject.getJSONArray("result");
        ArrayList<relation> relations = new ArrayList<>();
        JSONArray names = result.names();
        for (int i = 0; i < names.length(); i++) {
            relation relation = new Gson().fromJson(result.getString(names.getString(i)), relation.class);

            System.out.println("relation.toString() = " + relation.toString());
            relations.add(relation);
        }
        return relationService.update(relations);
    }


    @ResponseBody
    @RequestMapping("deleteOne")
    public String deleteOne(@RequestBody relation relation){
        return relationService.delete(relation);
    }


//      找到某个用户在某个领域下的所有标注并以字符串的形式返回。
    @ResponseBody
    @RequestMapping("/findOne")
    public String findOne(@RequestParam("field") String field, @RequestParam("author") String author){
        return relationService.findOne(field,author);
    }
    @ResponseBody
    @RequestMapping("/findFields")
    public String findFields(@RequestParam("account") String author){
        return relationService.findFields(author);
    }
    @ResponseBody
    @RequestMapping("startTag")
    public String startTag(@RequestParam String field){

        return relationService.tagInfo(field);
    }
}
