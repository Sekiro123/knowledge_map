package com.jia.modeling.service;

import com.alibaba.fastjson.JSON;
import com.jia.modeling.dao.RelationDAO;
import com.jia.common.entity.entity;
import com.jia.common.entity.relation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
