package com.jia.modeling.service;

import com.alibaba.fastjson.JSON;
import com.jia.modeling.dao.RelationDAO;
import com.jia.common.entity.entity;
import com.jia.common.entity.relation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class relationServiceImpl implements relationService{
    @Autowired
    private RelationDAO relationDAO;
    @Override
    public void save(relation relation) {
        relationDAO.save(relation);
    }
    @Override
    public String update(List<relation> relations){
        if(relations.size()==0){
            return "failed";
        }else{
            relationDAO.deleteByUserField(relations.get(0).getAuthor(),relations.get(0).getField());
            for (int i = 0; i < relations.size(); i++) {
                relationDAO.save(relations.get(i));
            }
            return "success";
        }
    }
    @Override
    public String findOne(String field,String author) {
        List<relation> list = relationDAO.findOne(field, author);
        return JSON.toJSONString(list);
    }
    public String findFields(String author){
        List<String> fields = relationDAO.findFields(author);
        System.out.println("author = " + author);
        System.out.println("JSON.toJSONString(fields) = " + JSON.toJSONString(fields));
        return JSON.toJSONString(fields);
    }
    @Override
    public Integer findTotals() {
        return null;
    }
    public String delete(relation relation){
        relationDAO.delete(relation);
        return "success";
    }
    public String tagInfo(String field){
        List<relation> relations = relationDAO.findByField(field);
        HashSet<String> set = new HashSet<>();
        for (relation relation : relations) {
            System.out.println("relation.toString() = " + relation.toString());
        }
        for (relation relation : relations) {
            set.add(relation.getEntity1());
            set.add(relation.getEntity2());
        }
        HashMap<String, HashMap<String, List<String>>> map = new HashMap<>();
        for (String s : set) {
            map.put(s,new HashMap<String,List<String>>());
            for (String s1 : set) {
                map.get(s).put(s1,new ArrayList<String>());
            }
        }
        for (relation relation : relations) {
            map.get(relation.getEntity1()).get(relation.getEntity2()).add(relation.getRelation());
        }

//        JSON.toJSONString()

        return JSON.toJSONString(map);
    }
}
