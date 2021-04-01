package com.jia.tag.service;

import com.jia.tag.dao.RedisUtil;
import com.jia.tag.dao.TagDao;
import com.jia.tag.entity.tag;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class tagServiceImpl implements tagService{
    @Autowired
    private TagDao tagDao;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public void save(tag tag) {
        tagDao.save(tag);
    }

    @Override
    public List<tag> findAll() {
        return tagDao.findAll();
    }
    public String updateNeo4j(){
        List<tag> all = tagDao.findAll();
        Driver driver = GraphDatabase.driver("bolt://192.168.31.65:7687", AuthTokens.basic("neo4j", "123456"));
        Session session = driver.session();
        for(int i=0;i<all.size();i++){
            tag tag = all.get(i);
            tag.setSubject(tag.getSubject().replaceAll("·","_"));
            tag.setObject(tag.getObject().replaceAll("·","_"));
            tag.setSubject_properties(tag.getSubject_properties().replaceAll("·","_"));
            tag.setObject_properties(tag.getObject_properties().replaceAll("·","_"));
            tag.setRelation(tag.getRelation().replaceAll("·","_"));

//            System.out.println("tag = " + tag);
//            System.out.println(redisUtil.findSet(tag.getSubject()));
            session.run("merge(:"+tag.getSubject_properties()+"{name:'"+tag.getSubject()+"'})");
            redisUtil.addSet(tag.getSubject(),tag.getSubject_properties());
            session.run("merge(:"+tag.getObject_properties()+"{name:'"+tag.getObject()+"'})");
            redisUtil.addSet(tag.getObject(),tag.getObject_properties());
            session.run("match(a:"+tag.getSubject_properties()+"{name:'"+tag.getSubject()+"'}),(b:"+tag.getObject_properties()
                            +"{name:'"+tag.getObject()+"'}) create (a)-[r:"+tag.getRelation()+"]->(b)");
        }
        return "success";
    }

}
