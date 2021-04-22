package com.jia.neo4j.service;

import com.alibaba.fastjson.JSON;
import com.jia.neo4j.dao.RedisUtil;
import com.jia.neo4j.dao.neo4jDAOImpl;
import com.jia.neo4j.entity.node;
import com.jia.neo4j.entity.node_index;
import com.jia.neo4j.entity.relation;
import com.jia.neo4j.entity.ret_type;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class neo4jServiceImpl implements neo4jService{
    @Autowired
    private neo4jDAOImpl neo4jDAO;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String findOne(String name) throws SQLException, JSONException {
//        neo4jDAOImpl neo4jDAO = new neo4jDAOImpl();
        if(redisUtil.hasKey("entity_"+name)){
            System.out.println("缓存中获取"+name);
            return redisUtil.get("entity_"+name);
        }
        ret_type ret_type = new ret_type();
        Set<String> set = redisUtil.findSet(name);
        ArrayList<List<String>> lists = new ArrayList<>();
        for (String s : set) {
            System.out.println("for");
            lists.add(this.neo4jDAO.findByName(name,s));
        }
        ArrayList<String> category = new ArrayList<>();
        HashMap<String, Integer> nodes = new HashMap<>();
        ArrayList<relation> relations = new ArrayList<>();
        for(int i=0;i<lists.size();i++){
            for(int j=0;j<lists.get(i).size();j++){
                String[] split = lists.get(i).get(j).substring(1,lists.get(i).get(j).length()-1).split("},");
                for(int k=0;k<split.length-1;k++){
                    split[k]=split[k]+"}";
                }
                JSONObject temp = new JSONObject(split[0]);
                System.out.println("temp.toString() = " + temp.toString());
//                String label1 = temp.getString("labels").substring(2, temp.getString("labels").length() - 2);
                String label1 = temp.getJSONArray("labels").toString().substring(2, temp.getJSONArray("labels").toString().length() - 2);
                String name1 = temp.getString("name");
                if(!category.contains(label1)){
                    category.add(label1);
                }
                nodes.put(name1,category.indexOf(label1));
                temp = new JSONObject(split[2]);
//                String label2 = temp.getString("labels").substring(2, temp.getString("labels").length() - 2);
                String label2 = temp.getJSONArray("labels").toString().substring(2, temp.getJSONArray("labels").toString().length() - 2);

                String name2 = temp.getString("name");
                if(!category.contains(label2)){
                    category.add(label2);
                }
                nodes.put(name2,category.indexOf(label2));
                temp = new JSONObject(split[1]);
                String r = temp.getString("type");
                relations.add(new relation(name1,name2,r));
            }
        }
        for(int i=0;i<category.size();i++){
            ret_type.getCategories().add(new node(category.get(i)));
        }
        for(String key:nodes.keySet()){
            ret_type.getNodes().add(new node_index(key,nodes.get(key)));
        }
        ret_type.setLinks(relations);
        System.out.println("ret_type.toString() = " + ret_type.toString());

        String s = JSON.toJSONString(ret_type);
        redisUtil.set("entity_"+name,s);
        redisUtil.expire("entity_"+name,3600);
        return s;
    }
}
