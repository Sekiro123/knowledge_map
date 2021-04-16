package com.jia.modeling.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String insert(@RequestBody @ApiParam("要插入的关系") String s) throws JSONException {
        JSONObject jsonObject = new JSONObject(s);
        ArrayList<String> connections = new ArrayList<String>();
        ArrayList<String> sourceId = new ArrayList<String>();
        ArrayList<String> targetId = new ArrayList<String>();
        HashMap<String, String> dict = new HashMap<>();
        JSONArray connects = jsonObject.getJSONArray("connects");
//        System.out.println("jsonObject.getJSONArray(\"block\") = " + jsonObject.getJSONArray("block").getJSONObject(1));
        JSONArray block = jsonObject.getJSONArray("block");
        for(int i=0;i<connects.length();i++){
            connections.add(connects.getJSONObject(i).getString("Connectiontext"));
            sourceId.add(connects.getJSONObject(i).getString("PageSourceId"));
            targetId.add(connects.getJSONObject(i).getString("PageTargetId"));
        }
        for(int i=0;i<block.length();i++){
            dict.put(block.getJSONObject(i).getString("BlockId"),block.getJSONObject(i).getString("BlockContent"));
        }
        System.out.println("dict = " + dict);
        System.out.println("connections = " + connections);
        System.out.println("sourceId = " + sourceId);
        System.out.println("targetId = " + targetId);
        ArrayList<relation> relations = new ArrayList<>();

        for(int i=0;i<connections.size();i++){
            relations.add(new relation(dict.get(sourceId.get(i)),dict.get(targetId.get(i)),connections.get(i)));
        }
        System.out.println("relations = " + relations);
        for(int i=0;i<relations.size();i++){
            relationService.save(relations.get(i));
        }
        return "success";
    }

}
